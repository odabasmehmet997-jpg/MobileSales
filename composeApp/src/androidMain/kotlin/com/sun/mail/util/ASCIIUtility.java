package com.sun.mail.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASCIIUtility {
    public static int parseInt(byte[] bArr, int i2, int i3, int i4) throws NumberFormatException {
        boolean z;
        int i5;
        int i6;
        if (null == bArr) {
            throw new NumberFormatException("null");
        } else if (i3 > i2) {
            int i7 = 0;
            if (45 == bArr[i2]) {
                i6 = i2 + 1;
                i5 = Integer.MIN_VALUE;
                z = true;
            } else {
                i5 = -2147483647;
                i6 = i2;
                z = false;
            }
            int i8 = i5 / i4;
            if (i6 < i3) {
                int i9 = i6 + 1;
                int digit = Character.digit((char) bArr[i6], i4);
                if (0 <= digit) {
                    int i10 = i9;
                    i7 = -digit;
                    i6 = i10;
                } else {
                    final String stringBuffer = "illegal number: " +
                            toString(bArr, i2, i3);
                    throw new NumberFormatException(stringBuffer);
                }
            }
            while (i6 < i3) {
                int i11 = i6 + 1;
                int digit2 = Character.digit((char) bArr[i6], i4);
                if (0 > digit2) {
                    throw new NumberFormatException("illegal number");
                } else if (i7 >= i8) {
                    int i12 = i7 * i4;
                    if (i12 >= i5 + digit2) {
                        i7 = i12 - digit2;
                        i6 = i11;
                    } else {
                        throw new NumberFormatException("illegal number");
                    }
                } else {
                    throw new NumberFormatException("illegal number");
                }
            }
            if (!z) {
                return -i7;
            }
            if (i6 > i2 + 1) {
                return i7;
            }
            throw new NumberFormatException("illegal number");
        } else {
            throw new NumberFormatException("illegal number");
        }
    }
    public static int parseInt(byte[] bArr, int i2, int i3) throws NumberFormatException {
        return parseInt(bArr, i2, i3, 10);
    }
    public static long parseLong(byte[] r18, int r19, int r20, int r21) throws NumberFormatException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.ASCIIUtility.parseLong(byte[], int, int, int):long");
    }
    public static long parseLong(byte[] bArr, int i2, int i3) throws NumberFormatException {
        return parseLong(bArr, i2, i3, 10);
    }
    public static String toString(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        char[] cArr = new char[i4];
        int i5 = 0;
        while (i5 < i4) {
            cArr[i5] = (char) (bArr[i2] & 255);
            i5++;
            i2++;
        }
        return new String(cArr);
    }
    public static String toString(byte[] bArr) {
        return toString(bArr, 0, bArr.length);
    }
    public static String toString(ByteArrayInputStream byteArrayInputStream) {
        int available = byteArrayInputStream.available();
        char[] cArr = new char[available];
        byte[] bArr = new byte[available];
        byteArrayInputStream.read(bArr, 0, available);
        for (int i2 = 0; i2 < available; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return new String(cArr);
    }
    public static byte[] getBytes(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) charArray[i2];
        }
        return bArr;
    }
    public static byte[] getBytes(InputStream inputStream) throws IOException {
        if (inputStream instanceof ByteArrayInputStream) {
            int available = inputStream.available();
            byte[] bArr = new byte[available];
            inputStream.read(bArr, 0, available);
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr2, 0, 1024);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
    }
}
