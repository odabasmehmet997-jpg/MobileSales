package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzas implements Iterator {
    final zzat zza;
    private int zzb;

    zzas(final zzat zzat) {
        zza = zzat;
    }

    public boolean hasNext() {
        return zzb < zza.zza.length();
    }

    public Object next() {
        final int i2 = zzb;
        final zzat zzat = zza;
        if (i2 < zzat.zza.length()) {
            final String zzb2 = zzat.zza;
            zzb = i2 + 1;
            return new zzat(String.valueOf(zzb2.charAt(i2)));
        }
        throw new NoSuchElementException();
    }
}
