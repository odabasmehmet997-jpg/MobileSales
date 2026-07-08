package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rpc {
    private static int zza;
    private static PendingIntent zzb;
    private static final Executor zzc = new Executor() {
        public   void execute(Runnable runnable) {
            runnable.run();
        }
    };
    private static final Pattern zzd = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)");
    private final Context zzf;
    private final zzt zzg;
    private final ScheduledExecutorService zzh;
    private Messenger zzj;
    private zzd zzk;
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zze = new SimpleArrayMap<>();
    private final Messenger zzi = new Messenger(new zzaa(this, Looper.getMainLooper()));
    public Rpc(Context context) {
        this.zzf = context;
        this.zzg = new zzt(context);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(60L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.zzh = scheduledThreadPoolExecutor;
    }
    static   Task zza(Bundle bundle) throws Exception {
        return zzi(bundle) ? Tasks.forResult(null) : Tasks.forResult(bundle);
    }
    static  void zzc(Rpc rpc, Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new zzc());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof zzd) {
                        rpc.zzk = (zzd) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        rpc.zzj = (Messenger) parcelableExtra;
                    }
                }
                Intent intent2 = (Intent) message.obj;
                String action = intent2.getAction();
                if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                    if (Log.isLoggable("Rpc", 3)) {
                        String strValueOf = String.valueOf(action);
                        Log.d("Rpc", strValueOf.length() != 0 ? "Unexpected response action: ".concat(strValueOf) : "Unexpected response action: ");
                        return;
                    }
                    return;
                }
                String stringExtra = intent2.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent2.getStringExtra("unregistered");
                }
                if (stringExtra != null) {
                    Matcher matcher = zzd.matcher(stringExtra);
                    if (!matcher.matches()) {
                        if (Log.isLoggable("Rpc", 3)) {
                            Log.d("Rpc", stringExtra.length() != 0 ? "Unexpected response string: ".concat(stringExtra) : "Unexpected response string: ");
                            return;
                        }
                        return;
                    }
                    String strGroup = matcher.group(1);
                    String strGroup2 = matcher.group(2);
                    if (strGroup != null) {
                        Bundle extras = intent2.getExtras();
                        extras.putString("registration_id", strGroup2);
                        rpc.zzh(strGroup, extras);
                        return;
                    }
                    return;
                }
                String stringExtra2 = intent2.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                if (stringExtra2 == null) {
                    String strValueOf2 = String.valueOf(intent2.getExtras());
                    String sb = "Unexpected response, no error or registration id " +
                            strValueOf2;
                    Log.w("Rpc", sb);
                    return;
                }
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", stringExtra2.length() != 0 ? "Received InstanceID error ".concat(stringExtra2) : "Received InstanceID error ");
                }
                if (!stringExtra2.startsWith("|")) {
                    synchronized (rpc.zze) {
                        for (int i2 = 0; i2 < rpc.zze.size(); i2++) {
                            try {
                                rpc.zzh(rpc.zze.keyAt(i2), intent2.getExtras());
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return;
                }
                String[] strArrSplit = stringExtra2.split("\\|");
                if (strArrSplit.length <= 2 || !"ID".equals(strArrSplit[1])) {
                    Log.w("Rpc", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : "Unexpected structured response ");
                    return;
                }
                String str = strArrSplit[2];
                String strSubstring = strArrSplit[3];
                if (strSubstring.startsWith(":")) {
                    strSubstring = strSubstring.substring(1);
                }
                rpc.zzh(str, intent2.putExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR, strSubstring).getExtras());
                return;
            }
        }
        Log.w("Rpc", "Dropping invalid message");
    } 
    private   com.google.android.gms.tasks.Task<android.os.Bundle> zze(android.os.Bundle r8) {
         
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.Rpc.zze(android.os.Bundle):com.google.android.gms.tasks.Task");
    }
    private static synchronized String zzf() {
        int i2;
        i2 = zza;
        zza = i2 + 1;
        return Integer.toString(i2);
    }
    private static synchronized void zzg(Context context, Intent intent) {
        try {
            if (zzb == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzb = com.google.android.gms.internal.cloudmessaging.zza.zza(context, 0, intent2, com.google.android.gms.internal.cloudmessaging.zza.zza);
            }
            intent.putExtra("app", zzb);
        } catch (Throwable th) {
            throw th;
        }
    }
    private final void zzh(String str, Bundle bundle) {
        synchronized (this.zze) {
            try {
                TaskCompletionSource<Bundle> taskCompletionSourceRemove = this.zze.remove(str);
                if (taskCompletionSourceRemove != null) {
                    taskCompletionSourceRemove.setResult(bundle);
                } else {
                    String strValueOf = String.valueOf(str);
                    Log.w("Rpc", strValueOf.length() != 0 ? "Missing callback for ".concat(strValueOf) : "Missing callback for ");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    private static boolean zzi(Bundle bundle) {
        return bundle != null && bundle.containsKey("google.messenger");
    }
    public Task<Bundle> send(final Bundle bundle) {
        return (this.zzg.zza() < 12000000) ? ((this.zzg.zzb() == 0) ? Tasks.forException(new IOException("MISSING_INSTANCEID_SERVICE")) : zze(bundle).continueWithTask(zzc, new Continuation() {
            public Object then(Task task) {
                try {
                    return zza;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        })) : zzs.zzb(this.zzf).zzd(1, bundle).continueWith(zzc, new Continuation() {
            public Object then(Task task) throws IOException {
                try {
                    if (task.isSuccessful()) {
                        return task.getResult();
                    }
                    if (Log.isLoggable("Rpc", 3)) {
                        String strValueOf = String.valueOf(task.getException());
                        String sb = "Error making request: " +
                                strValueOf;
                        Log.d("Rpc", sb);
                    }
                    throw new IOException("SERVICE_NOT_AVAILABLE", task.getException());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public final   Task zzb(Bundle bundle, Task task) throws Exception {
        try {
            return (task.isSuccessful() && zzi((Bundle) task.getResult())) ? zze(bundle).onSuccessTask(zzc, new SuccessContinuation() {
                public Task then(Object obj) {
                    try {
                        return Rpc.zza((Bundle) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }) : task;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public final   void zzd(String str, ScheduledFuture scheduledFuture, Task task) {
        try {
            synchronized (this.zze) {
                this.zze.remove(str);
            }
            scheduledFuture.cancel(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
