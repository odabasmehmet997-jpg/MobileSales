package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR = new zzx();
    @SafeParcelable.Field
    public final float bearing;
    @SafeParcelable.Field
    public final float tilt;
    @SafeParcelable.Field
    public final float zoom;
    private final StreetViewPanoramaOrientation zza;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaCamera(@SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final float f4) {
        boolean z = -90.0f <= f3 && 90.0f >= f3;
        Preconditions.checkArgument(z, "Tilt needs to be between -90 and 90 inclusive: " + f3);
        zoom = 0.0d >= f2 ? 0.0f : f2;
        tilt = 0.0f + f3;
        bearing = (0.0d >= f4 ? (f4 % 360.0f) + 360.0f : f4) % 360.0f;
        final StreetViewPanoramaOrientation.Builder builder = new StreetViewPanoramaOrientation.Builder();
        builder.tilt(f3);
        builder.bearing(f4);
        zza = builder.build();
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera streetViewPanoramaCamera)) {
            return false;
        }
        return Float.floatToIntBits(zoom) == Float.floatToIntBits(streetViewPanoramaCamera.zoom) && Float.floatToIntBits(tilt) == Float.floatToIntBits(streetViewPanoramaCamera.tilt) && Float.floatToIntBits(bearing) == Float.floatToIntBits(streetViewPanoramaCamera.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(zoom), Float.valueOf(tilt), Float.valueOf(bearing));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("zoom", Float.valueOf(zoom)).add("tilt", Float.valueOf(tilt)).add("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final float f2 = zoom;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, f2);
        SafeParcelWriter.writeFloat(parcel, 3, tilt);
        SafeParcelWriter.writeFloat(parcel, 4, bearing);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
