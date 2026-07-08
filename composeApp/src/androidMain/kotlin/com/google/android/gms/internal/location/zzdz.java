package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.*;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzdz extends GmsClient {
    public static final int r8clinit = 0;
    private final SimpleArrayMap zzf = new SimpleArrayMap();
    private final SimpleArrayMap zzg = new SimpleArrayMap();
    private final SimpleArrayMap zzh = new SimpleArrayMap();
    private final SimpleArrayMap zzi = new SimpleArrayMap();

    public zzdz(final Context context, final Looper looper, final ClientSettings clientSettings, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    private boolean zzG(final Feature feature) {
        Feature feature2;
        final Feature[] availableFeatures = this.getAvailableFeatures();
        if (null != availableFeatures) {
            int i2 = 0;
            while (true) {
                if (i2 >= availableFeatures.length) {
                    feature2 = null;
                    break;
                }
                feature2 = availableFeatures[i2];
                if (feature.getName().equals(feature2.getName())) {
                    break;
                }
                i2++;
            }
            return null != feature2 && feature2.getVersion() >= feature.getVersion();
        }
        return false;
    }

    
    public IInterface createServiceInterface(final IBinder iBinder) {
        if (null == iBinder) {
            return null;
        }
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzv ? (zzv) queryLocalInterface : new zzu(iBinder);
    }

    public Feature[] getApiFeatures() {
        return zzo.zzp;
    }

    public int getMinApkVersion() {
        return 11717000;
    }

    
    public String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    
    public String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    public void onConnectionSuspended(final int i2) {
        super.onConnectionSuspended(i2);
        synchronized (zzf) {
            zzf.clear();
        }
        synchronized (zzg) {
            zzg.clear();
        }
        synchronized (zzh) {
            zzh.clear();
        }
    }

    public boolean usesClientTelemetry() {
        return true;
    }

    public void zzA(final Location location, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzh)) {
            ((zzv) this.getService()).zzB(location, new zzdj(null, taskCompletionSource));
            return;
        }
        ((zzv) this.getService()).zzA(location);
        taskCompletionSource.setResult(null);
    }

    public void zzB(final TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzv) this.getService()).zzC(new zzdn(null, taskCompletionSource));
    }

    public void zzC(final ListenerHolder listenerHolder, final DeviceOrientationRequest deviceOrientationRequest, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        final ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
        Objects.requireNonNull(listenerKey);
        synchronized (zzh) {
            try {
                zzdq zzdq = (zzdq) zzh.get(listenerKey);
                if (null == zzdq) {
                    zzdq = new zzdq(listenerHolder);
                    zzh.put(listenerKey, zzdq);
                } else {
                    zzdq.zzc(listenerHolder);
                }
                ((zzv) this.getService()).zzF(new zzj(1, new zzh(deviceOrientationRequest, this.zzh.zza, (String) null), zzdq, new zzdn(null, taskCompletionSource)));
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    public void zzD(final ListenerHolder.ListenerKey listenerKey, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (zzh) {
            try {
                final zzdq zzdq = (zzdq) zzh.remove(listenerKey);
                if (null == zzdq) {
                    taskCompletionSource.setResult(Boolean.FALSE);
                    return;
                }
                zzdq.zze();
                ((zzv) this.getService()).zzF(new zzj(2, (zzh) null, zzdq, new zzdn(Boolean.TRUE, taskCompletionSource)));
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    public void zzE(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzn)) {
            ((zzv) this.getService()).zze(geofencingRequest, pendingIntent, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) this.getService()).zzd(geofencingRequest, pendingIntent, new zzdg(taskCompletionSource));
        }
    }

    public void zzF(final zzem zzem, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzn)) {
            ((zzv) this.getService()).zzg(zzem, new zzdj(null, taskCompletionSource));
        } else {
            ((zzv) this.getService()).zzf(zzem, new zzdg(taskCompletionSource));
        }
    }

    public void zzp(final zzad zzad, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzj)) {
            ((zzv) this.getService()).zzo(zzad, new zzee(5, (IBinder) null, new zzdl(taskCompletionSource), (PendingIntent) null, (String) null));
        } else {
            taskCompletionSource.setResult(((zzv) this.getService()).zzp(this.getContext().getPackageName()));
        }
    }

    public void zzq(final LastLocationRequest lastLocationRequest, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzj)) {
            ((zzv) this.getService()).zzq(lastLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
        } else if (this.zzG(zzo.zzf)) {
            ((zzv) this.getService()).zzr(lastLocationRequest, new zzdk(taskCompletionSource));
        } else {
            taskCompletionSource.setResult(((zzv) this.getService()).zzs());
        }
    }

    public void zzr(final CurrentLocationRequest currentLocationRequest, final CancellationToken cancellationToken, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzj)) {
            final ICancelToken zzt = ((zzv) this.getService()).zzt(currentLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
            if (null != cancellationToken) {
                cancellationToken.onCanceledRequested(new zzed(zzt));
            }
        } else if (this.zzG(zzo.zze)) {
            final ICancelToken zzu = ((zzv) this.getService()).zzu(currentLocationRequest, new zzdk(taskCompletionSource));
            if (null != cancellationToken) {
                cancellationToken.onCanceledRequested(new zzeb(zzu));
            }
        } else {
            final ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(new zzdh(this, taskCompletionSource), zzfc.zza(), "GetCurrentLocation");
            final ListenerHolder.ListenerKey listenerKey = createListenerHolder.getListenerKey();
            Objects.requireNonNull(listenerKey);
            final zzdi zzdi = new zzdi(this, createListenerHolder, taskCompletionSource);
            final TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            final LocationRequest.Builder builder = new LocationRequest.Builder(currentLocationRequest.getPriority(), 0);
            builder.setMinUpdateIntervalMillis(0);
            builder.setDurationMillis(currentLocationRequest.getDurationMillis());
            builder.setGranularity(currentLocationRequest.getGranularity());
            builder.setMaxUpdateAgeMillis(currentLocationRequest.getMaxUpdateAgeMillis());
            builder.zzb(currentLocationRequest.zza());
            builder.zza(currentLocationRequest.zzb());
            builder.setWaitForAccurateLocation(true);
            builder.zzc(currentLocationRequest.zzc());
            this.zzt(zzdi, builder.build(), taskCompletionSource2);
            taskCompletionSource2.getTask().addOnCompleteListener(new zzea(taskCompletionSource));
            if (null != cancellationToken) {
                cancellationToken.onCanceledRequested(new zzec(this, listenerKey));
            }
        }
    }
    public void zzs(final com.google.android.gms.internal.location.zzdr r18, final com.google.android.gms.location.LocationRequest r19, final com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzs(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public void zzt(final com.google.android.gms.internal.location.zzdr r18, final com.google.android.gms.location.LocationRequest r19, final com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzt(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public void zzu(final PendingIntent pendingIntent, final LocationRequest locationRequest, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzj)) {
            ((zzv) this.getService()).zzw(zzee.zzc(pendingIntent), locationRequest, new zzdj(null, taskCompletionSource));
            return;
        }
        final zzeg zza = zzeg.zza(null, locationRequest);
        final zzdn zzdn = new zzdn(null, taskCompletionSource);
        final int hashCode = pendingIntent.hashCode();
        String sb = "PendingIntent@" +
                hashCode;
        ((zzv) this.getService()).zzv(new zzei(1, zza, (IBinder) null, (IBinder) null, pendingIntent, zzdn, sb));
    }


    public void zzv(final com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey r10, final boolean r11, final com.google.android.gms.tasks.TaskCompletionSource r12) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzv(com.google.android.gms.common.api.internal.ListenerHolderListenerKey, boolean, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public void zzw(final com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey r10, final boolean r11, final com.google.android.gms.tasks.TaskCompletionSource r12) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzw(com.google.android.gms.common.api.internal.ListenerHolderListenerKey, boolean, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public void zzx(final PendingIntent pendingIntent, final TaskCompletionSource taskCompletionSource, final Object obj) throws RemoteException {
        if (this.zzG(zzo.zzj)) {
            ((zzv) this.getService()).zzx(zzee.zzc(pendingIntent), new zzdj(null, taskCompletionSource));
            return;
        }
        ((zzv) this.getService()).zzv(new zzei(2, (zzeg) null, (IBinder) null, (IBinder) null, pendingIntent, new zzdn(null, taskCompletionSource), (String) null));
    }

    public void zzy(final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzg)) {
            ((zzv) this.getService()).zzz(true, new zzdj(null, taskCompletionSource));
            return;
        }
        ((zzv) this.getService()).zzy(true);
        taskCompletionSource.setResult(null);
    }

    public void zzz(final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (this.zzG(zzo.zzg)) {
            ((zzv) this.getService()).zzz(false, new zzdj(Boolean.TRUE, taskCompletionSource));
            return;
        }
        ((zzv) this.getService()).zzy(false);
        taskCompletionSource.setResult(Boolean.TRUE);
    }
}
