package com.google.android.gms.internal.gtm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzcj extends zzbr {
    private boolean zza;
    private final zzcd zzb;
    private final zzer zzc;
    private final zzep zzd;
    private final zzcb zze;
    private long zzf = Long.MIN_VALUE;
    private final zzcv zzg;
    private final zzcv zzh;
    private final zzfb zzi;
    private long zzj;
    private boolean zzk;

    zzcj(final zzbu zzbu, final zzbv zzbv) {
        super(zzbu);
        Preconditions.checkNotNull(zzbv);
        zzd = new zzep(zzbu);
        zzb = new zzcd(zzbu);
        zzc = new zzer(zzbu);
        zze = new zzcb(zzbu);
        zzi = new zzfb(this.zzC());
        zzg = new zzcf(this, zzbu);
        zzh = new zzcg(this, zzbu);
    }

    private void zzaf() {
        final zzcx zzy = this.zzy();
        if (zzy.zze()) {
            zzy.zza();
        }
    }

    private void zzag() {
        if (zzg.zzh()) {
            this.zzN("All hits dispatched or no network/service. Going to power save mode");
        }
        zzg.zzf();
    }

    private void zzah() {
        long j2;
        final zzcx zzy = this.zzy();
        if (zzy.zzc() && !zzy.zze()) {
            zzr.zzh();
            this.zzV();
            try {
                j2 = zzb.zzc();
            } catch (final SQLiteException e2) {
                this.zzJ("Failed to get min/max hit times from local store", e2);
                j2 = 0;
            }
            if (0 != j2) {
                final long abs = Math.abs(this.zzC().currentTimeMillis() - j2);
                this.zzw();
                if (abs <= ((Long) zzeh.zzh.zzb()).longValue()) {
                    this.zzw();
                    this.zzO("Dispatch alarm scheduled (ms)", Long.valueOf(zzcs.zzd()));
                    zzy.zzb();
                }
            }
        }
    }

    private void zzai(final zzbw zzbw, final zzav zzav) {
        Preconditions.checkNotNull(zzbw);
        Preconditions.checkNotNull(zzav);
        final zza zza2 = new zza(this.zzt());
        zza2.zzc(zzbw.zzc());
        zza2.zzd(zzbw.zzf());
        final zzh zza3 = zza2.zza();
        final zzbd zzbd = (zzbd) zza3.zzb(zzbd.class);
        zzbd.zzk(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        zzbd.zzl(true);
        zza3.zzg(zzav);
        final zzay zzay = (zzay) zza3.zzb(zzay.class);
        final zzau zzau = (zzau) zza3.zzb(zzau.class);
        for (final Map.Entry entry : zzbw.zzd().entrySet()) {
            final String str = (String) entry.getKey();
            final String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzau.zzk(str2);
            } else if ("av".equals(str)) {
                zzau.zzl(str2);
            } else if ("aid".equals(str)) {
                zzau.zzi(str2);
            } else if ("aiid".equals(str)) {
                zzau.zzj(str2);
            } else if ("uid".equals(str)) {
                zzbd.zzm(str2);
            } else {
                zzay.zze(str, str2);
            }
        }
        this.zzG("Sending installation campaign to", zzbw.zzc(), zzav);
        zza3.zzj(this.zzA().zza());
        zza3.zzk();
    }

    private boolean zzaj(final String str) {
        return 0 == Wrappers.packageManager(zzo()).checkCallingOrSelfPermission(str);
    }

    static void zzc(final zzcj zzcj) {
        try {
            zzcj.zzb.zza();
            zzcj.zzad();
        } catch (final SQLiteException e2) {
            zzcj.zzR("Failed to delete stale hits", e2);
        }
        final zzcv zzcv = zzcj.zzh;
        zzcj.zzw();
        zzcv.zzg(86400000);
    }

    public void zzY(long j2) {
        zzr.zzh();
        this.zzV();
        if (0 > j2) {
            j2 = 0;
        }
        zzf = j2;
        this.zzad();
    }

    
    public void zzZ() {
        this.zzV();
        Preconditions.checkState(!zza, "Analytics backend already started");
        zza = true;
        this.zzq().zzi(new zzch(this));
    }

    public long zza() {
        final long j2 = zzf;
        if (Long.MIN_VALUE != j2) {
            return j2;
        }
        this.zzw();
        final long longValue = ((Long) zzeh.zze.zzb()).longValue();
        final zzfg zzB = this.zzB();
        zzB.zzV();
        if (!zzB.zzc) {
            return longValue;
        }
        final zzfg zzB2 = this.zzB();
        zzB2.zzV();
        return ((long) zzB2.zzd) * 1000;
    }

    
    public void zzaa() {
        this.zzV();
        this.zzw();
        zzr.zzh();
        final Context zza2 = this.zzt().zza();
        if (!zzev.zzb(zza2)) {
            this.zzQ("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzfa.zzh(zza2)) {
            this.zzI("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzb(zza2)) {
            this.zzQ("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        this.zzA().zza();
        if (!this.zzaj("android.permission.ACCESS_NETWORK_STATE")) {
            this.zzI("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzac();
        }
        if (!this.zzaj("android.permission.INTERNET")) {
            this.zzI("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzac();
        }
        if (zzfa.zzh(this.zzo())) {
            this.zzN("AnalyticsService registered in the app manifest and enabled");
        } else {
            this.zzw();
            this.zzQ("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!zzk) {
            this.zzw();
            if (!zzb.zzab()) {
                this.zzi();
            }
        }
        this.zzad();
    }

    public void zzab() {
        zzr.zzh();
        this.zzV();
        this.zzE("Sync dispatching local hits");
        final long j2 = zzj;
        this.zzw();
        this.zzi();
        try {
            this.zzae();
            this.zzA().zzi();
            this.zzad();
            if (zzj != j2) {
                zzd.zzb();
            }
        } catch (final Exception e2) {
            this.zzJ("Sync local dispatch failed", e2);
            this.zzad();
        }
    }

    public void zzac() {
        this.zzV();
        zzr.zzh();
        zzk = true;
        zze.zzc();
        this.zzad();
    }

    public void zzad() {
        long j2;
        zzr.zzh();
        this.zzV();
        if (!zzk) {
            this.zzw();
            if (0 < zza()) {
                if (zzb.zzab()) {
                    zzd.zzc();
                    this.zzag();
                    this.zzaf();
                    return;
                }
                if (!((Boolean) zzeh.zzz.zzb()).booleanValue()) {
                    zzd.zza();
                    if (!zzd.zzd()) {
                        this.zzag();
                        this.zzaf();
                        this.zzah();
                        return;
                    }
                }
                this.zzah();
                final long zza2 = this.zza();
                final long zzb2 = this.zzA().zzb();
                if (0 != zzb2) {
                    j2 = zza2 - Math.abs(this.zzC().currentTimeMillis() - zzb2);
                    if (0 >= j2) {
                        this.zzw();
                        j2 = Math.min(zzcs.zze(), zza2);
                    }
                } else {
                    this.zzw();
                    j2 = Math.min(zzcs.zze(), zza2);
                }
                this.zzO("Dispatch scheduled (ms)", Long.valueOf(j2));
                if (zzg.zzh()) {
                    zzg.zze(Math.max(1, j2 + zzg.zzb()));
                    return;
                }
                zzg.zzg(j2);
                return;
            }
        }
        zzd.zzc();
        this.zzag();
        this.zzaf();
    }


    public long zzb(final zzbw zzbw, final boolean z) {
        Preconditions.checkNotNull(zzbw);
        this.zzV();
        zzr.zzh();
        long j2 = -1;
        try {
            zzb.zzm();
            final zzcd zzcd = zzb;
            final String zzb2 = zzbw.zzb();
            Preconditions.checkNotEmpty(zzb2);
            zzcd.zzV();
            zzr.zzh();
            final int delete = zzcd.zzf().delete("properties", "app_uid=? AND cid<>?", new String[]{"0", zzb2});
            if (0 < delete) {
                zzcd.zzO("Deleted property records", Integer.valueOf(delete));
            }
            final long zze2 = zzb.zze(0, zzbw.zzb(), zzbw.zzc());
            zzbw.zze(1 + zze2);
            final zzcd zzcd2 = zzb;
            Preconditions.checkNotNull(zzbw);
            zzcd2.zzV();
            zzr.zzh();
            final SQLiteDatabase zzf2 = zzcd2.zzf();
            final Map zzd2 = zzbw.zzd();
            Preconditions.checkNotNull(zzd2);
            final Uri.Builder builder = new Uri.Builder();
            for (final Map.Entry entry : zzd2.entrySet()) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (null == encodedQuery) {
                encodedQuery = "";
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", 0L);
            contentValues.put("cid", zzbw.zzb());
            contentValues.put("tid", zzbw.zzc());
            contentValues.put("adid", Integer.valueOf(zzbw.zzf() ? 1 : 0));
            contentValues.put("hits_count", Long.valueOf(zzbw.zza()));
            contentValues.put("params", encodedQuery);
            try {
                if (-1 == zzf2.insertWithOnConflict("properties", null, contentValues, 5)) {
                    zzcd2.zzI("Failed to insert/update a property (got -1)");
                }
            } catch (final SQLiteException e2) {
                zzcd2.zzJ("Error storing a property", e2);
            }
            zzb.zzaa();
            j2 = zze2;
        } catch (final SQLiteException e3) {
            this.zzJ("Failed to update Analytics property", e3);
        } catch (final Throwable th) {
            try {
                zzb.zzZ();
            } catch (final SQLiteException e4) {
                this.zzJ("Failed to end transaction", e4);
            }
            throw th;
        }
        try {
            zzb.zzZ();
        } catch (final SQLiteException e5) {
            this.zzJ("Failed to end transaction", e5);
        }
        return j2;
    }

    
    public void zzd() {
        zzb.zzW();
        zzc.zzW();
        zze.zzW();
    }

    public void zzf(final zzcy zzcy) {
        this.zzg(zzcy, zzj);
    }

    public void zzg(final zzcy zzcy, final long j2) {
        zzr.zzh();
        this.zzV();
        final long zzb2 = this.zzA().zzb();
        this.zzF("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(0 != zzb2 ? Math.abs(this.zzC().currentTimeMillis() - zzb2) : -1));
        this.zzw();
        this.zzi();
        try {
            this.zzae();
            this.zzA().zzi();
            this.zzad();
            if (null != zzcy) {
                zzcy.zza(null);
            }
            if (zzj != j2) {
                zzd.zzb();
            }
        } catch (final Exception e2) {
            this.zzJ("Local dispatch failed", e2);
            this.zzA().zzi();
            this.zzad();
            if (null != zzcy) {
                zzcy.zza(e2);
            }
        }
    }

    public void zzh() {
        zzr.zzh();
        this.zzV();
        this.zzw();
        this.zzN("Delete all hits from local store");
        try {
            final zzcd zzcd = zzb;
            zzr.zzh();
            zzcd.zzV();
            zzcd.zzf().delete("hits2", null, null);
            final zzcd zzcd2 = zzb;
            zzr.zzh();
            zzcd2.zzV();
            zzcd2.zzf().delete("properties", null, null);
            this.zzad();
        } catch (final SQLiteException e2) {
            this.zzR("Failed to delete hits from store", e2);
        }
        this.zzi();
        if (zze.zze()) {
            this.zzN("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    
    public void zzi() {
        if (!zzk) {
            this.zzw();
            if (zzcs.zzl() && !zze.zzg()) {
                this.zzw();
                if (zzi.zzc(((Long) zzeh.zzC.zzb()).longValue())) {
                    zzi.zzb();
                    this.zzN("Connecting to service");
                    if (zze.zzf()) {
                        this.zzN("Connected to service");
                        zzi.zza();
                        this.zzm();
                    }
                }
            }
        }
    }

    public void zzj(final com.google.android.gms.internal.gtm.zzek r20) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcj.zzj(com.google.android.gms.internal.gtm.zzek):void");
    }

    
    public void zzk(final zzbw zzbw) {
        zzr.zzh();
        this.zzF("Sending first hit to property", zzbw.zzc());
        final zzfb zzf2 = this.zzA().zzf();
        this.zzw();
        if (!zzf2.zzc(zzcs.zzc())) {
            final String zzg2 = this.zzA().zzg();
            if (!TextUtils.isEmpty(zzg2)) {
                final zzav zzb2 = zzff.zzb(this.zzz(), zzg2);
                this.zzF("Found relevant installation campaign", zzb2);
                this.zzai(zzbw, zzb2);
            }
        }
    }

    
    public void zzl() {
        zzr.zzh();
        zzj = this.zzC().currentTimeMillis();
    }


    public void zzm() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcj.zzm():void");
    }

    public void zzn(final String str) {
        final String str2 = str;
        Preconditions.checkNotEmpty(str);
        zzr.zzh();
        this.zzw();
        final zzav zzb2 = zzff.zzb(this.zzz(), str2);
        if (null == zzb2) {
            this.zzR("Parsing failed. Ignoring invalid campaign data", str2);
            return;
        }
        final String zzg2 = this.zzA().zzg();
        if (str2.equals(zzg2)) {
            this.zzQ("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzg2)) {
            this.zzK("Ignoring multiple install campaigns. original, new", zzg2, str2);
        } else {
            this.zzA().zzh(str2);
            final zzfb zzf2 = this.zzA().zzf();
            this.zzw();
            if (zzf2.zzc(zzcs.zzc())) {
                this.zzR("Campaign received too late, ignoring", zzb2);
                return;
            }
            this.zzF("Received installation campaign", zzb2);
            final zzcd zzcd = zzb;
            zzcd.zzV();
            zzr.zzh();
            final SQLiteDatabase zzf3 = zzcd.zzf();
            final Cursor cursor = null;
            try {
                zzcd.zzw();
                final int intValue = ((Integer) zzeh.zzd.zzb()).intValue();
                final Cursor query = zzf3.query("properties", new String[]{"cid", "tid", "adid", "hits_count", "params"}, "app_uid=?", new String[]{"0"}, null, null, null, String.valueOf(intValue));
                final ArrayList<zzbw> arrayList = new ArrayList<>();
                if (query.moveToFirst()) {
                    do {
                        final String string = query.getString(0);
                        final String string2 = query.getString(1);
                        final boolean z = 0 != query.getInt(2);
                        final long j2 = query.getInt(3);
                        final Map zzl = zzcd.zzl(query.getString(4));
                        if (!TextUtils.isEmpty(string)) {
                            if (!TextUtils.isEmpty(string2)) {
                                arrayList.add(new zzbw(0, string, string2, z, j2, zzl));
                            }
                        }
                        zzcd.zzS("Read property with empty client id or tracker id", string, string2);
                    } while (query.moveToNext());
                }
                if (arrayList.size() >= intValue) {
                    zzcd.zzQ("Sending hits to too many properties. Campaign report might be incorrect");
                }
                query.close();
                for (final zzbw zzai : arrayList) {
                    this.zzai(zzai, zzb2);
                }
            } catch (final SQLiteException e2) {
                zzcd.zzJ("Error loading hits from the database", e2);
                throw e2;
            } catch (final Throwable th) {
                if (null != cursor) {
                    cursor.close();
                }
                throw th;
            }
        }
    }
}
