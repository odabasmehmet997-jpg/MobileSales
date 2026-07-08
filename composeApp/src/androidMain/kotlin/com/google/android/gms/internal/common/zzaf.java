package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzaf extends zzag {
    final transient int zza;
    final transient int zzb;
    final zzag zzc;

    zzaf(zzag zzag, int i2, int i3) {
        this.zzc = zzag;
        this.zza = i2;
        this.zzb = i3;
    }

    public Object get(int i2) {
        zzs.zza(i2, this.zzb, FirebaseAnalytics.Param.INDEX);
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

    
    public boolean zzf() {
        return true;
    }

    
    public Object[] zzg() {
        return this.zzc.zzg();
    }

    public zzag zzh(int i2, int i3) {
        zzs.zzc(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }
}
