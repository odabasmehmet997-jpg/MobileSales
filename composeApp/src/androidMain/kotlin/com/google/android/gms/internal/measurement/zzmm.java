package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmm implements Iterator {
    final Iterator zza;
    final zzmn zzb;

    zzmm(zzmn zzmn) {
        this.zzb = zzmn;
        this.zza = zzmn.zza.iterator();
    }

    public boolean hasNext() {
        return this.zza.hasNext();
    }

    public Object next() {
        return this.zza.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
