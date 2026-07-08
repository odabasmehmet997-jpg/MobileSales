package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zan extends zabw {
    final Dialog zaa;
    final zao zab;

    zan(final zao zao, final Dialog dialog) {
        zab = zao;
        zaa = dialog;
    }

    public void zaa() {
        zab.zaa.zad();
        if (zaa.isShowing()) {
            zaa.dismiss();
        }
    }
}
