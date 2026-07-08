package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzag();
    static final List zza = Collections.emptyList();
    @SafeParcelable.Field
    private final List zzb;

    @SafeParcelable.Constructor
    LocationResult(@SafeParcelable.Param final List list) {
        zzb = list;
    }

    public boolean equals(@androidx.annotation.Nullable final java.lang.Object r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationResult.equals(java.lang.Object):boolean");
    }

    @Nullable
    public Location getLastLocation() {
        final int size = zzb.size();
        if (0 == size) {
            return null;
        }
        return (Location) zzb.get(size - 1);
    }

    @NonNull
    public List<Location> getLocations() {
        return zzb;
    }

    public int hashCode() {
        return Objects.hashCode(zzb);
    }

    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationResult");
        final int i2 = zzak.r8clinit;
        final List<Location> list = zzb;
        sb.ensureCapacity(list.size() * 100);
        sb.append("[");
        boolean z = false;
        for (final Location zza2 : list) {
            zzak.zza(zza2, sb);
            sb.append(", ");
            z = true;
        }
        if (z) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
