package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzoy implements zzox {
    public static final zzhu zza;
    public static final zzhu zzb;
    public static final zzhu zzc;
    public static final zzhu zzd;
    public static final zzhu zze;

    static {
        final zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.test.boolean_flag", false);
        zzb = zzhr.zzb("measurement.test.double_flag", -3.0d);
        zzc = zzhr.zzc("measurement.test.int_flag", -2);
        zzd = zzhr.zzc("measurement.test.long_flag", -1);
        zze = zzhr.zzd("measurement.test.string_flag", "---");
    }

    public double zza() {
        return ((Double) zzoy.zzb.zzb()).doubleValue();
    }

    public long zzb() {
        return ((Long) zzoy.zzc.zzb()).longValue();
    }

    public long zzc() {
        return ((Long) zzoy.zzd.zzb()).longValue();
    }

    public String zzd() {
        return (String) zzoy.zze.zzb();
    }

    public boolean zze() {
        return ((Boolean) zzoy.zza.zzb()).booleanValue();
    }
}
