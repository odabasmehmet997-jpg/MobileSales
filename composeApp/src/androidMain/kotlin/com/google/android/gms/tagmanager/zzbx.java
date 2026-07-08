package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbx extends Thread {
    private static zzbx zza;
    private final LinkedBlockingQueue zzb = new LinkedBlockingQueue();
    private volatile boolean zzc = false;
    private final boolean zzd = false;
    
    public volatile zzby zze;
    
    public final Context zzf;

    private zzbx(Context context) {
        super("GAThread");
        if (context != null) {
            this.zzf = context.getApplicationContext();
        } else {
            this.zzf = null;
        }
        start();
    }

    static zzbx zzb(Context context) {
        if (zza == null) {
            zza = new zzbx(context);
        }
        return zza;
    }

    public void run() {
        while (true) {
            try {
                Runnable runnable = (Runnable) this.zzb.take();
                if (!this.zzc) {
                    runnable.run();
                }
            } catch (InterruptedException e2) {
                try {
                    zzdc.zzb.zzb(e2.toString());
                } catch (Exception e3) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(byteArrayOutputStream);
                    e3.printStackTrace(printStream);
                    printStream.flush();
                    Log.e("GoogleTagManager", "Error on Google TagManager Thread: ".concat(byteArrayOutputStream.toString()));
                    Log.e("GoogleTagManager", "Google TagManager is shutting down.");
                    this.zzc = true;
                }
            }
        }
    }

    public void zze(Runnable runnable) {
        this.zzb.add(runnable);
    }

    
    @VisibleForTesting
    public void zzf(String str, long j2) {
        this.zzb.add(new zzbw(this, this, j2, str));
    }
}
