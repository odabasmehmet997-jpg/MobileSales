package okio;

import kotlin.jvm.internal.Intrinsics;

import java.util.Arrays;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public class _Base64Kt {
    private static final byte[] BASE64;
    private static final byte[] BASE64_URL_SAFE;
    static {
        ByteString.Companion companion = ByteString.Companion;
        BASE64 = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getDataokio();
        BASE64_URL_SAFE = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getDataokio();
    }
    public static byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }
    public static byte[] decodeBase64ToArray(String str) {
        int i2 = 0;
        char cCharAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        while (0 < length && ('=' == (cCharAt = str.charAt(length - 1)) || '\n' == cCharAt || '\r' == cCharAt || ' ' == cCharAt || '\t' == cCharAt)) {
            length--;
        }
        int i3 = (length * 6) / 8;
        byte[] bArr = new byte[i3];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt2 = str.charAt(i7);
            if ('A' <= cCharAt2 && '[' > cCharAt2) {
                i2 = cCharAt2 - 'A';
            } else if ('a' <= cCharAt2 && '{' > cCharAt2) {
                i2 = cCharAt2 - 'G';
            } else if ('0' <= cCharAt2 && ':' > cCharAt2) {
                i2 = cCharAt2 + 4;
            } else if ('+' == cCharAt2 || '-' == cCharAt2) {
                i2 = 62;
            } else if ('/' == cCharAt2 || '_' == cCharAt2) {
                i2 = 63;
            } else {
                if ('\n' != cCharAt2 && '\r' != cCharAt2 && ' ' != cCharAt2 && '\t' != cCharAt2) {
                    return null;
                }
            }
            i5 = (i5 << 6) | i2;
            i4++;
            if (0 == i4 % 4) {
                bArr[i6] = (byte) (i5 >> 16);
                int i8 = i6 + 2;
                bArr[i6 + 1] = (byte) (i5 >> 8);
                i6 += 3;
                bArr[i8] = (byte) i5;
            }
        }
        int i9 = i4 % 4;
        if (1 == i9) {
            return null;
        }
        if (2 == i9) {
            bArr[i6] = (byte) ((i5 << 12) >> 16);
            i6++;
        } else if (3 == i9) {
            int i10 = i5 << 6;
            int i11 = i6 + 1;
            bArr[i6] = (byte) (i10 >> 16);
            i6 += 2;
            bArr[i11] = (byte) (i10 >> 8);
        }
        if (i6 == i3) {
            return bArr;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, i6);
        checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
        return bArrCopyOf;
    }
    public static String encodeBase64default(byte[] bArr, byte[] bArr2, int i2, Object obj) {
        if (0 != (i2 & 1)) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }
    public static String encodeBase64(byte[] bArr, byte[] map) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        byte[] bArr2 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            byte b2 = bArr[i2];
            int i4 = i2 + 2;
            byte b3 = bArr[i2 + 1];
            i2 += 3;
            byte b4 = bArr[i4];
            bArr2[i3] = map[(b2 & 255) >> 2];
            bArr2[i3 + 1] = map[((b2 & 3) << 4) | ((b3 & 255) >> 4)];
            int i5 = i3 + 3;
            bArr2[i3 + 2] = map[((b3 & 15) << 2) | ((b4 & 255) >> 6)];
            i3 += 4;
            bArr2[i5] = map[b4 & 63];
        }
        int length2 = bArr.length - length;
        if (1 == length2) {
            byte b5 = bArr[i2];
            bArr2[i3] = map[(b5 & 255) >> 2];
            bArr2[i3 + 1] = map[(b5 & 3) << 4];
            final byte b6 = 61;
            bArr2[i3 + 2] = b6;
            bArr2[i3 + 3] = b6;
        } else if (2 == length2) {
            int i6 = i2 + 1;
            byte b7 = bArr[i2];
            byte b8 = bArr[i6];
            bArr2[i3] = map[(b7 & 255) >> 2];
            bArr2[i3 + 1] = map[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            bArr2[i3 + 2] = map[(b8 & 15) << 2];
            bArr2[i3 + 3] = 61;
        }
        return _JvmPlatformKt.toUtf8String(bArr2);
    }
}
