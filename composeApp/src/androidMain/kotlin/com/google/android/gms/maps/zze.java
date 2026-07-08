package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zze extends zzae {
    final GoogleMap.OnInfoWindowCloseListener zza;

    public void zzb(final zzah zzah) {
        zza.onInfoWindowClose(new Marker(zzah));
    }
}
