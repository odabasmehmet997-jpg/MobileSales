package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@17.1.0 */
public enum zzc {
    ;
    private static final ClassLoader zza = zzc.class.getClassLoader();

    public static void zza(final Parcel parcel, final boolean z) {
        parcel.writeInt(1);
    }

    public static boolean zzb(final Parcel parcel) {
        return 0 != parcel.readInt();
    }
}
