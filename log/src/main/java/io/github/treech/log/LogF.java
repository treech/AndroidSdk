package io.github.treech.log;

import static io.github.treech.log.parse.ObjectUtil.objectToString;

import android.text.TextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogF {

    private static final String DEFAULT_TAG = "LogF";
    private static boolean sDebug = true;
    private static String sDefaultTag = null;
    private static final Logger mLogger = LoggerFactory.getLogger(DEFAULT_TAG);

    private static String getClassName() {
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        if (sElements.length >= 4) {
            return sElements[3].getFileName().replace(".java", "");
        }
        return TextUtils.isEmpty(sDefaultTag) ? DEFAULT_TAG : sDefaultTag;
    }

    private static void writeLogToFile(String tag, String msg) {
        if (sDebug) {
            mLogger.info(String.format("[TAG:%s] %s", TextUtils.isEmpty(tag) ? DEFAULT_TAG : tag, msg));
        }
    }

    private static int E(String tag, String msg) {
        writeLogToFile(tag, msg);
        return android.util.Log.e(tag, msg);
    }

    private static int W(String tag, String msg) {
        writeLogToFile(tag, msg);
        return android.util.Log.w(tag, msg);
    }

    private static int I(String tag, String msg) {
        writeLogToFile(tag, msg);
        return android.util.Log.i(tag, msg);
    }

    private static int D(String tag, String msg) {
        writeLogToFile(tag, msg);
        return android.util.Log.d(tag, msg);
    }

    private static int V(String tag, String msg) {
        writeLogToFile(tag, msg);
        return android.util.Log.v(tag, msg);
    }

    public static int e(Object msg) {
        return E(getTag(null), getMsg(msg));
    }

    public static int w(Object msg) {
        return W(getTag(null), getMsg(msg));
    }

    public static int i(Object msg) {
        return I(getTag(null), getMsg(msg));
    }

    public static int d(Object msg) {
        return D(getTag(null), getMsg(msg));
    }

    public static int v(Object msg) {
        return V(getTag(null), getMsg(msg));
    }

    public static int e() {
        return E(getTag(null), getStackTraceString(new Throwable()));
    }

    public static int w() {
        return W(getTag(null), getStackTraceString(new Throwable()));
    }

    public static int i() {
        return I(getTag(null), getStackTraceString(new Throwable()));
    }

    public static int d() {
        return D(getTag(null), getStackTraceString(new Throwable()));
    }

    public static int v() {
        return V(getTag(null), getStackTraceString(new Throwable()));
    }

    public static int e(String tag, String msg, Throwable tr) {
        return E(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int w(String tag, String msg, Throwable tr) {
        return W(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int i(String tag, String msg, Throwable tr) {
        return I(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int d(String tag, String msg, Throwable tr) {
        return D(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int v(String tag, String msg, Throwable tr) {
        return V(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    private static String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

    public static int w(String tag, Throwable tr) {
        return W(tag, getStackTraceString(tr));
    }

    private static String getMessage(Object obj) {
        if (obj != null) {
            return objectToString(obj);
        }
        return "";
    }

    public static int e(Object tag, Object obj) {
        return E(getTag(tag), getMsg(obj));
    }

    public static int w(Object tag, Object obj) {
        return W(getTag(tag), getMsg(obj));
    }

    public static int i(Object tag, Object obj) {
        return I(getTag(tag), getMsg(obj));
    }

    public static int d(Object tag, Object obj) {
        return D(getTag(tag), getMsg(obj));
    }

    public static int v(Object tag, Object obj) {
        return V(getTag(tag), getMsg(obj));
    }

    private static String getTag(Object tag) {
        String logTag = null;
        if (tag != null) {
            if (tag instanceof String) {
                logTag = (String) tag;
            } else {
                logTag = tag.getClass().getSimpleName();
            }
        }
        if (TextUtils.isEmpty(logTag)) {
            logTag = getClassName();
        }
        return logTag;
    }

    private static String getMsg(Object obj) {
        String msg = null;
        if (obj != null) {
            if (obj instanceof String) {
                msg = (String) obj;
            } else {
                msg = getMessage(obj);
            }
        }
        msg = appendLogLocation(msg);
        return msg;
    }

    private static String appendLogLocation(String msg) {
        StackTraceElement[] sElements = Thread.currentThread().getStackTrace();
        if (sElements.length >= 6) {
            int index = 5;
            String className = sElements[index].getFileName();
            //处理LogF调用
            if (!TextUtils.isEmpty(className) && className.equals("LogF.java")) {
                if (sElements.length >= 7) {
                    index = 6;
                    className = sElements[index].getFileName();
                } else {
                    return msg;
                }
            }
            String methodName = sElements[index].getMethodName();
            int lineNumber = sElements[index].getLineNumber();
            String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodNameShort).append(" ] ");
            if (!TextUtils.isEmpty(msg)) {
                stringBuilder.append(msg);
            }
            return stringBuilder.toString();
        }
        return msg;
    }

    public static void setFileDebuggable(boolean debug) {
        sDebug = debug;
    }

    public static boolean getFileDebuggable() {
        return sDebug;
    }

    public static void setDefaultTag(String defaultTag) {
        sDefaultTag = defaultTag;
    }

}
