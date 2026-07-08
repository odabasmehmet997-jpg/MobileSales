package kotlin.jvm.internal;

import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;

public final class ArrayIteratorsKt {
    public static ByteIterator iterator(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        return new ArrayByteIterator(bArr);
    }

    public static CharIterator iterator(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "array");
        return new ArrayCharIterator(cArr);
    }

    public static ShortIterator iterator(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "array");
        return new ArrayShortIterator(sArr);
    }

    public static IntIterator iterator(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "array");
        return new ArrayIntIterator(iArr);
    }

    public static LongIterator iterator(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "array");
        return new ArrayLongIterator(jArr);
    }

    public static FloatIterator iterator(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "array");
        return new ArrayFloatIterator(fArr);
    }

    public static DoubleIterator iterator(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "array");
        return new ArrayDoubleIterator(dArr);
    }

    public static ArrayIterators iterator(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "array");
        return new ArrayIterators(zArr);
    }
}
