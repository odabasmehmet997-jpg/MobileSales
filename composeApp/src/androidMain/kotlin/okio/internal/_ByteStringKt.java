package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;

public class _ByteStringKt {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static  char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }
    public static void commonWrite(ByteString byteString, Buffer buffer, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write(byteString.getDataokio(), i2, i3);
    }
    public static int decodeHexDigit(char c2) {
        if ('0' <= c2 && ':' > c2) {
            return c2 - '0';
        }
        if ('a' <= c2 && 'g' > c2) {
            return c2 - 'W';
        }
        if ('A' <= c2 && 'G' > c2) {
            return c2 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }
    public static int codePointIndexToCharIndex(byte[] bArr, int i2) {
        byte b2;
        int i3;
        int length = bArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            byte b3 = bArr[i4];
            if (0 <= b3) {
                int i7 = i6 + 1;
                if (i6 == i2) {
                    return i5;
                }
                if ((10 != b3 && 13 != b3 && ((0 <= b3 && 32 > b3) || (Byte.MAX_VALUE <= b3 && 160 > b3))) || 65533 == b3) {
                    return -1;
                }
                i5 += 65536 > b3 ? 1 : 2;
                i4++;
                while (true) {
                    i6 = i7;
                    if (i4 < length && 0 <= (b2 = bArr[i4])) {
                        i4++;
                        i7 = i6 + 1;
                        if (i6 == i2) {
                            return i5;
                        }
                        if ((10 == b2 || 13 == b2 || ((0 > b2 || 32 <= b2) && (Byte.MAX_VALUE > b2 || 160 <= b2))) && 65533 != b2) {
                            i5 += 65536 > b2 ? 1 : 2;
                        }
                    }
                }
            } else if (-2 == (b3 >> 5)) {
                int i8 = i4 + 1;
                if (length <= i8) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b4 = bArr[i8];
                if (128 != (b4 & 192)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                int i9 = (b3 << 6) ^ (b4 ^ 3968);
                if (128 > i9) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                int i10 = i6 + 1;
                if (i6 == i2) {
                    return i5;
                }
                if ((10 != i9 && 13 != i9 && ((0 <= i9 && 32 > i9) || (127 <= i9 && 160 > i9))) || 65533 == i9) {
                    return -1;
                }
                i5 += 65536 > i9 ? 1 : 2;
                i4 += 2;
                i6 = i10;
            } else {
                if (-2 == (b3 >> 4)) {
                    int i11 = i4 + 2;
                    if (length <= i11) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    byte b5 = bArr[i4 + 1];
                    if (128 != (b5 & 192)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    byte b6 = bArr[i11];
                    if (128 != (b6 & 192)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    int i12 = (b3 << 12) ^ ((b6 ^ (-123008)) ^ (b5 << 6));
                    if (2048 > i12) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    if (55296 <= i12 && 57344 > i12) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    i3 = i6 + 1;
                    if (i6 == i2) {
                        return i5;
                    }
                    if ((10 != i12 && 13 != i12 && ((0 <= i12 && 32 > i12) || (127 <= i12 && 160 > i12))) || 65533 == i12) {
                        return -1;
                    }
                    i5 += 65536 > i12 ? 1 : 2;
                    i4 += 3;
                } else {
                    if (-2 != (b3 >> 3)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    int i13 = i4 + 3;
                    if (length <= i13) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    byte b7 = bArr[i4 + 1];
                    if (128 != (b7 & 192)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    byte b8 = bArr[i4 + 2];
                    if (128 != (b8 & 192)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    byte b9 = bArr[i13];
                    if (128 != (b9 & 192)) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    int i14 = (b3 << 18) ^ (((b9 ^ 3678080) ^ (b8 << 6)) ^ (b7 << 12));
                    if (1114111 < i14) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    if (55296 <= i14 && 57344 > i14) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    if (65536 > i14) {
                        if (i6 == i2) {
                            return i5;
                        }
                        return -1;
                    }
                    i3 = i6 + 1;
                    if (i6 == i2) {
                        return i5;
                    }
                    if ((10 != i14 && 13 != i14 && ((0 <= i14 && 32 > i14) || (127 <= i14 && 160 > i14))) || 65533 == i14) {
                        return -1;
                    }
                    i5 += 65536 > i14 ? 1 : 2;
                    i4 += 4;
                }
                i6 = i3;
            }
        }
        return i5;
    }
}
