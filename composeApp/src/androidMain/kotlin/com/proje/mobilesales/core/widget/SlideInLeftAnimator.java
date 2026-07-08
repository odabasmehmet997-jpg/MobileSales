package com.proje.mobilesales.core.widget;

import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.widget.BaseItemAnimator;

public class SlideInLeftAnimator extends BaseItemAnimator {
    public SlideInLeftAnimator() {
    }
    public SlideInLeftAnimator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }
    protected void animateRemoveImpl(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).translationX(-viewHolder.itemView.getRootView().getWidth()).setDuration(getRemoveDuration()).setInterpolator(this.mInterpolator).setListener(new BaseItemAnimator.DefaultRemoveVpaListener(viewHolder)).setStartDelay(getRemoveDelay(viewHolder)).start();
    }
    protected void preAnimateAddImpl(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.setTranslationX(viewHolder.itemView, -viewHolder.itemView.getRootView().getWidth());
    }
    protected void animateAddImpl(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).translationX(0.0f).setDuration(getAddDuration()).setInterpolator(this.mInterpolator).setListener(new BaseItemAnimator.DefaultAddVpaListener(viewHolder)).setStartDelay(getAddDelay(viewHolder)).start();
    }
}
