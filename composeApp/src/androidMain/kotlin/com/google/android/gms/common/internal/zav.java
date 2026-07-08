package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zav extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zav> CREATOR = new zaw();
    @SafeParcelable.VersionField
    final int zaa;
    @SafeParcelable.Field
    @Nullable
    final IBinder zab;
    @SafeParcelable.Field
    private final ConnectionResult zac;
    @SafeParcelable.Field
    private final boolean zad;
    @SafeParcelable.Field
    private final boolean zae;

    @SafeParcelable.Constructor
    zav(@SafeParcelable.Param int i2, @SafeParcelable.Param @Nullable IBinder iBinder, @SafeParcelable.Param ConnectionResult connectionResult, @SafeParcelable.Param boolean z, @SafeParcelable.Param boolean z2) {
        this.zaa = i2;
        this.zab = iBinder;
        this.zac = connectionResult;
        this.zad = z;
        this.zae = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zav zav)) {
            return false;
        }
        return this.zac.equals(zav.zac) && Objects.equal(zab(), zav.zab());
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zad);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ConnectionResult zaa() {
        return this.zac;
    }

    @Nullable
    public IAccountAccessor zab() {
        IBinder iBinder = this.zab;
        if (null == iBinder) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public boolean zac() {
        return this.zad;
    }

    public boolean zad() {
        return this.zae;
    }
}
