package com.google.android.gms.dynamite;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    public DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int zzb = iVersions.zzb(context, str, true);
        selectionResult.remoteVersion = zzb;
        if (0 != zzb) {
            selectionResult.selection = 1;
        } else {
            int zza = iVersions.zza(context, str);
            selectionResult.localVersion = zza;
            if (0 != zza) {
                selectionResult.selection = -1;
            }
        }
        return selectionResult;
    }
}
