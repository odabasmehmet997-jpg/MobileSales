package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzem> CREATOR = new zzen();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    @Nullable
    private final PendingIntent zzb;
    @SafeParcelable.Field
    private final String zzc;

    @SafeParcelable.Constructor
    zzem(@SafeParcelable.Param @Nullable final List list, @SafeParcelable.Param @Nullable final PendingIntent pendingIntent, @SafeParcelable.Param final String str) {
        zzex zzex;
        if (null == list) {
            zzex = com.google.android.gms.internal.location.zzex.zzi();
        } else {
            zzex = com.google.android.gms.internal.location.zzex.zzj(list);
        }
        zza = zzex;
        zzb = pendingIntent;
        zzc = str;
    }

    public static zzem zza(final List list) {
        Preconditions.checkNotNull(list, "geofence can't be null.");
        Preconditions.checkArgument(!list.isEmpty(), "Geofences must contains at least one id.");
        return new zzem(list, null, "");
    }

    public static zzem zzb(final PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent can not be null.");
        return new zzem(null, pendingIntent, "");
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final List list = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, list, false);
        SafeParcelWriter.writeParcelable(parcel, 2, zzb, i2, false);
        SafeParcelWriter.writeString(parcel, 3, zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
