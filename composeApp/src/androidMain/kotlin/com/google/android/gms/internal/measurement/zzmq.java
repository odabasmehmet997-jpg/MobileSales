package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmq extends zzmr {
    zzmq(final Unsafe unsafe) {
        super(unsafe);
    }

    public double zza(final Object obj, final long j2) {
        return Double.longBitsToDouble(this.zzk(obj, j2));
    }

    public float zzb(final Object obj, final long j2) {
        return Float.intBitsToFloat(this.zzj(obj, j2));
    }

    public void zzc(final Object obj, final long j2, final boolean z) {
        if (zzms.zzb) {
            zzms.zzD(obj, j2, r3 ? (byte) 1 : 0);
        } else {
            zzms.zzE(obj, j2, r3 ? (byte) 1 : 0);
        }
    }

    public void zzd(final Object obj, final long j2, final byte b2) {
        if (zzms.zzb) {
            zzms.zzD(obj, j2, b2);
        } else {
            zzms.zzE(obj, j2, b2);
        }
    }

    public void zze(final Object obj, final long j2, final double d2) {
        this.zzo(obj, j2, Double.doubleToLongBits(d2));
    }

    public void zzf(final Object obj, final long j2, final float f2) {
        this.zzn(obj, j2, Float.floatToIntBits(f2));
    }

    public boolean zzg(final Object obj, final long j2) {
        if (zzms.zzb) {
            return zzms.zzt(obj, j2);
        }
        return zzms.zzu(obj, j2);
    }
}
