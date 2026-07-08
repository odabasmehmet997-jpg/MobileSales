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
import java.util.Comparator;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class DetectedActivity extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DetectedActivity> CREATOR = new zzl();
    @NonNull
    public static final Comparator zza = new zzk();
    @SafeParcelable.Field
    final int zzb;
    @SafeParcelable.Field
    final int zzc;

    @SafeParcelable.Constructor
    public DetectedActivity(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3) {
        zzb = i2;
        zzc = i3;
    }

    @ShowFirstParty
    public final boolean equals(@Nullable final Object obj) {
        if (obj instanceof DetectedActivity detectedActivity) {
            return zzb == detectedActivity.zzb && zzc == detectedActivity.zzc;
        }
        return false;
    }

    public int getConfidence() {
        return zzc;
    }

    public int getType() {
        final int i2 = zzb;
        if (22 < i2 || 0 > i2) {
            return 4;
        }
        return i2;
    }

    @ShowFirstParty
    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), Integer.valueOf(zzc));
    }

    @NonNull
    public String toString() {
        final String str;
        final int type = this.getType();
        if (0 != type) {
            str = 1 != type ? 2 != type ? 3 != type ? 4 != type ? 5 != type ? 7 != type ? 8 != type ? 16 != type ? 17 != type ? Integer.toString(type) : "IN_RAIL_VEHICLE" : "IN_ROAD_VEHICLE" : "RUNNING" : "WALKING" : "TILTING" : "UNKNOWN" : "STILL" : "ON_FOOT" : "ON_BICYCLE";
        } else {
            str = "IN_VEHICLE";
        }
        final int i2 = zzc;
        String sb = "DetectedActivity [type=" +
                str +
                ", confidence=" +
                i2 +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zzb);
        SafeParcelWriter.writeInt(parcel, 2, zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
