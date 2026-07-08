package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabu implements BaseGmsClient.ConnectionProgressReportCallbacks, zacs {
    final GoogleApiManager zaa;
    
    public final Api.Client zab;
    
    public final ApiKey zac;
    @Nullable
    private IAccountAccessor zad;
    @Nullable
    private Set zae;
    
    public boolean zaf;

    public zabu(final GoogleApiManager googleApiManager, final Api.Client client, final ApiKey apiKey) {
        zaa = googleApiManager;
        zab = client;
        zac = apiKey;
    }

    
    @WorkerThread
    public void zah() {
        final IAccountAccessor iAccountAccessor;
        if (zaf && null != (iAccountAccessor = this.zad)) {
            zab.getRemoteService(iAccountAccessor, zae);
        }
    }

    public void onReportServiceBinding(@NonNull final ConnectionResult connectionResult) {
        zaa.zar.post(new zabt(this, connectionResult));
    }

    @WorkerThread
    public void zae(final ConnectionResult connectionResult) {
        final zabq zabq = (zabq) zaa.zan.get(zac);
        if (null != zabq) {
            zabq.zas(connectionResult);
        }
    }

    @WorkerThread
    public void zag(final int i2) {
        final zabq zabq = (zabq) zaa.zan.get(zac);
        if (null == zabq) {
            return;
        }
        if (zabq.zaj) {
            zabq.zas(new ConnectionResult(17));
        } else {
            zabq.onConnectionSuspended(i2);
        }
    }

    @WorkerThread
    public void zaf(@Nullable final IAccountAccessor iAccountAccessor, @Nullable final Set set) {
        if (null == iAccountAccessor || null == set) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            this.zae(new ConnectionResult(4));
            return;
        }
        zad = iAccountAccessor;
        zae = set;
        this.zah();
    }
}
