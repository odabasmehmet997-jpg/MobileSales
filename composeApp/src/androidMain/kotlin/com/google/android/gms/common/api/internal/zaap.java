package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaap extends zaav {
    final zaaw zaa;
    private final ArrayList zac;

    public zaap(final zaaw zaaw, final ArrayList arrayList) {
        super(zaaw, null);
        zaa = zaaw;
        zac = arrayList;
    }

    @WorkerThread
    public void zaa() {
        final zaaw zaaw = zaa;
        zaaw.zaa.zag.zad = com.google.android.gms.common.api.internal.zaaw.zao(zaaw);
        final ArrayList arrayList = zac;
        final int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            final zaaw zaaw2 = zaa;
            ((Api.Client) arrayList.get(i2)).getRemoteService(zaaw2.zao, zaaw2.zaa.zag.zad);
        }
    }
}
