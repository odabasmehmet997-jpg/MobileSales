package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzty {
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
