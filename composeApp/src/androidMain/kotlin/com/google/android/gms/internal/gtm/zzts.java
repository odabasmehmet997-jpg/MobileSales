package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzts extends zztl {
    final zztt zza;

    zzts(final zztt zztt) {
        zza = zztt;
    }

    public Object get(final int i2) {
        zztd.zza(i2, zza.zzc, FirebaseAnalytics.Param.INDEX);
        final int i3 = i2 + i2;
        final Object obj = zza.zzb[i3];
        Objects.requireNonNull(obj);
        final Object obj2 = zza.zzb[i3 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public int size() {
        return zza.zzc;
    }
}
