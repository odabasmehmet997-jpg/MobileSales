package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzau extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzau> CREATOR = new zzav();
    @SafeParcelable.Field
    public final String zza;
    @SafeParcelable.Field
    public final zzas zzb;
    @SafeParcelable.Field
    public final String zzc;
    @SafeParcelable.Field
    public final long zzd;

    zzau(final zzau zzau, final long j2) {
        Preconditions.checkNotNull(zzau);
        zza = zzau.zza;
        zzb = zzau.zzb;
        zzc = zzau.zzc;
        zzd = j2;
    }

    public String toString() {
        final String str = zzc;
        final String str2 = zza;
        final String valueOf = String.valueOf(zzb);
        final int length = String.valueOf(str).length();
        String sb = "origin=" +
                str +
                ",name=" +
                str2 +
                ",params=" +
                valueOf;
        return sb;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        zzav.zza(this, parcel, i2);
    }

    @SafeParcelable.Constructor
    public zzau(@SafeParcelable.Param final String str, @SafeParcelable.Param final zzas zzas, @SafeParcelable.Param final String str2, @SafeParcelable.Param final long j2) {
        zza = str;
        zzb = zzas;
        zzc = str2;
        zzd = j2;
    }
}
