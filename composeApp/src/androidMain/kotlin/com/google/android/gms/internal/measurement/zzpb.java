package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzpb implements zzpa {
    public static final zzhu zza;
    public static final zzhu zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.module.pixie.ees", true);
        zzb = zzhr.zze("measurement.module.pixie.fix_array", true);
    }

    public boolean zza() {
        return true;
    }

    public boolean zzb() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }
}
