package com.github.mikephil.charting.data.filter;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class Approximator {
    private boolean[] keep;
    private float mDeltaRatio;
    private float mScaleRatio;
    private double mTolerance;
    private ApproximatorType mType;

    public Approximator() {
        mType = ApproximatorType.DOUGLAS_PEUCKER;
        mTolerance = 0.0d;
        mScaleRatio = 1.0f;
        mDeltaRatio = 1.0f;
        mType = ApproximatorType.NONE;
    }

    public Approximator(final ApproximatorType approximatorType, final double d) {
        mType = ApproximatorType.DOUGLAS_PEUCKER;
        mTolerance = 0.0d;
        mScaleRatio = 1.0f;
        mDeltaRatio = 1.0f;
        this.setup(approximatorType, d);
    }

    public void setup(final ApproximatorType approximatorType, final double d) {
        mType = approximatorType;
        mTolerance = d;
    }

    public void setTolerance(final double d) {
        mTolerance = d;
    }

    public void setType(final ApproximatorType approximatorType) {
        mType = approximatorType;
    }

    public void setRatios(final float f, final float f2) {
        mDeltaRatio = f;
        mScaleRatio = f2;
    }

    public List<Entry> filter(final List<Entry> list) {
        return this.filter(list, mTolerance);
    }

    public List<Entry> filter(final List<Entry> list, final double d) {
        if (0.0d >= d) {
            return list;
        }
        keep = new boolean[list.size ()];
        if (1 != C12341.f817xc71afc41[this.mType.ordinal ()]) {
            return list;
        }
        return this.reduceWithDouglasPeuker(list, d);
    }

    private List<Entry> reduceWithDouglasPeuker(final List<Entry> list, final double d) {
        if (0.0d >= d || 3 > list.size ()) {
            return list;
        }
        final boolean[] zArr = keep;
        zArr[0] = true;
        zArr[list.size () - 1] = true;
        this.algorithmDouglasPeucker(list, d, 0, list.size () - 1);
        final ArrayList arrayList = new ArrayList ();
        for (int i = 0; i < list.size (); i++) {
            if (keep[i]) {
                final Entry entry = list.get (i);
                arrayList.add (new Entry (entry.getVal (), entry.getXIndex ()));
            }
        }
        return arrayList;
    }

    private void algorithmDouglasPeucker(final List<Entry> list, final double d, final int i, final int i2) {
        int i3 = i + 1;
        if (i2 > i3) {
            final Entry entry = list.get (i);
            final Entry entry2 = list.get (i2);
            int i4 = 0;
            double d2 = 0.0d;
            while (i3 < i2) {
                final double calcAngleBetweenLines = this.calcAngleBetweenLines(entry, entry2, entry, list.get (i3));
                if (calcAngleBetweenLines > d2) {
                    i4 = i3;
                    d2 = calcAngleBetweenLines;
                }
                i3++;
            }
            if (d2 > d) {
                keep[i4] = true;
                this.algorithmDouglasPeucker(list, d, i, i4);
                this.algorithmDouglasPeucker(list, d, i4, i2);
            }
        }
    }

    public double calcPointToLineDistance(final Entry entry, final Entry entry2, final Entry entry3) {
        final float xIndex = ((float) entry2.getXIndex ()) - entry.getXIndex ();
        final float xIndex2 = ((float) entry3.getXIndex ()) - entry.getXIndex ();
        return Math.abs ((xIndex2 * (entry2.getVal () - entry.getVal ())) - ((entry3.getVal () - entry.getVal ()) * xIndex)) / Math.sqrt ((xIndex * xIndex) + ((entry2.getVal () - entry.getVal ()) * (entry2.getVal () - entry.getVal ())));
    }

    public double calcAngleBetweenLines(final Entry entry, final Entry entry2, final Entry entry3, final Entry entry4) {
        return Math.abs (this.calcAngleWithRatios(entry, entry2) - this.calcAngleWithRatios(entry3, entry4));
    }

    public double calcAngleWithRatios(final Entry entry, final Entry entry2) {
        return (Math.atan2 ((entry2.getVal () * mScaleRatio) - (entry.getVal () * mScaleRatio), (entry2.getXIndex () * mDeltaRatio) - (entry.getXIndex () * mDeltaRatio)) * 180.0d) / 3.141592653589793d;
    }

    public double calcAngle(final Entry entry, final Entry entry2) {
        return (Math.atan2 (entry2.getVal () - entry.getVal (), (float) (entry2.getXIndex () - entry.getXIndex ())) * 180.0d) / 3.141592653589793d;
    }

    public enum ApproximatorType {
        NONE,
        DOUGLAS_PEUCKER
    }

    public enum C12341 {
        ;

        /* renamed from: SwitchMapcomgithubmikephilchartingdatafilterApproximatorApproximatorType */
        static final int[] f817xc71afc41;

        static {
            final int[] iArr = new int[ApproximatorType.values ().length];
            f817xc71afc41 = iArr;
            try {
                iArr[ApproximatorType.DOUGLAS_PEUCKER.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12341.f817xc71afc41[ApproximatorType.NONE.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
        }
    }
}
