package org.kobjects.util;

public class Strings {
    public static String replace(final String str, final String str2, final String str3) {
        int indexOf = str.indexOf(str2);
        if (-1 == indexOf) {
            return str;
        }
        final StringBuffer stringBuffer = new StringBuffer(str.substring(0, indexOf));
        while (true) {
            stringBuffer.append(str3);
            final int length = indexOf + str2.length();
            final int indexOf2 = str.indexOf(str2, length);
            if (-1 != indexOf2) {
                stringBuffer.append(str, length, indexOf2);
                indexOf = indexOf2;
            } else {
                stringBuffer.append(str.substring(length));
                return stringBuffer.toString();
            }
        }
    }
    public static String toAscii(final String str) {
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            final char charAt = str.charAt(i2);
            if (' ' >= charAt) {
                stringBuffer.append(' ');
            } else if ('\u007f' > charAt) {
                stringBuffer.append(charAt);
            } else if ('\u00c4' == charAt) {
                stringBuffer.append("Ae");
            } else if ('\u00d6' == charAt) {
                stringBuffer.append("Oe");
            } else if ('\u00dc' == charAt) {
                stringBuffer.append("Ue");
            } else if ('\u00df' == charAt) {
                stringBuffer.append("ss");
            } else if ('\u00e4' == charAt) {
                stringBuffer.append("ae");
            } else if ('\u00f6' == charAt) {
                stringBuffer.append("oe");
            } else if ('\u00fc' == charAt) {
                stringBuffer.append("ue");
            } else {
                stringBuffer.append('?');
            }
        }
        return stringBuffer.toString();
    }
    public static String fill(final String str, final int i2, final char c2) {
        final boolean z = 0 > i2;
        final int abs = Math.abs(i2);
        if (str.length() >= abs) {
            return str;
        }
        final StringBuffer stringBuffer = new StringBuffer();
        for (int length = abs - str.length(); 0 < length; length--) {
            stringBuffer.append(c2);
        }
        if (z) {
            stringBuffer.append(str);
            return stringBuffer.toString();
        }
        return str + stringBuffer;
    }
    public static String beautify(final String str) {
        final StringBuffer stringBuffer = new StringBuffer();
        if (0 < str.length()) {
            stringBuffer.append(Character.toUpperCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length() - 1; i2++) {
                final char charAt = str.charAt(i2);
                if (Character.isUpperCase(charAt) && Character.isLowerCase(str.charAt(i2 - 1)) && Character.isLowerCase(str.charAt(i2 + 1))) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append(charAt);
            }
            if (1 < str.length()) {
                stringBuffer.append(str.charAt(str.length() - 1));
            }
        }
        return stringBuffer.toString();
    }
    public static String lTrim(final String str, final String str2) {
        final int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            final char charAt = str.charAt(i2);
            if (null != str2) {
                if (-1 == str2.indexOf(charAt)) {
                    break;
                }
                i2++;
            } else {
                if (' ' < charAt) {
                    break;
                }
                i2++;
            }
        }
        return 0 == i2 ? str : str.substring(i2);
    }
    public static String rTrim(final String str, final String str2) {
        int length = str.length() - 1;
        while (0 <= length) {
            final char charAt = str.charAt(length);
            if (null != str2) {
                if (-1 == str2.indexOf(charAt)) {
                    break;
                }
                length--;
            } else {
                if (' ' < charAt) {
                    break;
                }
                length--;
            }
        }
        return length == str.length() + (-1) ? str : str.substring(0, length + 1);
    }
}
