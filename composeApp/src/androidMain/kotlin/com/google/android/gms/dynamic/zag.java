package com.google.android.gms.dynamic;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zag implements zah {
    final DeferredLifecycleHelper zaa;

    zag(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    public int zaa() {
        return 5;
    }

    public void zab(LifecycleDelegate lifecycleDelegate) {
        this.zaa.zaa.onResume();
    }
}
