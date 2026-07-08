package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;

import java.util.ArrayList;

import static android.text.TextUtils.join;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class AvailabilityException extends Exception {
    private final ArrayMap zaa;

    public AvailabilityException(@NonNull final ArrayMap arrayMap) {
        zaa = arrayMap;
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull final GoogleApi<? extends Api.ApiOptions> googleApi) {
        final ArrayMap arrayMap = zaa;
        final ApiKey<? extends Api.ApiOptions> apiKey = googleApi.getApiKey();
        final Object obj = arrayMap.get(apiKey);
        final String zaa2 = apiKey.zaa();
        Preconditions.checkArgument(null != obj, "The given API (" + zaa2 + ") was not part of the availability request.");
        return Preconditions.checkNotNull((ConnectionResult) zaa.get(apiKey));
    }

    @NonNull
    public String getMessage() {
        final ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (final ApiKey apiKey : zaa.keySet()) {
            final ConnectionResult connectionResult = Preconditions.checkNotNull((ConnectionResult) zaa.get(apiKey));
            z &= !connectionResult.isSuccess();
            arrayList.add(apiKey.zaa() + ": " + connectionResult);
        }
        final StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(join("; ", arrayList));
        return sb.toString();
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull final HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        final ArrayMap arrayMap = zaa;
        final ApiKey<? extends Api.ApiOptions> apiKey = hasApiKey.getApiKey();
        final Object obj = arrayMap.get(apiKey);
        final String zaa2 = apiKey.zaa();
        Preconditions.checkArgument(null != obj, "The given API (" + zaa2 + ") was not part of the availability request.");
        return Preconditions.checkNotNull((ConnectionResult) zaa.get(apiKey));
    }
}
