package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class CustomCap extends Cap {
    @NonNull
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    @NonNull
    public String toString() {
        final String valueOf = String.valueOf(bitmapDescriptor);
        return "[CustomCap: bitmapDescriptor=" + valueOf + " refWidth=" + refWidth + "]";
    }
    public CustomCap(@androidx.annotation.NonNull final com.google.android.gms.maps.model.BitmapDescriptor r3, final float r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.CustomCap.<init>(com.google.android.gms.maps.model.BitmapDescriptor, float):void");
    }
}
