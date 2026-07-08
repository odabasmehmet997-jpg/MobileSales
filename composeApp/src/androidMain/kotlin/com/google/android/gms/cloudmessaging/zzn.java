package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;

final class zzn {
    private final Messenger zza;
    private final zzd zzb;
    zzn(final IBinder iBinder) throws RemoteException {
        final String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            zza = new Messenger(iBinder);
            zzb = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            zzb = new zzd(iBinder);
            zza = null;
        } else {
            final String valueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", 0 != valueOf.length() ? "Invalid interface descriptor: " + valueOf : "Invalid interface descriptor: ");
            throw new RemoteException();
        }
    }
    public void zza(final Message message) throws RemoteException {
        final Messenger messenger = zza;
        if (null != messenger) {
            messenger.send(message);
            return;
        }
        final zzd zzd = zzb;
        if (null != zzd) {
            zzd.zzb(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
