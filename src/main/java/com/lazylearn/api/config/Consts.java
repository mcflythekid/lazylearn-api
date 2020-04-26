package com.lazylearn.api.config;

import com.lazylearn.api.config.exception.AppException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author McFly the Kid
 */
public final class Consts {

    public static final Double SM2_EF_MIN = 1.3D;
    public static final Double SM2_EF_INIT = 2.5D;
    public static final Integer[] SM2_SPACE_STEP = new Integer[]{0, 1, 6};
    public static final Integer[] SM2_ALLOWS_CORRECT_QUALITY = new Integer[]{3,4,5};
    public static final Integer SM2_INCORRECT_QUALITY = 0;
    public static final Integer SM2_CORRECT_QUALITY_DEFAULT = 5;

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

    public static final Integer STEP_BEGIN = 0;

    public static final String PROGRAM__SM2 = "sm2";
    public static final String PROGRAM__DEFAULT = "sm2";

    public static final Integer CARDDECK_ARCHIVED = 1;
    public static final Integer CARDDECK_UNARCHIVED = 0;
    public static final Integer CARDDECK_SHARED = 1;
    public static final Integer CARDDECK_UNSHARED = 0;

    public static final String DO_NOT_CHANGE = "Do not modify this card";



    public static final String VOCABDECK_LANGUAGE = "english";

    public static final String DECKTYPE__TOPIC = "topic";
    public static final String DECKTYPE__VOCAB = "vocab";
    public static final String DECKTYPE__MINPAIR = "minpair";

    public static class Deck{
        public static final String LEARN_ALL_DECK_ID = "all-deck";
        public static final String LEARN_ALL_DECK_NAME = "All cards";
    }

    public static class Exception {
        public static final AppException CANNOT_INSPECT_VIRTUAL_DECK = new AppException(503, "Cannot inspect virtual deck");
        public static final AppException NOT_FOUND = new AppException(404, "Item not found");
        public static final AppException INVALID_OWNERSHIP = new AppException(403, "Invalid owner ship");
        public static final AppException PROTECTED_VOCABDECK = new AppException(403, "VOCABDECK protected");
        public static final AppException PROTECTED_MINPAIR = new AppException(403, "MINPAIR protected");
        public static final AppException PROTECTED_TOPIC = new AppException(403, "TOPIC protected");

    }

}
