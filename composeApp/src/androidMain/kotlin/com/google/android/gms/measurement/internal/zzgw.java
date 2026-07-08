package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzcl;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzgw {
    final Context zza;
    @Nullable
    String zzb;
    @Nullable
    String zzc;
    @Nullable
    String zzd;
    @Nullable
    Boolean zze;
    long zzf;
    @Nullable
    zzcl zzg;
    boolean zzh = true;
    @Nullable
    final Long zzi;
    @Nullable
    String zzj;

    @VisibleForTesting
    public zzgw(final Context context, @Nullable final zzcl zzcl, @Nullable final Long l) {
        Preconditions.checkNotNull(context);
        final Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        zza = applicationContext;
        zzi = l;
        if (null != zzcl) {
            zzg = zzcl;
            zzb = zzcl.zzf;
            zzc = zzcl.zze;
            zzd = zzcl.zzd;
            zzh = zzcl.zzc;
            zzf = zzcl.zzb;
            zzj = zzcl.zzh;
            final Bundle bundle = zzcl.zzg;
            if (null != bundle) {
                zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
