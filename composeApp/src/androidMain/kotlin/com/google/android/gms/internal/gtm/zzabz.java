package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabz implements zzadj {
    private static final zzabz zza = new zzabz();

    private zzabz() {
    }

    public static zzabz zza() {
        return zzabz.zza;
    }

    public zzadi zzb(final Class cls) {
        final Class<zzacf> cls2 = zzacf.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzadi) zzacf.zzad(cls.asSubclass(cls2)).zzb(3, null, null);
            } catch (final Exception e2) {
                throw new RuntimeException("Unable to get message info for " + cls.getName(), e2);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
    }

    public boolean zzc(final Class cls) {
        return zzacf.class.isAssignableFrom(cls);
    }
}
