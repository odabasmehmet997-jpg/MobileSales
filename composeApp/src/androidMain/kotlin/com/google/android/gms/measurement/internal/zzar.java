package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzar implements Iterator {
    final Iterator zza;
    final zzas zzb;

    zzar(zzas zzas) {
        this.zzb = zzas;
        this.zza = zzas.zza.keySet().iterator();
    }

    public boolean hasNext() {
        return this.zza.hasNext();
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /* renamed from: zza */
    public String next() {
        return (String) this.zza.next();
    }
}
