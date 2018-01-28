package com.mcflythekid.lazylearncore;

/**
 * @author McFly the Kid
 * @JsonFormat(pattern = "", timezone = "")
 */
public final class Const {
    public static final Integer PARAM_PORT = 8088;
    public static final String PARAM_JSON_DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String PARAM_JSON_TIMEZONE = "GMT+7";

    public static final String ROLE_DEFAULT = "DEFAULT";

    public static final String RET_OK = "ok";
    public static final String RET_ERROR = "error";

    public static final Integer FORGETPASSWORD_STATUS_NEW = 0;
    public static final Integer FORGETPASSWORD_STATUS_USED = 1;
    public static final Integer FORGETPASSWORD_EXPIRED_DAYS = 10;
}
