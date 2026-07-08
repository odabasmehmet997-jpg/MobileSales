package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    @SafeParcelable.Field
    private final String zza;
    @SafeParcelable.Field
    private final zzj zzb;
    @SafeParcelable.Field
    private final boolean zzc;
    @SafeParcelable.Field
    private final boolean zzd;

    @SafeParcelable.Constructor
    zzs(@SafeParcelable.Param final String str, @SafeParcelable.Param final IBinder iBinder, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2) {
        final byte[] bArr;
        zza = str;
        zzk zzk = null;
        if (null != iBinder) {
            try {
                final IObjectWrapper zzd2 = zzz.zzg(iBinder).zzd();
                if (null == zzd2) {
                    bArr = null;
                } else {
                    bArr = ObjectWrapper.unwrap(zzd2);
                }
                if (null != bArr) {
                    zzk = new zzk(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (final RemoteException e2) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e2);
            }
        }
        zzb = zzk;
        zzc = z;
        zzd = z2;
    }

    zzs(final String str, final zzj zzj, final boolean z, final boolean z2) {
        zza = str;
        zzb = zzj;
        zzc = z;
        zzd = z2;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final String str = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        zzj zzj = zzb;
        if (null == zzj) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            zzj = null;
        }
        SafeParcelWriter.writeIBinder(parcel, 2, zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 3, zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
