package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzjy {
    final zzkc zza;
    private zzjx zzb;

    zzjy(zzkc zzkc) {
        this.zza = zzkc;
    }

    
    @WorkerThread
    public void zza(long j2) {
        this.zzb = new zzjx(this, this.zza.zzs.zzav().currentTimeMillis(), j2);
        this.zza.zzd.postDelayed(this.zzb, 2000);
    }

    
    @WorkerThread
    public void zzb() {
        this.zza.zzg();
        zzjx zzjx = this.zzb;
        if (null != zzjx) {
            this.zza.zzd.removeCallbacks(zzjx);
        }
        this.zza.zzs.zzm().zzl.zza(false);
    }
}
