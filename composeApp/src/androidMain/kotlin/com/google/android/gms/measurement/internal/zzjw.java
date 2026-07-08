package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public record zzjw(zzjx zza) implements Runnable {
    /* synthetic */

    public void run() {
        zzjx zzjx = this.zza;
        zzjy zzjy = zzjx.zzc();
        long j2 = zzjx.zza();
        long j3 = zzjx.zzb();
        zzjy.zza.zzg();
        zzjy.zza.zzs.zzay().zzc().zza("Application going to the background");
        zzjy.zza.zzs.zzm().zzl.zza(true);
        Bundle bundle = new Bundle();
        if (!zzjy.zza.zzs.zzf().zzu()) {
            zzjy.zza.zzb.zzb(j3);
            zzjy.zza.zzb.zzd(false, false, j3);
        }
        zzjy.zza.zzs.zzq().zzH("auto", "_ab", j2, bundle);
    }
}
