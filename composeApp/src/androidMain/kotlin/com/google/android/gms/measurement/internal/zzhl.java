package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhl implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final boolean zzd;
    final zzhy zze;

    zzhl(zzhy zzhy, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zze = zzhy;
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = z;
    }

    public void run() {
        this.zze.zzs.zzt().zzz(this.zza, null, this.zzb, this.zzc, this.zzd);
    }
}
