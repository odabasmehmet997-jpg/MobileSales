package com.google.android.gms.common.api.internal;

import androidx.annotation.BinderThread;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaar extends zac {
    private final WeakReference zaa;

    zaar(final zaaw zaaw) {
        zaa = new WeakReference(zaaw);
    }

    @BinderThread
    public void zab(final zak zak) {
        final zaaw zaaw = (zaaw) zaa.get();
        if (null != zaaw) {
            zaaw.zaa.zal(new zaaq(this, zaaw, zaaw, zak));
        }
    }
}
