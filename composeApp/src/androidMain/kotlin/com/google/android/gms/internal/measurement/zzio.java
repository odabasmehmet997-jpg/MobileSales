package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzio extends zzir {
    final zziy zza;
    private int zzb;
    private final int zzc;

    zzio(zziy zziy) {
        this.zza = zziy;
        this.zzc = zziy.zzd();
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
