package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzlq implements zzld {
    private final zzlg zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzlq(zzlg zzlg, String str, Object[] objArr) {
        this.zza = zzlg;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (55296 > charAt) {
            this.zzd = charAt;
            return;
        }
        char c2 = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (55296 <= charAt2) {
                c2 |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.zzd = c2 | (charAt2 << i2);
                return;
            }
        }
    }

    public zzlg zza() {
        return this.zza;
    }

    public boolean zzb() {
        return 2 == (zzd & 2);
    }

    public int zzc() {
        return 1 == (zzd & 1) ? 1 : 2;
    }

    
    public String zzd() {
        return this.zzb;
    }

    
    public Object[] zze() {
        return this.zzc;
    }
}
