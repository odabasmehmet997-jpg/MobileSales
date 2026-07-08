package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzas extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzas> CREATOR = new zzat();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final int zzd;

    @SafeParcelable.Constructor
    public zzas(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final int i4, @SafeParcelable.Param final int i5) {
        final boolean z = true;
        Preconditions.checkState(0 <= i2 && 23 >= i2, "Start hour must be in range [0, 23].");
        Preconditions.checkState(0 <= i3 && 59 >= i3, "Start minute must be in range [0, 59].");
        Preconditions.checkState(0 <= i4 && 23 >= i4, "End hour must be in range [0, 23].");
        Preconditions.checkState(0 <= i5 && 59 >= i5, "End minute must be in range [0, 59].");
        Preconditions.checkState(0 < ((i2 + i3) + i4) + i5 && z, "Parameters can't be all 0.");
        zza = i2;
        zzb = i3;
        zzc = i4;
        zzd = i5;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzas zzas)) {
            return false;
        }
        return zza == zzas.zza && zzb == zzas.zzb && zzc == zzas.zzc && zzd == zzas.zzd;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza), Integer.valueOf(zzb), Integer.valueOf(zzc), Integer.valueOf(zzd));
    }

    public String toString() {
        final int i2 = zza;
        final int length = String.valueOf(i2).length();
        final int i3 = zzb;
        final int length2 = String.valueOf(i3).length();
        final int i4 = zzc;
        final int length3 = String.valueOf(i4).length();
        final int i5 = zzd;
        String sb = "UserPreferredSleepWindow [startHour=" +
                i2 +
                ", startMinute=" +
                i3 +
                ", endHour=" +
                i4 +
                ", endMinute=" +
                i5 +
                "]";
        return sb;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, zzb);
        SafeParcelWriter.writeInt(parcel, 3, zzc);
        SafeParcelWriter.writeInt(parcel, 4, zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
