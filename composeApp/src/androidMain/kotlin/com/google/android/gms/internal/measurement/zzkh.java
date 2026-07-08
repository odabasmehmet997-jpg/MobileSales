package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public enum zzkh {
    ;
    static final Charset zza = StandardCharsets.US_ASCII;
    static final Charset zzb = StandardCharsets.UTF_8;
    static final Charset zzc = StandardCharsets.ISO_8859_1;
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzjc zzf;

    static {
        final byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        final int i2 = zzjc.r8clinit;
        final zzja zzja = new zzja(bArr, 0, 0, false, null);
        try {
            zzja.zza(0);
            zzf = zzja;
        } catch (final zzkj e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int zza(final boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(final byte[] bArr) {
        final int length = bArr.length;
        final int zzd2 = zzkh.zzd(length, bArr, 0, length);
        if (0 == zzd2) {
            return 1;
        }
        return zzd2;
    }

    public static int zzc(final long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    static int zzd(int i2, final byte[] bArr, final int i3, final int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    static Object zze(final Object obj) {
        obj.getClass();
        return obj;
    }

    static Object zzf(final Object obj, final String str) {
        if (null != obj) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    static Object zzg(final Object obj, final Object obj2) {
        return ((zzlg) obj).zzbB().zzas((zzlg) obj2).zzaA();
    }

    public static String zzh(final byte[] bArr) {
        return new String(bArr, zzkh.zzb);
    }

    public static boolean zzi(final byte[] bArr) {
        return zzmx.zze(bArr);
    }
}
