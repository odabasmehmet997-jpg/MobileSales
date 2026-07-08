package com.google.android.gms.tagmanager;

import androidx.core.app.NotificationCompat;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzfg(TagManager zza) implements zzaq {

    public void zza(Map map) {
        Object obj = map.get(NotificationCompat.CATEGORY_EVENT);
        if (obj != null) {
            TagManager.zzb(this.zza, obj.toString());
        }
    }
}
