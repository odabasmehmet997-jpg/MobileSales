package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private final ClickListener clickListener;
    private final GestureDetector gestureDetector;
    public interface ClickListener {
        void onClick(View view, int i2);

        void onLongClick(View view, int i2);
    }
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
            public void onLongPress(MotionEvent motionEvent) {
                ClickListener clickListener2;
                View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder == null || (clickListener2 = clickListener) == null) {
                    return;
                }
                clickListener2.onLongClick(findChildViewUnder, recyclerView.getChildLayoutPosition(findChildViewUnder));
            }
        });
    }
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.clickListener == null || !this.gestureDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        this.clickListener.onClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
        return false;
    }
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}

}
