package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzfw(zzgl zzb, zzab zza) implements Runnable {

    public void run() {
        this.zzb.zza.zzA();
        if (null == zza.zzc.zza()) {
            this.zzb.zza.zzM(this.zza);
        } else {
            this.zzb.zza.zzS(this.zza);
        }
    }
}
