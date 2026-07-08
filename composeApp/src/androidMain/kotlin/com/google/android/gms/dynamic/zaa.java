package com.google.android.gms.dynamic;

import java.util.Iterator;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zaa(DeferredLifecycleHelper zaa) implements OnDelegateCreatedListener {

    public void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        this.zaa.zaa = lifecycleDelegate;
        Iterator it = this.zaa.zac.iterator();
        while (it.hasNext()) {
            ((zah) it.next()).zab(this.zaa.zaa);
        }
        this.zaa.zac.clear();
        this.zaa.zab = null;
    }
}
