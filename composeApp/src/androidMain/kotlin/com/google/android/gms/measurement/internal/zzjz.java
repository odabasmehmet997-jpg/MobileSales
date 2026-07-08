package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzjz extends zzan {
    final zzka zza;

    
    zzjz(zzka zzka, zzgo zzgo) {
        super(zzgo);
        this.zza = zzka;
    }

    @WorkerThread
    public void zzc() {
        zzka zzka = this.zza;
        zzka.zzc.zzg();
        zzka.zzd(false, false, zzka.zzc.zzs.zzav().elapsedRealtime());
        zzka.zzc.zzs.zzd().zzf(zzka.zzc.zzs.zzav().elapsedRealtime());
    }
}
