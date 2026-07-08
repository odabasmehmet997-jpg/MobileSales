package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzah();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    private final boolean zzb;
    @SafeParcelable.Field
    private final boolean zzc;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final ArrayList zza = new ArrayList();
        private final boolean zzb;
        private final boolean zzc;
    }

    @SafeParcelable.Constructor
    LocationSettingsRequest(@SafeParcelable.Param final List list, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2) {
        zza = list;
        zzb = z;
        zzc = z2;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final List list = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(list), false);
        SafeParcelWriter.writeBoolean(parcel, 2, zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
