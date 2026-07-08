package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzvy implements Iterator {
    final zzvz zza;
    private int zzb;

    zzvy(zzvz zzvz) {
        this.zza = zzvz;
    }

    public boolean hasNext() {
        int i2 = this.zzb;
        zzvz zzvz = this.zza;
        return i2 < zzvz.zza() - zzvz.zzb();
    }

    public Object next() {
        int i2 = this.zzb;
        zzvz zzvz = this.zza;
        if (i2 < zzvz.zza() - zzvz.zzb()) {
            zzvz zzvz2 = this.zza;
            Object obj = zzvz2.zzb.zzb[zzvz2.zzb() + i2];
            this.zzb = i2 + 1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
