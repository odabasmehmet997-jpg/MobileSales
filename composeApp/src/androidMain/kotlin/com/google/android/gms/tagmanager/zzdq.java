package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfp;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdq extends zzfp {
    final zzdr zza;
    private boolean zzb;
    private long zzc = 0;

    
    zzdq(zzdr zzdr, Context context, String str) {
        super(context, "gtm_urls.db", null, 1);
        this.zza = zzdr;
    }

    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (!this.zzb || this.zzc + 3600000 <= this.zza.zzg.currentTimeMillis()) {
            this.zzb = true;
            this.zzc = this.zza.zzg.currentTimeMillis();
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException unused) {
                zzdr zzdr = this.zza;
                zzdr.zzd.getDatabasePath(zzdr.zze).delete();
                sQLiteDatabase = null;
            }
            if (sQLiteDatabase == null) {
                sQLiteDatabase = super.getWritableDatabase();
            }
            this.zzb = false;
            return sQLiteDatabase;
        }
        throw new SQLiteException("Database creation failed");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbq.zza(sQLiteDatabase.getPath());
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public void onOpen(android.database.sqlite.SQLiteDatabase r11) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdq.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }
}
