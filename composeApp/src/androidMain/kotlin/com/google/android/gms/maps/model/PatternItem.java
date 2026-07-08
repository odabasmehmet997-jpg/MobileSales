package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzq();
    private final int zzb;
    private final Float zzc;
    public PatternItem(@SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final Float f2) {
        boolean z = 1 == i2 || (null != f2 && !(0.0f > f2.floatValue()));
        Preconditions.checkArgument(z, "Invalid PatternItem: type=" + i2 + " length=" + f2);
        zzb = i2;
        zzc = f2;
    }
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem patternItem)) {
            return false;
        }
        return zzb == patternItem.zzb && Objects.equal(zzc, patternItem.zzc);
    }
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), zzc);
    }
    public String toString() {
        return "[PatternItem: type=" + zzb + " length=" + zzc + "]";
    }
    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = zzb;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i3);
        SafeParcelWriter.writeFloatObject(parcel, 3, zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
