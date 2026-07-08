package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(final GenericGF genericGF) {
        field = genericGF;
    }

    public void decode(final int[] iArr, final int i2) throws ReedSolomonException {
        final GenericGFPoly genericGFPoly = new GenericGFPoly(field, iArr);
        final int[] iArr2 = new int[i2];
        int i3 = 0;
        boolean z = true;
        for (int i4 = 0; i4 < i2; i4++) {
            final GenericGF genericGF = field;
            final int evaluateAt = genericGFPoly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + i4));
            iArr2[(i2 - 1) - i4] = evaluateAt;
            if (0 != evaluateAt) {
                z = false;
            }
        }
        if (!z) {
            final GenericGFPoly[] runEuclideanAlgorithm = this.runEuclideanAlgorithm(field.buildMonomial(i2, 1), new GenericGFPoly(field, iArr2), i2);
            final GenericGFPoly genericGFPoly2 = runEuclideanAlgorithm[0];
            final GenericGFPoly genericGFPoly3 = runEuclideanAlgorithm[1];
            final int[] findErrorLocations = this.findErrorLocations(genericGFPoly2);
            final int[] findErrorMagnitudes = this.findErrorMagnitudes(genericGFPoly3, findErrorLocations);
            while (i3 < findErrorLocations.length) {
                final int length = (iArr.length - 1) - field.log(findErrorLocations[i3]);
                if (0 <= length) {
                    iArr[length] = GenericGF.addOrSubtract(iArr[length], findErrorMagnitudes[i3]);
                    i3++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, final int i2) throws ReedSolomonException {
        if (genericGFPoly.getDegree() < genericGFPoly2.getDegree()) {
            final GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
        }
        GenericGFPoly zero = field.getZero();
        GenericGFPoly one = field.getOne();
        do {
            final GenericGFPoly genericGFPoly4 = r11;
            r11 = genericGFPoly;
            genericGFPoly = genericGFPoly4;
            final GenericGFPoly genericGFPoly5 = one;
            final GenericGFPoly genericGFPoly6 = zero;
            zero = genericGFPoly5;
            if (genericGFPoly.getDegree() < i2 / 2) {
                final int coefficient = zero.getCoefficient(0);
                if (0 != coefficient) {
                    final int inverse = field.inverse(coefficient);
                    return new GenericGFPoly[]{zero.multiply(inverse), genericGFPoly.multiply(inverse)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else if (!genericGFPoly.isZero()) {
                GenericGFPoly zero2 = field.getZero();
                final int inverse2 = field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
                while (r11.getDegree() >= genericGFPoly.getDegree() && !r11.isZero()) {
                    final int degree = r11.getDegree() - genericGFPoly.getDegree();
                    final int multiply = field.multiply(r11.getCoefficient(r11.getDegree()), inverse2);
                    zero2 = zero2.addOrSubtract(field.buildMonomial(degree, multiply));
                    r11 = r11.addOrSubtract(genericGFPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).addOrSubtract(genericGFPoly6);
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        } while (r11.getDegree() < genericGFPoly.getDegree());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    private int[] findErrorLocations(final GenericGFPoly genericGFPoly) throws ReedSolomonException {
        final int degree = genericGFPoly.getDegree();
        if (1 == degree) {
            return new int[]{genericGFPoly.getCoefficient(1)};
        }
        final int[] iArr = new int[degree];
        int i2 = 0;
        for (int i3 = 1; i3 < field.getSize() && i2 < degree; i3++) {
            if (0 == genericGFPoly.evaluateAt(i3)) {
                iArr[i2] = field.inverse(i3);
                i2++;
            }
        }
        if (i2 == degree) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(final GenericGFPoly genericGFPoly, final int[] iArr) {
        final int length = iArr.length;
        final int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            final int inverse = field.inverse(iArr[i2]);
            int i3 = 1;
            for (int i4 = 0; i4 < length; i4++) {
                if (i2 != i4) {
                    final int multiply = field.multiply(iArr[i4], inverse);
                    i3 = field.multiply(i3, 0 == (multiply & 1) ? multiply | 1 : multiply & -2);
                }
            }
            iArr2[i2] = field.multiply(genericGFPoly.evaluateAt(inverse), field.inverse(i3));
            if (0 != this.field.getGeneratorBase()) {
                iArr2[i2] = field.multiply(iArr2[i2], inverse);
            }
        }
        return iArr2;
    }
}
