package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzeb extends SQLiteOpenHelper {
    final zzec zza;

    
    zzeb(final zzec zzec, final Context context, final String str) {
        super(context, "google_app_measurement_local.db", null, 1);
        zza = zzec;
    }

    @WorkerThread
    public SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (final SQLiteDatabaseLockedException e2) {
            throw e2;
        } catch (final SQLiteException unused) {
            zza.zzs.zzay().zzd().zza("Opening the local database failed, dropping and recreating it");
            zza.zzs.zzf();
            if (!zza.zzs.zzau().getDatabasePath("google_app_measurement_local.db").delete()) {
                zza.zzs.zzay().zzd().zzb("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (final SQLiteException e3) {
                zza.zzs.zzay().zzd().zzb("Failed to open local database. Events will bypass local storage", e3);
                return null;
            }
        }
    }

    @WorkerThread
    public void onCreate(final SQLiteDatabase sQLiteDatabase) {
        zzal.zzb(zza.zzs.zzay(), sQLiteDatabase);
    }

    @WorkerThread
    public void onDowngrade(final SQLiteDatabase sQLiteDatabase, final int i2, final int i3) {
    }

    @WorkerThread
    public void onOpen(final SQLiteDatabase sQLiteDatabase) {
        zzal.zza(zza.zzs.zzay(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    @WorkerThread
    public void onUpgrade(final SQLiteDatabase sQLiteDatabase, final int i2, final int i3) {
    }
}
