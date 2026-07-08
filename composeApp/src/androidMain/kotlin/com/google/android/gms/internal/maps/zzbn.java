package com.google.android.gms.internal.maps;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbn extends zzbx {
    private final Object zza;
    private boolean zzb;

    zzbn(Object obj) {
        this.zza = obj;
    }

    public boolean hasNext() {
        return !this.zzb;
    }

    public Object next() {
        if (!this.zzb) {
            this.zzb = true;
            return this.zza;
        }
        throw new NoSuchElementException();
    }
}
