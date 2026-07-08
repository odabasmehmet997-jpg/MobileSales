package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public enum zzcb {
    ;

    @Nullable
    public static Parcelable zza(@Nullable Bundle bundle, String str) {
        ClassLoader zzd = zzd();
        bundle.setClassLoader(zzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (null == bundle2) {
            return null;
        }
        bundle2.setClassLoader(zzd);
        return bundle2.getParcelable(str);
    }

    public static void zzb(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (null != bundle && null != bundle2) {
            Parcelable zza = zza(bundle, "MapOptions");
            if (null != zza) {
                zzc(bundle2, "MapOptions", zza);
            }
            Parcelable zza2 = zza(bundle, "StreetViewPanoramaOptions");
            if (null != zza2) {
                zzc(bundle2, "StreetViewPanoramaOptions", zza2);
            }
            Parcelable zza3 = zza(bundle, "camera");
            if (null != zza3) {
                zzc(bundle2, "camera", zza3);
            }
            if (bundle.containsKey(PrivacyPolicyState.POSITION)) {
                bundle2.putString(PrivacyPolicyState.POSITION, bundle.getString(PrivacyPolicyState.POSITION));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    public static void zzc(Bundle bundle, String str, @Nullable Parcelable parcelable) {
        ClassLoader zzd = zzd();
        bundle.setClassLoader(zzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (null == bundle2) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(zzd);
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    private static ClassLoader zzd() {
        return Preconditions.checkNotNull(zzcb.class.getClassLoader());
    }
}
