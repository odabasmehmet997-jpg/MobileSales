package com.proje.mobilesales.core.view;

import android.content.Context;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.interfaces.RecyclerViewClickListener;

public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private final RecyclerViewClickListener clickListener;
    private final GestureDetector gestureDetector;
    private long lastTimeClicked = 0;
    private final long mDefaultInterval;
    public void onRequestDisallowInterceptTouchEvent(boolean z) {}
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) { }
    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewClickListener recyclerViewClickListener, long j2) {
        this.mDefaultInterval = j2;
        this.clickListener = recyclerViewClickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
            public void onLongPress(MotionEvent motionEvent) {
                RecyclerViewClickListener recyclerViewClickListener2 = null;
                View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder == null || recyclerViewClickListener2 == null) {
                    return;
                }
                recyclerViewClickListener2.onClickLong(findChildViewUnder, recyclerView.getChildLayoutPosition(findChildViewUnder));
            }
        });
    }
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.clickListener == null || !this.gestureDetector.onTouchEvent(motionEvent) || SystemClock.elapsedRealtime() - this.lastTimeClicked < this.mDefaultInterval) {
            return false;
        }
        this.lastTimeClicked = SystemClock.elapsedRealtime();
        this.clickListener.onClick(findChildViewUnder, recyclerView.getChildLayoutPosition(findChildViewUnder));
        return false;
    }
}
