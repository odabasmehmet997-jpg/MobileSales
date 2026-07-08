package com.google.android.material.animation;

import androidx.annotation.NonNull;

/*  INFO: loaded from: classes2.dex */
public interface AnimatableView {

    interface Listener {
        void onAnimationEnd();
    }

    void startAnimation(@NonNull Listener listener);

    void stopAnimation();
}
