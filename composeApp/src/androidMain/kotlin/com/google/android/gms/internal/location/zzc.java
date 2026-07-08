package com.google.android.gms.internal.location;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzc {
    ;
    public static final int r8clinit = 0;
    private static final ClassLoader zzb = zzc.class.getClassLoader();

    public static Parcelable zza(final Parcel parcel, final Parcelable.Creator creator) {
        if (0 == parcel.readInt()) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void zzb(final Parcel parcel, final Parcelable parcelable) {
        if (null == parcelable) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    public static void zzc(final Parcel parcel, final IInterface iInterface) {
        parcel.writeStrongBinder(iInterface.asBinder());
    }

    public static void zzd(final Parcel parcel) {
        final int dataAvail = parcel.dataAvail();
        if (0 < dataAvail) {
            String sb = "Parcel data not fully consumed, unread size: " +
                    dataAvail;
            throw new BadParcelableException(sb);
        }
    }
}
