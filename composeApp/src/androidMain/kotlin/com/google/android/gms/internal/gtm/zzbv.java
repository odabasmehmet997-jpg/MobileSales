package com.google.android.gms.internal.gtm;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbv {
    private final Context zza;
    private final Context zzb;

    public zzbv(final Context context) {
        Preconditions.checkNotNull(context);
        final Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "Application context can't be null");
        zza = applicationContext;
        zzb = applicationContext;
    }

    public Context zza() {
        return zza;
    }

    public Context zzb() {
        return zzb;
    }
}
