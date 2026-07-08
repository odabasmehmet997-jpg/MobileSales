package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzago implements zzach {
    UNKNOWN(0),
    SUCCESS(1),
    FAILURE(2),
    CANCEL(3);
    
    private static final zzaci zze = null;
    private final int zzg;

    static {
        zze = new zzagn();
    }

    zzago(int i2) {
        this.zzg = i2;
    }

    public static zzaci zzb() {
        return zze;
    }

    public final String toString() {
        return Integer.toString(this.zzg);
    }

    public final int zza() {
        return this.zzg;
    }
}
