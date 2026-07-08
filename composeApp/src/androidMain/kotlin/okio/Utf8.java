package okio;

import kotlin.jvm.internal.Intrinsics;

public final class Utf8 {
    public static long sizedefault(String str, int i2, int i3, int i4, Object obj) {
        if (0 != (i4 & 1)) {
            i2 = 0;
        }
        if (0 != (i4 & 2)) {
            i3 = str.length();
        }
        return size(str, i2, i3);
    }
    public static long size(String str, int i2, int i3) {
        int i4 = 0;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (0 > i2) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i2));
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2));
        }
        if (i3 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + str.length()));
        }
        long j2 = 0;
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if ('\u0080' > cCharAt) {
                j2++;
            } else {
                if ('\u0800' > cCharAt) {
                    i4 = 2;
                } else if ('\ud800' > cCharAt || '\udfff' < cCharAt) {
                    i4 = 3;
                } else {
                    int i5 = i2 + 1;
                    char cCharAt2 = i5 < i3 ? str.charAt(i5) : (char) 0;
                    if ('\udbff' < cCharAt || '\udc00' > cCharAt2 || '\udfff' < cCharAt2) {
                        j2++;
                        i2 = i5;
                    } else {
                        j2 += 4;
                        i2 += 2;
                    }
                }
                j2 += i4;
            }
            i2++;
        }
        return j2;
    }
}
