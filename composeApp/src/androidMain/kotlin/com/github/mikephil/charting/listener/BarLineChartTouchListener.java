package com.github.mikephil.charting.listener;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private final Matrix mSavedMatrix = new Matrix ();
    private final PointF mTouchStartPoint = new PointF ();
    private final PointF mTouchPointCenter = new PointF ();
    private final float mDragTriggerDist = Utils.convertDpToPixel (3.0f);
    private final float mMinScalePointerDistance = Utils.convertDpToPixel (3.5f);
    private IDataSet mClosestDataSetToTouch;
    private Matrix mMatrix;
    private VelocityTracker mVelocityTracker;
    private float mSavedXDist = 1.0f;
    private float mSavedYDist = 1.0f;
    private float mSavedDist = 1.0f;
    private long mDecelerationLastTime;
    private PointF mDecelerationCurrentPoint = new PointF ();
    private PointF mDecelerationVelocity = new PointF ();

    public BarLineChartTouchListener(final BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, final Matrix matrix) {
        super (barLineChartBase);
        mMatrix = new Matrix ();
        mMatrix = matrix;
    }

    private static void midPoint(final PointF pointF, final MotionEvent motionEvent) {
        pointF.set ((motionEvent.getX (0) + motionEvent.getX (1)) / 2.0f, (motionEvent.getY (0) + motionEvent.getY (1)) / 2.0f);
    }

    private static float spacing(final MotionEvent motionEvent) {
        final float x = motionEvent.getX (0) - motionEvent.getX (1);
        final float y = motionEvent.getY (0) - motionEvent.getY (1);
        return (float) Math.sqrt ((x * x) + (y * y));
    }

    private static float getXDist(final MotionEvent motionEvent) {
        return Math.abs (motionEvent.getX (0) - motionEvent.getX (1));
    }

    private static float getYDist(final MotionEvent motionEvent) {
        return Math.abs (motionEvent.getY (0) - motionEvent.getY (1));
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final VelocityTracker velocityTracker;
        if (null == this.mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain ();
        }
        mVelocityTracker.addMovement (motionEvent);
        if (3 == motionEvent.getActionMasked () && null != (velocityTracker = this.mVelocityTracker)) {
            velocityTracker.recycle ();
            mVelocityTracker = null;
        }
        if (0 == mTouchMode) {
            this.mGestureDetector.onTouchEvent (motionEvent);
        }
        if (!this.mChart.isDragEnabled () && !this.mChart.isScaleXEnabled () && !this.mChart.isScaleYEnabled ()) {
            return true;
        }
        final int action = motionEvent.getAction () & 255;
        if (0 == action) {
            this.startAction(motionEvent);
            this.stopDeceleration();
            this.saveTouchStart(motionEvent);
        } else if (1 == action) {
            final VelocityTracker velocityTracker2 = mVelocityTracker;
            final int pointerId = motionEvent.getPointerId (0);
            velocityTracker2.computeCurrentVelocity (1000, Utils.getMaximumFlingVelocity ());
            final float yVelocity = velocityTracker2.getYVelocity (pointerId);
            final float xVelocity = velocityTracker2.getXVelocity (pointerId);
            if ((Math.abs (xVelocity) > Utils.getMinimumFlingVelocity () || Math.abs (yVelocity) > Utils.getMinimumFlingVelocity ()) && 1 == mTouchMode && this.mChart.isDragDecelerationEnabled ()) {
                this.stopDeceleration();
                mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis ();
                mDecelerationCurrentPoint = new PointF (motionEvent.getX (), motionEvent.getY ());
                mDecelerationVelocity = new PointF (xVelocity, yVelocity);
                Utils.postInvalidateOnAnimation (this.mChart);
            }
            final int i = this.mTouchMode;
            if (2 == i || 3 == i || 4 == i || 5 == i) {
                this.mChart.calculateOffsets ();
                this.mChart.postInvalidate ();
            }
            this.mTouchMode = 0;
            this.mChart.enableScroll ();
            final VelocityTracker velocityTracker3 = mVelocityTracker;
            if (null != velocityTracker3) {
                velocityTracker3.recycle ();
                mVelocityTracker = null;
            }
            this.endAction(motionEvent);
        } else if (2 == action) {
            final int i2 = this.mTouchMode;
            if (1 == i2) {
                this.mChart.disableScroll ();
                this.performDrag(motionEvent);
            } else if (2 == i2 || 3 == i2 || 4 == i2) {
                this.mChart.disableScroll ();
                if (this.mChart.isScaleXEnabled () || this.mChart.isScaleYEnabled ()) {
                    this.performZoom(motionEvent);
                }
            } else if (0 == i2 && Math.abs (ChartTouchListener.distance(motionEvent.getX (), mTouchStartPoint.x, motionEvent.getY (), mTouchStartPoint.y)) > mDragTriggerDist) {
                if (this.mChart.hasNoDragOffset ()) {
                    if (this.mChart.isFullyZoomedOut () || !this.mChart.isDragEnabled ()) {
                        this.mLastGesture = ChartGesture.DRAG;
                        if (this.mChart.isHighlightPerDragEnabled ()) {
                            this.performHighlightDrag(motionEvent);
                        }
                    } else {
                        this.mTouchMode = 1;
                    }
                } else if (this.mChart.isDragEnabled ()) {
                    this.mLastGesture = ChartGesture.DRAG;
                    this.mTouchMode = 1;
                }
            }
        } else if (3 == action) {
            this.mTouchMode = 0;
            this.endAction(motionEvent);
        } else if (5 != action) {
            if (6 == action) {
                Utils.velocityTrackerPointerUpCleanUpIfNecessary (motionEvent, mVelocityTracker);
                this.mTouchMode = 5;
            }
        } else if (2 <= motionEvent.getPointerCount ()) {
            this.mChart.disableScroll ();
            this.saveTouchStart(motionEvent);
            mSavedXDist = BarLineChartTouchListener.getXDist(motionEvent);
            mSavedYDist = BarLineChartTouchListener.getYDist(motionEvent);
            final float spacing = BarLineChartTouchListener.spacing(motionEvent);
            mSavedDist = spacing;
            if (10.0f < spacing) {
                if (this.mChart.isPinchZoomEnabled ()) {
                    this.mTouchMode = 4;
                } else if (mSavedXDist > mSavedYDist) {
                    this.mTouchMode = 2;
                } else {
                    this.mTouchMode = 3;
                }
            }
            BarLineChartTouchListener.midPoint(mTouchPointCenter, motionEvent);
        }
        mMatrix = this.mChart.getViewPortHandler ().refresh (mMatrix, this.mChart, true);
        return true;
    }

    private void saveTouchStart(final MotionEvent motionEvent) {
        mSavedMatrix.set (mMatrix);
        mTouchStartPoint.set (motionEvent.getX (), motionEvent.getY ());
        mClosestDataSetToTouch = this.mChart.getDataSetByTouchPoint (motionEvent.getX (), motionEvent.getY ());
    }

    private void performDrag(final MotionEvent r5) {
        throw new UnsupportedOperationException ("com.github.mikephil.charting.listener.BarLineChartTouchListener.performDrag(android.view.MotionEvent):void");
    }

    private void performZoom(final MotionEvent motionEvent) {
        final boolean z;
        final boolean z2;
        final boolean z3;
        final boolean z4;
        if (2 <= motionEvent.getPointerCount ()) {
            final com.github.mikephil.charting.listener.OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
            final float spacing = BarLineChartTouchListener.spacing(motionEvent);
            if (spacing > mMinScalePointerDistance) {
                final PointF pointF = mTouchPointCenter;
                final PointF trans = this.getTrans(pointF.x, pointF.y);
                final ViewPortHandler viewPortHandler = this.mChart.getViewPortHandler ();
                final int i = this.mTouchMode;
                boolean z5 = false;
                float f = 1.0f;
                if (4 == i) {
                    this.mLastGesture = ChartGesture.PINCH_ZOOM;
                    final float f2 = spacing / mSavedDist;
                    if (1.0f > f2) {
                        z5 = true;
                    }
                    if (z5) {
                        z3 = viewPortHandler.canZoomOutMoreX ();
                    } else {
                        z3 = viewPortHandler.canZoomInMoreX ();
                    }
                    if (z5) {
                        z4 = viewPortHandler.canZoomOutMoreY ();
                    } else {
                        z4 = viewPortHandler.canZoomInMoreY ();
                    }
                    final float f3 = this.mChart.isScaleXEnabled () ? f2 : 1.0f;
                    if (this.mChart.isScaleYEnabled ()) {
                        f = f2;
                    }
                    if (z4 || z3) {
                        mMatrix.set (mSavedMatrix);
                        mMatrix.postScale (f3, f, trans.x, trans.y);
                        if (null != onChartGestureListener) {
                            onChartGestureListener.onChartScale (motionEvent, f3, f);
                        }
                    }
                } else if (2 == i && this.mChart.isScaleXEnabled ()) {
                    this.mLastGesture = ChartGesture.X_ZOOM;
                    final float xDist = BarLineChartTouchListener.getXDist(motionEvent) / mSavedXDist;
                    if (1.0f > xDist) {
                        z2 = viewPortHandler.canZoomOutMoreX ();
                    } else {
                        z2 = viewPortHandler.canZoomInMoreX ();
                    }
                    if (z2) {
                        mMatrix.set (mSavedMatrix);
                        mMatrix.postScale (xDist, 1.0f, trans.x, trans.y);
                        if (null != onChartGestureListener) {
                            onChartGestureListener.onChartScale (motionEvent, xDist, 1.0f);
                        }
                    }
                } else if (3 == mTouchMode && this.mChart.isScaleYEnabled ()) {
                    this.mLastGesture = ChartGesture.Y_ZOOM;
                    final float yDist = BarLineChartTouchListener.getYDist(motionEvent) / mSavedYDist;
                    if (1.0f > yDist) {
                        z5 = true;
                    }
                    if (z5) {
                        z = viewPortHandler.canZoomOutMoreY ();
                    } else {
                        z = viewPortHandler.canZoomInMoreY ();
                    }
                    if (z) {
                        mMatrix.set (mSavedMatrix);
                        mMatrix.postScale (1.0f, yDist, trans.x, trans.y);
                        if (null != onChartGestureListener) {
                            onChartGestureListener.onChartScale (motionEvent, 1.0f, yDist);
                        }
                    }
                }
            }
        }
    }

    private void performHighlightDrag(final MotionEvent motionEvent) {
        final Highlight highlightByTouchPoint = this.mChart.getHighlightByTouchPoint (motionEvent.getX (), motionEvent.getY ());
        if (null != highlightByTouchPoint && !highlightByTouchPoint.equalTo (this.mLastHighlighted)) {
            this.mLastHighlighted = highlightByTouchPoint;
            this.mChart.highlightValue (highlightByTouchPoint, true);
        }
    }

    public PointF getTrans(final float f, final float f2) {
        final float f3;
        final IDataSet iDataSet;
        final ViewPortHandler viewPortHandler = this.mChart.getViewPortHandler ();
        final float offsetLeft = f - viewPortHandler.offsetLeft ();
        if (!this.mChart.isAnyAxisInverted () || null == (iDataSet = this.mClosestDataSetToTouch) || !this.mChart.isInverted (iDataSet.getAxisDependency ())) {
            f3 = -((this.mChart.getMeasuredHeight () - f2) - viewPortHandler.offsetBottom ());
        } else {
            f3 = -(f2 - viewPortHandler.offsetTop ());
        }
        return new PointF (offsetLeft, f3);
    }

    public Matrix getMatrix() {
        return mMatrix;
    }

    // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(final MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.DOUBLE_TAP;
        final com.github.mikephil.charting.listener.OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartDoubleTapped (motionEvent);
        }
        if (this.mChart.isDoubleTapToZoomEnabled ()) {
            final PointF trans = this.getTrans(motionEvent.getX (), motionEvent.getY ());
            final BarLineChartBase barLineChartBase = this.mChart;
            float f = 1.0f;
            final float f2 = barLineChartBase.isScaleXEnabled () ? 1.4f : 1.0f;
            if (barLineChartBase.isScaleYEnabled ()) {
                f = 1.4f;
            }
            barLineChartBase.zoom (f2, f, trans.x, trans.y);
            if (this.mChart.isLogEnabled ()) {
                Log.i ("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.x + ", y: " + trans.y);
            }
        }
        return super.onDoubleTap (motionEvent);
    }

    public void onLongPress(final MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.LONG_PRESS;
        final com.github.mikephil.charting.listener.OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartLongPressed (motionEvent);
        }
    }

    public boolean onSingleTapUp(final MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.SINGLE_TAP;
        final com.github.mikephil.charting.listener.OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartSingleTapped (motionEvent);
        }
        if (!this.mChart.isHighlightPerTapEnabled ()) {
            return false;
        }
        this.performHighlight(this.mChart.getHighlightByTouchPoint (motionEvent.getX (), motionEvent.getY ()), motionEvent);
        return super.onSingleTapUp (motionEvent);
    }

    public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float f, final float f2) {
        this.mLastGesture = ChartGesture.FLING;
        final OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartFling (motionEvent, motionEvent2, f, f2);
        }
        return super.onFling (motionEvent, motionEvent2, f, f2);
    }

    public void stopDeceleration() {
        mDecelerationVelocity = new PointF (0.0f, 0.0f);
    }

    public void computeScroll() {
        final PointF pointF = mDecelerationVelocity;
        if (0.0f != pointF.x || 0.0f != pointF.y) {
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis ();
            mDecelerationVelocity.x *= this.mChart.getDragDecelerationFrictionCoef ();
            mDecelerationVelocity.y *= this.mChart.getDragDecelerationFrictionCoef ();
            final float f = (currentAnimationTimeMillis - mDecelerationLastTime) / 1000.0f;
            final PointF pointF2 = mDecelerationVelocity;
            final float f2 = pointF2.x * f;
            final float f3 = pointF2.y * f;
            final PointF pointF3 = mDecelerationCurrentPoint;
            final float f4 = pointF3.x + f2;
            pointF3.x = f4;
            final float f5 = pointF3.y + f3;
            pointF3.y = f5;
            final MotionEvent obtain = MotionEvent.obtain (currentAnimationTimeMillis, currentAnimationTimeMillis, 2, f4, f5, 0);
            this.performDrag(obtain);
            obtain.recycle ();
            mMatrix = this.mChart.getViewPortHandler ().refresh (mMatrix, this.mChart, false);
            mDecelerationLastTime = currentAnimationTimeMillis;
            if (0.01d <= Math.abs (this.mDecelerationVelocity.x) || 0.01d <= Math.abs (this.mDecelerationVelocity.y)) {
                Utils.postInvalidateOnAnimation (this.mChart);
                return;
            }
            this.mChart.calculateOffsets ();
            this.mChart.postInvalidate ();
            this.stopDeceleration();
        }
    }
}
