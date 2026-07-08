package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaz implements zabz {
    final zaaa zaa;

    zaz(final zaaa zaaa, final zay zay) {
        zaa = zaaa;
    }

    public void zaa(@NonNull final ConnectionResult connectionResult) {
        zaa.zam.lock();
        try {
            zaa.zak = connectionResult;
            zaaa.zap(zaa);
        } finally {
            zaa.zam.unlock();
        }
    }

    public void zab(@Nullable final Bundle bundle) {
        zaa.zam.lock();
        try {
            zaa.zak = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(zaa);
        } finally {
            zaa.zam.unlock();
        }
    }

    public void zac(final int i2, final boolean z) {
        zaa.zam.lock();
        try {
            final zaaa zaaa = zaa;
            if (zaaa.zal) {
                zaaa.zal = false;
                com.google.android.gms.common.api.internal.zaaa.zan(zaa, i2, z);
            } else {
                zaaa.zal = true;
                zaa.zad.onConnectionSuspended(i2);
            }
        } finally {
            zaa.zam.unlock();
        }
    }
}
