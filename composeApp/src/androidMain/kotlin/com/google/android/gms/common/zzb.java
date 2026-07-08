package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzb implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        PendingIntent pendingIntent = null;
        int i2 = 0;
        int i3 = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (3 == fieldId) {
                pendingIntent = SafeParcelReader.createParcelable(parcel, readHeader, PendingIntent.CREATOR);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ConnectionResult(i2, i3, pendingIntent, str);
    }

    public Object[] newArray(final int i2) {
        return new ConnectionResult[i2];
    }
}
