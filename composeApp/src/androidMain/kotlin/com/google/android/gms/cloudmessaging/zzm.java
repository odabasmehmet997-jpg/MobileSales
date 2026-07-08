package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class zzm implements ServiceConnection {
    zzn zzc;
    final  zzs zzf;
    int zza = 0;
    final Messenger zzb = new Messenger(new com.google.android.gms.internal.cloudmessaging.zzf(Looper.getMainLooper(), new Handler.Callback() {
        private zzm zza;

        public   boolean handleMessage(Message message) {
            zzm zzmVar = this.zza;
            int i2 = message.arg1;
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String sb = "Received response to request: " +
                        i2;
                Log.d("MessengerIpcClient", sb);
            }
            synchronized (zzmVar) {
                zzp<?> zzpVar = zzmVar.zze.get(i2);
                if (zzpVar == null) {
                    StringBuilder sb2 = new StringBuilder(50);
                    sb2.append("Received response for unknown request: ");
                    sb2.append(i2);
                    Log.w("MessengerIpcClient", sb2.toString());
                    return true;
                }
                zzmVar.zze.remove(i2);
                zzmVar.zzf();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzpVar.zzc(new zzq(4, "Not supported by GmsCore", null));
                    return true;
                }
                zzpVar.zza(data);
                return true;
            }
        }
    }));
    final Queue<zzp<?>> zzd = new ArrayDeque();
    final SparseArray<zzp<?>> zze = new SparseArray<>();
    zzm(zzs zzsVar, zzl zzlVar) {
        this.zzf = zzsVar;
    }
    public   void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.zzf.zzc.execute(new Runnable() {
            private zzm zza;

            public   void run() {
                zzm zzmVar = this.zza;
                IBinder iBinder2 = iBinder;
                synchronized (zzmVar) {
                    if (iBinder2 == null) {
                        zzmVar.zza(0, "Null service connection");
                        return;
                    }
                    try {
                        zzmVar.zzc = new zzn(iBinder2);
                        zzmVar.zza = 2;
                        zzmVar.zzc();
                    } catch (RemoteException e2) {
                        zzmVar.zza(0, e2.getMessage());
                    }
                }
            }
        });
    }
    public   void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.zzf.zzc.execute(new Runnable() {
            private AdvertisingIdClient zza;

            public   void run() {
                this.zza.zza(2, "Service disconnected");
            }
        });
    }
    synchronized void zza(int i2, @Nullable String str) {
        zzb(i2, str, null);
    }
    synchronized void zzb(int i2, @Nullable String str, @Nullable Throwable th) {
        try {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(str);
                Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : "Disconnected: ");
            }
            int i3 = this.zza;
            if (i3 == 0) {
                throw new IllegalStateException();
            }
            if (i3 != 1 && i3 != 2) {
                if (i3 != 3) {
                    return;
                }
                this.zza = 4;
                return;
            }
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Unbinding service");
            }
            this.zza = 4;
            ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
            zzq zzqVar = new zzq(i2, str, th);
            Iterator<zzp<?>> it = this.zzd.iterator();
            while (it.hasNext()) {
                it.next().zzc(zzqVar);
            }
            this.zzd.clear();
            for (int i4 = 0; i4 < this.zze.size(); i4++) {
                this.zze.valueAt(i4).zzc(zzqVar);
            }
            this.zze.clear();
        } catch (Throwable th2) {
            throw th2;
        }
    }
    void zzc() {
        this.zzf.zzc.execute(new Runnable() {
            public void run() {
                zzp<?> zzpVarPoll;
                final zzm zzmVar = this.zza;
                while (true) {
                    synchronized (zzmVar) {
                        try {
                            if (zzmVar.zza != 2) {
                                return;
                            }
                            if (zzmVar.zzd.isEmpty()) {
                                zzmVar.zzf();
                                return;
                            } else {
                                zzpVarPoll = zzmVar.zzd.poll();
                                zzmVar.zze.put(zzpVarPoll.zza, zzpVarPoll);
                                zzp<?> finalZzpVarPoll = zzpVarPoll;
                                zzmVar.zzf.zzc.schedule(new Runnable() {
                                    public void run() {
                                        zzmVar.zze(finalZzpVarPoll.zza);
                                    }
                                }, 30L, TimeUnit.SECONDS);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String strValueOf = String.valueOf(zzpVarPoll);
                        String sb = "Sending " +
                                strValueOf;
                        Log.d("MessengerIpcClient", sb);
                    }
                    Context context = zzmVar.zzf.zzb;
                    Messenger messenger = zzmVar.zzb;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = zzpVarPoll.zzc;
                    messageObtain.arg1 = zzpVarPoll.zza;
                    messageObtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzpVarPoll.zzb());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, zzpVarPoll.zzd);
                    messageObtain.setData(bundle);
                    try {
                        zzmVar.zzc.zza(messageObtain);
                    } catch (RemoteException e2) {
                        zzmVar.zza(2, e2.getMessage());
                    }
                }
            }
        });
    }
    synchronized void zzd() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }
    synchronized void zze(int i2) {
        zzp<?> zzpVar = this.zze.get(i2);
        if (zzpVar != null) {
            String sb = "Timing out request: " +
                    i2;
            Log.w("MessengerIpcClient", sb);
            this.zze.remove(i2);
            zzpVar.zzc(new zzq(3, "Timed out waiting for response", null));
            zzf();
        }
    }
    synchronized void zzf() {
        try {
            if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
                }
                this.zza = 3;
                ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
    synchronized boolean zzg(zzp<?> zzpVar) {
        int i2 = this.zza;
        if (i2 != 0) {
            if (i2 == 1) {
                this.zzd.add(zzpVar);
                return true;
            }
            if (i2 != 2) {
                return false;
            }
            this.zzd.add(zzpVar);
            zzc();
            return true;
        }
        this.zzd.add(zzpVar);
        Preconditions.checkState(this.zza == 0);
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Starting bind to GmsCore");
        }
        this.zza = 1;
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        try {
            if (ConnectionTracker.getInstance().bindService(this.zzf.zzb, intent, this, 1)) {
                this.zzf.zzc.schedule(this::zzd, 30L, TimeUnit.SECONDS);
            } else {
                zza(0, "Unable to bind to service");
            }
        } catch (SecurityException e2) {
            zzb(0, "Unable to bind to service", e2);
        }
        return true;
    }
}
