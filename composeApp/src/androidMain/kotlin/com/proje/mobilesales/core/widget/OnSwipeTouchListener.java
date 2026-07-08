package com.proje.mobilesales.core.widget;

import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private float downX;
    private float downY;
    private final int min_distance = 100;
    private float upX;
    private float upY;
    View f1221v;

    public void onBottomToTopSwipe() {
    }

    public void onLeftToRightSwipe() {
    }

    public void onRightToLeftSwipe() {
    }

    public void onTopToBottomSwipe() {
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.f1221v = view;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
            return true;
        }
        if (action != 1) {
            return false;
        }
        this.upX = motionEvent.getX();
        float y = motionEvent.getY();
        this.upY = y;
        float f2 = this.downX - this.upX;
        float f3 = this.downY - y;
        if (Math.abs(f2) > Math.abs(f3)) {
            if (Math.abs(f2) <= this.min_distance) {
                return false;
            }
            if (f2 < 0.0f) {
                onLeftToRightSwipe();
                return true;
            }
            if (f2 > 0.0f) {
                onRightToLeftSwipe();
                return true;
            }
        } else if (Math.abs(f3) > this.min_distance) {
            if (f3 < 0.0f) {
                onTopToBottomSwipe();
                return true;
            }
            if (f3 > 0.0f) {
                onBottomToTopSwipe();
                return true;
            }
        }
        return false;
    }
}
