package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzq extends zza {
    zzq(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public int zze() throws RemoteException {
        final Parcel zzB = this.zzB(6, this.zza());
        final int readInt = zzB.readInt();
        zzB.recycle();
        return readInt;
    }

    public int zzf(final IObjectWrapper iObjectWrapper, final String str, final boolean z) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(z ? 1 : 0);
        final Parcel zzB = this.zzB(3, zza);
        final int readInt = zzB.readInt();
        zzB.recycle();
        return readInt;
    }

    public int zzg(final IObjectWrapper iObjectWrapper, final String str, final boolean z) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(z ? 1 : 0);
        final Parcel zzB = this.zzB(5, zza);
        final int readInt = zzB.readInt();
        zzB.recycle();
        return readInt;
    }

    public IObjectWrapper zzh(final IObjectWrapper iObjectWrapper, final String str, final int i2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i2);
        final Parcel zzB = this.zzB(2, zza);
        final IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzB.readStrongBinder());
        zzB.recycle();
        return asInterface;
    }

    public IObjectWrapper zzi(final IObjectWrapper iObjectWrapper, final String str, final int i2, final IObjectWrapper iObjectWrapper2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i2);
        zzc.zze(zza, iObjectWrapper2);
        final Parcel zzB = this.zzB(8, zza);
        final IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzB.readStrongBinder());
        zzB.recycle();
        return asInterface;
    }

    public IObjectWrapper zzj(final IObjectWrapper iObjectWrapper, final String str, final int i2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i2);
        final Parcel zzB = this.zzB(4, zza);
        final IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzB.readStrongBinder());
        zzB.recycle();
        return asInterface;
    }

    public IObjectWrapper zzk(final IObjectWrapper iObjectWrapper, final String str, final boolean z, final long j2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zze(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(z ? 1 : 0);
        zza.writeLong(j2);
        final Parcel zzB = this.zzB(7, zza);
        final IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzB.readStrongBinder());
        zzB.recycle();
        return asInterface;
    }
}
