package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzca extends zzbm implements zzcc {
    zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public void beginAdUnitExposure(String str, long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j2);
        zzc(23, zza);
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, bundle);
        zzc(9, zza);
    }

    public void clearMeasurementEnabled(long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j2);
        zzc(43, zza);
    }

    public void endAdUnitExposure(String str, long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j2);
        zzc(24, zza);
    }

    public void generateEventId(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(22, zza);
    }

    public void getAppInstanceId(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(20, zza);
    }

    public void getCachedAppInstanceId(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(19, zza);
    }

    public void getConditionalUserProperties(String str, String str2, zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zze(zza, zzcf);
        zzc(10, zza);
    }

    public void getCurrentScreenClass(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(17, zza);
    }

    public void getCurrentScreenName(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(16, zza);
    }

    public void getGmpAppId(zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zzc(21, zza);
    }

    public void getMaxUserProperties(String str, zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzbo.zze(zza, zzcf);
        zzc(6, zza);
    }

    public void getTestFlag(zzcf zzcf, int i2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzcf);
        zza.writeInt(i2);
        zzc(38, zza);
    }

    public void getUserProperties(String str, String str2, boolean z, zzcf zzcf) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzc(zza, z);
        zzbo.zze(zza, zzcf);
        zzc(5, zza);
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzcl zzcl, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zzd(zza, zzcl);
        zza.writeLong(j2);
        zzc(1, zza);
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, bundle);
        zzbo.zzc(zza, z);
        zzbo.zzc(zza, z2);
        zza.writeLong(j2);
        zzc(2, zza);
    }

    public void logHealthData(int i2, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(5);
        zza.writeString(str);
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zze(zza, iObjectWrapper2);
        zzbo.zze(zza, iObjectWrapper3);
        zzc(33, zza);
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zzd(zza, bundle);
        zza.writeLong(j2);
        zzc(27, zza);
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeLong(j2);
        zzc(28, zza);
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeLong(j2);
        zzc(29, zza);
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeLong(j2);
        zzc(30, zza);
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcf zzcf, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zze(zza, zzcf);
        zza.writeLong(j2);
        zzc(31, zza);
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeLong(j2);
        zzc(25, zza);
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeLong(j2);
        zzc(26, zza);
    }

    public void performAction(Bundle bundle, zzcf zzcf, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzbo.zze(zza, zzcf);
        zza.writeLong(j2);
        zzc(32, zza);
    }

    public void registerOnMeasurementEventListener(zzci zzci) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzci);
        zzc(35, zza);
    }

    public void resetAnalyticsData(long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j2);
        zzc(12, zza);
    }

    public void setConditionalUserProperty(Bundle bundle, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zza.writeLong(j2);
        zzc(8, zza);
    }

    public void setConsent(Bundle bundle, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zza.writeLong(j2);
        zzc(44, zza);
    }

    public void setConsentThirdParty(Bundle bundle, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zza.writeLong(j2);
        zzc(45, zza);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeLong(j2);
        zzc(15, zza);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzc(zza, z);
        zzc(39, zza);
    }

    public void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzc(42, zza);
    }

    public void setEventInterceptor(zzci zzci) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzci);
        zzc(34, zza);
    }

    public void setMeasurementEnabled(boolean z, long j2) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzc(zza, z);
        zza.writeLong(j2);
        zzc(11, zza);
    }

    public void setSessionTimeoutDuration(long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j2);
        zzc(14, zza);
    }

    public void setUserId(String str, long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeLong(j2);
        zzc(7, zza);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j2) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zze(zza, iObjectWrapper);
        zzbo.zzc(zza, z);
        zza.writeLong(j2);
        zzc(4, zza);
    }

    public void unregisterOnMeasurementEventListener(zzci zzci) throws RemoteException {
        Parcel zza = zza();
        zzbo.zze(zza, zzci);
        zzc(36, zza);
    }
}
