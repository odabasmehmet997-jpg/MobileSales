package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzk();
    @SafeParcelable.Field
    private BitmapDescriptor zza;
    @SafeParcelable.Field
    @Nullable
    private LatLng zzb;
    @SafeParcelable.Field
    private float zzc;
    @SafeParcelable.Field
    private float zzd;
    @SafeParcelable.Field
    @Nullable
    private LatLngBounds zze;
    @SafeParcelable.Field
    private float zzf;
    @SafeParcelable.Field
    private float zzg;
    @SafeParcelable.Field
    private boolean zzh = true;
    @SafeParcelable.Field
    private float zzi;
    @SafeParcelable.Field
    private float zzj = 0.5f;
    @SafeParcelable.Field
    private float zzk = 0.5f;
    @SafeParcelable.Field
    private boolean zzl;

    public GroundOverlayOptions() {
    }

    public float getAnchorU() {
        return zzj;
    }

    public float getAnchorV() {
        return zzk;
    }

    public float getBearing() {
        return zzf;
    }

    @Nullable
    public LatLngBounds getBounds() {
        return zze;
    }

    public float getHeight() {
        return zzd;
    }

    @Nullable
    public LatLng getLocation() {
        return zzb;
    }

    public float getTransparency() {
        return zzi;
    }

    public float getWidth() {
        return zzc;
    }

    public float getZIndex() {
        return zzg;
    }

    public boolean isClickable() {
        return zzl;
    }

    public boolean isVisible() {
        return zzh;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, zza.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i2, false);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzc);
        SafeParcelWriter.writeFloat(parcel, 5, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i2, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzg);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzi);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzj);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    GroundOverlayOptions(@SafeParcelable.Param final IBinder iBinder, @SafeParcelable.Param final LatLng latLng, @SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final LatLngBounds latLngBounds, @SafeParcelable.Param final float f4, @SafeParcelable.Param final float f5, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final float f6, @SafeParcelable.Param final float f7, @SafeParcelable.Param final float f8, @SafeParcelable.Param final boolean z2) {
        zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        zzb = latLng;
        zzc = f2;
        zzd = f3;
        zze = latLngBounds;
        zzf = f4;
        zzg = f5;
        zzh = z;
        zzi = f6;
        zzj = f7;
        zzk = f8;
        zzl = z2;
    }
}
