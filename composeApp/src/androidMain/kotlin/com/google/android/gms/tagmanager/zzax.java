package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfp;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzax extends zzfp {
    final zzaz zza;

    
    zzax(zzaz zzaz, Context context, String str) {
        super(context, "google_tagmanager.db", null, 1);
        this.zza = zzaz;
    }

    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = super.getWritableDatabase();
        } catch (SQLiteException unused) {
            this.zza.zzc.getDatabasePath("google_tagmanager.db").delete();
            sQLiteDatabase = null;
        }
        return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbq.zza(sQLiteDatabase.getPath());
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public void onOpen(android.database.sqlite.SQLiteDatabase r11) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzax.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }
}
