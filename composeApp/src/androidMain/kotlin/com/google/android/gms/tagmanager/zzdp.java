package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
        /* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */ record zzdp(
        zzdr zza) implements zzfc {

    public void zza(zzbv zzbv) {
        long zza2 = zzbv.zza();
        if (zza2 == 0) {
            zzdr.zzi(this.zza, zzbv.zzb(), this.zza.zzg.currentTimeMillis());
        } else if (zza2 + 14400000 < this.zza.zzg.currentTimeMillis()) {
            this.zza.zzl(zzbv.zzb());
            long zzb = zzbv.zzb();
            zzdc.zzb.zzd("Giving up on failed hitId: " + zzb);
        }
    }
}
