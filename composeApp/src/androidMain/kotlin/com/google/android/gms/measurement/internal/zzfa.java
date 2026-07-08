package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbr;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfa implements ServiceConnection {
    final zzfb zza;
    
    public final String zzb;

    zzfa(zzfb zzfb, String str) {
        this.zza = zzfb;
        this.zzb = str;
    }

    @MainThread
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (null != iBinder) {
            try {
                zzbr zzb2 = zzbq.zzb(iBinder);
                if (null == zzb2) {
                    this.zza.zza.zzay().zzk().zza("Install Referrer Service implementation was not found");
                    return;
                }
                this.zza.zza.zzay().zzj().zza("Install Referrer Service connected");
                this.zza.zza.zzaz().zzp(new zzez(this, zzb2, this));
            } catch (RuntimeException e2) {
                this.zza.zza.zzay().zzk().zzb("Exception occurred while calling Install Referrer API", e2);
            }
        } else {
            this.zza.zza.zzay().zzk().zza("Install Referrer connection returned with null binder");
        }
    }

    @MainThread
    public void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzay().zzj().zza("Install Referrer Service disconnected");
    }
}
