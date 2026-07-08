package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzkd {
    private final Clock zza;
    private long zzb;

    public zzkd(final Clock clock) {
        Preconditions.checkNotNull(clock);
        zza = clock;
    }

    public void zza() {
        zzb = 0;
    }

    public void zzb() {
        zzb = zza.elapsedRealtime();
    }

    public boolean zzc(final long j2) {
        return 0 == this.zzb || 3600000 <= this.zza.elapsedRealtime() - this.zzb;
    }
}
