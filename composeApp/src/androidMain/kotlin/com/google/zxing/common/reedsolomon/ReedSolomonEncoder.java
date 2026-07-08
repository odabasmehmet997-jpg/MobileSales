package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(final GenericGF genericGF) {
        field = genericGF;
        final ArrayList arrayList = new ArrayList();
        cachedGenerators = arrayList;
        arrayList.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(final int i2) {
        if (i2 >= cachedGenerators.size()) {
            final List<GenericGFPoly> list = cachedGenerators;
            GenericGFPoly genericGFPoly = list.get(list.size() - 1);
            for (int size = cachedGenerators.size(); size <= i2; size++) {
                final GenericGF genericGF = field;
                genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp((size - 1) + genericGF.getGeneratorBase())}));
                cachedGenerators.add(genericGFPoly);
            }
        }
        return cachedGenerators.get(i2);
    }

    public void encode(final int[] iArr, final int i2) {
        if (0 != i2) {
            final int length = iArr.length - i2;
            if (0 < length) {
                final GenericGFPoly buildGenerator = this.buildGenerator(i2);
                final int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                final int[] coefficients = new GenericGFPoly(field, iArr2).multiplyByMonomial(i2, 1).divide(buildGenerator)[1].getCoefficients();
                final int length2 = i2 - coefficients.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr[length + i3] = 0;
                }
                System.arraycopy(coefficients, 0, iArr, length + length2, coefficients.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
