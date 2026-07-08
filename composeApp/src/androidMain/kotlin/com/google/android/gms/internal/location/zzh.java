package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    static final List zza = Collections.emptyList();
    static final DeviceOrientationRequest zzb = new DeviceOrientationRequest.Builder(20000).build();
    @SafeParcelable.Field
    final DeviceOrientationRequest zzc;
    @SafeParcelable.Field
    final List zzd;
    @SafeParcelable.Field
    @Nullable
    final String zze;

    @SafeParcelable.Constructor
    zzh(@SafeParcelable.Param final DeviceOrientationRequest deviceOrientationRequest, @SafeParcelable.Param final List list, @SafeParcelable.Param final String str) {
        zzc = deviceOrientationRequest;
        zzd = list;
        zze = str;
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof zzh zzh)) {
            return false;
        }
        return Objects.equal(zzc, zzh.zzc) && Objects.equal(zzd, zzh.zzd) && Objects.equal(zze, zzh.zze);
    }

    public int hashCode() {
        return zzc.hashCode();
    }

    public String toString() {
        final String valueOf = String.valueOf(zzc);
        final String valueOf2 = String.valueOf(zzd);
        final int length = valueOf.length();
        final int length2 = valueOf2.length();
        final String str = zze;
        String sb = "DeviceOrientationRequestInternal[deviceOrientationRequest=" +
                valueOf +
                ", clients=" +
                valueOf2 +
                ", tag='" +
                str +
                "']";
        return sb;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzc, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 2, zzd, false);
        SafeParcelWriter.writeString(parcel, 3, zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
