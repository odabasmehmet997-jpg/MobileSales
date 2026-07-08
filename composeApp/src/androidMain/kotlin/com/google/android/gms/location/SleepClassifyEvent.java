package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class SleepClassifyEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SleepClassifyEvent> CREATOR = new zzao();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final int zzd;
    @SafeParcelable.Field
    private final int zze;
    @SafeParcelable.Field
    private final int zzf;
    @SafeParcelable.Field
    private final int zzg;
    @SafeParcelable.Field
    private final boolean zzh;
    @SafeParcelable.Field
    private final int zzi;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepClassifyEvent(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final int i4, @SafeParcelable.Param final int i5, @SafeParcelable.Param final int i6, @SafeParcelable.Param final int i7, @SafeParcelable.Param final int i8, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final int i9) {
        zza = i2;
        zzb = i3;
        zzc = i4;
        zzd = i5;
        zze = i6;
        zzf = i7;
        zzg = i8;
        zzh = z;
        zzi = i9;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepClassifyEvent sleepClassifyEvent)) {
            return false;
        }
        return zza == sleepClassifyEvent.zza && zzb == sleepClassifyEvent.zzb;
    }

    public int getConfidence() {
        return zzb;
    }

    public int getLight() {
        return zzd;
    }

    public int getMotion() {
        return zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza), Integer.valueOf(zzb));
    }

    @NonNull
    public String toString() {
        final int i2 = zza;
        final int length = String.valueOf(i2).length();
        final int i3 = zzb;
        final int length2 = String.valueOf(i3).length();
        final int i4 = zzc;
        final int length3 = String.valueOf(i4).length();
        final int i5 = zzd;
        String sb = i2 +
                " Conf:" +
                i3 +
                " Motion:" +
                i4 +
                " Light:" +
                i5;
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, zze);
        SafeParcelWriter.writeInt(parcel, 6, zzf);
        SafeParcelWriter.writeInt(parcel, 7, zzg);
        SafeParcelWriter.writeBoolean(parcel, 8, zzh);
        SafeParcelWriter.writeInt(parcel, 9, zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
