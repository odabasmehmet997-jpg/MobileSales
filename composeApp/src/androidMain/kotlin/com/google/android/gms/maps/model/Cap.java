package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class Cap extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String zza = "Cap";
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    @Nullable
    private final BitmapDescriptor zzc;
    @SafeParcelable.Field
    @Nullable
    private final Float zzd;

    protected Cap(final int i2) {
        this(i2, (BitmapDescriptor) null, null);
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap cap)) {
            return false;
        }
        return zzb == cap.zzb && Objects.equal(zzc, cap.zzc) && Objects.equal(zzd, cap.zzd);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), zzc, zzd);
    }

    @NonNull
    public String toString() {
        return "[Cap: type=" + zzb + "]";
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final IBinder iBinder;
        final int i3 = zzb;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i3);
        final BitmapDescriptor bitmapDescriptor = zzc;
        if (null == bitmapDescriptor) {
            iBinder = null;
        } else {
            iBinder = bitmapDescriptor.zza().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.writeFloatObject(parcel, 4, zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    
    public final Cap zza() {
        final int i2 = zzb;
        if (0 == i2) {
            return new ButtCap();
        }
        boolean z = true;
        if (1 == i2) {
            return new SquareCap();
        }
        if (2 == i2) {
            return new RoundCap();
        }
        if (3 != i2) {
            Log.w(Cap.zza, "Unknown Cap type: " + i2);
            return this;
        }
        Preconditions.checkState(null != this.zzc, "bitmapDescriptor must not be null");
        if (null == this.zzd) {
            z = false;
        }
        Preconditions.checkState(z, "bitmapRefWidth must not be null");
        return new CustomCap(zzc, zzd.floatValue());
    }

    Cap(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param final int r2, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param @androidx.annotation.Nullable final android.os.IBinder r3, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param @androidx.annotation.Nullable final java.lang.Float r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.<init>(int, android.os.IBinder, java.lang.Float):void");
    }

    private Cap(int i2, @Nullable final BitmapDescriptor bitmapDescriptor, @Nullable final Float f2) {
        boolean z = true;
        final boolean z2 = null != f2 && 0.0f < f2.floatValue();
        if (3 == i2) {
            z = null != bitmapDescriptor && z2 && z;
            i2 = 3;
        }
        Preconditions.checkArgument(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(i2), bitmapDescriptor, f2));
        zzb = i2;
        zzc = bitmapDescriptor;
        zzd = f2;
    }

    protected Cap(@NonNull final BitmapDescriptor bitmapDescriptor, final float f2) {
        this(3, bitmapDescriptor, Float.valueOf(f2));
    }
}
