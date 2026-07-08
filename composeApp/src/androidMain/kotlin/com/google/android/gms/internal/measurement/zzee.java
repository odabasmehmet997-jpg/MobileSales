package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzfl;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzie;

import java.util.*;
import java.util.concurrent.*;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
public final class zzee {
    private static volatile zzee zzc;
    final Clock zza;
    private final ExecutorService zzb;

    public final String zzd;
    private final AppMeasurementSdk zze;
    @GuardedBy("listenerList")
    private final List zzf;
    private int zzg;

    public boolean zzh;
    private final String zzi;

    public volatile zzcc zzj;

    private zzee(Context context, String str, String str2, String str3, Bundle bundle) {
        if (null == str || !zzV(str2, str3)) {
            this.zzd = "FA";
        } else {
            this.zzd = str;
        }
        this.zza = DefaultClock.getInstance();
        zzbx.zza();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzdi(this));
        final boolean z = true;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.zzb = Executors.unconfigurableExecutorService(threadPoolExecutor);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        try {
            if (null != zzie.zzc(context, "google_app_id", zzfl.zza(context)) && !zzR()) {
                this.zzi = null;
                this.zzh = true;
                Log.w(this.zzd, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
                return;
            }
        } catch (IllegalStateException unused) {
        }
        if (!zzV(str2, str3)) {
            this.zzi = "fa";
            if (null == str2 || null == str3) {
                if ((null == str2) ^ (null == str3 && z)) {
                    Log.w(this.zzd, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzd, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        } else {
            this.zzi = str2;
        }
        zzU(new zzcx(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (null == application) {
            Log.w(this.zzd, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzed(this));
        }
    }

    private static boolean zzR() {
        return true;
    }


    public void zzS(Exception exc, boolean z, boolean z2) {
        this.zzh |= z;
        if (z) {
            Log.w(this.zzd, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zzA(5, "Error with data collection. Data lost.", exc, null, null);
        }
        Log.w(this.zzd, "Error with data collection. Data lost.", exc);
    }

    private void zzT(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zzU(new zzdr(this, l, str, str2, bundle, z, z2));
    }


    public void zzU(zzdt zzdt) {
        this.zzb.execute(zzdt);
    }


    public static boolean zzV(String str, String str2) {
        return null != str2 && null != str && !zzR();
    }

    public static zzee zzg(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (null == zzee.zzc) {
            synchronized (zzee.class) {
                try {
                    if (null == zzee.zzc) {
                        zzc = new zzee(context, str, str2, str3, bundle);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzc;
    }

    public void zzA(int i2, String str, Object obj, Object obj2, Object obj3) {
        zzU(new zzdg(this, false, 5, str, obj, null, null));
    }

    public void zzB(com.google.android.gms.measurement.internal.zzgu r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzee.zzB(com.google.android.gms.measurement.internal.zzgu):void");
    }

    public void zzC() {
        zzU(new zzcv(this));
    }

    public void zzD(Bundle bundle) {
        zzU(new zzcn(this, bundle));
    }

    public void zzF(Bundle bundle) {
        zzU(new zzcu(this, bundle));
    }

    public void zzG(Activity activity, String str, String str2) {
        zzU(new zzcr(this, activity, str, str2));
    }

    public void zzH(boolean z) {
        zzU(new zzdm(this, z));
    }

    public void zzI(Bundle bundle) {
        zzU(new zzdn(this, bundle));
    }

    public void zzJ(zzgt zzgt) {
        zzdu zzdu = new zzdu(zzgt);
        if (null != zzj) {
            try {
                this.zzj.setEventInterceptor(zzdu);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzd, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zzU(new zzdo(this, zzdu));
    }

    public void zzK(Boolean bool) {
        zzU(new zzcs(this, bool));
    }

    public void zzL(long j2) {
        zzU(new zzcw(this, j2));
    }

    public void zzM(String str) {
        zzU(new zzcq(this, str));
    }

    public void zzN(String str, String str2, Object obj, boolean z) {
        zzU(new zzds(this, str, str2, obj, z));
    }

    public void zzO(com.google.android.gms.measurement.internal.zzgu r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzee.zzO(com.google.android.gms.measurement.internal.zzgu):void");
    }

    public int zza(String str) {
        zzbz zzbz = new zzbz();
        zzU(new zzdj(this, str, zzbz));
        Integer num = (Integer) com.google.android.gms.internal.measurement.zzbz.zze(zzbz.zzb(WorkRequest.MIN_BACKOFF_MILLIS), Integer.class);
        if (null == num) {
            return 25;
        }
        return num.intValue();
    }

    public long zzb() {
        zzbz zzbz = new zzbz();
        zzU(new zzdc(this, zzbz));
        Long l = (Long) com.google.android.gms.internal.measurement.zzbz.zze(zzbz.zzb(500), Long.class);
        if (null != l) {
            return l.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i2 = this.zzg + 1;
        this.zzg = i2;
        return nextLong + i2;
    }

    public AppMeasurementSdk zzd() {
        return this.zze;
    }


    public zzcc zzf(Context context, boolean z) {
        try {
            return zzcb.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate(classLoader, "com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e2) {
            zzS(e2, true, false);
            return null;
        }
    }

    public Object zzh(int i2) {
        zzbz zzbz = new zzbz();
        zzU(new zzdl(this, zzbz, i2));
        return com.google.android.gms.internal.measurement.zzbz.zze(zzbz.zzb(15000), Object.class);
    }

    @WorkerThread
    public String zzk() {
        zzbz zzbz = new zzbz();
        zzU(new zzdk(this, zzbz));
        return zzbz.zzc(120000);
    }

    public String zzl() {
        zzbz zzbz = new zzbz();
        zzU(new zzdb(this, zzbz));
        return zzbz.zzc(50);
    }

    public String zzm() {
        zzbz zzbz = new zzbz();
        zzU(new zzde(this, zzbz));
        return zzbz.zzc(500);
    }

    public String zzn() {
        zzbz zzbz = new zzbz();
        zzU(new zzdd(this, zzbz));
        return zzbz.zzc(500);
    }

    public String zzo() {
        zzbz zzbz = new zzbz();
        zzU(new zzda(this, zzbz));
        return zzbz.zzc(500);
    }

    public List zzp(String str, String str2) {
        zzbz zzbz = new zzbz();
        zzU(new zzcp(this, str, str2, zzbz));
        List list = (List) com.google.android.gms.internal.measurement.zzbz.zze(zzbz.zzb(CoroutineLiveDataKt.DEFAULT_TIMEOUT), List.class);
        return null == list ? Collections.emptyList() : list;
    }

    public Map zzq(String str, String str2, boolean z) {
        zzbz zzbz = new zzbz();
        zzU(new zzdf(this, str, str2, z, zzbz));
        Bundle zzb2 = zzbz.zzb(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        if (null == zzb2 || 0 == zzb2.size()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zzb2.size());
        for (String next : zzb2.keySet()) {
            Object obj = zzb2.get(next);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    public void zzu(String str) {
        zzU(new zzcy(this, str));
    }

    public void zzv(String str, String str2, Bundle bundle) {
        zzU(new zzco(this, str, str2, bundle));
    }

    public void zzw(String str) {
        zzU(new zzcz(this, str));
    }

    public void zzx(@NonNull String str, Bundle bundle) {
        zzT(null, str, bundle, false, true, null);
    }

    public void zzy(String str, String str2, Bundle bundle) {
        zzT(str, str2, bundle, true, true, null);
    }

    public void zzz(String str, String str2, Bundle bundle, long j2) {
        zzT(str, str2, bundle, true, false, Long.valueOf(j2));
    }
}
