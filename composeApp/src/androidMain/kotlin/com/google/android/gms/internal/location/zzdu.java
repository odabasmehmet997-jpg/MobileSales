package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
record zzdu(zzdv zza) implements ListenerHolder.Notifier {

    public void notifyListener(final Object obj) {
        final LocationCallback locationCallback = (LocationCallback) obj;
        zza.zzh().zzc();
    }

    public void onNotifyListenerFailed() {
    }
}
