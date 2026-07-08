package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzfb {
    private final Clock zza;
    private long zzb;

    public zzfb(final Clock clock) {
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
        return 0 == this.zzb || zza.elapsedRealtime() - zzb > j2;
    }

    public zzfb(final Clock clock, final long j2) {
        Preconditions.checkNotNull(clock);
        zza = clock;
        zzb = j2;
    }
}
