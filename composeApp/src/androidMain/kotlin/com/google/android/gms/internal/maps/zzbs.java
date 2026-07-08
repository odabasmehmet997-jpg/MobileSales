package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbs extends zzbi {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    zzbs(final Object[] objArr, final int i2, final int i3) {
        zza = objArr;
        zzb = i2;
        zzc = i3;
    }

    public Object get(final int i2) {
        zzba.zza(i2, zzc, FirebaseAnalytics.Param.INDEX);
        final Object obj = zza[i2 + i2 + zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    public int size() {
        return zzc;
    }
}
