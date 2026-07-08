package com.proje.mobilesales.core.widget;

import android.view.View;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AnimateViewHolder extends RecyclerView.ViewHolder {
    public abstract void animateAddImpl(ViewPropertyAnimatorListener viewPropertyAnimatorListener);
    public abstract void animateRemoveImpl(ViewPropertyAnimatorListener viewPropertyAnimatorListener);
    public void preAnimateAddImpl() {
    }
    public void preAnimateRemoveImpl() {
    }
    public AnimateViewHolder(View view) {
        super(view);
    }
}
