package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzds implements ListenerHolder.Notifier {
    final LocationResult zza;

    zzds(zzdv zzdv, LocationResult locationResult) {
        this.zza = locationResult;
    }

    public void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationResult(this.zza);
    }

    public void onNotifyListenerFailed() {
    }
}
