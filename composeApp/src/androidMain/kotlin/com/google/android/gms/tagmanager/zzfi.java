package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzfi(TagManager zza) implements ComponentCallbacks2 {

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i2) {
        if (i2 == 20) {
            this.zza.dispatch();
        }
    }
}
