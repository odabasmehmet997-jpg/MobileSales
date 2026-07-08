package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.R;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public enum zzfl {
    ;

    public static String zza(final Context context) {
        try {
            return context.getResources().getResourcePackageName(R.string.common_google_play_services_unknown_issue);
        } catch (final Resources.NotFoundException unused) {
            return context.getPackageName();
        }
    }

    @Nullable
    public static String zzb(final String str, final Resources resources, final String str2) {
        final int identifier = resources.getIdentifier(str, TypedValues.Custom.S_STRING, str2);
        if (0 == identifier) {
            return null;
        }
        try {
            return resources.getString(identifier);
        } catch (final Resources.NotFoundException unused) {
            return null;
        }
    }
}
