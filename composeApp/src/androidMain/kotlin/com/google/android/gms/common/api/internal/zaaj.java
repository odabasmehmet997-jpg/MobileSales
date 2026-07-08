package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaaj implements zabf {
    
    public final zabi zaa;
    private boolean zab;

    public zaaj(final zabi zabi) {
        zaa = zabi;
    }

    public BaseImplementation.ApiMethodImpl zab(final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        try {
            zaa.zag.zai.zaa(apiMethodImpl);
            final zabe zabe = zaa.zag;
            final Api.Client client = (Api.Client) zabe.zac.get(apiMethodImpl.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !zaa.zab.containsKey(apiMethodImpl.getClientKey())) {
                apiMethodImpl.run(client);
            } else {
                apiMethodImpl.setFailedResult(new Status(17));
            }
        } catch (final DeadObjectException unused) {
            zaa.zal(new zaah(this, this));
        }
        return apiMethodImpl;
    }

    public void zad() {
    }

    public void zae() {
        if (zab) {
            zab = false;
            zaa.zal(new zaai(this, this));
        }
    }

    
    public void zaf() {
        if (zab) {
            zab = false;
            zaa.zag.zai.zab();
            this.zaj();
        }
    }

    public void zag(@Nullable final Bundle bundle) {
    }

    public void zah(final ConnectionResult connectionResult, final Api api, final boolean z) {
    }

    public void zai(final int i2) {
        zaa.zak(null);
        zaa.zah.zac(i2, zab);
    }

    public boolean zaj() {
        if (zab) {
            return false;
        }
        final Set<zada> set = zaa.zag.zah;
        if (null == set || set.isEmpty()) {
            zaa.zak(null);
            return true;
        }
        zab = true;
        for (final zada zah : set) {
            zah.zah();
        }
        return false;
    }
}
