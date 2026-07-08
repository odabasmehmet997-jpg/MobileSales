package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

public class ResultPoint {
    private final float x;
    private final float y;
    public ResultPoint(final float f2, final float f3) {
        x = f2;
        y = f3;
    }
    public final float getX() {
        return x;
    }
    public final float getY() {
        return y;
    }
    public final boolean equals(final Object obj) {
        if (obj instanceof ResultPoint resultPoint) {
            return x == resultPoint.x && y == resultPoint.y;
        }
        return false;
    }
    public final int hashCode() {
        return (Float.floatToIntBits(x) * 31) + Float.floatToIntBits(y);
    }
    public String toString() {
        return "(" + x + ',' + y + ')';
    }
    public static void orderBestPatterns(final ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        final ResultPoint resultPoint3;
        final float distance = ResultPoint.distance(resultPointArr[0], resultPointArr[1]);
        final float distance2 = ResultPoint.distance(resultPointArr[1], resultPointArr[2]);
        final float distance3 = ResultPoint.distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint3 = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint3 = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[1];
        } else {
            resultPoint3 = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint = resultPointArr[2];
        }
        if (0.0f > crossProductZ(resultPoint2, resultPoint3, resultPoint)) {
            final ResultPoint resultPoint4 = resultPoint;
            resultPoint = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint3;
        resultPointArr[2] = resultPoint;
    }
    public static float distance(final ResultPoint resultPoint, final ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.x, resultPoint.y, resultPoint2.x, resultPoint2.y);
    }
    private static float crossProductZ(final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3) {
        final float f2 = resultPoint2.x;
        final float f3 = resultPoint2.y;
        return ((resultPoint3.x - f2) * (resultPoint.y - f3)) - ((resultPoint3.y - f3) * (resultPoint.x - f2));
    }
}
