package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztk extends zztl {
    final transient int zza;
    final transient int zzb;
    final zztl zzc;

    zztk(zztl zztl, int i2, int i3) {
        this.zzc = zztl;
        this.zza = i2;
        this.zzb = i3;
    }

    public Object get(int i2) {
        zztd.zza(i2, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i2 + this.zza);
    }

    public int size() {
        return this.zzb;
    }

    public List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    
    public int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    
    public int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    
    public Object[] zze() {
        return this.zzc.zze();
    }

    public zztl zzf(int i2, int i3) {
        zztd.zzc(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }
}
