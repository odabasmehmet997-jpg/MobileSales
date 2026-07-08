package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzac implements Iterator {
    final Iterator zza;
    final Iterator zzb;

    zzac(zzae zzae, Iterator it, Iterator it2) {
        this.zza = it;
        this.zzb = it2;
    }

    public boolean hasNext() {
        if (this.zza.hasNext()) {
            return true;
        }
        return this.zzb.hasNext();
    }

    public Object next() {
        if (this.zza.hasNext()) {
            return new zzat(this.zza.next().toString());
        }
        if (this.zzb.hasNext()) {
            return new zzat((String) this.zzb.next());
        }
        throw new NoSuchElementException();
    }
}
