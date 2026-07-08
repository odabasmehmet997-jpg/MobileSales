package com.google.android.gms.common.util;

import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.regex.Pattern;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum Strings {
    ;
    private static final Pattern zza = Pattern.compile("\\\\{(.*?)\\}");

    @KeepForSdk
    public static boolean isEmptyOrWhitespace(@Nullable final String str) {
        return null == str || str.trim().isEmpty();
    }
}
