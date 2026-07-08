package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashSet;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzeh {
    ;
    public static final zzeg zzA;
    public static final zzeg zzB;
    public static final zzeg zzC = zzeg.zza(1800000L, 1800000L, new zzdq());
    public static final zzeg zzD = zzeg.zza(86400000L, 86400000L, new zzdr());
    public static final zzeg zzE;
    public static final zzeg zzF;
    public static final zzeg zza;
    public static final zzeg zzb = zzeg.zza("GAv4", "GAv4-SVC", new zzdj());
    public static final zzeg zzc = zzeg.zza(2000, 20000, new zzdu());
    public static final zzeg zzd = zzeg.zza(100, 100, new zzdw());
    public static final zzeg zze = zzeg.zza(1800000L, 120000L, new zzdx());
    public static final zzeg zzf;
    public static final zzeg zzg = zzeg.zza(7200000L, 7200000L, new zzdv());
    public static final zzeg zzh = zzeg.zza(32400000L, 32400000L, new zzdz());
    public static final zzeg zzi = zzeg.zza(20, 20, new zzea());
    public static final zzeg zzj = zzeg.zza(20, 20, new zzeb());
    public static final zzeg zzk = zzeg.zza("http://www.google-analytics.com", "http://www.google-analytics.com", new zzec());
    public static final zzeg zzl = zzeg.zza("https://ssl.google-analytics.com", "https://ssl.google-analytics.com", new zzed());
    public static final zzeg zzm = zzeg.zza("/collect", "/collect", new zzee());
    public static final zzeg zzn = zzeg.zza("/batch", "/batch", new zzcz());
    public static final zzeg zzo = zzeg.zza(2036, 2036, new zzdb());
    public static final zzeg zzp = zzeg.zza("BATCH_BY_COUNT", "BATCH_BY_COUNT", new zzdc());
    public static final zzeg zzq = zzeg.zza("GZIP", "GZIP", new zzdd());
    public static final zzeg zzr = zzeg.zza(8192, 8192, new zzde());
    public static final zzeg zzs = zzeg.zza(8192, 8192, new zzdf());
    public static final zzeg zzt = zzeg.zza(8192, 8192, new zzdg());
    public static final zzeg zzu = zzeg.zza("404,502", "404,502", new zzdh());
    public static final zzeg zzv = zzeg.zza(3600, 3600, new zzdi());
    public static final zzeg zzw = zzeg.zza(60000, 60000, new zzdk());
    public static final zzeg zzx = zzeg.zza(61000, 61000, new zzdl());
    public static final zzeg zzy = zzeg.zza(86400000L, 86400000L, new zzdm());
    public static final zzeg zzz;

    static {
        Collections.synchronizedSet(new HashSet());
        final Boolean bool = Boolean.TRUE;
        zza = zzeg.zza(bool, bool, new zzda());
        final Long valueOf = Long.valueOf(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzf = zzeg.zza(valueOf, valueOf, new zzdy());
        final Boolean bool2 = Boolean.FALSE;
        zzz = zzeg.zza(bool2, bool2, new zzdn());
        final Long valueOf2 = Long.valueOf(WorkRequest.MIN_BACKOFF_MILLIS);
        zzA = zzeg.zza(valueOf2, valueOf2, new zzdo());
        zzB = zzeg.zza(valueOf, valueOf, new zzdp());
        zzE = zzeg.zza(valueOf, valueOf, new zzds());
        zzF = zzeg.zza(bool2, bool2, new zzdt());
    }
}
