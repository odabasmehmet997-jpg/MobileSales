package com.google.android.gms.maps;

import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public interface LocationSource {

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    interface OnLocationChangedListener {
    }

    void activate(@NonNull OnLocationChangedListener onLocationChangedListener);

    void deactivate();
}
