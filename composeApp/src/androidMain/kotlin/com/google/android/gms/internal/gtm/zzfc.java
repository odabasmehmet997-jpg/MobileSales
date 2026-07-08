package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzfc extends zzbq implements zzcq {
    private final zzfe zza = new zzfe();

    public zzfc(zzbu zzbu) {
        super(zzbu);
    }

    public zzcp zza() {
        return this.zza;
    }

    public void zzb(String str, String str2) {
        this.zza.zzg.put(str, str2);
    }

    public void zzc(String str, boolean z) {
        if ("ga_autoActivityTracking".equals(str)) {
            this.zza.zzd = z;
        } else if ("ga_anonymizeIp".equals(str)) {
            this.zza.zze = z;
        } else if ("ga_reportUncaughtExceptions".equals(str)) {
            this.zza.zzf = z ? 1 : 0;
        } else {
            zzR("bool configuration name not recognized", str);
        }
    }

    public void zzd(String str, int i2) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zza.zzc = i2;
        } else {
            zzR("int configuration name not recognized", str);
        }
    }

    public void zze(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zza.zza = str2;
        } else if ("ga_sampleFrequency".equals(str)) {
            try {
                this.zza.zzb = Double.parseDouble(str2);
            } catch (NumberFormatException e2) {
                zzS("Error parsing ga_sampleFrequency value", str2, e2);
            }
        } else {
            zzR("string configuration name not recognized", str);
        }
    }
}
