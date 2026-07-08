package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzr implements Handler.Callback {
    final zzs zza;

    zzr(zzs zzs, zzq zzq) {
        this.zza = zzs;
    }

    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (0 == i2) {
            synchronized (this.zza.zzb) {
                try {
                    zzo zzo = (zzo) message.obj;
                    zzp zzp = (zzp) this.zza.zzb.get(zzo);
                    if (null != zzp && zzp.zzi()) {
                        if (zzp.zzj()) {
                            zzp.zzg("GmsClientSupervisor");
                        }
                        this.zza.zzb.remove(zzo);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } else if (1 != i2) {
            return false;
        } else {
            synchronized (this.zza.zzb) {
                try {
                    zzo zzo2 = (zzo) message.obj;
                    zzp zzp2 = (zzp) this.zza.zzb.get(zzo2);
                    if (null != zzp2 && 3 == zzp2.zza()) {
                        String valueOf = String.valueOf(zzo2);
                        Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + valueOf, new Exception());
                        ComponentName zzb = zzp2.zzb();
                        if (null == zzb) {
                            zzb = zzo2.zza();
                        }
                        if (null == zzb) {
                            String zzc = zzo2.zzc();
                            Preconditions.checkNotNull(zzc);
                            zzb = new ComponentName(zzc, EnvironmentCompat.MEDIA_UNKNOWN);
                        }
                        zzp2.onServiceDisconnected(zzb);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return true;
        }
    }
}
