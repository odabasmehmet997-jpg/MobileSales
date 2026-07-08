package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzb implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (3 == fieldId) {
                pendingIntent = SafeParcelReader.createParcelable(parcel, readHeader, PendingIntent.CREATOR);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                connectionResult = SafeParcelReader.createParcelable(parcel, readHeader, ConnectionResult.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Status(i2, str, pendingIntent, connectionResult);
    }

    public Object[] newArray(final int i2) {
        return new Status[i2];
    }
}
