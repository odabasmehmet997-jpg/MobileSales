package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.*;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.zzo;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzg extends GmsClient {
    public zzg(final Context context, final Looper looper, final ClientSettings clientSettings, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
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

    
    public Bundle getGetServiceRequestExtraArgs() {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", "activity_recognition");
        return bundle;
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

    public boolean usesClientTelemetry() {
        return true;
    }

    public void zzp(final PendingIntent pendingIntent) throws RemoteException {
        Preconditions.checkNotNull(pendingIntent);
        ((zzv) this.getService()).zzl(pendingIntent);
    }
}
