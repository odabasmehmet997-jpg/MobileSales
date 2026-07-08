package com.google.android.gms.tagmanager;

import android.util.LruCache;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzcv extends LruCache {
    final zzq zza;

    
    zzcv(zzcw zzcw, int i2, zzq zzq) {
        super(1048576);
        this.zza = zzq;
    }

    
    public int sizeOf(Object obj, Object obj2) {
        return this.zza.zza(obj, obj2);
    }
}
