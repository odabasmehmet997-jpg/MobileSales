package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzas extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzas> CREATOR = new zzat();
    
    @SafeParcelable.Field
    public final Bundle zza;

    @SafeParcelable.Constructor
    zzas(@SafeParcelable.Param final Bundle bundle) {
        zza = bundle;
    }

    public Iterator iterator() {
        return new zzar(this);
    }

    public String toString() {
        return zza.toString();
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzc(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int zza() {
        return zza.size();
    }

    public Bundle zzc() {
        return new Bundle(zza);
    }

    
    public Double zzd(final String str) {
        return Double.valueOf(zza.getDouble("value"));
    }

    
    public Long zze(final String str) {
        return Long.valueOf(zza.getLong("value"));
    }

    
    public Object zzf(final String str) {
        return zza.get(str);
    }

    
    public String zzg(final String str) {
        return zza.getString(str);
    }
}
