package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzew {
    @VisibleForTesting
    final String zza;
    final zzey zzb;
    private final String zzc;
    private final String zzd;
    private final long zze;

    zzew(zzey zzey, String str, long j2, zzev zzev) {
        this.zzb = zzey;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(0 < j2);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = j2;
    }

    @WorkerThread
    private long zzc() {
        return this.zzb.zza().getLong(this.zza, 0);
    }

    @WorkerThread
    private void zzd() {
        this.zzb.zzg();
        long currentTimeMillis = this.zzb.zzs.zzav().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzb.zza().edit();
        edit.remove(this.zzc);
        edit.remove(this.zzd);
        edit.putLong(this.zza, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    public Pair zza() {
        long j2;
        this.zzb.zzg();
        this.zzb.zzg();
        long zzc2 = zzc();
        if (0 == zzc2) {
            zzd();
            j2 = 0;
        } else {
            j2 = Math.abs(zzc2 - this.zzb.zzs.zzav().currentTimeMillis());
        }
        long j3 = this.zze;
        if (j2 < j3) {
            return null;
        }
        if (j2 > j3 + j3) {
            zzd();
            return null;
        }
        String string = this.zzb.zza().getString(this.zzd, null);
        long j4 = this.zzb.zza().getLong(this.zzc, 0);
        zzd();
        if (null == string || 0 >= j4) {
            return zzey.zza;
        }
        return new Pair(string, Long.valueOf(j4));
    }

    @WorkerThread
    public void zzb(String str, long j2) {
        this.zzb.zzg();
        if (0 == this.zzc()) {
            zzd();
        }
        if (null == str) {
            str = "";
        }
        long j3 = this.zzb.zza().getLong(this.zzc, 0);
        if (0 >= j3) {
            SharedPreferences.Editor edit = this.zzb.zza().edit();
            edit.putString(this.zzd, str);
            edit.putLong(this.zzc, 1);
            edit.apply();
            return;
        }
        long nextLong = this.zzb.zzs.zzv().zzF().nextLong();
        long j4 = j3 + 1;
        long j5 = LocationRequestCompat.PASSIVE_INTERVAL / j4;
        SharedPreferences.Editor edit2 = this.zzb.zza().edit();
        if ((LocationRequestCompat.PASSIVE_INTERVAL & nextLong) < j5) {
            edit2.putString(this.zzd, str);
        }
        edit2.putLong(this.zzc, j4);
        edit2.apply();
    }
}
