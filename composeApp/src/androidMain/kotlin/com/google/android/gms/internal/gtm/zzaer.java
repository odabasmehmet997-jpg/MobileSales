package com.google.android.gms.internal.gtm;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaer extends zzaes {
    zzaer(final Unsafe unsafe) {
        super(unsafe);
    }

    public double zza(final Object obj, final long j2) {
        return Double.longBitsToDouble(zza.getLong(obj, j2));
    }

    public float zzb(final Object obj, final long j2) {
        return Float.intBitsToFloat(zza.getInt(obj, j2));
    }

    public void zzc(final Object obj, final long j2, final boolean z) {
        if (zzaet.zzb) {
            zzaet.zzD(obj, j2, r3 ? (byte) 1 : 0);
        } else {
            zzaet.zzE(obj, j2, r3 ? (byte) 1 : 0);
        }
    }

    public void zzd(final Object obj, final long j2, final byte b2) {
        if (zzaet.zzb) {
            zzaet.zzD(obj, j2, b2);
        } else {
            zzaet.zzE(obj, j2, b2);
        }
    }

    public void zze(final Object obj, final long j2, final double d2) {
        zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    public void zzf(final Object obj, final long j2, final float f2) {
        zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    public boolean zzg(final Object obj, final long j2) {
        if (zzaet.zzb) {
            return zzaet.zzt(obj, j2);
        }
        return zzaet.zzu(obj, j2);
    }
}
