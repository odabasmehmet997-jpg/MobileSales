package com.proje.mobilesales.core.view;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PanAndZoomListener implements View.OnTouchListener {
    static final int DRAG = 1;
    static final int NONE = 0;
    private static final String TAG = "PanAndZoomListener";
    static final int ZOOM = 2;
    PanZoomCalculator panZoomCalculator;
    int mode = 0;
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1.0f;
    public static class Anchor {
        public static final int CENTER = 0;
        public static final int TOPLEFT = 1;
    }
    public PanAndZoomListener(FrameLayout frameLayout, View view, int i2) {
        this.panZoomCalculator = new PanZoomCalculator(frameLayout, view, i2);
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.start.set(motionEvent.getX(), motionEvent.getY());
            Log.d(TAG, "mode=DRAG");
            this.mode = 1;
        } else {
            if (action != 1) {
                if (action == 2) {
                    int i2 = this.mode;
                    if (i2 == 1) {
                        this.panZoomCalculator.doPan(motionEvent.getX() - this.start.x, motionEvent.getY() - this.start.y);
                        this.start.set(motionEvent.getX(), motionEvent.getY());
                    } else if (i2 == 2) {
                        float spacing = spacing(motionEvent);
                        Log.d(TAG, "newDist=" + spacing);
                        if (spacing > 10.0f) {
                            float f2 = spacing / this.oldDist;
                            this.oldDist = spacing;
                            this.panZoomCalculator.doZoom(f2, this.mid);
                        }
                    }
                } else if (action == 5) {
                    this.oldDist = spacing(motionEvent);
                    Log.d(TAG, "oldDist=" + this.oldDist);
                    if (this.oldDist > 10.0f) {
                        midPoint(this.mid, motionEvent);
                        this.mode = 2;
                        Log.d(TAG, "mode=ZOOM");
                    }
                }
            }
            this.mode = 0;
            Log.d(TAG, "mode=NONE");
        }
        return true;
    }
    private float spacing(MotionEvent motionEvent) {
        double x = motionEvent.getX(0) - motionEvent.getX(1);
        double y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }
    private void midPoint(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }
    public static class PanZoomCalculator {
        int anchor;
        View child;
        View window;
        int panJitter = 0;
        PointF currentPan = new PointF(0.0f, 0.0f);
        float currentZoom = 1.0f;
        Matrix matrix = new Matrix();

        private float getMinimumZoom() {
            return 1.0f;
        }

        PanZoomCalculator(View view, View view2, int i2) {
            this.window = view;
            this.child = view2;
            this.anchor = i2;
            onPanZoomChanged();
        }

        public void doZoom(float f2, PointF pointF) {
            float f3 = this.currentZoom;
            this.currentZoom = f2 * f3;
            float max = Math.max(getMinimumZoom(), this.currentZoom);
            this.currentZoom = max;
            this.currentZoom = Math.min(8.0f, max);
            float width = this.window.getWidth();
            float height = this.window.getHeight();
            float f4 = width * f3;
            float f5 = f3 * height;
            float f6 = this.currentZoom;
            float f7 = width * f6;
            float f8 = f6 * height;
            if (this.anchor == 0) {
                float f9 = pointF.x;
                PointF pointF2 = this.currentPan;
                float f10 = pointF2.x;
                float f11 = pointF.y;
                float f12 = pointF2.y;
                pointF2.x = f10 + (((((((f7 - width) * 0.5f) + f9) - f10) / f7) - (((((f4 - width) * 0.5f) + f9) - f10) / f4)) * f7);
                pointF2.y = f12 + (((((((f8 - height) * 0.5f) + f11) - f12) / f8) - (((((f5 - height) * 0.5f) + f11) - f12) / f5)) * f8);
            } else {
                float f13 = pointF.x;
                PointF pointF3 = this.currentPan;
                float f14 = pointF3.x;
                float f15 = (f13 - f14) / f4;
                float f16 = pointF.y;
                float f17 = pointF3.y;
                pointF3.x = f14 + ((((f13 - f14) / f7) - f15) * f7);
                pointF3.y = f17 + ((((f16 - f17) / f8) - ((f16 - f17) / f5)) * f8);
            }
            onPanZoomChanged();
        }

        public void doPan(float f2, float f3) {
            PointF pointF = this.currentPan;
            pointF.x += f2;
            pointF.y += f3;
            onPanZoomChanged();
        }

        public void reset() {
            this.currentZoom = getMinimumZoom();
            this.currentPan = new PointF(0.0f, 0.0f);
            onPanZoomChanged();
        }

        public void onPanZoomChanged() {
            Bitmap bitmap;
            float width = this.window.getWidth();
            float height = this.window.getHeight();
            float f2 = this.currentZoom;
            if (f2 <= 1.0f) {
                PointF pointF = this.currentPan;
                pointF.x = 0.0f;
                pointF.y = 0.0f;
            } else if (this.anchor == 0) {
                float width2 = (f2 - 1.0f) * this.window.getWidth() * 0.5f;
                float height2 = (this.currentZoom - 1.0f) * this.window.getHeight() * 0.5f;
                PointF pointF2 = this.currentPan;
                pointF2.x = Math.max(-width2, Math.min(width2, pointF2.x));
                PointF pointF3 = this.currentPan;
                pointF3.y = Math.max(-height2, Math.min(height2, pointF3.y));
            } else {
                float height3 = (this.currentZoom - 1.0f) * this.window.getHeight();
                PointF pointF4 = this.currentPan;
                pointF4.x = Math.max(-((f2 - 1.0f) * this.window.getWidth()), Math.min(0.0f, pointF4.x));
                PointF pointF5 = this.currentPan;
                pointF5.y = Math.max(-height3, Math.min(0.0f, pointF5.y));
            }
            View view = this.child;
            if ((view instanceof ImageView) && ((ImageView) view).getScaleType() == ImageView.ScaleType.MATRIX) {
                Drawable drawable = ((ImageView) this.child).getDrawable();
                if (drawable == null || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null) {
                    return;
                }
                float width3 = bitmap.getWidth();
                float height4 = bitmap.getHeight();
                float min = Math.min(width / width3, height / height4);
                float f3 = (width - (width3 * min)) * 0.5f;
                float f4 = this.currentZoom;
                float f5 = f3 * f4;
                float f6 = (height - (height4 * min)) * 0.5f * f4;
                this.matrix.reset();
                Matrix matrix = this.matrix;
                float f7 = this.currentZoom;
                matrix.postScale(f7 * min, f7 * min);
                Matrix matrix2 = this.matrix;
                PointF pointF6 = this.currentPan;
                matrix2.postTranslate(pointF6.x + f5, pointF6.y + f6);
                ((ImageView) this.child).setImageMatrix(this.matrix);
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.child.getLayoutParams();
            PointF pointF7 = this.currentPan;
            marginLayoutParams.leftMargin = ((int) pointF7.x) + this.panJitter;
            marginLayoutParams.topMargin = (int) pointF7.y;
            marginLayoutParams.width = (int) (this.window.getWidth() * this.currentZoom);
            marginLayoutParams.height = (int) (this.window.getHeight() * this.currentZoom);
            this.panJitter ^= 1;
            this.child.setLayoutParams(marginLayoutParams);
        }
    }
}
