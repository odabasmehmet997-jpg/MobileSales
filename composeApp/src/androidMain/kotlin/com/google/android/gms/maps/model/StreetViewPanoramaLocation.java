package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class StreetViewPanoramaLocation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new zzz();
    @SafeParcelable.Field
    @NonNull
    public final StreetViewPanoramaLink[] links;
    @SafeParcelable.Field
    @NonNull
    public final String panoId;
    @SafeParcelable.Field
    @NonNull
    public final LatLng position;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLocation(@SafeParcelable.Param @NonNull final StreetViewPanoramaLink[] streetViewPanoramaLinkArr, @SafeParcelable.Param @NonNull final LatLng latLng, @SafeParcelable.Param @NonNull final String str) {
        links = streetViewPanoramaLinkArr;
        position = latLng;
        panoId = str;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation streetViewPanoramaLocation)) {
            return false;
        }
        return panoId.equals(streetViewPanoramaLocation.panoId) && position.equals(streetViewPanoramaLocation.position);
    }

    public int hashCode() {
        return Objects.hashCode(position, panoId);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("panoId", panoId).add(PrivacyPolicyState.POSITION, position.toString()).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final StreetViewPanoramaLink[] streetViewPanoramaLinkArr = links;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, streetViewPanoramaLinkArr, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, position, i2, false);
        SafeParcelWriter.writeString(parcel, 4, panoId, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
