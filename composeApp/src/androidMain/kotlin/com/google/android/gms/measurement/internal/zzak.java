package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zznv;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzak extends zzkh {
    
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    
    public static final String[] zzb = {FirebaseAnalytics.Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    
    public static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;"};
    
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    
    public static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzaj zzj;
    
    public final zzkd zzk = new zzkd(zzs.zzav());

    zzak(final zzkr zzkr) {
        super(zzkr);
        zzs.zzf();
        zzj = new zzaj(this, zzs.zzau(), "google_app_measurement.db");
    }

    @WorkerThread
    static void zzV(final ContentValues contentValues, final String str, final Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put("value", (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    private long zzZ(final String str, final String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = this.zzh().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                final long j2 = cursor.getLong(0);
                cursor.close();
                return j2;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Database error", str, e2);
            throw e2;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private long zzaa(final String str, final String[] strArr, final long j2) {
        final Cursor cursor = null;
        try {
            final Cursor rawQuery = this.zzh().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                final long j3 = rawQuery.getLong(0);
                rawQuery.close();
                return j3;
            }
            rawQuery.close();
            return j2;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Database error", str, e2);
            throw e2;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public void zzA(final String str, final String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzg();
        this.zzW();
        try {
            this.zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzd("Error deleting user property. appId", zzej.zzn(str), zzs.zzj().zzf(str2), e2);
        }
    }

    public void zzB(final java.lang.String r24, final java.util.List r25) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzB(java.lang.String, java.util.List):void");
    }

    @WorkerThread
    public void zzC() {
        this.zzW();
        this.zzh().setTransactionSuccessful();
    }

    @WorkerThread
    public void zzD(final zzg zzg2) {
        Preconditions.checkNotNull(zzg2);
        this.zzg();
        this.zzW();
        final String zzt = zzg2.zzt();
        Preconditions.checkNotNull(zzt);
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzt);
        contentValues.put("app_instance_id", zzg2.zzu());
        contentValues.put("gmp_app_id", zzg2.zzy());
        contentValues.put("resettable_device_id_hash", zzg2.zzA());
        contentValues.put("last_bundle_index", Long.valueOf(zzg2.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzg2.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzg2.zzn()));
        contentValues.put("app_version", zzg2.zzw());
        contentValues.put("app_store", zzg2.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzg2.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzg2.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzg2.zzah()));
        contentValues.put("day", Long.valueOf(zzg2.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzg2.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzg2.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzg2.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzg2.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzg2.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzg2.zzb()));
        contentValues.put("firebase_instance_id", zzg2.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzg2.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzg2.zzh()));
        contentValues.put("health_monitor_sample", zzg2.zzz());
        contentValues.put("android_id", Long.valueOf(zzg2.zza()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzg2.zzag()));
        contentValues.put("admob_app_id", zzg2.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzg2.zzk()));
        final List zzB = zzg2.zzB();
        if (null != zzB) {
            if (zzB.isEmpty()) {
                zzs.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", zzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzB));
            }
        }
        zznv.zzc();
        if (zzs.zzf().zzs(null, zzdw.zzay) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            final SQLiteDatabase zzh2 = this.zzh();
            if (0 == ((long) zzh2.update("apps", contentValues, "app_id = ?", new String[]{zzt})) && -1 == zzh2.insertWithOnConflict("apps", null, contentValues, 5)) {
                zzs.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzej.zzn(zzt));
            }
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error storing app. appId", zzej.zzn(zzt), e2);
        }
    }

    @WorkerThread
    public void zzE(final zzaq zzaq) {
        Preconditions.checkNotNull(zzaq);
        this.zzg();
        this.zzW();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaq.zza);
        contentValues.put("name", zzaq.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzaq.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzaq.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzaq.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzaq.zzg));
        contentValues.put("last_bundled_day", zzaq.zzh);
        contentValues.put("last_sampled_complex_event_id", zzaq.zzi);
        contentValues.put("last_sampling_rate", zzaq.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzaq.zze));
        final Boolean bool = zzaq.zzk;
        contentValues.put("last_exempt_from_sampling", (null == bool || !bool.booleanValue()) ? null : 1L);
        try {
            if (-1 == zzh().insertWithOnConflict("events", null, contentValues, 5)) {
                zzs.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzej.zzn(zzaq.zza));
            }
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error storing event aggregates. appId", zzej.zzn(zzaq.zza), e2);
        }
    }

    public boolean zzF() {
        return 0 != zzZ("select count(1) > 0 from raw_events", null);
    }

    public boolean zzG() {
        return 0 != zzZ("select count(1) > 0 from queue where has_realtime = 1", null);
    }

    public boolean zzH() {
        return 0 != zzZ("select count(1) > 0 from raw_events where realtime = 1", null);
    }

    
    @VisibleForTesting
    public boolean zzI() {
        final Context zzau = zzs.zzau();
        zzs.zzf();
        return zzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public boolean zzJ(final String str, final Long l, final long j2, final zzfo zzfo) {
        this.zzg();
        this.zzW();
        Preconditions.checkNotNull(zzfo);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        final byte[] zzbq = zzfo.zzbq();
        zzs.zzay().zzj().zzc("Saving complex main event, appId, data size", zzs.zzj().zzd(str), Integer.valueOf(zzbq.length));
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j2));
        contentValues.put("main_event", zzbq);
        try {
            if (-1 != zzh().insertWithOnConflict("main_event_params", null, contentValues, 5)) {
                return true;
            }
            zzs.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzej.zzn(str));
            return false;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error storing complex main event. appId", zzej.zzn(str), e2);
            return false;
        }
    }

    @WorkerThread
    public boolean zzK(final zzab zzab) {
        Preconditions.checkNotNull(zzab);
        this.zzg();
        this.zzW();
        final String str = zzab.zza;
        Preconditions.checkNotNull(str);
        if (null == zzp(str, zzab.zzc.zzb)) {
            final long zzZ = this.zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            zzs.zzf();
            if (1000 <= zzZ) {
                return false;
            }
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzab.zzb);
        contentValues.put("name", zzab.zzc.zzb);
        zzak.zzV(contentValues, "value", Preconditions.checkNotNull(zzab.zzc.zza()));
        contentValues.put("active", Boolean.valueOf(zzab.zze));
        contentValues.put("trigger_event_name", zzab.zzf);
        contentValues.put("trigger_timeout", Long.valueOf(zzab.zzh));
        contentValues.put("timed_out_event", zzs.zzv().zzan(zzab.zzg));
        contentValues.put("creation_timestamp", Long.valueOf(zzab.zzd));
        contentValues.put("triggered_event", zzs.zzv().zzan(zzab.zzi));
        contentValues.put("triggered_timestamp", Long.valueOf(zzab.zzc.zzc));
        contentValues.put("time_to_live", Long.valueOf(zzab.zzj));
        contentValues.put("expired_event", zzs.zzv().zzan(zzab.zzk));
        try {
            if (-1 != zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5)) {
                return true;
            }
            zzs.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzej.zzn(str));
            return true;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error storing conditional user property", zzej.zzn(str), e2);
            return true;
        }
    }

    @WorkerThread
    public boolean zzL(final zzkw zzkw) {
        Preconditions.checkNotNull(zzkw);
        this.zzg();
        this.zzW();
        if (null == zzp(zzkw.zza(), zzkw.zzc())) {
            if (zzky.zzah(zzkw.zzc())) {
                if (this.zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkw.zza()}) >= zzs.zzf().zzf(zzkw.zza(), zzdw.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzkw.zzc())) {
                final long zzZ = this.zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkw.zza(), zzkw.zzb()});
                zzs.zzf();
                if (25 <= zzZ) {
                    return false;
                }
            }
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkw.zza());
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzkw.zzb());
        contentValues.put("name", zzkw.zzc());
        contentValues.put("set_timestamp", Long.valueOf(zzkw.zzd()));
        zzak.zzV(contentValues, "value", zzkw.zze());
        try {
            if (-1 != zzh().insertWithOnConflict("user_attributes", null, contentValues, 5)) {
                return true;
            }
            zzs.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzej.zzn(zzkw.zza()));
            return true;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error storing user property. appId", zzej.zzn(zzkw.zza()), e2);
            return true;
        }
    }

    public void zzU(final java.lang.String r20, final long r21, final long r23, final com.google.android.gms.measurement.internal.zzkq r25) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzU(java.lang.String, long, long, com.google.android.gms.measurement.internal.zzkq):void");
    }

    @WorkerThread
    public int zza(final String str, final String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzg();
        this.zzW();
        try {
            return this.zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzd("Error deleting conditional property", zzej.zzn(str), zzs.zzj().zzf(str2), e2);
            return 0;
        }
    }

    
    public boolean zzb() {
        return false;
    }

    public long zzc(final String str, final String str2) {
        long j2;
        SQLiteException e2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        this.zzg();
        this.zzW();
        final SQLiteDatabase zzh2 = this.zzh();
        zzh2.beginTransaction();
        try {
            final String sb = "select " +
                    "first_open_count" +
                    " from app2 where app_id=?";
            j2 = this.zzaa(sb, new String[]{str}, -1);
            if (-1 == j2) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (-1 == zzh2.insertWithOnConflict("app2", null, contentValues, 5)) {
                    zzs.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzej.zzn(str), "first_open_count");
                    zzh2.endTransaction();
                    return -1;
                }
                j2 = 0;
            }
            try {
                final ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put("first_open_count", Long.valueOf(1 + j2));
                if (0 == ((long) zzh2.update("app2", contentValues2, "app_id = ?", new String[]{str}))) {
                    zzs.zzay().zzd().zzc("Failed to update column (got 0). appId", zzej.zzn(str), "first_open_count");
                    zzh2.endTransaction();
                    return -1;
                }
                zzh2.setTransactionSuccessful();
                zzh2.endTransaction();
                return j2;
            } catch (final SQLiteException e3) {
                e2 = e3;
                try {
                    zzs.zzay().zzd().zzd("Error inserting column. appId", zzej.zzn(str), "first_open_count", e2);
                    zzh2.endTransaction();
                    return j2;
                } catch (final Throwable th) {
                    zzh2.endTransaction();
                    throw th;
                }
            }
        } catch (final SQLiteException e4) {
            e2 = e4;
            j2 = 0;
            zzs.zzay().zzd().zzd("Error inserting column. appId", zzej.zzn(str), "first_open_count", e2);
            zzh2.endTransaction();
            return j2;
        }
    }

    @WorkerThread
    public long zzd() {
        return this.zzaa("select max(bundle_end_timestamp) from queue", null, 0);
    }

    @WorkerThread
    public long zze() {
        return this.zzaa("select max(timestamp) from raw_events", null, 0);
    }

    public long zzf(final String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }


    public SQLiteDatabase zzh() {
        this.zzg();
        try {
            return zzj.getWritableDatabase();
        } catch (final SQLiteException e2) {
            zzs.zzay().zzk().zzb("Error opening database", e2);
            throw e2;
        }
    }

    public android.os.Bundle zzi(final java.lang.String r8) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzi(java.lang.String):android.os.Bundle");
    }

    public com.google.android.gms.measurement.internal.zzg zzj(final java.lang.String r33) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    public com.google.android.gms.measurement.internal.zzab zzk(final java.lang.String r27, final java.lang.String r28) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzk(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzab");
    }

    @WorkerThread
    public zzai zzl(final long j2, final String str, final boolean z, final boolean z2, final boolean z3, final boolean z4, final boolean z5) {
        return this.zzm(j2, str, 1, false, false, z3, false, z5);
    }

    @WorkerThread
    public zzai zzm(final long j2, final String str, final long j3, final boolean z, final boolean z2, final boolean z3, final boolean z4, final boolean z5) {
        Preconditions.checkNotEmpty(str);
        this.zzg();
        this.zzW();
        final String[] strArr = {str};
        final zzai zzai = new zzai();
        Cursor cursor = null;
        try {
            final SQLiteDatabase zzh2 = this.zzh();
            cursor = zzh2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            if (!cursor.moveToFirst()) {
                zzs.zzay().zzk().zzb("Not updating daily counts, app is not known. appId", zzej.zzn(str));
                cursor.close();
                return zzai;
            }
            if (cursor.getLong(0) == j2) {
                zzai.zzb = cursor.getLong(1);
                zzai.zza = cursor.getLong(2);
                zzai.zzc = cursor.getLong(3);
                zzai.zzd = cursor.getLong(4);
                zzai.zze = cursor.getLong(5);
            }
            if (z) {
                zzai.zzb += j3;
            }
            if (z2) {
                zzai.zza += j3;
            }
            if (z3) {
                zzai.zzc += j3;
            }
            if (z4) {
                zzai.zzd += j3;
            }
            if (z5) {
                zzai.zze += j3;
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j2));
            contentValues.put("daily_public_events_count", Long.valueOf(zzai.zza));
            contentValues.put("daily_events_count", Long.valueOf(zzai.zzb));
            contentValues.put("daily_conversions_count", Long.valueOf(zzai.zzc));
            contentValues.put("daily_error_events_count", Long.valueOf(zzai.zzd));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzai.zze));
            zzh2.update("apps", contentValues, "app_id=?", strArr);
            cursor.close();
            return zzai;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error updating daily counts. appId", zzej.zzn(str), e2);
            if (null != cursor) {
                cursor.close();
            }
            return zzai;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    public com.google.android.gms.measurement.internal.zzaq zzn(final java.lang.String r30, final java.lang.String r31) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzn(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzaq");
    }

    public com.google.android.gms.measurement.internal.zzkw zzp(final java.lang.String r11, final java.lang.String r12) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzp(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzkw");
    }


    public Object zzq(final Cursor cursor, final int i2) {
        final int type = cursor.getType(i2);
        if (0 == type) {
            zzs.zzay().zzd().zza("Loaded invalid null value from database");
            return null;
        } else if (1 == type) {
            return Long.valueOf(cursor.getLong(i2));
        } else {
            if (2 == type) {
                return Double.valueOf(cursor.getDouble(i2));
            }
            if (3 == type) {
                return cursor.getString(i2);
            }
            if (4 != type) {
                zzs.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzs.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    public java.lang.String zzr() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzr():java.lang.String");
    }

    @WorkerThread
    public List zzs(final String str, final String str2, final String str3) {
        Preconditions.checkNotEmpty(str);
        this.zzg();
        this.zzW();
        final ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        final StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + ProxyConfig.MATCH_ALL_SCHEMES);
            sb.append(" and name glob ?");
        }
        return this.zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public List zzt(final String str, final String[] strArr) {
        this.zzg();
        this.zzW();
        final ArrayList arrayList = new ArrayList();
        final Cursor cursor = null;
        try {
            final SQLiteDatabase zzh2 = this.zzh();
            final String[] strArr2 = {"app_id", FirebaseAnalytics.Param.ORIGIN, "name", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"};
            zzs.zzf();
            final Cursor query = zzh2.query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
            if (query.moveToFirst()) {
                while (true) {
                    final int size = arrayList.size();
                    zzs.zzf();
                    if (1000 > size) {
                        final String string = query.getString(0);
                        final String string2 = query.getString(1);
                        final String string3 = query.getString(2);
                        final Object zzq = this.zzq(query, 3);
                        final boolean z = 0 != query.getInt(4);
                        final String string4 = query.getString(5);
                        final long j2 = query.getLong(6);
                        final zzkt zzu = zzf.zzu();
                        final byte[] blob = query.getBlob(7);
                        final Parcelable.Creator<zzau> creator = zzau.CREATOR;
                        arrayList.add(new zzab(string, string2, new zzku(string3, query.getLong(10), zzq, string2), query.getLong(8), z, string4, (zzau) zzu.zzh(blob, creator), j2, (zzau) zzf.zzu().zzh(query.getBlob(9), creator), query.getLong(11), (zzau) zzf.zzu().zzh(query.getBlob(12), creator)));
                        if (!query.moveToNext()) {
                            break;
                        }
                    } else {
                        final zzeh zzd2 = zzs.zzay().zzd();
                        zzs.zzf();
                        zzd2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
                        break;
                    }
                }
                query.close();
                return arrayList;
            }
            query.close();
            return arrayList;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzb("Error querying conditional user property value", e2);
            final List emptyList = Collections.emptyList();
            if (null != cursor) {
                cursor.close();
            }
            return emptyList;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public List zzu(final String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg();
        this.zzW();
        final ArrayList arrayList = new ArrayList();
        final Cursor cursor = null;
        try {
            zzs.zzf();
            final Cursor query = this.zzh().query("user_attributes", new String[]{"name", FirebaseAnalytics.Param.ORIGIN, "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
            if (query.moveToFirst()) {
                do {
                    final String string = query.getString(0);
                    String string2 = query.getString(1);
                    if (null == string2) {
                        string2 = "";
                    }
                    final String str2 = string2;
                    final long j2 = query.getLong(2);
                    final Object zzq = this.zzq(query, 3);
                    if (null == zzq) {
                        zzs.zzay().zzd().zzb("Read invalid user property value, ignoring it. appId", zzej.zzn(str));
                    } else {
                        arrayList.add(new zzkw(str, str2, string, j2, zzq));
                    }
                } while (query.moveToNext());
                query.close();
                return arrayList;
            }
            query.close();
            return arrayList;
        } catch (final SQLiteException e2) {
            zzs.zzay().zzd().zzc("Error querying user properties. appId", zzej.zzn(str), e2);
            final List emptyList = Collections.emptyList();
            if (null != cursor) {
                cursor.close();
            }
            return emptyList;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    public java.util.List zzv(final java.lang.String r17, final java.lang.String r18, final java.lang.String r19) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zzv(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public void zzw() {
        this.zzW();
        this.zzh().beginTransaction();
    }

    @WorkerThread
    public void zzx() {
        this.zzW();
        this.zzh().endTransaction();
    } 
    public void zzy(final List list) {
        this.zzg();
        this.zzW();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (this.zzI()) {
            final String join = TextUtils.join(",", list);
            final String sb2 = "(" +
                    join +
                    ")";
            final StringBuilder sb3 = new StringBuilder(sb2.length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (0 < zzZ(sb3.toString(), null)) {
                zzs.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                final SQLiteDatabase zzh2 = this.zzh();
                String sb4 = "UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " +
                        sb2 +
                        " AND (retry_count IS NULL OR retry_count < " +
                        Integer.MAX_VALUE +
                        ")";
                zzh2.execSQL(sb4);
            } catch (final SQLiteException e2) {
                zzs.zzay().zzd().zzb("Error incrementing retry count. error", e2);
            }
        }
    }

    
    @WorkerThread
    public void zzz() {
        this.zzg();
        this.zzW();
        if (this.zzI()) {
            final long zza2 = zzf.zzs().zza.zza();
            final long elapsedRealtime = zzs.zzav().elapsedRealtime();
            final long abs = Math.abs(elapsedRealtime - zza2);
            zzs.zzf();
            if (abs > ((Long) zzdw.zzx.zza(null)).longValue()) {
                zzf.zzs().zza.zzb(elapsedRealtime);
                this.zzg();
                this.zzW();
                if (this.zzI()) {
                    final SQLiteDatabase zzh2 = this.zzh();
                    final String valueOf = String.valueOf(zzs.zzav().currentTimeMillis());
                    zzs.zzf();
                    final int delete = zzh2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{valueOf, String.valueOf(zzaf.zzA())});
                    if (0 < delete) {
                        zzs.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }
}
