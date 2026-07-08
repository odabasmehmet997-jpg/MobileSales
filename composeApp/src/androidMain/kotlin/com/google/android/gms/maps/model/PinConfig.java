package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Objects;

public class PinConfig extends AbstractSafeParcelable {

    public static final Parcelable.Creator<PinConfig> CREATOR = new zzr();
    private final int zza;
    private final int zzb;
    private final Glyph zzc;
    public static class Builder {
        private final int zza = -1424587;
        private final int zzb = -3857889;
        private final Glyph zzc = new Glyph(-5041134);
    }
    PinConfig(@SafeParcelable.Param @ColorInt final int i2, @SafeParcelable.Param @ColorInt final int i3, @SafeParcelable.Param final Glyph glyph) {
        zza = i2;
        zzb = i3;
        zzc = glyph;
    }
    public int getBackgroundColor() {
        return zza;
    }
    public int getBorderColor() {
        return zzb;
    }
    public Glyph getGlyph() {
        return zzc;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeInt(parcel, 3, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    public static class Glyph extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<Glyph> CREATOR = new zzj();
        @SafeParcelable.Field
        @Nullable
        private String zza;
        @SafeParcelable.Field
        @Nullable
        private BitmapDescriptor zzb;
        @SafeParcelable.Field
        private int zzc;
        @SafeParcelable.Field
        @ColorInt
        private int zzd;

        public Glyph(@ColorInt final int i2) {
            zzd = ViewCompat.MEASURED_STATE_MASK;
            zzc = i2;
        }

        public boolean equals(@Nullable final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Glyph glyph)) {
                return false;
            }
            if (zzc != glyph.zzc || !Objects.equals(zza, glyph.zza) || zzd != glyph.zzd) {
                return false;
            }
            final BitmapDescriptor bitmapDescriptor = zzb;
            if ((null == bitmapDescriptor && null != glyph.zzb) || (null != bitmapDescriptor && null == glyph.zzb)) {
                return false;
            }
            final BitmapDescriptor bitmapDescriptor2 = glyph.zzb;
            if (null == bitmapDescriptor || null == bitmapDescriptor2) {
                return true;
            }
            return Objects.equals(ObjectWrapper.unwrap(bitmapDescriptor.zza()), ObjectWrapper.unwrap(bitmapDescriptor2.zza()));
        }

        public int getGlyphColor() {
            return zzc;
        }

        @Nullable
        public String getText() {
            return zza;
        }

        public int getTextColor() {
            return zzd;
        }

        public int hashCode() {
            return Objects.hash(zza, zzb, Integer.valueOf(zzc));
        }

        public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
            final IBinder iBinder;
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.zza, false);
            final BitmapDescriptor bitmapDescriptor = zzb;
            if (null == bitmapDescriptor) {
                iBinder = null;
            } else {
                iBinder = bitmapDescriptor.zza().asBinder();
            }
            SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
            SafeParcelWriter.writeInt(parcel, 4, this.zzc);
            SafeParcelWriter.writeInt(parcel, 5, this.zzd);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        @SafeParcelable.Constructor
        Glyph(@SafeParcelable.Param final String str, @SafeParcelable.Param final IBinder iBinder, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3) {
            final BitmapDescriptor bitmapDescriptor;
            zzc = -5041134;
            zzd = ViewCompat.MEASURED_STATE_MASK;
            zza = str;
            if (null == iBinder) {
                bitmapDescriptor = null;
            } else {
                bitmapDescriptor = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
            }
            zzb = bitmapDescriptor;
            zzc = i2;
            zzd = i3;
        }
    }
}
