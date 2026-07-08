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
public final class VisibleRegion extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<VisibleRegion> CREATOR = new zzal();
    @SafeParcelable.Field
    @NonNull
    public final LatLng farLeft;
    @SafeParcelable.Field
    @NonNull
    public final LatLng farRight;
    @SafeParcelable.Field
    @NonNull
    public final LatLngBounds latLngBounds;
    @SafeParcelable.Field
    @NonNull
    public final LatLng nearLeft;
    @SafeParcelable.Field
    @NonNull
    public final LatLng nearRight;

    @SafeParcelable.Constructor
    public VisibleRegion(@SafeParcelable.Param @NonNull final LatLng latLng, @SafeParcelable.Param @NonNull final LatLng latLng2, @SafeParcelable.Param @NonNull final LatLng latLng3, @SafeParcelable.Param @NonNull final LatLng latLng4, @SafeParcelable.Param @NonNull final LatLngBounds latLngBounds2) {
        nearLeft = latLng;
        nearRight = latLng2;
        farLeft = latLng3;
        farRight = latLng4;
        latLngBounds = latLngBounds2;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion visibleRegion)) {
            return false;
        }
        return nearLeft.equals(visibleRegion.nearLeft) && nearRight.equals(visibleRegion.nearRight) && farLeft.equals(visibleRegion.farLeft) && farRight.equals(visibleRegion.farRight) && latLngBounds.equals(visibleRegion.latLngBounds);
    }

    public int hashCode() {
        return Objects.hashCode(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("nearLeft", nearLeft).add("nearRight", nearRight).add("farLeft", farLeft).add("farRight", farRight).add("latLngBounds", latLngBounds).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final LatLng latLng = nearLeft;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, nearRight, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, farLeft, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, farRight, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, latLngBounds, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
