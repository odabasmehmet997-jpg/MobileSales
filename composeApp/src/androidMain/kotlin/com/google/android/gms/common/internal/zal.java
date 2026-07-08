package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.internal.ApiKey;

public final class zal {
    private final SparseIntArray zaa;
    private final GoogleApiAvailabilityLight zab;
    public zal() {
        this(GoogleApiAvailability.getInstance());
    }
    public int zaa(Context context, int i2) {
        return this.zaa.get(i2, -1);
    }
    public int zab( Context context,  Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        int i2 = 0;
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int zaa2 = zaa(context, minApkVersion);
        if (-1 == zaa2) {
            int i3 = 0;
            while (true) {
                if (i3 >= this.zaa.size()) {
                    i2 = -1;
                    break;
                }
                int keyAt = this.zaa.keyAt(i3);
                if (keyAt > minApkVersion && 0 == zaa.get(keyAt)) {
                    break;
                }
                i3++;
            }
            zaa2 = -1 == i2 ? this.zab.isGooglePlayServicesAvailable(context, minApkVersion) : i2;
            this.zaa.put(minApkVersion, zaa2);
        }
        return zaa2;
    }
    public void zac() {
        this.zaa.clear();
    }
    public zal( GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zaa = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zab = googleApiAvailabilityLight;
    }
    public Iterable<Object> zab() {
        return null;
    }

    public HasApiKey<Api.ApiOptions> zac(ApiKey apiKey, ConnectionResult connectionResult, String s) {
        return null;
    }
}
