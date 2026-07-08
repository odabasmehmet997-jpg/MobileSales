package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.Collections;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdr implements zzby {
    
    public static final String zza = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    private final zzdq zzb;
    private final zzbf zzc;
    
    public final Context zzd;
    
    public final String zze = "gtm_urls.db";
    private final long zzf;
    
    public final Clock zzg;
    private final int zzh;
    private final zzeu zzi;

    zzdr(zzeu zzeu, Context context) {
        Context applicationContext = context.getApplicationContext();
        this.zzd = applicationContext;
        this.zzi = zzeu;
        this.zzg = DefaultClock.getInstance();
        this.zzb = new zzdq(this, applicationContext, "gtm_urls.db");
        this.zzc = new zzfd(applicationContext, new zzdp(this));
        this.zzf = 0;
        this.zzh = 2000;
    }

    static void zzi(zzdr zzdr, long j2, long j3) {
        SQLiteDatabase zzk = zzdr.zzk("Error opening database for getNumStoredHits.");
        if (zzk != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j3));
            try {
                zzk.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j2)});
            } catch (SQLiteException unused) {
                Log.w("GoogleTagManager", "Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j2);
                zzdr.zzl(j2);
            }
        }
    }

    private SQLiteDatabase zzk(String str) {
        try {
            return this.zzb.getWritableDatabase();
        } catch (SQLiteException unused) {
            Log.w("GoogleTagManager", str);
            return null;
        }
    }

    
    public void zzl(long j2) {
        zzj(new String[]{String.valueOf(j2)});
    }

    public void zza() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdr.zza():void");
    }


    public void zzb(long r19, java.lang.String r21) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdr.zzb(long, java.lang.String):void");
    }

    public int zzc() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdr.zzc():int");
    }

    
    public void zzj(String[] strArr) {
        int length;
        SQLiteDatabase zzk;
        if (strArr != null && (length = strArr.length) != 0 && (zzk = zzk("Error opening database for deleteHits.")) != null) {
            try {
                zzk.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(length, "?"))), strArr);
                this.zzi.zza(zzc() == 0);
            } catch (SQLiteException unused) {
                Log.w("GoogleTagManager", "Error deleting hits");
            }
        }
    }
}
