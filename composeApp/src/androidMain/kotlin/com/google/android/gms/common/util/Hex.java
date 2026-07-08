package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum Hex {
    ;
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @NonNull
    @KeepForSdk
    public static String bytesToStringLowercase(@NonNull final byte[] bArr) {
        final int length = bArr.length;
        final char[] cArr = new char[(length + length)];
        int i2 = 0;
        for (final byte b2 : bArr) {
            final char[] cArr2 = Hex.zzb;
            cArr[i2] = cArr2[(b2 & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b2 & 15];
            i2 += 2;
        }
        return new String(cArr);
    }

    @NonNull
    @KeepForSdk
    public static String bytesToStringUppercase(@NonNull final byte[] bArr, final boolean z) {
        final int length = bArr.length;
        final StringBuilder sb = new StringBuilder(length + length);
        int i2 = 0;
        while (i2 < length && (!z || i2 != length - 1 || 0 != (bArr[i2] & 255))) {
            final char[] cArr = Hex.zza;
            sb.append(cArr[(bArr[i2] & 240) >>> 4]);
            sb.append(cArr[bArr[i2] & 15]);
            i2++;
        }
        return sb.toString();
    }
}
