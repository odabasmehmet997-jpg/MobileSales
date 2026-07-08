package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaj extends Drawable.ConstantState {
    int zaa;
    int zab;

    zaj(@Nullable final zaj zaj) {
        if (null != zaj) {
            zaa = zaj.zaa;
            zab = zaj.zab;
        }
    }

    public int getChangingConfigurations() {
        return zaa;
    }

    public Drawable newDrawable() {
        return new zak(this);
    }
}
