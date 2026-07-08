package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzkq {
    zzfy zza;
    List zzb;
    List zzc;
    long zzd;
    final zzkr zze;

    zzkq(zzkr zzkr, zzkp zzkp) {
        this.zze = zzkr;
    }

    private static long zzb(zzfo zzfo) {
        return ((zzfo.zzd() / 1000) / 60) / 60;
    }

    public boolean zza(long j2, zzfo zzfo) {
        Preconditions.checkNotNull(zzfo);
        if (null == zzc) {
            this.zzc = new ArrayList();
        }
        if (null == zzb) {
            this.zzb = new ArrayList();
        }
        if (0 < zzc.size() && zzb((zzfo) this.zzc.get(0)) != zzb(zzfo)) {
            return false;
        }
        long zzbr = this.zzd + zzfo.zzbr();
        this.zze.zzg();
        if (zzbr >= Math.max(0, ((Integer) zzdw.zzh.zza(null)).intValue())) {
            return false;
        }
        this.zzd = zzbr;
        this.zzc.add(zzfo);
        this.zzb.add(Long.valueOf(j2));
        int size = this.zzc.size();
        this.zze.zzg();
        return size < Math.max(1, ((Integer) zzdw.zzi.zza(null)).intValue());
    }
}
