package com.google.android.material.slider;

import androidx.annotation.NonNull;

/*  INFO: loaded from: classes2.dex */
public interface LabelFormatter {
    int LABEL_FLOATING = 0;
    int LABEL_GONE = 2;
    int LABEL_VISIBLE = 3;
    int LABEL_WITHIN_BOUNDS = 1;

    @NonNull
    String getFormattedValue(float f2);
}
