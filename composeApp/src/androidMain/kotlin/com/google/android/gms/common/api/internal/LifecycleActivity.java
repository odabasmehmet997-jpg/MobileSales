package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

public class LifecycleActivity {
    private final Object zza;

    public LifecycleActivity(@NonNull final Activity activity) {
        Preconditions.checkNotNull(activity, "Activity must not be null");
        zza = activity;
    }
    public final Activity zza() {
        return (Activity) zza;
    }
    public final FragmentActivity zzb() {
        return (FragmentActivity) zza;
    }

    public final boolean zzc() {
        return zza instanceof Activity;
    }

    public final boolean zzd() {
        return zza instanceof FragmentActivity;
    }
}
