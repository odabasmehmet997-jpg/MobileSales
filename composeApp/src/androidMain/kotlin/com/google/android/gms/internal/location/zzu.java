package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.*;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzb;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzu extends zza implements zzv {
    zzu(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public void zzA(final Location location) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, location);
        this.zzc(13, zza);
    }

    public void zzB(final Location location, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, location);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(85, zza);
    }

    public void zzC(final zzr zzr) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzc(zza, zzr);
        this.zzc(67, zza);
    }

    public void zzD(final LocationSettingsRequest locationSettingsRequest, final zzab zzab, final String str) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, locationSettingsRequest);
        zzc.zzc(zza, zzab);
        zza.writeString(null);
        this.zzc(63, zza);
    }

    public void zzE(final zzo zzo) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzc(zza, zzo);
        this.zzc(95, zza);
    }

    public void zzF(final zzj zzj) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzj);
        this.zzc(75, zza);
    }

    public void zzd(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent, final zzt zzt) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, geofencingRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, zzt);
        this.zzc(57, zza);
    }

    public void zze(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, geofencingRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(97, zza);
    }

    public void zzf(final zzem zzem, final zzt zzt) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzem);
        zzc.zzc(zza, zzt);
        this.zzc(74, zza);
    }

    public void zzg(final zzem zzem, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzem);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(98, zza);
    }

    public void zzh(final long j2, final boolean z, final PendingIntent pendingIntent) throws RemoteException {
        final Parcel zza = this.zza();
        zza.writeLong(j2);
        final int i2 = zzc.r8clinit;
        zza.writeInt(1);
        zzc.zzb(zza, pendingIntent);
        this.zzc(5, zza);
    }

    public void zzi(final zzb zzb, final PendingIntent pendingIntent, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzb);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(70, zza);
    }

    public void zzj(final ActivityTransitionRequest activityTransitionRequest, final PendingIntent pendingIntent, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, activityTransitionRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(72, zza);
    }

    public void zzk(final PendingIntent pendingIntent, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(73, zza);
    }

    public void zzl(final PendingIntent pendingIntent) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, pendingIntent);
        this.zzc(6, zza);
    }

    public void zzm(final PendingIntent pendingIntent, final SleepSegmentRequest sleepSegmentRequest, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzb(zza, sleepSegmentRequest);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(79, zza);
    }

    public void zzn(final PendingIntent pendingIntent, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(69, zza);
    }

    public void zzo(final zzad zzad, final zzee zzee) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzad);
        zzc.zzb(zza, zzee);
        this.zzc(91, zza);
    }

    public LocationAvailability zzp(final String str) throws RemoteException {
        final Parcel zza = this.zza();
        zza.writeString(str);
        final Parcel zzb = this.zzb(34, zza);
        final LocationAvailability locationAvailability = (LocationAvailability) zzc.zza(zzb, LocationAvailability.CREATOR);
        zzb.recycle();
        return locationAvailability;
    }

    public void zzq(final LastLocationRequest lastLocationRequest, final zzee zzee) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, lastLocationRequest);
        zzc.zzb(zza, zzee);
        this.zzc(90, zza);
    }

    public void zzr(final LastLocationRequest lastLocationRequest, final zzz zzz) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, lastLocationRequest);
        zzc.zzc(zza, zzz);
        this.zzc(82, zza);
    }

    public Location zzs() throws RemoteException {
        final Parcel zzb = this.zzb(7, this.zza());
        final Location location = (Location) zzc.zza(zzb, Location.CREATOR);
        zzb.recycle();
        return location;
    }

    public ICancelToken zzt(final CurrentLocationRequest currentLocationRequest, final zzee zzee) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, currentLocationRequest);
        zzc.zzb(zza, zzee);
        final Parcel zzb = this.zzb(92, zza);
        final ICancelToken asInterface = ICancelToken.Stub.asInterface(zzb.readStrongBinder());
        zzb.recycle();
        return asInterface;
    }

    public ICancelToken zzu(final CurrentLocationRequest currentLocationRequest, final zzz zzz) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, currentLocationRequest);
        zzc.zzc(zza, zzz);
        final Parcel zzb = this.zzb(87, zza);
        final ICancelToken asInterface = ICancelToken.Stub.asInterface(zzb.readStrongBinder());
        zzb.recycle();
        return asInterface;
    }

    public void zzv(final zzei zzei) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzei);
        this.zzc(59, zza);
    }

    public void zzw(final zzee zzee, final LocationRequest locationRequest, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzee);
        zzc.zzb(zza, locationRequest);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(88, zza);
    }

    public void zzx(final zzee zzee, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzb(zza, zzee);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(89, zza);
    }

    public void zzy(final boolean z) throws RemoteException {
        final Parcel zza = this.zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        this.zzc(12, zza);
    }

    public void zzz(final boolean z, final IStatusCallback iStatusCallback) throws RemoteException {
        final Parcel zza = this.zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc.zzc(zza, iStatusCallback);
        this.zzc(84, zza);
    }
}
