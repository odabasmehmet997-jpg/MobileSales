package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabs {
    
    public final ApiKey zaa;
    
    public final Feature zab;

    zabs(final ApiKey apiKey, final Feature feature, final zabr zabr) {
        zaa = apiKey;
        zab = feature;
    }

    public boolean equals(@Nullable final Object obj) {
        if (null != obj && (obj instanceof zabs zabs)) {
            return Objects.equal(zaa, zabs.zaa) && Objects.equal(zab, zabs.zab);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(zaa, zab);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("key", zaa).add("feature", zab).toString();
    }
}
