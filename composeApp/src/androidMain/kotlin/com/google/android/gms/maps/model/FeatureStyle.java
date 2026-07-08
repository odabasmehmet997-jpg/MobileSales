package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class FeatureStyle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<FeatureStyle> CREATOR = new zzi();
    @SafeParcelable.Field
    @Nullable
    private final Integer zza;
    @SafeParcelable.Field
    @Nullable
    private final Integer zzb;
    @SafeParcelable.Field
    @Nullable
    private final Float zzc;
    @SafeParcelable.Field
    @Nullable
    private final Float zzd;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
    }

    @Nullable
    public Integer getFillColor() {
        return zza;
    }

    @Nullable
    public Float getPointRadius() {
        return zzd;
    }

    @Nullable
    public Integer getStrokeColor() {
        return zzb;
    }

    @Nullable
    public Float getStrokeWidth() {
        return zzc;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 1, this.zza, false);
        SafeParcelWriter.writeIntegerObject(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    FeatureStyle(@SafeParcelable.Param @Nullable final Integer num, @SafeParcelable.Param @Nullable final Integer num2, @SafeParcelable.Param @Nullable final Float f2, @SafeParcelable.Param @Nullable final Float f3) {
        zza = num;
        zzb = num2;
        zzc = f2;
        zzd = f3;
    }
}
