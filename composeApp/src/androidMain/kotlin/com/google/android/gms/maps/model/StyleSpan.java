package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class StyleSpan extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StyleSpan> CREATOR = new zzae();
    @SafeParcelable.Field
    private final StrokeStyle zza;
    @SafeParcelable.Field
    private final double zzb;

    public double getSegments() {
        return zzb;
    }

    @NonNull
    public StrokeStyle getStyle() {
        return zza;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i2, false);
        SafeParcelWriter.writeDouble(parcel, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public StyleSpan(@SafeParcelable.Param @NonNull final StrokeStyle strokeStyle, @SafeParcelable.Param final double d2) {
        if (0.0d < d2) {
            zza = strokeStyle;
            zzb = d2;
            return;
        }
        throw new IllegalArgumentException("A style must be applied to some segments on a polyline.");
    }
}
