package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zziw extends zzan {
    final zzjm zza;

    
    zziw(zzjm zzjm, zzgo zzgo) {
        super(zzgo);
        this.zza = zzjm;
    }

    public void zzc() {
        zzjm zzjm = this.zza;
        zzjm.zzg();
        if (zzjm.zzL()) {
            zzjm.zzs.zzay().zzj().zza("Inactivity, disconnecting from the service");
            zzjm.zzs();
        }
    }
}
