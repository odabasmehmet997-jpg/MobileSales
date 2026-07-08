package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzaeu {
    ;

    static void zza(final byte b2, final byte b3, final byte b4, final byte b5, final char[] cArr, final int i2) {
        if (zzaeu.zze(b3) || 0 != (((b2 << 28) + (b3 + 112)) >> 30) || zzaeu.zze(b4) || zzaeu.zze(b5)) {
            throw new zzacq("Protocol message had invalid UTF-8.");
        }
        final byte b6 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
        cArr[i2] = (char) ((b6 >>> 10) + 55232);
        cArr[i2 + 1] = (char) ((b6 & 1023) + 56320);
    }

    static void zzc(final byte b2, final byte b3, final char[] cArr, final int i2) {
        if (-62 > b2 || zzaeu.zze(b3)) {
            throw new zzacq("Protocol message had invalid UTF-8.");
        }
        cArr[i2] = (char) (((b2 & 31) << 6) | (b3 & 63));
    }

    static boolean zzd(final byte b2) {
        return 0 <= b2;
    }

    private static boolean zze(final byte b2) {
        return -65 < b2;
    }

    static void zzb(byte b2, final byte b3, final byte b4, final char[] cArr, final int i2) {
        if (!zzaeu.zze(b3)) {
            if (-32 == b2) {
                if (-96 <= b3) {
                    b2 = -32;
                }
            }
            if (-19 == b2) {
                if (-96 > b3) {
                    b2 = -19;
                }
            }
            if (!zzaeu.zze(b4)) {
                cArr[i2] = (char) (((b2 & 15) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                return;
            }
        }
        throw new zzacq("Protocol message had invalid UTF-8.");
    }
}
