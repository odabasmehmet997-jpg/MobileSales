package org.kobjects.util;

import com.fasterxml.jackson.core.JsonFactory;
import java.util.Vector;

public class Csv {
    public static String encode(final String str, final char c2) {
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            final char charAt = str.charAt(i2);
            if (charAt == c2 || '^' == charAt) {
                stringBuffer.append(charAt);
                stringBuffer.append(charAt);
            } else if (' ' > charAt) {
                stringBuffer.append('^');
                stringBuffer.append((char) (charAt + '@'));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }
    public static String encode(final Object[] objArr) {
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (0 != i2) {
                stringBuffer.append(',');
            }
            final Object obj = objArr[i2];
            if ((obj instanceof Number) || (obj instanceof Boolean)) {
                stringBuffer.append(obj);
            } else {
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                stringBuffer.append(Csv.encode(obj.toString(), JsonFactory.DEFAULT_QUOTE_CHAR));
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            }
        }
        return stringBuffer.toString();
    }
    public static String[] decode(final String str) {
        int i2;
        final Vector vector = new Vector();
        final int length = str.length();
        final int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 < length && ' ' >= str.charAt(i4)) {
                i4++;
            } else {
                if (i4 >= length) {
                    break;
                }
                if ('\"' == str.charAt(i4)) {
                    int i5 = i4 + 1;
                    final StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        i2 = i5 + 1;
                        final char charAt = str.charAt(i5);
                        if ('^' == charAt && i2 < length) {
                            i5 += 2;
                            char charAt2 = str.charAt(i2);
                            if ('^' != charAt2) {
                                charAt2 = (char) (charAt2 - '@');
                            }
                            stringBuffer.append(charAt2);
                        } else {
                            if ('\"' == charAt) {
                                if (i2 == length || '\"' != str.charAt(i2)) {
                                    break;
                                }
                                i2 = i5 + 2;
                            }
                            stringBuffer.append(charAt);
                            i5 = i2;
                        }
                    }
                    vector.addElement(stringBuffer.toString());
                    while (i2 < length && ' ' >= str.charAt(i2)) {
                        i2++;
                    }
                    if (i2 >= length) {
                        break;
                    }
                    if (',' != str.charAt(i2)) {
                        throw new RuntimeException("Comma expected at " + i2 + " line: " + str);
                    }
                    i4 = i2 + 1;
                } else {
                    final int indexOf = str.indexOf(44, i4);
                    if (-1 == indexOf) {
                        vector.addElement(str.substring(i4).trim());
                        break;
                    }
                    vector.addElement(str.substring(i4, indexOf).trim());
                    i4 = indexOf + 1;
                }
            }
        }
        return new String[0];
    }
}
