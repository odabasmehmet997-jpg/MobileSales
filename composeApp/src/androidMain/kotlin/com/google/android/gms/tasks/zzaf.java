package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzaf<T> implements zzae<T> {
    private final Object zza = new Object();
    private final int zzb;
    private final zzw zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private Exception zzg;
    private boolean zzh;

    public zzaf(int i2, zzw zzw) {
        this.zzb = i2;
        this.zzc = zzw;
    }

    private void zza() {
        if (this.zzd + this.zze + this.zzf != this.zzb) {
            return;
        }
        if (null != zzg) {
            zzw zzw = this.zzc;
            int i2 = this.zze;
            int i3 = this.zzb;
            zzw.zza(new ExecutionException(i2 + " out of " + i3 + " underlying tasks failed", this.zzg));
        } else if (this.zzh) {
            this.zzc.zzc();
        } else {
            this.zzc.zzb(null);
        }
    }

    public void onCanceled() {
        synchronized (this.zza) {
            this.zzf++;
            this.zzh = true;
            zza();
        }
    }

    public void onFailure(@NonNull Exception exc) {
        synchronized (this.zza) {
            this.zze++;
            this.zzg = exc;
            zza();
        }
    }

    public void onSuccess(T t) {
        synchronized (this.zza) {
            this.zzd++;
            zza();
        }
    }
}
