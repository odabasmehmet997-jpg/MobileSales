package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzdv {
    private static final Object zza = new Object();
    private final String zzb;
    private final zzds zzc;
    private final Object zzd;
    private final Object zze;
    private final Object zzf = new Object();
    @GuardedBy("overrideLock")
    private final Object zzg;
    @GuardedBy("cachingLock")
    private final Object zzh;

    zzdv(final String str, final Object obj, final Object obj2, final zzds zzds, final zzdu zzdu) {
        zzb = str;
        zzd = obj;
        zze = obj2;
        zzc = zzds;
    }

    public java.lang.Object zza(final java.lang.Object r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzdv.zza(java.lang.Object):java.lang.Object");
    }

    public String zzb() {
        return zzb;
    }
}
