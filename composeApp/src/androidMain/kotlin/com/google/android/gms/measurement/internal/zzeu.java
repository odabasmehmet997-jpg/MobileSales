package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzeu {
    final zzey zza;
    private final String zzb;
    private final long zzc;
    private boolean zzd;
    private long zze;

    public zzeu(final zzey zzey, final String str, final long j2) {
        zza = zzey;
        Preconditions.checkNotEmpty(str);
        zzb = str;
        zzc = j2;
    }

    @WorkerThread
    public long zza() {
        if (!zzd) {
            zzd = true;
            zze = zza.zza().getLong(zzb, zzc);
        }
        return zze;
    }

    @WorkerThread
    public void zzb(final long j2) {
        final SharedPreferences.Editor edit = zza.zza().edit();
        edit.putLong(zzb, j2);
        edit.apply();
        zze = j2;
    }
}
