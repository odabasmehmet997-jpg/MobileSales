package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zza extends AsyncTask {
    final Context zza;
    final ProviderInstaller.ProviderInstallListener zzb;

    
    public Object doInBackground(final Object[] objArr) {
        final Void[] voidArr = (Void[]) objArr;
        try {
            ProviderInstaller.installIfNeeded(zza);
            return 0;
        } catch (final GooglePlayServicesRepairableException e2) {
            return Integer.valueOf(e2.getConnectionStatusCode());
        } catch (final GooglePlayServicesNotAvailableException e3) {
            return Integer.valueOf(e3.errorCode);
        }
    }

    
    public void onPostExecute(final Object obj) {
        final Integer num = (Integer) obj;
        if (0 == num.intValue()) {
            zzb.onProviderInstalled();
            return;
        }
        zzb.onProviderInstallFailed(num.intValue(), ProviderInstaller.zza.getErrorResolutionIntent(zza, num.intValue(), "pi"));
    }
}
