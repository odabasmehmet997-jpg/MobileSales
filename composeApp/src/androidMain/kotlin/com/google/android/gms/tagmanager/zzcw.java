package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzcw {
    private final LruCache zza;

    zzcw(int i2, zzq zzq) {
        this.zza = new zzcv(this, 1048576, zzq);
    }

    public Object zza(Object obj) {
        return this.zza.get(obj);
    }

    public void zzb(Object obj, Object obj2) {
        this.zza.put(obj, obj2);
    }
}
