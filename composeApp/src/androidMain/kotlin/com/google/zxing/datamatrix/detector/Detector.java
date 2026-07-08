package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;

import java.io.Serializable;
import java.util.*;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    public Detector(final BitMatrix bitMatrix) throws NotFoundException {
        image = bitMatrix;
        rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    public DetectorResult detect() throws NotFoundException {
        ResultPoint resultPoint;
        final BitMatrix bitMatrix;
        final ResultPoint resultPoint2;
        final ResultPoint[] detect = rectangleDetector.detect();
        final ResultPoint resultPoint3 = detect[0];
        final ResultPoint resultPoint4 = detect[1];
        final ResultPoint resultPoint5 = detect[2];
        final ResultPoint resultPoint6 = detect[3];
        final ArrayList arrayList = new ArrayList(4);
        arrayList.add(this.transitionsBetween(resultPoint3, resultPoint4));
        arrayList.add(this.transitionsBetween(resultPoint3, resultPoint5));
        arrayList.add(this.transitionsBetween(resultPoint4, resultPoint6));
        arrayList.add(this.transitionsBetween(resultPoint5, resultPoint6));
        ResultPoint resultPoint7 = null;
        Collections.sort(arrayList, new ResultPointsAndTransitionsComparator());
        final ResultPointsAndTransitions resultPointsAndTransitions = (ResultPointsAndTransitions) arrayList.get(0);
        final ResultPointsAndTransitions resultPointsAndTransitions2 = (ResultPointsAndTransitions) arrayList.get(1);
        final HashMap hashMap = new HashMap();
        Detector.increment(hashMap, resultPointsAndTransitions.from());
        Detector.increment(hashMap, resultPointsAndTransitions.to());
        Detector.increment(hashMap, resultPointsAndTransitions2.from());
        Detector.increment(hashMap, resultPointsAndTransitions2.to());
        ResultPoint resultPoint8 = null;
        ResultPoint resultPoint9 = null;
        for (final Map.Entry entry : hashMap.entrySet()) {
            final ResultPoint resultPoint10 = (ResultPoint) entry.getKey();
            if (2 == ((Integer) entry.getValue()).intValue()) {
                resultPoint8 = resultPoint10;
            } else if (null == resultPoint7) {
                resultPoint7 = resultPoint10;
            } else {
                resultPoint9 = resultPoint10;
            }
        }
        if (null == resultPoint7 || null == resultPoint8 || null == resultPoint9) {
            throw NotFoundException.getNotFoundInstance();
        }
        final ResultPoint[] resultPointArr = {resultPoint7, resultPoint8, resultPoint9};
        ResultPoint.orderBestPatterns(resultPointArr);
        final ResultPoint resultPoint11 = resultPointArr[0];
        final ResultPoint resultPoint12 = resultPointArr[1];
        final ResultPoint resultPoint13 = resultPointArr[2];
        if (!hashMap.containsKey(resultPoint3)) {
            resultPoint = resultPoint3;
        } else if (!hashMap.containsKey(resultPoint4)) {
            resultPoint = resultPoint4;
        } else {
            resultPoint = !hashMap.containsKey(resultPoint5) ? resultPoint5 : resultPoint6;
        }
        int transitions = this.transitionsBetween(resultPoint13, resultPoint).transitions();
        int transitions2 = this.transitionsBetween(resultPoint11, resultPoint).transitions();
        if (1 == (transitions & 1)) {
            transitions++;
        }
        final int i2 = transitions + 2;
        if (1 == (transitions2 & 1)) {
            transitions2++;
        }
        final int i3 = transitions2 + 2;
        if (i2 * 4 >= i3 * 7 || i3 * 4 >= i2 * 7) {
            final ResultPoint resultPoint14 = resultPoint13;
            final ResultPoint resultPoint15 = resultPoint14;
            final ResultPoint correctTopRightRectangular = this.correctTopRightRectangular(resultPoint12, resultPoint11, resultPoint14, resultPoint, i2, i3);
            if (null != correctTopRightRectangular) {
                resultPoint = correctTopRightRectangular;
            }
            int transitions3 = this.transitionsBetween(resultPoint15, resultPoint).transitions();
            int transitions4 = this.transitionsBetween(resultPoint11, resultPoint).transitions();
            if (1 == (transitions3 & 1)) {
                transitions3++;
            }
            final int i4 = transitions3;
            if (1 == (transitions4 & 1)) {
                transitions4++;
            }
            resultPoint2 = resultPoint15;
            bitMatrix = Detector.sampleGrid(image, resultPoint15, resultPoint12, resultPoint11, resultPoint, i4, transitions4);
        } else {
            final ResultPoint correctTopRight = this.correctTopRight(resultPoint12, resultPoint11, resultPoint13, resultPoint, Math.min(i3, i2));
            if (null != correctTopRight) {
                resultPoint = correctTopRight;
            }
            final int max = Math.max(this.transitionsBetween(resultPoint13, resultPoint).transitions(), this.transitionsBetween(resultPoint11, resultPoint).transitions());
            final int i5 = max + 1;
            final int i6 = 1 == (i5 & 1) ? max + 2 : i5;
            bitMatrix = Detector.sampleGrid(image, resultPoint13, resultPoint12, resultPoint11, resultPoint, i6, i6);
            resultPoint2 = resultPoint13;
        }
        return new DetectorResult(bitMatrix, new ResultPoint[]{resultPoint2, resultPoint12, resultPoint11, resultPoint});
    }

    private ResultPoint correctTopRightRectangular(final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4, final int i2, final int i3) {
        final float distance = ((float) Detector.distance(resultPoint, resultPoint2)) / i2;
        final float distance2 = Detector.distance(resultPoint3, resultPoint4);
        final ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        final float distance3 = ((float) Detector.distance(resultPoint, resultPoint3)) / i3;
        final float distance4 = Detector.distance(resultPoint2, resultPoint4);
        final ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / distance4) * distance3), resultPoint4.getY() + (distance3 * ((resultPoint4.getY() - resultPoint2.getY()) / distance4)));
        if (this.isValid(resultPoint5)) {
            return (this.isValid(resultPoint6) && Math.abs(i2 - this.transitionsBetween(resultPoint3, resultPoint5).transitions()) + Math.abs(i3 - this.transitionsBetween(resultPoint2, resultPoint5).transitions()) > Math.abs(i2 - this.transitionsBetween(resultPoint3, resultPoint6).transitions()) + Math.abs(i3 - this.transitionsBetween(resultPoint2, resultPoint6).transitions())) ? resultPoint6 : resultPoint5;
        }
        if (this.isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private ResultPoint correctTopRight(final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4, final int i2) {
        final float f2 = i2;
        final float distance = Detector.distance(resultPoint, resultPoint2) / f2;
        final float distance2 = Detector.distance(resultPoint3, resultPoint4);
        final ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        final float distance3 = Detector.distance(resultPoint, resultPoint3) / f2;
        final float distance4 = Detector.distance(resultPoint2, resultPoint4);
        final ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / distance4) * distance3), resultPoint4.getY() + (distance3 * ((resultPoint4.getY() - resultPoint2.getY()) / distance4)));
        if (this.isValid(resultPoint5)) {
            return (this.isValid(resultPoint6) && Math.abs(this.transitionsBetween(resultPoint3, resultPoint5).transitions() - this.transitionsBetween(resultPoint2, resultPoint5).transitions()) > Math.abs(this.transitionsBetween(resultPoint3, resultPoint6).transitions() - this.transitionsBetween(resultPoint2, resultPoint6).transitions())) ? resultPoint6 : resultPoint5;
        }
        if (this.isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private boolean isValid(final ResultPoint resultPoint) {
        return 0.0f <= resultPoint.getX() && resultPoint.getX() < image.getWidth() && 0.0f < resultPoint.getY() && resultPoint.getY() < image.getHeight();
    }

    private static int distance(final ResultPoint resultPoint, final ResultPoint resultPoint2) {
        return MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2));
    }

    private static void increment(final Map<ResultPoint, Integer> map, final ResultPoint resultPoint) {
        final Integer num = map.get(resultPoint);
        int i2 = 1;
        if (null != num) {
            i2 = 1 + num.intValue();
        }
        map.put(resultPoint, Integer.valueOf(i2));
    }

    private static BitMatrix sampleGrid(final BitMatrix bitMatrix, final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4, final int i2, final int i3) throws NotFoundException {
        final float f2 = i2 - 0.5f;
        final float f3 = i3 - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i2, i3, 0.5f, 0.5f, f2, 0.5f, f2, f3, 0.5f, f3, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(final ResultPoint resultPoint, final ResultPoint resultPoint2) {
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i2 = 0;
        int i3 = 1;
        final boolean z = Math.abs(y2 - y) > Math.abs(x2 - x);
        if (z) {
            final int i4 = y;
            y = x;
            x = i4;
            final int i5 = y2;
            y2 = x2;
            x2 = i5;
        }
        final int abs = Math.abs(x2 - x);
        final int abs2 = Math.abs(y2 - y);
        int i6 = (-abs) / 2;
        final int i7 = y < y2 ? 1 : -1;
        if (x >= x2) {
            i3 = -1;
        }
        boolean z2 = image.get(z ? y : x, z ? x : y);
        while (x != x2) {
            final boolean z3 = image.get(z ? y : x, z ? x : y);
            if (z3 != z2) {
                i2++;
                z2 = z3;
            }
            i6 += abs2;
            if (0 < i6) {
                if (y == y2) {
                    break;
                }
                y += i7;
                i6 -= abs;
            }
            x += i3;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i2);
    }

    private record ResultPointsAndTransitions(ResultPoint from, ResultPoint to, int transitions) {

        public String toString() {
                return from + "/" + to + '/' + transitions;
            }
        }

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<ResultPointsAndTransitions> {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(final ResultPointsAndTransitions resultPointsAndTransitions, final ResultPointsAndTransitions resultPointsAndTransitions2) {
            return resultPointsAndTransitions.transitions() - resultPointsAndTransitions2.transitions();
        }
    }
}
