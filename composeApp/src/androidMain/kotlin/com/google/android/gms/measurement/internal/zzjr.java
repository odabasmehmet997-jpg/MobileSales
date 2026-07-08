package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzjr implements Runnable {
    final zzkr zza;
    final Runnable zzb;

    zzjr(final zzjt zzjt, final zzkr zzkr, final Runnable runnable) {
        zza = zzkr;
        zzb = runnable;
    }

    public void run() {
        zza.zzA();
        zza.zzz(zzb);
        zza.zzW();
    }
}
