package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zac> CREATOR = new zae();
    @SafeParcelable.VersionField
    final int zaa;
    @SafeParcelable.Field
    final String zab;
    @SafeParcelable.Field
    final int zac;

    @SafeParcelable.Constructor
    zac(@SafeParcelable.Param int i2, @SafeParcelable.Param String str, @SafeParcelable.Param int i3) {
        this.zaa = i2;
        this.zab = str;
        this.zac = i3;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    zac(String str, int i2) {
        this.zaa = 1;
        this.zab = str;
        this.zac = i2;
    }
}
