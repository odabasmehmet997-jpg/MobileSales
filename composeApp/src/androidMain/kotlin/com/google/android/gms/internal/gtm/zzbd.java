package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.firebase.messaging.Constants;
import java.util.HashMap;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbd extends zzj {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private boolean zze;
    private boolean zzf;

    public String toString() {
        final HashMap hashMap = new HashMap();
        hashMap.put("hitType", zza);
        hashMap.put("clientId", zzb);
        hashMap.put("userId", zzc);
        hashMap.put("androidAdId", zzd);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(zze));
        hashMap.put("sessionControl", null);
        hashMap.put("nonInteraction", Boolean.valueOf(zzf));
        hashMap.put("sampleRate", Double.valueOf(0.0d));
        return zza(hashMap);
    }

    public void zzc(final zzj zzj) {
        final zzbd zzbd = (zzbd) zzj;
        if (!TextUtils.isEmpty(zza)) {
            zzbd.zza = zza;
        }
        if (!TextUtils.isEmpty(zzb)) {
            zzbd.zzb = zzb;
        }
        if (!TextUtils.isEmpty(zzc)) {
            zzbd.zzc = zzc;
        }
        if (!TextUtils.isEmpty(zzd)) {
            zzbd.zzd = zzd;
        }
        if (zze) {
            zzbd.zze = true;
        }
        TextUtils.isEmpty(null);
        if (zzf) {
            zzbd.zzf = true;
        }
    }

    public String zzd() {
        return zzd;
    }

    public String zze() {
        return zzb;
    }

    public String zzf() {
        return zza;
    }

    public String zzg() {
        return zzc;
    }

    public void zzh(final boolean z) {
        zze = z;
    }

    public void zzi(final String str) {
        zzd = str;
    }

    public void zzj(final String str) {
        zzb = str;
    }

    public void zzk(final String str) {
        zza = Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
    }

    public void zzl(final boolean z) {
        zzf = true;
    }

    public void zzm(final String str) {
        zzc = str;
    }

    public boolean zzn() {
        return zze;
    }

    public boolean zzo() {
        return zzf;
    }
}
