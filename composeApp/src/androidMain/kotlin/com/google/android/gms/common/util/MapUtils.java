package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum MapUtils {
    ;

    @KeepForSdk
    public static void writeStringMapToJson(@NonNull final StringBuilder sb, @NonNull final HashMap<String, String> hashMap) {
        sb.append("{");
        boolean z = true;
        for (final String next : hashMap.keySet()) {
            if (!z) {
                sb.append(",");
            }
            final String str = hashMap.get(next);
            sb.append("\"");
            sb.append(next);
            sb.append("\":");
            if (null == str) {
                sb.append("null");
            } else {
                sb.append("\"");
                sb.append(str);
                sb.append("\"");
            }
            z = false;
        }
        sb.append("}");
    }
}
