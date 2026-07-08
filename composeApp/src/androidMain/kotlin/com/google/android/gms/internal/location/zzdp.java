package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.DeviceOrientation;
import com.google.android.gms.location.DeviceOrientationListener;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdp implements ListenerHolder.Notifier {
    final DeviceOrientation zza;

    zzdp(zzdq zzdq, DeviceOrientation deviceOrientation) {
        this.zza = deviceOrientation;
    }

    public void notifyListener(Object obj) {
        ((DeviceOrientationListener) obj).onDeviceOrientationChanged(this.zza);
    }

    public void onNotifyListenerFailed() {
    }
}
