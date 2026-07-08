package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zznf implements zzne {
    public static final zzhu zza;
    public static final zzhu zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.androidId.delete_feature", true);
        zzb = zzhr.zze("measurement.log_androidId_enabled", false);
    }

    public boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
