package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingServiceConnection implements ServiceConnection {
    boolean zza;
    private final BlockingQueue zzb = new LinkedBlockingQueue();
    public IBinder getServiceWithTimeout(final long j2, final TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (!zza) {
            zza = true;
            final IBinder iBinder = (IBinder) zzb.poll(j2, timeUnit);
            if (iBinder != null) {
                return iBinder;
            }
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        throw new IllegalStateException("Cannot call get on this connection more than once");
    }
    public final void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
        zzb.add(iBinder);
    }
    public final void onServiceDisconnected(final ComponentName componentName) {
    }
}
