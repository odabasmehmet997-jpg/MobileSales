package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzf extends zzh {
    final GoogleMap.InfoWindowAdapter zza;

    public IObjectWrapper zzb(final zzah zzah) {
        return ObjectWrapper.wrap(zza.getInfoContents(new Marker(zzah)));
    }

    public IObjectWrapper zzc(final zzah zzah) {
        return ObjectWrapper.wrap(zza.getInfoWindow(new Marker(zzah)));
    }
}
