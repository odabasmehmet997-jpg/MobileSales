package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.errorprone.annotations.concurrent.GuardedBy;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class BaseGmsClient<T extends IInterface> {
    @NonNull
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    private static final Feature[] zze = new Feature[0];
    @Nullable
    private volatile String zzA;
    
    @Nullable
    public ConnectionResult zzB;
    
    public boolean zzC;
    @Nullable
    private volatile zzk zzD;
    @VisibleForTesting
    zzv zza;
    final Handler zzb;
    @VisibleForTesting
    @NonNull
    protected ConnectionProgressReportCallbacks zzc;
    @VisibleForTesting
    @NonNull
    protected AtomicInteger zzd;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private long zzj;
    @Nullable
    private volatile String zzk;
    private final Context zzl;
    private final Looper zzm;
    private final GmsClientSupervisor zzn;
    private final GoogleApiAvailabilityLight zzo;
    private final Object zzp;
    
    public final Object zzq;
    
    @Nullable
    @GuardedBy
    public IGmsServiceBroker zzr;
    @Nullable
    @GuardedBy
    private IInterface zzs;
    
    public final ArrayList zzt;
    @Nullable
    @GuardedBy
    private zze zzu;
    @GuardedBy
    private int zzv;
    
    @Nullable
    public final BaseConnectionCallbacks zzw;
    
    @Nullable
    public final BaseOnConnectionFailedListener zzx;
    private final int zzy;
    @Nullable
    private final String zzz;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i2);
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface BaseOnConnectionFailedListener {
        @KeepForSdk
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }

        public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
            } else if (null != zzx) {
                BaseGmsClient.this.zzx.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    static void zzj(BaseGmsClient baseGmsClient, zzk zzk2) {
        RootTelemetryConfiguration rootTelemetryConfiguration;
        baseGmsClient.zzD = zzk2;
        if (baseGmsClient.usesClientTelemetry()) {
            ConnectionTelemetryConfiguration connectionTelemetryConfiguration = zzk2.zzd;
            RootTelemetryConfigManager instance = RootTelemetryConfigManager.getInstance();
            if (null == connectionTelemetryConfiguration) {
                rootTelemetryConfiguration = null;
            } else {
                rootTelemetryConfiguration = connectionTelemetryConfiguration.zza();
            }
            instance.zza(rootTelemetryConfiguration);
        }
    }

    static void zzk(BaseGmsClient baseGmsClient, int i2) {
        int i3;
        int i4;
        synchronized (baseGmsClient.zzp) {
            i3 = baseGmsClient.zzv;
        }
        if (3 == i3) {
            baseGmsClient.zzC = true;
            i4 = 5;
        } else {
            i4 = 4;
        }
        Handler handler = baseGmsClient.zzb;
        handler.sendMessage(handler.obtainMessage(i4, baseGmsClient.zzd.get(), 16));
    }

    static boolean zzn(BaseGmsClient baseGmsClient, int i2, int i3, IInterface iInterface) {
        synchronized (baseGmsClient.zzp) {
            try {
                if (baseGmsClient.zzv != i2) {
                    return false;
                }
                baseGmsClient.zzp(i3, iInterface);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static boolean zzo(BaseGmsClient baseGmsClient) {
        if (baseGmsClient.zzC || TextUtils.isEmpty(baseGmsClient.getServiceDescriptor()) || TextUtils.isEmpty(baseGmsClient.getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(baseGmsClient.getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    
    public final void zzp(int i2, @Nullable IInterface iInterface) {
        zzv zzv2;
        zzv zzv3;
        final boolean z = (4 == i2) == (null != iInterface);
        Preconditions.checkArgument(z);
        synchronized (this.zzp) {
            try {
                this.zzv = i2;
                this.zzs = iInterface;
                if (1 == i2) {
                    zze zze2 = this.zzu;
                    if (null != zze2) {
                        GmsClientSupervisor gmsClientSupervisor = this.zzn;
                        String zzb2 = this.zza.zzb();
                        Preconditions.checkNotNull(zzb2);
                        gmsClientSupervisor.zzb(zzb2, this.zza.zza(), 4225, zze2, zze(), this.zza.zzc());
                        this.zzu = null;
                    }
                } else if (2 == i2 || 3 == i2) {
                    zze zze3 = this.zzu;
                    if (!(null == zze3 || null == (zzv3 = zza))) {
                        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzv3.zzb() + " on " + zzv3.zza());
                        GmsClientSupervisor gmsClientSupervisor2 = this.zzn;
                        String zzb3 = this.zza.zzb();
                        Preconditions.checkNotNull(zzb3);
                        gmsClientSupervisor2.zzb(zzb3, this.zza.zza(), 4225, zze3, zze(), this.zza.zzc());
                        this.zzd.incrementAndGet();
                    }
                    zze zze4 = new zze(this, this.zzd.get());
                    this.zzu = zze4;
                    if (3 != zzv || null == this.getLocalStartServiceAction()) {
                        zzv2 = new zzv(getStartServicePackage(), getStartServiceAction(), false, 4225, getUseDynamicLookup());
                    } else {
                        zzv2 = new zzv(zzl.getPackageName(), getLocalStartServiceAction(), true, 4225, false);
                    }
                    this.zza = zzv2;
                    if (zzv2.zzc()) {
                        if (17895000 > this.getMinApkVersion()) {
                            throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: " + this.zza.zzb());
                        }
                    }
                    GmsClientSupervisor gmsClientSupervisor3 = this.zzn;
                    String zzb4 = this.zza.zzb();
                    Preconditions.checkNotNull(zzb4);
                    if (!gmsClientSupervisor3.zzc(new zzo(zzb4, this.zza.zza(), 4225, this.zza.zzc()), zze4, zze(), getBindServiceExecutor())) {
                        Log.w("GmsClient", "unable to connect to service: " + this.zza.zzb() + " on " + this.zza.zza());
                        zzl(16, null, this.zzd.get());
                    }
                } else if (4 == i2) {
                    Preconditions.checkNotNull(iInterface);
                    onConnectedLocked(iInterface);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzo.isGooglePlayServicesAvailable(this.zzl, getMinApkVersion());
        if (0 != isGooglePlayServicesAvailable) {
            zzp(1, null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    
    @KeepForSdk
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        zzp(2, null);
    }

    
    @KeepForSdk
    @Nullable
    public abstract T createServiceInterface(@NonNull IBinder iBinder);

    @KeepForSdk
    public void disconnect() {
        this.zzd.incrementAndGet();
        synchronized (this.zzt) {
            try {
                int size = this.zzt.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((zzc) this.zzt.get(i2)).zzf();
                }
                this.zzt.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        synchronized (this.zzq) {
            this.zzr = null;
        }
        zzp(1, null);
    }

    @KeepForSdk
    public void dump(@NonNull String str, @NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr) {
        int i2;
        IInterface iInterface;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.zzp) {
            i2 = this.zzv;
            iInterface = this.zzs;
        }
        synchronized (this.zzq) {
            iGmsServiceBroker = this.zzr;
        }
        printWriter.append(str).append("mConnectState=");
        if (1 == i2) {
            printWriter.print("DISCONNECTED");
        } else if (2 == i2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (3 == i2) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (4 == i2) {
            printWriter.print("CONNECTED");
        } else if (5 != i2) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (null == iInterface) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (null == iGmsServiceBroker) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (0 < zzh) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j2 = this.zzh;
            String format = simpleDateFormat.format(new Date(j2));
            append.println(j2 + " " + format);
        }
        if (0 < zzg) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i3 = this.zzf;
            if (1 == i3) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (2 == i3) {
                printWriter.append("CAUSE_NETWORK_LOST");
            } else if (3 != i3) {
                printWriter.append(String.valueOf(i3));
            } else {
                printWriter.append("CAUSE_DEAD_OBJECT_EXCEPTION");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j3 = this.zzg;
            String format2 = simpleDateFormat.format(new Date(j3));
            append2.println(j3 + " " + format2);
        }
        if (0 < zzj) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzi));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j4 = this.zzj;
            String format3 = simpleDateFormat.format(new Date(j4));
            append3.println(j4 + " " + format3);
        }
    }

    
    @KeepForSdk
    public boolean enableLocalFallback() {
        return false;
    }

    @KeepForSdk
    @Nullable
    public Account getAccount() {
        return null;
    }

    @NonNull
    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zze;
    }

    @KeepForSdk
    @Nullable
    public final Feature[] getAvailableFeatures() {
        zzk zzk2 = this.zzD;
        if (null == zzk2) {
            return null;
        }
        return zzk2.zzb;
    }

    
    @KeepForSdk
    @Nullable
    public Executor getBindServiceExecutor() {
        return null;
    }

    @KeepForSdk
    @Nullable
    public Bundle getConnectionHint() {
        return null;
    }

    @NonNull
    @KeepForSdk
    public final Context getContext() {
        return this.zzl;
    }

    @NonNull
    @KeepForSdk
    public String getEndpointPackageName() {
        zzv zzv2;
        if (isConnected() && null != (zzv2 = zza)) {
            return zzv2.zza();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getGCoreServiceId() {
        return this.zzy;
    }

    
    @NonNull
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @KeepForSdk
    @Nullable
    public String getLastDisconnectMessage() {
        return this.zzk;
    }

    
    @KeepForSdk
    @Nullable
    public String getLocalStartServiceAction() {
        return null;
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(@Nullable IAccountAccessor iAccountAccessor, @NonNull Set<Scope> set) {
        Set<Scope> set2 = set;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        String str = this.zzA;
        final int i2 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Scope[] scopeArr = GetServiceRequest.zza;
        Bundle bundle = new Bundle();
        int i3 = this.zzy;
        Feature[] featureArr = GetServiceRequest.zzb;
        GetServiceRequest getServiceRequest = r3;
        GetServiceRequest getServiceRequest2 = new GetServiceRequest(6, i3, i2, null, null, scopeArr, bundle, null, featureArr, featureArr, true, 0, false, str);
        GetServiceRequest getServiceRequest3 = getServiceRequest;
        getServiceRequest3.zzf = this.zzl.getPackageName();
        getServiceRequest3.zzi = getServiceRequestExtraArgs;
        if (null != set2) {
            getServiceRequest3.zzh = set2.toArray(new Scope[0]);
        }
        if (requiresSignIn()) {
            Account account = getAccount();
            if (null == account) {
                account = new Account("<<default account>>", "com.google");
            }
            getServiceRequest3.zzj = account;
            if (null != iAccountAccessor) {
                getServiceRequest3.zzg = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest3.zzj = getAccount();
        }
        getServiceRequest3.zzk = zze;
        getServiceRequest3.zzl = getApiFeatures();
        if (usesClientTelemetry()) {
            getServiceRequest3.zzo = true;
        }
        try {
            synchronized (this.zzq) {
                IGmsServiceBroker iGmsServiceBroker = this.zzr;
                if (null != iGmsServiceBroker) {
                    iGmsServiceBroker.getService(new zzd(this, this.zzd.get()), getServiceRequest3);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            triggerConnectionSuspended(3);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RemoteException | RuntimeException e4) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e4);
            onPostInitHandler(8, null, null, this.zzd.get());
        } catch (Throwable th) {
            throw th;
        }
    }

    
    @NonNull
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.emptySet();
    }

    @NonNull
    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.zzp) {
            try {
                if (5 != zzv) {
                    checkConnected();
                    t = this.zzs;
                    Preconditions.checkNotNull(t, "Client is connected but service is null");
                } else {
                    throw new DeadObjectException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    
    @NonNull
    @KeepForSdk
    public abstract String getServiceDescriptor();

    @NonNull
    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    
    @NonNull
    @KeepForSdk
    public abstract String getStartServiceAction();

    
    @NonNull
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @KeepForSdk
    @Nullable
    public ConnectionTelemetryConfiguration getTelemetryConfiguration() {
        zzk zzk2 = this.zzD;
        if (null == zzk2) {
            return null;
        }
        return zzk2.zzd;
    }

    
    @KeepForSdk
    public boolean getUseDynamicLookup() {
        return 211700000 <= this.getMinApkVersion();
    }

    @KeepForSdk
    public boolean hasConnectionInfo() {
        return null != zzD;
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.zzp) {
            z = 4 == zzv;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzp) {
            int i2 = this.zzv;
            z = true;
            if (2 != i2) {
                if (3 != i2) {
                    z = false;
                }
            }
        }
        return z;
    }

    
    @CallSuper
    @KeepForSdk
    public void onConnectedLocked(@NonNull T t) {
        this.zzh = System.currentTimeMillis();
    }

    
    @CallSuper
    @KeepForSdk
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzi = connectionResult.getErrorCode();
        this.zzj = System.currentTimeMillis();
    }

    
    @CallSuper
    @KeepForSdk
    public void onConnectionSuspended(int i2) {
        this.zzf = i2;
        this.zzg = System.currentTimeMillis();
    }

    
    @KeepForSdk
    public void onPostInitHandler(int i2, @Nullable IBinder iBinder, @Nullable Bundle bundle, int i3) {
        this.zzb.sendMessage(this.zzb.obtainMessage(1, i3, -1, new zzf(this, i2, iBinder, bundle)));
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void setAttributionTag(@NonNull String str) {
        this.zzA = str;
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i2) {
        this.zzb.sendMessage(this.zzb.obtainMessage(6, this.zzd.get(), i2));
    }

    
    @VisibleForTesting
    @KeepForSdk
    public void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i2, @Nullable PendingIntent pendingIntent) {
        Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        this.zzb.sendMessage(this.zzb.obtainMessage(3, this.zzd.get(), i2, pendingIntent));
    }

    @KeepForSdk
    public boolean usesClientTelemetry() {
        return false;
    }

    
    @NonNull
    public final String zze() {
        String str = this.zzz;
        return null == str ? this.zzl.getClass().getName() : str;
    }

    
    public final void zzl(int i2, @Nullable Bundle bundle, int i3) {
        this.zzb.sendMessage(this.zzb.obtainMessage(7, i3, -1, new zzg(this, i2, null)));
    }

    protected BaseGmsClient(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.NonNull android.os.Looper r11, int r12, @androidx.annotation.Nullable com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks r13, @androidx.annotation.Nullable com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener r14, @androidx.annotation.Nullable java.lang.String r15) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.<init>(android.content.Context, android.os.Looper, int, com.google.android.gms.common.internal.BaseGmsClientBaseConnectionCallbacks, com.google.android.gms.common.internal.BaseGmsClientBaseOnConnectionFailedListener, java.lang.String):void");
    }

    @KeepForSdk
    public void disconnect(@NonNull String str) {
        this.zzk = str;
        disconnect();
    }

    @VisibleForTesting
    @KeepForSdk
    protected BaseGmsClient(@NonNull Context context, @NonNull Looper looper, @NonNull GmsClientSupervisor gmsClientSupervisor, @NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight, int i2, @Nullable BaseConnectionCallbacks baseConnectionCallbacks, @Nullable BaseOnConnectionFailedListener baseOnConnectionFailedListener, @Nullable String str) {
        this.zzk = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList();
        this.zzv = 1;
        this.zzB = null;
        this.zzC = false;
        this.zzD = null;
        this.zzd = new AtomicInteger(0);
        Preconditions.checkNotNull(context, "Context must not be null");
        this.zzl = context;
        Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzm = looper;
        Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzn = gmsClientSupervisor;
        Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzo = googleApiAvailabilityLight;
        this.zzb = new zzb(this, looper);
        this.zzy = i2;
        this.zzw = baseConnectionCallbacks;
        this.zzx = baseOnConnectionFailedListener;
        this.zzz = str;
    }
}
