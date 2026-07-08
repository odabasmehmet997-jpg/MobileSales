package com.google.android.gms.tagmanager;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzs implements zzal {
    final Container zza;

    zzs(Container container, zzu zzu) {
        this.zza = container;
    }

    public Object zza(String str, Map map) {
        Container.FunctionCallMacroCallback zza2 = this.zza.zza(str);
        if (zza2 == null) {
            return null;
        }
        return zza2.getValue(str, map);
    }
}
