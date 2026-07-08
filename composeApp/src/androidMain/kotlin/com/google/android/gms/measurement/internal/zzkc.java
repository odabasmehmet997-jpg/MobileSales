package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.internal.measurement.zzby;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzkc extends zzf {
    final zzkb zza = new zzkb(this);
    final zzka zzb = new zzka(this);
    private final zzjy zzc = new zzjy(this);
    
    public Handler zzd;

    zzkc(final zzft zzft) {
        super(zzft);
    }

    static void zzj(final zzkc zzkc, final long j2) {
        zzkc.zzg();
        zzkc.zzm();
        zzkc.zzs.zzay().zzj().zzb("Activity paused, time", Long.valueOf(j2));
        zzkc.zzc.zza(j2);
        if (zzkc.zzs.zzf().zzu()) {
            zzkc.zzb.zzb(j2);
        }
    }

    static void zzl(final zzkc zzkc, final long j2) {
        zzkc.zzg();
        zzkc.zzm();
        zzkc.zzs.zzay().zzj().zzb("Activity resumed, time", Long.valueOf(j2));
        if (zzkc.zzs.zzf().zzu() || zzkc.zzs.zzm().zzl.zzb()) {
            zzkc.zzb.zzc(j2);
        }
        zzkc.zzc.zzb();
        final zzkb zzkb = zzkc.zza;
        zzkb.zza.zzg();
        if (zzkb.zza.zzs.zzJ()) {
            zzkb.zzb(zzkb.zza.zzs.zzav().currentTimeMillis(), false);
        }
    }

    
    @WorkerThread
    public void zzm() {
        this.zzg();
        if (null == this.zzd) {
            zzd = new zzby(Looper.getMainLooper());
        }
    }

    
    public boolean zzf() {
        return false;
    }
}
