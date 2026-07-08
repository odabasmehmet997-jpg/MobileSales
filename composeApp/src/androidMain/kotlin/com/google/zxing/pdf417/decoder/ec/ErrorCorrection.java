package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    public int decode(final int[] iArr, final int i2, final int[] iArr2) throws ChecksumException {
        final ModulusPoly modulusPoly = new ModulusPoly(field, iArr);
        final int[] iArr3 = new int[i2];
        int i3 = 0;
        boolean z = false;
        for (int i4 = i2; 0 < i4; i4--) {
            final int evaluateAt = modulusPoly.evaluateAt(field.exp(i4));
            iArr3[i2 - i4] = evaluateAt;
            if (0 != evaluateAt) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        ModulusPoly one = field.getOne();
        if (null != iArr2) {
            for (final int length : iArr2) {
                final int exp = field.exp((iArr.length - 1) - length);
                final ModulusGF modulusGF = field;
                one = one.multiply(new ModulusPoly(modulusGF, new int[]{modulusGF.subtract(0, exp), 1}));
            }
        }
        final ModulusPoly[] runEuclideanAlgorithm = this.runEuclideanAlgorithm(field.buildMonomial(i2, 1), new ModulusPoly(field, iArr3), i2);
        final ModulusPoly modulusPoly2 = runEuclideanAlgorithm[0];
        final ModulusPoly modulusPoly3 = runEuclideanAlgorithm[1];
        final int[] findErrorLocations = this.findErrorLocations(modulusPoly2);
        final int[] findErrorMagnitudes = this.findErrorMagnitudes(modulusPoly3, modulusPoly2, findErrorLocations);
        while (i3 < findErrorLocations.length) {
            final int length2 = (iArr.length - 1) - field.log(findErrorLocations[i3]);
            if (0 <= length2) {
                iArr[length2] = field.subtract(iArr[length2], findErrorMagnitudes[i3]);
                i3++;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return findErrorLocations.length;
    }

    private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, final int i2) throws ChecksumException {
        if (modulusPoly.getDegree() < modulusPoly2.getDegree()) {
            final ModulusPoly modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
        }
        ModulusPoly zero = field.getZero();
        ModulusPoly one = field.getOne();
        while (true) {
            final ModulusPoly modulusPoly4 = r11;
            r11 = modulusPoly;
            modulusPoly = modulusPoly4;
            final ModulusPoly modulusPoly5 = one;
            final ModulusPoly modulusPoly6 = zero;
            zero = modulusPoly5;
            if (modulusPoly.getDegree() < i2 / 2) {
                final int coefficient = zero.getCoefficient(0);
                if (0 != coefficient) {
                    final int inverse = field.inverse(coefficient);
                    return new ModulusPoly[]{zero.multiply(inverse), modulusPoly.multiply(inverse)};
                }
                throw ChecksumException.getChecksumInstance();
            } else if (!modulusPoly.isZero()) {
                ModulusPoly zero2 = field.getZero();
                final int inverse2 = field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
                while (r11.getDegree() >= modulusPoly.getDegree() && !r11.isZero()) {
                    final int degree = r11.getDegree() - modulusPoly.getDegree();
                    final int multiply = field.multiply(r11.getCoefficient(r11.getDegree()), inverse2);
                    zero2 = zero2.add(field.buildMonomial(degree, multiply));
                    r11 = r11.subtract(modulusPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).subtract(modulusPoly6).negative();
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private int[] findErrorLocations(final ModulusPoly modulusPoly) throws ChecksumException {
        final int degree = modulusPoly.getDegree();
        final int[] iArr = new int[degree];
        int i2 = 0;
        for (int i3 = 1; i3 < field.getSize() && i2 < degree; i3++) {
            if (0 == modulusPoly.evaluateAt(i3)) {
                iArr[i2] = field.inverse(i3);
                i2++;
            }
        }
        if (i2 == degree) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] findErrorMagnitudes(final ModulusPoly modulusPoly, final ModulusPoly modulusPoly2, final int[] iArr) {
        final int degree = modulusPoly2.getDegree();
        final int[] iArr2 = new int[degree];
        for (int i2 = 1; i2 <= degree; i2++) {
            iArr2[degree - i2] = field.multiply(i2, modulusPoly2.getCoefficient(i2));
        }
        final ModulusPoly modulusPoly3 = new ModulusPoly(field, iArr2);
        final int length = iArr.length;
        final int[] iArr3 = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            final int inverse = field.inverse(iArr[i3]);
            iArr3[i3] = field.multiply(field.subtract(0, modulusPoly.evaluateAt(inverse)), field.inverse(modulusPoly3.evaluateAt(inverse)));
        }
        return iArr3;
    }
}
