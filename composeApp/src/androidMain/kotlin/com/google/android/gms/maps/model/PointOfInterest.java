package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class PointOfInterest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzs();
    @SafeParcelable.Field
    @NonNull
    public final LatLng latLng;
    @SafeParcelable.Field
    @NonNull
    public final String name;
    @SafeParcelable.Field
    @NonNull
    public final String placeId;

    @SafeParcelable.Constructor
    public PointOfInterest(@SafeParcelable.Param @NonNull final LatLng latLng2, @SafeParcelable.Param @NonNull final String str, @SafeParcelable.Param @NonNull final String str2) {
        latLng = latLng2;
        placeId = str;
        name = str2;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final LatLng latLng2 = latLng;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng2, i2, false);
        SafeParcelWriter.writeString(parcel, 3, placeId, false);
        SafeParcelWriter.writeString(parcel, 4, name, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
