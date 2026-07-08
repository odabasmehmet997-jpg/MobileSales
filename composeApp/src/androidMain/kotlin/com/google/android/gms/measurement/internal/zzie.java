package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public enum zzie {
    ;

    public static java.lang.Object zza(final java.lang.Object r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzie.zza(java.lang.Object):java.lang.Object");
    }

    public static String zzb(final String str, final String[] strArr, final String[] strArr2) {
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        final int min = Math.min(strArr.length, strArr2.length);
        for (int i2 = 0; i2 < min; i2++) {
            final String str2 = strArr[i2];
            if ((null == str && null == str2) || (null != str && str.equals(str2))) {
                return strArr2[i2];
            }
        }
        return null;
    }

    public static String zzc(final Context context, final String str, String str2) {
        Preconditions.checkNotNull(context);
        final Resources resources = context.getResources();
        if (TextUtils.isEmpty(str2)) {
            str2 = zzfl.zza(context);
        }
        return zzfl.zzb("google_app_id", resources, str2);
    }
}
