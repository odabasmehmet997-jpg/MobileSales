package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzv;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzm extends zzy {
    final GoogleMap.OnGroundOverlayClickListener zza;

    public void zzb(final zzv zzv) {
        zza.onGroundOverlayClick(new GroundOverlay(zzv));
    }
}
