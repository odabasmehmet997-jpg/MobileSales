package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzab();
    @NonNull
    public static final LocationAvailability zza = new LocationAvailability(0, 1, 1, 0, null, true);
    @NonNull
    public static final LocationAvailability zzb = new LocationAvailability(1000, 1, 1, 0, null, false);
    @SafeParcelable.Field
    final int zzc;
    @SafeParcelable.Field
    private final int zzd;
    @SafeParcelable.Field
    private final int zze;
    @SafeParcelable.Field
    private final long zzf;
    @SafeParcelable.Field
    private final zzal[] zzg;

    @SafeParcelable.Constructor
    LocationAvailability(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final int i4, @SafeParcelable.Param final long j2, @SafeParcelable.Param final zzal[] zzalArr, @SafeParcelable.Param final boolean z) {
        zzc = 1000 > i2 ? 0 : 1000;
        zzd = i3;
        zze = i4;
        zzf = j2;
        zzg = zzalArr;
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof LocationAvailability locationAvailability) {
            return zzd == locationAvailability.zzd && zze == locationAvailability.zze && zzf == locationAvailability.zzf && zzc == locationAvailability.zzc && Arrays.equals(zzg, locationAvailability.zzg);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzc));
    }

    public boolean isLocationAvailable() {
        return 1000 > this.zzc;
    }

    @NonNull
    public String toString() {
        final boolean isLocationAvailable = this.isLocationAvailable();
        String sb = "LocationAvailability[" +
                isLocationAvailable +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = zzd;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, zze);
        SafeParcelWriter.writeLong(parcel, 3, zzf);
        SafeParcelWriter.writeInt(parcel, 4, zzc);
        SafeParcelWriter.writeTypedArray(parcel, 5, zzg, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.isLocationAvailable());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
