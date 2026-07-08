package com.google.android.gms.common;

import android.content.Intent;
import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zza;

    public GooglePlayServicesRepairableException(final int i2, @NonNull final String str, @NonNull final Intent intent) {
        super(str, intent);
        zza = i2;
    }

    public int getConnectionStatusCode() {
        return zza;
    }
}
