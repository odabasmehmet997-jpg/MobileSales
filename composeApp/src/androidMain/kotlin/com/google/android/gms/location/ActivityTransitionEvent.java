package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class ActivityTransitionEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransitionEvent> CREATOR = new zzf();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final long zzc;

    @SafeParcelable.Constructor
    public ActivityTransitionEvent(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final long j2) {
        ActivityTransition.zza(i3);
        zza = i2;
        zzb = i3;
        zzc = j2;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransitionEvent activityTransitionEvent)) {
            return false;
        }
        return zza == activityTransitionEvent.zza && zzb == activityTransitionEvent.zzb && zzc == activityTransitionEvent.zzc;
    }

    public int getActivityType() {
        return zza;
    }

    public long getElapsedRealTimeNanos() {
        return zzc;
    }

    public int getTransitionType() {
        return zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza), Integer.valueOf(zzb), Long.valueOf(zzc));
    }

    @NonNull
    public String toString() {
        final int i2 = zza;
        String sb2 = "ActivityType " +
                i2;
        final int i3 = zzb;
        String sb3 = "TransitionType " +
                i3;
        final long j2 = zzc;
        String sb4 = "ElapsedRealTimeNanos " +
                j2;
        String sb = sb2 +
                " " +
                sb3 +
                " " +
                sb4;
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
