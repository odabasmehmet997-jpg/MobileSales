package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new zaa();
    final int zaa;
    private final int zab;
    private final Bundle zac;
    GoogleSignInOptionsExtensionParcelable( final int i2,  final int i3,  final Bundle bundle) {
        zaa = i2;
        zab = i3;
        zac = bundle;
    }
    public int getType() {
        return zab;
    }
    public final void writeToParcel( final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zaa);
        SafeParcelWriter.writeInt(parcel, 2, zab);
        SafeParcelWriter.writeBundle(parcel, 3, zac, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
