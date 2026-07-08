package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhk implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final zzhy zzd;

    zzhk(zzhy zzhy, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zzd = zzhy;
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
    }

    public void run() {
        this.zzd.zzs.zzt().zzw(this.zza, null, this.zzb, this.zzc);
    }
}
