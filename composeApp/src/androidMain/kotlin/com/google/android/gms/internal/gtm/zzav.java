package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzav extends zzj {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.zza);
        hashMap.put("source", this.zzb);
        hashMap.put("medium", this.zzc);
        hashMap.put("keyword", this.zzd);
        hashMap.put(FirebaseAnalytics.Param.CONTENT, this.zze);
        hashMap.put(Name.MARK, this.zzf);
        hashMap.put("adNetworkId", this.zzg);
        hashMap.put("gclid", this.zzh);
        hashMap.put("dclid", this.zzi);
        hashMap.put(FirebaseAnalytics.Param.ACLID, this.zzj);
        return zzj.zza(hashMap);
    }

    public void zzc(zzj zzj2) {
        zzav zzav = (zzav) zzj2;
        if (!TextUtils.isEmpty(this.zza)) {
            zzav.zza = this.zza;
        }
        if (!TextUtils.isEmpty(this.zzb)) {
            zzav.zzb = this.zzb;
        }
        if (!TextUtils.isEmpty(this.zzc)) {
            zzav.zzc = this.zzc;
        }
        if (!TextUtils.isEmpty(this.zzd)) {
            zzav.zzd = this.zzd;
        }
        if (!TextUtils.isEmpty(this.zze)) {
            zzav.zze = this.zze;
        }
        if (!TextUtils.isEmpty(this.zzf)) {
            zzav.zzf = this.zzf;
        }
        if (!TextUtils.isEmpty(this.zzg)) {
            zzav.zzg = this.zzg;
        }
        if (!TextUtils.isEmpty(this.zzh)) {
            zzav.zzh = this.zzh;
        }
        if (!TextUtils.isEmpty(this.zzi)) {
            zzav.zzi = this.zzi;
        }
        if (!TextUtils.isEmpty(this.zzj)) {
            zzav.zzj = this.zzj;
        }
    }

    public String zzd() {
        return this.zzj;
    }

    public String zze() {
        return this.zzg;
    }

    public String zzf() {
        return this.zze;
    }

    public String zzg() {
        return this.zzi;
    }

    public String zzh() {
        return this.zzh;
    }

    public String zzi() {
        return this.zzf;
    }

    public String zzj() {
        return this.zzd;
    }

    public String zzk() {
        return this.zzc;
    }

    public String zzl() {
        return this.zza;
    }

    public String zzm() {
        return this.zzb;
    }

    public void zzn(String str) {
        this.zzj = str;
    }

    public void zzo(String str) {
        this.zzg = str;
    }

    public void zzp(String str) {
        this.zze = str;
    }

    public void zzq(String str) {
        this.zzi = str;
    }

    public void zzr(String str) {
        this.zzh = str;
    }

    public void zzs(String str) {
        this.zzf = str;
    }

    public void zzt(String str) {
        this.zzd = str;
    }

    public void zzu(String str) {
        this.zzc = str;
    }

    public void zzv(String str) {
        this.zza = str;
    }

    public void zzw(String str) {
        this.zzb = str;
    }
}
