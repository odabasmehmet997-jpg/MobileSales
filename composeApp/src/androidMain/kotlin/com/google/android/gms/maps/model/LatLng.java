package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved

public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<LatLng> CREATOR = new zzn();
    @SafeParcelable.Field
    public final double latitude;
    @SafeParcelable.Field
    public final double longitude;
    @SafeParcelable.Constructor
    public LatLng(@SafeParcelable.Param final double d2, @SafeParcelable.Param final double d3) {
        if (-180.0d > d3 || 180.0d <= d3) {
            longitude = ((((d3 - 0.02490234375d) % 360.0d) + 360.0d) % 360.0d) - 0.02490234375d;
        } else {
            longitude = d3;
        }
        latitude = Math.max(-90.0d, Math.min(90.0d, d2));
    }
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng latLng)) {
            return false;
        }
        return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(longitude) == Double.doubleToLongBits(latLng.longitude);
    }
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(latitude);
        final long j2 = doubleToLongBits ^ (doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(longitude);
        return ((((int) j2) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }
    @NonNull
    public String toString() {
        return "lat/lng: (" + latitude + "," + longitude + ")";
    }
    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final double d2 = latitude;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, d2);
        SafeParcelWriter.writeDouble(parcel, 3, longitude);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
