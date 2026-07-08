package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LocationSettingsStates extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzaj();
    @SafeParcelable.Field
    private final boolean zza;
    @SafeParcelable.Field
    private final boolean zzb;
    @SafeParcelable.Field
    private final boolean zzc;
    @SafeParcelable.Field
    private final boolean zzd;
    @SafeParcelable.Field
    private final boolean zze;
    @SafeParcelable.Field
    private final boolean zzf;

    @SafeParcelable.Constructor
    public LocationSettingsStates(@SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param final boolean z4, @SafeParcelable.Param final boolean z5, @SafeParcelable.Param final boolean z6) {
        zza = z;
        zzb = z2;
        zzc = z3;
        zzd = z4;
        zze = z5;
        zzf = z6;
    }

    public boolean isBlePresent() {
        return zzf;
    }

    public boolean isBleUsable() {
        return zzc;
    }

    public boolean isGpsPresent() {
        return zzd;
    }

    public boolean isGpsUsable() {
        return zza;
    }

    public boolean isNetworkLocationPresent() {
        return zze;
    }

    public boolean isNetworkLocationUsable() {
        return zzb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
