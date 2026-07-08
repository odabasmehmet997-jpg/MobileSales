package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzes {
    final zzey zza;
    private final String zzb;
    private final boolean zzc;
    private boolean zzd;
    private boolean zze;

    public zzes(final zzey zzey, final String str, final boolean z) {
        zza = zzey;
        Preconditions.checkNotEmpty(str);
        zzb = str;
        zzc = z;
    }

    @WorkerThread
    public void zza(final boolean z) {
        final SharedPreferences.Editor edit = zza.zza().edit();
        edit.putBoolean(zzb, z);
        edit.apply();
        zze = z;
    }

    @WorkerThread
    public boolean zzb() {
        if (!zzd) {
            zzd = true;
            zze = zza.zza().getBoolean(zzb, zzc);
        }
        return zze;
    }
}
