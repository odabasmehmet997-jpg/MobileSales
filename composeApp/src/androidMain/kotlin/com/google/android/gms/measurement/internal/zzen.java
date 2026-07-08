package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzen implements Runnable {
    private final zzel zza;
    private final int zzb;
    private final Throwable zzc;
    private final byte[] zzd;
    private final String zze;
    private final Map zzf;

    zzen(final String str, final zzel zzel, final int i2, final Throwable th, final byte[] bArr, final Map map, final zzem zzem) {
        Preconditions.checkNotNull(zzel);
        zza = zzel;
        zzb = i2;
        zzc = th;
        zzd = bArr;
        zze = str;
        zzf = map;
    }

    public void run() {
        zza.zza(zze, zzb, zzc, zzd, zzf);
    }
}
