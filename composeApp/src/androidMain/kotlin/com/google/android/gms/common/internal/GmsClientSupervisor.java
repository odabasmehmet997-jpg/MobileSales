package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class GmsClientSupervisor {
    @VisibleForTesting
    @Nullable
    static HandlerThread zza;
    private static final Object zzb = new Object();
    @Nullable
    private static zzs zzc;
    @Nullable
    private static final Executor zzd;
    private static final boolean zze;

    @KeepForSdk
    public static int getDefaultBindFlags() {
        return 4225;
    }

    @NonNull
    @KeepForSdk
    public static GmsClientSupervisor getInstance(@NonNull Context context) {
        Looper looper;
        synchronized (zzb) {
            try {
                if (null == GmsClientSupervisor.zzc) {
                    Context applicationContext = context.getApplicationContext();
                    if (zze) {
                        looper = getOrStartHandlerThread().getLooper();
                    } else {
                        looper = context.getMainLooper();
                    }
                    zzc = new zzs(applicationContext, looper, zzd);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzc;
    }

    @NonNull
    @KeepForSdk
    public static HandlerThread getOrStartHandlerThread() {
        synchronized (zzb) {
            try {
                HandlerThread handlerThread = zza;
                if (null != handlerThread) {
                    return handlerThread;
                }
                HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
                zza = handlerThread2;
                handlerThread2.start();
                HandlerThread handlerThread3 = zza;
                return handlerThread3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    
    public abstract void zza(zzo zzo, ServiceConnection serviceConnection, String str);

    public final void zzb(@NonNull String str, @NonNull String str2, int i2, @NonNull ServiceConnection serviceConnection, @NonNull String str3, boolean z) {
        zza(new zzo(str, str2, 4225, z), serviceConnection, str3);
    }

    
    public abstract boolean zzc(zzo zzo, ServiceConnection serviceConnection, String str, @Nullable Executor executor);
}
