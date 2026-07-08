package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class Tile extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Tile> CREATOR = new zzah();
    @SafeParcelable.Field
    @Nullable
    public final byte[] data;
    @SafeParcelable.Field
    public final int height;
    @SafeParcelable.Field
    public final int width;

    @SafeParcelable.Constructor
    public Tile(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param @Nullable final byte[] bArr) {
        width = i2;
        height = i3;
        data = bArr;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = width;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i3);
        SafeParcelWriter.writeInt(parcel, 3, height);
        SafeParcelWriter.writeByteArray(parcel, 4, data, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
