package com.lazylearn.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author McFly the Kid
 */
public final class StringUtils2 {
    private StringUtils2() {}


    private static final String READABLE_ID_FORMAT_DATE = "yyyy_MM_dd";

    public static String generateReadableId(String prefix, int suffixLength){
        return new StringBuilder()
                .append(prefix)
                .append("_")
                .append(new SimpleDateFormat(READABLE_ID_FORMAT_DATE).format(new Date()))
                .append("_")
                .append(randomBeautyString(suffixLength))
                .toString();
    }

    public static String removeTabAndDoubleSpaceAndTrim(String input){
        String data = input;
        if (isBlank(data)){
            return data;
        }
        data = data.replaceAll("\\s{2,}", " ");
        data = data.replaceAll("\\t", "");
        return data.trim();
    }

    public static String generateRandomId(){
        return randomBeautyString(32);
    }

    public static String randomBeautyString(Integer length){
        final String SALTCHARS = "QAZXWECRFVBGTYHNUMKLP";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}