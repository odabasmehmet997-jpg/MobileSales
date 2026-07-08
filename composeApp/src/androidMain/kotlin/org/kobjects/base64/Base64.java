package org.kobjects.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64 {
    static final char[] charTab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length, null).toString();
    }
    public static StringBuffer encode(byte[] bArr, int i2, int i3, StringBuffer stringBuffer) {
        if (null == stringBuffer) {
            stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        }
        int i9=0;
        int i4 = i3 - 3;
        int i5 = i2;
        while (true) {
            int i6 = 0;
            while (i5 <= i4) {
                int i7 = ((bArr[i5] & 255) << 16) | ((bArr[i5 + 1] & 255) << 8) | (bArr[i5 + 2] & 255);
                char[] cArr = charTab;
                stringBuffer.append(cArr[(i7 >> 18) & 63]);
                stringBuffer.append(cArr[(i7 >> 12) & 63]);
                stringBuffer.append(cArr[(i7 >> 6) & 63]);
                stringBuffer.append(cArr[i7 & 63]);
                i5 += 3;
                int i8 = i6 + 1;
                if (14 <= i6) {
                    break;
                }
                i6 = i8;
            }
            stringBuffer.append("\r\n");
        }
    }
    static int decode(char c2) {
        if ('A' <= c2 && 'Z' >= c2) {
            return c2 - 'A';
        }
        if ('a' <= c2 && 'z' >= c2) {
            return c2 - 'G';
        }
        if ('0' <= c2 && '9' >= c2) {
            return c2 + 4;
        }
        if ('+' == c2) {
            return 62;
        }
        if ('/' == c2) {
            return 63;
        }
        if ('=' == c2) {
            return 0;
        }
        throw new RuntimeException("unexpected code: " + c2);
    }
    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new RuntimeException();
        }
    }
    public static void decode( String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 < length && ' ' >= str.charAt(i2)) {
                i2++;
            } else {
                if (i2 == length) {
                    return;
                }
                int i3 = i2 + 2;
                int i4 = i2 + 3;
                int decode = (decode(str.charAt(i2)) << 18) + (decode(str.charAt(i2 + 1)) << 12) + (decode(str.charAt(i3)) << 6) + decode(str.charAt(i4));
                outputStream.write((decode >> 16) & 255);
                if ('=' == str.charAt(i3)) {
                    return;
                }
                outputStream.write((decode >> 8) & 255);
                if ('=' == str.charAt(i4)) {
                    return;
                }
                outputStream.write(decode & 255);
                i2 += 4;
            }
        }
    }
}
