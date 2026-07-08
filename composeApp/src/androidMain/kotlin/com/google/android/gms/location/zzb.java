package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();
    @SafeParcelable.Field
    private final long zza;
    @SafeParcelable.Field
    private final boolean zzb;
    @SafeParcelable.Field
    @Nullable
    private final WorkSource zzc;
    @SafeParcelable.Field
    @Nullable
    private final String zzd;
    @SafeParcelable.Field
    @Nullable
    private final int[] zze;
    @SafeParcelable.Field
    private final boolean zzf;
    @SafeParcelable.Field
    @Nullable
    private final String zzg;
    @SafeParcelable.Field
    private final long zzh;
    @SafeParcelable.Field
    @Nullable
    private final String zzi;

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param final long j2, @SafeParcelable.Param final boolean z, @SafeParcelable.Param @Nullable final WorkSource workSource, @SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @Nullable final int[] iArr, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param @Nullable final String str2, @SafeParcelable.Param final long j3, @SafeParcelable.Param @Nullable final String str3) {
        zza = j2;
        zzb = z;
        zzc = workSource;
        zzd = str;
        zze = iArr;
        zzf = z2;
        zzg = str2;
        zzh = j3;
        zzi = str3;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, zza);
        SafeParcelWriter.writeBoolean(parcel, 2, zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, zzc, i2, false);
        SafeParcelWriter.writeString(parcel, 4, zzd, false);
        SafeParcelWriter.writeIntArray(parcel, 5, zze, false);
        SafeParcelWriter.writeBoolean(parcel, 6, zzf);
        SafeParcelWriter.writeString(parcel, 7, zzg, false);
        SafeParcelWriter.writeLong(parcel, 8, zzh);
        SafeParcelWriter.writeString(parcel, 9, zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
