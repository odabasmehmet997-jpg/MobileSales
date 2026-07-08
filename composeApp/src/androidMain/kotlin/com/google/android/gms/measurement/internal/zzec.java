package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzec extends zzf {
    private final zzeb zza;
    private boolean zzb;

    zzec(final zzft zzft) {
        super(zzft);
        final Context zzau = zzs.zzau();
        zzs.zzf();
        zza = new zzeb(this, zzau, "google_app_measurement_local.db");
    }

    private boolean zzq(final int r17, final byte[] r18) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzec.zzq(int, byte[]):boolean");
    }

    
    public boolean zzf() {
        return false;
    }

    public SQLiteDatabase zzh() throws SQLiteException {
        if (zzb) {
            return null;
        }
        final SQLiteDatabase writableDatabase = zza.getWritableDatabase();
        if (null != writableDatabase) {
            return writableDatabase;
        }
        zzb = true;
        return null;
    }

    public java.util.List zzi(final int r23) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzec.zzi(int):java.util.List");
    }
    public void zzj() {
        final int delete;
        this.zzg();
        try {
            final SQLiteDatabase zzh = this.zzh();
            if (null != zzh && 0 < (delete = zzh.delete("messages", null, null))) {
                zzs.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzb("Error resetting local analytics data. error", e2);
        }
    }
    public boolean zzk() {
        return this.zzq(3, new byte[0]);
    }
    public boolean zzl() {
        final Context zzau = zzs.zzau();
        zzs.zzf();
        return zzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    @WorkerThread
    public boolean zzm() {
        this.zzg();
        if (!zzb && this.zzl()) {
            int i2 = 5;
            int i3 = 0;
            while (5 > i3) {
                final SQLiteDatabase sQLiteDatabase = null;
                try {
                    final SQLiteDatabase zzh = this.zzh();
                    if (null == zzh) {
                        zzb = true;
                        return false;
                    }
                    zzh.beginTransaction();
                    zzh.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    zzh.setTransactionSuccessful();
                    zzh.endTransaction();
                    zzh.close();
                    return true;
                } catch (final SQLiteFullException e2) {
                    zzs.zzay().zzd().zzb("Error deleting app launch break from local database", e2);
                    zzb = true;
                    if (null == sQLiteDatabase) {
                        i3++;
                    }
                    sQLiteDatabase.close();
                    i3++;
                } catch (final SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i2);
                    i2 += 20;
                    if (null == sQLiteDatabase) {
                        i3++;
                    }
                    sQLiteDatabase.close();
                    i3++;
                } catch (final SQLiteException e3) {
                    if (null != sQLiteDatabase) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    zzs.zzay().zzd().zzb("Error deleting app launch break from local database", e3);
                    zzb = true;
                    if (null != sQLiteDatabase) {
                        sQLiteDatabase.close();
                        i3++;
                    } else {
                        i3++;
                    }
                } catch (final Throwable th) {
                    if (null != sQLiteDatabase) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            }
            zzs.zzay().zzk().zza("Error deleting app launch break from local database in reasonable time");
        }
        return false;
    }

    public boolean zzn(final zzab zzab) {
        final byte[] zzan = zzs.zzv().zzan(zzab);
        if (131072 >= zzan.length) {
            return this.zzq(2, zzan);
        }
        zzs.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public boolean zzo(final zzau zzau) {
        final Parcel obtain = Parcel.obtain();
        zzav.zza(zzau, obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (131072 >= marshall.length) {
            return this.zzq(0, marshall);
        }
        zzs.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public boolean zzp(final zzku zzku) {
        final Parcel obtain = Parcel.obtain();
        zzkv.zza(zzku, obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (131072 >= marshall.length) {
            return this.zzq(1, marshall);
        }
        zzs.zzay().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
