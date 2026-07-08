package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zzm();
    @SafeParcelable.Field
    @NonNull
    public final LatLng northeast;
    @SafeParcelable.Field
    @NonNull
    public final LatLng southwest;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
        private final double zza = Double.POSITIVE_INFINITY;
        private final double zzb = Double.NEGATIVE_INFINITY;
        private final double zzc = Double.NaN;
        private final double zzd = Double.NaN;
    }

    @SafeParcelable.Constructor
    public LatLngBounds(@SafeParcelable.Param @NonNull final LatLng latLng, @SafeParcelable.Param @NonNull final LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "southwest must not be null.");
        Preconditions.checkNotNull(latLng2, "northeast must not be null.");
        final double d2 = latLng2.latitude;
        final double d3 = latLng.latitude;
        Preconditions.checkArgument(d2 >= d3, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(d3), Double.valueOf(latLng2.latitude));
        southwest = latLng;
        northeast = latLng2;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds latLngBounds)) {
            return false;
        }
        return southwest.equals(latLngBounds.southwest) && northeast.equals(latLngBounds.northeast);
    }

    public int hashCode() {
        return Objects.hashCode(southwest, northeast);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("southwest", southwest).add("northeast", northeast).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final LatLng latLng = southwest;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, northeast, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
