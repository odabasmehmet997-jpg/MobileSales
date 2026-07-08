package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Feature extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Feature> CREATOR = new zzc();
    @SafeParcelable.Field
    private final String zza;
    @SafeParcelable.Field
    @Deprecated
    private final int zzb;
    @SafeParcelable.Field
    private final long zzc;

    @SafeParcelable.Constructor
    public Feature(@SafeParcelable.Param @NonNull final String str, @SafeParcelable.Param final int i2, @SafeParcelable.Param final long j2) {
        zza = str;
        zzb = i2;
        zzc = j2;
    }

    @KeepForSdk
    public Feature(@NonNull final String str, final long j2) {
        zza = str;
        zzc = j2;
        zzb = -1;
    }

    public final boolean equals(@Nullable final Object obj) {
        if (obj instanceof Feature feature) {
            return ((null != zza && this.zza.equals(feature.zza)) || (null == zza && null == feature.zza)) && this.getVersion() == feature.getVersion();
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public String getName() {
        return zza;
    }

    @KeepForSdk
    public long getVersion() {
        final long j2 = zzc;
        return -1 == j2 ? zzb : j2;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, Long.valueOf(this.getVersion()));
    }

    @NonNull
    public final String toString() {
        final Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("name", this.zza);
        stringHelper.add("version", Long.valueOf(this.getVersion()));
        return stringHelper.toString();
    }

    public final void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeInt(parcel, 2, zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.getVersion());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
