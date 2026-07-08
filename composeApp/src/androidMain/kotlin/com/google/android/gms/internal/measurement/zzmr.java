package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
abstract class zzmr {
    final Unsafe zza;

    zzmr(final Unsafe unsafe) {
        zza = unsafe;
    }

    public abstract double zza(Object obj, long j2);

    public abstract float zzb(Object obj, long j2);

    public abstract void zzc(Object obj, long j2, boolean z);

    public abstract void zzd(Object obj, long j2, byte b2);

    public abstract void zze(Object obj, long j2, double d2);

    public abstract void zzf(Object obj, long j2, float f2);

    public abstract boolean zzg(Object obj, long j2);

    public final int zzh(final Class cls) {
        return zza.arrayBaseOffset(cls);
    }

    public final int zzi(final Class cls) {
        return zza.arrayIndexScale(cls);
    }

    public final int zzj(final Object obj, final long j2) {
        return zza.getInt(obj, j2);
    }

    public final long zzk(final Object obj, final long j2) {
        return zza.getLong(obj, j2);
    }

    public final long zzl(final Field field) {
        return zza.objectFieldOffset(field);
    }

    public final Object zzm(final Object obj, final long j2) {
        return zza.getObject(obj, j2);
    }

    public final void zzn(final Object obj, final long j2, final int i2) {
        zza.putInt(obj, j2, i2);
    }

    public final void zzo(final Object obj, final long j2, final long j3) {
        zza.putLong(obj, j2, j3);
    }

    public final void zzp(final Object obj, final long j2, final Object obj2) {
        zza.putObject(obj, j2, obj2);
    }
}
