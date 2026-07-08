package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.zal;
import com.google.errorprone.annotations.concurrent.GuardedBy;

import java.util.ArrayList;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaao extends zaav {
    final zaaw zaa;
    private final Map zac;

    public zaao(final zaaw zaaw, final Map map) {
        super(zaaw, null);
        zaa = zaaw;
        zac = map;
    }

    @WorkerThread
    @GuardedBy
    public void zaa() {
        final zal zal = new zal(zaa.zad);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (final Api.Client client : zac.keySet()) {
            if (!client.requiresGooglePlayServices() || ((zaal) zac.get(client)).zac) {
                arrayList2.add(client);
            } else {
                arrayList.add(client);
            }
        }
        int i2 = 0;
        int i3 = -1;
        if (!arrayList.isEmpty()) {
            final int size = arrayList.size();
            while (i2 < size) {
                i3 = zal.zab(zaa.zac, (Api.Client) arrayList.get(i2));
                i2++;
                if (0 != i3) {
                    break;
                }
            }
        } else {
            final int size2 = arrayList2.size();
            while (i2 < size2) {
                i3 = zal.zab(zaa.zac, (Api.Client) arrayList2.get(i2));
                i2++;
                if (0 == i3) {
                    break;
                }
            }
        }
        if (0 != i3) {
            final ConnectionResult connectionResult = new ConnectionResult(i3, null);
            final zaaw zaaw = zaa;
            zaaw.zaa.zal(new zaam(this, zaaw, connectionResult));
            return;
        }
        final zaaw zaaw2 = zaa;
        if (zaaw2.zam && null != zaaw2.zak) {
            zaaw2.zak.zab();
        }
        for (final Api.Client client2 : zac.keySet()) {
            final BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = (BaseGmsClient.ConnectionProgressReportCallbacks) zac.get(client2);
            if (!client2.requiresGooglePlayServices() || 0 == zal.zab(this.zaa.zac, client2)) {
                client2.connect(connectionProgressReportCallbacks);
            } else {
                final zaaw zaaw3 = zaa;
                zaaw3.zaa.zal(new zaan(this, zaaw3, connectionProgressReportCallbacks));
            }
        }
    }
}
