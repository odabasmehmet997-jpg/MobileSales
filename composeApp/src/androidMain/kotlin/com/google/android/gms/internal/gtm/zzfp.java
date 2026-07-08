package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzfp extends SQLiteOpenHelper {
    
    protected zzfp(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, "".equals(str) ? null : str, null, 1);
        final int i3 = zzfr.r8clinit;
        zzfm.zza();
    }
}
