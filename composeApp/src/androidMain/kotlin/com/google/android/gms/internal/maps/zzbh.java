package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbh extends zzbi {
    final transient int zza;
    final transient int zzb;
    final zzbi zzc;

    zzbh(final zzbi zzbi, final int i2, final int i3) {
        zzc = zzbi;
        zza = i2;
        zzb = i3;
    }

    public Object get(final int i2) {
        zzba.zza(i2, zzb, FirebaseAnalytics.Param.INDEX);
        return zzc.get(i2 + zza);
    }

    public int size() {
        return zzb;
    }

    public List subList(final int i2, final int i3) {
        return this.subList(i2, i3);
    }

    
    public int zzb() {
        return zzc.zzc() + zza + zzb;
    }

    
    public int zzc() {
        return zzc.zzc() + zza;
    }

    
    public Object[] zze() {
        return zzc.zze();
    }

    public zzbi zzf(final int i2, final int i3) {
        zzba.zzc(i2, i3, zzb);
        final int i4 = zza;
        return zzc.subList(i2 + i4, i3 + i4);
    }
}
