package com.google.android.gms.common.images;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class Size {
    private final int zaa;
    private final int zab;

    public boolean equals(@Nullable Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof final Size size) {
            return this.zaa == size.zaa && this.zab == size.zab;
        }
    }

    public int hashCode() {
        int i2 = this.zaa;
        return ((i2 >>> 16) | (i2 << 16)) ^ this.zab;
    }

    @NonNull
    public String toString() {
        return this.zaa + "x" + this.zab;
    }
}
