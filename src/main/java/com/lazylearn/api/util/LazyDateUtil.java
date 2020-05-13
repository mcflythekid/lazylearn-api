package com.lazylearn.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LazyDateUtil {

    private static final String FORMAT_YEAR_MONTH = "yyyy_MM";
    private static final String FORMAT_DAY = "dd";

    public static String generateCurrentYyyyMm(){
        return new SimpleDateFormat(FORMAT_YEAR_MONTH).format(new Date());
    }

    public static String generateCurrentDd(){
        return new SimpleDateFormat(FORMAT_DAY).format(new Date());
    }
}
