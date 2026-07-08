package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class StreetViewSource extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzab();
    @NonNull
    public static final StreetViewSource DEFAULT = new StreetViewSource(0);
    @NonNull
    public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
    @SafeParcelable.Field
    private final int zza;

    @SafeParcelable.Constructor
    public StreetViewSource(@SafeParcelable.Param final int i2) {
        zza = i2;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StreetViewSource) && zza == ((StreetViewSource) obj).zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza));
    }

    @NonNull
    public String toString() {
        final String str;
        final int i2 = zza;
        if (0 != i2) {
            str = 1 != i2 ? String.format("UNKNOWN(%s)", Integer.valueOf(i2)) : "OUTDOOR";
        } else {
            str = "DEFAULT";
        }
        return String.format("StreetViewSource:%s", str);
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i3);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
