package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzak(Iterator zza) implements Iterator {

    public boolean hasNext() {
        return this.zza.hasNext();
    }

    public Object next() {
        return new zzat((String) this.zza.next());
    }
}
