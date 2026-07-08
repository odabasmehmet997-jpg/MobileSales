package com.google.android.gms.tagmanager;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzbw(zzbx zzc, zzbx zzd, long zza, String zzb) implements Runnable {

    public void run() {
        if (this.zzc.zze == null) {
            zzbx zzbx = this.zzc;
            zzbx zzbx2 = this.zzd;
            zzfa zzg = zzfa.zzg();
            zzg.zzl(zzbx.zzf, zzbx2);
            this.zzc.zze = zzg.zzf();
        }
        zzbx zzbx3 = this.zzc;
        zzbx3.zze.zzb(this.zza, this.zzb);
    }
}
