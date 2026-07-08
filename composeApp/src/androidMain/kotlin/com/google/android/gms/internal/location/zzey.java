package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzey extends zzex {
    static final zzex zza = new zzey(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzey(final Object[] objArr, final int i2) {
        zzb = objArr;
        zzc = i2;
    }

    public Object get(final int i2) {
        zzer.zzc(i2, zzc, FirebaseAnalytics.Param.INDEX);
        final Object obj = zzb[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    public int size() {
        return zzc;
    }

    
    public Object[] zzb() {
        return zzb;
    }

    
    public int zzc() {
        return 0;
    }

    
    public int zzd() {
        return zzc;
    }

    
    public boolean zzf() {
        return false;
    }

    
    public int zzg(final Object[] objArr, final int i2) {
        System.arraycopy(zzb, 0, objArr, 0, zzc);
        return zzc;
    }
}
