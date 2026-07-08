package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

public class zzal {

    static void zza(final com.google.android.gms.measurement.internal.zzej r10, final android.database.sqlite.SQLiteDatabase r11, final java.lang.String r12, final java.lang.String r13, final java.lang.String r14, final java.lang.String[] r15) throws android.database.sqlite.SQLiteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(com.google.android.gms.measurement.internal.zzej, android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    static void zzb(final zzej zzej, final SQLiteDatabase sQLiteDatabase) {
        if (null != zzej) {
            final File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzej.zzk().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzej.zzk().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzej.zzk().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzej.zzk().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
