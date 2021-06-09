package com.lazylearn.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
public final class DateUtils2 {
    private DateUtils2() {
    }

    public static Date getRemoteTimezoneMidnightDate(String remoteTimezone) throws ParseException {
        final String fullFormat = "yyyy-MM-dd HH:mm:ss";
        final String shortFormat = "yyyy-MM-dd";
        SimpleDateFormat localFullFormatter = new SimpleDateFormat(fullFormat);
        SimpleDateFormat remoteShortFormatter = new SimpleDateFormat(shortFormat);
        SimpleDateFormat remoteFullFormatter = new SimpleDateFormat(fullFormat);
        remoteShortFormatter.setTimeZone(TimeZone.getTimeZone(remoteTimezone));
        remoteFullFormatter.setTimeZone(TimeZone.getTimeZone(remoteTimezone));

        String remoteShortNow = remoteShortFormatter.format(new Date());
        String remoteFullAtRemoteMidnightString = remoteShortNow + " 00:00:00";
        Date remoteFullAtRemoteMidnightDate = remoteFullFormatter.parse(remoteFullAtRemoteMidnightString);

        return remoteFullAtRemoteMidnightDate;
    }

    public static void main(String[] args) throws ParseException {
        String format = "yyyy-MM-dd HH:mm:ss";
        for (int i = 12; i >= 1; i--) {
            String gmt = "GMT+" + i;
            Date date = getRemoteTimezoneMidnightDate(gmt);
            String s = new SimpleDateFormat(format).format(date);
            System.out.println(gmt + ": " + s);
        }
        for (int i = 1; i <= 12; i++) {
            String gmt = "GMT-" + i;
            Date date = getRemoteTimezoneMidnightDate(gmt);
            String s = new SimpleDateFormat(format).format(date);
            System.out.println(gmt + ": " + s);
        }
    }
}
