package com.google.android.gms.internal.gtm;

import android.content.ComponentName;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzbz(zzca zzb, ComponentName zza) implements Runnable {

    public void run() {
        zzcb.zzb(this.zzb.zza, this.zza);
    }
}
