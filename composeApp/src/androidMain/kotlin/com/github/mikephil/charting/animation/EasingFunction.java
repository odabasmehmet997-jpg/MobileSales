package com.github.mikephil.charting.animation;

import android.animation.TimeInterpolator;

public interface EasingFunction extends TimeInterpolator {
    float getInterpolation(float f);
}
