package com.google.android.gms.internal.gtm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzcd extends zzbr implements Closeable {
    
    public static final String zza = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private static final String zzb = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
    private final zzcc zzc;
    private final zzfb zzd = new zzfb(this.zzC());
    
    public final zzfb zze = new zzfb(this.zzC());

    zzcd(final zzbu zzbu) {
        super(zzbu);
        zzc = new zzcc(this, zzbu.zza(), this.zzad());
    }

    private long zzac(final String str, final String[] strArr, final long j2) {
        final Cursor cursor = null;
        try {
            final Cursor rawQuery = this.zzf().rawQuery(str, strArr);
            final long j3 = rawQuery.moveToFirst() ? rawQuery.getLong(0) : 0;
            rawQuery.close();
            return j3;
        } catch (final SQLiteException e2) {
            this.zzK("Database error", str, e2);
            throw e2;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    
    public String zzad() {
        this.zzw();
        this.zzw();
        return "google_analytics_v4.db";
    }

    public void close() {
        try {
            zzc.close();
        } catch (final SQLiteException e2) {
            this.zzJ("Sql error closing database", e2);
        } catch (final IllegalStateException e3) {
            this.zzJ("Error closing database", e3);
        }
    }

    public void zzY(final List list) {
        Preconditions.checkNotNull(list);
        zzr.zzh();
        this.zzV();
        if (!list.isEmpty()) {
            final StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i2 = 0; i2 < list.size(); i2++) {
                final Long l = (Long) list.get(i2);
                if (null == l || 0 == l.longValue()) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (0 < i2) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            final String sb2 = sb.toString();
            try {
                final SQLiteDatabase zzf = this.zzf();
                this.zzO("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                final int delete = zzf.delete("hits2", sb2, null);
                if (delete != list.size()) {
                    this.zzT("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (final SQLiteException e2) {
                this.zzJ("Error deleting hits", e2);
                throw e2;
            }
        }
    }

    public void zzZ() {
        this.zzV();
        this.zzf().endTransaction();
    }

    public int zza() {
        zzr.zzh();
        this.zzV();
        if (!zzd.zzc(86400000)) {
            return 0;
        }
        zzd.zzb();
        this.zzN("Deleting stale hits (if any)");
        final int delete = this.zzf().delete("hits2", "hit_time < ?", new String[]{Long.toString(this.zzC().currentTimeMillis() - 2592000000L)});
        this.zzO("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public void zzaa() {
        this.zzV();
        this.zzf().setTransactionSuccessful();
    }

    
    public boolean zzab() {
        return 0 == zzb();
    }

    public long zzb() {
        zzr.zzh();
        this.zzV();
        Cursor cursor = null;
        try {
            cursor = this.zzf().rawQuery("SELECT COUNT(*) FROM hits2", null);
            if (cursor.moveToFirst()) {
                final long j2 = cursor.getLong(0);
                cursor.close();
                return j2;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (final SQLiteException e2) {
            this.zzK("Database error", "SELECT COUNT(*) FROM hits2", e2);
            throw e2;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    public long zzc() {
        zzr.zzh();
        this.zzV();
        return this.zzac(zzcd.zzb, null, 0);
    }

    
    public void zzd() {
    }

    public long zze(final long j2, final String str, final String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzV();
        zzr.zzh();
        return this.zzac("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{"0", str, str2}, 0);
    }

    
    @VisibleForTesting
    public SQLiteDatabase zzf() {
        try {
            return zzc.getWritableDatabase();
        } catch (final SQLiteException e2) {
            this.zzR("Error opening database", e2);
            throw e2;
        }
    }
    public java.util.List zzj(final long r17) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcd.zzj(long):java.util.List");
    }

    public Map zzk(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                str = "?" + str;
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (final URISyntaxException e2) {
            this.zzJ("Error parsing hit parameters", e2);
            return new HashMap(0);
        }
    }

    
    @VisibleForTesting
    public Map zzl(final String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            return HttpUtils.parse(new URI("?" + str), "UTF-8");
        } catch (final URISyntaxException e2) {
            this.zzJ("Error parsing property parameters", e2);
            return new HashMap(0);
        }
    }

    public void zzm() {
        this.zzV();
        this.zzf().beginTransaction();
    }

    public void zzn(final long j2) {
        zzr.zzh();
        this.zzV();
        final ArrayList arrayList = new ArrayList(1);
        final Long valueOf = Long.valueOf(j2);
        arrayList.add(valueOf);
        this.zzO("Deleting hit, id", valueOf);
        this.zzY(arrayList);
    }
}
