package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzba;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzg extends zzba {
    final GoogleMap.OnMyLocationChangeListener zza;

    public void zzb(final IObjectWrapper iObjectWrapper) {
        zza.onMyLocationChange(ObjectWrapper.unwrap(iObjectWrapper));
    }
}
