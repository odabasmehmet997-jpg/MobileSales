package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.location.zzbb;
import com.google.android.gms.internal.location.zzbi;
import com.google.android.gms.internal.location.zzcr;
import com.google.android.gms.internal.location.zzcz;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum LocationServices {
    ;
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = zzbi.zzb;
    @NonNull
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new zzbb();
    @NonNull
    @Deprecated
    public static final GeofencingApi GeofencingApi = new zzcr();
    @NonNull
    @Deprecated
    public static final SettingsApi SettingsApi = new zzcz();

    @NonNull
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull final Activity activity) {
        return new zzbi(activity);
    }

    @NonNull
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull final Context context) {
        return new zzbi(context);
    }
}
