package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.webkit.ProxyConfig;
import androidx.work.WorkRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzha;
import com.google.android.gms.internal.measurement.zzhk;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;

import java.util.*;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public enum zzdw {
    ;
    public static final zzdv zzA = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbg.zza);
    public static final zzdv zzB = zza("measurement.upload.retry_count", 6, 6, zzbi.zza);
    public static final zzdv zzC = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbj.zza);
    public static final zzdv zzD = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbk.zza);
    public static final zzdv zzE = zza("measurement.audience.filter_result_max_count", 200, 200, zzbm.zza);
    public static final zzdv zzF = zza("measurement.upload.max_public_user_properties", 25, 25, null);
    public static final zzdv zzG = zza("measurement.upload.max_event_name_cardinality", 500, 500, null);
    public static final zzdv zzH = zza("measurement.upload.max_public_event_params", 25, 25, null);
    public static final zzdv zzI;
    public static final zzdv zzJ;
    public static final zzdv zzK = zza("measurement.test.string_flag", "---", "---", zzbp.zza);
    public static final zzdv zzL = zza("measurement.test.long_flag", -1L, -1L, zzbq.zza);
    public static final zzdv zzM = zza("measurement.test.int_flag", -2, -2, zzbr.zza);
    public static final zzdv zzN;
    public static final zzdv zzO = zza("measurement.experiment.max_ids", 50, 50, zzbu.zza);
    public static final zzdv zzP = zza("measurement.max_bundles_per_iteration", 100, 100, zzbv.zza);
    public static final zzdv zzQ = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzbw.zza);
    public static final zzdv zzR;
    public static final zzdv zzS;
    public static final zzdv zzT;
    public static final zzdv zzU;
    public static final zzdv zzV;
    public static final zzdv zzW;
    public static final zzdv zzX;
    public static final zzdv zzY;
    public static final zzdv zzZ;
    public static final zzdv zza;
    
    public static final List zzaA = Collections.synchronizedList(new ArrayList());
    private static final Set zzaB = Collections.synchronizedSet(new HashSet());
    public static final zzdv zzaa;
    public static final zzdv zzab;
    public static final zzdv zzac;
    public static final zzdv zzad;
    public static final zzdv zzae;
    public static final zzdv zzaf;
    public static final zzdv zzag;
    public static final zzdv zzah;
    public static final zzdv zzai = zza("measurement.service.storage_consent_support_version", 203600, 203600, zzcr.zza);
    public static final zzdv zzaj;
    public static final zzdv zzak;
    public static final zzdv zzal;
    public static final zzdv zzam;
    public static final zzdv zzan;
    public static final zzdv zzao;
    public static final zzdv zzap;
    public static final zzdv zzaq;
    public static final zzdv zzar;
    public static final zzdv zzas;
    public static final zzdv zzat;
    public static final zzdv zzau;
    public static final zzdv zzav;
    public static final zzdv zzaw;
    public static final zzdv zzax;
    public static final zzdv zzay;
    public static final zzdv zzaz;
    public static final zzdv zzb = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzbh.zza);
    public static final zzdv zzc = zza("measurement.config.cache_time", 86400000L, 3600000L, zzdv.zza);
    public static final zzdv zzd = zza("measurement.config.url_scheme", ProxyConfig.MATCH_HTTPS, ProxyConfig.MATCH_HTTPS, zzbl.zza);
    public static final zzdv zze = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzbx.zza);
    public static final zzdv zzf = zza("measurement.upload.max_bundles", 100, 100, zzcj.zza);
    public static final zzdv zzg = zza("measurement.upload.max_batch_size", 65536, 65536, zzcv.zza);
    public static final zzdv zzh = zza("measurement.upload.max_bundle_size", 65536, 65536, zzdh.zza);
    public static final zzdv zzi = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzdn.zza);
    public static final zzdv zzj = zza("measurement.upload.max_events_per_day", 100000, 100000, zzdo.zza);
    public static final zzdv zzk = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzbs.zza);
    public static final zzdv zzl;
    public static final zzdv zzm = zza("measurement.upload.max_conversions_per_day", 10000, 10000, zzco.zza);
    public static final zzdv zzn = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzcz.zza);
    public static final zzdv zzo = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzdk.zza);
    public static final zzdv zzp = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzdp.zza);
    public static final zzdv zzq = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzdq.zza);
    public static final zzdv zzr = zza("measurement.upload.window_interval", 3600000L, 3600000L, zzdr.zza);
    public static final zzdv zzs = zza("measurement.upload.interval", 3600000L, 3600000L, zzdv.zza);
    public static final zzdv zzt;
    public static final zzdv zzu = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzba.zza);
    public static final zzdv zzv = zza("measurement.upload.minimum_delay", 500L, 500L, zzbb.zza);
    public static final zzdv zzw = zza("measurement.alarm_manager.minimum_interval", 60000L, 60000L, zzbc.zza);
    public static final zzdv zzx = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbd.zza);
    public static final zzdv zzy = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbe.zza);
    public static final zzdv zzz = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbf.zza);

    static {
        Long valueOf = Long.valueOf(WorkRequest.MIN_BACKOFF_MILLIS);
        zza = zza("measurement.ad_id_cache_time", valueOf, valueOf, zzdv.zza);
        Integer valueOf2 = Integer.valueOf(ServicesClientForTiger.TRANSFER_PART_SIZE);
        zzl = zza("measurement.upload.max_public_events_per_day", valueOf2, valueOf2, zzcd.zza);
        zzt = zza("measurement.upload.realtime_upload_interval", valueOf, valueOf, zzdv.zza);
        Long valueOf3 = Long.valueOf(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzI = zza("measurement.service_client.idle_disconnect_millis", valueOf3, valueOf3, zzbn.zza);
        Boolean bool = Boolean.FALSE;
        zzJ = zza("measurement.test.boolean_flag", bool, bool, zzbo.zza);
        Double valueOf4 = Double.valueOf(-3.0d);
        zzN = zza("measurement.test.double_flag", valueOf4, valueOf4, zzbt.zza);
        Boolean bool2 = Boolean.TRUE;
        zzR = zza("measurement.validation.internal_limits_internal_event_params", bool2, bool2, zzby.zza);
        zzS = zza("measurement.collection.log_event_and_bundle_v2", bool2, bool2, zzbz.zza);
        zzT = zza("measurement.quality.checksum", bool, bool, null);
        zzU = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", bool, bool, zzca.zza);
        zzV = zza("measurement.audience.refresh_event_count_filters_timestamp", bool, bool, zzcb.zza);
        zzW = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", bool, bool, zzcc.zza);
        zzX = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", bool2, bool2, zzce.zza);
        zzY = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", bool, bool, zzcf.zza);
        zzZ = zza("measurement.lifecycle.app_in_background_parameter", bool, bool, zzcg.zza);
        zzaa = zza("measurement.integration.disable_firebase_instance_id", bool, bool, zzch.zza);
        zzab = zza("measurement.collection.service.update_with_analytics_fix", bool, bool, zzci.zza);
        zzac = zza("measurement.client.firebase_feature_rollout.v1.enable", bool2, bool2, zzck.zza);
        zzad = zza("measurement.client.sessions.check_on_reset_and_enable2", bool2, bool2, zzcl.zza);
        zzae = zza("measurement.scheduler.task_thread.cleanup_on_exit", bool, bool, zzcm.zza);
        zzaf = zza("measurement.upload.file_truncate_fix", bool, bool, zzcn.zza);
        zzag = zza("measurement.collection.synthetic_data_mitigation", bool, bool, zzcp.zza);
        zzah = zza("measurement.androidId.delete_feature", bool2, bool2, zzcq.zza);
        zzaj = zza("measurement.client.click_identifier_control.dev", bool, bool, zzcs.zza);
        zzak = zza("measurement.service.click_identifier_control", bool, bool, zzct.zza);
        zzal = zza("measurement.client.consent.gmpappid_worker_thread_fix", bool2, bool2, zzcu.zza);
        zzam = zza("measurement.module.pixie.fix_array", bool2, bool2, zzcw.zza);
        zzan = zza("measurement.adid_zero.service", bool2, bool2, zzcx.zza);
        zzao = zza("measurement.adid_zero.remove_lair_if_adidzero_false", bool2, bool2, zzcy.zza);
        zzap = zza("measurement.adid_zero.remove_lair_if_userid_cleared", bool2, bool2, zzda.zza);
        zzaq = zza("measurement.adid_zero.remove_lair_on_id_value_change_only", bool2, bool2, zzdb.zza);
        zzar = zza("measurement.adid_zero.adid_uid", bool, bool, zzdc.zza);
        zzas = zza("measurement.adid_zero.app_instance_id_fix", bool2, bool2, zzdd.zza);
        zzat = zza("measurement.service.refactor.package_side_screen", bool2, bool2, zzde.zza);
        zzau = zza("measurement.enhanced_campaign.service", bool, bool, zzdf.zza);
        zzav = zza("measurement.enhanced_campaign.client", bool, bool, zzdg.zza);
        zzaw = zza("measurement.enhanced_campaign.srsltid.client", bool, bool, zzdi.zza);
        zzax = zza("measurement.enhanced_campaign.srsltid.service", bool, bool, zzdj.zza);
        zzay = zza("measurement.service.store_null_safelist", bool, bool, zzdl.zza);
        zzaz = zza("measurement.service.store_safelist", bool, bool, zzdm.zza);
    }

    @VisibleForTesting
    static zzdv zza(String str, Object obj, Object obj2, zzds zzds) {
        zzdv zzdv = new zzdv(str, obj, obj2, zzds, null);
        zzaA.add(zzdv);
        return zzdv;
    }

    public static Map zzc(Context context) {
        zzha zza2 = zzha.zza(context.getContentResolver(), zzhk.zza("com.google.android.gms.measurement"));
        return null == zza2 ? Collections.emptyMap() : zza2.zzc();
    }
}
