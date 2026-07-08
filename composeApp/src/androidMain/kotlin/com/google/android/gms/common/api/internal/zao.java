package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zao implements Runnable {
    final zap zaa;
    private final zam zab;

    zao(final zap zap, final zam zam) {
        zaa = zap;
        zab = zam;
    }

    @MainThread
    public void run() {
        if (zaa.zaa) {
            final ConnectionResult zab2 = zab.zab();
            if (zab2.hasResolution()) {
                final zap zap = zaa;
                zap.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(zap.getActivity(), Preconditions.checkNotNull(zab2.getResolution()), zab.zaa(), false), 1);
                return;
            }
            final zap zap2 = zaa;
            if (null != zap2.zac.getErrorResolutionIntent(zap2.getActivity(), zab2.getErrorCode(), null)) {
                final zap zap3 = zaa;
                zap3.zac.zag(zap3.getActivity(), zap3.mLifecycleFragment, zab2.getErrorCode(), 2, zaa);
            } else if (18 == zab2.getErrorCode()) {
                final zap zap4 = zaa;
                final Dialog zab3 = zap4.zac.zab(zap4.getActivity(), zap4);
                final zap zap5 = zaa;
                zap5.zac.zac(zap5.getActivity().getApplicationContext(), new zan(this, zab3));
            } else {
                zaa.zaa(zab2, zab.zaa());
            }
        }
    }
}
