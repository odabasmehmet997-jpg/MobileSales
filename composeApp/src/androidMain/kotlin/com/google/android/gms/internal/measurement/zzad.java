package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzad implements Iterator {
    final zzae zza;
    private int zzb;

    zzad(zzae zzae) {
        this.zza = zzae;
    }

    public boolean hasNext() {
        return this.zzb < this.zza.zzc();
    }

    public Object next() {
        if (this.zzb < this.zza.zzc()) {
            zzae zzae = this.zza;
            int i2 = this.zzb;
            this.zzb = i2 + 1;
            return zzae.zze(i2);
        }
        int i3 = this.zzb;
        final String sb = "Out of bounds index: " +
                i3;
        throw new NoSuchElementException(sb);
    }
}
