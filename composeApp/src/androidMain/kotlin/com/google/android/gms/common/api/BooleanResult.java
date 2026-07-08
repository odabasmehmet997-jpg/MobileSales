package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class BooleanResult implements Result {
    private final Status zaa = null;
    private final boolean zab = false;

    public final boolean equals(@Nullable final Object obj) {
        if (null == obj) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult booleanResult)) {
            return false;
        }
        return zaa.equals(booleanResult.zaa) && zab == booleanResult.zab;
    }

    @NonNull
    public Status getStatus() {
        return zaa;
    }

    public final int hashCode() {
        return ((zaa.hashCode() + 527) * 31) + (zab ? 1 : 0);
    }
}
