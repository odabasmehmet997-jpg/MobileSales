package org.kobjects.isodate;

import androidx.exifinterface.media.ExifInterface;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class IsoDate {
    public static final int DATE = 1;
    public static final int DATE_TIME = 3;
    public static final int TIME = 2;
    static void dd(StringBuffer stringBuffer, int i2) {
        stringBuffer.append((char) ((i2 / 10) + 48));
        stringBuffer.append((char) ((i2 % 10) + 48));
    }
    public static String dateToString(Date date, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);
        StringBuffer stringBuffer = new StringBuffer();
        if ((i2 & 1) != 0) {
            int i3 = calendar.get(1);
            dd(stringBuffer, i3 / 100);
            dd(stringBuffer, i3 % 100);
            stringBuffer.append('-');
            dd(stringBuffer, calendar.get(2) + 1);
            stringBuffer.append('-');
            dd(stringBuffer, calendar.get(5));
            if (i2 == 3) {
                stringBuffer.append(ExifInterface.GPS_DIRECTION_TRUE);
            }
        }
        if ((i2 & 2) != 0) {
            dd(stringBuffer, calendar.get(11));
            stringBuffer.append(':');
            dd(stringBuffer, calendar.get(12));
            stringBuffer.append(':');
            dd(stringBuffer, calendar.get(13));
            stringBuffer.append('.');
            int i4 = calendar.get(14);
            stringBuffer.append((char) ((i4 / 100) + 48));
            dd(stringBuffer, i4 % 100);
            stringBuffer.append('Z');
        }
        return stringBuffer.toString();
    }
    public static Date stringToDate(String str, int i2) {
        String strSubstring = str;
        Calendar calendar = Calendar.getInstance();
        int i3 = 8;
        int i4 = 0;
        if ((i2 & 1) != 0) {
            calendar.set(1, Integer.parseInt(strSubstring.substring(0, 4)));
            calendar.set(2, Integer.parseInt(strSubstring.substring(5, 7)) - 1);
            calendar.set(5, Integer.parseInt(strSubstring.substring(8, 10)));
            if (i2 != 3 || str.length() < 11) {
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                return calendar.getTime();
            }
            strSubstring = strSubstring.substring(11);
        } else {
            calendar.setTime(new Date(0L));
        }
        calendar.set(11, Integer.parseInt(strSubstring.substring(0, 2)));
        calendar.set(12, Integer.parseInt(strSubstring.substring(3, 5)));
        calendar.set(13, Integer.parseInt(strSubstring.substring(6, 8)));
        if (8 < strSubstring.length() && strSubstring.charAt(8) == '.') {
            int i5 = 100;
            while (true) {
                i3++;
                char cCharAt = strSubstring.charAt(i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i4 += (cCharAt - '0') * i5;
                i5 /= 10;
            }
            calendar.set(14, i4);
        } else {
            calendar.set(14, 0);
        }
        if (i3 < strSubstring.length()) {
            if (strSubstring.charAt(i3) == '+' || strSubstring.charAt(i3) == '-') {
                calendar.setTimeZone(TimeZone.getTimeZone("GMT" + strSubstring.substring(i3)));
            } else if (strSubstring.charAt(i3) == 'Z') {
                calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
            } else {
                throw new RuntimeException("illegal time format!");
            }
        }
        return calendar.getTime();
    }
}
