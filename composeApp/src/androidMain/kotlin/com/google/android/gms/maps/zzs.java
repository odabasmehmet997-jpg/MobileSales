package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzae;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.MapCapabilities;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzs extends zzak {
    final GoogleMap.OnMapCapabilitiesChangedListener zza;

    public void zzb(final zzae zzae) {
        zza.onMapCapabilitiesChanged(new MapCapabilities(zzae));
    }
}
