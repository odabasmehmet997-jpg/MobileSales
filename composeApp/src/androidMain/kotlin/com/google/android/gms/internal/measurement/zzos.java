package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzos implements zzor {
    public static final zzhu zza;
    public static final zzhu zzb;
    public static final zzhu zzc;
    public static final zzhu zzd;

    static {
        final zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zzc("measurement.id.lifecycle.app_in_background_parameter", 0);
        zzb = zzhr.zze("measurement.lifecycle.app_backgrounded_tracking", true);
        zzc = zzhr.zze("measurement.lifecycle.app_in_background_parameter", false);
        zzd = zzhr.zzc("measurement.id.lifecycle.app_backgrounded_tracking", 0);
    }

    public boolean zza() {
        return ((Boolean) zzos.zzc.zzb()).booleanValue();
    }
}
