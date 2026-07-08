package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public record zzfu(zzgl zza, String zzb, Bundle zzc) implements Runnable {
    /* synthetic */

    public void run() {
        this.zza.zzw(this.zzb, this.zzc);
    }
}
