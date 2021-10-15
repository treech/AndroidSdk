package io.github.treech.log.core.logging;

import android.text.TextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;


@SuppressWarnings("ConstantConditions")
public class Slf4jFormatPrinter implements FormatPrinter {
    private static final String TAG = "HttpLog";
    private static final Logger LOGGER = LoggerFactory.getLogger(TAG);
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final String N = "\n";
    private static final String T = "\t";
    private static final String REQUEST_UP_LINE = "   ┌────── Request ────────────────────────────────────────────────────────────────────────";
    private static final String END_LINE = "   └───────────────────────────────────────────────────────────────────────────────────────";
    private static final String RESPONSE_UP_LINE = "   ┌────── Response ───────────────────────────────────────────────────────────────────────";
    private static final String BODY_TAG = "Body:";
    private static final String URL_TAG = "URL: ";
    private static final String METHOD_TAG = "Method: @";
    private static final String HEADERS_TAG = "Headers:";
    private static final String STATUS_CODE_TAG = "Status Code: ";
    private static final String RECEIVED_TAG = "Received in: ";
    private static final String CORNER_UP = "┌ ";
    private static final String CORNER_BOTTOM = "└ ";
    private static final String CENTER_LINE = "├ ";
    private static final String DEFAULT_LINE = "│ ";
    private static final String[] ARMS = new String[]{"-A-", "-R-", "-M-", "-S-"};
    private static ThreadLocal<Integer> last = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || N.equals(line) || T.equals(line) || TextUtils.isEmpty(line.trim());
    }

    private static String computeKey() {
        if (last.get() >= 4) {
            last.set(0);
        }
        String s = ARMS[last.get()];
        last.set(last.get() + 1);
        return s;
    }

    /**
     * 此方法是为了解决在 AndroidStudio v3.1 以上 Logcat 输出的日志无法对齐的问题
     * <p>
     * 此问题引起的原因, 据 JessYan 猜测, 可能是因为 AndroidStudio v3.1 以上将极短时间内以相同 tag 输出多次的 log 自动合并为一次输出
     * 导致本来对称的输出日志, 出现不对称的问题
     * AndroidStudio v3.1 此次对输出日志的优化, 不小心使市面上所有具有日志格式化输出功能的日志框架无法正常工作
     * 现在暂时能想到的解决方案有两个: 1. 改变每行的 tag (每行 tag 都加一个可变化的 token) 2. 延迟每行日志打印的间隔时间
     * <p>
     * {@link #resolveTag(String)} 使用第一种解决方案
     *
     * @param tag
     */
    private static String resolveTag(String tag) {
        return computeKey() + tag;
    }

    private static String getRequest(Request request) {
        String log;
        String header = request.headers().toString();
        log = METHOD_TAG + request.method() + LINE_SEPARATOR +
                (isEmpty(header) ? "" : HEADERS_TAG + LINE_SEPARATOR + dotHeaders(header));
        return log;
    }

    private static String getResponse(String header, long tookMs, int code, boolean isSuccessful,
                                      List<String> segments, String message) {
        String log;
        String segmentString = slashSegments(segments);
        log = ((!TextUtils.isEmpty(segmentString) ? segmentString + " - " : "") + "is success : "
                + isSuccessful + " - " + RECEIVED_TAG + tookMs + "ms" + LINE_SEPARATOR + STATUS_CODE_TAG +
                code + " / " + message + LINE_SEPARATOR + (isEmpty(header) ? "" : HEADERS_TAG + LINE_SEPARATOR +
                dotHeaders(header)));
        return log;
    }

    private static String slashSegments(List<String> segments) {
        StringBuilder segmentString = new StringBuilder();
        for (String segment : segments) {
            segmentString.append("/").append(segment);
        }
        return segmentString.toString();
    }

    /**
     * 对 {@code header} 按规定的格式进行处理
     *
     * @param header
     * @return
     */
    private static String dotHeaders(String header) {
        String[] headers = header.split(LINE_SEPARATOR);
        StringBuilder builder = new StringBuilder();
        String tag = "─ ";
        if (headers.length > 1) {
            for (int i = 0; i < headers.length; i++) {
                if (i == 0) {
                    tag = CORNER_UP;
                } else if (i == headers.length - 1) {
                    tag = CORNER_BOTTOM;
                } else {
                    tag = CENTER_LINE;
                }
                builder.append(tag).append(headers[i]).append("\n");
            }
        } else {
            for (String item : headers) {
                builder.append(tag).append(item).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * 打印网络请求信息, 当网络请求时 {{@link okhttp3.RequestBody}} 可以解析的情况
     *
     * @param request
     * @param bodyString
     */
    @Override
    public void printJsonRequest(Request request, String bodyString) {
        final String requestBody = LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + bodyString;

        LOGGER.info(REQUEST_UP_LINE);
        LOGGER.info(URL_TAG + request.url());
        LOGGER.info(getRequest(request));
        LOGGER.info(requestBody);
        LOGGER.info(END_LINE);
    }

    /**
     * 打印网络请求信息, 当网络请求时 {{@link okhttp3.RequestBody}} 为 {@code null} 或不可解析的情况
     *
     * @param request
     */
    @Override
    public void printFileRequest(Request request) {

        LOGGER.info(REQUEST_UP_LINE);
        LOGGER.info(URL_TAG + request.url());
        LOGGER.info(getRequest(request));
        LOGGER.info("Omitted request body");
        LOGGER.info(END_LINE);
    }

    /**
     * 打印网络响应信息, 当网络响应时 {{@link okhttp3.ResponseBody}} 可以解析的情况
     *
     * @param chainMs      服务器响应耗时(单位毫秒)
     * @param isSuccessful 请求是否成功
     * @param code         响应码
     * @param headers      请求头
     * @param contentType  服务器返回数据的数据类型
     * @param bodyString   服务器返回的数据(已解析)
     * @param segments     域名后面的资源地址
     * @param message      响应信息
     * @param responseUrl  请求地址
     */
    @Override
    public void printJsonResponse(long chainMs, boolean isSuccessful, int code, String headers, MediaType contentType,
                                  String bodyString, List<String> segments, String message, final String responseUrl) {
        bodyString = RequestInterceptor.isJson(contentType) ? CharacterHandler.jsonFormat(bodyString)
                : RequestInterceptor.isXml(contentType) ? CharacterHandler.xmlFormat(bodyString) : bodyString;

        final String responseBody = LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + bodyString;

        LOGGER.info(RESPONSE_UP_LINE);
        LOGGER.info(URL_TAG + responseUrl);
        LOGGER.info(getResponse(headers, chainMs, code, isSuccessful, segments, message));
        LOGGER.info(responseBody);
        LOGGER.info(END_LINE);
    }

    /**
     * 打印网络响应信息, 当网络响应时 {{@link okhttp3.ResponseBody}} 为 {@code null} 或不可解析的情况
     *
     * @param chainMs      服务器响应耗时(单位毫秒)
     * @param isSuccessful 请求是否成功
     * @param code         响应码
     * @param headers      请求头
     * @param segments     域名后面的资源地址
     * @param message      响应信息
     * @param responseUrl  请求地址
     */
    @Override
    public void printFileResponse(long chainMs, boolean isSuccessful, int code, String headers,
                                  List<String> segments, String message, final String responseUrl) {

        LOGGER.info(RESPONSE_UP_LINE);
        LOGGER.info(URL_TAG + responseUrl);
        LOGGER.info(getResponse(headers, chainMs, code, isSuccessful, segments, message));
        LOGGER.info("Omitted response body");
        LOGGER.info(END_LINE);
    }

}
