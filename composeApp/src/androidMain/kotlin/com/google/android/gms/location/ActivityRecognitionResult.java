package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Array;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzd();
    @SafeParcelable.Field
    List zza;
    @SafeParcelable.Field
    long zzb;
    @SafeParcelable.Field
    long zzc;
    @SafeParcelable.Field
    int zzd;
    @SafeParcelable.Field
    @Nullable
    Bundle zze;

    private static boolean zzb(@Nullable final Bundle bundle, @Nullable final Bundle bundle2) {
        int length;
        if (null == bundle) {
            return null == bundle2;
        }
        if (null == bundle2 || bundle.size() != bundle2.size()) {
            return false;
        }
        for (final String next : bundle.keySet()) {
            if (!bundle2.containsKey(next)) {
                return false;
            }
            final Object obj = bundle.get(next);
            final Object obj2 = bundle2.get(next);
            if (null == obj) {
                if (null != obj2) {
                    return false;
                }
            } else if (obj instanceof Bundle) {
                if (!ActivityRecognitionResult.zzb(bundle.getBundle(next), bundle2.getBundle(next))) {
                    return false;
                }
            } else if (obj.getClass().isArray()) {
                if (null != obj2 && obj2.getClass().isArray() && (length = Array.getLength(obj)) == Array.getLength(obj2)) {
                    int i2 = 0;
                    while (i2 < length) {
                        if (Objects.equal(Array.get(obj, i2), Array.get(obj2, i2))) {
                            i2++;
                        }
                    }
                    continue;
                }
                return false;
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    @ShowFirstParty
    public final boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && this.getClass() == obj.getClass()) {
            final ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
            return zzb == activityRecognitionResult.zzb && zzc == activityRecognitionResult.zzc && zzd == activityRecognitionResult.zzd && Objects.equal(zza, activityRecognitionResult.zza) && ActivityRecognitionResult.zzb(zze, activityRecognitionResult.zze);
        }
    }

    @ShowFirstParty
    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(zzb), Long.valueOf(zzc), Integer.valueOf(zzd), zza, zze);
    }

    @NonNull
    public String toString() {
        final String valueOf = String.valueOf(zza);
        final long j2 = zzb;
        final long j3 = zzc;
        final int length = valueOf.length();
        String sb = "ActivityRecognitionResult [probableActivities=" +
                valueOf +
                ", timeMillis=" +
                j2 +
                ", elapsedRealtimeMillis=" +
                j3 +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, zza, false);
        SafeParcelWriter.writeLong(parcel, 2, zzb);
        SafeParcelWriter.writeLong(parcel, 3, zzc);
        SafeParcelWriter.writeInt(parcel, 4, zzd);
        SafeParcelWriter.writeBundle(parcel, 5, zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ActivityRecognitionResult(@SafeParcelable.Param @NonNull final List list, @SafeParcelable.Param final long j2, @SafeParcelable.Param final long j3, @SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final Bundle bundle) {
        final boolean z = true;
        Preconditions.checkArgument(null != list && !list.isEmpty(), "Must have at least 1 detected activity");
        Preconditions.checkArgument(0 < j2 && 0 < j3 && z, "Must set times");
        zza = list;
        zzb = j2;
        zzc = j3;
        zzd = i2;
        zze = bundle;
    }
}
