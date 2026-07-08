package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaae extends zap {
    private final ArraySet zad = new ArraySet();
    private final GoogleApiManager zae;

    @VisibleForTesting
    zaae(final LifecycleFragment lifecycleFragment, final GoogleApiManager googleApiManager, final GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        zae = googleApiManager;
        mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    @MainThread
    public static void zad(final Activity activity, final GoogleApiManager googleApiManager, final ApiKey apiKey) {
        final LifecycleFragment fragment = getFragment(activity);
        zaae zaae = fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zaae.class);
        if (null == zaae) {
            zaae = new zaae(fragment, googleApiManager, GoogleApiAvailability.getInstance());
        }
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        zaae.zad.add(apiKey);
        googleApiManager.zaA(zaae);
    }

    private void zae() {
        if (!zad.isEmpty()) {
            zae.zaA(this);
        }
    }

    public void onResume() {
        super.onResume();
        this.zae();
    }

    public void onStart() {
        super.onStart();
        this.zae();
    }

    public void onStop() {
        super.onStop();
        zae.zaB(this);
    }

    
    public ArraySet zaa() {
        return zad;
    }

    
    public void zab(final ConnectionResult connectionResult, final int i2) {
        zae.zax(connectionResult, i2);
    }

    
    public void zac() {
        zae.zay();
    }
}
