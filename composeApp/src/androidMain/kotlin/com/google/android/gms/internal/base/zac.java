package com.google.android.gms.internal.base;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public enum zac {
    ;
    private static final ClassLoader zaa = zac.class.getClassLoader();

    public static Parcelable zaa(final Parcel parcel, final Parcelable.Creator creator) {
        if (0 == parcel.readInt()) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void zab(final Parcel parcel) {
        final int dataAvail = parcel.dataAvail();
        if (0 < dataAvail) {
            throw new BadParcelableException("Parcel data not fully consumed, unread size: " + dataAvail);
        }
    }

    public static void zac(final Parcel parcel, final Parcelable parcelable) {
        if (null == parcelable) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    public static void zad(final Parcel parcel, final IInterface iInterface) {
        if (null == iInterface) {
            parcel.writeStrongBinder(null);
        } else {
            parcel.writeStrongBinder(iInterface.asBinder());
        }
    }
}
