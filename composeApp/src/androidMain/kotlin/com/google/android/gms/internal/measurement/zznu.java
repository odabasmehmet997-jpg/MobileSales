package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zznu implements zznt {
    public static final zzhu zza;
    public static final zzhu zzb;
    public static final zzhu zzc;
    public static final zzhu zzd;
    public static final zzhu zze;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zza();
        zza = zza2.zze("measurement.enhanced_campaign.client", false);
        zzb = zza2.zze("measurement.enhanced_campaign.service", false);
        zzc = zza2.zze("measurement.enhanced_campaign.srsltid.client", false);
        zzd = zza2.zze("measurement.enhanced_campaign.srsltid.service", false);
        zze = zza2.zzc("measurement.id.enhanced_campaign.service", 0);
    }

    public boolean zza() {
        return true;
    }

    public boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    public boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }

    public boolean zzd() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }

    public boolean zze() {
        return ((Boolean) zzd.zzb()).booleanValue();
    }
}
