package com.github.mikephil.charting.highlight;

public final class Range {
    public float from;
    public float f818to;

    public Range(final float f, final float f2) {
        from = f;
        f818to = f2;
    }

    public boolean contains(final float f) {
        return f > from && f <= f818to;
    }

    public boolean isLarger(final float f) {
        return f > f818to;
    }

    public boolean isSmaller(final float f) {
        return f < from;
    }
}
