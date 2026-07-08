package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zab implements zah {
    final Activity zaa;
    final Bundle zab;
    final Bundle zac;
    final DeferredLifecycleHelper zad;

    zab(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zad = deferredLifecycleHelper;
        this.zaa = activity;
        this.zab = bundle;
        this.zac = bundle2;
    }

    public int zaa() {
        return 0;
    }

    public void zab(LifecycleDelegate lifecycleDelegate) {
        this.zad.zaa.onInflate(this.zaa, this.zab, this.zac);
    }
}
