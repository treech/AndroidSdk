package io.github.treech.net;

public interface HttpConstant {
    /**
     * 是否成功
     */
    int IS_SUCCESS = 200;

    /**
     * 是否有权限
     */
    int IS_PERMISSION_DENIED = 403;

    /**
     * token过期
     */
    int IS_OUT_OF_DATE = 70019;

    /**
     * 授权服务即将过期
     */
    int AUTHORIZATION_IS_ABOUT_TO_EXPIRE = 40102;

    /**
     * 账号在另一端登录被踢下线
     */
    int IS_KICK_OFF = 40004;

    /**
     * 无效的返回结果
     */
    int IS_INVALID = 500;

    interface HEADER {
        String ACCEPT_CONTENT = "Accept: */*";
        String CONTENT_TYPE_JSON = "Content-type: application/json";
        String CONTENT_TYPE_FORM = "Content-type: application/x-www-form-urlencoded";
        String USER_AGENT = "User-Agent";
        String DOMAIN_NAME = "Domain-Name";
    }
}
