package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzop implements zzoo {
    public static final zzhu zza;
    public static final zzhu zzb;
    public static final zzhu zzc;
    public static final zzhu zzd;

    static {
        final zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzhr.zze("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zzhr.zze("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzhr.zzc("measurement.id.sdk.collection.last_deep_link_referrer2", 0);
    }

    public boolean zza() {
        return ((Boolean) zzop.zzc.zzb()).booleanValue();
    }
}
