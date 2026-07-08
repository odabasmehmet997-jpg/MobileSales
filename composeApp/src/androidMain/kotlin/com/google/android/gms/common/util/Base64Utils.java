package com.google.android.gms.common.util;

import android.util.Base64;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum Base64Utils {
    ;

    @NonNull
    @KeepForSdk
    public static String encode(@NonNull final byte[] bArr) {
        if (null == bArr) {
            return null;
        }
        return Base64.encodeToString(bArr, 0);
    }

    @NonNull
    @KeepForSdk
    public static String encodeUrlSafe(@NonNull final byte[] bArr) {
        if (null == bArr) {
            return null;
        }
        return Base64.encodeToString(bArr, 10);
    }

    @NonNull
    @KeepForSdk
    public static String encodeUrlSafeNoPadding(@NonNull final byte[] bArr) {
        if (null == bArr) {
            return null;
        }
        return Base64.encodeToString(bArr, 11);
    }
}
