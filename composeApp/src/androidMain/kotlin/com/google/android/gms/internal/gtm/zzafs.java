package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzafs implements zzach {
    DF_NONE(0),
    DF_HTTPHEADER(1),
    DF_COOKIE(2),
    DF_URL(3),
    DF_CGI_ARGS(4),
    DF_HOST_ORDER(5),
    DF_BYTE_SWAPPED(6),
    DF_LOGGING_ELEMENT_TYPE_ID(7);
    
    private static final zzaci zzi = null;
    private final int zzk;

    static {
        zzi = new zzafr();
    }

    zzafs(final int i2) {
        zzk = i2;
    }

    public static zzaci zzb() {
        return zzafs.zzi;
    }

    public final String toString() {
        return Integer.toString(zzk);
    }

    public final int zza() {
        return zzk;
    }
}
