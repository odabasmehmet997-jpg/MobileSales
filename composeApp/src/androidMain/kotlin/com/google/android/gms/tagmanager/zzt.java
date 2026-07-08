package com.google.android.gms.tagmanager;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzt implements zzal {
    final Container zza;

    zzt(Container container, zzu zzu) {
        this.zza = container;
    }

    public Object zza(String str, Map map) {
        Container.FunctionCallTagCallback zzb = this.zza.zzb(str);
        if (zzb != null) {
            zzb.execute(str, map);
        }
        return zzfp.zzl();
    }
}
