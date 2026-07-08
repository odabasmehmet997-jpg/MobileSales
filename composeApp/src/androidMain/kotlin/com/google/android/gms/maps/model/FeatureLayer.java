package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class FeatureLayer {

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public interface OnFeatureClickListener {
        void onFeatureClick(@NonNull FeatureClickEvent featureClickEvent);
    }

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public interface StyleFactory {
        @Nullable
        FeatureStyle getStyle(@NonNull Feature feature);
    }
}
