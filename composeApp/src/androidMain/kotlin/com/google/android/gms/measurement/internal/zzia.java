package com.google.android.gms.measurement.internal;

import java.util.Map;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public record zzia(zzib zza, int zzb, Exception zzc, byte[] zzd, Map zze) implements Runnable {
    /* synthetic */

    public void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
