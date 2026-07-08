package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzis(zzjm zzc, zzp zza, zzcf zzb) implements Runnable {

    public void run() {
        zzft zzft;
        String str = null;
        try {
            if (!this.zzc.zzs.zzm().zzc().zzi(zzag.ANALYTICS_STORAGE)) {
                this.zzc.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzs.zzq().zzO(null);
                this.zzc.zzs.zzm().zze.zzb(null);
                zzft = this.zzc.zzs;
            } else {
                zzjm zzjm = this.zzc;
                zzdz zzh = zzjm.zzb;
                if (null == zzh) {
                    zzjm.zzs.zzay().zzd().zza("Failed to get app instance id");
                    zzft = this.zzc.zzs;
                } else {
                    Preconditions.checkNotNull(this.zza);
                    str = zzh.zzd(this.zza);
                    if (null != str) {
                        this.zzc.zzs.zzq().zzO(str);
                        this.zzc.zzs.zzm().zze.zzb(str);
                    }
                    this.zzc.zzQ();
                    zzft = this.zzc.zzs;
                }
            }
        } catch (RemoteException e2) {
            this.zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e2);
            zzft = this.zzc.zzs;
        } catch (Throwable th) {
            this.zzc.zzs.zzv().zzU(this.zzb, null);
            throw th;
        }
        zzft.zzv().zzU(this.zzb, str);
    }
}
