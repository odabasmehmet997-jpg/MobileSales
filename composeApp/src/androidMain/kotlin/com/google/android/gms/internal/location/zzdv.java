package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdv extends zzv {
    private final zzdr zza;

    zzdv(zzdr zzdr) {
        this.zza = zzdr;
    }

    
    public zzdv zzc(ListenerHolder listenerHolder) {
        this.zza.zzb(listenerHolder);
        return this;
    }

    public void zzd(LocationResult locationResult) throws RemoteException {
        this.zza.zza().notifyListener(new zzds(this, locationResult));
    }

    public void zze(LocationAvailability locationAvailability) throws RemoteException {
        this.zza.zza().notifyListener(new zzdt(this, locationAvailability));
    }

    public void zzf() {
        this.zza.zza().notifyListener(new zzdu(this));
    }

    
    public void zzg() {
        this.zza.zza().clear();
    }

    
    public zzdr zzh() {
        return this.zza;
    }
}
