package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.internal._ByteStringKt;

public final class _UtilKt {
    private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();
    private static final int DEFAULT__ByteString_size = -1234567890;
    public static int reverseBytes(int i2) {
        return ((i2 & 255) << 24) | (((-16777216) & i2) >>> 24) | ((16711680 & i2) >>> 8) | ((65280 & i2) << 8);
    }
    public static long reverseBytes(long j2) {
        return ((j2 & 255) << 56) | (((-72057594037927936L) & j2) >>> 56) | ((71776119061217280L & j2) >>> 40) | ((280375465082880L & j2) >>> 24) | ((1095216660480L & j2) >>> 8) | ((4278190080L & j2) << 8) | ((16711680 & j2) << 24) | ((65280 & j2) << 40);
    }
    public static short reverseBytes(short s) {
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }
    public static void checkOffsetAndCount(long j2, long j3, long j4) {
        if (0 > (j3 | j4) || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException("size=" + j2 + " offset=" + j3 + " byteCount=" + j4);
        }
    }
    public static boolean arrayRangeEquals(byte[] a2, int i2, byte[] b2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        for (int i5 = 0; i5 < i4; i5++) {
            if (a2[i5 + i2] != b2[i5 + i3]) {
                return false;
            }
        }
        return true;
    }
    public static String toHexString(byte b2) {
        return StringsKt.concatToString(new char[]{_ByteStringKt.getHEX_DIGIT_CHARS()[(b2 >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[b2 & 15]});
    }
    public static String toHexString(int i2) {
        int i3 = 0;
        if (0 == i2) {
            return "0";
        }
        char[] cArr = {_ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 28) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 24) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 20) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 16) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 12) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 8) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i2 >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[i2 & 15]};
        while (8 > i3 && '0' == cArr[i3]) {
            i3++;
        }
        return StringsKt.concatToString(cArr, i3, 8);
    }
    public static Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return DEFAULT__new_UnsafeCursor;
    }
    public static Buffer.UnsafeCursor resolveDefaultParameter(Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return unsafeCursor == DEFAULT__new_UnsafeCursor ? new Buffer.UnsafeCursor() : unsafeCursor;
    }
    public static int getDEFAULT__ByteString_size() {
        return DEFAULT__ByteString_size;
    }
    public static int resolveDefaultParameter(ByteString byteString, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return DEFAULT__ByteString_size == i2 ? byteString.size() : i2;
    }
    public static int resolveDefaultParameter(byte[] bArr, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return DEFAULT__ByteString_size == i2 ? bArr.length : i2;
    }
}
