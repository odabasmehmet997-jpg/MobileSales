package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;

public abstract class zag {
    final zad zaa;
    protected int zab;

    
    public abstract void zaa(@Nullable Drawable drawable, boolean z, boolean z2, boolean z3);

    
    public final void zab(Context context, zam zam, boolean z) {
        int i2 = this.zab;
        zaa(0 != i2 ? context.getResources().getDrawable(i2) : null, z, false, false);
    }

    
    public final void zac(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), false, false, true);
    }
}
