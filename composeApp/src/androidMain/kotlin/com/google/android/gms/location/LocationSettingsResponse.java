package com.google.android.gms.location;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Response;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class LocationSettingsResponse extends Response<LocationSettingsResult> {
    public LocationSettingsResponse(@NonNull final LocationSettingsResult locationSettingsResult) {
        super(locationSettingsResult);
    }
}
