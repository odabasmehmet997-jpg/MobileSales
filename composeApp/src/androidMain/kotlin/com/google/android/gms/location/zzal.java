package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class
@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzal> CREATOR = new zzam();
    @SafeParcelable.Field
    public final int zza;
    @SafeParcelable.Field
    public final int zzb;
    @SafeParcelable.Field
    public final long zzc;
    @SafeParcelable.Field
    public final long zzd;

    @SafeParcelable.Constructor
    zzal(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final long j2, @SafeParcelable.Param final long j3) {
        zza = i2;
        zzb = i3;
        zzc = j2;
        zzd = j3;
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof zzal zzal) {
            return zza == zzal.zza && zzb == zzal.zzb && zzc == zzal.zzc && zzd == zzal.zzd;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), Integer.valueOf(zza), Long.valueOf(zzd), Long.valueOf(zzc));
    }

    public String toString() {
        final int i2 = zza;
        final int length = String.valueOf(i2).length();
        final int i3 = zzb;
        final int length2 = String.valueOf(i3).length();
        final long j2 = zzd;
        final int length3 = String.valueOf(j2).length();
        final long j3 = zzc;
        String sb = "NetworkLocationStatus: Wifi status: " +
                i2 +
                " Cell status: " +
                i3 +
                " elapsed time NS: " +
                j2 +
                " system time ms: " +
                j3;
        return sb;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, zzb);
        SafeParcelWriter.writeLong(parcel, 3, zzc);
        SafeParcelWriter.writeLong(parcel, 4, zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
