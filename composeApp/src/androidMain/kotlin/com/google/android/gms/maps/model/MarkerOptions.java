package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class MarkerOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzp();
    @SafeParcelable.Field
    private LatLng zza;
    @SafeParcelable.Field
    @Nullable
    private String zzb;
    @SafeParcelable.Field
    @Nullable
    private String zzc;
    @SafeParcelable.Field
    @Nullable
    private BitmapDescriptor zzd;
    @SafeParcelable.Field
    private float zze = 0.5f;
    @SafeParcelable.Field
    private float zzf = 1.0f;
    @SafeParcelable.Field
    private boolean zzg;
    @SafeParcelable.Field
    private boolean zzh = true;
    @SafeParcelable.Field
    private boolean zzi;
    @SafeParcelable.Field
    private float zzj;
    @SafeParcelable.Field
    private float zzk = 0.5f;
    @SafeParcelable.Field
    private float zzl;
    @SafeParcelable.Field
    private float zzm = 1.0f;
    @SafeParcelable.Field
    private float zzn;
    @SafeParcelable.Field
    @AdvancedMarkerOptions.CollisionBehavior
    private int zzo;
    @SafeParcelable.Field
    @Nullable
    private View zzp;
    @SafeParcelable.Field
    private int zzq;
    @SafeParcelable.Field
    @Nullable
    private String zzr;
    @SafeParcelable.Field
    private float zzs;

    public MarkerOptions() {
    }

    @NonNull
    public MarkerOptions draggable(final boolean z) {
        zzg = z;
        return this;
    }

    public float getAlpha() {
        return zzm;
    }

    public float getAnchorU() {
        return zze;
    }

    public float getAnchorV() {
        return zzf;
    }

    public float getInfoWindowAnchorU() {
        return zzk;
    }

    public float getInfoWindowAnchorV() {
        return zzl;
    }

    @NonNull
    public LatLng getPosition() {
        return zza;
    }

    public float getRotation() {
        return zzj;
    }

    @Nullable
    public String getSnippet() {
        return zzc;
    }

    @Nullable
    public String getTitle() {
        return zzb;
    }

    public float getZIndex() {
        return zzn;
    }

    public boolean isDraggable() {
        return zzg;
    }

    public boolean isFlat() {
        return zzi;
    }

    public boolean isVisible() {
        return zzh;
    }

    @NonNull
    public MarkerOptions position(@NonNull final LatLng latLng) {
        if (null != latLng) {
            zza = latLng;
            return this;
        }
        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    @NonNull
    public MarkerOptions snippet(@Nullable final String str) {
        zzc = str;
        return this;
    }

    @NonNull
    public MarkerOptions title(@Nullable final String str) {
        zzb = str;
        return this;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final IBinder iBinder;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i2, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        final BitmapDescriptor bitmapDescriptor = zzd;
        if (null == bitmapDescriptor) {
            iBinder = null;
        } else {
            iBinder = bitmapDescriptor.zza().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, iBinder, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zze);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzg);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzj);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzk);
        SafeParcelWriter.writeFloat(parcel, 13, this.zzl);
        SafeParcelWriter.writeFloat(parcel, 14, this.zzm);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzn);
        SafeParcelWriter.writeInt(parcel, 17, zzo);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap(zzp).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 19, zzq);
        SafeParcelWriter.writeString(parcel, 20, zzr, false);
        SafeParcelWriter.writeFloat(parcel, 21, zzs);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zzb() {
        return zzq;
    }

    @NonNull
    public final MarkerOptions zzf(final int i2) {
        zzq = 1;
        return this;
    }

    @SafeParcelable.Constructor
    MarkerOptions(@SafeParcelable.Param final LatLng latLng, @SafeParcelable.Param final String str, @SafeParcelable.Param final String str2, @SafeParcelable.Param @Nullable final IBinder iBinder, @SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param final float f4, @SafeParcelable.Param final float f5, @SafeParcelable.Param final float f6, @SafeParcelable.Param final float f7, @SafeParcelable.Param final float f8, @SafeParcelable.Param final int i2, @SafeParcelable.Param final IBinder iBinder2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final String str3, @SafeParcelable.Param final float f9) {
        zza = latLng;
        zzb = str;
        zzc = str2;
        final View view = null;
        if (null == iBinder) {
            zzd = null;
        } else {
            zzd = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        }
        zze = f2;
        zzf = f3;
        zzg = z;
        zzh = z2;
        zzi = z3;
        zzj = f4;
        zzk = f5;
        zzl = f6;
        zzm = f7;
        zzn = f8;
        zzq = i3;
        zzo = i2;
        final IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(iBinder2);
        zzp = null != asInterface ? (View) ObjectWrapper.unwrap(asInterface) : view;
        zzr = str3;
        zzs = f9;
    }
}
