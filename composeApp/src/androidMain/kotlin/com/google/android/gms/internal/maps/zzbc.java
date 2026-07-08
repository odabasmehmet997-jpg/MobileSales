package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
abstract class zzbc extends zzby {
    private final int zza;
    private int zzb;

    protected zzbc(final int i2, final int i3) {
        zzba.zzb(i3, i2, FirebaseAnalytics.Param.INDEX);
        zza = i2;
        zzb = i3;
    }

    public final boolean hasNext() {
        return zzb < zza;
    }

    public final boolean hasPrevious() {
        return 0 < this.zzb;
    }

    public final Object next() {
        if (this.hasNext()) {
            final int i2 = zzb;
            zzb = i2 + 1;
            return this.zza(i2);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return zzb;
    }

    public final Object previous() {
        if (this.hasPrevious()) {
            final int i2 = zzb - 1;
            zzb = i2;
            return this.zza(i2);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return zzb - 1;
    }

    
    public abstract Object zza(int i2);
}
