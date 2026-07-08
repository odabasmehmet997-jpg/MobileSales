package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

public abstract class Binarizer {
    private final LuminanceSource source;
    public abstract Binarizer createBinarizer(com.google.zxing.LuminanceSource luminanceSource);
    public abstract BitMatrix getBlackMatrix() throws NotFoundException;
    public abstract BitArray getBlackRow(int i2, BitArray bitArray) throws NotFoundException;
    protected Binarizer(final com.google.zxing.LuminanceSource luminanceSource) {
        source = luminanceSource;
    }
    public final com.google.zxing.LuminanceSource getLuminanceSource() {
        return source;
    }
    public final int getWidth() {
        return source.getWidth();
    }
    public final int getHeight() {
        return source.getHeight();
    }
}
