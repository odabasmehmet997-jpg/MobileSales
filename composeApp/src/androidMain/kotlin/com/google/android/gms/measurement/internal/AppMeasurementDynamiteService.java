package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzci;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzcl;

import java.util.Map;

@DynamiteApi
/* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
public class AppMeasurementDynamiteService extends zzcb {
    @VisibleForTesting
    zzft zza;
    @GuardedBy("listenerMap")
    private final Map zzb = new ArrayMap();

    private final void zzb() {
        if (null == this.zza) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(final zzcf zzcf, final String str) {
        this.zzb();
        zza.zzv().zzU(zzcf, str);
    }

    public void beginAdUnitExposure(@NonNull final String str, final long j2) throws RemoteException {
        this.zzb();
        zza.zzd().zzd(str, j2);
    }

    public void clearConditionalUserProperty(@NonNull final String str, @NonNull final String str2, @NonNull final Bundle bundle) throws RemoteException {
        this.zzb();
        zza.zzq().zzz(str, str2, bundle);
    }

    public void clearMeasurementEnabled(final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzV(null);
    }

    public void endAdUnitExposure(@NonNull final String str, final long j2) throws RemoteException {
        this.zzb();
        zza.zzd().zze(str, j2);
    }

    public void generateEventId(final zzcf zzcf) throws RemoteException {
        this.zzb();
        final long zzq = zza.zzv().zzq();
        this.zzb();
        zza.zzv().zzT(zzcf, zzq);
    }

    public void getAppInstanceId(final zzcf zzcf) throws RemoteException {
        this.zzb();
        zza.zzaz().zzp(new zzh(this, zzcf));
    }

    public void getCachedAppInstanceId(final zzcf zzcf) throws RemoteException {
        this.zzb();
        this.zzc(zzcf, zza.zzq().zzo());
    }

    public void getConditionalUserProperties(final String str, final String str2, final zzcf zzcf) throws RemoteException {
        this.zzb();
        zza.zzaz().zzp(new zzl(this, zzcf, str, str2));
    }

    public void getCurrentScreenClass(final zzcf zzcf) throws RemoteException {
        this.zzb();
        this.zzc(zzcf, zza.zzq().zzp());
    }

    public void getCurrentScreenName(final zzcf zzcf) throws RemoteException {
        this.zzb();
        this.zzc(zzcf, zza.zzq().zzq());
    }

    public void getGmpAppId(final zzcf zzcf) throws RemoteException {
        String str;
        this.zzb();
        final zzhy zzq = zza.zzq();
        if (null != zzq.zzs.zzw()) {
            str = zzq.zzs.zzw();
        } else {
            try {
                str = zzie.zzc(zzq.zzs.zzau(), "google_app_id", zzq.zzs.zzz());
            } catch (final IllegalStateException e2) {
                zzq.zzs.zzay().zzd().zzb("getGoogleAppId failed with exception", e2);
                str = null;
            }
        }
        this.zzc(zzcf, str);
    }

    public void getMaxUserProperties(final String str, final zzcf zzcf) throws RemoteException {
        this.zzb();
        zza.zzq().zzh(str);
        this.zzb();
        zza.zzv().zzS(zzcf, 25);
    }

    public void getTestFlag(final zzcf zzcf, final int i2) throws RemoteException {
        this.zzb();
        if (0 == i2) {
            zza.zzv().zzU(zzcf, zza.zzq().zzr());
        } else if (1 == i2) {
            zza.zzv().zzT(zzcf, zza.zzq().zzm().longValue());
        } else if (2 == i2) {
            final zzky zzv = zza.zzv();
            final double doubleValue = zza.zzq().zzj().doubleValue();
            final Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzcf.zzd(bundle);
            } catch (final RemoteException e2) {
                zzv.zzs.zzay().zzk().zzb("Error returning double value to wrapper", e2);
            }
        } else if (3 == i2) {
            zza.zzv().zzS(zzcf, zza.zzq().zzl().intValue());
        } else if (4 == i2) {
            zza.zzv().zzO(zzcf, zza.zzq().zzi().booleanValue());
        }
    }

    public void getUserProperties(final String str, final String str2, final boolean z, final zzcf zzcf) throws RemoteException {
        this.zzb();
        zza.zzaz().zzp(new zzj(this, zzcf, str, str2, z));
    }

    public void initForTests(@NonNull final Map map) throws RemoteException {
        this.zzb();
    }

    public void initialize(final IObjectWrapper iObjectWrapper, final zzcl zzcl, final long j2) throws RemoteException {
        final zzft zzft = zza;
        if (null == zzft) {
            zza = com.google.android.gms.measurement.internal.zzft.zzp(Preconditions.checkNotNull(ObjectWrapper.unwrap(iObjectWrapper)), zzcl, Long.valueOf(j2));
        } else {
            zzft.zzay().zzk().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(final zzcf zzcf) throws RemoteException {
        this.zzb();
        zza.zzaz().zzp(new zzm(this, zzcf));
    }

    public void logEvent(@NonNull final String str, @NonNull final String str2, @NonNull final Bundle bundle, final boolean z, final boolean z2, final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzE(str, str2, bundle, z, z2, j2);
    }

    public void logEventAndBundle(final String str, final String str2, final Bundle bundle, final zzcf zzcf, final long j2) throws RemoteException {
        this.zzb();
        Preconditions.checkNotEmpty(str2);
        (null != bundle ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        zza.zzaz().zzp(new zzi(this, zzcf, new zzau(str2, new zzas(bundle), "app", j2), str));
    }

    public void logHealthData(final int i2, @NonNull final String str, @NonNull final IObjectWrapper iObjectWrapper, @NonNull final IObjectWrapper iObjectWrapper2, @NonNull final IObjectWrapper iObjectWrapper3) throws RemoteException {
        final Object obj;
        final Object obj2;
        this.zzb();
        Object obj3 = null;
        if (null == iObjectWrapper) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (null == iObjectWrapper2) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (null != iObjectWrapper3) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        zza.zzay().zzt(i2, true, false, str, obj, obj2, obj3);
    }

    public void onActivityCreated(@NonNull final IObjectWrapper iObjectWrapper, @NonNull final Bundle bundle, final long j2) throws RemoteException {
        this.zzb();
        final zzhx zzhx = zza.zzq().zza;
        if (null != zzhx) {
            zza.zzq().zzA();
            zzhx.onActivityCreated(ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(@NonNull final IObjectWrapper iObjectWrapper, final long j2) throws RemoteException {
        this.zzb();
        final zzhx zzhx = zza.zzq().zza;
        if (null != zzhx) {
            zza.zzq().zzA();
            zzhx.onActivityDestroyed(ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(@NonNull final IObjectWrapper iObjectWrapper, final long j2) throws RemoteException {
        this.zzb();
        final zzhx zzhx = zza.zzq().zza;
        if (null != zzhx) {
            zza.zzq().zzA();
            zzhx.onActivityPaused(ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(@NonNull final IObjectWrapper iObjectWrapper, final long j2) throws RemoteException {
        this.zzb();
        final zzhx zzhx = zza.zzq().zza;
        if (null != zzhx) {
            zza.zzq().zzA();
            zzhx.onActivityResumed(ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(final IObjectWrapper iObjectWrapper, final zzcf zzcf, final long j2) throws RemoteException {
        this.zzb();
        final zzhx zzhx = zza.zzq().zza;
        final Bundle bundle = new Bundle();
        if (null != zzhx) {
            zza.zzq().zzA();
            zzhx.onActivitySaveInstanceState(ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzcf.zzd(bundle);
        } catch (final RemoteException e2) {
            zza.zzay().zzk().zzb("Error returning bundle value to wrapper", e2);
        }
    }

    public void onActivityStarted(@NonNull final IObjectWrapper iObjectWrapper, final long j2) throws RemoteException {
        this.zzb();
        if (null != this.zza.zzq().zza) {
            zza.zzq().zzA();
            final Activity activity = ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    public void onActivityStopped(@NonNull final IObjectWrapper iObjectWrapper, final long j2) throws RemoteException {
        this.zzb();
        if (null != this.zza.zzq().zza) {
            zza.zzq().zzA();
            final Activity activity = ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    public void performAction(final Bundle bundle, final zzcf zzcf, final long j2) throws RemoteException {
        this.zzb();
        zzcf.zzd(null);
    }

    public void registerOnMeasurementEventListener(final zzci zzci) throws RemoteException {
        zzgu zzgu;
        this.zzb();
        synchronized (zzb) {
            try {
                zzgu = (zzgu) zzb.get(Integer.valueOf(zzci.zzd()));
                if (null == zzgu) {
                    zzgu = new zzo(this, zzci);
                    zzb.put(Integer.valueOf(zzci.zzd()), zzgu);
                }
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        zza.zzq().zzJ(zzgu);
    }

    public void resetAnalyticsData(final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzK(j2);
    }

    public void setConditionalUserProperty(@NonNull final Bundle bundle, final long j2) throws RemoteException {
        this.zzb();
        if (null == bundle) {
            zza.zzay().zzd().zza("Conditional user property must not be null");
        } else {
            zza.zzq().zzQ(bundle, j2);
        }
    }

    public void setConsent(@NonNull final Bundle bundle, final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzT(bundle, j2);
    }

    public void setConsentThirdParty(@NonNull final Bundle bundle, final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzR(bundle, -20, j2);
    }

    public void setCurrentScreen(@NonNull final IObjectWrapper iObjectWrapper, @NonNull final String str, @NonNull final String str2, final long j2) throws RemoteException {
        this.zzb();
        zza.zzs().zzw(ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setDataCollectionEnabled(final boolean z) throws RemoteException {
        this.zzb();
        final zzhy zzq = zza.zzq();
        zzq.zza();
        zzq.zzs.zzaz().zzp(new zzha(zzq, z));
    }

    public void setDefaultEventParameters(@NonNull final Bundle bundle) {
        final Bundle bundle2;
        this.zzb();
        final zzhy zzq = zza.zzq();
        if (null == bundle) {
            bundle2 = null;
        } else {
            bundle2 = new Bundle(bundle);
        }
        zzq.zzs.zzaz().zzp(new zzgy(zzq, bundle2));
    }

    public void setEventInterceptor(final zzci zzci) throws RemoteException {
        this.zzb();
        final zzn zzn = new zzn(this, zzci);
        if (zza.zzaz().zzs()) {
            zza.zzq().zzU(zzn);
        } else {
            zza.zzaz().zzp(new zzk(this, zzn));
        }
    }

    public void setInstanceIdProvider(final zzck zzck) throws RemoteException {
        this.zzb();
    }

    public void setMeasurementEnabled(final boolean z, final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzV(Boolean.valueOf(z));
    }

    public void setMinimumSessionDuration(final long j2) throws RemoteException {
        this.zzb();
    }

    public void setSessionTimeoutDuration(final long j2) throws RemoteException {
        this.zzb();
        final zzhy zzq = zza.zzq();
        zzq.zzs.zzaz().zzp(new zzhc(zzq, j2));
    }

    public void setUserId(@NonNull final String str, final long j2) throws RemoteException {
        this.zzb();
        if (null == str || 0 != str.length()) {
            zza.zzq().zzY(null, "_id", str, true, j2);
        } else {
            zza.zzay().zzk().zza("User ID must be non-empty");
        }
    }

    public void setUserProperty(@NonNull final String str, @NonNull final String str2, @NonNull final IObjectWrapper iObjectWrapper, final boolean z, final long j2) throws RemoteException {
        this.zzb();
        zza.zzq().zzY(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j2);
    }

    public void unregisterOnMeasurementEventListener(final zzci zzci) throws RemoteException {
        zzgu zzgu;
        this.zzb();
        synchronized (zzb) {
            zzgu = (zzgu) zzb.remove(Integer.valueOf(zzci.zzd()));
        }
        if (null == zzgu) {
            zzgu = new zzo(this, zzci);
        }
        zza.zzq().zzaa(zzgu);
    }
}
