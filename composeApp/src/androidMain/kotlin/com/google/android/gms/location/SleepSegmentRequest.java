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
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class SleepSegmentRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SleepSegmentRequest> CREATOR = new zzaq();
    @SafeParcelable.Field
    @Nullable
    private final List zza;
    @SafeParcelable.Field
    private final int zzb;

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepSegmentRequest sleepSegmentRequest)) {
            return false;
        }
        return Objects.equal(zza, sleepSegmentRequest.zza) && zzb == sleepSegmentRequest.zzb;
    }

    public int getRequestedDataType() {
        return zzb;
    }

    public int hashCode() {
        return Objects.hashCode(zza, Integer.valueOf(zzb));
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        Preconditions.checkNotNull(parcel);
        final List list = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SleepSegmentRequest(@SafeParcelable.Param @Nullable final List list, @SafeParcelable.Param final int i2) {
        zza = list;
        zzb = i2;
    }
}
