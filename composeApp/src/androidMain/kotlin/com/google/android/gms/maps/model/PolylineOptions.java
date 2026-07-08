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
public final class PolylineOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzu();
    @SafeParcelable.Field
    private final List zza;
    @SafeParcelable.Field
    private float zzb;
    @SafeParcelable.Field
    private int zzc;
    @SafeParcelable.Field
    private float zzd;
    @SafeParcelable.Field
    private boolean zze;
    @SafeParcelable.Field
    private boolean zzf;
    @SafeParcelable.Field
    private boolean zzg;
    @SafeParcelable.Field
    private Cap zzh;
    @SafeParcelable.Field
    private Cap zzi;
    @SafeParcelable.Field
    private int zzj;
    @SafeParcelable.Field
    @Nullable
    private List zzk;
    @SafeParcelable.Field
    private List zzl;

    public PolylineOptions() {
        zzb = 10.0f;
        zzc = ViewCompat.MEASURED_STATE_MASK;
        zzd = 0.0f;
        zze = true;
        zzf = false;
        zzg = false;
        zzh = new ButtCap();
        zzi = new ButtCap();
        zzj = 0;
        zzk = null;
        zzl = new ArrayList();
        zza = new ArrayList();
    }

    public int getColor() {
        return zzc;
    }

    @NonNull
    public Cap getEndCap() {
        return zzi.zza();
    }

    public int getJointType() {
        return zzj;
    }

    @Nullable
    public List<PatternItem> getPattern() {
        return zzk;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return zza;
    }

    @NonNull
    public Cap getStartCap() {
        return zzh.zza();
    }

    public float getWidth() {
        return zzb;
    }

    public float getZIndex() {
        return zzd;
    }

    public boolean isClickable() {
        return zzg;
    }

    public boolean isGeodesic() {
        return zzf;
    }

    public boolean isVisible() {
        return zze;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeFloat(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzg);
        SafeParcelWriter.writeParcelable(parcel, 9, this.getStartCap(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.getEndCap(), i2, false);
        SafeParcelWriter.writeInt(parcel, 11, this.zzj);
        SafeParcelWriter.writeTypedList(parcel, 12, this.getPattern(), false);
        final ArrayList arrayList = new ArrayList(zzl.size());
        for (final StyleSpan styleSpan : zzl) {
            final StrokeStyle.Builder builder = new StrokeStyle.Builder(styleSpan.getStyle());
            builder.zzd(zzb);
            builder.zzc(zze);
            arrayList.add(new StyleSpan(builder.build(), styleSpan.getSegments()));
        }
        SafeParcelWriter.writeTypedList(parcel, 13, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    PolylineOptions(@SafeParcelable.Param final List list, @SafeParcelable.Param final float f2, @SafeParcelable.Param final int i2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param @Nullable final Cap cap, @SafeParcelable.Param @Nullable final Cap cap2, @SafeParcelable.Param final int i3, @SafeParcelable.Param @Nullable final List list2, @SafeParcelable.Param @Nullable final List list3) {
        zzb = 10.0f;
        zzc = ViewCompat.MEASURED_STATE_MASK;
        zzd = 0.0f;
        zze = true;
        zzf = false;
        zzg = false;
        zzh = new ButtCap();
        zzi = new ButtCap();
        zzj = 0;
        zzk = null;
        zzl = new ArrayList();
        zza = list;
        zzb = f2;
        zzc = i2;
        zzd = f3;
        zze = z;
        zzf = z2;
        zzg = z3;
        if (null != cap) {
            zzh = cap;
        }
        if (null != cap2) {
            zzi = cap2;
        }
        zzj = i3;
        zzk = list2;
        if (null != list3) {
            zzl = list3;
        }
    }
}
