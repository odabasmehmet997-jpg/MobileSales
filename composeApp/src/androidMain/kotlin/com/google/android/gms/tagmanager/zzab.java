package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzab(zzag zza) implements zzdb {
    public void zza(int i2) {
        if (i2 == 4) {
            this.zza.zzi.zzc();
        }
        synchronized (this.zza) {
            try {
                if (!this.zza.isReady()) {
                    if (this.zza.zzk != null) {
                        zzag zzag = this.zza;
                        zzag.setResult(zzag.zzk);
                    } else {
                        zzag zzag2 = this.zza;
                        zzag2.setResult(zzag2.createFailedResult(Status.RESULT_TIMEOUT));
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.zza.zzr(this.zza.zzi.zzb());
    }
}
