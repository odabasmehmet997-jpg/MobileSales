package com.github.mikephil.charting.buffer;


public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    protected float phaseX = 1.0f;
    protected float phaseY = 1.0f;
    protected int mFrom;
    protected int mTo;
    protected int index;
    protected AbstractBuffer(final int i) {
        buffer = new float[i];
    }
    public abstract void feed(T t);
    public void limitFrom(int i) {
        if (0 > i) {
            i = 0;
        }
        mFrom = i;
    }
    public void limitTo(int i) {
        if (0 > i) {
            i = 0;
        }
        mTo = i;
    }
    public void reset() {
        index = 0;
    }
    public int size() {
        return buffer.length;
    }
    public void setPhases(final float f, final float f2) {
        phaseX = f;
        phaseY = f2;
    }
}
