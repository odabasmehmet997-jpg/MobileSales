package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzahl implements zzach {
    TOMBSTONE_UNSPECIFIED(0),
    TOMBSTONE_RETAIN(1),
    TOMBSTONE_DROP(2);
    
    private static final zzaci zzd = null;
    private final int zzf;

    static {
        zzd = new zzahk();
    }

    zzahl(final int i2) {
        zzf = i2;
    }

    public static zzaci zzb() {
        return zzahl.zzd;
    }

    public final String toString() {
        return Integer.toString(zzf);
    }

    public final int zza() {
        return zzf;
    }
}
