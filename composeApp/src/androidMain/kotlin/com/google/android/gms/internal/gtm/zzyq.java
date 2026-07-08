package com.google.android.gms.internal.gtm;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzyq extends zzyr {
    final zzyx zza;
    private int zzb;
    private final int zzc;

    zzyq(zzyx zzyx) {
        this.zza = zzyx;
        this.zzc = zzyx.zzd();
    }

    public boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public byte zza() {
        int i2 = this.zzb;
        if (i2 < this.zzc) {
            this.zzb = i2 + 1;
            return this.zza.zzb(i2);
        }
        throw new NoSuchElementException();
    }
}
