package com.google.zxing.common.reedsolomon;

public final class GenericGF {
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;

    static int addOrSubtract(final int i2, final int i3) {
        return i2 ^ i3;
    }

    static {
        final GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        final GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(final int i2, final int i3, final int i4) {
        primitive = i2;
        size = i3;
        generatorBase = i4;
        expTable = new int[i3];
        logTable = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            expTable[i6] = i5;
            i5 <<= 1;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            logTable[expTable[i7]] = i7;
        }
        zero = new GenericGFPoly(this, new int[]{0});
        one = new GenericGFPoly(this, new int[]{1});
    }

    
    public GenericGFPoly getZero() {
        return zero;
    }

    
    public GenericGFPoly getOne() {
        return one;
    }

    
    public GenericGFPoly buildMonomial(final int i2, final int i3) {
        if (0 > i2) {
            throw new IllegalArgumentException();
        } else if (0 == i3) {
            return zero;
        } else {
            final int[] iArr = new int[(i2 + 1)];
            iArr[0] = i3;
            return new GenericGFPoly(this, iArr);
        }
    }

    
    public int exp(final int i2) {
        return expTable[i2];
    }

    
    public int log(final int i2) {
        if (0 != i2) {
            return logTable[i2];
        }
        throw new IllegalArgumentException();
    }

    
    public int inverse(final int i2) {
        if (0 != i2) {
            return expTable[(size - logTable[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    
    public int multiply(final int i2, final int i3) {
        if (0 == i2 || 0 == i3) {
            return 0;
        }
        final int[] iArr = expTable;
        final int[] iArr2 = logTable;
        return iArr[(iArr2[i2] + iArr2[i3]) % (size - 1)];
    }

    public int getSize() {
        return size;
    }

    public int getGeneratorBase() {
        return generatorBase;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(primitive) + ',' + size + ')';
    }
}
