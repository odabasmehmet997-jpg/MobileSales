package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonFactory;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public enum ISO8601Utils {
    ;
    private static final TimeZone TIMEZONE_Z = TimeZone.getTimeZone("UTC");

    public static String format(final Date date) {
        return ISO8601Utils.format(date, false, ISO8601Utils.TIMEZONE_Z);
    }

    public static String format(final Date date, final boolean z, final TimeZone timeZone) {
        return ISO8601Utils.format(date, z, timeZone, Locale.US);
    }

    public static String format(final Date date, final boolean z, final TimeZone timeZone, final Locale locale) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, locale);
        gregorianCalendar.setTime(date);
        final StringBuilder sb = new StringBuilder(30);
        sb.append(String.format("%04d-%02d-%02dT%02d:%02d:%02d", Integer.valueOf(gregorianCalendar.get(1)), Integer.valueOf(gregorianCalendar.get(2) + 1), Integer.valueOf(gregorianCalendar.get(5)), Integer.valueOf(gregorianCalendar.get(11)), Integer.valueOf(gregorianCalendar.get(12)), Integer.valueOf(gregorianCalendar.get(13))));
        if (z) {
            sb.append(String.format(".%03d", Integer.valueOf(gregorianCalendar.get(14))));
        }
        final int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (0 != offset) {
            final int i2 = offset / 60000;
            sb.append(String.format("%c%02d:%02d", Character.valueOf(0 > offset ? '-' : '+'), Integer.valueOf(Math.abs(i2 / 60)), Integer.valueOf(Math.abs(i2 % 60))));
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    public static Date parse(final String str, final ParsePosition parsePosition) throws ParseException {
        final String str2;
        final int i2;
        final int i3;
        final int i4;
        int i5;
        final int length;
        final TimeZone timeZone;
        final char cCharAt;
        try {
            final int index = parsePosition.getIndex();
            int i6 = index + 4;
            final int i7 = ISO8601Utils.parseInt(str, index, i6);
            if (ISO8601Utils.checkOffset(str, i6, '-')) {
                i6 = index + 5;
            }
            int i8 = i6 + 2;
            final int i9 = ISO8601Utils.parseInt(str, i6, i8);
            if (ISO8601Utils.checkOffset(str, i8, '-')) {
                i8 = i6 + 3;
            }
            int i10 = i8 + 2;
            final int i11 = ISO8601Utils.parseInt(str, i8, i10);
            final boolean zCheckOffset = ISO8601Utils.checkOffset(str, i10, 'T');
            if (!zCheckOffset && str.length() <= i10) {
                final GregorianCalendar gregorianCalendar = new GregorianCalendar(i7, i9 - 1, i11);
                parsePosition.setIndex(i10);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i12 = i8 + 5;
                final int i13 = ISO8601Utils.parseInt(str, i8 + 3, i12);
                if (ISO8601Utils.checkOffset(str, i12, ':')) {
                    i12 = i8 + 6;
                }
                int i14 = i12 + 2;
                final int i15 = ISO8601Utils.parseInt(str, i12, i14);
                if (ISO8601Utils.checkOffset(str, i14, ':')) {
                    i14 = i12 + 3;
                }
                if (str.length() <= i14 || 'Z' == (cCharAt = str.charAt(i14)) || '+' == cCharAt || '-' == cCharAt) {
                    i4 = 0;
                    i5 = 0;
                    i3 = i15;
                    i10 = i14;
                    i2 = i13;
                } else {
                    final int i16 = i14 + 2;
                    i5 = ISO8601Utils.parseInt(str, i14, i16);
                    if (59 < i5 && 63 > i5) {
                        i5 = 59;
                    }
                    if (ISO8601Utils.checkOffset(str, i16, '.')) {
                        final int i17 = i14 + 3;
                        final int iIndexOfNonDigit = ISO8601Utils.indexOfNonDigit(str, i14 + 4);
                        final int iMin = Math.min(iIndexOfNonDigit, i14 + 6);
                        int i18 = ISO8601Utils.parseInt(str, i17, iMin);
                        final int i19 = iMin - i17;
                        if (1 == i19) {
                            i18 *= 100;
                        } else if (2 == i19) {
                            i18 *= 10;
                        }
                        i2 = i13;
                        i10 = iIndexOfNonDigit;
                        i3 = i15;
                        i4 = i18;
                    } else {
                        i2 = i13;
                        i10 = i16;
                        i4 = 0;
                        i3 = i15;
                    }
                }
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            }
            if (str.length() <= i10) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            final char cCharAt2 = str.charAt(i10);
            if ('Z' == cCharAt2) {
                timeZone = ISO8601Utils.TIMEZONE_Z;
                length = i10 + 1;
            } else {
                if ('+' != cCharAt2 && '-' != cCharAt2) {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt2 + "'");
                }
                final String strSubstring = str.substring(i10);
                length = i10 + strSubstring.length();
                if ("+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                    timeZone = ISO8601Utils.TIMEZONE_Z;
                } else {
                    final String str3 = "GMT" + strSubstring;
                    final TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                    final String id = timeZone2.getID();
                    if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                    }
                    timeZone = timeZone2;
                }
            }
            final GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, i7);
            gregorianCalendar2.set(2, i9 - 1);
            gregorianCalendar2.set(5, i11);
            gregorianCalendar2.set(11, i2);
            gregorianCalendar2.set(12, i3);
            gregorianCalendar2.set(13, i5);
            gregorianCalendar2.set(14, i4);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (final Exception e2) {
            if (null == str) {
                str2 = null;
            } else {
                str2 = JsonFactory.DEFAULT_QUOTE_CHAR + str + JsonFactory.DEFAULT_QUOTE_CHAR;
            }
            String message = e2.getMessage();
            if (null == message || message.isEmpty()) {
                message = "(" + e2.getClass().getName() + ")";
            }
            final ParseException parseException = new ParseException("Failed to parse date " + str2 + ": " + message, parsePosition.getIndex());
            parseException.initCause(e2);
            throw parseException;
        }
    }

    private static boolean checkOffset(final String str, final int i2, final char c2) {
        return i2 < str.length() && str.charAt(i2) == c2;
    }

    private static int parseInt(final String str, final int i2, final int i3) throws NumberFormatException {
        int i4;
        int i5;
        if (0 > i2 || i3 > str.length() || i2 > i3) {
            throw new NumberFormatException(str);
        }
        if (i2 < i3) {
            i5 = i2 + 1;
            final int iDigit = Character.digit(str.charAt(i2), 10);
            if (0 > iDigit) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = -iDigit;
        } else {
            i4 = 0;
            i5 = i2;
        }
        while (i5 < i3) {
            final int i6 = i5 + 1;
            final int iDigit2 = Character.digit(str.charAt(i5), 10);
            if (0 > iDigit2) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = (i4 * 10) - iDigit2;
            i5 = i6;
        }
        return -i4;
    }

    private static int indexOfNonDigit(final String str, int i2) {
        while (i2 < str.length()) {
            final char cCharAt = str.charAt(i2);
            if ('0' > cCharAt || '9' < cCharAt) {
                return i2;
            }
            i2++;
        }
        return str.length();
    }
}
