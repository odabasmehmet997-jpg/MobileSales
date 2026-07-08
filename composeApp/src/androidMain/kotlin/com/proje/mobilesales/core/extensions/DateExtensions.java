package com.proje.mobilesales.core.extensions;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;

public final class DateExtensions {
    public static   String toDateStringdefault(String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "yyyy-MM-dd HH:mm:ss";
        }
        if ((i2 & 2) != 0) {
            str3 = "dd.MM.yyyy HH:mm:ss";
        }
        if ((i2 & 4) != 0) {
            str4 = "-";
        }
        return toDateString(str, str2, str3, str4);
    }

    public static String toDateString(String str, String inputFormat, String outputFormat, String defaultValue) {
        String str2;
        Date parse = null;
        Intrinsics.checkNotNullParameter(inputFormat, "inputFormat");
        Intrinsics.checkNotNullParameter(outputFormat, "outputFormat");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (str == null || str.length() == 0) {
            return defaultValue;
        }
        try {
            parse = new SimpleDateFormat(inputFormat).parse(str);
        } catch (ParseException e2) {
            Log.e("StringExtensions", "toDateString: ", e2);
            str2 = "";
        }
        if (parse == null) {
            return defaultValue;
        }
        str2 = new SimpleDateFormat(outputFormat).format(parse);
        return str2 == null ? defaultValue : str2;
    }

    public static Date toDatedefault(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "yyyy-MM-dd HH:mm:ss";
        }
        return toDate(str, str2);
    }

    public static Date toDate(String str, String format) {
        Intrinsics.checkNotNullParameter(format, "format");
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e2) {
            Log.e("StringExtensions", "toDate: ", e2);
            return null;
        }
    }

    public static String formatDatedefault(Date date, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "dd.MM.yyyy HH:mm:ss";
        }
        return formatDate(date, str);
    }

    public static String formatDate(Date date, String format) {
        Intrinsics.checkNotNullParameter(format, "format");
        if (date == null) {
            return "";
        }
        try {
            String format2 = new SimpleDateFormat(format).format(date);
            Intrinsics.checkNotNull(format2);
            return format2;
        } catch (Exception e2) {
            Log.e("StringExtensions", "formatDate: ", e2);
            return "";
        }
    }

    public static Date withoutTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date toDatedefault(String str, Object o, int i, Object o1) {
        return null;
    }

    public static String formatDatedefault(Date datedefault, Object o, int i, Object o1) {
        return "";
    }
}
