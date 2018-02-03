package com.mcflythekid.lazylearncore;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * @author McFly the Kid
 */
public final class Utils {

    public static Object getField(Object obj, String field){
        try {
            Field f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);//Very important, this allows the setting to work.
            return f.get(obj);
        } catch (Exception e){
            return null;
        }
    }

    public static String randomString(Integer length){
        final String SALTCHARS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}
