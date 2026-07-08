package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class StrokeStyle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StrokeStyle> CREATOR = new zzad();
    @SafeParcelable.Field
    private final float zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final boolean zzd;
    @SafeParcelable.Field
    @Nullable
    private final StampStyle zze;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
        private float zza;
        private final int zzb;
        private final int zzc;
        private boolean zzd;
        @Nullable
        private final StampStyle zze;

        private Builder() {
            throw null;
        }

        public Builder(@NonNull final StrokeStyle strokeStyle) {
            zza = strokeStyle.zza();
            final Pair zzb2 = strokeStyle.zzb();
            zzb = ((Integer) zzb2.first).intValue();
            zzc = ((Integer) zzb2.second).intValue();
            zzd = strokeStyle.isVisible();
            zze = strokeStyle.getStamp();
        }

        @NonNull
        public StrokeStyle build() {
            return new StrokeStyle(zza, zzb, zzc, zzd, zze);
        }

        @NonNull
        public Builder zzc(final boolean z) {
            zzd = z;
            return this;
        }

        @NonNull
        public Builder zzd(final float f2) {
            zza = f2;
            return this;
        }
    }

    @SafeParcelable.Constructor
    StrokeStyle(@SafeParcelable.Param final float f2, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param @Nullable final StampStyle stampStyle) {
        zza = f2;
        zzb = i2;
        zzc = i3;
        zzd = z;
        zze = stampStyle;
    }

    @Nullable
    public StampStyle getStamp() {
        return zze;
    }

    public boolean isVisible() {
        return zzd;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, zza);
        SafeParcelWriter.writeInt(parcel, 3, zzb);
        SafeParcelWriter.writeInt(parcel, 4, zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public float zza() {
        return zza;
    }

    @NonNull
    public Pair zzb() {
        return new Pair(Integer.valueOf(zzb), Integer.valueOf(zzc));
    }
}
