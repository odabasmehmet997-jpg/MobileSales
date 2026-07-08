package com.google.android.material.sidesheet;

import android.view.View;
import androidx.annotation.NonNull;

/*  INFO: loaded from: classes2.dex */
public abstract class SideSheetCallback implements SheetCallback {
    void onLayout(@NonNull View view) {
    }

    @Override // com.google.android.material.sidesheet.SheetCallback
    public abstract void onSlide(@NonNull View view, float f2);

    @Override // com.google.android.material.sidesheet.SheetCallback
    public abstract void onStateChanged(@NonNull View view, int i2);
}
