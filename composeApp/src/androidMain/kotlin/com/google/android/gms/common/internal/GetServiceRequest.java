package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class GetServiceRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzn();
    static final Scope[] zza = new Scope[0];
    static final Feature[] zzb = new Feature[0];
    @SafeParcelable.VersionField
    final int zzc;
    @SafeParcelable.Field
    final int zzd;
    @SafeParcelable.Field
    final int zze;
    @SafeParcelable.Field
    String zzf;
    @SafeParcelable.Field
    @Nullable
    IBinder zzg;
    @SafeParcelable.Field
    Scope[] zzh;
    @SafeParcelable.Field
    Bundle zzi;
    @SafeParcelable.Field
    @Nullable
    Account zzj;
    @SafeParcelable.Field
    Feature[] zzk;
    @SafeParcelable.Field
    Feature[] zzl;
    @SafeParcelable.Field
    final boolean zzm;
    @SafeParcelable.Field
    final int zzn;
    @SafeParcelable.Field
    boolean zzo;
    @SafeParcelable.Field
    @Nullable
    private final String zzp;

    @SafeParcelable.Constructor
    GetServiceRequest(@SafeParcelable.Param int i2, @SafeParcelable.Param int i3, @SafeParcelable.Param int i4, @SafeParcelable.Param String str, @SafeParcelable.Param @Nullable IBinder iBinder, @SafeParcelable.Param Scope[] scopeArr, @SafeParcelable.Param Bundle bundle, @SafeParcelable.Param @Nullable Account account, @SafeParcelable.Param Feature[] featureArr, @SafeParcelable.Param Feature[] featureArr2, @SafeParcelable.Param boolean z, @SafeParcelable.Param int i5, @SafeParcelable.Param boolean z2, @SafeParcelable.Param @Nullable String str2) {
        scopeArr = null == scopeArr ? zza : scopeArr;
        bundle = null == bundle ? new Bundle() : bundle;
        featureArr = null == featureArr ? zzb : featureArr;
        featureArr2 = null == featureArr2 ? zzb : featureArr2;
        this.zzc = i2;
        this.zzd = i3;
        this.zze = i4;
        if ("com.google.android.gms".equals(str)) {
            this.zzf = "com.google.android.gms";
        } else {
            this.zzf = str;
        }
        if (2 > i2) {
            this.zzj = null != iBinder ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.zzg = iBinder;
            this.zzj = account;
        }
        this.zzh = scopeArr;
        this.zzi = bundle;
        this.zzk = featureArr;
        this.zzl = featureArr2;
        this.zzm = z;
        this.zzn = i5;
        this.zzo = z2;
        this.zzp = str2;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i2) {
        zzn.zza(this, parcel, i2);
    }

    @Nullable
    public final String zza() {
        return this.zzp;
    }
}
