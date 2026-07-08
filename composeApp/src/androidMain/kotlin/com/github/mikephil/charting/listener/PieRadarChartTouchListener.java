package com.github.mikephil.charting.listener;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.SelectionDetail;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PieRadarChartTouchListener extends com.github.mikephil.charting.listener.ChartTouchListener<PieRadarChartBase<?>> {
    private final PointF mTouchStartPoint = new PointF ();
    private final ArrayList<AngularVelocitySample> _velocitySamples = new ArrayList<> ();
    private float mStartAngle;
    private long mDecelerationLastTime;
    private float mDecelerationAngularVelocity;

    public PieRadarChartTouchListener(final PieRadarChartBase<?> pieRadarChartBase) {
        super (pieRadarChartBase);
    }

    public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (!this.mGestureDetector.onTouchEvent (motionEvent) && this.mChart.isRotationEnabled ()) {
            final float x = motionEvent.getX ();
            final float y = motionEvent.getY ();
            final int action = motionEvent.getAction ();
            if (0 == action) {
                this.startAction(motionEvent);
                this.stopDeceleration();
                this.resetVelocity();
                if (this.mChart.isDragDecelerationEnabled ()) {
                    this.sampleVelocity(x, y);
                }
                this.setGestureStartAngle(x, y);
                final PointF pointF = mTouchStartPoint;
                pointF.x = x;
                pointF.y = y;
            } else if (1 == action) {
                if (this.mChart.isDragDecelerationEnabled ()) {
                    this.stopDeceleration();
                    this.sampleVelocity(x, y);
                    final float calculateVelocity = this.calculateVelocity();
                    mDecelerationAngularVelocity = calculateVelocity;
                    if (0.0f != calculateVelocity) {
                        mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis ();
                        Utils.postInvalidateOnAnimation (this.mChart);
                    }
                }
                this.mChart.enableScroll ();
                this.mTouchMode = 0;
                this.endAction(motionEvent);
            } else if (2 == action) {
                if (this.mChart.isDragDecelerationEnabled ()) {
                    this.sampleVelocity(x, y);
                }
                if (0 == mTouchMode) {
                    final PointF pointF2 = mTouchStartPoint;
                    if (ChartTouchListener.distance(x, pointF2.x, y, pointF2.y) > Utils.convertDpToPixel (8.0f)) {
                        this.mLastGesture = ChartGesture.ROTATE;
                        this.mTouchMode = 6;
                        this.mChart.disableScroll ();
                        this.endAction(motionEvent);
                    }
                }
                if (6 == mTouchMode) {
                    this.updateGestureRotation(x, y);
                    this.mChart.invalidate ();
                }
                this.endAction(motionEvent);
            }
        }
        return true;
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
        final OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener ();
        if (null != onChartGestureListener) {
            onChartGestureListener.onChartSingleTapped (motionEvent);
        }
        int i = 0;
        if (!this.mChart.isHighlightPerTapEnabled ()) {
            return false;
        }
        final float distanceToCenter = this.mChart.distanceToCenter (motionEvent.getX (), motionEvent.getY ());
        if (distanceToCenter > this.mChart.getRadius ()) {
            if (null == mLastHighlighted) {
                this.mChart.highlightValues (null);
            } else {
                this.mChart.highlightTouch (null);
            }
            this.mLastHighlighted = null;
            return true;
        }
        final float angleForPoint = this.mChart.getAngleForPoint (motionEvent.getX (), motionEvent.getY ());
        final float phaseY = this.mChart.getAnimator ().getPhaseY ();
        final int indexForAngle = this.mChart.getIndexForAngle (angleForPoint);
        if (0 > indexForAngle) {
            this.mChart.highlightValues (null);
            this.mLastHighlighted = null;
            return true;
        }
        final List<SelectionDetail> selectionDetailsAtIndex = ((PieRadarChartBase) this.mChart).getSelectionDetailsAtIndex (indexForAngle);
        i = Utils.getClosestDataSetIndexByValue (selectionDetailsAtIndex, distanceToCenter / phaseY, null);
        if (0 > i) {
            this.mChart.highlightValues (null);
            this.mLastHighlighted = null;
            return true;
        }
        this.performHighlight(new Highlight (indexForAngle, i), motionEvent);
        return true;
    }

    private void resetVelocity() {
        _velocitySamples.clear ();
    }

    private void sampleVelocity(final float f, final float f2) {
        final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis ();
        _velocitySamples.add (new AngularVelocitySample (currentAnimationTimeMillis, this.mChart.getAngleForPoint (f, f2)));
        for (int size = _velocitySamples.size (); 0 < size - 2 && 1000 < currentAnimationTimeMillis - this._velocitySamples.get (0).time; size--) {
            _velocitySamples.remove (0);
        }
    }

    private float calculateVelocity() {
        if (_velocitySamples.isEmpty ()) {
            return 0.0f;
        }
        boolean z = false;
        final AngularVelocitySample angularVelocitySample = _velocitySamples.get (0);
        final ArrayList<AngularVelocitySample> arrayList = _velocitySamples;
        final AngularVelocitySample angularVelocitySample2 = arrayList.get (arrayList.size () - 1);
        AngularVelocitySample angularVelocitySample3 = angularVelocitySample;
        for (int size = _velocitySamples.size () - 1; 0 <= size; size--) {
            angularVelocitySample3 = _velocitySamples.get (size);
            if (angularVelocitySample3.angle != angularVelocitySample2.angle) {
                break;
            }
        }
        float f = (angularVelocitySample2.time - angularVelocitySample.time) / 1000.0f;
        if (0.0f == f) {
            f = 0.1f;
        }
        final float f2 = angularVelocitySample2.angle;
        final float f3 = angularVelocitySample3.angle;
        if (f2 >= f3) {
            z = true;
        }
        if (270.0d < Math.abs (f2 - f3)) {
            z = !z;
        }
        final float f4 = angularVelocitySample2.angle;
        final float f5 = angularVelocitySample.angle;
        if (180.0d < (f4 - f5)) {
            angularVelocitySample.angle = (float) (f5 + 360.0d);
        } else if (180.0d < (f5 - f4)) {
            angularVelocitySample2.angle = (float) (f4 + 360.0d);
        }
        final float abs = Math.abs ((angularVelocitySample2.angle - angularVelocitySample.angle) / f);
        return !z ? -abs : abs;
    }

    public void setGestureStartAngle(final float f, final float f2) {
        mStartAngle = this.mChart.getAngleForPoint (f, f2) - this.mChart.getRawRotationAngle ();
    }

    public void updateGestureRotation(final float f, final float f2) {
        this.mChart.setRotationAngle (this.mChart.getAngleForPoint (f, f2) - mStartAngle);
    }

    public void stopDeceleration() {
        mDecelerationAngularVelocity = 0.0f;
    }

    public void computeScroll() {
        if (0.0f != this.mDecelerationAngularVelocity) {
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis ();
            mDecelerationAngularVelocity *= this.mChart.getDragDecelerationFrictionCoef ();

            this.mChart.setRotationAngle (this.mChart.getRotationAngle () + (mDecelerationAngularVelocity * ((currentAnimationTimeMillis - mDecelerationLastTime) / 1000.0f)));
            mDecelerationLastTime = currentAnimationTimeMillis;
            if (0.001d <= Math.abs (this.mDecelerationAngularVelocity)) {
                Utils.postInvalidateOnAnimation (this.mChart);
            } else {
                this.stopDeceleration();
            }
        }
    }

    public class AngularVelocitySample {
        public float angle;
        public long time;

        public AngularVelocitySample(final long j, final float f) {
            time = j;
            angle = f;
        }
    }
}
