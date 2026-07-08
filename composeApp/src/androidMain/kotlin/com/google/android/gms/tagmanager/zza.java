package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zza implements zzc {
    final zzd zza;

    zza(zzd zzd) {
        this.zza = zzd;
    }

    public AdvertisingIdClient.Info zza() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zza.zzi);
        } catch (IllegalStateException e2) {
            Log.w("GoogleTagManager", "IllegalStateException getting Advertising Id Info", e2);
            return null;
        } catch (GooglePlayServicesRepairableException e3) {
            Log.w("GoogleTagManager", "GooglePlayServicesRepairableException getting Advertising Id Info", e3);
            return null;
        } catch (IOException e4) {
            Log.w("GoogleTagManager", "IOException getting Ad Id Info", e4);
            return null;
        } catch (GooglePlayServicesNotAvailableException e5) {
            this.zza.zze();
            Log.w("GoogleTagManager", "GooglePlayServicesNotAvailableException getting Advertising Id Info", e5);
            return null;
        } catch (Exception e6) {
            Log.w("GoogleTagManager", "Unknown exception. Could not get the Advertising Id Info.", e6);
            return null;
        }
    }
}
