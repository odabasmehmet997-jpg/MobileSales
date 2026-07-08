package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzfh extends LruCache {
    final zzfk zza;

    
    zzfh(final zzfk zzfk, final int i2) {
        super(20);
        zza = zzfk;
    }

    
    public Object create(final Object obj) {
        final String str = (String) obj;
        Preconditions.checkNotEmpty(str);
        return zzfk.zzd(zza, str);
    }
}
