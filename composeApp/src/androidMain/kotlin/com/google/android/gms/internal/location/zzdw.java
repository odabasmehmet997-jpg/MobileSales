package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdw implements ListenerHolder.Notifier {
    final Location zza;

    zzdw(final zzdy zzdy, final Location location) {
        zza = location;
    }

    public void notifyListener(final Object obj) {
        ((LocationListener) obj).onLocationChanged(zza);
    }

    public void onNotifyListenerFailed() {
    }
}
