package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzcf;

public class zzix(zzjm zzd, zzau zza, String zzb, zzcf zzc) implements Runnable {

    public void run() {
        zzft zzft;
        byte[] bArr = null;
        try {
            final zzjm zzjm = zzd;
            final zzdz zzh = zzjm.zzb;
            if (null == zzh) {
                zzjm.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                zzft = zzd.zzs;
            } else {
                bArr = zzh.zzu(zza, zzb);
                zzd.zzQ();
                zzft = zzd.zzs;
            }
        } catch (final RemoteException e2) {
            zzd.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e2);
            zzft = zzd.zzs;
        } catch (final Throwable th) {
            zzd.zzs.zzv().zzR(zzc, bArr);
            throw th;
        }
        zzft.zzv().zzR(zzc, bArr);
    }
}
