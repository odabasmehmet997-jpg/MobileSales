package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;
    FinderPattern(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }
    private FinderPattern(float f2, float f3, float f4, int r4) {
        super(f2, f3);
        this.estimatedModuleSize = f4;
        this.count = r4;
    }
    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }
    int getCount() {
        return this.count;
    }
    boolean aboutEquals(float f2, float f3, float f4) {
        if (Math.abs(f3 - getY()) > f2 || Math.abs(f4 - getX()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs <= this.estimatedModuleSize;
    }
    FinderPattern combineEstimate(float f2, float f3, float f4) {
        int r0 = this.count;
        int r1 = r0 + 1;
        float x = (r0 * getX()) + f3;
        float f5 = r1;
        return new FinderPattern(x / f5, ((this.count * getY()) + f2) / f5, ((this.count * this.estimatedModuleSize) + f4) / f5, r1);
    }
}
