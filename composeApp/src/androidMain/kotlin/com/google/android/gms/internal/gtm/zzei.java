package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzei implements zzcq {
    private final zzbu zza;
    private final zzej zzb = new zzej();

    public zzei(final zzbu zzbu) {
        zza = zzbu;
    }

    public zzcp zza() {
        return zzb;
    }

    public void zzb(final String str, final String str2) {
    }

    public void zzc(final String str, final boolean z) {
        if ("ga_dryRun".equals(str)) {
            zzb.zze = z ? 1 : 0;
            return;
        }
        zza.zzm().zzR("Bool xml configuration name not recognized", str);
    }

    public void zzd(final String str, final int i2) {
        if ("ga_dispatchPeriod".equals(str)) {
            zzb.zzd = i2;
        } else {
            zza.zzm().zzR("Int xml configuration name not recognized", str);
        }
    }

    public void zze(final String str, final String str2) {
        if ("ga_appName".equals(str)) {
            zzb.zza = str2;
        } else if ("ga_appVersion".equals(str)) {
            zzb.zzb = str2;
        } else if ("ga_logLevel".equals(str)) {
            zzb.zzc = str2;
        } else {
            zza.zzm().zzR("String xml configuration name not recognized", str);
        }
    }
}
