package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.Map;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class QuantizerResult {
    public final Map<Integer, Integer> colorToCount;

    QuantizerResult(Map<Integer, Integer> map) {
        this.colorToCount = map;
    }
}
