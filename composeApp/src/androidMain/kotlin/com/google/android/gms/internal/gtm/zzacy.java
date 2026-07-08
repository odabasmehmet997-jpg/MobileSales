package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzacy {
    zzacy() {
    }

    public static List zza(final Object obj, final long j2) {
        final zzacn zzacn = (zzacn) zzaet.zzf(obj, j2);
        if (zzacn.zzc()) {
            return zzacn;
        }
        final int size = zzacn.size();
        final zzacn zzd = zzacn.zzd(0 == size ? 10 : size + size);
        zzaet.zzs(obj, j2, zzd);
        return zzd;
    }
}
