package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzkw(String zza, String zzb, String zzc, long zzd, Object zze) {
    zzkw {
        Preconditions.checkNotEmpty(zza);
        Preconditions.checkNotEmpty(zzc);
        Preconditions.checkNotNull(zze);
    }
}
