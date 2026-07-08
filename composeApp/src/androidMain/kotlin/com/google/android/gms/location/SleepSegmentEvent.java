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
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class SleepSegmentEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SleepSegmentEvent> CREATOR = new zzap();
    @SafeParcelable.Field
    private final long zza;
    @SafeParcelable.Field
    private final long zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final int zzd;
    @SafeParcelable.Field
    private final int zze;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentEvent(@SafeParcelable.Param final long j2, @SafeParcelable.Param final long j3, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final int i4) {
        Preconditions.checkArgument(j2 <= j3, "endTimeMillis must be greater than or equal to startTimeMillis");
        zza = j2;
        zzb = j3;
        zzc = i2;
        zzd = i3;
        zze = i4;
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof SleepSegmentEvent sleepSegmentEvent) {
            return zza == sleepSegmentEvent.zza && zzb == sleepSegmentEvent.zzb && zzc == sleepSegmentEvent.zzc && zzd == sleepSegmentEvent.zzd && zze == sleepSegmentEvent.zze;
        }
        return false;
    }

    public long getEndTimeMillis() {
        return zzb;
    }

    public long getStartTimeMillis() {
        return zza;
    }

    public int getStatus() {
        return zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(zza), Long.valueOf(zzb), Integer.valueOf(zzc));
    }

    @NonNull
    public String toString() {
        final long j2 = zza;
        final int length = String.valueOf(j2).length();
        final long j3 = zzb;
        final int length2 = String.valueOf(j3).length();
        final int i2 = zzc;
        String sb = "startMillis=" +
                j2 +
                ", endMillis=" +
                j3 +
                ", status=" +
                i2;
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, zzd);
        SafeParcelWriter.writeInt(parcel, 5, zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
