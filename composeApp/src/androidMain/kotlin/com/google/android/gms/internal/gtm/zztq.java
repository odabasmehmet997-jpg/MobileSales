package com.google.android.gms.internal.gtm;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztq extends zzua {
    private final Object zza;
    private boolean zzb;

    zztq(Object obj) {
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
