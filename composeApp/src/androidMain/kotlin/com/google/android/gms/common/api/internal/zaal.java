package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaal implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference zaa;
    private final Api zab;
    
    public final boolean zac;

    public zaal(final zaaw zaaw, final Api api, final boolean z) {
        zaa = new WeakReference(zaaw);
        zab = api;
        zac = z;
    }

    public void onReportServiceBinding(@NonNull final ConnectionResult connectionResult) {
        final zaaw zaaw = (zaaw) zaa.get();
        if (null != zaaw) {
            Preconditions.checkState(Looper.myLooper() == zaaw.zaa.zag.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaaw.zab.lock();
            try {
                if (zaaw.zaG(0)) {
                    if (!connectionResult.isSuccess()) {
                        zaaw.zaE(connectionResult, zab, zac);
                    }
                    if (zaaw.zaH()) {
                        zaaw.zaF();
                    }
                }
            } finally {
                zaaw.zab.unlock();
            }
        }
    }
}
