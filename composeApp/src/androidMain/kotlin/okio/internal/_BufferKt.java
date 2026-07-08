package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.*;

import java.io.EOFException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public class _BufferKt {
    private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");
    public static byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }
    public static boolean rangeEquals(Segment segment, int i2, byte[] bytes, int i3, int i4) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int i5 = segment.limit;
        byte[] bArr = segment.data;
        while (i3 < i4) {
            if (i2 == i5) {
                segment = segment.next;
                checkNotNull(segment);
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i2 = segment.pos;
                i5 = segment.limit;
            }
            if (bArr[i2] != bytes[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }
    public static String readUtf8Line(Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (0 < j2) {
            long j3 = j2 - 1;
            if (((byte) 13) == buffer.getByte(j3)) {
                String readUtf8 = null;
                try {
                    readUtf8 = buffer.readUtf8(j3);
                } catch (EOFException e) {
                    throw new RuntimeException(e);
                }
                try {
                    buffer.skip(2L);
                } catch (EOFException e) {
                    throw new RuntimeException(e);
                }
                return readUtf8;
            }
        }
        String readUtf82 = null;
        try {
            readUtf82 = buffer.readUtf8(j2);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
        try {
            buffer.skip(1L);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
        return readUtf82;
    }
    public static int selectPrefixdefault(Buffer buffer, Options options, boolean z, int i2, Object obj) {
        if (0 != (i2 & 2)) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }
    public static int selectPrefix(Buffer buffer, Options options, boolean z) {
        int i2;
        int i3;
        Segment segment;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Segment segment2 = buffer.head;
        if (null == segment2) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment2.data;
        int i6 = segment2.pos;
        int i7 = segment2.limit;
        int[] trieokio = options.getTrieokio();
        Segment segment3 = segment2;
        int i8 = -1;
        int i9 = 0;
        loop0: while (true) {
            int i10 = i9 + 1;
            int i11 = trieokio[i9];
            int i12 = i9 + 2;
            int i13 = trieokio[i10];
            if (-1 != i13) {
                i8 = i13;
            }
            if (null == segment3) {
                break;
            }
            if (0 <= i11) {
                i2 = i6 + 1;
                int i14 = bArr[i6] & 255;
                int i15 = i12 + i11;
                while (i12 != i15) {
                    if (i14 == trieokio[i12]) {
                        i3 = trieokio[i12 + i11];
                        if (i2 == i7) {
                            segment3 = segment3.next;
                            checkNotNull(segment3);
                            i2 = segment3.pos;
                            bArr = segment3.data;
                            i7 = segment3.limit;
                            if (segment3 == segment2) {
                                segment3 = null;
                            }
                        }
                    } else {
                        i12++;
                    }
                }
                return i8;
            }
            int i16 = i12 + (i11 * (-1));
            while (true) {
                int i17 = i6 + 1;
                int i18 = i12 + 1;
                if ((bArr[i6] & 255) != trieokio[i12]) {
                    return i8;
                }
                boolean z2 = i18 == i16;
                if (i17 == i7) {
                    checkNotNull(segment3);
                    Segment segment4 = segment3.next;
                    checkNotNull(segment4);
                    i5 = segment4.pos;
                    byte[] bArr2 = segment4.data;
                    i4 = segment4.limit;
                    if (segment4 != segment2) {
                        segment = segment4;
                        bArr = bArr2;
                    } else {
                        if (!z2) {
                            break loop0;
                        }
                        bArr = bArr2;
                        segment = null;
                    }
                } else {
                    segment = segment3;
                    i4 = i7;
                    i5 = i17;
                }
                if (z2) {
                    i3 = trieokio[i18];
                    i2 = i5;
                    i7 = i4;
                    segment3 = segment;
                    break;
                }
                i6 = i5;
                i7 = i4;
                segment3 = segment;
                i12 = i18;
            }
            if (0 <= i3) {
                return i3;
            }
            i9 = -i3;
            i6 = i2;
        }
        if (z) {
            return -2;
        }
        return i8;
    }
    public static Buffer.UnsafeCursor commonReadAndWriteUnsafe(Buffer buffer, Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        Buffer.UnsafeCursor resolveDefaultParameter = _UtilKt.resolveDefaultParameter(unsafeCursor);
        if (null != resolveDefaultParameter.buffer) {
            throw new IllegalStateException("already attached to a buffer");
        }
        resolveDefaultParameter.buffer = buffer;
        resolveDefaultParameter.readWrite = true;
        return resolveDefaultParameter;
    }
}

