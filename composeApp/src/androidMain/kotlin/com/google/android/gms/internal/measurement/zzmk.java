package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmk extends zzmi {
    zzmk() {
    }

    
    public int zza(Object obj) {
        return ((zzmj) obj).zza();
    }

    
    public int zzb(Object obj) {
        return ((zzmj) obj).zzb();
    }

    
    public Object zzc(Object obj) {
        return ((zzjz) obj).zzc;
    }

    
    public Object zzd(Object obj, Object obj2) {
        zzmj zzmj = (zzmj) obj2;
        if (zzmj.equals(com.google.android.gms.internal.measurement.zzmj.zzc())) {
            return obj;
        }
        return com.google.android.gms.internal.measurement.zzmj.zzd((zzmj) obj, zzmj);
    }

    
    public Object zze() {
        return zzmj.zze();
    }

    
    public void zzf(Object obj, int i2, long j2) {
        ((zzmj) obj).zzh(i2 << 3, Long.valueOf(j2));
    }

    
    public void zzg(Object obj) {
        ((zzjz) obj).zzc.zzf();
    }

    
    public void zzh(Object obj, Object obj2) {
        ((zzjz) obj).zzc = (zzmj) obj2;
    }

    
    public void zzi(Object obj, zzjh zzjh) throws IOException {
        ((zzmj) obj).zzi(zzjh);
    }
}
