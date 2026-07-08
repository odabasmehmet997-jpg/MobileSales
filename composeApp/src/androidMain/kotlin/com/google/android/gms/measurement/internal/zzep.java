package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzep extends zzkh {
    public zzep(final zzkr zzkr) {
        super(zzkr);
    }

    public boolean zza() {
        this.zzW();
        final ConnectivityManager connectivityManager = (ConnectivityManager) zzs.zzau().getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (null != connectivityManager) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (final SecurityException unused) {
            }
        }
        return null != networkInfo && networkInfo.isConnected();
    }

    
    public boolean zzb() {
        return false;
    }
}
