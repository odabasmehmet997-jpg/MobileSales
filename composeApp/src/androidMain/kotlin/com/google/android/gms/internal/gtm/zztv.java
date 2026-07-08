package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztv extends zztl {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc = 6;

    zztv(final Object[] objArr, final int i2, final int i3) {
        zza = objArr;
        zzb = i2;
    }

    public Object get(final int i2) {
        zztd.zza(i2, zzc, FirebaseAnalytics.Param.INDEX);
        final Object obj = zza[i2 + i2 + zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    public int size() {
        return zzc;
    }
}
