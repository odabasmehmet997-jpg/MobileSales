package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zav;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zal();
    @SafeParcelable.VersionField
    final int zaa;
    @SafeParcelable.Field
    private final ConnectionResult zab;
    @SafeParcelable.Field
    @Nullable
    private final zav zac;

    @SafeParcelable.Constructor
    zak(@SafeParcelable.Param int i2, @SafeParcelable.Param ConnectionResult connectionResult, @SafeParcelable.Param @Nullable zav zav) {
        this.zaa = i2;
        this.zab = connectionResult;
        this.zac = zav;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zab, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ConnectionResult zaa() {
        return this.zab;
    }

    @Nullable
    public zav zab() {
        return this.zac;
    }
}
