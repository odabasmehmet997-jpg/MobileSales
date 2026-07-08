package com.google.android.gms.dynamic;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaf implements zah {
    final DeferredLifecycleHelper zaa;

    zaf(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    public int zaa() {
        return 4;
    }

    public void zab(LifecycleDelegate lifecycleDelegate) {
        this.zaa.zaa.onStart();
    }
}
