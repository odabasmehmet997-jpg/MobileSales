package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class PendingResultUtil {
    private static final zas zaa = new zao();

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public interface ResultConverter<R extends Result, T> {
        @KeepForSdk
        @Nullable
        T convert(@NonNull R r);
    }
}
