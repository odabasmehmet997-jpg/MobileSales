package com.google.android.gms.internal.maps;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public enum zzbv {
    ;

    static int zza(final Set set) {
        final Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            final Object next = it.next();
            i2 += null != next ? next.hashCode() : 0;
        }
        return i2;
    }
}
