package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzag extends Api.AbstractClientBuilder {
    zzag() {
    }

    public Api.Client buildClient(final Context context, final Looper looper, final ClientSettings clientSettings, final Object obj, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        final Api.ApiOptions.NoOptions noOptions = (Api.ApiOptions.NoOptions) obj;
        return new zzg(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
