package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzeg {
    private final Object zza;
    private final Object zzb = new Object();

    private zzeg(final Object obj, final Object obj2, final zzef zzef) {
        zza = obj;
    }

    static zzeg zza(final Object obj, final Object obj2, final zzef zzef) {
        return new zzeg(obj, obj2, zzef);
    }

    public Object zzb() {
        synchronized (zzb) {
        }
        return zza;
    }
}
