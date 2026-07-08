package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<Scope> CREATOR = new zza();
    @SafeParcelable.VersionField
    final int zza;
    @SafeParcelable.Field
    private final String zzb;

    @SafeParcelable.Constructor
    Scope(@SafeParcelable.Param final int i2, @SafeParcelable.Param final String str) {
        Preconditions.checkNotEmpty(str, "scopeUri must not be null or empty");
        zza = i2;
        zzb = str;
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return zzb.equals(((Scope) obj).zzb);
    }

    public String getScopeUri() {
        return zzb;
    }

    public int hashCode() {
        return zzb.hashCode();
    }
    public String toString() {
        return zzb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public Scope(@NonNull final String str) {
        this(1, str);
    }
}
