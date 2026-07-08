package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zav;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zal implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ConnectionResult connectionResult = null;
        int i2 = 0;
        zav zav = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                connectionResult = SafeParcelReader.createParcelable(parcel, readHeader, ConnectionResult.CREATOR);
            } else if (3 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zav = SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.common.internal.zav.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zak(i2, connectionResult, zav);
    }

    public Object[] newArray(int i2) {
        return new zak[i2];
    }
}
