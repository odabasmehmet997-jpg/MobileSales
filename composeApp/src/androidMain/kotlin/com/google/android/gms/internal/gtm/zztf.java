package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
abstract class zztf extends zzub {
    private final int zza;
    private int zzb;

    protected zztf(int i2, int i3) {
        zztd.zzb(i3, i2, FirebaseAnalytics.Param.INDEX);
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
