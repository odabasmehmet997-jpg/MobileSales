package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzf extends zza {
    @Nullable
    public final IBinder zze;
    final BaseGmsClient zzf;

    public zzf(BaseGmsClient baseGmsClient, @Nullable int i2, @Nullable IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i2, bundle);
        this.zzf = baseGmsClient;
        this.zze = iBinder;
    }

    
    public void zzb(ConnectionResult connectionResult) {
        if (null != zzf.zzx) {
            this.zzf.zzx.onConnectionFailed(connectionResult);
        }
        this.zzf.onConnectionFailed(connectionResult);
    }

    
    public boolean zzd() {
        try {
            IBinder iBinder = this.zze;
            Preconditions.checkNotNull(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if (!this.zzf.getServiceDescriptor().equals(interfaceDescriptor)) {
                String serviceDescriptor = this.zzf.getServiceDescriptor();
                Log.w("GmsClient", "service descriptor mismatch: " + serviceDescriptor + " vs. " + interfaceDescriptor);
                return false;
            }
            IInterface createServiceInterface = this.zzf.createServiceInterface(this.zze);
            if (null == createServiceInterface || (!BaseGmsClient.zzn(this.zzf, 2, 4, createServiceInterface) && !BaseGmsClient.zzn(this.zzf, 3, 4, createServiceInterface))) {
                return false;
            }
            this.zzf.zzB = null;
            BaseGmsClient baseGmsClient = this.zzf;
            Bundle connectionHint = baseGmsClient.getConnectionHint();
            if (null == baseGmsClient.zzw) {
                return true;
            }
            this.zzf.zzw.onConnected(connectionHint);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
