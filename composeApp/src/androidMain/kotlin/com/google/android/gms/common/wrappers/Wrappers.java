package com.google.android.gms.common.wrappers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class Wrappers {
    private static final Wrappers zza = new Wrappers();
    @Nullable
    private PackageManagerWrapper zzb;

    @NonNull
    @KeepForSdk
    public static PackageManagerWrapper packageManager(@NonNull Context context) {
        return zza.zza(context);
    }

    @VisibleForTesting
    @NonNull
    public final synchronized PackageManagerWrapper zza(@NonNull Context context) {
        try {
            if (null == zzb) {
                if (null != context.getApplicationContext()) {
                    context = context.getApplicationContext();
                }
                this.zzb = new PackageManagerWrapper(context);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.zzb;
    }
}
