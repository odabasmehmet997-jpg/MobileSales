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

import java.util.*;

@SuppressWarnings("rawtypes")
@SafeParcelable.Class
@SafeParcelable.Reserved
public class ActivityTransitionRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ActivityTransitionRequest> CREATOR = new zzh();
    @NonNull
    public static final Comparator<ActivityTransition> IS_SAME_TRANSITION = new zzg();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    @Nullable
    private final String zzb;
    @SafeParcelable.Field
    private final List zzc;
    @SafeParcelable.Field
    @Nullable
    private final String zzd;
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && this.getClass() == obj.getClass()) {
            final ActivityTransitionRequest activityTransitionRequest = (ActivityTransitionRequest) obj;
            return Objects.equal(zza, activityTransitionRequest.zza) && Objects.equal(zzb, activityTransitionRequest.zzb) && Objects.equal(zzd, activityTransitionRequest.zzd) && Objects.equal(zzc, activityTransitionRequest.zzc);
        }
        return false;
    }
    public int hashCode() {
        final int hashCode = zza.hashCode() * 31;
        final String str = zzb;
        int i2 = 0;
        final int hashCode2 = (hashCode + (null != str ? str.hashCode() : 0)) * 31;
        final List list = zzc;
        final int hashCode3 = (hashCode2 + (null != list ? list.hashCode() : 0)) * 31;
        final String str2 = zzd;
        if (null != str2) {
            i2 = str2.hashCode();
        }
        return hashCode3 + i2;
    }
    @NonNull
    public String toString() {
        final List list = zzc;
        final String valueOf = String.valueOf(zza);
        final String valueOf2 = String.valueOf(list);
        final String str = zzd;
        final int length = valueOf.length();
        final String str2 = zzb;
        final int length2 = String.valueOf(str2).length();
        String sb = "ActivityTransitionRequest [mTransitions=" +
                valueOf +
                ", mTag='" +
                str2 +
                "', mClients=" +
                valueOf2 +
                ", mAttributionTag=" +
                str +
                "]";
        return sb;
    }
    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final List list = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.writeString(parcel, 2, zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, zzc, false);
        SafeParcelWriter.writeString(parcel, 4, zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    @SafeParcelable.Constructor
    public ActivityTransitionRequest(@SafeParcelable.Param @NonNull final List list, @SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @Nullable final List list2, @SafeParcelable.Param @Nullable final String str2) {
        final List list3;
        Preconditions.checkNotNull(list, "transitions can't be null");
        Preconditions.checkArgument(!list.isEmpty(), "transitions can't be empty.");
        Preconditions.checkNotNull(list);
        final TreeSet treeSet = new TreeSet(ActivityTransitionRequest.IS_SAME_TRANSITION);
        final Iterator it = list.iterator();
        while (it.hasNext()) {
            final ActivityTransition activityTransition = (ActivityTransition) it.next();
            Preconditions.checkArgument(treeSet.add(activityTransition), String.format("Found duplicated transition: %s.", activityTransition));
        }
        zza = Collections.unmodifiableList(list);
        zzb = str;
        if (null == list2) {
            list3 = Collections.emptyList();
        } else {
            list3 = Collections.unmodifiableList(list2);
        }
        zzc = list3;
        zzd = str2;
    }
}
