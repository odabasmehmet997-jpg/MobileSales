package com.google.android.gms.internal.gtm;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Preconditions;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzes {
    final zzeu zza;
    private final String zzb;
    private final long zzc;

    zzes(zzeu zzeu, String str, long j2, zzet zzet) {
        this.zza = zzeu;
        Preconditions.checkNotEmpty("monitoring");
        Preconditions.checkArgument(0 < j2);
        this.zzb = "monitoring";
        this.zzc = j2;
    }

    private long zzd() {
        return this.zza.zza.getLong(zzf(), 0);
    }

    private String zze() {
        return this.zzb + ":count";
    }

    private String zzf() {
        return this.zzb + ":start";
    }

    private void zzg() {
        long currentTimeMillis = this.zza.zzC().currentTimeMillis();
        SharedPreferences.Editor edit = this.zza.zza.edit();
        edit.remove(zze());
        edit.remove(zzb());
        edit.putLong(zzf(), currentTimeMillis);
        edit.commit();
    }

    public Pair zza() {
        long j2;
        long zzd = zzd();
        if (0 == zzd) {
            j2 = 0;
        } else {
            j2 = Math.abs(zzd - this.zza.zzC().currentTimeMillis());
        }
        long j3 = this.zzc;
        if (j2 >= j3) {
            if (j2 > j3 + j3) {
                zzg();
                return null;
            }
            String string = this.zza.zza.getString(zzb(), null);
            long j4 = this.zza.zza.getLong(zze(), 0);
            zzg();
            if (null != string && 0 < j4) {
                return new Pair(string, Long.valueOf(j4));
            }
        }
        return null;
    }

    
    @VisibleForTesting
    public String zzb() {
        return this.zzb + ":value";
    }

    public void zzc(String str) {
        if (0 == this.zzd()) {
            zzg();
        }
        if (null == str) {
            str = "";
        }
        synchronized (this) {
            try {
                long j2 = this.zza.zza.getLong(zze(), 0);
                if (0 >= j2) {
                    SharedPreferences.Editor edit = this.zza.zza.edit();
                    edit.putString(zzb(), str);
                    edit.putLong(zze(), 1);
                    edit.apply();
                    return;
                }
                long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits() & LocationRequestCompat.PASSIVE_INTERVAL;
                long j3 = j2 + 1;
                long j4 = LocationRequestCompat.PASSIVE_INTERVAL / j3;
                SharedPreferences.Editor edit2 = this.zza.zza.edit();
                if (leastSignificantBits < j4) {
                    edit2.putString(zzb(), str);
                }
                edit2.putLong(zze(), j3);
                edit2.apply();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
