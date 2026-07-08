package com.google.android.gms.common;

import android.content.Intent;
import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class UserRecoverableException extends Exception {
    private final Intent zza;

    public UserRecoverableException(@NonNull final String str, @NonNull final Intent intent) {
        super(str);
        zza = intent;
    }

    @NonNull
    public Intent getIntent() {
        return new Intent(zza);
    }
}
