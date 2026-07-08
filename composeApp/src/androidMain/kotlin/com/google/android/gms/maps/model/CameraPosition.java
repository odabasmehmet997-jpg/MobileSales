package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class CameraPosition extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<CameraPosition> CREATOR = new zza();
    @SafeParcelable.Field
    public final float bearing;
    @SafeParcelable.Field
    @NonNull
    public final LatLng target;
    @SafeParcelable.Field
    public final float tilt;
    @SafeParcelable.Field
    public final float zoom;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
        private LatLng zza;
        private float zzb;
        private float zzc;
        private float zzd;

        @NonNull
        public Builder bearing(final float f2) {
            zzd = f2;
            return this;
        }

        @NonNull
        public CameraPosition build() {
            return new CameraPosition(zza, zzb, zzc, zzd);
        }

        @NonNull
        public Builder target(@NonNull final LatLng latLng) {
            zza = Preconditions.checkNotNull(latLng, "location must not be null.");
            return this;
        }

        @NonNull
        public Builder tilt(final float f2) {
            zzc = f2;
            return this;
        }

        @NonNull
        public Builder zoom(final float f2) {
            zzb = f2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public CameraPosition(@SafeParcelable.Param @NonNull final LatLng latLng, @SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final float f4) {
        Preconditions.checkNotNull(latLng, "camera target must not be null.");
        boolean z = 0.0f <= f3 && 90.0f >= f3;
        Preconditions.checkArgument(z, "Tilt needs to be between 0 and 90 inclusive: %s", Float.valueOf(f3));
        target = latLng;
        zoom = f2;
        tilt = f3 + 0.0f;
        bearing = (0.0d >= f4 ? (f4 % 360.0f) + 360.0f : f4) % 360.0f;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition cameraPosition)) {
            return false;
        }
        return target.equals(cameraPosition.target) && Float.floatToIntBits(zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(target, Float.valueOf(zoom), Float.valueOf(tilt), Float.valueOf(bearing));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(TypedValues.AttributesType.S_TARGET, target).add("zoom", Float.valueOf(zoom)).add("tilt", Float.valueOf(tilt)).add("bearing", Float.valueOf(bearing)).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final LatLng latLng = target;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng, i2, false);
        SafeParcelWriter.writeFloat(parcel, 3, zoom);
        SafeParcelWriter.writeFloat(parcel, 4, tilt);
        SafeParcelWriter.writeFloat(parcel, 5, bearing);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
