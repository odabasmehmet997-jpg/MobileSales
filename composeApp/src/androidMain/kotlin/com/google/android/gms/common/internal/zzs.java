package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.common.zzi;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzs extends GmsClientSupervisor {
    
    @GuardedBy
    public final HashMap zzb = new HashMap();
    
    public final Context zzc;
    
    public volatile Handler zzd;
    private final zzr zze;
    
    public final ConnectionTracker zzf;
    private final long zzg;
    
    public final long zzh;
    @Nullable
    private final Executor zzi;

    zzs(Context context, Looper looper, @Nullable Executor executor) {
        zzr zzr = new zzr(this, null);
        this.zze = zzr;
        this.zzc = context.getApplicationContext();
        this.zzd = new zzi(looper, zzr);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
        this.zzh = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
        this.zzi = executor;
    }

    
    public void zza(zzo zzo, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzb) {
            try {
                zzp zzp = (zzp) this.zzb.get(zzo);
                if (null == zzp) {
                    String obj = zzo.toString();
                    throw new IllegalStateException("Nonexistent connection status for service config: " + obj);
                } else if (zzp.zzh(serviceConnection)) {
                    zzp.zzf(serviceConnection, str);
                    if (zzp.zzi()) {
                        this.zzd.sendMessageDelayed(this.zzd.obtainMessage(0, zzo), this.zzg);
                    }
                } else {
                    String obj2 = zzo.toString();
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + obj2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    
    public boolean zzc(zzo zzo, ServiceConnection serviceConnection, String str, @Nullable Executor executor) {
        boolean zzj;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzb) {
            try {
                zzp zzp = (zzp) this.zzb.get(zzo);
                if (null == executor) {
                    executor = this.zzi;
                }
                if (null == zzp) {
                    zzp = new zzp(this, zzo);
                    zzp.zzd(serviceConnection, serviceConnection, str);
                    zzp.zze(str, executor);
                    this.zzb.put(zzo, zzp);
                } else {
                    this.zzd.removeMessages(0, zzo);
                    if (!zzp.zzh(serviceConnection)) {
                        zzp.zzd(serviceConnection, serviceConnection, str);
                        int zza = zzp.zza();
                        if (1 == zza) {
                            serviceConnection.onServiceConnected(zzp.zzb(), zzp.zzc());
                        } else if (2 == zza) {
                            zzp.zze(str, executor);
                        }
                    } else {
                        String obj = zzo.toString();
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + obj);
                    }
                }
                zzj = zzp.zzj();
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzj;
    }
}
