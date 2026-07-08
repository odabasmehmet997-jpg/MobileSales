package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzov implements zzou {
    public static final zzhu zza;
    public static final zzhu zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.validation.internal_limits_internal_event_params", true);
        zzb = zzhr.zzc("measurement.id.validation.internal_limits_internal_event_params", 0);
    }

    public boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
