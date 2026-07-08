package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
abstract class zzku {
    private static final zzku zza = new zzkq(null);
    private static final zzku zzb = new zzks(null);

    zzku(final zzkt zzkt) {
    }

    static zzku zzc() {
        return zzku.zza;
    }

    static zzku zzd() {
        return zzku.zzb;
    }

    
    public abstract void zza(Object obj, long j2);

    
    public abstract void zzb(Object obj, Object obj2, long j2);
}
