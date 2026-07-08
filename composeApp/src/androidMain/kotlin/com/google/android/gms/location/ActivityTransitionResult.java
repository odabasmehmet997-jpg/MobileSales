package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class ActivityTransitionResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransitionResult> CREATOR = new zzi();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    @Nullable
    private Bundle zzb;

    public ActivityTransitionResult(@SafeParcelable.Param @NonNull final List<ActivityTransitionEvent> list) {
        zzb = null;
        Preconditions.checkNotNull(list, "transitionEvents list can't be null.");
        if (!list.isEmpty()) {
            for (int i2 = 1; i2 < list.size(); i2++) {
                final int i3 = i2 - 1;
                Preconditions.checkArgument(list.get(i2).getElapsedRealTimeNanos() >= list.get(i3).getElapsedRealTimeNanos(), "Transition out of order: ts1=%d, ts2=%d", Long.valueOf(list.get(i2).getElapsedRealTimeNanos()), Long.valueOf(list.get(i3).getElapsedRealTimeNanos()));
            }
        }
        zza = Collections.unmodifiableList(list);
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || this.getClass() != obj.getClass()) {
            return false;
        }
        return zza.equals(((ActivityTransitionResult) obj).zza);
    }

    @NonNull
    public List<ActivityTransitionEvent> getTransitionEvents() {
        return zza;
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.getTransitionEvents(), false);
        SafeParcelWriter.writeBundle(parcel, 2, zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ActivityTransitionResult(@SafeParcelable.Param @NonNull final List list, @SafeParcelable.Param @Nullable final Bundle bundle) {
        this(list);
        zzb = bundle;
    }
}
