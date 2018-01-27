package com.mcflythekid.lazylearncore;

import java.lang.reflect.Field;

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
}
