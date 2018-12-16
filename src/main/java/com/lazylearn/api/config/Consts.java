package com.lazylearn.api.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author McFly the Kid
 */
public final class Consts {
    public static final String PARAM_JSON_DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PARAM_JSON_TIMEZONE = "GMT+7";
    public static final int PARAM_POST_MAXSIZE = 10000000;
    public static final int PARAM_JWT_SECONDS = 3600 * 24 * 365 * 10;

    public static final String AUTHORITY_DEFAULT = "default";
    public static final String AUTHORITY_ADMIN = "admin";
    public static final String AUTHORITY_FACEBOOK = "facebook";

    public static final String RET_OK = "ok";
    public static final String RET_ERROR = "error";

    public static final Integer FORGETPASSWORD_EXPIRED_DAYS = 10;

    public static final String LEARNTYPE_LEARN = "learn";
    public static final String LEARNTYPE_REVIEW = "review";

    public static final Integer CARD_STEP_BEGIN = 0;
    public static final String CARD_STEP_BEGIN__LABEL = "NOW";
    public static final String CARD_STEP_END__LABEL = "DONE";

    public static final String STEP_PROGRAM__PIMSLEUR = "pimsleur";
    public static final String STEP_PROGRAM__EFFORTLESS = "effortless";
    public static final String STEP_PROGRAM_BIGGEST = STEP_PROGRAM__EFFORTLESS;

    public static final Integer CARDDECK_ARCHIVED = 1;
    public static final Integer CARDDECK_UNARCHIVED = 0;

    public static final Integer CARDDECK_SHARED = 1;
    public static final Integer CARDDECK_UNSHARED = 0;


}
