package com.mcflythekid.lazylearncore;

import java.util.HashMap;
import java.util.Map;

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

    public static final Integer CARD_STEP_BEGIN = 0;
    public static final Integer CARD_STEP_END = 6;

    public static final String LEARNTYPE_LEARN = "learn";
    public static final String LEARNTYPE_REVIEW = "review";

    public static Map<Integer, Integer> CARD_STEP_MAP;
    static{
        CARD_STEP_MAP = new HashMap<>();
        CARD_STEP_MAP.put(1, 1);
        CARD_STEP_MAP.put(2, 5);
        CARD_STEP_MAP.put(3, 25);
        CARD_STEP_MAP.put(4, 125);
        CARD_STEP_MAP.put(5, 625);
    }

    public static Map<Integer, String> CARD_STEP_LABEL_MAP;
    static{
        CARD_STEP_LABEL_MAP = new HashMap<>();
        CARD_STEP_LABEL_MAP.put(0, "Now");
        CARD_STEP_LABEL_MAP.put(1, "1 day");
        CARD_STEP_LABEL_MAP.put(2, "5 days");
        CARD_STEP_LABEL_MAP.put(3, "25 days");
        CARD_STEP_LABEL_MAP.put(4, "4 months");
        CARD_STEP_LABEL_MAP.put(5, "2 years");
        CARD_STEP_LABEL_MAP.put(6, "Done");
    }
}
