package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.internal.measurement.zzfb;
import com.google.android.gms.internal.measurement.zzfc;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzgo;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzr;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.BuildConfig;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfk extends zzkh implements zzae {
    @VisibleForTesting
    final Map zza = new ArrayMap();
    @VisibleForTesting
    final Map zzb = new ArrayMap();
    @VisibleForTesting
    final LruCache zzc = new zzfh(this, 20);
    final zzr zzd = new zzfi(this);

    public final Map zze = new ArrayMap();
    private final Map zzg = new ArrayMap();
    private final Map zzh = new ArrayMap();
    private final Map zzi = new ArrayMap();

    zzfk(zzkr zzkr) {
        super(zzkr);
    }

    static zzc zzd(zzfk zzfk, String str) {
        zzfk.zzW();
        Preconditions.checkNotEmpty(str);
        if (!zzfk.zzl(str)) {
            return null;
        }
        if (!zzfk.zzg.containsKey(str) || null == zzfk.zzg.get(str)) {
            zzfk.zzt(str);
        } else {
            zzfk.zzu(str, (zzfc) zzfk.zzg.get(str));
        }
        return (zzc) zzfk.zzc.snapshot().get(str);
    }

    @WorkerThread
    private zzfc zzr(String str, byte[] bArr) {
        if (null == bArr) {
            return zzfc.zzg();
        }
        try {
            final zzfc zzfc = (zzfc) ((zzfb) zzkt.zzl(com.google.android.gms.internal.measurement.zzfc.zze(), bArr)).zzay();
            zzeh zzj = this.zzs.zzay().zzj();
            String str2 = null;
            Long valueOf = zzfc.zzq() ? Long.valueOf(zzfc.zzc()) : null;
            if (zzfc.zzp()) {
                str2 = zzfc.zzh();
            }
            zzj.zzc("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzfc;
        } catch (zzkj e2) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzej.zzn(str), e2);
            return zzfc.zzg();
        } catch (RuntimeException e3) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzej.zzn(str), e3);
            return zzfc.zzg();
        }
    }

    private void zzs(String str, zzfb zzfb) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (null != zzfb) {
            for (int i2 = 0; i2 < zzfb.zza(); i2++) {
                zzez zzez = (zzez) zzfb.zzb(i2).zzbt();
                if (TextUtils.isEmpty(zzez.zzc())) {
                    this.zzs.zzay().zzk().zza("EventConfig contained null event name");
                } else {
                    String zzc2 = zzez.zzc();
                    String zzb2 = zzgq.zzb(zzez.zzc());
                    if (!TextUtils.isEmpty(zzb2)) {
                        zzez.zzb(zzb2);
                        zzfb.zzd(i2, zzez);
                    }
                    if (zzez.zzf() && zzez.zzd()) {
                        arrayMap.put(zzc2, Boolean.TRUE);
                    }
                    if (zzez.zzg() && zzez.zze()) {
                        arrayMap2.put(zzez.zzc(), Boolean.TRUE);
                    }
                    if (zzez.zzh()) {
                        if (2 > zzez.zza() || 65535 < zzez.zza()) {
                            this.zzs.zzay().zzk().zzc("Invalid sampling rate. Event name, sample rate", zzez.zzc(), Integer.valueOf(zzez.zza()));
                        } else {
                            arrayMap3.put(zzez.zzc(), Integer.valueOf(zzez.zza()));
                        }
                    }
                }
            }
        }
        this.zza.put(str, arrayMap);
        this.zzb.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    /*  WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        if (r2 != null) goto L_0x0042;
     */
    /*  WARNING: Removed duplicated region for block: B:31:0x009c  */
    /*  WARNING: Removed duplicated region for block: B:33:0x00bb  */
    /*  WARNING: Removed duplicated region for block: B:36:0x00fb  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzt(java.lang.String r11) {
        /*
            r10 = this;
            r10.zzW()
            r10.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            java.util.Map r0 = r10.zzg
            java.lang.Object r0 = r0.get(r11)
            if (r0 != 0) goto L_0x00ff
            com.google.android.gms.measurement.internal.zzkr r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzak r0 = r0.zzi()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r0.zzg()
            r0.zzW()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r0.zzh()     // Catch:{ SQLiteException -> 0x0081, all -> 0x007e }
            java.lang.String r3 = "remote_config"
            java.lang.String r4 = "config_last_modified_time"
            java.lang.String[] r4 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0081, all -> 0x007e }
            java.lang.String[] r6 = new java.lang.String[]{r11}     // Catch:{ SQLiteException -> 0x0081, all -> 0x007e }
            java.lang.String r3 = "apps"
            java.lang.String r5 = "app_id=?"
            r8 = 0
            r9 = 0
            r7 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0081, all -> 0x007e }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x006d }
            if (r3 != 0) goto L_0x0047
        L_0x0042:
            r2.close()
        L_0x0045:
            r5 = r1
            goto L_0x009a
        L_0x0047:
            r3 = 0
            byte[] r3 = r2.getBlob(r3)     // Catch:{ SQLiteException -> 0x006d }
            r4 = 1
            java.lang.String r4 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x006d }
            boolean r5 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x006d }
            if (r5 == 0) goto L_0x006f
            com.google.android.gms.measurement.internal.zzft r5 = r0.zzs     // Catch:{ SQLiteException -> 0x006d }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzay()     // Catch:{ SQLiteException -> 0x006d }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzd()     // Catch:{ SQLiteException -> 0x006d }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzej.zzn(r11)     // Catch:{ SQLiteException -> 0x006d }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x006d }
            goto L_0x006f
        L_0x006b:
            r11 = move-exception
            goto L_0x007b
        L_0x006d:
            r3 = move-exception
            goto L_0x0084
        L_0x006f:
            if (r3 != 0) goto L_0x0072
            goto L_0x0042
        L_0x0072:
            android.util.Pair r5 = new android.util.Pair     // Catch:{ SQLiteException -> 0x006d }
            r5.<init>(r3, r4)     // Catch:{ SQLiteException -> 0x006d }
            r2.close()
            goto L_0x009a
        L_0x007b:
            r1 = r2
            goto L_0x00f9
        L_0x007e:
            r11 = move-exception
            goto L_0x00f9
        L_0x0081:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L_0x0084:
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ all -> 0x006b }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()     // Catch:{ all -> 0x006b }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzd()     // Catch:{ all -> 0x006b }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzej.zzn(r11)     // Catch:{ all -> 0x006b }
            r0.zzc(r4, r5, r3)     // Catch:{ all -> 0x006b }
            if (r2 == 0) goto L_0x0045
            goto L_0x0042
        L_0x009a:
            if (r5 != 0) goto L_0x00bb
            java.util.Map r0 = r10.zze
            r0.put(r11, r1)
            java.util.Map r0 = r10.zza
            r0.put(r11, r1)
            java.util.Map r0 = r10.zzb
            r0.put(r11, r1)
            java.util.Map r0 = r10.zzg
            r0.put(r11, r1)
            java.util.Map r0 = r10.zzi
            r0.put(r11, r1)
            java.util.Map r0 = r10.zzh
            r0.put(r11, r1)
            return
        L_0x00bb:
            java.lang.Object r0 = r5.first
            byte[] r0 = (byte[]) r0
            com.google.android.gms.internal.measurement.zzfc r0 = r10.zzr(r11, r0)
            com.google.android.gms.internal.measurement.zzjv r0 = r0.zzbt()
            com.google.android.gms.internal.measurement.zzfb r0 = (com.google.android.gms.internal.measurement.zzfb) r0
            r10.zzs(r11, r0)
            java.util.Map r1 = r10.zze
            com.google.android.gms.internal.measurement.zzjz r2 = r0.zzay()
            com.google.android.gms.internal.measurement.zzfc r2 = (com.google.android.gms.internal.measurement.zzfc) r2
            java.util.Map r2 = zzv(r2)
            r1.put(r11, r2)
            java.util.Map r1 = r10.zzg
            com.google.android.gms.internal.measurement.zzjz r2 = r0.zzay()
            com.google.android.gms.internal.measurement.zzfc r2 = (com.google.android.gms.internal.measurement.zzfc) r2
            r1.put(r11, r2)
            com.google.android.gms.internal.measurement.zzjz r0 = r0.zzay()
            com.google.android.gms.internal.measurement.zzfc r0 = (com.google.android.gms.internal.measurement.zzfc) r0
            r10.zzu(r11, r0)
            java.util.Map r0 = r10.zzi
            java.lang.Object r1 = r5.second
            java.lang.String r1 = (java.lang.String) r1
            r0.put(r11, r1)
            return
        L_0x00f9:
            if (r1 == 0) goto L_0x00fe
            r1.close()
        L_0x00fe:
            throw r11
        L_0x00ff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfk.zzt(java.lang.String):void");
    }

    @WorkerThread
    private void zzu(String str, zzfc zzfc) {
        if (0 != zzfc.zza()) {
            this.zzs.zzay().zzj().zzb("EES programs found", Integer.valueOf(zzfc.zza()));
            zzgo zzgo = (zzgo) zzfc.zzj().get(0);
            try {
                zzc zzc2 = new zzc();
                zzc2.zzd("internal.remoteConfig", new zzfe(this, str));
                zzc2.zzd("internal.appMetadata", new zzfg(this, str));
                zzc2.zzd("internal.logger", new zzfd(this));
                zzc2.zzc(zzgo);
                this.zzc.put(str, zzc2);
                this.zzs.zzay().zzj().zzc("EES program loaded for appId, activities", str, Integer.valueOf(zzgo.zza().zza()));
                for (zzgm zzb2 : zzgo.zza().zzd()) {
                    this.zzs.zzay().zzj().zzb("EES program activity", zzb2.zzb());
                }
            } catch (zzd unused) {
                this.zzs.zzay().zzd().zzb("Failed to load EES program. appId", str);
            }
        } else {
            this.zzc.remove(str);
        }
    }

    private static Map zzv(zzfc zzfc) {
        ArrayMap arrayMap = new ArrayMap();
        if (null != zzfc) {
            for (zzfe zzfe : zzfc.zzk()) {
                arrayMap.put(zzfe.zzb(), zzfe.zzc());
            }
        }
        return arrayMap;
    }

    @WorkerThread
    public String zza(String str, String str2) {
        zzg();
        zzt(str);
        Map map = (Map) this.zze.get(str);
        if (null != map) {
            return (String) map.get(str2);
        }
        return null;
    }


    public boolean zzb() {
        return false;
    }


    @WorkerThread
    public int zzc(String str, String str2) {
        Integer num;
        zzg();
        zzt(str);
        Map map = (Map) this.zzh.get(str);
        if (null == map || null == (num = (Integer) map.get(str2))) {
            return 1;
        }
        return num.intValue();
    }


    @WorkerThread
    public zzfc zze(String str) {
        zzW();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzt(str);
        return (zzfc) this.zzg.get(str);
    }


    @WorkerThread
    public String zzf(String str) {
        zzg();
        return (String) this.zzi.get(str);
    }


    @WorkerThread
    public void zzi(String str) {
        zzg();
        this.zzi.put(str, null);
    }


    @WorkerThread
    public void zzj(String str) {
        zzg();
        this.zzg.remove(str);
    }


    @WorkerThread
    public boolean zzk(String str) {
        zzg();
        zzfc zze2 = zze(str);
        if (null == zze2) {
            return false;
        }
        return zze2.zzo();
    }

    public boolean zzl(String str) {
        zzfc zzfc;
        return !TextUtils.isEmpty(str) && null != (zzfc = (zzfc) zzg.get(str)) && 0 != zzfc.zza();
    }


    public boolean zzm(String str) {
        return BuildConfig.NETSIS_DEMO_PASSWORD.equals(zza(str, "measurement.upload.blacklist_internal"));
    }


    @WorkerThread
    public boolean zzn(String str, String str2) {
        Boolean bool;
        zzg();
        zzt(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = (Map) this.zzb.get(str);
        if (null == map || null == (bool = (Boolean) map.get(str2))) {
            return false;
        }
        return bool.booleanValue();
    }


    @WorkerThread
    public boolean zzo(String str, String str2) {
        Boolean bool;
        zzg();
        zzt(str);
        if (zzm(str) && zzky.zzag(str2)) {
            return true;
        }
        if (zzp(str) && zzky.zzah(str2)) {
            return true;
        }
        Map map = (Map) this.zza.get(str);
        if (null == map || null == (bool = (Boolean) map.get(str2))) {
            return false;
        }
        return bool.booleanValue();
    }


    public boolean zzp(String str) {
        return BuildConfig.NETSIS_DEMO_PASSWORD.equals(zza(str, "measurement.upload.blacklist_public"));
    }


    @WorkerThread
    public boolean zzq(String str, byte[] bArr, String str2) {
        zzW();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzfb zzfb = (zzfb) zzr(str, bArr).zzbt();
        if (null == zzfb) {
            return false;
        }
        zzs(str, zzfb);
        zzu(str, (zzfc) zzfb.zzay());
        this.zzg.put(str, zzfb.zzay());
        this.zzi.put(str, str2);
        this.zze.put(str, zzv((zzfc) zzfb.zzay()));
        this.zzf.zzi().zzB(str, new ArrayList(zzfb.zze()));
        try {
            zzfb.zzc();
            bArr = zzfb.zzay().zzbq();
        } catch (RuntimeException e2) {
            this.zzs.zzay().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzej.zzn(str), e2);
        }
        zzak zzi2 = this.zzf.zzi();
        Preconditions.checkNotEmpty(str);
        zzi2.zzg();
        zzi2.zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        try {
            if (0 == ((long) zzi2.zzh().update("apps", contentValues, "app_id = ?", new String[]{str}))) {
                zzi2.zzs.zzay().zzd().zzb("Failed to update remote config (got 0). appId", zzej.zzn(str));
            }
        } catch (SQLiteException e3) {
            zzi2.zzs.zzay().zzd().zzc("Error storing remote config. appId", zzej.zzn(str), e3);
        }
        this.zzg.put(str, zzfb.zzay());
        return true;
    }
}
