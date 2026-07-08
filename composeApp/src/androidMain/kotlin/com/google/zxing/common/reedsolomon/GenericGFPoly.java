package com.google.zxing.common.reedsolomon;

final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    GenericGFPoly(final GenericGF genericGF, final int[] iArr) {
        if (0 != iArr.length) {
            field = genericGF;
            final int length = iArr.length;
            int i2 = 1;
            if (1 >= length || 0 != iArr[0]) {
                coefficients = iArr;
                return;
            }
            while (i2 < length && 0 == iArr[i2]) {
                i2++;
            }
            if (i2 == length) {
                coefficients = new int[]{0};
                return;
            }
            final int[] iArr2 = new int[(length - i2)];
            coefficients = iArr2;
            System.arraycopy(iArr, i2, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    
    public int[] getCoefficients() {
        return coefficients;
    }

    
    public int getDegree() {
        return coefficients.length - 1;
    }

    
    public boolean isZero() {
        return 0 == this.coefficients[0];
    }

    
    public int getCoefficient(final int i2) {
        final int[] iArr = coefficients;
        return iArr[(iArr.length - 1) - i2];
    }

    
    public int evaluateAt(final int i2) {
        if (0 == i2) {
            return this.getCoefficient(0);
        }
        if (1 == i2) {
            int i3 = 0;
            for (final int addOrSubtract : coefficients) {
                i3 = GenericGF.addOrSubtract(i3, addOrSubtract);
            }
            return i3;
        }
        final int[] iArr = coefficients;
        int i4 = iArr[0];
        final int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            i4 = GenericGF.addOrSubtract(field.multiply(i2, i4), coefficients[i5]);
        }
        return i4;
    }

    
    public GenericGFPoly addOrSubtract(final GenericGFPoly genericGFPoly) {
        if (!field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (this.isZero()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.isZero()) {
                return this;
            }
            int[] iArr = coefficients;
            int[] iArr2 = genericGFPoly.coefficients;
            if (iArr.length <= iArr2.length) {
                final int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            final int[] iArr4 = new int[iArr.length];
            final int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr4, 0, length);
            for (int i2 = length; i2 < iArr.length; i2++) {
                iArr4[i2] = GenericGF.addOrSubtract(iArr2[i2 - length], iArr[i2]);
            }
            return new GenericGFPoly(field, iArr4);
        }
    }

    
    public GenericGFPoly multiply(final GenericGFPoly genericGFPoly) {
        if (!field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (this.isZero() || genericGFPoly.isZero()) {
            return field.getZero();
        } else {
            final int[] iArr = coefficients;
            final int length = iArr.length;
            final int[] iArr2 = genericGFPoly.coefficients;
            final int length2 = iArr2.length;
            final int[] iArr3 = new int[((length + length2) - 1)];
            for (int i2 = 0; i2 < length; i2++) {
                final int i3 = iArr[i2];
                for (int i4 = 0; i4 < length2; i4++) {
                    final int i5 = i2 + i4;
                    iArr3[i5] = GenericGF.addOrSubtract(iArr3[i5], field.multiply(i3, iArr2[i4]));
                }
            }
            return new GenericGFPoly(field, iArr3);
        }
    }

    
    public GenericGFPoly multiply(final int i2) {
        if (0 == i2) {
            return field.getZero();
        }
        if (1 == i2) {
            return this;
        }
        final int length = coefficients.length;
        final int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = field.multiply(coefficients[i3], i2);
        }
        return new GenericGFPoly(field, iArr);
    }

    
    public GenericGFPoly multiplyByMonomial(final int i2, final int i3) {
        if (0 > i2) {
            throw new IllegalArgumentException();
        } else if (0 == i3) {
            return field.getZero();
        } else {
            final int length = coefficients.length;
            final int[] iArr = new int[(i2 + length)];
            for (int i4 = 0; i4 < length; i4++) {
                iArr[i4] = field.multiply(coefficients[i4], i3);
            }
            return new GenericGFPoly(field, iArr);
        }
    }

    
    public GenericGFPoly[] divide(final GenericGFPoly genericGFPoly) {
        if (!field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!genericGFPoly.isZero()) {
            GenericGFPoly zero = field.getZero();
            final int inverse = field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
            GenericGFPoly genericGFPoly2 = this;
            while (genericGFPoly2.getDegree() >= genericGFPoly.getDegree() && !genericGFPoly2.isZero()) {
                final int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
                final int multiply = field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
                final GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
                zero = zero.addOrSubtract(field.buildMonomial(degree, multiply));
                genericGFPoly2 = genericGFPoly2.addOrSubtract(multiplyByMonomial);
            }
            return new GenericGFPoly[]{zero, genericGFPoly2};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getDegree() * 8);
        for (int degree = this.getDegree(); 0 <= degree; degree--) {
            int coefficient = this.getCoefficient(degree);
            if (0 != coefficient) {
                if (0 > coefficient) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else if (0 < sb.length()) {
                    sb.append(" + ");
                }
                if (0 == degree || 1 != coefficient) {
                    final int log = field.log(coefficient);
                    if (0 == log) {
                        sb.append('1');
                    } else if (1 == log) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(log);
                    }
                }
                if (0 != degree) {
                    if (1 == degree) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(degree);
                    }
                }
            }
        }
        return sb.toString();
    }
}
