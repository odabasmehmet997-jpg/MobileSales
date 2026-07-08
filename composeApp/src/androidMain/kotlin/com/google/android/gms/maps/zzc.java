package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzc extends zzac {
    final GoogleMap.OnInfoWindowClickListener zza;

    public void zzb(final zzah zzah) {
        zza.onInfoWindowClick(new Marker(zzah));
    }
}
