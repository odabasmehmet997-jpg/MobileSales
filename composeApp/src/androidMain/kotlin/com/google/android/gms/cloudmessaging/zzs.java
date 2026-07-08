package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzs {
    private static zzs zza;
    public final Context zzb;
    public final ScheduledExecutorService zzc;
    private zzm zzd = new zzm(this, null);
    private static int zze = 1;
    zzs(final Context context, final ScheduledExecutorService scheduledExecutorService) {
        zzc = scheduledExecutorService;
        zzb = context.getApplicationContext();
    }
    public static synchronized zzs zzb(final Context context) {
        zzs zzs;
        synchronized (zzs.class) {
            try {
                if (null == zza) {
                    zza = new zzs(context, Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, new NamedThreadFactory("MessengerIpcClient"))));
                }
                zzs = zza;
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzs;
    }
    private synchronized int zzf() {
        final int i2;
        i2 = zze;
        zze = i2 + 1;
        return i2;
    }
    private synchronized <T> Task<T> zzg(final zzp<T> zzp) {
        try {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                final String valueOf = String.valueOf(zzp);
                String sb = "Queueing " +
                        valueOf;
                Log.d("MessengerIpcClient", sb);
            }
            if (!zzd.zzg(zzp)) {
                final zzm zzm = new zzm(this, null);
                zzd = zzm;
                zzm.zzg(zzp);
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
        return zzp.zzb.getTask();
    }
    public Task<Void> zzc(final int i2, final Bundle bundle) {
        return this.zzg(new zzo(this.zzf(), 2, bundle));
    }
    public Task<Bundle> zzd(final int i2, final Bundle bundle) {
        return this.zzg(new zzr(this.zzf(), 1, bundle));
    }
}
