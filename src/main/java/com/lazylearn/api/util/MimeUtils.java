package com.lazylearn.api.util;

import org.apache.tika.Tika;

import java.util.Base64;

/**
 * @author McFly the Kid
 */
public final class MimeUtils {
    private MimeUtils() {
    }

    public static String getMimeString(String encodedFile) {
        return new Tika().detect(Base64.getDecoder().decode(encodedFile));
    }

    public static String getMimeString(byte[] bytes) {
        return new Tika().detect(bytes);
    }

    /**
     * Example:
     * image/.+
     * audio/.+
     *
     * @param bytes
     * @param regex
     * @return Boolean
     */
    public static boolean validateMime(byte[] bytes, String regex) {
        return getMimeString(bytes).matches(regex);
    }

    /**
     * Example:
     * image/.+
     * audio/.+
     *
     * @param encodedFile
     * @param regex
     * @return Boolean
     */
    public static boolean validateMime(String encodedFile, String regex) {
        return getMimeString(encodedFile).matches(regex);
    }
}