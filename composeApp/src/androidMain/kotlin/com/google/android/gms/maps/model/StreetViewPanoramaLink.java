package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class StreetViewPanoramaLink extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaLink> CREATOR = new zzy();
    @SafeParcelable.Field
    public final float bearing;
    @SafeParcelable.Field
    @NonNull
    public final String panoId;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLink(@SafeParcelable.Param @NonNull final String str, @SafeParcelable.Param final float f2) {
        panoId = str;
        bearing = (0.0d >= f2 ? (f2 % 360.0f) + 360.0f : f2) % 360.0f;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink streetViewPanoramaLink)) {
            return false;
        }
        return panoId.equals(streetViewPanoramaLink.panoId) && Float.floatToIntBits(bearing) == Float.floatToIntBits(streetViewPanoramaLink.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(panoId, Float.valueOf(bearing));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("panoId", panoId).add("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final String str = panoId;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, str, false);
        SafeParcelWriter.writeFloat(parcel, 3, bearing);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
