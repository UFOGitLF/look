package com.fly.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * <p>
 * Created by xinshidai on 17/9/25.
 */
public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
        return null;
    }

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatTime(Date date) {
        if (date != null) {
            return format(date, DATE_TIME_PATTERN);
        }
        return null;
    }
}
