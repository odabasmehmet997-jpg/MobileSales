package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class GeofencingRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzp();
    @SafeParcelable.Field
    private final List zza;
    @InitialTrigger
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    @Nullable
    private final String zzc;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final List zza = new ArrayList();
        @InitialTrigger
        private final int zzb = 5;
    }

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public @interface InitialTrigger {
    }

    @SafeParcelable.Constructor
    GeofencingRequest(@SafeParcelable.Param final List list, @InitialTrigger @SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final String str) {
        zza = list;
        zzb = i2;
        zzc = str;
    }

    @InitialTrigger
    public int getInitialTrigger() {
        return zzb;
    }

    @NonNull
    public String toString() {
        final String valueOf = String.valueOf(zza);
        final int length = valueOf.length();
        final int i2 = zzb;
        String sb = "GeofencingRequest[geofences=" +
                valueOf +
                ", initialTrigger=" +
                i2 +
                "]";
        return sb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final List list = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
