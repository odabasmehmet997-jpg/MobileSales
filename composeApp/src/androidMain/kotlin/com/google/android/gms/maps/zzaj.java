package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzaj extends zzbm {
    final StreetViewPanorama.OnStreetViewPanoramaChangeListener zza;

    public void zzb(final StreetViewPanoramaLocation streetViewPanoramaLocation) {
        zza.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
