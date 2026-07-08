package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzlb {
    zzlb() {
    }

    public static int zza(final int i2, final Object obj, final Object obj2) {
        final zzla zzla = (zzla) obj;
        final zzkz zzkz = (zzkz) obj2;
        if (zzla.isEmpty()) {
            return 0;
        }
        final Iterator it = zzla.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        final Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static Object zzb(final Object obj, final Object obj2) {
        zzla zzla = (zzla) obj;
        final zzla zzla2 = (zzla) obj2;
        if (!zzla2.isEmpty()) {
            if (!zzla.zze()) {
                zzla = zzla.zzb();
            }
            zzla.zzd(zzla2);
        }
        return zzla;
    }
}
