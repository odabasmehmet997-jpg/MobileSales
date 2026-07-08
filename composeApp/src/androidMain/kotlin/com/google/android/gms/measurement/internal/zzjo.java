package com.google.android.gms.measurement.internal;

import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.measurement.zzna;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzjo extends zzkh {
    public final zzeu zza;
    public final zzeu zzb;
    public final zzeu zzc;
    public final zzeu zzd;
    public final zzeu zze;
    private final Map zzg = new HashMap();
    private String zzh;
    private boolean zzi;
    private long zzj;

    zzjo(zzkr zzkr) {
        super(zzkr);
        zzey zzm = this.zzs.zzm();
        zzm.getClass();
        this.zza = new zzeu(zzm, "last_delete_stale", 0);
        zzey zzm2 = this.zzs.zzm();
        zzm2.getClass();
        this.zzb = new zzeu(zzm2, "backoff", 0);
        zzey zzm3 = this.zzs.zzm();
        zzm3.getClass();
        this.zzc = new zzeu(zzm3, "last_upload", 0);
        zzey zzm4 = this.zzs.zzm();
        zzm4.getClass();
        this.zzd = new zzeu(zzm4, "last_upload_attempt", 0);
        zzey zzm5 = this.zzs.zzm();
        zzm5.getClass();
        this.zze = new zzeu(zzm5, "midnight_offset", 0);
    }

    
    @WorkerThread
    @Deprecated
    public Pair zza(String str) {
        zzjn zzjn;
        zzg();
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        zzna.zzc();
        if (this.zzs.zzf().zzs(null, zzdw.zzar)) {
            zzjn zzjn2 = (zzjn) this.zzg.get(str);
            if (null != zzjn2 && elapsedRealtime < zzjn2.zzc()) {
                return new Pair(zzjn2.zza(), Boolean.valueOf(zzjn2.zzb()));
            }
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
            long zzi2 = elapsedRealtime + this.zzs.zzf().zzi(str, zzdw.zza);
            try {
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
                if (null == advertisingIdInfo) {
                    return new Pair("", Boolean.FALSE);
                }
                String id = advertisingIdInfo.getId();
                if (null != id) {
                    zzjn = new zzjn(id, advertisingIdInfo.isLimitAdTrackingEnabled(), zzi2);
                } else {
                    zzjn = new zzjn("", advertisingIdInfo.isLimitAdTrackingEnabled(), zzi2);
                }
                this.zzg.put(str, zzjn);
                AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
                return new Pair(zzjn.zza(), Boolean.valueOf(zzjn.zzb()));
            } catch (Exception e2) {
                this.zzs.zzay().zzc().zzb("Unable to get advertising id", e2);
                zzjn = new zzjn("", false, zzi2);
            }
        } else {
            String str2 = this.zzh;
            if (null != str2 && elapsedRealtime < this.zzj) {
                return new Pair(str2, Boolean.valueOf(this.zzi));
            }
            this.zzj = elapsedRealtime + this.zzs.zzf().zzi(str, zzdw.zza);
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
            try {
                AdvertisingIdClient.Info advertisingIdInfo2 = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
                if (null == advertisingIdInfo2) {
                    return new Pair("", Boolean.FALSE);
                }
                this.zzh = "";
                String id2 = advertisingIdInfo2.getId();
                if (null != id2) {
                    this.zzh = id2;
                }
                this.zzi = advertisingIdInfo2.isLimitAdTrackingEnabled();
                AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
                return new Pair(this.zzh, Boolean.valueOf(this.zzi));
            } catch (Exception e3) {
                this.zzs.zzay().zzc().zzb("Unable to get advertising id", e3);
                this.zzh = "";
            }
        }
    }

    
    public boolean zzb() {
        return false;
    }

    
    @WorkerThread
    public Pair zzd(String str, zzah zzah) {
        if (zzah.zzi(zzag.AD_STORAGE)) {
            return zza(str);
        }
        return new Pair("", Boolean.FALSE);
    }

    
    @WorkerThread
    @Deprecated
    public String zzf(String str) {
        zzg();
        String str2 = (String) zza(str).first;
        MessageDigest zzE = zzky.zzE();
        if (null == zzE) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzE.digest(str2.getBytes(StandardCharsets.UTF_8))));
    }
}
