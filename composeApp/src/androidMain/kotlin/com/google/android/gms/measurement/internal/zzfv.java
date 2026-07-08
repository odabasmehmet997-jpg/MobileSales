package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzfv(zzgl zzc, zzab zza, zzp zzb) implements Runnable {

    public void run() {
        this.zzc.zza.zzA();
        if (null == zza.zzc.zza()) {
            this.zzc.zza.zzN(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzT(this.zza, this.zzb);
        }
    }
}
