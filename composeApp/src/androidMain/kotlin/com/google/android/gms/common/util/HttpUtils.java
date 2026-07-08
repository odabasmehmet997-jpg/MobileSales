package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.common.zzo;
import com.google.android.gms.internal.common.zzx;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum HttpUtils {
    ;
    private static final Pattern zza = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}");
    private static final Pattern zzb = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}");
    private static final Pattern zzc = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)");

    @NonNull
    @KeepForSdk
    public static Map<String, String> parse(@NonNull final URI uri, @NonNull final String str) {
        Map<String, String> emptyMap = Collections.emptyMap();
        final String rawQuery = uri.getRawQuery();
        if (null != rawQuery && 0 < rawQuery.length()) {
            emptyMap = new HashMap<>();
            final zzx zzc2 = zzx.zzc(zzo.zzb('='));
            for (final String zzf : zzx.zzc(zzo.zzb('&')).zzb().zzd(rawQuery)) {
                final List zzf2 = zzc2.zzf(zzf);
                if (zzf2.isEmpty() || 2 < zzf2.size()) {
                    throw new IllegalArgumentException("bad parameter");
                }
                emptyMap.put(HttpUtils.zza((String) zzf2.get(0), str), 2 == zzf2.size() ? HttpUtils.zza((String) zzf2.get(1), str) : null);
            }
        }
        return emptyMap;
    }

    private static String zza(final String str, String str2) {
        if (null == str2) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (final UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
