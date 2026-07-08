package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzha implements zzhe {
    public static final String[] zza = {"key", "value"};
    @GuardedBy("ConfigurationContentLoader.class")
    private static final Map zzb = new ArrayMap();
    private final ContentResolver zzc;
    private final Uri zzd;
    private final ContentObserver zze;
    private final Object zzf = new Object();
    private volatile Map zzg;
    @GuardedBy("this")
    private final List zzh = new ArrayList();

    private zzha(ContentResolver contentResolver, Uri uri) {
        zzgz zzgz = new zzgz(this, null);
        this.zze = zzgz;
        contentResolver.getClass();
        uri.getClass();
        this.zzc = contentResolver;
        this.zzd = uri;
        contentResolver.registerContentObserver(uri, false, zzgz);
    }

    public static com.google.android.gms.internal.measurement.zzha zza(android.content.ContentResolver r4, android.net.Uri r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.measurement.zzha");
    }

    static synchronized void zze() {
        synchronized (zzha.class) {
            try {
                for (zzha zzha : zzb.values()) {
                    zzha.zzc.unregisterContentObserver(zzha.zze);
                }
                zzb.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public Object zzb(String str) {
        return zzc().get(str);
    }

    public Map zzc() {
        Map map;
        Map map2 = this.zzg;
        if (null == map2) {
            synchronized (this.zzf) {
                map2 = this.zzg;
                if (null == map2) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = (Map) zzhc.zza(new zzgy(this));
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                        try {
                            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            map = null;
                        } catch (Throwable th) {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            throw th;
                        }
                    }
                    this.zzg = map;
                    map2 = map;
                }
            }
        }
        if (null != map2) {
            return map2;
        }
        return Collections.emptyMap();
    }

    
    public Map zzd() {
        Map map;
        Cursor query = this.zzc.query(this.zzd, zza, null, null, null);
        if (null == query) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (0 == count) {
                return Collections.emptyMap();
            }
            if (256 >= count) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }

    public void zzf() {
        synchronized (this.zzf) {
            this.zzg = null;
            zzhu.zzd();
        }
        synchronized (this) {
            try {
                for (zzhb zza2 : this.zzh) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
