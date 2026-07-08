package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdt implements ListenerHolder.Notifier {
    final LocationAvailability zza;

    zzdt(final zzdv zzdv, final LocationAvailability locationAvailability) {
        zza = locationAvailability;
    }

    public void notifyListener(final Object obj) {
        ((LocationCallback) obj).onLocationAvailability(zza);
    }

    public void onNotifyListenerFailed() {
    }
}
