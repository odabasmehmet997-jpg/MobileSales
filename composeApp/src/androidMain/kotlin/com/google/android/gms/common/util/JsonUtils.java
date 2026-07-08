package com.google.android.gms.common.util;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum JsonUtils {
    ;
    private static final Pattern zza = Pattern.compile("\\\\.");
    private static final Pattern zzb = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    @KeepForSdk
    @Nullable
    public static String escapeString(@Nullable final String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        final Matcher matcher = JsonUtils.zzb.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (null == stringBuffer) {
                stringBuffer = new StringBuffer();
            }
            final char charAt = matcher.group().charAt(0);
            if (12 == charAt) {
                matcher.appendReplacement(stringBuffer, "\\\\f");
            } else if (13 == charAt) {
                matcher.appendReplacement(stringBuffer, "\\\\r");
            } else if ('\"' == charAt) {
                matcher.appendReplacement(stringBuffer, "\\\\\\\"");
            } else if ('/' == charAt) {
                matcher.appendReplacement(stringBuffer, "\\\\/");
            } else if ('\\' != charAt) {
                switch (charAt) {
                    case 8:
                        matcher.appendReplacement(stringBuffer, "\\\\b");
                        break;
                    case 9:
                        matcher.appendReplacement(stringBuffer, "\\\\t");
                        break;
                    case 10:
                        matcher.appendReplacement(stringBuffer, "\\\\n");
                        break;
                }
            } else {
                matcher.appendReplacement(stringBuffer, "\\\\\\\\");
            }
        }
        if (null == stringBuffer) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
