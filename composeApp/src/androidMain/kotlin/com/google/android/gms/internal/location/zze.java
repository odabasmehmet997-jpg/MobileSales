package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.util.Arrays;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    public static final zzd zza = new zzd(null);
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final String zzc;
    @SafeParcelable.Field
    private final String zzd;
    @SafeParcelable.Field
    private final String zze;
    @SafeParcelable.Field
    private final List zzf;
    @SafeParcelable.Field
    private final zze zzg;

    static {
        Process.myUid();
        Process.myPid();
    }

    @SafeParcelable.Constructor
    public zze(@SafeParcelable.Param final int i2, @SafeParcelable.Param final String str, @SafeParcelable.Param final String str2, @SafeParcelable.Param final String str3, @SafeParcelable.Param List list, @SafeParcelable.Param final zze zze2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        if (null == zze2 || !zze2.zza()) {
            zzb = i2;
            zzc = str;
            zzd = str2;
            final List list2 = null;
            zze = null == str3 ? null != zze2 ? zze2.zze : null : str3;
            if (null == list) {
                list = null != zze2 ? zze2.zzf : list2;
                if (null == list) {
                    list = zzex.zzi();
                    checkNotNullExpressionValue(list, "of(...)");
                }
            }
            Intrinsics.checkNotNullParameter(list, "<this>");
            final zzex zzj = zzex.zzj(list);
            checkNotNullExpressionValue(zzj, "copyOf(...)");
            zzf = zzj;
            zzg = zze2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public boolean equals(final Object obj) {
        if (obj instanceof zze zze2) {
            return zzb == zze2.zzb && areEqual(zzc, zze2.zzc) && areEqual(zzd, zze2.zzd) && areEqual(zze, zze2.zze) && areEqual(zzg, zze2.zzg) && areEqual(zzf, zze2.zzf);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzb), zzc, zzd, zze, zzg});
    }

    public String toString() {
        final int length = zzc.length() + 18;
        final String str = zzd;
        int i2 = 0;
        final StringBuilder sb = new StringBuilder(length + (null != str ? str.length() : 0));
        sb.append(zzb);
        sb.append("/");
        sb.append(zzc);
        final String str2 = zzd;
        if (null != str2) {
            sb.append("[");
            if (StringsKt.startsWithdefault(str2, zzc, false, 2, (Object) null)) {
                sb.append(str2, zzc.length(), str2.length());
            } else {
                sb.append(str2);
            }
            sb.append("]");
        }
        if (null != this.zze) {
            sb.append("/");
            final String str3 = zze;
            if (null != str3) {
                i2 = str3.hashCode();
            }
            sb.append(Integer.toHexString(i2));
        }
        final String sb2 = sb.toString();
        checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        final int i3 = zzb;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeString(parcel, 3, zzc, false);
        SafeParcelWriter.writeString(parcel, 4, zzd, false);
        SafeParcelWriter.writeString(parcel, 6, zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, zzg, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 8, zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean zza() {
        return null != this.zzg;
    }
}
