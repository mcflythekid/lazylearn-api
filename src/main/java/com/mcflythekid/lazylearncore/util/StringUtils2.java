package com.mcflythekid.lazylearncore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author McFly the Kid
 */
public final class StringUtils2 {
    private StringUtils2() {}

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