package com.google.android.gms.auth.api.signin.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class HashAccumulator {
    private int zaa = 1;

    @NonNull
    @KeepForSdk
    @CanIgnoreReturnValue
    public HashAccumulator addObject(@Nullable final Object obj) {
        zaa = (zaa * 31) + (null == obj ? 0 : obj.hashCode());
        return this;
    }

    @KeepForSdk
    public int hash() {
        return zaa;
    }

    @NonNull
    @CanIgnoreReturnValue
    public final HashAccumulator zaa(final boolean z) {
        zaa = (zaa * 31) + (z ? 1 : 0);
        return this;
    }
}
