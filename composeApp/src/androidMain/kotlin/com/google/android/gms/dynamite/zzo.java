package com.google.android.gms.dynamite;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzo implements DynamiteModule.VersionPolicy.IVersions {
    private final int zza;

    public zzo(int i2, int i3) {
        this.zza = i2;
    }

    public int zza(Context context, String str) {
        return this.zza;
    }

    public int zzb(Context context, String str, boolean z) {
        return 0;
    }
}
