package com.proje.mobilesales.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import androidx.annotation.StringRes;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ObjectType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.NetsisDataHeader;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.model.KeyValuePair;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

public final class StringUtils {
    private static final String CatStringNewLine = "catStringNewLine: ";
    private static final String CatStringSpecial = "catStringSpecial: ";
    private static final String ConvertBooleanToString = "convertBooleanToString: ";
    private static final String ConvertString = "convertString: ";
    private static final String DecimalFormatString = "###,###,##0.00";
    private static final String FormatDouble = "formatDouble: ";
    public static final StringUtils INSTANCE = new StringUtils();
    private static final String TAG = "StringUtils";
    private static final String http = "http://";
    public static float convertDoubleToFloat(double d2) {
        return (float) d2;
    }
    public static int convertDoubleToInteger(double d2) {
        return (int) d2;
    }
    public static boolean convertIntToBoolean(int i2) {
        return i2 == 1;
    }
    public static boolean convertIntToBooleanInverse(int i2) {
        return !(i2 == 1);
    }
    public static String insert(String source, int i2, String str) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(str, "str");
        return source.substring(0, i2) + str + source.substring(i2);
    }
    public static String empty() {
        return "";
    }
    public static String[] wrapTextInColumns(String text, int i2) {
        Intrinsics.checkNotNullParameter(text, "text");
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (i3 < text.length()) {
            int i4 = i3 + i2;
            if (i4 > text.length()) {
                i4 = text.length();
            }
            String substring = text.substring(i3, i4);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            int lastIndexOfdefault = StringsKt.lastIndexOf(substring, " ", 0, false);
            if (lastIndexOfdefault != -1) {
                int i5 = lastIndexOfdefault + i3 + 1;
                substring = text.substring(i3, i5);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                i3 = i5;
            } else {
                i3 = i4;
            }
            sb.append(substring);
            sb.append(SqlLiteVariable._NEW_LINE);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return StringsKt.split(sb2, new String[]{SqlLiteVariable._NEW_LINE}, false, 0).toArray(new String[0]);
    }
    public static String[] split(String text, String splitCharacter) {
        String[] strArr = new String[0];
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(splitCharacter, "splitCharacter");
        String[] strArr2 = null;
        Exception e;
        try {
            strArr = StringsKt.split(StringsKt.trim(text).toString(), new String[]{splitCharacter}, false, 0).toArray(new String[0]);
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (strArr.length == 0) {
                return null;
            }
            return strArr;
        } catch (Exception e3) {
            e = e3;
            strArr2 = strArr;
            Log.e(TAG, "splitComma: ", e);
            return strArr2;
        }
    }
    public static String[] splitInitValue(String text, String splitCharacter, int i2) {
        String[] strArr;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(splitCharacter, "splitCharacter");
        String[] splitInit = INSTANCE.splitInit(i2, "");
        Exception e;
        try {
            strArr = split(text, splitCharacter);
            if (strArr != null) {
                try {
                    if (strArr.length != i2) {
                        int length = strArr.length;
                        System.arraycopy(strArr, 0, splitInit, 0, length);
                        String[] strArr2 = new String[i2];
                    } else {
                        splitInit = strArr;
                    }
                } catch (Exception e2) {
                    e = e2;
                    splitInit = strArr;
                    Log.e(TAG, "splitComma: ", e);
                    Intrinsics.checkNotNull(splitInit);
                    return splitInit;
                }
            }
        } catch (Exception e3) {
            e = e3;
            strArr = null;
        }
        Intrinsics.checkNotNull(splitInit);
        return splitInit;
    }

    private String[] splitInit(int i2, String str) {
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = str;
        }
        return strArr;
    }
    private boolean[] splitInit(int i2, boolean z) {
        boolean[] zArr = new boolean[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i3] = z;
        }
        return zArr;
    }
    public boolean[] convertArrayStringToBool(String[] array, String trueText) {
        Intrinsics.checkNotNullParameter(array, "array");
        Intrinsics.checkNotNullParameter(trueText, "trueText");
        if (array.length == 0) {
            return null;
        }
        boolean[] zArr = splitInit(array.length, false);
        try {
            int length = array.length;
            for (int i2 = 0; i2 < length; i2++) {
                zArr[i2] = Intrinsics.areEqual(array[i2], trueText);
            }
        } catch (Exception e2) {
            Log.e(TAG, "convertArrayStringToBool: ", e2);
        }
        return zArr;
    }
    public static String convertNullStringToString(String str) {
        try {
            if (Intrinsics.areEqual(str, "null")) {
                str = "";
            }
        } catch (Exception e2) {
            Log.e(TAG, "convertNullPointToString: ", e2);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }
    public static String convertNullToString(Object obj) {
        if (obj != null) {
            try {
                return obj.toString();
            } catch (Exception e2) {
                Log.e(TAG, "convertNullPointToString: ", e2);
            }
        }
        return "";
    }
    public String convertNullToEmpty(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        try {
            return TextUtils.isEmpty(text) ? "" : text;
        } catch (Exception e2) {
            Log.e(TAG, "convertNullToEmpty: ", e2);
            return text;
        }
    }
    private String convertObjectToString(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }
    public static int convertObjectToInt(Object obj) {
        try {
            Integer valueOf = Integer.valueOf(INSTANCE.convertObjectToString(obj));
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf.intValue();
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return 0;
        }
    }
    public String checkNullValueString(String str) {
        return (str == null || Intrinsics.areEqual(str, "null")) ? "" : str;
    }
    public String checkNullObject(Object obj) {
        return checkNullValueString(convertObjectToString(obj));
    }
    public static int convertStringToInt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            Intrinsics.checkNotNullParameter(str, "str");
            int length = str.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean z2 = Intrinsics.compare(str.charAt(!z ? i2 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i2++;
                } else {
                    z = true;
                }
            }
            Integer valueOf = Integer.valueOf(str.subSequence(i2, length + 1).toString());
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf.intValue();
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToInt: ", e2);
            return 0;
        }
    }
    public static long convertStringToLong(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                Intrinsics.checkNotNullParameter(str, "str");
                int length = str.length() - 1;
                int i2 = 0;
                boolean z = false;
                while (i2 <= length) {
                    boolean z2 = Intrinsics.compare(str.charAt(!z ? i2 : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i2++;
                    } else {
                        z = true;
                    }
                }
                Long valueOf = Long.valueOf(str.subSequence(i2, length + 1).toString());
                Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
                return valueOf.longValue();
            }
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToLong: ", e2);
        }
        return 0L;
    }
    public static int convertStringToIntNegative(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        try {
            Integer valueOf = Integer.valueOf(text);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf.intValue();
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToIntNegative: ", e2);
            return -1;
        }
    }
    public static boolean convertStringToBoolean(String str) {
        try {
            Boolean valueOf = Boolean.valueOf(str);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf.booleanValue();
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToBoolean: ", e2);
            return false;
        }
    }
    public static String convertBooleanToString(Boolean bool) {
        try {
            return String.valueOf(bool);
        } catch (Exception e2) {
            Log.e(TAG, "convertBooleanToString: ", e2);
            return "";
        }
    }
    public static int convertBooleanToInt(Boolean bool) {
        try {
            Intrinsics.checkNotNull(bool);
            return bool.booleanValue() ? 1 : 0;
        } catch (Exception e2) {
            Log.e(TAG, "convertBooleanToInt: ", e2);
            return 1;
        }
    }
    public static int convertBooleanToIntInverse(Boolean bool) {
        try {
            Intrinsics.checkNotNull(bool);
            return 1 ^ (bool.booleanValue() ? 1 : 0);
        } catch (Exception e2) {
            Log.e(TAG, "convertBooleanToIntInverse: ", e2);
            return 1;
        }
    }
    public static String convertBlobToString(byte[] bArr, String str) {
        if (bArr != null) {
            try {
                Intrinsics.checkNotNull(str);
                Charset forName = Charset.forName(str);
                Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
                return new String(bArr, forName);
            } catch (Exception e2) {
                Log.e(TAG, "convertBlobToString : ", e2);
            }
        }
        return "";
    }
    public static boolean convertStringToBooleanParams(String str) {
        try {
            return Intrinsics.areEqual(str, BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToBooleanParams: ", e2);
            return false;
        }
    }
    public static float convertStringToFloat(String str) {
        try {
            Intrinsics.checkNotNull(str);
            return Float.parseFloat(str);
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToFloat: ", e2);
            return 0.0f;
        }
    }
    public static double convertStringToDouble(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                Intrinsics.checkNotNull(str);
                Double valueOf = Double.valueOf(str);
                Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
                return valueOf.doubleValue();
            }
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToDouble: ", e2);
        }
        return 0.0d;
    }
    public static String formatToTwoDecimals(double d2) {
        String format = new DecimalFormat("#,##0.00").format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return StringsKt.replace(format, ".", ",", false);
    }
    public static double convertStringToDoubleNetsis(String str) {
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
            Intrinsics.checkNotNull(numberFormat, "null cannot be cast to non-null type java.text.DecimalFormat");
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            decimalFormat.setParseBigDecimal(true);
            Object parseObject = decimalFormat.parseObject(str);
            Intrinsics.checkNotNull(parseObject, "null cannot be cast to non-null type java.math.BigDecimal");
            return ((BigDecimal) parseObject).doubleValue();
        } catch (Exception e2) {
            Log.e(TAG, "convertStringToDoubleNetsis: ", e2);
            return 0.0d;
        }
    }
    public static String convertStringToDate(String str) {
        return "";
    }
    public static String convertDoubleToStringWithThreeDigits(Double d2) {
        String convertDoubleToString;
        Intrinsics.checkNotNull(d2);
        try {
            if (String.valueOf(d2.doubleValue() - (d2)).length() > 5) {
                convertDoubleToString = StringsKt.replace(formatDoubleThreeDigits(d2.doubleValue()), ",", ".", false);
            } else {
                convertDoubleToString = convertDoubleToString(d2);
            }
            return convertDoubleToString;
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }
    public static String removeSlashFromWord(String word) {
        Intrinsics.checkNotNullParameter(word, "word");
        return new Regex("/").replace(word, "");
    }
    public static String convertDoubleToString(Double d2) {
        try {
            return String.valueOf(d2);
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }
    public static String convertDoubleToStringDot(Double d2) {
        String str;
        try {
            str = new DecimalFormat("#.00").format(d2);
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            str = "";
        }
        return StringsKt.replace(str, ",", ".", false);
    }
    public int convertDoubleToInt(double d2) {
        return convertStringToInt(convertDoubleToString(Double.valueOf(d2)));
    }
    public static String convertIntToString(int i2) {
        try {
            return String.valueOf(i2);
        } catch (Exception e2) {
            Log.e(TAG, "convertIntToString: ", e2);
            return "";
        }
    }
    public String formatStringToFloat(String str) {
        try {
            String format = new DecimalFormat(DecimalFormatString).format(convertStringToFloat(str));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return StringsKt.replace(format, ",", ".", false);
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }
    public String formatFloat(float f2) {
        try {
            String format = new DecimalFormat(DecimalFormatString).format(f2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return StringsKt.replace(format, ",", ".", false);
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }
    public String formatDoubleNegativeClose(double d2) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(DecimalFormatString);
            decimalFormat.setNegativePrefix("");
            String format = decimalFormat.format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String formatDouble(double d2) {
        try {
            String format = new DecimalFormat(DecimalFormatString).format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String formatDoubleNormal(double d2) {
        try {
            String format = new DecimalFormat(DecimalFormatString).format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String formatDoubleThreeDigits(double d2) {
        try {
            String format = new DecimalFormat("###,###,##0.000").format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String formatDoubleDynamicDigitsWithDot(double d2, int i2) {
        String str = "#.";
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                str = str + '#';
            } catch (Exception e2) {
                Log.e(TAG, "formatDoubleDynamicDigitsWithDot: ", e2);
                return "";
            }
        }
        String format = new DecimalFormat(str, DecimalFormatSymbols.getInstance(Locale.ROOT)).format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static String formatDoubleFourDigits(double d2) {
        try {
            String format = new DecimalFormat("###,###,##0.0000").format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String formatDouble(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            return formatDouble(convertStringToDouble(value));
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String roundDouble(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            return INSTANCE.roundDouble(convertStringToDouble(value));
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public String roundDouble(double d2) {
        try {
            String format = new DecimalFormat("###,###,###").format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public String formatFloat(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            return formatDouble(convertStringToFloat(value));
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public String formatDoubleLastTransfer(double d2) {
        try {
            String format = new DecimalFormat(".###############").format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, FormatDouble, e2);
            return "";
        }
    }
    public static String catStringSpace(String... param) {
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            boolean z = true;
            for (String str2 : param) {
                if (str2 != null && str2.length() != 0) {
                    if (z) {
                        z = !z;
                        str = str2;
                    } else {
                        str = str + ' ' + str2;
                    }
                }
            }
            return StringsKt.replace(str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringNewLine, e2);
            return str;
        }
    }
    public static String catStringNewLine(String... param) {
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            boolean z = true;
            for (String str2 : param) {
                if (str2 != null && str2.length() != 0) {
                    if (z) {
                        z = !z;
                        str = str2;
                    } else {
                        str = str + StringsKt.trimIndent(str2);
                    }
                }
            }
            return StringsKt.replace(str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringNewLine, e2);
            return str;
        }
    }
    public static String catString(String... param) {
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            boolean z = true;
            for (String str2 : param) {
                if (str2 != null && str2.length() != 0) {
                    if (z) {
                        z = !z;
                        str = str2;
                    } else {
                        String sb = str +
                                StringsKt.trimIndent('-' + str2);
                        str = sb;
                    }
                }
            }
            return StringsKt.replace(str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringNewLine, e2);
            return str;
        }
    }
    public String catIntegerSpecial(String character, ArrayList<Integer> param) {
        Intrinsics.checkNotNullParameter(character, "character");
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            Iterator<Integer> it = param.iterator();
            boolean z = true;
            while (it.hasNext()) {
                Integer next = it.next();
                if (next != null) {
                    if (z) {
                        str = String.valueOf(next);
                        z = !z;
                    } else {
                        str = str + character + next.intValue();
                    }
                }
            }
            return StringsKt.replace(str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringSpecial, e2);
            return str;
        }
    }
    public static String catStringSpecial(String character, List<String> param) {
        Intrinsics.checkNotNullParameter(character, "character");
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            boolean z = true;
            for (String str2 : param) {
                if (str2 != null) {
                    if (z) {
                        str = '\'' + str2 + '\'';
                        z = !z;
                    } else {
                        str = str + character + '\'' + str2 + '\'';
                    }
                }
            }
            return StringsKt.replace (str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringSpecial, e2);
            return str;
        }
    }
    public static String catIntegerSpecial(String character, List<Integer> param) {
        Intrinsics.checkNotNullParameter(character, "character");
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            boolean z = true;
            for (Integer num : param) {
                if (num != null) {
                    if (z) {
                        str = num.toString();
                        z = !z;
                    } else {
                        str = str + character + num;
                    }
                }
            }
            return StringsKt.replace (str, '\n', ' ', false);
        } catch (Exception e2) {
            Log.e(TAG, CatStringSpecial, e2);
            return str;
        }
    }
    public static String catStringNewLineCursor(Cursor cursor, @StringRes int... param) {
        Intrinsics.checkNotNullParameter(param, "param");
        String str = "";
        try {
            try {
                Intrinsics.checkNotNull(cursor);
                if (cursor.moveToFirst()) {
                    boolean z = true;
                    for (int i2 : param) {
                        String stringResource = ContextUtils.getStringResource(i2);
                        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
                        @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex(stringResource));
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        if (stringResource.length() > 0) {
                            if (z) {
                                z = !z;
                                str = string;
                            } else {
                                String sb = str +
                                        StringsKt.trimIndent("\n                                \n                                " + string + "\n                                ");
                                str = sb;
                            }
                        }
                    }
                }
                String replacedefault = StringsKt.replace(str, '\n', ' ', false);
                if (cursor.isClosed()) {
                    return replacedefault;
                }
                cursor.close();
                return replacedefault;
            } catch (Exception e2) {
                Log.e(TAG, CatStringNewLine, e2);
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return str;
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }
    public static String convertStringCustomerDebitCreditText(double d2) {
        String str;
        String formatDouble = formatDouble(d2);
        StringBuilder sb = new StringBuilder();
        sb.append(formatDouble);
        if (d2 < 0.0d) {
            str = " ( B ) ";
        } else if (d2 > 0.0d) {
            str = " ( A ) ";
        } else {
            str = "       ";
        }
        sb.append(str);
        return sb.toString();
    }
    public boolean getStringBoolean(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Intrinsics.areEqual(value, BuildConfig.NETSIS_DEMO_PASSWORD);
    }
    public boolean getStringBooleanInverse(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return !Intrinsics.areEqual(value, BuildConfig.NETSIS_DEMO_PASSWORD);
    }
    public static String getSerialLotCode(ArrayList<Serilot> list, int i2) {
        Intrinsics.checkNotNullParameter(list, "list");
        String str = "";
        try {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                String sb = str +
                        (i2 == TrackType.SERIAL_GROUP.getType() ? list.get(i3).grpBegCode + " (" + list.get(i3).amount + ')' : list.get(i3).code) +
                        ", ";
                str = sb;
            }
            if (str.length() == 0) {
                return str;
            }
            String substring = str.substring(0, str.length() - 2);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        } catch (Exception e2) {
            Log.e(TAG, "getSeriLotCode: ", e2);
            return str;
        }
    }
    public String getSerialLotCodeNewLine(ArrayList<Serilot> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        String str = "";
        try {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String sb = str +
                        StringsKt.trimIndent("\n                    " + list.get(i2).code + "\n                    \n                    ");
                str = sb;
            }
        } catch (Exception e2) {
            Log.e(TAG, "getSeriLotCode: ", e2);
        }
        return str;
    }
    public static String getArrayToString(ArrayList<Integer> arrayList, String character) {
        Intrinsics.checkNotNullParameter(arrayList, "arrayList");
        Intrinsics.checkNotNullParameter(character, "character");
        String str = "";
        try {
            Iterator<Integer> it = arrayList.iterator();
            boolean z = true;
            while (it.hasNext()) {
                Integer next = it.next();
                if (z) {
                    str = String.valueOf(next.intValue());
                    z = !z;
                } else {
                    str = str + character + next.intValue();
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "getArrayToString: ", e2);
        }
        return str;
    }
    public static String getKeyValuePairArrayToString(List<? extends KeyValuePair> arrayList, String character) {
        Intrinsics.checkNotNullParameter(arrayList, "arrayList");
        Intrinsics.checkNotNullParameter(character, "character");
        String str = "";
        try {
            boolean z = true;
            for (KeyValuePair keyValuePair : arrayList) {
                if (z) {
                    String value = keyValuePair.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                    z = !z;
                    str = value;
                } else {
                    str = str + character + keyValuePair.getValue();
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "getArrayToString: ", e2);
        }
        return str;
    }
    public static String catStringColon(String str, String str2) {
        return catString(str, str2, " : ");
    }
    public static String catString(String str, String str2, String str3) {
        try {
            PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            String format = String.format("%s %s %s", Arrays.copyOf(new Object[]{str, str3, str2}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, "catOneString: ", e2);
            return "";
        }
    }
    public static boolean paramValueTrueCheck(String parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        try {
            return Intrinsics.areEqual(parameter, "True");
        } catch (Exception e2) {
            Log.e(TAG, "paramValueControl: ", e2);
            return false;
        }
    }
    public static boolean paramValueFalseCheck(String parameter) {
        boolean result;
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        try {
            result = Intrinsics.areEqual(parameter, "False");
        } catch (Exception e2) {
            Log.e(TAG, "paramValueControl: ", e2);
            result = false;
        }
        return result;
    }
    public static String parameterStringReplacer(int i2, char c2, String value) {
        String format;
        Intrinsics.checkNotNullParameter(value, "value");
        int length = value.length();
        try {
            if (length < i2) {
                String replacedefault = StringsKt.replace(new String(new char[i2 - length]), '0', c2, false);
                PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                format = String.format("%s%s", Arrays.copyOf(new Object[]{replacedefault, value}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            } else {
                PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                format = String.format("%s", Arrays.copyOf(new Object[]{value}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            }
            return format;
        } catch (Exception e2) {
            Log.e(TAG, "parameterStringReplacer: ", e2);
            return "";
        }
    }
    public boolean inverseParamValueTrueCheck(String parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        return !paramValueTrueCheck(parameter);
    }
    public static boolean paramValueNumberCheck(String parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        try {
            return Intrinsics.areEqual(parameter, BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "paramValueControl: ", e2);
            return false;
        }
    }
    public static boolean inverseParamValueNumberCheck(String parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        return !paramValueNumberCheck(parameter);
    }
    public static String formatStock(double d2) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.###");
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        String format = decimalFormat.format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static boolean urlControl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.startsWith(url, "http://", false);
    }
    public static String urlAddHtpp(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (urlControl(url)) {
            return url;
        }
        return "http://" + url;
    }
    public boolean urlEndSlashControl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.endsWith(url, "/", false);
    }
    public String urlAddSlash(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return url + '/';
    }
    public static String formatFirmNumber(int i2) {
        String formatStringEnglish = ContextUtils.formatStringEnglish("%03d", Integer.valueOf(i2));
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish, "formatStringEnglish(...)");
        return formatStringEnglish;
    }

    public String formatServerAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "serverAddress");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = "http://";
        if (StringsKt.startsWith(str, str2, false)) {
            str2 = "";
        }
        String format = String.format("%s%s", Arrays.copyOf(new Object[]{str2, str}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static String padRight(String str, int i2) {
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        String format = String.format("%1" + i2 + 's', Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static String padLeft(String str, int i2) {
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        String format = String.format("%1-" + i2 + 's', Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static String remove(String str, int i2, String value) {
        int length;
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (Intrinsics.areEqual(new SharedPreferencesHelper(ContextUtils.getmContext()).getApplicationLanguage(), "ar")) {
            length = value.length() + i2;
            value = '\u200e' + value;
        } else {
            length = value.length() + i2;
        }
        stringBuffer.replace(i2, length, value);
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "toString(...)");
        return stringBuffer2;
    }
    public static String getCreateAndFicheNo(ErpType erpType, int i2) {
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        if (erpType == ErpType.TIGER || erpType == ErpType.GO) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = uuid.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        }
        return String.valueOf(System.currentTimeMillis());
    }
    public static String[] split(String data, char c2) {
        Intrinsics.checkNotNullParameter(data, "data");
        Vector vector = new Vector();
        if (data.length() > 0) {
            int indexOfdefault = StringsKt.indexOf(data, c2, 0, false);
            if (indexOfdefault != -1) {
                int i2 = 0;
                while (indexOfdefault != -1) {
                    String substring = data.substring(i2, indexOfdefault);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    vector.addElement(substring);
                    i2 = indexOfdefault + 1;
                    indexOfdefault = StringsKt.indexOf(data, c2, i2, false);
                }
                if (i2 != data.length()) {
                    String substring2 = data.substring(i2);
                    Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                    vector.addElement(substring2);
                }
            } else {
                vector.addElement(data);
            }
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }
    public static String replace(String target, String from, String str) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(from, "from");
        int indexOfdefault = StringsKt.indexOf(target, from, 0, false);
        if (indexOfdefault == -1) {
            return target;
        }
        int length = from.length();
        char[] charArray = target.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (indexOfdefault != -1) {
            stringBuffer.append(charArray, i2, indexOfdefault - i2);
            stringBuffer.append(str);
            i2 = indexOfdefault + length;
            indexOfdefault = StringsKt.indexOf(target, from, i2, false);
        }
        stringBuffer.append(charArray, i2, charArray.length - i2);
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "toString(...)");
        return stringBuffer2;
    }
    public static String convTrCharEN(String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(target, "\u0130", "I"), "\u0131", "i"), "\u00e7", "c"), "\u00c7", "C"), "\u015f", "s"), "\u015e", ExifInterface.LATITUDE_SOUTH), "\u00f6", "o"), "\u00d6", "O"), "\u00fc", "u"), "\u00dc", "U"), "\u011f", "g"), "\u011e", "G");
    }
    public String convTrCharRepRes(String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(target, "\u0130", "\\u0130"), "\u0131", "\\u0131"), "\u00e7", "\\u00E7"), "\u00c7", "\\u00C7"), "\u015f", "\\u015F"), "\u015e", "\\u015E"), "\u00f6", "\\u00F6"), "\u00d6", "\\u00D6"), "\u00fc", "\\u00FC"), "\u00dc", "\\u00DC"), "\u011f", "\\u011F"), "\u011e", "\\u011E"), " ", "%20");
    }
    public String convTrChar(String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(target, "\\u0130", "\u0130"), "\\u0131", "\u0131"), "\\u00E7", "\u00e7"), "\\u00C7", "\u00c7"), "\\u015F", "\u015f"), "\\u015E", "\u015e"), "\\u00F6", "\u00f6"), "\\u00D6", "\u00d6"), "\\u00FC", "\u00fc"), "\\u00DC", "\u00dc"), "\\u011F", "\u011f"), "\\u011E", "\u011e");
    }
    public static String md5(String password) {
        Intrinsics.checkNotNullParameter(password, "password");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = password.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                while (hexString.length() < 2) {
                    hexString = '0' + hexString;
                }
                stringBuffer.append(hexString);
            }
            String stringBuffer2 = stringBuffer.toString();
            Intrinsics.checkNotNullExpressionValue(stringBuffer2, "toString(...)");
            return stringBuffer2;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }
    public static double toDouble(String deger) {
        Intrinsics.checkNotNullParameter(deger, "deger");
        try {
            return Double.parseDouble(StringsKt.replace(deger, ",", ".", false));
        } catch (Exception e2) {
            Log.e(TAG, "toDouble: ", e2);
            return 0.0d;
        }
    }
    public static double toDouble2(String deger) {
        Intrinsics.checkNotNullParameter(deger, "deger");
        try {
            return Double.parseDouble(StringsKt.replace(deger, ",", "", false));
        } catch (Exception e2) {
            Log.e(TAG, "toDouble2: ", e2);
            return 0.0d;
        }
    }
    public static String dFormat(double d2) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("tr"));
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        String format = new DecimalFormat(DecimalFormatString, decimalFormatSymbols).format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static String dFormat(String dFormatString) {
        Intrinsics.checkNotNullParameter(dFormatString, "dFormatString");
        try {
            return dFormat(Double.parseDouble(dFormatString));
        } catch (Exception unused) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
    }
    public static double roundTwoDecimals(double d2) {
        String format = new DecimalFormat("#.##").format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return toDouble(format);
    }
    public static String fFormat(float f2) {
        try {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("tr"));
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setGroupingSeparator('.');
            String format = new DecimalFormat(DecimalFormatString, decimalFormatSymbols).format(f2);
            Intrinsics.checkNotNull(format);
            return format;
        } catch (Exception unused) {
            return "0.00";
        }
    }
    public static String fFormat(double d2) {
        try {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("tr"));
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setGroupingSeparator('.');
            String format = new DecimalFormat(DecimalFormatString, decimalFormatSymbols).format(d2);
            Intrinsics.checkNotNull(format);
            return format;
        } catch (Exception unused) {
            return "0.00";
        }
    }
    public static String ffFormat(double d2) {
        try {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("tr"));
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setGroupingSeparator('.');
            String format = new DecimalFormat("###,###,###,##0.00", decimalFormatSymbols).format(d2);
            Intrinsics.checkNotNull(format);
            return format;
        } catch (Exception unused) {
            return "0.00";
        }
    }
    public static String fFormat(String strVal) {
        float f2;
        Intrinsics.checkNotNullParameter(strVal, "strVal");
        try {
            f2 = Float.parseFloat(strVal);
        } catch (Exception unused) {
            f2 = 0.0f;
        }
        return fFormat(f2);
    }
    public static String convertTotal2TextByCurrency(String total, int i2) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(total, "total");
        try {
            String currCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCurrCode(i2);
            String currencySubName = ErpCreator.getInstance().getmBaseErp().getCurrencySubName(i2);
            Intrinsics.areEqual(Locale.getDefault(), Locale.ENGLISH);
            if (StringsKt.indexOf(total, ",", 0, false) > 0) {
                String substring = total.substring(0, StringsKt.indexOf(total, ",", 0, false));
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                str = INSTANCE.getNumberDigitText(replace(replace(substring, ",", ""), ".", "")) + ' ' + currCode;
            } else {
                str = "";
            }
            if (StringsKt.indexOf(total, ",", 0, false) + 1 > 0 && StringsKt.indexOf(total, ",", 0, false) + 1 < total.length()) {
                String substring2 = total.substring(StringsKt.indexOf(total, ",", 0, false) + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                String numberDigitText = INSTANCE.getNumberDigitText(replace(replace(substring2, ",", ""), ".", ""));
                if (!Intrinsics.areEqual(numberDigitText, "Sifir")) {
                    str2 = numberDigitText + ' ' + currencySubName;
                    return str + ' ' + str2;
                }
            }
            str2 = "";
            return str + ' ' + str2;
        } catch (Exception e2) {
            Log.e(TAG, "convertTotal2Text: ", e2);
            return "";
        }
    }
    public static String convertTotal2Text(String total, String firstMoneyDesc, String secondMoneyDesc, String str) {
        String logoParamValue;
        String logoParamValue2;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(firstMoneyDesc, "firstMoneyDesc");
        Intrinsics.checkNotNullParameter(secondMoneyDesc, "secondMoneyDesc");
        try {
            if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                int localCurrType = ErpCreator.getInstance().getmBaseErp().getLocalCurrType();
                logoParamValue = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCurrCode(localCurrType);
                Intrinsics.checkNotNullExpressionValue(logoParamValue, "getCurrCode(...)");
                logoParamValue2 = ErpCreator.getInstance().getmBaseErp().getCurrencySubName(localCurrType);
                Intrinsics.checkNotNullExpressionValue(logoParamValue2, "getCurrencySubName(...)");
            } else {
                logoParamValue = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("LOKAL_KUR");
                Intrinsics.checkNotNullExpressionValue(logoParamValue, "getLogoParamValue(...)");
                logoParamValue2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getLogoParamValue("LOKAL_ALTKUR");
                Intrinsics.checkNotNullExpressionValue(logoParamValue2, "getLogoParamValue(...)");
            }
            Intrinsics.areEqual(Locale.getDefault(), Locale.ENGLISH);
            if (StringsKt.indexOf(total, ",", 0, false) > 0) {
                String substring = total.substring(0, StringsKt.indexOf(total, ",", 0, false));
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                str2 = INSTANCE.getNumberDigitText(replace(replace(substring, ",", ""), ".", "")) + ' ' + logoParamValue;
            } else {
                str2 = "";
            }
            if (StringsKt.indexOf(total, ",", 0, false) + 1 > 0 && StringsKt.indexOf(total, ",", 0, false) + 1 < total.length()) {
                String substring2 = total.substring(StringsKt.indexOf(total, ",", 0, false) + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                String numberDigitText = INSTANCE.getNumberDigitText(replace(replace(substring2, ",", ""), ".", ""));
                if (!Intrinsics.areEqual(numberDigitText, "Sifir")) {
                    str3 = numberDigitText + ' ' + logoParamValue2;
                    return str2 + ' ' + str3;
                }
            }
            str3 = "";
            return str2 + ' ' + str3;
        } catch (Exception e2) {
            Log.e(TAG, "convertTotal2Text: ", e2);
            return "";
        }
    }
    private String getNumberDigitText(String str) {
        String[] strArr = new String[10];
        String[] strArr2 = {"", ContextUtils.getStringResource(R.string.str_1), ContextUtils.getStringResource(R.string.str_2), ContextUtils.getStringResource(R.string.str_3), ContextUtils.getStringResource(R.string.str_4), ContextUtils.getStringResource(R.string.str_5), ContextUtils.getStringResource(R.string.str_6), ContextUtils.getStringResource(R.string.str_7), ContextUtils.getStringResource(R.string.str_8), ContextUtils.getStringResource(R.string.str_9)};
        String[] strArr3 = {"", ContextUtils.getStringResource(R.string.str_10), ContextUtils.getStringResource(R.string.str_20), ContextUtils.getStringResource(R.string.str_30), ContextUtils.getStringResource(R.string.str_40), ContextUtils.getStringResource(R.string.str_50), ContextUtils.getStringResource(R.string.str_60), ContextUtils.getStringResource(R.string.str_70), ContextUtils.getStringResource(R.string.str_80), ContextUtils.getStringResource(R.string.str_90)};
        strArr[0] = ContextUtils.getStringResource(R.string.str_trillion);
        strArr[1] = ContextUtils.getStringResource(R.string.str_billion);
        strArr[2] = ContextUtils.getStringResource(R.string.str_million);
        strArr[3] = ContextUtils.getStringResource(R.string.str_thousand);
        strArr[4] = "";
        if (controlIsError(str)) {
            return "Nr-Eror";
        }
        StringBuilder sb = new StringBuilder();
        String substring = "000000000000000000".substring(0, 15 - str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        sb.append(substring);
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = "";
        for (int i2 = 0; i2 < 5; i2++) {
            str2 = str2 + getStepText(getStrTmp(i2, sb2), i2, strArr3, strArr2, strArr);
        }
        if (!Intrinsics.areEqual(str2, "")) {
            return str2;
        }
        String stringResource = ContextUtils.getStringResource(R.string.str_0);
        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
        return stringResource;
    }
    private char[] getStrTmp(int i2, String str) {
        if (i2 == 0) {
            String substring = str.substring(0, 3);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            char[] charArray = substring.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
            return charArray;
        }
        if (i2 == 1) {
            String substring2 = str.substring(3, 6);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            char[] charArray2 = substring2.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray2, "toCharArray(...)");
            return charArray2;
        }
        if (i2 == 2) {
            String substring3 = str.substring(6, 9);
            Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
            char[] charArray3 = substring3.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray3, "toCharArray(...)");
            return charArray3;
        }
        if (i2 == 3) {
            String substring4 = str.substring(9, 12);
            Intrinsics.checkNotNullExpressionValue(substring4, "substring(...)");
            char[] charArray4 = substring4.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray4, "toCharArray(...)");
            return charArray4;
        }
        String substring5 = str.substring(12, 15);
        Intrinsics.checkNotNullExpressionValue(substring5, "substring(...)");
        char[] charArray5 = substring5.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray5, "toCharArray(...)");
        return charArray5;
    }
    private String getStepText(char[] cArr, int i2, String[] strArr, String[] strArr2, String[] strArr3) {
        String str;
        int convertStringToInt = convertStringToInt(String.valueOf(cArr[0]));
        int convertStringToInt2 = convertStringToInt(String.valueOf(cArr[1]));
        int convertStringToInt3 = convertStringToInt(String.valueOf(cArr[2]));
        char c2 = cArr[0];
        if (c2 == '0') {
            str = "";
        } else if (c2 == '1') {
            str = ContextUtils.getStringResource(R.string.str_hundred);
            Intrinsics.checkNotNull(str);
        } else {
            str = strArr2[convertStringToInt] + ContextUtils.getStringResource(R.string.str_hundred);
        }
        String str2 = str + strArr[convertStringToInt2] + strArr2[convertStringToInt3];
        if (str2 != "") {
            str2 = str2 + strArr3[i2];
        }
        if (i2 != 3 || !Intrinsics.areEqual(str2, ContextUtils.getStringResource(R.string.str_one_thousand))) {
            return str2;
        }
        String stringResource = ContextUtils.getStringResource(R.string.str_thousand);
        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
        return stringResource;
    }

    private boolean controlIsError(String str) {
        if (str.length() > 15) {
            return true;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (Intrinsics.compare(charAt, 57) > 0 || Intrinsics.compare(charAt, 48) < 0) {
                return true;
            }
        }
        return false;
    }
    public static String fillFormat(int i2, int i3) {
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        String format = String.format("%0" + i2 + 'd', Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static float toFloat(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Float.parseFloat(StringsKt.replace(value, ",", "\\.", false));
    }
    public static String formatNetsisDataResponse(DataResponse<?> dataResponse) {
        Intrinsics.checkNotNullParameter(dataResponse, "dataResponse");
        return INSTANCE.formatNetsisDataResponse(dataResponse.getErrorCode(), dataResponse.getErrorDesc());
    }
    public static String formatNetsisDataResponse(NetsisDataHeader dataHeader) {
        Intrinsics.checkNotNullParameter(dataHeader, "dataHeader");
        return INSTANCE.formatNetsisDataResponse(dataHeader.getErrorCode(), dataHeader.getErrorDesc());
    }
    public String formatNetsisDataResponse(String str, String str2) {
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        String format = String.format("%s:%s", Arrays.copyOf(new Object[]{str, str2}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static ArrayList<String> arrayToList(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (strArr != null) {
            Iterator it = ArrayIteratorKt.iterator(strArr);
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
    public static String stringAddHexStart(String str) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
    public static ObjectType getObjectType(Class<?> value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, String.class)) {
            return ObjectType.STRING;
        }
        Class cls = Integer.TYPE;
        if (Intrinsics.areEqual(value, cls) || Intrinsics.areEqual(value, cls)) {
            return ObjectType.INT;
        }
        Class cls2 = Float.TYPE;
        if (Intrinsics.areEqual(value, cls2) || Intrinsics.areEqual(value, cls2)) {
            return ObjectType.FLOAT;
        }
        Class cls3 = Double.TYPE;
        if (Intrinsics.areEqual(value, cls3) || Intrinsics.areEqual(value, cls3)) {
            return ObjectType.DOUBLE;
        }
        Class cls4 = Boolean.TYPE;
        if (Intrinsics.areEqual(value, cls4) || Intrinsics.areEqual(value, cls4)) {
            return ObjectType.BOOL;
        }
        return ObjectType.STRING;
    }
    public Object getValueDeclared(ObjectType objectType, String str) throws IOException {
        Intrinsics.checkNotNullParameter(objectType, "objectType");
        if (objectType == ObjectType.STRING) {
            return checkNullValueString(str);
        }
        if (objectType == ObjectType.INT) {
            return Integer.valueOf(convertStringToInt(str));
        }
        if (objectType == ObjectType.FLOAT) {
            return Float.valueOf(convertStringToFloat(str));
        }
        if (objectType == ObjectType.DOUBLE) {
            return Double.valueOf(convertStringToDouble(str));
        }
        if (objectType == ObjectType.BOOL) {
            return Boolean.valueOf(convertStringToBoolean(str));
        }
        return "";
    }
    public static String getBarcodeText(ArrayList<BarcodeResult> arrayList) {
        String str = "";
        if (arrayList == null || arrayList.size() <= 0) {
            return "";
        }
        for (BarcodeResult barcodeResult : arrayList) {
            str = str + '\'' + barcodeResult.getBarcode() + "' ,";
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String substring = str.substring(0, str.length() - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
    public static String getLastnCharacters(String inputString, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        int length = inputString.length();
        if (length <= i2 || (i3 = length - i2) < inputString.length() || i3 > inputString.length()) {
            return inputString;
        }
        String substring = inputString.substring(i3);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
    public static void logLargeContent(String str, String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (content.length() > 4000) {
            String substring = content.substring(0, 4000);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            Log.d(str, substring);
            String substring2 = content.substring(4000);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            logLargeContent(str, substring2);
            return;
        }
        Log.d(str, content);
    }
    public String cat(String title, String str) {
        Intrinsics.checkNotNullParameter(title, "title");
        if (str == null || TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(title);
            sb.append(" ");
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            sb.append(context.getString(R.string.empty_text));
            return sb.toString();
        }
        return title + " " + str;
    }
    public static String clearTurkishChars(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        char[] cArr = {'\u0131', '\u0130', '\u00fc', '\u00dc', '\u00f6', '\u00d6', '\u015f', '\u015e', '\u00e7', '\u00c7', '\u011f', '\u011e'};
        char[] cArr2 = {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i2 = 0; i2 < 12; i2++) {
            str = new Regex(String.valueOf(cArr[i2])).replace(str, String.valueOf(cArr2[i2]));
        }
        return str;
    }
    public static String formatList(List<String> stringArrayList, boolean z) {
        Intrinsics.checkNotNullParameter(stringArrayList, "stringArrayList");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int size = stringArrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (z) {
                sb.append("'");
                sb.append(stringArrayList.get(i2));
                sb.append("'");
            } else {
                sb.append(stringArrayList.get(i2));
            }
            if (i2 < stringArrayList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    public static String encloseWithQuotes(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        List splitdefault = StringsKt.split(value, new String[]{","}, false, 0);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(splitdefault, 10));
        Iterator it = splitdefault.iterator();
        while (it.hasNext()) {
            arrayList.add('\'' + StringsKt.trim((String) it.next()).toString() + '\'');
        }
        return CollectionsKt.joinToString(arrayList, ",", null, null, 0, null, null);
    }
    public static int compareVersionNames(String str, String str2) {
        int i2;
        Intrinsics.checkNotNullParameter(str, "oldVersionName");
        Intrinsics.checkNotNullParameter(str2, "newVersionName");
        List splitdefault = StringsKt.split(str, new String[]{"."}, false, 0);
        List splitdefault2 = StringsKt.split(str2, new String[]{"."}, false, 0);
        int min = Math.min(splitdefault.size(), splitdefault2.size());
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i2 = -1;
            if (i4 >= min) {
                break;
            }
            int parseInt = Integer.parseInt((String) splitdefault.get(i4));
            int parseInt2 = Integer.parseInt((String) splitdefault2.get(i4));
            if (parseInt < parseInt2) {
                i3 = -1;
                break;
            } else if (parseInt > parseInt2) {
                i3 = 1;
                break;
            } else {
                i4++;
            }
        }
        if (i3 != 0 || splitdefault.size() == splitdefault2.size()) {
            return i3;
        }
        if (splitdefault.size() > splitdefault2.size()) {
            i2 = 1;
        }
        return i2;
    }
    public static CharSequence removeAllSpacesFilterlambda3(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        return StringsKt.replace(charSequence.toString(), " ", "", false);
    }
    public void removeAllSpacesFilter(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        editText.setFilters(new InputFilter[]{new InputFilter() {
            public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
                return StringUtils.removeAllSpacesFilterlambda3(charSequence, i2, i3, spanned, i4, i5);
            }
        }});
    }
    public  String formatForCsv(Object obj) {
        String str;
        if (obj == null || (str = obj.toString()) == null) {
            str = "";
        }
        if (str.length() == 0) {
            return "";
        }
        try {
            String plainString = new BigDecimal(StringsKt.replace(str, ',', '.', false)).setScale(2, RoundingMode.HALF_UP).toPlainString();
            Intrinsics.checkNotNull(plainString);
            String replacedefault = StringsKt.replace(plainString, '.', ',', false);
            return "=\"" + replacedefault + JsonFactory.DEFAULT_QUOTE_CHAR;
        } catch (Exception unused) {
            return clearTurkishChars(str);
        }
    }
    public static String formatForDisplay(double d2) {
        try {
            String format = new DecimalFormat(DecimalFormatString).format(BigDecimal.valueOf(d2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e(TAG, ConvertString, e2);
            return "";
        }
    }

}
