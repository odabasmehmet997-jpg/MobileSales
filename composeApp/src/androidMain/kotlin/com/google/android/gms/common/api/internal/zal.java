package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zal {
    private final ArrayMap zaa;
    private final ArrayMap zab;
    private final TaskCompletionSource zac;
    private int zad;
    private boolean zae;

    public Set zab() {
        return zaa.keySet();
    }

    public void zac(final ApiKey apiKey, final ConnectionResult connectionResult, @Nullable final String str) {
        zaa.put(apiKey, connectionResult);
        zab.put(apiKey, str);
        zad--;
        if (!connectionResult.isSuccess()) {
            zae = true;
        }
        if (0 != this.zad) {
            return;
        }
        if (zae) {
            zac.setException(new AvailabilityException(zaa));
            return;
        }
        zac.setResult(zab);
    }
}
