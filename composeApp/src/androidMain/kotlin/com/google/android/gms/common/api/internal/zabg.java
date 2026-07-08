package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
abstract class zabg {
    private final zabf zaa;

    protected zabg(final zabf zabf) {
        zaa = zabf;
    }

    
    public abstract void zaa();

    public final void zab(final zabi zabi) {
        zabi.zai.lock();
        try {
            if (zabi.zan == zaa) {
                this.zaa();
            }
        } finally {
            zabi.zai.unlock();
        }
    }
}
