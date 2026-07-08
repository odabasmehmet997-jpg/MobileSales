package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class StampStyle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StampStyle> CREATOR = new zzw();
    @SafeParcelable.Field
    @NonNull
    protected final BitmapDescriptor zza;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    static abstract class Builder<T extends Builder<T>> {
        Builder() {
        }
    }

    @SafeParcelable.Constructor
    StampStyle(@SafeParcelable.Param final IBinder iBinder) {
        zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
    }

    public final void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final BitmapDescriptor bitmapDescriptor = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
