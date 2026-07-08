package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzak extends zzbk {
    final StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener zza;

    public void zzb(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        zza.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }
}
