package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class AdvancedMarkerOptions extends MarkerOptions {

    @Retention(RetentionPolicy.RUNTIME)
    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public @interface CollisionBehavior {
    }

    @NonNull
    public AdvancedMarkerOptions draggable(final boolean z) {
        super.draggable(z);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions position(@NonNull final LatLng latLng) {
        super.position(latLng);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions snippet(@Nullable final String str) {
        super.snippet(str);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions title(@Nullable final String str) {
        super.title(str);
        return this;
    }
}
