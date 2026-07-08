package com.google.android.material.sidesheet;

import androidx.annotation.RestrictTo;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.sidesheet.SheetCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*  INFO: loaded from: classes2.dex */
interface Sheet<C extends SheetCallback> extends MaterialBackHandler {
    int EDGE_LEFT = 1;
    int EDGE_RIGHT = 0;
    int STATE_DRAGGING = 1;
    int STATE_EXPANDED = 3;
    int STATE_HIDDEN = 5;
    int STATE_SETTLING = 2;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @interface SheetEdge {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @interface SheetState {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @interface StableSheetState {
    }

    void addCallback(C c2);

    int getState();

    void removeCallback(C c2);

    void setState(int i2);
}
