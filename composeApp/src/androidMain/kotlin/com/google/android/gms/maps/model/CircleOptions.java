package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class CircleOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
    @SafeParcelable.Field
    @Nullable
    private final LatLng zza;
    @SafeParcelable.Field
    private final double zzb;
    @SafeParcelable.Field
    private final float zzc;
    @SafeParcelable.Field
    private final int zzd;
    @SafeParcelable.Field
    private final int zze;
    @SafeParcelable.Field
    private final float zzf;
    @SafeParcelable.Field
    private final boolean zzg;
    @SafeParcelable.Field
    private final boolean zzh;
    @SafeParcelable.Field
    @Nullable
    private final List zzi;

    public CircleOptions() {
        zza = null;
        zzb = 0.0d;
        zzc = 10.0f;
        zzd = ViewCompat.MEASURED_STATE_MASK;
        zze = 0;
        zzf = 0.0f;
        zzg = true;
        zzh = false;
        zzi = null;
    }

    @Nullable
    public LatLng getCenter() {
        return zza;
    }

    public int getFillColor() {
        return zze;
    }

    public double getRadius() {
        return zzb;
    }

    public int getStrokeColor() {
        return zzd;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return zzi;
    }

    public float getStrokeWidth() {
        return zzc;
    }

    public float getZIndex() {
        return zzf;
    }

    public boolean isClickable() {
        return zzh;
    }

    public boolean isVisible() {
        return zzg;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i2, false);
        SafeParcelWriter.writeDouble(parcel, 3, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzc);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.writeInt(parcel, 6, this.zze);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzg);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeTypedList(parcel, 10, this.getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    CircleOptions(@SafeParcelable.Param final LatLng latLng, @SafeParcelable.Param final double d2, @SafeParcelable.Param final float f2, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final float f3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param @Nullable final List list) {
        zza = latLng;
        zzb = d2;
        zzc = f2;
        zzd = i2;
        zze = i3;
        zzf = f3;
        zzg = z;
        zzh = z2;
        zzi = list;
    }
}
