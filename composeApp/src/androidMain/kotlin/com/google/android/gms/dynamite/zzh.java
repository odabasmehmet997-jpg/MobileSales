package com.google.android.gms.dynamite;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzh implements DynamiteModule.VersionPolicy {
    zzh() {
    }

    public DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int i2 = 0;
        int zzb = iVersions.zzb(context, str, false);
        selectionResult.remoteVersion = zzb;
        if (0 != zzb) {
            i2 = 1;
        }
        selectionResult.selection = i2;
        return selectionResult;
    }
}
