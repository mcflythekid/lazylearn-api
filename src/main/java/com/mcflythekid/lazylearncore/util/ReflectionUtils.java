package com.mcflythekid.lazylearncore.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * @author McFly the Kid
 */
public final class ReflectionUtils {

    private ReflectionUtils(){}

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