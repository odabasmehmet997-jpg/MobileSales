package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadv implements zzadi {
    private final zzadl zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzadv(final zzadl zzadl, final String str, final Object[] objArr) {
        zza = zzadl;
        zzb = str;
        zzc = objArr;
        final char charAt = str.charAt(0);
        if (55296 > charAt) {
            zzd = charAt;
            return;
        }
        char c2 = charAt & 8191;
        int i2 = 1;
        int i3 = 13;
        while (true) {
            final int i4 = i2 + 1;
            final char charAt2 = str.charAt(i2);
            if (55296 <= charAt2) {
                c2 |= (charAt2 & 8191) << i3;
                i3 += 13;
                i2 = i4;
            } else {
                zzd = c2 | (charAt2 << i3);
                return;
            }
        }
    }

    public zzadl zza() {
        return zza;
    }

    public boolean zzb() {
        return 2 == (this.zzd & 2);
    }

    public int zzc() {
        final int i2 = zzd;
        if (0 != (i2 & 1)) {
            return 1;
        }
        return 4 == (i2 & 4) ? 3 : 2;
    }

    
    public String zzd() {
        return zzb;
    }

    
    public Object[] zze() {
        return zzc;
    }
}
