package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzew extends zzex {
    final transient int zza;
    final transient int zzb;
    final zzex zzc;

    zzew(final zzex zzex, final int i2, final int i3) {
        zzc = zzex;
        zza = i2;
        zzb = i3;
    }

    public Object get(final int i2) {
        zzer.zzc(i2, zzb, FirebaseAnalytics.Param.INDEX);
        return zzc.get(i2 + zza);
    }

    public int size() {
        return zzb;
    }

    public List subList(final int i2, final int i3) {
        return this.subList(i2, i3);
    }

    
    public Object[] zzb() {
        return zzc.zzb();
    }

    
    public int zzc() {
        return zzc.zzc() + zza;
    }

    
    public int zzd() {
        return zzc.zzc() + zza + zzb;
    }

    
    public boolean zzf() {
        return true;
    }

    public zzex zzh(final int i2, final int i3) {
        zzer.zze(i2, i3, zzb);
        final int i4 = zza;
        return zzc.subList(i2 + i4, i3 + i4);
    }
}
