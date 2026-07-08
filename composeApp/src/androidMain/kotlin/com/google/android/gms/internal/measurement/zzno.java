package com.google.android.gms.internal.measurement;

import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.webkit.ProxyConfig;
import androidx.work.WorkRequest;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzno implements zznn {
    public static final zzhu zzA;
    public static final zzhu zzB;
    public static final zzhu zzC;
    public static final zzhu zzD;
    public static final zzhu zzE;
    public static final zzhu zzF;
    public static final zzhu zzG;
    public static final zzhu zzH;
    public static final zzhu zzI;
    public static final zzhu zzJ;
    public static final zzhu zzK;
    public static final zzhu zzL;
    public static final zzhu zza;
    public static final zzhu zzb;
    public static final zzhu zzc;
    public static final zzhu zzd;
    public static final zzhu zze;
    public static final zzhu zzf;
    public static final zzhu zzg;
    public static final zzhu zzh;
    public static final zzhu zzi;
    public static final zzhu zzj;
    public static final zzhu zzk;
    public static final zzhu zzl;
    public static final zzhu zzm;
    public static final zzhu zzn;
    public static final zzhu zzo;
    public static final zzhu zzp;
    public static final zzhu zzq;
    public static final zzhu zzr;
    public static final zzhu zzs;
    public static final zzhu zzt;
    public static final zzhu zzu;
    public static final zzhu zzv;
    public static final zzhu zzw;
    public static final zzhu zzx;
    public static final zzhu zzy;
    public static final zzhu zzz;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zzc("measurement.ad_id_cache_time", WorkRequest.MIN_BACKOFF_MILLIS);
        zzb = zzhr.zzc("measurement.max_bundles_per_iteration", 100);
        zzc = zzhr.zzc("measurement.config.cache_time", 86400000);
        zzd = zzhr.zzd("measurement.log_tag", "FA");
        zze = zzhr.zzd("measurement.config.url_authority", "app-measurement.com");
        zzf = zzhr.zzd("measurement.config.url_scheme", ProxyConfig.MATCH_HTTPS);
        zzg = zzhr.zzc("measurement.upload.debug_upload_interval", 1000);
        zzh = zzhr.zzc("measurement.lifetimevalue.max_currency_tracked", 4);
        zzi = zzhr.zzc("measurement.store.max_stored_events_per_app", 100000);
        zzj = zzhr.zzc("measurement.experiment.max_ids", 50);
        zzk = zzhr.zzc("measurement.audience.filter_result_max_count", 200);
        zzl = zzhr.zzc("measurement.alarm_manager.minimum_interval", 60000);
        zzm = zzhr.zzc("measurement.upload.minimum_delay", 500);
        zzn = zzhr.zzc("measurement.monitoring.sample_period_millis", 86400000);
        zzo = zzhr.zzc("measurement.upload.realtime_upload_interval", WorkRequest.MIN_BACKOFF_MILLIS);
        zzp = zzhr.zzc("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zzq = zzhr.zzc("measurement.config.cache_time.service", 3600000);
        zzr = zzhr.zzc("measurement.service_client.idle_disconnect_millis", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzs = zzhr.zzd("measurement.log_tag.service", "FA-SVC");
        zzt = zzhr.zzc("measurement.upload.stale_data_deletion_interval", 86400000);
        zzu = zzhr.zzc("measurement.sdk.attribution.cache.ttl", 604800000);
        zzv = zzhr.zzc("measurement.upload.backoff_period", 43200000);
        zzw = zzhr.zzc("measurement.upload.initial_upload_delay_time", 15000);
        zzx = zzhr.zzc("measurement.upload.interval", 3600000);
        zzy = zzhr.zzc("measurement.upload.max_bundle_size", 65536);
        zzz = zzhr.zzc("measurement.upload.max_bundles", 100);
        zzA = zzhr.zzc("measurement.upload.max_conversions_per_day", 500);
        zzB = zzhr.zzc("measurement.upload.max_error_events_per_day", 1000);
        zzC = zzhr.zzc("measurement.upload.max_events_per_bundle", 1000);
        zzD = zzhr.zzc("measurement.upload.max_events_per_day", 100000);
        zzE = zzhr.zzc("measurement.upload.max_public_events_per_day", 50000);
        zzF = zzhr.zzc("measurement.upload.max_queue_time", 2419200000L);
        zzG = zzhr.zzc("measurement.upload.max_realtime_events_per_day", 10);
        zzH = zzhr.zzc("measurement.upload.max_batch_size", 65536);
        zzI = zzhr.zzc("measurement.upload.retry_count", 6);
        zzJ = zzhr.zzc("measurement.upload.retry_time", 1800000);
        zzK = zzhr.zzd("measurement.upload.url", "https://app-measurement.com/a");
        zzL = zzhr.zzc("measurement.upload.window_interval", 3600000);
    }

    public long zzA() {
        return ((Long) zzF.zzb()).longValue();
    }

    public long zzB() {
        return ((Long) zzG.zzb()).longValue();
    }

    public long zzC() {
        return ((Long) zzH.zzb()).longValue();
    }

    public long zzD() {
        return ((Long) zzI.zzb()).longValue();
    }

    public long zzE() {
        return ((Long) zzJ.zzb()).longValue();
    }

    public long zzF() {
        return ((Long) zzL.zzb()).longValue();
    }

    public String zzG() {
        return (String) zze.zzb();
    }

    public String zzH() {
        return (String) zzf.zzb();
    }

    public String zzI() {
        return (String) zzK.zzb();
    }

    public long zza() {
        return ((Long) zza.zzb()).longValue();
    }

    public long zzb() {
        return ((Long) zzb.zzb()).longValue();
    }

    public long zzc() {
        return ((Long) zzc.zzb()).longValue();
    }

    public long zzd() {
        return ((Long) zzg.zzb()).longValue();
    }

    public long zze() {
        return ((Long) zzh.zzb()).longValue();
    }

    public long zzf() {
        return ((Long) zzi.zzb()).longValue();
    }

    public long zzg() {
        return ((Long) zzj.zzb()).longValue();
    }

    public long zzh() {
        return ((Long) zzk.zzb()).longValue();
    }

    public long zzi() {
        return ((Long) zzl.zzb()).longValue();
    }

    public long zzj() {
        return ((Long) zzm.zzb()).longValue();
    }

    public long zzk() {
        return ((Long) zzn.zzb()).longValue();
    }

    public long zzl() {
        return ((Long) zzo.zzb()).longValue();
    }

    public long zzm() {
        return ((Long) zzp.zzb()).longValue();
    }

    public long zzn() {
        return ((Long) zzr.zzb()).longValue();
    }

    public long zzo() {
        return ((Long) zzt.zzb()).longValue();
    }

    public long zzp() {
        return ((Long) zzu.zzb()).longValue();
    }

    public long zzq() {
        return ((Long) zzv.zzb()).longValue();
    }

    public long zzr() {
        return ((Long) zzw.zzb()).longValue();
    }

    public long zzs() {
        return ((Long) zzx.zzb()).longValue();
    }

    public long zzt() {
        return ((Long) zzy.zzb()).longValue();
    }

    public long zzu() {
        return ((Long) zzz.zzb()).longValue();
    }

    public long zzv() {
        return ((Long) zzA.zzb()).longValue();
    }

    public long zzw() {
        return ((Long) zzB.zzb()).longValue();
    }

    public long zzx() {
        return ((Long) zzC.zzb()).longValue();
    }

    public long zzy() {
        return ((Long) zzD.zzb()).longValue();
    }

    public long zzz() {
        return ((Long) zzE.zzb()).longValue();
    }
}
