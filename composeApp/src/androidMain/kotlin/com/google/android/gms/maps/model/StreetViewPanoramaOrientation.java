package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR = new zzaa();
    @SafeParcelable.Field
    public final float bearing;
    @SafeParcelable.Field
    public final float tilt;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
        public float bearing;
        public float tilt;

        @NonNull
        public Builder bearing(final float f2) {
            bearing = f2;
            return this;
        }

        @NonNull
        public StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(tilt, bearing);
        }

        @NonNull
        public Builder tilt(final float f2) {
            tilt = f2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaOrientation(@SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3) {
        boolean z = -90.0f <= f2 && 90.0f >= f2;
        Preconditions.checkArgument(z, "Tilt needs to be between -90 and 90 inclusive: " + f2);
        tilt = f2 + 0.0f;
        bearing = (0.0d >= f3 ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation streetViewPanoramaOrientation)) {
            return false;
        }
        return Float.floatToIntBits(tilt) == Float.floatToIntBits(streetViewPanoramaOrientation.tilt) && Float.floatToIntBits(bearing) == Float.floatToIntBits(streetViewPanoramaOrientation.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(tilt), Float.valueOf(bearing));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("tilt", Float.valueOf(tilt)).add("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final float f2 = tilt;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, f2);
        SafeParcelWriter.writeFloat(parcel, 3, bearing);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
