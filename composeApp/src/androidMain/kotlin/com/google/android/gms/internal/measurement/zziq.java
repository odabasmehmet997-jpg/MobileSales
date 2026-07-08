package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zziq implements Comparator, Serializable {
    zziq() {
    }

    public int compare(Object obj, Object obj2) {
        zziy zziy = (zziy) obj;
        zziy zziy2 = (zziy) obj2;
        zzio zzio = new zzio(zziy);
        zzio zzio2 = new zzio(zziy2);
        while (zzio.hasNext() && zzio2.hasNext()) {
            int zza = zzip.zza(zzio.zza() & 255, zzio2.zza() & 255);
            if (0 != zza) {
                return zza;
            }
        }
        return zzip.zza(zziy.zzd(), zziy2.zzd());
    }
}
