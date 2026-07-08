package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    protected static final int DRAG = 1;
    protected static final int NONE = 0;
    protected static final int PINCH_ZOOM = 4;
    protected static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;
    protected static final int X_ZOOM = 2;
    protected static final int Y_ZOOM = 3;
    protected T mChart;
    protected GestureDetector mGestureDetector;
    protected Highlight mLastHighlighted;
    protected ChartGesture mLastGesture = ChartGesture.NONE;
    protected int mTouchMode;

    protected ChartTouchListener(final T t) {
        mChart = t;
        mGestureDetector = new GestureDetector (t.getContext (), this);
    }

    public static float distance(final float f, final float f2, final float f3, final float f4) {
        final float f5 = f - f2;
        final float f6 = f3 - f4;
        return (float) Math.sqrt ((f5 * f5) + (f6 * f6));
    }

    public void startAction(final MotionEvent motionEvent) {
        final OnChartGestureListener onChartGestureListener = mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartGestureStart (motionEvent, mLastGesture);
        }
    }

    public void endAction(final MotionEvent motionEvent) {
        final OnChartGestureListener onChartGestureListener = mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartGestureEnd (motionEvent, mLastGesture);
        }
    }

    public void setLastHighlighted(final Highlight highlight) {
        mLastHighlighted = highlight;
    }

    public int getTouchMode() {
        return mTouchMode;
    }

    public ChartGesture getLastGesture() {
        return mLastGesture;
    }

    public void performHighlight(final Highlight highlight, final MotionEvent motionEvent) {
        if (null == highlight || highlight.equalTo (mLastHighlighted)) {
            mChart.highlightValue (null, true);
            mLastHighlighted = null;
            return;
        }
        mChart.highlightValue (highlight, true);
        mLastHighlighted = highlight;
    }

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }
}
