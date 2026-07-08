package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.errorprone.annotations.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaam extends zabg {
    final ConnectionResult zaa;
    final zaao zab;

    zaam(final zaao zaao, final zabf zabf, final ConnectionResult connectionResult) {
        super(zabf);
        zab = zaao;
        zaa = connectionResult;
    }

    public void zaa() {
        zab.zaa.zaD(zaa);
    }
}
