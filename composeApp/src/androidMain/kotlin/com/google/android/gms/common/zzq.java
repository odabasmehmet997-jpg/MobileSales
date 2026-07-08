package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    @SafeParcelable.Field
    private final boolean zza;
    @SafeParcelable.Field
    private final String zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final int zzd;

    @SafeParcelable.Constructor
    zzq(@SafeParcelable.Param final boolean z, @SafeParcelable.Param final String str, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3) {
        zza = z;
        zzb = str;
        zzc = zzy.zza(i2) - 1;
        zzd = this.zzd.zza(i3) - 1;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, zza);
        SafeParcelWriter.writeString(parcel, 2, zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, zzc);
        SafeParcelWriter.writeInt(parcel, 4, zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String zza() {
        return zzb;
    }

    public boolean zzb() {
        return zza;
    }

    public int zzc() {
        return this.zzd.zza(zzd);
    }

    public int zzd() {
        return zzy.zza(zzc);
    }
}
