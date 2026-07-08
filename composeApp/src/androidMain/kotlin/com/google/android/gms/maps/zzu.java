package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.model.CameraPosition;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzu extends zzm {
    final GoogleMap.OnCameraChangeListener zza;

    public void zzb(final CameraPosition cameraPosition) {
        zza.onCameraChange(cameraPosition);
    }
}
