package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaw;
import com.google.android.gms.internal.maps.zzax;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzak();
    
    @SafeParcelable.Field
    @Nullable
    public zzax zza;
    @Nullable
    private TileProvider zzb;
    @SafeParcelable.Field
    private boolean zzc = true;
    @SafeParcelable.Field
    private float zzd;
    @SafeParcelable.Field
    private boolean zze = true;
    @SafeParcelable.Field
    private float zzf;

    public TileOverlayOptions() {
    }

    public boolean getFadeIn() {
        return zze;
    }

    public float getTransparency() {
        return zzf;
    }

    public float getZIndex() {
        return zzd;
    }

    public boolean isVisible() {
        return zzc;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final IBinder iBinder;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        final zzax zzax = zza;
        if (null == zzax) {
            iBinder = null;
        } else {
            iBinder = zzax.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, iBinder, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    TileOverlayOptions(@SafeParcelable.Param final IBinder iBinder, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final float f2, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final float f3) {
        final zzai zzai;
        final zzax zzc2 = zzaw.zzc(iBinder);
        zza = zzc2;
        if (null == zzc2) {
            zzai = null;
        } else {
            zzai = new zzai(this);
        }
        zzb = zzai;
        zzc = z;
        zzd = f2;
        zze = z2;
        zzf = f3;
    }
}
