package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztr extends zztl {
    static final zztl zza = new zztr(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zztr(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    public Object get(int i2) {
        zztd.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zzb[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    public int size() {
        return this.zzc;
    }

    
    public int zza(Object[] objArr, int i2) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    
    public int zzb() {
        return this.zzc;
    }

    
    public int zzc() {
        return 0;
    }

    
    public Object[] zze() {
        return this.zzb;
    }
}
