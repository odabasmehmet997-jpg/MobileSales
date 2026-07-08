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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class ActivityTransition extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransition> CREATOR = new zze();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    private final int zzb;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static class Builder {
        private final int zza = -1;
        private final int zzb = -1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public @interface SupportedActivityTransition {
    }

    @SafeParcelable.Constructor
    ActivityTransition(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3) {
        zza = i2;
        zzb = i3;
    }

    public static void zza(final int i2) {
        boolean z = 0 <= i2 && 1 >= i2;
        String sb = "Transition type " +
                i2 +
                " is not valid.";
        Preconditions.checkArgument(z, sb);
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransition activityTransition)) {
            return false;
        }
        return zza == activityTransition.zza && zzb == activityTransition.zzb;
    }

    public int getActivityType() {
        return zza;
    }

    public int getTransitionType() {
        return zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza), Integer.valueOf(zzb));
    }

    @NonNull
    public String toString() {
        final int i2 = zza;
        final int length = String.valueOf(i2).length();
        final int i3 = zzb;
        String sb = "ActivityTransition [mActivityType=" +
                i2 +
                ", mTransitionType=" +
                i3 +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
