package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.gtm.zzfx;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

final class zzaz implements zzas {
    
    public static final String zza = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", "value", "expires");
    private final Executor zzb;
    
    public final Context zzc;
    private final zzax zzd;
    private final Clock zze;

    public zzaz(Context context) {
        Clock instance = DefaultClock.getInstance();
        ExecutorService zza2 = zzfx.zza().zza(2);
        this.zzc = context;
        this.zze = instance;
        this.zzb = zza2;
        this.zzd = new zzax(this, context, "google_tagmanager.db");
    }


    static java.util.List zzf(com.google.android.gms.tagmanager.zzaz r10) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzaz.zzf(com.google.android.gms.tagmanager.zzaz):java.util.List");
    }

    static void zzg(zzaz zzaz, String str) {
        SQLiteDatabase zzi = zzaz.zzi("Error opening database for clearKeysWithPrefix.");
        if (zzi != null) {
            try {
                int delete = zzi.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, str + ".%"});
                zzdc.zzb.zzd("Cleared " + delete + " items");
            } catch (SQLiteException e2) {
                String obj = e2.toString();
                Log.w("GoogleTagManager", "Error deleting entries with key prefix: " + str + " (" + obj + ").");
            } finally {
                zzaz.zzj();
            }
        }
    }

    private SQLiteDatabase zzi(String str) {
        try {
            return this.zzd.getWritableDatabase();
        } catch (SQLiteException unused) {
            Log.w("GoogleTagManager", str);
            return null;
        }
    }

    private void zzj() {
        try {
            this.zzd.close();
        } catch (SQLiteException unused) {
        }
    }

    private void zzk(long j2) {
        SQLiteDatabase zzi = zzi("Error opening database for deleteOlderThan.");
        if (zzi != null) {
            try {
                int delete = zzi.delete("datalayer", "expires <= ?", new String[]{Long.toString(j2)});
                zzdc.zzb.zzd("Deleted " + delete + " expired items");
            } catch (SQLiteException unused) {
                Log.w("GoogleTagManager", "Error deleting old entries.");
            }
        }
    }

    public synchronized void zzl(java.util.List r18, long r19) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzaz.zzl(java.util.List, long):void");
    }

    public void zza(String str) {
        this.zzb.execute(new zzaw(this, str));
    }

    public void zzb(zzar zzar) {
        this.zzb.execute(new zzav(this, zzar));
    }

    public void zzc(java.util.List r8, long r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzaz.zzc(java.util.List, long):void");
    }
}
