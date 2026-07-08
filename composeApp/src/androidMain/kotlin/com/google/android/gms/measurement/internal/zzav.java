package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzav implements Parcelable.Creator {
    static void zza(zzau zzau, Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, zzau.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, zzau.zzb, i2, false);
        SafeParcelWriter.writeString(parcel, 4, zzau.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, zzau.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        zzas zzas = null;
        String str2 = null;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (2 == fieldId) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (3 == fieldId) {
                zzas = SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.measurement.internal.zzas.CREATOR);
            } else if (4 == fieldId) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzau(str, zzas, str2, j2);
    }

    public Object[] newArray(int i2) {
        return new zzau[i2];
    }
}
