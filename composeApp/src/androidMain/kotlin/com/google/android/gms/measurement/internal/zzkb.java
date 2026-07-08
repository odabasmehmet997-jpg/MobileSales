package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzob;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzkb {
    final zzkc zza;

    zzkb(final zzkc zzkc) {
        zza = zzkc;
    }

    
    @WorkerThread
    public void zza() {
        zza.zzg();
        if (zza.zzs.zzm().zzk(zza.zzs.zzav().currentTimeMillis())) {
            zza.zzs.zzm().zzg.zza(true);
            final ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (100 == runningAppProcessInfo.importance) {
                zza.zzs.zzay().zzj().zza("Detected application was in foreground");
                this.zzc(zza.zzs.zzav().currentTimeMillis(), false);
            }
        }
    }

    
    @WorkerThread
    public void zzb(final long j2, final boolean z) {
        zza.zzg();
        zza.zzm();
        if (zza.zzs.zzm().zzk(j2)) {
            zza.zzs.zzm().zzg.zza(true);
        }
        zza.zzs.zzm().zzj.zzb(j2);
        if (zza.zzs.zzm().zzg.zzb()) {
            this.zzc(j2, z);
        }
    }

    
    @WorkerThread
    @VisibleForTesting
    public void zzc(final long j2, final boolean z) {
        zza.zzg();
        if (zza.zzs.zzJ()) {
            zza.zzs.zzm().zzj.zzb(j2);
            zza.zzs.zzay().zzj().zzb("Session started, time", Long.valueOf(zza.zzs.zzav().elapsedRealtime()));
            final long j3 = j2 / 1000;
            zza.zzs.zzq().zzZ("auto", "_sid", Long.valueOf(j3), j2);
            zza.zzs.zzm().zzg.zza(false);
            final Bundle bundle = new Bundle();
            bundle.putLong("_sid", j3);
            if (zza.zzs.zzf().zzs(null, zzdw.zzZ) && z) {
                bundle.putLong("_aib", 1);
            }
            zza.zzs.zzq().zzH("auto", "_s", j2, bundle);
            zzob.zzc();
            if (zza.zzs.zzf().zzs(null, zzdw.zzac)) {
                final String zza2 = zza.zzs.zzm().zzo.zza();
                if (!TextUtils.isEmpty(zza2)) {
                    final Bundle bundle2 = new Bundle();
                    bundle2.putString("_ffr", zza2);
                    zza.zzs.zzq().zzH("auto", "_ssr", j2, bundle2);
                }
            }
        }
    }
}
