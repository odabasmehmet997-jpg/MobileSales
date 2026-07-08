package com.google.android.gms.internal.gtm;

import java.util.Set;
import java.util.logging.Level;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzvm implements zzvf {
    private final String zza;
    private final Level zzb;
    private final Set zzc;
    private final zzuw zzd;
    private final int zze;

    private zzvm() {
        this("", true, 2, Level.ALL, false, zzvp.zzb, zzvp.zzc);
    }

    private zzvm(String str, boolean z, int i2, Level level, boolean z2, Set set, zzuw zzuw) {
        this.zza = "";
        this.zze = 2;
        this.zzb = level;
        this.zzc = set;
        this.zzd = zzuw;
    }

    zzvm(zzvo zzvo) {
        this("", true, 2, Level.ALL, false, zzvp.zzb, zzvp.zzc);
    }
}
