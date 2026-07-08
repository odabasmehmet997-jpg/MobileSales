package com.google.zxing.common;

import com.google.zxing.ResultPoint;

public class DetectorResult {
    private final BitMatrix bits;
    private final ResultPoint[] points;
    public DetectorResult(final BitMatrix bitMatrix, final ResultPoint[] resultPointArr) {
        bits = bitMatrix;
        points = resultPointArr;
    }
    public final BitMatrix getBits() {
        return bits;
    }
    public final ResultPoint[] getPoints() {
        return points;
    }
}
