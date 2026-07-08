package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArraySet;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability; 
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.internal.zal;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.internal.base.zau;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;  
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
 
public class GoogleApiManager implements Handler.Callback { 
    public static final Status zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status zab = new Status(4, "The user must be signed in to make this API call.");
    public static final Object zac = new Object(); 
    private static GoogleApiManager zad;
    public long zae = WorkRequest.MIN_BACKOFF_MILLIS;
    public boolean zaf; 
    private TelemetryData zag; 
    private TelemetryLoggingClient zah;
    public final Context zai;
    public final GoogleApiAvailability zaj;
    public final zal zak;
    private final AtomicInteger zal = new AtomicInteger(1);
    private final AtomicInteger zam = new AtomicInteger(0);
    public final Map zan = new ConcurrentHashMap(5, 0.75f, 1);
    public zaae zao;
    public final Set zap = new ArraySet();
    private final Set zaq = new ArraySet(); 
    public final Handler zar;
    public volatile boolean zas = true; 
    private GoogleApiManager(final Context context, final Looper looper, final GoogleApiAvailability googleApiAvailability) {
        zai = context;
        final zau zau = new zau(looper, this);
        zar = zau;
        zaj = googleApiAvailability;
        zak = new zal(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            zas = false;
        }
        zau.sendMessage(zau.obtainMessage(6));
    }
    public static Status zaF(final ApiKey apiKey, final ConnectionResult connectionResult) {
        final String zaa2 = apiKey.zaa();
        final String valueOf = String.valueOf(connectionResult);
        return new Status(connectionResult, "API: " + zaa2 + " is not available on this device. Connection failed with: " + valueOf);
    }
    private final zabq zaG(final GoogleApi googleApi) {
        final Map map = zan;
        final ApiKey apiKey = googleApi.getApiKey();
        zabq zabq = (zabq) map.get(apiKey);
        if (null == zabq) {
            zabq = new zabq(this, googleApi);
            zan.put(apiKey, zabq);
        }
        if (zabq.zaA()) {
            zaq.add(apiKey);
        }
        zabq.zao();
        return zabq;
    }
    private final TelemetryLoggingClient zaH() {
        if (null == this.zah) {
            zah = TelemetryLogging.getClient(zai);
        }
        return zah;
    }
    private final void zaI() {
        final TelemetryData telemetryData = zag;
        if (null != telemetryData) {
            if (0 < telemetryData.zaa() || this.zaD()) {
                this.zaH().log(telemetryData);
            }
            zag = null;
        }
    }
    private final void zaJ(final TaskCompletionSource taskCompletionSource, final int i2, final GoogleApi googleApi) {
        final zacd zaa2;
        if (0 != i2 && null != (zaa2 = zacd.zaa(this, i2, googleApi.getApiKey()))) {
            final Task task = taskCompletionSource.getTask();
            final Handler handler = zar;
            handler.getClass();
            task.addOnCompleteListener(new zabk(handler), zaa2)
        }
    } 
    public static GoogleApiManager zak( final Context context) {
        final GoogleApiManager googleApiManager;
        synchronized (GoogleApiManager.zac) {
            try {
                if (null == zad) {
                    GoogleApiManager.zad = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.getOrStartHandlerThread().getLooper(), GoogleApiAvailability.getInstance());
                }
                googleApiManager = GoogleApiManager.zad;
            } catch (final Throwable th) {
                throw th;
            }
        }
        return googleApiManager;
    } 
    public final boolean handleMessage( final Message message) {
        final int i2 = message.what;
        long j2 = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
        zabq zabq = null;
        switch (i2) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j2 = WorkRequest.MIN_BACKOFF_MILLIS;
                }
                zae = j2;
                zar.removeMessages(12);
                for (final Object obtainMessage : zan.keySet()) {
                    final Handler handler = zar;
                    handler.sendMessageDelayed(handler.obtainMessage(12, obtainMessage), zae);
                }
                break;
            case 2:
                final com.google.android.gms.common.api.internal.zal zal2 = (com.google.android.gms.common.api.internal.zal) message.obj;
                final Iterator it = zal2.zab().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        final ApiKey apiKey = (ApiKey) it.next();
                        final zabq zabq2 = (zabq) zan.get(apiKey);
                        if (null == zabq2) {
                            zal2.zac(apiKey, new ConnectionResult(13), null);
                            break;
                        } else if (zabq2.zaz()) {
                            zal2.zac(apiKey, ConnectionResult.RESULT_SUCCESS, zabq2.zaf().getEndpointPackageName());
                        } else {
                            final ConnectionResult zad2 = zabq2.zad();
                            if (null != zad2) {
                                zal2.zac(apiKey, zad2, null);
                            } else {
                                zabq2.zat(zal2);
                                zabq2.zao();
                            }
                        }
                    }
                }
            case 3:
                for (final Object zabq3 : zan.values()) {
                    zabq3.zan();
                    zabq3.zao();
                }
                break;
            case 4:
            case 8:
            case 13:
                final zach zach = (zach) message.obj;
                zabq zabq4 = (zabq) zan.get(zach.zac().getApiKey());
                if (null == zabq4) {
                    zabq4 = this.zaG(zach.zac());
                }
                if (zabq4.zaA() && zam.get() != zach.zab()) {
                    zach.zaa().zad(GoogleApiManager.zaa);
                    zabq4.zav();
                    break;
                } else {
                    zabq4.zap(zach.zaa());
                    break;
                }
            case 5:
                final int i3 = message.arg1;
                final ConnectionResult connectionResult = (ConnectionResult) message.obj;
                final Iterator it2 = zan.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        final zabq zabq5 = (zabq) it2.next();
                        if (zabq5.zab() == i3) {
                            zabq = zabq5;
                        }
                    }
                }
                if (null != zabq) {
                    if (13 != connectionResult.getErrorCode()) {
                        zabq.zaE(GoogleApiManager.zaF(zabq.zad, connectionResult));
                        break;
                    } else {
                        final String errorString = zaj.getErrorString(connectionResult.getErrorCode());
                        final String errorMessage = connectionResult.getErrorMessage();
                        zabq.zaE(new Status(17, "Error resolution was canceled by the user, original error message: " + errorString + ": " + errorMessage));
                        break;
                    }
                } else {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i3 + " while trying to fail enqueued calls.", new Exception());
                    break;
                }
            case 6:
                if (zai.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize((Application) zai.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabl(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        zae = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
                        break;
                    }
                }
                break;
            case 7:
                this.zaG((GoogleApi) message.obj);
                break;
            case 9:
                if (zan.containsKey(message.obj)) {
                    ((zabq) zan.get(message.obj)).zau();
                    break;
                }
                break;
            case 10:
                for (final Object remove : zaq) {
                    final zabq zabq6 = (zabq) zan.remove(remove);
                    if (null != zabq6) {
                        zabq6.zav();
                    }
                }
                zaq.clear();
                break;
            case 11:
                if (zan.containsKey(message.obj)) {
                    ((zabq) zan.get(message.obj)).zaw();
                    break;
                }
                break;
            case 12:
                if (zan.containsKey(message.obj)) {
                    ((zabq) zan.get(message.obj)).zaB();
                    break;
                }
                break;
            case 14:
                final zaaf zaaf = (zaaf) message.obj;
                final ApiKey zaa2 = zaaf.zaa();
                if (zan.containsKey(zaa2)) {
                    zaaf.zab().setResult(Boolean.valueOf(((zabq) zan.get(zaa2)).zaO(false)));
                    break;
                } else {
                    zaaf.zab().setResult(Boolean.FALSE);
                    break;
                }
            case 15:
                final zabs zabs = (zabs) message.obj;
                if (zan.containsKey(zabs.zaa)) {
                    com.google.android.gms.common.api.internal.zabq.zal((zabq) zan.get(zabs.zaa), zabs);
                    break;
                }
                break;
            case 16:
                final zabs zabs2 = (zabs) message.obj;
                if (zan.containsKey(zabs2.zaa)) {
                    com.google.android.gms.common.api.internal.zabq.zam((zabq) zan.get(zabs2.zaa), zabs2);
                    break;
                }
                break;
            case 17:
                this.zaI();
                break;
            case 18:
                final zace zace = (zace) message.obj;
                if (0 != zace.zac()) {
                    final TelemetryData telemetryData = zag;
                    if (null != telemetryData) {
                        final List zab2 = telemetryData.zab();
                        if (telemetryData.zaa() != zace.zab() || (null != zab2 && zab2.size() >= zace.zad())) {
                            zar.removeMessages(17);
                            this.zaI();
                        } else {
                            zag.zac(zace.zaa());
                        }
                    }
                    if (null == this.zag) {
                        final ArrayList arrayList = new ArrayList();
                        arrayList.add(zace.zaa());
                        zag = new TelemetryData(zace.zab(), arrayList);
                        final Handler handler2 = zar;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), zace.zac());
                        break;
                    }
                } else {
                    this.zaH().log(new TelemetryData(zace.zab(), Collections.singletonList(zace.zaa())));
                    break;
                }
                break;
            case 19:
                zaf = false;
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i2);
                return false;
        }
        return true;
    }
    public final void zaA(final zaae zaae) {
        synchronized (GoogleApiManager.zac) {
            try {
                if (zao != zaae) {
                    zao = zaae;
                    zap.clear();
                }
                zap.addAll(zaae.zaa());
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    public final void zaB(final zaae zaae) {
        synchronized (GoogleApiManager.zac) {
            try {
                if (zao == zaae) {
                    zao = null;
                    zap.clear();
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    public final boolean zaD() {
        if (zaf) {
            return false;
        }
        final RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (null != config && !config.getMethodInvocationTelemetryEnabled()) {
            return false;
        }
        final int zaa2 = zak.zaa(zai, 203400000);
        return -1 == zaa2 || 0 == zaa2;
    }
    public final boolean zaE(final ConnectionResult connectionResult, final int i2) {
        return zaj.zah(zai, connectionResult, i2);
    }
    public final int zaa() {
        return zal.getAndIncrement();
    }
    public final zabq zai(final ApiKey apiKey) {
        return (zabq) zan.get(apiKey);
    }
    public final Task zao(final GoogleApi googleApi, final RegisterListenerMethod registerListenerMethod, final UnregisterListenerMethod unregisterListenerMethod, final Runnable runnable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zaJ(taskCompletionSource, registerListenerMethod.zaa(), googleApi);
        zar.sendMessage(zar.obtainMessage(8, new zach(new zaf(new zaci(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource), zam.get(), googleApi)));
        return taskCompletionSource.getTask();
    }
    public final Task zap(final GoogleApi googleApi, final ListenerHolder.ListenerKey listenerKey, final int i2) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zaJ(taskCompletionSource, i2, googleApi);
        zar.sendMessage(zar.obtainMessage(13, new zach(new zah(listenerKey, taskCompletionSource), zam.get(), googleApi)));
        return taskCompletionSource.getTask();
    }
    public final void zau(final GoogleApi googleApi, final int i2, final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zar.sendMessage(zar.obtainMessage(4, new zach(new zae(i2, apiMethodImpl), zam.get(), googleApi)));
    }
    public final void zav(final GoogleApi googleApi, final int i2, final TaskApiCall taskApiCall, final TaskCompletionSource taskCompletionSource, final StatusExceptionMapper statusExceptionMapper) {
        this.zaJ(taskCompletionSource, taskApiCall.zaa(), googleApi);
        zar.sendMessage(zar.obtainMessage(4, new zach(new zag(i2, taskApiCall, taskCompletionSource, statusExceptionMapper), zam.get(), googleApi)));
    }
    public final void zaw(final MethodInvocation methodInvocation, final int i2, final long j2, final int i3) {
        zar.sendMessage(zar.obtainMessage(18, new zace(methodInvocation, i2, j2, i3)));
    }
    public final void zax(final ConnectionResult connectionResult, final int i2) {
        if (!this.zaE(connectionResult, i2)) {
            final Handler handler = zar;
            handler.sendMessage(handler.obtainMessage(5, i2, 0, connectionResult));
        }
    }
    public final void zay() {
        final Handler handler = zar;
        handler.sendMessage(handler.obtainMessage(3));
    }
    public final void zaz(final GoogleApi googleApi) {
        final Handler handler = zar;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }
}
