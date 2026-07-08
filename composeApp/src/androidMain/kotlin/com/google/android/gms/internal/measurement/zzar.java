package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzar implements Iterator {
    final zzat zza;
    private int zzb;

    zzar(final zzat zzat) {
        zza = zzat;
    }

    public boolean hasNext() {
        return zzb < zza.zza.length();
    }

    public Object next() {
        final int i2 = zzb;
        if (i2 < zza.zza.length()) {
            zzb = i2 + 1;
            return new zzat(String.valueOf(i2));
        }
        throw new NoSuchElementException();
    }
}
