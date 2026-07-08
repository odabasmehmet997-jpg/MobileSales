package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzj;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzt extends zzj {
    final LocationSource zza;

    public void activate(final zzaj zzaj) {
        zza.activate(new zzl(this, zzaj));
    }

    public void deactivate() {
        zza.deactivate();
    }
}
