package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzgc(zzgl zzb, zzp zza) implements Runnable {

    public void run() {
        this.zzb.zza.zzA();
        zzkr zzc = this.zzb.zza;
        zzp zzp = this.zza;
        zzc.zzaz().zzg();
        zzc.zzB();
        Preconditions.checkNotEmpty(zzp.zza);
        zzc.zzd(zzp);
    }
}
