package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;

public class ViewPortHandler {
    protected final Matrix mMatrixTouch = new Matrix ();
    protected final float[] matrixBuffer = new float[9];
    protected RectF mContentRect = new RectF ();
    protected float mChartWidth;
    protected float mChartHeight;
    private float mMinScaleY = 1.0f;
    private float mMaxScaleY = Float.MAX_VALUE;
    private float mMinScaleX = 1.0f;
    private float mMaxScaleX = Float.MAX_VALUE;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mTransX;
    private float mTransY;
    private float mTransOffsetX;
    private float mTransOffsetY;

    public void setChartDimens(final float f, final float f2) {
        final float offsetLeft = this.offsetLeft();
        final float offsetTop = this.offsetTop();
        final float offsetRight = this.offsetRight();
        final float offsetBottom = this.offsetBottom();
        mChartHeight = f2;
        mChartWidth = f;
        this.restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public boolean hasChartDimens() {
        return 0.0f < this.mChartHeight && 0.0f < this.mChartWidth;
    }

    public void restrainViewPort(final float f, final float f2, final float f3, final float f4) {
        mContentRect.set (f, f2, mChartWidth - f3, mChartHeight - f4);
    }

    public float offsetLeft() {
        return mContentRect.left;
    }

    public float offsetRight() {
        return mChartWidth - mContentRect.right;
    }

    public float offsetTop() {
        return mContentRect.top;
    }

    public float offsetBottom() {
        return mChartHeight - mContentRect.bottom;
    }

    public float contentTop() {
        return mContentRect.top;
    }

    public float contentLeft() {
        return mContentRect.left;
    }

    public float contentRight() {
        return mContentRect.right;
    }

    public float contentBottom() {
        return mContentRect.bottom;
    }

    public float contentWidth() {
        return mContentRect.width ();
    }

    public float contentHeight() {
        return mContentRect.height ();
    }

    public RectF getContentRect() {
        return mContentRect;
    }

    public PointF getContentCenter() {
        return new PointF (mContentRect.centerX (), mContentRect.centerY ());
    }

    public float getChartHeight() {
        return mChartHeight;
    }

    public float getChartWidth() {
        return mChartWidth;
    }

    public Matrix zoomIn(final float f, final float f2) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postScale (1.4f, 1.4f, f, f2);
        return matrix;
    }

