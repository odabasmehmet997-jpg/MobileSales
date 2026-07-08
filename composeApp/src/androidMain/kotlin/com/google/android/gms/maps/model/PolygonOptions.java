package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class PolygonOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzt();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    private final List zzb;
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
    private final boolean zzi;
    @SafeParcelable.Field
    private final int zzj;
    @SafeParcelable.Field
    @Nullable
    private final List zzk;

    public PolygonOptions() {
        zzc = 10.0f;
        zzd = ViewCompat.MEASURED_STATE_MASK;
        zze = 0;
        zzf = 0.0f;
        zzg = true;
        zzh = false;
        zzi = false;
        zzj = 0;
        zzk = null;
        zza = new ArrayList();
        zzb = new ArrayList();
    }

    public int getFillColor() {
        return zze;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return zza;
    }

    public int getStrokeColor() {
        return zzd;
    }

    public int getStrokeJointType() {
        return zzj;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return zzk;
    }

    public float getStrokeWidth() {
        return zzc;
    }

    public float getZIndex() {
        return zzf;
    }

    public boolean isClickable() {
        return zzi;
    }

    public boolean isGeodesic() {
        return zzh;
    }

    public boolean isVisible() {
        return zzg;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, zzb, false);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzc);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.writeInt(parcel, 6, this.zze);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzg);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeInt(parcel, 11, this.zzj);
        SafeParcelWriter.writeTypedList(parcel, 12, this.getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    PolygonOptions(@SafeParcelable.Param final List list, @SafeParcelable.Param final List list2, @SafeParcelable.Param final float f2, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final float f3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param final int i4, @SafeParcelable.Param @Nullable final List list3) {
        zza = list;
        zzb = list2;
        zzc = f2;
        zzd = i2;
        zze = i3;
        zzf = f3;
        zzg = z;
        zzh = z2;
        zzi = z3;
        zzj = i4;
        zzk = list3;
    }
}
