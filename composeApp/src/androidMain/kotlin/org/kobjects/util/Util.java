package org.kobjects.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
    public static OutputStream streamcopy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final int i2 = 1048576 <= Runtime.getRuntime().freeMemory() ? 16384 : 128;
        final byte[] bArr = new byte[i2];
        while (true) {
            final int read = inputStream.read(bArr, 0, i2);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return outputStream;
            }
        }
    }
    public static int indexOf(final Object[] objArr, final Object obj) {
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (objArr[i2].equals(obj)) {
                return i2;
            }
        }
        return -1;
    }
    public static String buildUrl(String str, final String str2) {
        final int indexOf = str2.indexOf(58);
        String str3 = "file:///";
        if (str2.startsWith("/") || 1 == indexOf) {
            return "file:///" + str2;
        }
        if (2 < indexOf && 6 > indexOf) {
            return str2;
        }
        if (null != str) {
            if (-1 == str.indexOf(58)) {
                str = "file:///" + str;
            }
            if (str.endsWith("/")) {
                str3 = str;
            } else {
                str3 = str + "/";
            }
        }
        return str3 + str2;
    }
    public static void sort(final Object[] objArr, final int i2, final int i3) {
        int i4 = 0;
        final int i5 = i3 - i2;
        if (2 >= i5) {
            if (2 == i5) {
                final int i6 = i2 + 1;
                if (0 < objArr[i2].toString().compareTo(objArr[i6].toString())) {
                    final Object obj = objArr[i2];
                    objArr[i2] = objArr[i6];
                    objArr[i6] = obj;
                    return;
                }
                return;
            }
            return;
        }
        if (3 == i5) {
            final int i7 = i2 + 2;
            Util.sort(objArr, i2, i7);
            Util.sort(objArr, i2 + 1, i2 + 3);
            Util.sort(objArr, i2, i7);
            return;
        }
        final int i8 = (i2 + i3) / 2;
        Util.sort(objArr, i2, i8);
        Util.sort(objArr, i8, i3);
        final Object[] objArr2 = new Object[i5];
        int i9 = i2;
        int i10 = i8;
        for (int i11 = 0; i11 < i5; i11++) {
            if (i9 == i8) {
                i4 = i10 + 1;
                objArr2[i11] = objArr[i10];
            } else if (i10 == i3 || 0 > objArr[i9].toString().compareTo(objArr[i10].toString())) {
                objArr2[i11] = objArr[i9];
                i9++;
            } else {
                i4 = i10 + 1;
                objArr2[i11] = objArr[i10];
            }
            i10 = i4;
        }
        System.arraycopy(objArr2, 0, objArr, i2, i5);
    }
}
