package com.proje.mobilesales.core.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.features.reports.model.AvgCalcItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateAndTimeUtils {
    private static final String TAG = "DateAndTimeUtils";
    public static int getLogoTimeCode(int i2, int i3, int i4) {
        return (i2 * 16777216) + (i3 * 65536) + (i4 * 256);
    }
    public static String nowDate(SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "nowDate: ", e2);
            return "";
        }
    }
    public static String dateAddToSqlDate(int i2) {
        return convertDateSqlDate(dateAdd(i2));
    }
    public static Date dateAdd(int i2) {
        Date time = Calendar.getInstance().getTime();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(6, i2);
            return calendar.getTime();
        } catch (Exception e2) {
            Log.e(TAG, "nowDate: ", e2);
            return time;
        }
    }
    public static String nowDateDMY() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "nowDateDMY: ", e2);
            return "";
        }
    }
    public static String nowDateMDY() {
        try {
            return new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "nowDateDMY: ", e2);
            return "";
        }
    }
    public static String getFirstDayOfYearMDY() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, 1);
            calendar.set(6, 1);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getFirstDayOfYear: ", e2);
            return "";
        }
    }
    public static String getLastDayOfYearMDY() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, 11);
            calendar.set(5, 31);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "lastDayOfYearMDY: ", e2);
            return "";
        }
    }
    public static String getNowDateTime() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public static String calendarDateDMY(Calendar calendar) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "calendarDateDMY: ", e2);
            return "";
        }
    }
    public static String getDateDMY(Date date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(Long.valueOf(date.getTime()));
        } catch (Exception e2) {
            Log.e(TAG, "calendarDateDMY: ", e2);
            return "";
        }
    }
    public static String getDateDMY2(Date date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(Long.valueOf(date.getTime()));
        } catch (Exception e2) {
            Log.e(TAG, "calendarDateDMY: ", e2);
            return "";
        }
    }
    public static String getDateDMY(int i2, int i3, int i4) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(i2, i3, i4);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateDMY: ", e2);
            return "";
        }
    }
    public static Calendar getCalendar(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(i2, i3, i4);
        } catch (Exception e2) {
            Log.e(TAG, "getDateDMY: ", e2);
        }
        return calendar;
    }
    public static Calendar getGregorianCalendar(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(date);
        } catch (Exception e2) {
            Log.e(TAG, "getDateDMY: ", e2);
        }
        return gregorianCalendar;
    }
    public static Date getDate(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            calendar.set(i2, i3, i4);
            return calendar.getTime();
        } catch (Exception e2) {
            Log.e(TAG, "getDateDMY: ", e2);
            return date;
        }
    }
    public static String convertStringDate(String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            return new SimpleDateFormat(str3).format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, "convertStringDate: ", e2);
            return "";
        }
    }
    public static Calendar getDateCalendarDMY(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(str));
        } catch (Exception e2) {
            Log.e(TAG, "getDateCalendarDMY: ", e2);
        }
        return calendar;
    }
    public static Calendar getDateCalendar(String str, String str2) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat(str2).parse(str));
        } catch (Exception e2) {
            Log.e(TAG, "getDateCalendarDMY: ", e2);
        }
        return calendar;
    }
    public static Date getDateDMY(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(str));
        } catch (Exception e2) {
            Log.e(TAG, "getDateCalendarDMY: ", e2);
        }
        return calendar.getTime();
    }
    public static Date getDate(String str, String str2) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat(str2).parse(str));
        } catch (Exception e2) {
            Log.e(TAG, "getDateCalendarDMY: ", e2);
        }
        return calendar.getTime();
    }
    public static int getLogoDateInt() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(1) * 65536) + ((calendar.get(2) + 1) * 256) + calendar.get(5);
    }
    public static int getLogoDateInt(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, e2.getMessage());
        }
        return (calendar.get(1) * 65536) + ((calendar.get(2) + 1) * 256) + calendar.get(5);
    }
    public static int getNetsisDateInt() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(5) + ((calendar.get(2) + 1) * 30) + ((calendar.get(1) - 2015) * 365);
    }
    public static int getNetsisDateInt(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, e2.getMessage());
        }
        return calendar.get(5) + ((calendar.get(2) + 1) * 30) + ((calendar.get(1) - 2015) * 365);
    }
    public static String getDateSql(SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateSql: ", e2);
            return "";
        }
    }
    public static String getDateSqlYearAndHour() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateSql: ", e2);
            return "";
        }
    }
    public static String getDateDMYWithoutTime(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(str);
        } catch (Exception e2) {
            Log.e(TAG, "getDateDMYWithoutTime : ", e2);
            return "";
        }
    }
    public static String getSqlDate(Boolean bool) {
        SimpleDateFormat simpleDateFormat;
        Calendar calendar = Calendar.getInstance();
        try {
            if (bool.booleanValue()) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            }
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateSql: ", e2);
            return "";
        }
    }
    public static String getSqlSelectedDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(convertStringToDateY(str));
        } catch (Exception e2) {
            Log.e(TAG, "getSqlSelectedDate: ", e2);
            return "";
        }
    }
    public static String nowDate() {
        return nowDate("dd.MM.yyyy");
    }
    public static String nowDate(String str) {
        try {
            return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime());
        } catch (Exception e2) {
            Log.e(TAG, "nowDate: ", e2);
            return "";
        }
    }
    public static int getDateToInt(String str) {
        try {
            String[] split = StringUtils.split(str, "\\.");
            return StringUtils.convertStringToInt(split[2] + split[1] + split[0]);
        } catch (Exception e2) {
            Log.e(TAG, "getDateToInt: ", e2);
            return 0;
        }
    }
    public static Date toDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date time = Calendar.getInstance().getTime();
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            Log.e(TAG, "toDate: ", e2);
            return time;
        }
    }
    public static Date toDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date time = Calendar.getInstance().getTime();
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            Log.e(TAG, "toDate: ", e2);
            return time;
        }
    }
    public static String toDateString(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            return new SimpleDateFormat(str3).format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, "toDateString: ", e2);
            return "";
        }
    }
    public static String toDateString(String str, String str2, String str3, String str4) {
        String dateString = toDateString(str, str2, str3);
        return !TextUtils.isEmpty(dateString) ? dateString : str4;
    }
    public static String getDateFull(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateFull: ", e2);
            return "";
        }
    }
    public static float getTimeSpanTotalSeconds(long j2, long j3) {
        return (int) ((j3 - j2) / 1000);
    }
    @SuppressLint({"SimpleDateFormat"})
    public static Calendar getDateTime(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(str));
        } catch (ParseException unused) {
            calendar.setTime(calendar.getTime());
        }
        return calendar;
    }
    public static String convertYMDToDMY(String str) {
        try {
            return toDateString(str, "yyyy-MM-dd", "dd-MM-yyyy");
        } catch (Exception e2) {
            Log.e(TAG, "convertYMDToDMY: ", e2);
            return "";
        }
    }
    public static int getLogoDateTimeCode(String str) {
        Calendar dateTime = getDateTime(str);
        return (dateTime.get(11) * 16777216) + (dateTime.get(12) * 65536) + (dateTime.get(13) * 256);
    }
    public static String intToNowTimeLogo(int i2) {
        try {
            int i3 = i2 / 16777216;
            int i4 = i2 % 16777216;
            int i5 = i4 / 65536;
            int i6 = (i4 % 65536) / 256;
            String valueOf = String.valueOf(i3);
            String valueOf2 = String.valueOf(i5);
            String valueOf3 = String.valueOf(i6);
            if (i3 < 10) {
                valueOf = "0" + valueOf;
            }
            if (i5 < 10) {
                valueOf2 = "0" + valueOf2;
            }
            if (i6 < 10) {
                valueOf3 = "0" + valueOf3;
            }
            return valueOf + ":" + valueOf2 + ":" + valueOf3;
        } catch (Exception e2) {
            Log.e(TAG, "intToNowTimeLogo: ", e2);
            return "";
        }
    }
    public static String getDateToFormat(Calendar calendar, String str) {
        try {
            return new SimpleDateFormat(str).format(calendar.getTime());
        } catch (Exception e2) {
            Log.e(TAG, "getDateToFormat: ", e2);
            return "";
        }
    }
    public static String convertDateSqlDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (str != null) {
            try {
                if (str.equals("")) {
                    return "";
                }
                return simpleDateFormat2.format(simpleDateFormat.parse(str));
            } catch (ParseException e2) {
                Log.e(TAG, "convertDateSqlDate: ", e2);
                return "";
            }
        }
        return "";
    }
    public static String convertDateSqlDateWithoutTime(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, "convertDateSqlDate: ", e2);
            return "";
        }
    }
    public static String convertDateSqlDateWithoutSecond(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        if (str != null) {
            try {
                if (str.equals("")) {
                    return "";
                }
                return simpleDateFormat2.format(simpleDateFormat.parse(str));
            } catch (ParseException e2) {
                Log.e(TAG, "convertDateSqlDate: ", e2);
                return "";
            }
        }
        return "";
    }
    public static String convertDateSqlDate(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e2) {
            Log.e(TAG, "convertDateSqlDate: ", e2);
            return "";
        }
    }
    public static String convertSqlDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, "convertDateSqlDate: ", e2);
            return "";
        }
    }
    public static String convertDateSqlDateFull(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            Log.e(TAG, "convertDateSqlDate: ", e2);
            return "";
        }
    }
    public static String getDateTime(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return "CONVERT(DATETIME, '" + i2 + "-" + StringUtils.fillFormat(2, i3) + "-" + StringUtils.fillFormat(2, i4) + " " + StringUtils.fillFormat(2, i5) + ":" + StringUtils.fillFormat(2, i6) + ":" + StringUtils.fillFormat(2, i7) + "." + StringUtils.fillFormat(3, i8) + "', 121)";
    }
    public static String getDateTime(int i2, int i3, int i4) {
        return "CONVERT(VARCHAR, '" + i2 + "-" + StringUtils.fillFormat(2, i3) + "-" + StringUtils.fillFormat(2, i4);
    }
    public static String getDateC(int i2, int i3, int i4) {
        return i2 + "-" + StringUtils.fillFormat(2, i3) + "-" + StringUtils.fillFormat(2, i4);
    }
    public static String getDateWithFormat(Calendar calendar, DateTimeFormat dateTimeFormat) {
        return new SimpleDateFormat(dateTimeFormat.mValue).format(calendar.getTime());
    }
    public static String convertDateSqlDateRest(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static String convertDateSqlDateRestG(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static Date convertDateSqlStringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
    public static String convertDateSqlToString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static String convertDateY(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static Date convertStringToDateddMMYYYY(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
    public static Date convertStringToDateTime(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
    public static Date convertStringToDateY(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
    public static String convertDateH(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new SimpleDateFormat("HH:mm:ss").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static String nowDateSqlPrint() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }
    public static String nowDateSqlFull2() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public static String nowDateTime() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public static String nowTime() {
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }
    public static String nowTime2() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    public static String getTimeOnly(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return new SimpleDateFormat("HH:mm:ss").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static String convertReportDateToSimpleDate(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static String intToNowTime(int i2) {
        int i3 = i2 / 16777216;
        int i4 = i2 % 16777216;
        int i5 = i4 / 65536;
        int i6 = (i4 % 256) / 256;
        String valueOf = String.valueOf(i3);
        String valueOf2 = String.valueOf(i5);
        String valueOf3 = String.valueOf(i6);
        if (i3 == 0) {
            valueOf = "00";
        }
        if (i5 == 0) {
            valueOf2 = "00";
        }
        if (i6 == 0) {
            valueOf3 = "00";
        }
        return valueOf + ":" + valueOf2 + ":" + valueOf3;
    }
    @SuppressLint({"SimpleDateFormat"})
    public static String now() {
        return new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
    }
    @SuppressLint({"SimpleDateFormat"})
    public static String toDateWithMonth(Calendar calendar) {
        return new SimpleDateFormat("MMMM dd, yyyy").format(calendar.getTime());
    }
    public static int getDateInt(String str) {
        String[] split = StringUtils.split(str, '.');
        return StringUtils.convertStringToInt(split[2] + split[1] + split[0]);
    }
    public static HashMap<String, String> calculateDateForCollectionList(List<AvgCalcItem> list) {
        HashMap<String, String> hashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        ArrayList<Double> doubleList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> dateList3 = new ArrayList<>();
        double d2 = 0.0d;
        for (AvgCalcItem avgCalcItem : list) {
            double d3 = avgCalcItem.total;
            d2 += d3;
            doubleList.add(d3);
            calendar.setTime(toDate(avgCalcItem.date, "yyyy-MM-dd"));
            dateList3.add(avgCalcItem.date);
            dateList.add(String.valueOf(calendar.get(6)));
        }
        calendar.setTime(toDate(nowDate()));
        Date date = toDate(nowDate());
        double d4 = 0.0d;
        for (int i2 = 0; i2 < dateList.size(); i2++) {
            d4 += doubleList.get(i2) * TimeUnit.DAYS.convert(toDate(dateList3.get(i2), "yyyy-MM-dd").getTime() - date.getTime(), TimeUnit.MILLISECONDS);
        }
        if (d4 != 0.0d || d2 != 0.0d) {
            double d5 = d4 / d2;
            try {
                Date date2 = toDate(nowDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date2);
                calendar2.add(Calendar.DAY_OF_MONTH, new BigDecimal(d5).setScale(2, RoundingMode.HALF_UP).intValue());
                hashMap.put("date", simpleDateFormat.format(calendar2.getTime()));
                hashMap.put("day", String.valueOf(d5));
                hashMap.put("total", StringUtils.formatDouble(d2));
            } catch (Exception e2) {
                Log.e(TAG, "calculateDateForCollectionList", e2);
                hashMap.put("date", " ");
                hashMap.put("day", "0");
                hashMap.put("total", "0.00");
            }
        } else {
            hashMap.put("date", " ");
            hashMap.put("day", "0");
            hashMap.put("total", "0.00");
        }
        return hashMap;
    }
    public static HashMap<String, String> calculateDate(List<AvgCalcItem> list) {
        HashMap<String, String> hashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (AvgCalcItem avgCalcItem : list) {
            double d4 = avgCalcItem.total;
            if (d4 > 0.0d) {
                d3 += d4;
                arrayList.add(Double.valueOf(d4));
                calendar.setTime(toDate(avgCalcItem.date, "yyyy-MM-dd"));
                arrayList2.add(Integer.valueOf(calendar.get(6)));
            }
        }
        calendar.setTime(toDate(nowDate()));
        int i2 = 0;
        double d5 = 0.0d;
        while (i2 < arrayList2.size()) {
            d5 += ((Double) arrayList.get(i2)).doubleValue() * (((Integer) arrayList2.get(i2)).intValue() - calendar.get(6));
            i2++;
            d2 = 0.0d;
        }
        double d6 = d2;
        if (d5 != d6 || d3 != d6) {
            double d7 = d5 / d3;
            Date date = toDate(nowDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar2.add(5, new BigDecimal((int) Math.round(d7)).setScale(2, RoundingMode.HALF_UP).intValue());
            hashMap.put("date", simpleDateFormat.format(calendar2.getTime()));
            hashMap.put("day", String.valueOf((int) Math.round(d7)));
            hashMap.put("total", StringUtils.formatDouble(d3));
        } else {
            hashMap.put("date", " ");
            hashMap.put("day", "0");
            hashMap.put("total", "0.00");
        }
        return hashMap;
    }
    public enum DateTimeFormat {
        ONLYDATE("dd.MM.yyyy"),
        ONLYTIME("HH:mm:ss"),
        DATETIME("dd.MM.yyyy HH:mm:ss");

        private String mValue;

        DateTimeFormat(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }

        public void setValue(String str) {
            this.mValue = str;
        }
    }
    public static Date addTime(Date date, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i2, i3);
        return calendar.getTime();
    }
    public static boolean isDateBetweenDates(Date date, Date date2, Date date3) {
        return date.after(date2) && date.before(date3);
    }
    public static boolean isDateBetweenDatesWithFormat(String str, String str2, String str3, String str4) {
        return isDateBetweenDates(getDate(str, str4), getDate(str2, str4), getDate(str3, str4));
    }
    public static String formatFromDate(Date date, String str) {
        try {
            return new SimpleDateFormat(str).format(date);
        } catch (Exception unused) {
            return "";
        }
    }
    public static boolean compareDates(String str, String str2, String str3) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3);
            return simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(str2)) <= 0;
        } catch (Exception unused) {
            return false;
        }
    }
    public static boolean isDateFormatValid(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
            return true;
        } catch (ParseException unused) {
            return false;
        }
    }
}
