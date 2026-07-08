package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zze;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    @SafeParcelable.Field
    private final boolean zza;
    @SafeParcelable.Field
    @Nullable
    private final zze zzb;

    @SafeParcelable.Constructor
    zzad(@SafeParcelable.Param final boolean z, @SafeParcelable.Param @Nullable final zze zze) {
        zza = z;
        zzb = zze;
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof zzad zzad)) {
            return false;
        }
        return zza == zzad.zza && Objects.equal(zzb, zzad.zzb);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(zza));
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LocationAvailabilityRequest[");
        if (zza) {
            sb.append("bypass, ");
        }
        if (null != this.zzb) {
            sb.append("impersonation=");
            sb.append(zzb);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final boolean z = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, z);
        SafeParcelWriter.writeParcelable(parcel, 2, zzb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
