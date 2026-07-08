package com.google.android.gms.internal.gtm;

import android.os.Looper;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzcu(zzcv zza) implements Runnable {

    public void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zza.zzb.zzd().zzi(this);
            return;
        }
        final zzcv zzcv = zza;
        final boolean zzh = zzcv.zzh();
        zzcv.zzd = 0;
        if (zzh) {
            zza.zza();
        }
    }
}
