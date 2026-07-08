package com.google.zxing.pdf417.decoder.ec;

public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(929, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(final int i2, final int i3) {
        modulus = i2;
        expTable = new int[i2];
        logTable = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            expTable[i5] = i4;
            i4 = (i4 * i3) % i2;
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            logTable[expTable[i6]] = i6;
        }
        zero = new ModulusPoly(this, new int[]{0});
        one = new ModulusPoly(this, new int[]{1});
    }

    
    public ModulusPoly getZero() {
        return zero;
    }

    
    public ModulusPoly getOne() {
        return one;
    }

    
    public ModulusPoly buildMonomial(final int i2, final int i3) {
        if (0 > i2) {
            throw new IllegalArgumentException();
        } else if (0 == i3) {
            return zero;
        } else {
            final int[] iArr = new int[(i2 + 1)];
            iArr[0] = i3;
            return new ModulusPoly(this, iArr);
        }
    }

    
    public int add(final int i2, final int i3) {
        return (i2 + i3) % modulus;
    }

    
    public int subtract(final int i2, final int i3) {
        final int i4 = modulus;
        return ((i2 + i4) - i3) % i4;
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
            return expTable[(modulus - logTable[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    
    public int multiply(final int i2, final int i3) {
        if (0 == i2 || 0 == i3) {
            return 0;
        }
        final int[] iArr = expTable;
        final int[] iArr2 = logTable;
        return iArr[(iArr2[i2] + iArr2[i3]) % (modulus - 1)];
    }

    
    public int getSize() {
        return modulus;
    }
}
