package com.google.android.gms.internal.gtm;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
abstract class zzaes {
    final Unsafe zza;

    zzaes(final Unsafe unsafe) {
        zza = unsafe;
    }

    public abstract double zza(Object obj, long j2);

    public abstract float zzb(Object obj, long j2);

    public abstract void zzc(Object obj, long j2, boolean z);

    public abstract void zzd(Object obj, long j2, byte b2);

    public abstract void zze(Object obj, long j2, double d2);

    public abstract void zzf(Object obj, long j2, float f2);

    public abstract boolean zzg(Object obj, long j2);
}
