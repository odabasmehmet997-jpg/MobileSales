package com.google.android.gms.cloudmessaging;

import android.os.*;
import androidx.annotation.Nullable;

public final class zzd implements Parcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zzb();
    Messenger zza;
    IMessengerCompat zzb;
    public zzd(final IBinder iBinder) {
        zza = new Messenger(iBinder);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals( final Object obj) {
        if (null == obj) {
            return false;
        }
        try {
            return this.zza().equals(((zzd) obj).zza());
        } catch (final ClassCastException unused) {
            return false;
        }
    }
    public int hashCode() {
        return this.zza().hashCode();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        final Messenger messenger = zza;
        if (null != messenger) {
            parcel.writeStrongBinder(messenger.getBinder());
        } else {
            parcel.writeStrongBinder(zzb.asBinder());
        }
    }
    public IBinder zza() {
        final Messenger messenger = zza;
        return null != messenger ? messenger.getBinder() : zzb.asBinder();
    }
    public void zzb(final Message message) throws RemoteException {
        final Messenger messenger = zza;
        if (null != messenger) {
            messenger.send(message);
        } else {
            zzb.send(message);
        }
    }
}
