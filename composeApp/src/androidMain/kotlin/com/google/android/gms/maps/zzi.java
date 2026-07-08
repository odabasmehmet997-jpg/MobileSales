package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.internal.zzbc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzi extends zzbc {
    final GoogleMap.OnMyLocationClickListener zza;

    public void zzb(final Location location) {
        zza.onMyLocationClick(location);
    }
}
