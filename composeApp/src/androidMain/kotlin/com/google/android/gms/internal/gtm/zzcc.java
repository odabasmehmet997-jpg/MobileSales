package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class zzcc extends zzfp {
    final zzcd zza;

    zzcc(final zzcd zzcd, final Context context, final String str) {
        super(context, "google_analytics_v4.db", null, 1);
        zza = zzcd;
    }

    private boolean zza(final SQLiteDatabase sQLiteDatabase, final String str) {
        boolean z;
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            z = cursor.moveToFirst();
        } catch (final SQLiteException e2) {
            zza.zzS("Error querying for table", str, e2);
            z = false;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
        if (null != cursor) {
            cursor.close();
        }
        return z;
    }

    private static Set zzb(final SQLiteDatabase sQLiteDatabase, final String str) {
        final HashSet hashSet = new HashSet();
        final Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
        try {
            final String[] columnNames = rawQuery.getColumnNames();
            Collections.addAll(hashSet, columnNames);
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    public SQLiteDatabase getWritableDatabase() {
        if (zza.zze.zzc(3600000)) {
            try {
                return super.getWritableDatabase();
            } catch (final SQLiteException unused) {
                zza.zze.zzb();
                zza.zzI("Opening the database failed, dropping the table and recreating it");
                final zzcd zzcd = zza;
                zzcd.zzo().getDatabasePath(zzcd.zzad()).delete();
                try {
                    final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zza.zze.zza();
                    return writableDatabase;
                } catch (final SQLiteException e2) {
                    zza.zzJ("Failed to open freshly created database", e2);
                    throw e2;
                }
            }
        } else {
            throw new SQLiteException("Database open failed");
        }
    }

    public void onCreate(final SQLiteDatabase sQLiteDatabase) {
        final String path = sQLiteDatabase.getPath();
        try {
            if (9 <= Integer.parseInt(Build.VERSION.SDK)) {
                zzfm.zza();
                final int i2 = zzfr.r8clinit;
                final File file = new File(path);
                file.setReadable(false, false);
                file.setWritable(false, false);
                file.setReadable(true, true);
                file.setWritable(true, true);
            }
        } catch (final NumberFormatException unused) {
            zzen.zzb("Invalid version number", Build.VERSION.SDK);
        }
    }

    public void onDowngrade(final SQLiteDatabase sQLiteDatabase, final int i2, final int i3) {
    }

    public void onUpgrade(final SQLiteDatabase sQLiteDatabase, final int i2, final int i3) {
    }

    public void onOpen(final SQLiteDatabase sQLiteDatabase) {
        int i2 = 0;
        if (!this.zza(sQLiteDatabase, "hits2")) {
            sQLiteDatabase.execSQL(zzcd.zza);
        } else {
            final Set zzb = zzcc.zzb(sQLiteDatabase, "hits2");
            final String[] strArr = {"hit_id", "hit_string", "hit_time", "hit_url"};
            int i3 = 0;
            while (4 > i3) {
                final String str = strArr[i3];
                if (zzb.remove(str)) {
                    i3++;
                } else {
                    throw new SQLiteException("Database hits2 is missing required column: " + str);
                }
            }
            final boolean remove = zzb.remove("hit_app_id");
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (!remove) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        }
        if (this.zza(sQLiteDatabase, "properties")) {
            final Set zzb2 = zzcc.zzb(sQLiteDatabase, "properties");
            final String[] strArr2 = {"app_uid", "cid", "tid", "params", "adid", "hits_count"};
            while (6 > i2) {
                final String str2 = strArr2[i2];
                if (zzb2.remove(str2)) {
                    i2++;
                } else {
                    throw new SQLiteException("Database properties is missing required column: " + str2);
                }
            }
            if (!zzb2.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
    }
}
