package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzd extends zze {
    private final Map zza = new ArrayMap();
    private final Map zzb = new ArrayMap();
    private long zzc;

    public zzd(zzft zzft) {
        super(zzft);
    }

    static void zza(zzd zzd, String str, long j2) {
        zzd.zzg();
        Preconditions.checkNotEmpty(str);
        if (zzd.zzb.isEmpty()) {
            zzd.zzc = j2;
        }
        Integer num = (Integer) zzd.zzb.get(str);
        if (null != num) {
            zzd.zzb.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (100 <= zzd.zzb.size()) {
            zzd.zzs.zzay().zzk().zza("Too many ads visible");
        } else {
            zzd.zzb.put(str, 1);
            zzd.zza.put(str, Long.valueOf(j2));
        }
    }

    static void zzb(zzd zzd, String str, long j2) {
        zzd.zzg();
        Preconditions.checkNotEmpty(str);
        Integer num = (Integer) zzd.zzb.get(str);
        if (null != num) {
            zzif zzj = zzd.zzs.zzs().zzj(false);
            int intValue = num.intValue() - 1;
            if (0 == intValue) {
                zzd.zzb.remove(str);
                Long l = (Long) zzd.zza.get(str);
                if (null == l) {
                    zzd.zzs.zzay().zzd().zza("First ad unit exposure time was never set");
                } else {
                    long longValue = l.longValue();
                    zzd.zza.remove(str);
                    zzd.zzi(str, j2 - longValue, zzj);
                }
                if (zzd.zzb.isEmpty()) {
                    long j3 = zzd.zzc;
                    if (0 == j3) {
                        zzd.zzs.zzay().zzd().zza("First ad exposure time was never set");
                        return;
                    }
                    zzd.zzh(j2 - j3, zzj);
                    zzd.zzc = 0;
                    return;
                }
                return;
            }
            zzd.zzb.put(str, Integer.valueOf(intValue));
            return;
        }
        zzd.zzs.zzay().zzd().zzb("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    @WorkerThread
    private void zzh(long j2, zzif zzif) {
        if (null == zzif) {
            this.zzs.zzay().zzj().zza("Not logging ad exposure. No active activity");
        } else if (1000 > j2) {
            this.zzs.zzay().zzj().zzb("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j2));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j2);
            zzky.zzJ(zzif, bundle, true);
            this.zzs.zzq().zzG("am", "_xa", bundle);
        }
    }

    @WorkerThread
    private void zzi(String str, long j2, zzif zzif) {
        if (null == zzif) {
            this.zzs.zzay().zzj().zza("Not logging ad unit exposure. No active activity");
        } else if (1000 > j2) {
            this.zzs.zzay().zzj().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j2));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j2);
            zzky.zzJ(zzif, bundle, true);
            this.zzs.zzq().zzG("am", "_xu", bundle);
        }
    }

    
    @WorkerThread
    public void zzj(long j2) {
        for (String put : this.zza.keySet()) {
            this.zza.put(put, Long.valueOf(j2));
        }
        if (!this.zza.isEmpty()) {
            this.zzc = j2;
        }
    }

    public void zzd(String str, long j2) {
        if (null == str || 0 == str.length()) {
            this.zzs.zzay().zzd().zza("Ad unit id must be a non-empty string");
        } else {
            this.zzs.zzaz().zzp(new zza(this, str, j2));
        }
    }

    public void zze(String str, long j2) {
        if (null == str || 0 == str.length()) {
            this.zzs.zzay().zzd().zza("Ad unit id must be a non-empty string");
        } else {
            this.zzs.zzaz().zzp(new zzb(this, str, j2));
        }
    }

    @WorkerThread
    public void zzf(long j2) {
        zzif zzj = this.zzs.zzs().zzj(false);
        for (String str : this.zza.keySet()) {
            zzi(str, j2 - ((Long) this.zza.get(str)).longValue(), zzj);
        }
        if (!this.zza.isEmpty()) {
            zzh(j2 - this.zzc, zzj);
        }
        zzj(j2);
    }
}
