package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
record zzdx(zzdy zza) implements ListenerHolder.Notifier {

    public void notifyListener(final Object obj) {
        final LocationListener locationListener = (LocationListener) obj;
        zza.zzg().zzc();
    }

    public void onNotifyListenerFailed() {
    }
}
