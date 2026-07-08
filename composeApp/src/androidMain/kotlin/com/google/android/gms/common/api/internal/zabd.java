package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabd extends zabw {
    private final WeakReference zaa;

    zabd(final zabe zabe) {
        zaa = new WeakReference(zabe);
    }

    public void zaa() {
        final zabe zabe = (zabe) zaa.get();
        if (null != zabe) {
            com.google.android.gms.common.api.internal.zabe.zai(zabe);
        }
    }
}
