package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationSettingsResult;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdf extends zzaa {
    final BaseImplementation.ResultHolder zza;

    zzdf(final BaseImplementation.ResultHolder resultHolder) {
        zza = resultHolder;
    }

    public void zzb(final LocationSettingsResult locationSettingsResult) {
        zza.setResult(locationSettingsResult);
    }
}
