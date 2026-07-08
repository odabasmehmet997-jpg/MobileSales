package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadg {
    zzadg() {
    }

    public static boolean zza(final Object obj) {
        return !((zzadf) obj).zze();
    }

    public static Object zzb(final Object obj, final Object obj2) {
        zzadf zzadf = (zzadf) obj;
        final zzadf zzadf2 = (zzadf) obj2;
        if (!zzadf2.isEmpty()) {
            if (!zzadf.zze()) {
                zzadf = zzadf.zzb();
            }
            zzadf.zzd(zzadf2);
        }
        return zzadf;
    }
}
