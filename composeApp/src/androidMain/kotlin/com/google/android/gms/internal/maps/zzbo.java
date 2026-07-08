package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbo extends zzbi {
    static final zzbi zza = new zzbo(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzbo(final Object[] objArr, final int i2) {
        zzb = objArr;
        zzc = i2;
    }

    public Object get(final int i2) {
        zzba.zza(i2, zzc, FirebaseAnalytics.Param.INDEX);
        final Object obj = zzb[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    public int size() {
        return zzc;
    }

    
    public int zza(final Object[] objArr, final int i2) {
        System.arraycopy(zzb, 0, objArr, 0, zzc);
        return zzc;
    }

    
    public int zzb() {
        return zzc;
    }

    
    public int zzc() {
        return 0;
    }

    
    public Object[] zze() {
        return zzb;
    }
}
