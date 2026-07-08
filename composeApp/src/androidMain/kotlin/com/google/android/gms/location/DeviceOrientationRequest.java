package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzer;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class DeviceOrientationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DeviceOrientationRequest> CREATOR = new zzn();
    @SafeParcelable.Field
    private final long zza;
    @SafeParcelable.Field
    private final boolean zzb;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private long zza;
        private final boolean zzb = false;

        public Builder(final long j2) {
            this.setSamplingPeriodMicros(j2);
        }

        @NonNull
        public DeviceOrientationRequest build() {
            return new DeviceOrientationRequest(zza, zzb);
        }

        @NonNull
        public Builder setSamplingPeriodMicros(final long j2) {
            boolean z = 0 <= j2 && LocationRequestCompat.PASSIVE_INTERVAL > j2;
            String sb = "Invalid interval: " +
                    j2 +
                    " should be greater than or equal to 0. Note: Long.MAX_VALUE is not a valid interval.";
            zzer.zzb(z, sb);
            zza = j2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    DeviceOrientationRequest(@SafeParcelable.Param final long j2, @SafeParcelable.Param final boolean z) {
        zza = j2;
        zzb = z;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceOrientationRequest deviceOrientationRequest)) {
            return false;
        }
        return zza == deviceOrientationRequest.zza && zzb == deviceOrientationRequest.zzb;
    }

    public long getSamplingPeriodMicros() {
        return zza;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(zza), Boolean.valueOf(zzb));
    }

    @NonNull
    public String toString() {
        final long j2 = zza;
        final int length = String.valueOf(j2).length();
        final String str = !zzb ? "" : ", withVelocity";
        String sb = "DeviceOrientationRequest[samplingPeriodMicros=" +
                j2 +
                str +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zza);
        SafeParcelWriter.writeBoolean(parcel, 6, zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
