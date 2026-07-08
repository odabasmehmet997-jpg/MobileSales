package com.google.android.material.timepicker;

import androidx.annotation.IntRange;
import androidx.annotation.StringRes;
import com.google.android.material.transformation.FabTransformationScrimBehavior;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface TimePickerControls {

    @interface ActiveSelection {
    }
    @interface ClockPeriod {
    }

    void setActiveSelection(int i2);

    void setHandRotation(float f2);

    void setValues(String[] strArr, @StringRes int i2);

    void updateTime(int i2, int i3, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY) int i4);
}
