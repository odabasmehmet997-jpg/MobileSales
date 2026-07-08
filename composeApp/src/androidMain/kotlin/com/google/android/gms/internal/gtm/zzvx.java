package com.google.android.gms.internal.gtm;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzvx implements Comparator, Serializable {
    zzvx() {
    }

    public int compare(Object obj, Object obj2) {
        return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
    }
}