    public Matrix zoomOut(final float f, final float f2) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postScale (0.7f, 0.7f, f, f2);
        return matrix;
    }

    public Matrix zoom(final float f, final float f2) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postScale (f, f2);
        return matrix;
    }

    public Matrix zoom(final float f, final float f2, final float f3, final float f4) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postScale (f, f2, f3, f4);
        return matrix;
    }

    public Matrix setZoom(final float f, final float f2) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.setScale (f, f2);
        return matrix;
    }

    public Matrix setZoom(final float f, final float f2, final float f3, final float f4) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.setScale (f, f2, f3, f4);
        return matrix;
    }

    public Matrix fitScreen() {
        mMinScaleX = 1.0f;
        mMinScaleY = 1.0f;
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        final float[] fArr = new float[9];
        matrix.getValues (fArr);
        fArr[2] = 0.0f;
        fArr[5] = 0.0f;
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        matrix.setValues (fArr);
        return matrix;
    }

    public Matrix translate(final float[] fArr) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postTranslate (-(fArr[0] - this.offsetLeft()), -(fArr[1] - this.offsetTop()));
        return matrix;
    }

    public void centerViewPort(final float[] fArr, final View view) {
        final Matrix matrix = new Matrix ();
        matrix.set (mMatrixTouch);
        matrix.postTranslate (-(fArr[0] - this.offsetLeft()), -(fArr[1] - this.offsetTop()));
        this.refresh(matrix, view, true);
    }

    public Matrix refresh(final Matrix matrix, final View view, final boolean z) {
        mMatrixTouch.set (matrix);
        this.limitTransAndScale(mMatrixTouch, mContentRect);
        if (z) {
            view.invalidate ();
        }
        matrix.set (mMatrixTouch);
        return matrix;
    }

    public void limitTransAndScale(final Matrix matrix, final RectF rectF) {
        final float f;
        final float f2;
        matrix.getValues (matrixBuffer);
        final float[] fArr = matrixBuffer;
        final float f3 = fArr[2];
        final float f4 = fArr[0];
        final float f5 = fArr[5];
        final float f6 = fArr[4];
        mScaleX = Math.min (Math.max (mMinScaleX, f4), mMaxScaleX);
        mScaleY = Math.min (Math.max (mMinScaleY, f6), mMaxScaleY);
        if (null != rectF) {
            f2 = rectF.width ();
            f = rectF.height ();
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        mTransX = Math.min (Math.max (f3, ((-f2) * (mScaleX - 1.0f)) - mTransOffsetX), mTransOffsetX);
        final float max = Math.max (Math.min (f5, (f * (mScaleY - 1.0f)) + mTransOffsetY), -mTransOffsetY);
        mTransY = max;
        final float[] fArr2 = matrixBuffer;
        fArr2[2] = mTransX;
        fArr2[0] = mScaleX;
        fArr2[5] = max;
        fArr2[4] = mScaleY;
        matrix.setValues (fArr2);
    }

    public void setMinimumScaleX(float f) {
        if (1.0f > f) {
            f = 1.0f;
        }
        mMinScaleX = f;
        this.limitTransAndScale(mMatrixTouch, mContentRect);
    }

    public void setMaximumScaleX(float f) {
        if (0.0f == f) {
            f = Float.MAX_VALUE;
        }
        mMaxScaleX = f;
        this.limitTransAndScale(mMatrixTouch, mContentRect);
    }

    public void setMinMaxScaleX(float f, float f2) {
        if (1.0f > f) {
            f = 1.0f;
        }
        if (0.0f == f2) {
            f2 = Float.MAX_VALUE;
        }
        mMinScaleX = f;
        mMaxScaleX = f2;
        this.limitTransAndScale(mMatrixTouch, mContentRect);
    }

    public void setMinimumScaleY(float f) {
        if (1.0f > f) {
            f = 1.0f;
        }
        mMinScaleY = f;
        this.limitTransAndScale(mMatrixTouch, mContentRect);
    }

    public void setMaximumScaleY(float f) {
        if (0.0f == f) {
            f = Float.MAX_VALUE;
        }
        mMaxScaleY = f;
        this.limitTransAndScale(mMatrixTouch, mContentRect);
    }

    public Matrix getMatrixTouch() {
        return mMatrixTouch;
    }

    public boolean isInBoundsX(final float f) {
        return this.isInBoundsLeft(f) && this.isInBoundsRight(f);
    }

    public boolean isInBoundsY(final float f) {
        return this.isInBoundsTop(f) && this.isInBoundsBottom(f);
    }

    public boolean isInBounds(final float f, final float f2) {
        return this.isInBoundsX(f) && this.isInBoundsY(f2);
    }

    public boolean isInBoundsLeft(final float f) {
        return mContentRect.left <= f;
    }

    public boolean isInBoundsRight(final float f) {
        return mContentRect.right >= ((int) (f * 100.0f)) / 100.0f;
    }

    public boolean isInBoundsTop(final float f) {
        return mContentRect.top <= f;
    }

    public boolean isInBoundsBottom(final float f) {
        return mContentRect.bottom >= ((int) (f * 100.0f)) / 100.0f;
    }

    public float getScaleX() {
        return mScaleX;
    }

    public float getScaleY() {
        return mScaleY;
    }

    public float getMinScaleX() {
        return mMinScaleX;
    }

    public float getMaxScaleX() {
        return mMaxScaleX;
    }

    public float getMinScaleY() {
        return mMinScaleY;
    }

    public float getMaxScaleY() {
        return mMaxScaleY;
    }

    public float getTransX() {
        return mTransX;
    }

    public float getTransY() {
        return mTransY;
    }

    public boolean isFullyZoomedOut() {
        return this.isFullyZoomedOutX() && this.isFullyZoomedOutY();
    }

    public boolean isFullyZoomedOutY() {
        final float f = mScaleY;
        final float f2 = mMinScaleY;
        return f <= f2 && 1.0f >= f2;
    }

    public boolean isFullyZoomedOutX() {
        final float f = mScaleX;
        final float f2 = mMinScaleX;
        return f <= f2 && 1.0f >= f2;
    }

    public void setDragOffsetX(final float f) {
        mTransOffsetX = com.github.mikephil.charting.utils.Utils.convertDpToPixel (f);
    }

    public void setDragOffsetY(final float f) {
        mTransOffsetY = Utils.convertDpToPixel (f);
    }

    public boolean hasNoDragOffset() {
        return 0.0f >= this.mTransOffsetX && 0.0f >= this.mTransOffsetY;
    }

    public boolean canZoomOutMoreX() {
        return mScaleX > mMinScaleX;
    }

    public boolean canZoomInMoreX() {
        return mScaleX < mMaxScaleX;
    }

    public boolean canZoomOutMoreY() {
        return mScaleY > mMinScaleY;
    }

    public boolean canZoomInMoreY() {
        return mScaleY < mMaxScaleY;
    }
}
