package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern {
    private final ResultPoint[] resultPoints;
    private final int[] startEnd;
    private final int value;

    public FinderPattern(final int i2, final int[] iArr, final int i3, final int i4, final int i5) {
        value = i2;
        startEnd = iArr;
        final float f2 = i3;
        final float f3 = i5;
        resultPoints = new ResultPoint[]{new ResultPoint(f2, f3), new ResultPoint(i4, f3)};
    }

    public int getValue() {
        return value;
    }

    public int[] getStartEnd() {
        return startEnd;
    }

    public ResultPoint[] getResultPoints() {
        return resultPoints;
    }

    public boolean equals(final Object obj) {
        return (obj instanceof FinderPattern) && value == ((FinderPattern) obj).value;
    }

    public int hashCode() {
        return value;
    }
}
