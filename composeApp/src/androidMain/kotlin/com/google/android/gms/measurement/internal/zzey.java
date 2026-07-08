package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzey extends zzgn {
    @VisibleForTesting
    static final Pair zza = new Pair("", 0L);
    public zzew zzb;
    public final zzeu zzc = new zzeu(this, "first_open_time", 0);
    public final zzeu zzd = new zzeu(this, "app_install_time", 0);
    public final zzex zze = new zzex(this, "app_instance_id", null);
    public final zzeu zzf = new zzeu(this, "session_timeout", 1800000);
    public final zzes zzg = new zzes(this, "start_new_session", true);
    public final zzex zzh = new zzex(this, "non_personalized_ads", null);
    public final zzes zzi = new zzes(this, "allow_remote_dynamite", false);
    public final zzeu zzj = new zzeu(this, "last_pause_time", 0);
    public boolean zzk;
    public final zzes zzl = new zzes(this, "app_backgrounded", false);
    public final zzes zzm = new zzes(this, "deep_link_retrieval_complete", false);
    public final zzeu zzn = new zzeu(this, "deep_link_retrieval_attempts", 0);
    public final zzex zzo = new zzex(this, "firebase_feature_rollouts", null);
    public final zzex zzp = new zzex(this, "deferred_attribution_cache", null);
    public final zzeu zzq = new zzeu(this, "deferred_attribution_cache_timestamp", 0);
    public final zzet zzr = new zzet(this, "default_event_parameters", null);
    private SharedPreferences zzt;
    private String zzu;
    private boolean zzv;
    private long zzw;

    zzey(zzft zzft) {
        super(zzft);
    }

    
    @WorkerThread
    @VisibleForTesting
    public SharedPreferences zza() {
        zzg();
        zzu();
        Preconditions.checkNotNull(this.zzt);
        return this.zzt;
    }

    
    @WorkerThread
    public void zzaA() {
        SharedPreferences sharedPreferences = this.zzs.zzau().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzt = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzk = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzt.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzs.zzf();
        this.zzb = new zzew(this, "health_monitor", Math.max(0, ((Long) zzdw.zzb.zza(null)).longValue()), null);
    }

    
    @WorkerThread
    public Pair zzb(String str) {
        zzg();
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        String str2 = this.zzu;
        if (null != str2 && elapsedRealtime < this.zzw) {
            return new Pair(str2, Boolean.valueOf(this.zzv));
        }
        this.zzw = elapsedRealtime + this.zzs.zzf().zzi(str, zzdw.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
            this.zzu = "";
            String id = advertisingIdInfo.getId();
            if (null != id) {
                this.zzu = id;
            }
            this.zzv = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e2) {
            this.zzs.zzay().zzc().zzb("Unable to get advertising id", e2);
            this.zzu = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzu, Boolean.valueOf(this.zzv));
    }

    
    @WorkerThread
    public zzah zzc() {
        zzg();
        return zzah.zzb(zza().getString("consent_settings", "G1"));
    }

    
    @WorkerThread
    public Boolean zzd() {
        zzg();
        if (zza().contains("measurement_enabled")) {
            return Boolean.valueOf(zza().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    
    public boolean zzf() {
        return true;
    }

    
    @WorkerThread
    public void zzh(Boolean bool) {
        zzg();
        SharedPreferences.Editor edit = zza().edit();
        if (null != bool) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    
    @WorkerThread
    public void zzi(boolean z) {
        zzg();
        this.zzs.zzay().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zza().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    
    @WorkerThread
    public boolean zzj() {
        SharedPreferences sharedPreferences = this.zzt;
        if (null == sharedPreferences) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    
    public boolean zzk(long j2) {
        return j2 - this.zzf.zza() > this.zzj.zza();
    }

    
    @WorkerThread
    public boolean zzl(int i2) {
        return zzah.zzj(i2, zza().getInt("consent_source", 100));
    }
}
