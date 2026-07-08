package com.google.android.gms.internal.gtm;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzco implements Parcelable {
    @Deprecated
    public static final Parcelable.Creator<zzco> CREATOR = new zzcn();
    private String zza;
    private String zzb;
    private String zzc;

    @Deprecated
    public zzco() {
    }

    @Deprecated
    zzco(Parcel parcel) {
        this.zza = parcel.readString();
        this.zzb = parcel.readString();
        this.zzc = parcel.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeString(this.zzc);
    }

    public String zza() {
        return this.zza;
    }

    public String zzb() {
        return this.zzc;
    }
}
