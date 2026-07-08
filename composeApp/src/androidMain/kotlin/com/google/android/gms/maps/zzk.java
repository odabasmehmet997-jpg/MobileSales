package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzy;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzk extends zzaa {
    final GoogleMap.OnIndoorStateChangeListener zza;

    public void zzb() {
        zza.onIndoorBuildingFocused();
    }

    public void zzc(final zzy zzy) {
        zza.onIndoorLevelActivated(new IndoorBuilding(zzy));
    }
}
