package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.jspecify.nullness.NullMarked;

import java.util.NoSuchElementException;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zzz extends zzak {
    private final int zza;
    private int zzb;

    protected zzz(int i2, int i3) {
        zzs.zzb(i3, i2, FirebaseAnalytics.Param.INDEX);
        this.zza = i2;
        this.zzb = i3;
    }

    public final boolean hasNext() {
        return this.zzb < this.zza;
    }

    public final boolean hasPrevious() {
        return 0 < zzb;
    }

    public final Object next() {
        if (hasNext()) {
            int i2 = this.zzb;
            this.zzb = i2 + 1;
            return zza(i2);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.zzb;
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i2 = this.zzb - 1;
            this.zzb = i2;
            return zza(i2);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.zzb - 1;
    }

    
    public abstract Object zza(int i2);
}
