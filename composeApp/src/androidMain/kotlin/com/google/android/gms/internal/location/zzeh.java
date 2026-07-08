package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzeh implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final Parcel parcel2 = parcel;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j2 = Long.MAX_VALUE;
        LocationRequest locationRequest = null;
        ArrayList<ClientIdentity> arrayList = null;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                locationRequest = SafeParcelReader.createParcelable(parcel2, readHeader, LocationRequest.CREATOR);
            } else if (5 == fieldId) {
                arrayList = SafeParcelReader.createTypedList(parcel2, readHeader, ClientIdentity.CREATOR);
            } else if (8 == fieldId) {
                z = SafeParcelReader.readBoolean(parcel2, readHeader);
            } else if (9 != fieldId) {
                switch (fieldId) {
                    case 11:
                        z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 12:
                        z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                        break;
                    case 13:
                        str = SafeParcelReader.createString(parcel2, readHeader);
                        break;
                    case 14:
                        j2 = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzeg(locationRequest, arrayList, z, z2, z3, z4, str, j2);
    }

    public Object[] newArray(final int i2) {
        return new zzeg[i2];
    }
}
