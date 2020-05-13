package com.lazylearn.api.config;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.util.SecurityUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

import static com.lazylearn.api.util.SecurityUtils.getCurrentUserLogin;

/**
 * @author McFly the Kid
 */
public final class Consts {

    public static class User{
        public static final String SYSTEM = "system";
    }

    public static class VocabTemplate {
        public static final int WORD_LIMIT = 3;
        public static final int LANGUAGE_ENGLISH = 1;
        public static final int PLATFORM_OXFORD_LEARNER = 1;
        public static final AppException LANG_NOT_FOUND = new AppException(404, "Language not found");
        public static final AppException INVALID_WORD = new AppException(403, "Invalid word");
        public static final AppException TOO_MANY_WORD = new AppException(403, "Too many words");
    }

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

    public static class Admin {
        static String[] ADMINS = new String[]{
                "20180204ndTJOOObNawFuTrrANnzZndoZyjjbK" //odopoc
        };
        public static boolean isAdmin(){
            String username = "";
            try {
                username = getCurrentUserLogin();
            } catch (java.lang.Exception exception) {  }
            if (ArrayUtils.contains(ADMINS, username)){
                return true;
            }
            return false;
        }
    }

}
