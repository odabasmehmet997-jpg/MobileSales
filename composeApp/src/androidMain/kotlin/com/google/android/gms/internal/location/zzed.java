package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzed implements OnTokenCanceledListener {
    private final ICancelToken zza;

    zzed(final ICancelToken iCancelToken) {
        zza = iCancelToken;
    }

    public void onCanceled() {
        final int i2 = zzdz.r8clinit;
        try {
            zza.cancel();
        } catch (final RemoteException unused) {
        }
    }
}
