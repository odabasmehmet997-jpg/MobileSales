package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i2, @NonNull IBinder iBinder, @NonNull Bundle bundle) throws RemoteException;

    void zzb(int i2, @NonNull Bundle bundle) throws RemoteException;

    void zzc(int i2, IBinder iBinder, zzk zzk) throws RemoteException;
}
