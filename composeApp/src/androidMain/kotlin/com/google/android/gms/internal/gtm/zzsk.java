package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzsk implements zzach {
    INT52(0),
    NUMBER(1),
    STRING(2);
    
    private static final zzaci zzd = null;
    private final int zzf;

    static {
        zzd = new zzsj();
    }

    zzsk(int i2) {
        this.zzf = i2;
    }

    public static zzaci zzb() {
        return zzd;
    }

    public final String toString() {
        return Integer.toString(this.zzf);
    }

    public final int zza() {
        return this.zzf;
    }
}
