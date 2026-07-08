package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zax implements zabz {
    final zaaa zaa;

    zax(final zaaa zaaa, final zaw zaw) {
        zaa = zaaa;
    }

    public void zaa(@NonNull final ConnectionResult connectionResult) {
        zaa.zam.lock();
        try {
            zaa.zaj = connectionResult;
            zaaa.zap(zaa);
        } finally {
            zaa.zam.unlock();
        }
    }

    public void zab(@Nullable final Bundle bundle) {
        zaa.zam.lock();
        try {
            zaaa.zao(zaa, bundle);
            zaa.zaj = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(zaa);
        } finally {
            zaa.zam.unlock();
        }
    }

    public void zac(final int i2, final boolean z) {
        zaa.zam.lock();
        try {
            final zaaa zaaa = zaa;
            if (!zaaa.zal && null != zaaa.zak) {
                if (zaaa.zak.isSuccess()) {
                    zaa.zal = true;
                    zaa.zae.onConnectionSuspended(i2);
                }
            }
            zaa.zal = false;
            com.google.android.gms.common.api.internal.zaaa.zan(zaa, i2, z);
        } finally {
            zaa.zam.unlock();
        }
    }
}
