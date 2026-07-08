package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzok;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzka {
    @VisibleForTesting
    private long zza;
    @VisibleForTesting
    long zzb;
    final zzkc zzc;
    private final zzan zzd;

    public zzka(final zzkc zzkc) {
        zzc = zzkc;
        zzd = new zzjz(this, zzkc.zzs);
        final long elapsedRealtime = zzkc.zzs.zzav().elapsedRealtime();
        zza = elapsedRealtime;
        zzb = elapsedRealtime;
    }

    
    public void zza() {
        zzd.zzb();
        zza = 0;
        zzb = 0;
    }

    
    @WorkerThread
    public void zzb(final long j2) {
        zzd.zzb();
    }

    
    @WorkerThread
    public void zzc(final long j2) {
        zzc.zzg();
        zzd.zzb();
        zza = j2;
        zzb = j2;
    }

    @WorkerThread
    public boolean zzd(final boolean z, final boolean z2, final long j2) {
        zzc.zzg();
        zzc.zza();
        zzok.zzc();
        if (!zzc.zzs.zzf().zzs(null, zzdw.zzad)) {
            zzc.zzs.zzm().zzj.zzb(zzc.zzs.zzav().currentTimeMillis());
        } else if (zzc.zzs.zzJ()) {
            zzc.zzs.zzm().zzj.zzb(zzc.zzs.zzav().currentTimeMillis());
        }
        long j3 = j2 - zza;
        if (z || 1000 <= j3) {
            if (!z2) {
                j3 = j2 - zzb;
                zzb = j2;
            }
            zzc.zzs.zzay().zzj().zzb("Recording user engagement, ms", Long.valueOf(j3));
            final Bundle bundle = new Bundle();
            bundle.putLong("_et", j3);
            zzky.zzJ(zzc.zzs.zzs().zzj(!zzc.zzs.zzf().zzu()), bundle, true);
            if (!z2) {
                zzc.zzs.zzq().zzG("auto", "_e", bundle);
            }
            zza = j2;
            zzd.zzb();
            zzd.zzd(3600000);
            return true;
        }
        zzc.zzs.zzay().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j3));
        return false;
    }
}
