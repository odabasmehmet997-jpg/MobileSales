package com.google.android.gms.internal.base;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zak extends Drawable implements Drawable.Callback {
    private int zaa;
    private long zab;
    private int zac;
    private int zad;
    private int zae;
    private int zaf;
    private final boolean zag;
    private boolean zah;
    private final zaj zai;
    private Drawable zaj;
    private Drawable zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private int zao;

    public zak(@Nullable Drawable drawable, @Nullable Drawable drawable2) {
        this(null);
        drawable = null == drawable ? this.zai.zaa : drawable;
        zaj = drawable;
        drawable.setCallback(this);
        final zaj zaj2 = zai;
        zaj2.zab = drawable.getChangingConfigurations() | zaj2.zab;
        drawable2 = null == drawable2 ? this.zai.zaa : drawable2;
        zak = drawable2;
        drawable2.setCallback(this);
        final zaj zaj3 = zai;
        zaj3.zab = drawable2.getChangingConfigurations() | zaj3.zab;
    }

    public int getChangingConfigurations() {
        final int changingConfigurations = super.getChangingConfigurations();
        final zaj zaj2 = zai;
        return changingConfigurations | zaj2.zaa | zaj2.zab;
    }

    @Nullable
    public Drawable.ConstantState getConstantState() {
        if (!this.zac()) {
            return null;
        }
        zai.zaa = this.getChangingConfigurations();
        return zai;
    }

    public int getIntrinsicHeight() {
        return Math.max(zaj.getIntrinsicHeight(), zak.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(zaj.getIntrinsicWidth(), zak.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!zan) {
            zao = resolveOpacity(zaj.getOpacity(), zak.getOpacity());
            zan = true;
        }
        return zao;
    }

    public void invalidateDrawable(final Drawable drawable) {
        final Drawable.Callback callback = this.getCallback();
        if (null != callback) {
            callback.invalidateDrawable(this);
        }
    }

    @CanIgnoreReturnValue
    public Drawable mutate() {
        if (!zah && super.mutate() == this) {
            if (this.zac()) {
                zaj.mutate();
                zak.mutate();
                zah = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    
    public void onBoundsChange(final Rect rect) {
        zaj.setBounds(rect);
        zak.setBounds(rect);
    }

    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long j2) {
        final Drawable.Callback callback = this.getCallback();
        if (null != callback) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    public void setAlpha(final int i2) {
        if (zaf == zad) {
            zaf = i2;
        }
        zad = i2;
        this.invalidateSelf();
    }

    public void setColorFilter(@Nullable final ColorFilter colorFilter) {
        zaj.setColorFilter(colorFilter);
        zak.setColorFilter(colorFilter);
    }

    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        final Drawable.Callback callback = this.getCallback();
        if (null != callback) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public Drawable zaa() {
        return zak;
    }

    public void zab(final int i2) {
        zac = zad;
        zaf = 0;
        zae = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        zaa = 1;
        this.invalidateSelf();
    }

    public boolean zac() {
        if (!zal) {
            boolean z = !(null == this.zaj.getConstantState() || null == this.zak.getConstantState());
            zam = z;
            zal = true;
        }
        return zam;
    }
    public void draw(final android.graphics.Canvas r8) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.base.zak.draw(android.graphics.Canvas):void");
    }

    zak(@Nullable final zaj zaj2) {
        zaa = 0;
        zad = 255;
        zaf = 0;
        zag = true;
        zai = new zaj(zaj2);
    }
}
