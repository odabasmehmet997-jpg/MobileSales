package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzen {
    private final zzdn zza;
    private final zzap zzb;

    public zzen(zzdn zzdn, zzap zzap) {
        this.zza = zzdn;
        this.zzb = zzap;
    }

    public int zza() {
        int i2;
        int zzY = ((zzap) this.zza.zza()).zzY();
        zzap zzap = this.zzb;
        if (zzap == null) {
            i2 = 0;
        } else {
            i2 = zzap.zzY();
        }
        return zzY + i2;
    }

    public zzap zzb() {
        return this.zzb;
    }

    public zzdn zzc() {
        return this.zza;
    }
}
