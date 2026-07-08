package com.google.android.gms.internal.gtm;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzr;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzeu extends zzbr {
    
    public SharedPreferences zza;
    private long zzb;
    private long zzc = -1;
    private final zzes zzd;

    zzeu(final zzbu zzbu) {
        super(zzbu);
        this.zzw();
        zzd = new zzes(this, "monitoring", ((Long) zzeh.zzD.zzb()).longValue(), null);
    }

    public long zza() {
        zzr.zzh();
        this.zzV();
        final long j2 = zzb;
        if (0 != j2) {
            return j2;
        }
        final long j3 = zza.getLong("first_run", 0);
        if (0 != j3) {
            zzb = j3;
            return j3;
        }
        final long currentTimeMillis = this.zzC().currentTimeMillis();
        final SharedPreferences.Editor edit = zza.edit();
        edit.putLong("first_run", currentTimeMillis);
        if (!edit.commit()) {
            this.zzQ("Failed to commit first run time");
        }
        zzb = currentTimeMillis;
        return currentTimeMillis;
    }

    public long zzb() {
        zzr.zzh();
        this.zzV();
        final long j2 = zzc;
        if (-1 != j2) {
            return j2;
        }
        final long j3 = zza.getLong("last_dispatch", 0);
        zzc = j3;
        return j3;
    }

    
    public void zzd() {
        zza = this.zzo().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public zzes zze() {
        return zzd;
    }

    public zzfb zzf() {
        return new zzfb(this.zzC(), this.zza());
    }

    public String zzg() {
        zzr.zzh();
        this.zzV();
        final String string = zza.getString("installation_campaign", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public void zzh(final String str) {
        zzr.zzh();
        this.zzV();
        final SharedPreferences.Editor edit = zza.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            this.zzQ("Failed to commit campaign data");
        }
    }

    public void zzi() {
        zzr.zzh();
        this.zzV();
        final long currentTimeMillis = this.zzC().currentTimeMillis();
        final SharedPreferences.Editor edit = zza.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        zzc = currentTimeMillis;
    }
}
