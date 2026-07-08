package com.google.android.gms.internal.gtm;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzvv implements Comparator, Serializable {
    zzvv() {
    }

    public int compare(final Object obj, final Object obj2) {
        final zzwb zza = zzwb.zza(obj);
        final zzwb zza2 = zzwb.zza(obj2);
        if (zza != zza2) {
            return zza.compareTo(zza2);
        }
        final int ordinal = zza.ordinal();
        if (0 == ordinal) {
            return ((Boolean) obj).compareTo((Boolean) obj2);
        }
        if (1 == ordinal) {
            return ((String) obj).compareTo((String) obj2);
        }
        if (2 == ordinal) {
            return ((Long) obj).compareTo((Long) obj2);
        }
        if (3 == ordinal) {
            return ((Double) obj).compareTo((Double) obj2);
        }
        throw null;
    }
}
