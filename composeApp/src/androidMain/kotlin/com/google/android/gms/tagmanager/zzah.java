package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Random;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzah {
    private final Context zza;
    private final Random zzb;
    private final String zzc;

    private long zze(long j2, long j3) {
        SharedPreferences zzf = zzf();
        long max = Math.max(0, zzf.getLong("FORBIDDEN_COUNT", 0));
        return (long) (this.zzb.nextFloat() * ((float) (j2 + ((long) ((((float) max) / ((float) ((max + Math.max(0, zzf.getLong("SUCCESSFUL_COUNT", 0))) + 1))) * ((float) (j3 - j2)))))));
    }

    private SharedPreferences zzf() {
        return this.zza.getSharedPreferences("_gtmContainerRefreshPolicy_".concat(String.valueOf(this.zzc)), 0);
    }

    public long zza() {
        return zze(7200000, 259200000) + 43200000;
    }

    public long zzb() {
        return zze(600000, 86400000) + 3600000;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void zzc() {
        long j2;
        SharedPreferences zzf = zzf();
        long j3 = zzf.getLong("FORBIDDEN_COUNT", 0);
        long j4 = zzf.getLong("SUCCESSFUL_COUNT", 0);
        SharedPreferences.Editor edit = zzf.edit();
        if (j3 == 0) {
            j2 = 3;
        } else {
            j2 = Math.min(10, j3 + 1);
        }
        long max = Math.max(0, Math.min(j4, 10 - j2));
        edit.putLong("FORBIDDEN_COUNT", j2);
        edit.putLong("SUCCESSFUL_COUNT", max);
        edit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public void zzd() {
        SharedPreferences zzf = zzf();
        long j2 = zzf.getLong("SUCCESSFUL_COUNT", 0);
        long j3 = zzf.getLong("FORBIDDEN_COUNT", 0);
        long min = Math.min(10, j2 + 1);
        long max = Math.max(0, Math.min(j3, 10 - min));
        SharedPreferences.Editor edit = zzf.edit();
        edit.putLong("SUCCESSFUL_COUNT", min);
        edit.putLong("FORBIDDEN_COUNT", max);
        edit.apply();
    }
}
