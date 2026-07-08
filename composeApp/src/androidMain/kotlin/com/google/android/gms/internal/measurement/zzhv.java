package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzhv implements zzhe {
    @GuardedBy("SharedPreferencesLoader.class")
    private static final Map zza = new ArrayMap();
    private final SharedPreferences zzb;

    static zzhv zza(Context context, String str) {
        zzhv zzhv;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        if (!zzgw.zza()) {
            synchronized (zzhv.class) {
                try {
                    zzhv = (zzhv) zza.get(null);
                    if (null == zzhv) {
                        allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        throw null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzhv;
        }
        throw null;
    }

    static synchronized void zzc() {
        synchronized (zzhv.class) {
            Map map = zza;
            Iterator it = map.values().iterator();
            if (!it.hasNext()) {
                map.clear();
            } else {
                SharedPreferences sharedPreferences = ((zzhv) it.next()).zzb;
                throw null;
            }
        }
    }

    public Object zzb(String str) {
        throw null;
    }
}
