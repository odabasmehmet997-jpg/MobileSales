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
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LastLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Creator<LastLocationRequest> CREATOR = new zzaa();
    @Field
    private final long zza;
    @Field
    private final int zzb;
    @Field
    private final boolean zzc;
    @Field
    @Nullable
    private final zze zzd;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final long zza = LocationRequestCompat.PASSIVE_INTERVAL;
        private final int zzb = 0;
        private final boolean zzc = false;
        @Nullable
        private final zze zzd = null;

        @NonNull
        public LastLocationRequest build() {
            return new LastLocationRequest(zza, zzb, zzc, zzd);
        }
    }

    @Constructor
    LastLocationRequest(@Param final long j2, @Param final int i2, @Param final boolean z, @Param @Nullable final zze zze) {
        zza = j2;
        zzb = i2;
        zzc = z;
        zzd = zze;
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof LastLocationRequest lastLocationRequest)) {
            return false;
        }
        return zza == lastLocationRequest.zza && zzb == lastLocationRequest.zzb && zzc == lastLocationRequest.zzc && Objects.equal(zzd, lastLocationRequest.zzd);
    }

    public int getGranularity() {
        return zzb;
    }

    public long getMaxUpdateAgeMillis() {
        return zza;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(zza), Integer.valueOf(zzb), Boolean.valueOf(zzc));
    }

    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LastLocationRequest[");
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.zza) {
            sb.append("maxAge=");
            zzeo.zzc(zza, sb);
        }
        if (0 != this.zzb) {
            sb.append(", ");
            sb.append(zzq.zzb(zzb));
        }
        if (zzc) {
            sb.append(", bypass");
        }
        if (null != this.zzd) {
            sb.append(", impersonation=");
            sb.append(zzd);
        }
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, zzc);
        SafeParcelWriter.writeParcelable(parcel, 5, zzd, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
