package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzkm(zzkr zzb, zzp zza) implements Callable {

    public Object call() throws Exception {
        zzah zzh = this.zzb.zzh(Preconditions.checkNotNull(this.zza.zza));
        final zzag zzag = com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE;
        if (zzh.zzi(zzag) && zzah.zzb(this.zza.zzv).zzi(zzag)) {
            return this.zzb.zzd(this.zza).zzu();
        }
        this.zzb.zzay().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
