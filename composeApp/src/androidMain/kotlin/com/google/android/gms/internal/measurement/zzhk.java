package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public enum zzhk {
    ;
    @GuardedBy("PhenotypeConstants.class")
    private static final ArrayMap zza = new ArrayMap();

    public static synchronized Uri zza(String str) {
        Uri uri;
        synchronized (zzhk.class) {
            try {
                ArrayMap arrayMap = zza;
                uri = (Uri) arrayMap.get("com.google.android.gms.measurement");
                if (null == uri) {
                    String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
                    uri = Uri.parse(0 != valueOf.length() ? "content://com.google.android.gms.phenotype/" + valueOf : "content://com.google.android.gms.phenotype/");
                    arrayMap.put("com.google.android.gms.measurement", uri);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return uri;
    }
}
