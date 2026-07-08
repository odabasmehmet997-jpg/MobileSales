package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzj implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        String str = null;
        IBinder iBinder = null;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (2 == fieldId) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (3 == fieldId) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (4 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PinConfig.Glyph(str, iBinder, i2, i3);
    }

    public Object[] newArray(final int i2) {
        return new PinConfig.Glyph[i2];
    }
}
