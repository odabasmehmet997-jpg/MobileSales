package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzy;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdy extends zzy {
    private final zzdr zza;

    zzdy(zzdr zzdr) {
        this.zza = zzdr;
    }

    
    public zzdy zzc(ListenerHolder listenerHolder) {
        this.zza.zzb(listenerHolder);
        return this;
    }

    public void zzd(Location location) {
        this.zza.zza().notifyListener(new zzdw(this, location));
    }

    public void zze() {
        this.zza.zza().notifyListener(new zzdx(this));
    }

    
    public void zzf() {
        this.zza.zza().clear();
    }

    
    public zzdr zzg() {
        return this.zza;
    }
}
