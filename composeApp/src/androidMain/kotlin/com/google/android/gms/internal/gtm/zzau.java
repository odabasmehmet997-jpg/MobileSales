package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzau extends zzj {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("appName", this.zza);
        hashMap.put("appVersion", this.zzb);
        hashMap.put("appId", this.zzc);
        hashMap.put("appInstallerId", this.zzd);
        return zzj.zza(hashMap);
    }

    public String zzd() {
        return this.zzc;
    }

    public String zze() {
        return this.zzd;
    }

    public String zzf() {
        return this.zza;
    }

    public String zzg() {
        return this.zzb;
    }

    /* renamed from: zzh */
    public void zzc(zzau zzau) {
        if (!TextUtils.isEmpty(this.zza)) {
            zzau.zza = this.zza;
        }
        if (!TextUtils.isEmpty(this.zzb)) {
            zzau.zzb = this.zzb;
        }
        if (!TextUtils.isEmpty(this.zzc)) {
            zzau.zzc = this.zzc;
        }
        if (!TextUtils.isEmpty(this.zzd)) {
            zzau.zzd = this.zzd;
        }
    }

    public void zzi(String str) {
        this.zzc = str;
    }

    public void zzj(String str) {
        this.zzd = str;
    }

    public void zzk(String str) {
        this.zza = str;
    }

    public void zzl(String str) {
        this.zzb = str;
    }
}
