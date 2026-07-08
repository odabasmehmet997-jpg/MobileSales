package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashSet;

public final class ArrayBuilders {
    private BooleanBuilder _booleanBuilder;
    private ByteBuilder _byteBuilder;
    private ShortBuilder _shortBuilder;
    private IntBuilder _intBuilder;
    private LongBuilder _longBuilder;
    private FloatBuilder _floatBuilder;
    private DoubleBuilder _doubleBuilder;

    public BooleanBuilder getBooleanBuilder() {
        if (null == this._booleanBuilder) {
            _booleanBuilder = new BooleanBuilder();
        }
        return _booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (null == this._byteBuilder) {
            _byteBuilder = new ByteBuilder();
        }
        return _byteBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (null == this._shortBuilder) {
            _shortBuilder = new ShortBuilder();
        }
        return _shortBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (null == this._intBuilder) {
            _intBuilder = new IntBuilder();
        }
        return _intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (null == this._longBuilder) {
            _longBuilder = new LongBuilder();
        }
        return _longBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (null == this._floatBuilder) {
            _floatBuilder = new FloatBuilder();
        }
        return _floatBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (null == this._doubleBuilder) {
            _doubleBuilder = new DoubleBuilder();
        }
        return _doubleBuilder;
    }

    public static final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public boolean[] _constructArray(final int i2) {
            return new boolean[i2];
        }
    }

    public static final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public byte[] _constructArray(final int i2) {
            return new byte[i2];
        }
    }

    public static final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public short[] _constructArray(final int i2) {
            return new short[i2];
        }
    }

    public static final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public int[] _constructArray(final int i2) {
            return new int[i2];
        }
    }

    public static final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public long[] _constructArray(final int i2) {
            return new long[i2];
        }
    }

    public static final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public float[] _constructArray(final int i2) {
            return new float[i2];
        }
    }

    public static final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public double[] _constructArray(final int i2) {
            return new double[i2];
        }
    }

    public static Object getArrayComparator(Object obj) {
        int length = Array.getLength(obj);
        Class<?> cls = obj.getClass();
        return new Object() { // from class: com.fasterxml.jackson.databind.util.ArrayBuilders.1
            public boolean equals(final Object obj2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
                if (obj2 == this) {
                    return true;
                }
                if (!ClassUtil.hasClass(obj2, cls) || Array.getLength(obj2) != length) {
                    return false;
                }
                for (int i2 = 0; i2 < length; i2++) {
                    final Object obj3 = Array.get(obj, i2);
                    final Object obj4 = Array.get(obj2, i2);
                    if (obj3 != obj4 && null != obj3 && !obj3.equals(obj4)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static <T> HashSet<T> arrayToSet(final T[] tArr) {
        if (null != tArr) {
            final HashSet<T> hashSet = new HashSet<>(tArr.length);
            Collections.addAll(hashSet, tArr);
            return hashSet;
        }
        return new HashSet<>();
    }

    public static <T> T[] insertInListNoDup(final T[] tArr, final T t) {
        final int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (tArr[i2] == t) {
                if (0 == i2) {
                    return tArr;
                }
                final T[] tArr2 = (T[]) Array.newInstance(tArr.getClass().getComponentType(), length);
                System.arraycopy(tArr, 0, tArr2, 1, i2);
                tArr2[0] = t;
                final int i3 = i2 + 1;
                final int i4 = length - i3;
                if (0 < i4) {
                    System.arraycopy(tArr, i3, tArr2, i3, i4);
                }
                return tArr2;
            }
        }
        final T[] tArr3 = (T[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1);
        if (0 < length) {
            System.arraycopy(tArr, 0, tArr3, 1, length);
        }
        tArr3[0] = t;
        return tArr3;
    }
}
