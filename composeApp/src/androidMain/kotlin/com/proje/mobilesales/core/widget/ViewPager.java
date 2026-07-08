package com.proje.mobilesales.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPager extends androidx.viewpager.widget.ViewPager {
    private boolean mSwipeEnabled;
    public ViewPager(Context context) {
        this(context, null);
    }
    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSwipeEnabled = true;
    }
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mSwipeEnabled && super.onInterceptTouchEvent(motionEvent);
    }
    public void setSwipeEnabled(boolean z) {
        this.mSwipeEnabled = z;
    }
}
