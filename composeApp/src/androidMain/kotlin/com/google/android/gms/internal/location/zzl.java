package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzl extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    public static final zzl zza = new zzl(Status.RESULT_SUCCESS);
    @SafeParcelable.Field
    private final Status zzb;

    @SafeParcelable.Constructor
    public zzl(@SafeParcelable.Param final Status status) {
        zzb = status;
    }

    public Status getStatus() {
        return zzb;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
