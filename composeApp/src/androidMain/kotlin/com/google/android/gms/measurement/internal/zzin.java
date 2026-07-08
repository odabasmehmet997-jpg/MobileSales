package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.List;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 * @param zzf synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzin(zzjm zzf, String zza, String zzb, zzp zzc, boolean zzd, zzcf zze) implements Runnable {

    public void run() {
        Throwable th;
        Bundle bundle;
        RemoteException e2;
        Bundle bundle2 = new Bundle();
        try {
            zzjm zzjm = this.zzf;
            zzdz zzh = zzjm.zzb;
            if (null == zzh) {
                zzjm.zzs.zzay().zzd().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);
                this.zzf.zzs.zzv().zzQ(this.zze, bundle2);
                return;
            }
            Preconditions.checkNotNull(this.zzc);
            List<zzku> zzh2 = zzh.zzh(this.zza, this.zzb, this.zzd, this.zzc);
            bundle = new Bundle();
            if (null != zzh2) {
                for (zzku zzku : zzh2) {
                    String str = zzku.zze;
                    if (null != str) {
                        bundle.putString(zzku.zzb, str);
                    } else {
                        Long l = zzku.zzd;
                        if (null != l) {
                            bundle.putLong(zzku.zzb, l.longValue());
                        } else {
                            Double d2 = zzku.zzg;
                            if (null != d2) {
                                bundle.putDouble(zzku.zzb, d2.doubleValue());
                            }
                        }
                    }
                }
            }
            try {
                this.zzf.zzQ();
                this.zzf.zzs.zzv().zzQ(this.zze, bundle);
            } catch (RemoteException e3) {
                e2 = e3;
                try {
                    this.zzf.zzs.zzay().zzd().zzc("Failed to get user properties; remote exception", this.zza, e2);
                    this.zzf.zzs.zzv().zzQ(this.zze, bundle);
                } catch (Throwable th2) {
                    th = th2;
                    bundle2 = bundle;
                    this.zzf.zzs.zzv().zzQ(this.zze, bundle2);
                    throw th;
                }
            }
        } catch (RemoteException e4) {
            bundle = bundle2;
            e2 = e4;
            this.zzf.zzs.zzay().zzd().zzc("Failed to get user properties; remote exception", this.zza, e2);
            this.zzf.zzs.zzv().zzQ(this.zze, bundle);
        } catch (Throwable th3) {
            th = th3;
            this.zzf.zzs.zzv().zzQ(this.zze, bundle2);
            throw th;
        }
    }
}
