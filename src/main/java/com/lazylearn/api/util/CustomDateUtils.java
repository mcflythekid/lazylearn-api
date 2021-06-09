package com.lazylearn.api.util;

import java.util.Date;

public final class CustomDateUtils {

    private CustomDateUtils() {
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        final double dayMiliseconds = 24 * 3600 * 1000 * 1.0;
        long diff = Math.abs(d2.getTime() - d1.getTime());
        return Math.round(diff / dayMiliseconds);

    }
}
