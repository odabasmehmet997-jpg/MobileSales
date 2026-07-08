package com.google.android.gms.internal.gtm;

import libcore.io.Memory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;





    private static int zzA(final Class cls) {
        if (zzaet.zzh) {
            return zzaet.zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        final int i2 = zzyk.r8clinit;
        final Class<Buffer> cls = Buffer.class;
        final Field zzC = zzaet.zzC(cls, "effectiveDirectAddress");
        if (null != zzC) {
            return zzC;
        }
        final Field zzC2 = zzaet.zzC(cls, "address");
        if (null == zzC2 || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(final Class cls, final String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (final Throwable unused) {
            return null;
        }
    }

    
    public static void zzD(final Object obj, final long j2, final byte b2) {
        final zzaes zzaes = zzaet.zzf;
        final long j3 = -4 & j2;
        final int i2 = zzaes.zza.getInt(obj, j3);
        final int i3 = ((~((int) j2)) & 3) << 3;
        zzaes.zza.putInt(obj, j3, ((255 & b2) << i3) | (i2 & (~(255 << i3))));
    }

    
    public static void zzE(final Object obj, final long j2, final byte b2) {
        final zzaes zzaes = zzaet.zzf;
        final long j3 = -4 & j2;
        final int i2 = (((int) j2) & 3) << 3;
        zzaes.zza.putInt(obj, j3, ((255 & b2) << i2) | (zzaes.zza.getInt(obj, j3) & (~(255 << i2))));
    }

    static double zza(final Object obj, final long j2) {
        return zzaet.zzf.zza(obj, j2);
    }

    static float zzb(final Object obj, final long j2) {
        return zzaet.zzf.zzb(obj, j2);
    }

    static int zzc(final Object obj, final long j2) {
        return zzaet.zzf.zza.getInt(obj, j2);
    }

    static long zzd(final Object obj, final long j2) {
        return zzaet.zzf.zza.getLong(obj, j2);
    }

    static Object zze(final Class cls) {
        try {
            return zzaet.zzc.allocateInstance(cls);
        } catch (final InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    static Object zzf(final Object obj, final long j2) {
        return zzaet.zzf.zza.getObject(obj, j2);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzaep());
        } catch (final Throwable unused) {
            return null;
        }
    }

    static void zzm(final Object obj, final long j2, final boolean z) {
        zzaet.zzf.zzc(obj, j2, z);
    }

    static void zzn(final byte[] bArr, final long j2, final byte b2) {
        zzaet.zzf.zzd(bArr, zzaet.zza + j2, b2);
    }

    static void zzo(final Object obj, final long j2, final double d2) {
        zzaet.zzf.zze(obj, j2, d2);
    }

    static void zzp(final Object obj, final long j2, final float f2) {
        zzaet.zzf.zzf(obj, j2, f2);
    }

    static void zzq(final Object obj, final long j2, final int i2) {
        zzaet.zzf.zza.putInt(obj, j2, i2);
    }

    static void zzr(final Object obj, final long j2, final long j3) {
        zzaet.zzf.zza.putLong(obj, j2, j3);
    }

    static void zzs(final Object obj, final long j2, final Object obj2) {
        zzaet.zzf.zza.putObject(obj, j2, obj2);
    }

    static boolean zzt(final Object obj, final long j2) {
        return 0 != ((byte) ((zzf.zza.getInt(obj, -4 & j2) >>> ((int) (((~j2) & 3) << 3))) & 255));
    }

    static boolean zzu(final Object obj, final long j2) {
        return 0 != ((byte) ((zzf.zza.getInt(obj, -4 & j2) >>> ((int) ((j2 & 3) << 3))) & 255));
    }

    static boolean zzv(final Class cls) {
        final Class<byte[]> cls2 = byte[].class;
        final int i2 = zzyk.r8clinit;
        try {
            final Class cls3 = zzaet.zzd;
            final Class cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", cls, cls4);
            cls3.getMethod("pokeLong", cls, Long.TYPE, cls4);
            final Class cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", cls, cls5, cls4);
            cls3.getMethod("peekInt", cls, cls4);
            cls3.getMethod("pokeByte", cls, Byte.TYPE);
            cls3.getMethod("peekByte", cls);
            cls3.getMethod("pokeByteArray", cls, cls2, cls5, cls5);
            cls3.getMethod("peekByteArray", cls, cls2, cls5, cls5);
            return true;
        } catch (final Throwable unused) {
            return false;
        }
    }

    static boolean zzw(final Object obj, final long j2) {
        return zzaet.zzf.zzg(obj, j2);
    }

    static boolean zzx() {
        return zzaet.zzh;
    }

    static boolean zzy() {
        return zzaet.zzg;
    }

    private static int zzz(final Class cls) {
        if (zzaet.zzh) {
            return zzaet.zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
