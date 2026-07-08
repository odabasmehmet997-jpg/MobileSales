package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzal extends zzbo {
    final StreetViewPanorama.OnStreetViewPanoramaClickListener zza;

    public void zzb(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        zza.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}
