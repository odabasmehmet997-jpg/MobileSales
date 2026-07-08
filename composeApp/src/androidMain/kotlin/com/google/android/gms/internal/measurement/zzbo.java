package com.google.android.gms.internal.measurement;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public enum zzbo {
    ;
    private static final ClassLoader zza = zzbo.class.getClassLoader();

    public static Parcelable zza(final Parcel parcel, final Parcelable.Creator creator) {
        if (0 == parcel.readInt()) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static HashMap zzb(final Parcel parcel) {
        return parcel.readHashMap(zzbo.zza);
    }

    public static void zzc(final Parcel parcel, final boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static void zzd(final Parcel parcel, final Parcelable parcelable) {
        if (null == parcelable) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    public static void zze(final Parcel parcel, final IInterface iInterface) {
        if (null == iInterface) {
            parcel.writeStrongBinder(null);
        } else {
            parcel.writeStrongBinder(iInterface.asBinder());
        }
    }

    public static boolean zzf(final Parcel parcel) {
        return 0 != parcel.readInt();
    }
}
