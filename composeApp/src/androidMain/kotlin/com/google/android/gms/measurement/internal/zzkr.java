package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzfc;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzna;
import com.google.android.gms.internal.measurement.zznv;
import com.google.android.gms.internal.measurement.zzpi;
import com.google.firebase.messaging.Constants;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzkr implements zzgo {
    private static volatile zzkr zzb;
    private long zzA;
    private final Map zzB;
    private zzif zzC;
    private String zzD;
    private final zzkx zzE = new zzko(this);

    long zza;
    private final zzfk zzc;
    private final zzep zzd;
    private zzak zze;
    private zzer zzf;
    private zzkf zzg;
    private zzz zzh;
    private final zzkt zzi;
    private zzid zzj;
    private zzjo zzk;
    private final zzki zzl;
    private zzfb zzm;
    
    public final zzft zzn;
    private boolean zzo;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;

    zzkr(final zzks zzks, final zzft zzft) {
        Preconditions.checkNotNull(zzks);
        zzn = com.google.android.gms.measurement.internal.zzft.zzp(zzks.zza, null, null);
        zzA = -1;
        zzl = new zzki(this);
        final zzkt zzkt = new zzkt(this);
        zzkt.zzX();
        zzi = zzkt;
        final zzep zzep = new zzep(this);
        zzep.zzX();
        zzd = zzep;
        final zzfk zzfk = new zzfk(this);
        zzfk.zzX();
        zzc = zzfk;
        zzB = new HashMap();
        this.zzaz().zzp(new zzkj(this, zzks));
    }

    static void zzZ(final zzfn zzfn, final int i2, final String str) {
        final List zzp2 = zzfn.zzp();
        int i3 = 0;
        while (i3 < zzp2.size()) {
            if (!"_err".equals(((zzfs) zzp2.get(i3)).zzg())) {
                i3++;
            } else {
                return;
            }
        }
        final zzfr zze2 = zzfs.zze();
        zze2.zzj("_err");
        zze2.zzi(i2);
        final zzfr zze3 = zzfs.zze();
        zze3.zzj("_ev");
        zze3.zzk(str);
        zzfn.zzf((zzfs) zze2.zzay());
        zzfn.zzf((zzfs) zze3.zzay());
    }
    static void zzaa(final zzfn zzfn, @NonNull final String str) {
        final List zzp2 = zzfn.zzp();
        for (int i2 = 0; i2 < zzp2.size(); i2++) {
            if (str.equals(((zzfs) zzp2.get(i2)).zzg())) {
                zzfn.zzh(i2);
                return;
            }
        }
    }
    private zzp zzab(final String str) {
        final String str2 = str;
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        final zzg zzj2 = zzak.zzj(str2);
        if (null == zzj2 || TextUtils.isEmpty(zzj2.zzw())) {
            this.zzay().zzc().zzb("No app data available; dropping", str2);
            return null;
        }
        final Boolean zzac = this.zzac(zzj2);
        if (null == zzac || zzac.booleanValue()) {
            return new zzp(str, zzj2.zzy(), zzj2.zzw(), zzj2.zzb(), zzj2.zzv(), zzj2.zzm(), zzj2.zzj(), null, zzj2.zzah(), false, zzj2.zzx(), zzj2.zza(), 0, 0, zzj2.zzag(), false, zzj2.zzr(), zzj2.zzq(), zzj2.zzk(), zzj2.zzB(), null, this.zzh(str).zzh());
        }
        this.zzay().zzd().zzb("App version does not match; dropping. appId", zzej.zzn(str));
        return null;
    }
    private Boolean zzac(final zzg zzg2) {
        try {
            if (-2147483648L != zzg2.zzb()) {
                if (zzg2.zzb() == Wrappers.packageManager(zzn.zzau()).getPackageInfo(zzg2.zzt(), 0).versionCode) {
                    return Boolean.TRUE;
                }
            } else {
                final String str = Wrappers.packageManager(zzn.zzau()).getPackageInfo(zzg2.zzt(), 0).versionName;
                final String zzw2 = zzg2.zzw();
                if (null != zzw2 && zzw2.equals(str)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (final PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
    private void zzad() {
        this.zzaz().zzg();
        if (zzt || zzu || zzv) {
            this.zzay().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(zzt), Boolean.valueOf(zzu), Boolean.valueOf(zzv));
            return;
        }
        this.zzay().zzj().zza("Stopping uploading service(s)");
        final List<Runnable> list = zzq;
        if (null != list) {
            for (final Runnable run : list) {
                run.run();
            }
            Preconditions.checkNotNull(zzq).clear();
        }
    }
    private void zzae(final zzfx zzfx, final long j2, final boolean z) {
        final String str;
        final zzkw zzkw;
        final String str2;
        if (!z) {
            str = "_lte";
        } else {
            str = "_se";
        }
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        final zzkw zzp2 = zzak.zzp(zzfx.zzak(), str);
        if (null == zzp2 || null == zzp2.zze()) {
            zzkw = new zzkw(zzfx.zzak(), "auto", str, this.zzav().currentTimeMillis(), Long.valueOf(j2)) {
                @Override
                public void zza(com.google.android.gms.internal.measurement.zzdd zzdd, Bundle bundle) {

                }

                @Override
                public void zzb(com.google.android.gms.internal.measurement.zzdd zzdd) {

                }

                @Override
                public void zzc(com.google.android.gms.internal.measurement.zzdd zzdd) {

                }

                @Override
                public void zzd(com.google.android.gms.internal.measurement.zzdd zzdd) {

                }

                @Override
                public void zze(com.google.android.gms.internal.measurement.zzdd zzdd, Bundle bundle) {

                }
            };
        } else {
            zzkw = new zzkw(zzfx.zzak(), "auto", str, this.zzav().currentTimeMillis(), Long.valueOf(((Long) zzp2.zze()).longValue() + j2));
        }
        final zzgg zzd2 = zzgh.zzd();
        zzd2.zzf(str);
        zzd2.zzg(this.zzav().currentTimeMillis());
        zzd2.zze(((Long) zzkw.zze()).longValue());
        final zzgh zzgh = (zzgh) zzd2.zzay();
        final int zza2 = zzkt.zza(zzfx, str);
        if (0 <= zza2) {
            zzfx.zzah(zza2, zzgh);
        } else {
            zzfx.zzl(zzgh);
        }
        if (0 < j2) {
            final zzak zzak2 = zze;
            zzkr.zzak(zzak2);
            zzak2.zzL(zzkw);
            if (!z) {
                str2 = "lifetime";
            } else {
                str2 = "session-scoped";
            }
            this.zzay().zzj().zzc("Updated engagement user property. scope, value", str2, zzkw.zze());
        }
    }

    private void zzaf() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzaf():void");
    }

    private boolean zzag(final java.lang.String r41, final long r42) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzag(java.lang.String, long):boolean");
    }

    private boolean zzah() {
        this.zzaz().zzg();
        this.zzB();
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        if (zzak.zzF()) {
            return true;
        }
        final zzak zzak2 = zze;
        zzkr.zzak(zzak2);
        return !TextUtils.isEmpty(zzak2.zzr());
    }

    private boolean zzai(final zzfn zzfn, final zzfn zzfn2) {
        Preconditions.checkArgument("_e".equals(zzfn.zzo()));
        zzkr.zzak(zzi);
        final zzfs zzB2 = zzkt.zzB((zzfo) zzfn.zzay(), "_sc");
        String str = null;
        final String zzh2 = null == zzB2 ? null : zzB2.zzh();
        zzkr.zzak(zzi);
        final zzfs zzB3 = zzkt.zzB((zzfo) zzfn2.zzay(), "_pc");
        if (null != zzB3) {
            str = zzB3.zzh();
        }
        if (null == str || !str.equals(zzh2)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfn.zzo()));
        zzkr.zzak(zzi);
        final zzfs zzB4 = zzkt.zzB((zzfo) zzfn.zzay(), "_et");
        if (null == zzB4 || !zzB4.zzw() || 0 >= zzB4.zzd()) {
            return true;
        }
        long zzd2 = zzB4.zzd();
        zzkr.zzak(zzi);
        final zzfs zzB5 = zzkt.zzB((zzfo) zzfn2.zzay(), "_et");
        if (null != zzB5 && 0 < zzB5.zzd()) {
            zzd2 += zzB5.zzd();
        }
        zzkr.zzak(zzi);
        zzkt.zzz(zzfn2, "_et", Long.valueOf(zzd2));
        zzkr.zzak(zzi);
        zzkt.zzz(zzfn, "_fr", 1L);
        return true;
    }

    private static boolean zzaj(final zzp zzp2) {
        return !TextUtils.isEmpty(zzp2.zzb) || !TextUtils.isEmpty(zzp2.zzq);
    }

    private static zzkh zzak(final zzkh zzkh) {
        if (null == zzkh) {
            throw new IllegalStateException("Upload Component not created");
        } else if (zzkh.zzY()) {
            return zzkh;
        } else {
            throw new IllegalStateException("Component not initialized: " + zzkh.getClass());
        }
    }

    public static zzkr zzt(final Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (null == zzb) {
            synchronized (zzkr.class) {
                try {
                    if (null == zzb) {
                        zzkr.zzb = new zzkr(Preconditions.checkNotNull(new zzks(context)), null);
                    }
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
        return zzkr.zzb;
    }

    static void zzy(final zzkr zzkr, final zzks zzks) {
        zzkr.zzaz().zzg();
        zzkr.zzm = new zzfb(zzkr);
        final zzak zzak = new zzak(zzkr);
        zzak.zzX();
        zzkr.zze = zzak;
        zzkr.zzg().zzq(Preconditions.checkNotNull(zzkr.zzc));
        final zzjo zzjo = new zzjo(zzkr);
        zzjo.zzX();
        zzkr.zzk = zzjo;
        final zzz zzz2 = new zzz(zzkr);
        zzz2.zzX();
        zzkr.zzh = zzz2;
        final zzid zzid = new zzid(zzkr);
        zzid.zzX();
        zzkr.zzj = zzid;
        final zzkf zzkf = new zzkf(zzkr);
        zzkf.zzX();
        zzkr.zzg = zzkf;
        zzkr.zzf = new zzer(zzkr);
        if (zzkr.zzr != zzkr.zzs) {
            zzkr.zzay().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzkr.zzr), Integer.valueOf(zzkr.zzs));
        }
        zzkr.zzo = true;
    }
    public void zzA() {
        this.zzaz().zzg();
        this.zzB();
        if (!zzp) {
            zzp = true;
            if (this.zzY()) {
                final FileChannel fileChannel = zzx;
                this.zzaz().zzg();
                int i2 = 0;
                if (null == fileChannel || !fileChannel.isOpen()) {
                    this.zzay().zzd().zza("Bad channel to read from");
                } else {
                    final ByteBuffer allocate = ByteBuffer.allocate(4);
                    try {
                        fileChannel.position(0);
                        final int read = fileChannel.read(allocate);
                        if (4 == read) {
                            allocate.flip();
                            i2 = allocate.getInt();
                        } else if (-1 != read) {
                            this.zzay().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(read));
                        }
                    } catch (final IOException e2) {
                        this.zzay().zzd().zzb("Failed to read from channel", e2);
                    }
                }
                final int zzi2 = zzn.zzh().zzi();
                this.zzaz().zzg();
                if (i2 > zzi2) {
                    this.zzay().zzd().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i2), Integer.valueOf(zzi2));
                } else if (i2 < zzi2) {
                    final FileChannel fileChannel2 = zzx;
                    this.zzaz().zzg();
                    if (null == fileChannel2 || !fileChannel2.isOpen()) {
                        this.zzay().zzd().zza("Bad channel to read from");
                    } else {
                        final ByteBuffer allocate2 = ByteBuffer.allocate(4);
                        allocate2.putInt(zzi2);
                        allocate2.flip();
                        try {
                            fileChannel2.truncate(0);
                            this.zzg().zzs(null, zzdw.zzaf);
                            fileChannel2.write(allocate2);
                            fileChannel2.force(true);
                            if (4 != fileChannel2.size()) {
                                this.zzay().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                            }
                            this.zzay().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i2), Integer.valueOf(zzi2));
                            return;
                        } catch (final IOException e3) {
                            this.zzay().zzd().zzb("Failed to write to channel", e3);
                        }
                    }
                    this.zzay().zzd().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i2), Integer.valueOf(zzi2));
                }
            }
        }
    }

    
    public void zzB() {
        if (!zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }
    public void zzC(final zzg zzg2) {
        final String str;
        this.zzaz().zzg();
        if (!TextUtils.isEmpty(zzg2.zzy()) || !TextUtils.isEmpty(zzg2.zzr())) {
            final zzki zzki = zzl;
            final Uri.Builder builder = new Uri.Builder();
            String zzy2 = zzg2.zzy();
            if (TextUtils.isEmpty(zzy2)) {
                zzy2 = zzg2.zzr();
            }
            ArrayMap arrayMap = null;
            final Uri.Builder encodedAuthority = builder.scheme((String) zzdw.zzd.zza(null)).encodedAuthority((String) zzdw.zze.zza(null));
            final String valueOf = String.valueOf(zzy2);
            if (0 != valueOf.length()) {
                str = "config/app/" + valueOf;
            } else {
                str = "config/app/";
            }
            final Uri.Builder appendQueryParameter = encodedAuthority.path(str).appendQueryParameter("app_instance_id", zzg2.zzu()).appendQueryParameter("platform", "android");
            zzki.zzs.zzf().zzh();
            appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(60000)).appendQueryParameter("runtime_version", "0");
            final String uri = builder.build().toString();
            try {
                final String str2 = Preconditions.checkNotNull(zzg2.zzt());
                final URL url = new URL(uri);
                this.zzay().zzj().zzb("Fetching remote configuration", str2);
                final zzfk zzfk = zzc;
                zzkr.zzak(zzfk);
                final zzfc zze2 = zzfk.zze(str2);
                final zzfk zzfk2 = zzc;
                zzkr.zzak(zzfk2);
                final String zzf2 = zzfk2.zzf(str2);
                if (null != zze2 && !TextUtils.isEmpty(zzf2)) {
                    arrayMap = new ArrayMap();
                    arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zzf2);
                }
                zzt = true;
                final zzep zzep = zzd;
                zzkr.zzak(zzep);
                final zzkl zzkl = new zzkl(this);
                zzep.zzg();
                zzep.zzW();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzkl);
                zzep.zzs.zzaz().zzo(new zzeo(zzep, str2, url, null, arrayMap, zzkl));
            } catch (final MalformedURLException unused) {
                this.zzay().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzej.zzn(zzg2.zzt()), uri);
            }
        } else {
            this.zzH(Preconditions.checkNotNull(zzg2.zzt()), 204, null, null, null);
        }
    }
    public void zzD(final zzau zzau, final zzp zzp2) {
        final zzau zzau2;
        final List<zzab> list;
        final List<zzab> list2;
        final List<zzab> list3;
        final String str;
        final zzp zzp3 = zzp2;
        Preconditions.checkNotNull(zzp2);
        Preconditions.checkNotEmpty(zzp3.zza);
        this.zzaz().zzg();
        this.zzB();
        final String str2 = zzp3.zza;
        zzau zzau3 = zzau;
        final long j2 = zzau3.zzd;
        zzpi.zzc();
        zzif zzif = null;
        if (this.zzg().zzs(null, zzdw.zzat)) {
            final zzek zzb2 = zzek.zzb(zzau);
            this.zzaz().zzg();
            if (!(null == this.zzC || null == (str = this.zzD) || !str.equals(str2))) {
                zzif = zzC;
            }
            zzky.zzJ(zzif, zzb2.zzd, false);
            zzau3 = zzb2.zza();
        }
        zzkr.zzak(zzi);
        if (zzkt.zzA(zzau3, zzp3)) {
            if (!zzp3.zzh) {
                this.zzd(zzp3);
                return;
            }
            final List list4 = zzp3.zzt;
            if (null == list4) {
                zzau2 = zzau3;
            } else if (list4.contains(zzau3.zza)) {
                final Bundle zzc2 = zzau3.zzb.zzc();
                zzc2.putLong("ga_safelisted", 1);
                zzau2 = new zzau(zzau3.zza, new zzas(zzc2), zzau3.zzc, zzau3.zzd);
            } else {
                this.zzay().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str2, zzau3.zza, zzau3.zzc);
                return;
            }
            final zzak zzak = zze;
            zzkr.zzak(zzak);
            zzak.zzw();
            try {
                final zzak zzak2 = zze;
                zzkr.zzak(zzak2);
                Preconditions.checkNotEmpty(str2);
                zzak2.zzg();
                zzak2.zzW();
                final int i2 = (0 < j2 ? 1 : (0 == j2 ? 0 : -1));
                if (0 > i2) {
                    zzak2.zzs.zzay().zzk().zzc("Invalid time querying timed out conditional properties", zzej.zzn(str2), Long.valueOf(j2));
                    list = Collections.emptyList();
                } else {
                    list = zzak2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j2)});
                }
                for (final zzab zzab : list) {
                    if (null != zzab) {
                        this.zzay().zzj().zzd("User property timed out", zzab.zza, zzn.zzj().zzf(zzab.zzc.zzb), zzab.zzc.zza());
                        final zzau zzau4 = zzab.zzg;
                        if (null != zzau4) {
                            this.zzX(new zzau(zzau4, j2), zzp3);
                        }
                        final zzak zzak3 = zze;
                        zzkr.zzak(zzak3);
                        zzak3.zza(str2, zzab.zzc.zzb);
                    }
                }
                final zzak zzak4 = zze;
                zzkr.zzak(zzak4);
                Preconditions.checkNotEmpty(str2);
                zzak4.zzg();
                zzak4.zzW();
                if (0 > i2) {
                    zzak4.zzs.zzay().zzk().zzc("Invalid time querying expired conditional properties", zzej.zzn(str2), Long.valueOf(j2));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzak4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j2)});
                }
                final ArrayList<zzau> arrayList = new ArrayList<>(list2.size());
                for (final zzab zzab2 : list2) {
                    if (null != zzab2) {
                        this.zzay().zzj().zzd("User property expired", zzab2.zza, zzn.zzj().zzf(zzab2.zzc.zzb), zzab2.zzc.zza());
                        final zzak zzak5 = zze;
                        zzkr.zzak(zzak5);
                        zzak5.zzA(str2, zzab2.zzc.zzb);
                        final zzau zzau5 = zzab2.zzk;
                        if (null != zzau5) {
                            arrayList.add(zzau5);
                        }
                        final zzak zzak6 = zze;
                        zzkr.zzak(zzak6);
                        zzak6.zza(str2, zzab2.zzc.zzb);
                    }
                }
                for (final zzau zzau6 : arrayList) {
                    this.zzX(new zzau(zzau6, j2), zzp3);
                }
                final zzak zzak7 = zze;
                zzkr.zzak(zzak7);
                final String str3 = zzau2.zza;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzak7.zzg();
                zzak7.zzW();
                if (0 > i2) {
                    zzak7.zzs.zzay().zzk().zzd("Invalid time querying triggered conditional properties", zzej.zzn(str2), zzak7.zzs.zzj().zzd(str3), Long.valueOf(j2));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzak7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j2)});
                }
                final ArrayList<zzau> arrayList2 = new ArrayList<>(list3.size());
                for (final zzab zzab3 : list3) {
                    if (null != zzab3) {
                        final zzku zzku = zzab3.zzc;
                        final zzkw zzkw = new zzkw(Preconditions.checkNotNull(zzab3.zza), zzab3.zzb, zzku.zzb, j2, Preconditions.checkNotNull(zzku.zza()));
                        final zzak zzak8 = zze;
                        zzkr.zzak(zzak8);
                        if (zzak8.zzL(zzkw)) {
                            this.zzay().zzj().zzd("User property triggered", zzab3.zza, zzn.zzj().zzf(zzkw.zzc()), zzkw.zze());
                        } else {
                            this.zzay().zzd().zzd("Too many active user properties, ignoring", zzej.zzn(zzab3.zza), zzn.zzj().zzf(zzkw.zzc()), zzkw.zze());
                        }
                        final zzau zzau7 = zzab3.zzi;
                        if (null != zzau7) {
                            arrayList2.add(zzau7);
                        }
                        zzab3.zzc = new zzku(zzkw);
                        zzab3.zze = true;
                        final zzak zzak9 = zze;
                        zzkr.zzak(zzak9);
                        zzak9.zzK(zzab3);
                    }
                }
                this.zzX(zzau2, zzp3);
                for (final zzau zzau8 : arrayList2) {
                    this.zzX(new zzau(zzau8, j2), zzp3);
                }
                final zzak zzak10 = zze;
                zzkr.zzak(zzak10);
                zzak10.zzC();
                final zzak zzak11 = zze;
                zzkr.zzak(zzak11);
                zzak11.zzx();
            } catch (final Throwable th) {
                final zzak zzak12 = zze;
                zzkr.zzak(zzak12);
                zzak12.zzx();
                throw th;
            }
        }
    }

    
    @WorkerThread
    public void zzE(final zzau zzau, final String str) {
        final zzau zzau2 = zzau;
        final String str2 = str;
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        final zzg zzj2 = zzak.zzj(str2);
        if (null == zzj2 || TextUtils.isEmpty(zzj2.zzw())) {
            this.zzay().zzc().zzb("No app data available; dropping event", str2);
            return;
        }
        final Boolean zzac = this.zzac(zzj2);
        if (null == zzac) {
            if (!"_ui".equals(zzau2.zza)) {
                this.zzay().zzk().zzb("Could not find package. appId", zzej.zzn(str));
            }
        } else if (!zzac.booleanValue()) {
            this.zzay().zzd().zzb("App version does not match; dropping event. appId", zzej.zzn(str));
            return;
        }
        final zzp zzp2 = r2;
        final zzp zzp3 = new zzp(str, zzj2.zzy(), zzj2.zzw(), zzj2.zzb(), zzj2.zzv(), zzj2.zzm(), zzj2.zzj(), null, zzj2.zzah(), false, zzj2.zzx(), zzj2.zza(), 0, 0, zzj2.zzag(), false, zzj2.zzr(), zzj2.zzq(), zzj2.zzk(), zzj2.zzB(), null, this.zzh(str2).zzh());
        this.zzF(zzau2, zzp2);
    }

    
    @WorkerThread
    public void zzF(final zzau zzau, final zzp zzp2) {
        Preconditions.checkNotEmpty(zzp2.zza);
        final zzek zzb2 = zzek.zzb(zzau);
        final zzky zzv2 = this.zzv();
        final Bundle bundle = zzb2.zzd;
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        zzv2.zzK(bundle, zzak.zzi(zzp2.zza));
        this.zzv().zzL(zzb2, this.zzg().zzd(zzp2.zza));
        final zzau zza2 = zzb2.zza();
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zza2.zza) && "referrer API v2".equals(zza2.zzb.zzg("_cis"))) {
            final String zzg2 = zza2.zzb.zzg("gclid");
            if (!TextUtils.isEmpty(zzg2)) {
                this.zzV(new zzku("_lgclid", zza2.zzd, zzg2, "auto"), zzp2);
            }
        }
        this.zzD(zza2, zzp2);
    }

    
    public void zzG() {
        zzs++;
    }

    public void zzH(final java.lang.String r7, final int r8, final java.lang.Throwable r9, final byte[] r10, final java.util.Map r11) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzH(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    
    public void zzI(final boolean z) {
        this.zzaf();
    }

    public void zzJ(int i2, final Throwable th, byte[] bArr, final String str) {
        zzak zzak;
        this.zzaz().zzg();
        this.zzB();
        if (null == bArr) {
            try {
                bArr = new byte[0];
            } catch (final Throwable th2) {
                zzu = false;
                this.zzad();
                throw th2;
            }
        }
        final List<Long> list = (List) Preconditions.checkNotNull(zzy);
        zzy = null;
        if (200 != i2) {
            if (204 == i2) {
                i2 = 204;
            }
            this.zzay().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i2), th);
            zzk.zzd.zzb(this.zzav().currentTimeMillis());
            if (503 == i2 || 429 == i2) {
                zzk.zzb.zzb(this.zzav().currentTimeMillis());
            }
            final zzak zzak2 = zze;
            zzkr.zzak(zzak2);
            zzak2.zzy(list);
            this.zzaf();
            zzu = false;
            this.zzad();
        }
        if (null == th) {
            try {
                zzk.zzc.zzb(this.zzav().currentTimeMillis());
                zzk.zzd.zzb(0);
                this.zzaf();
                this.zzay().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i2), Integer.valueOf(bArr.length));
                final zzak zzak3 = zze;
                zzkr.zzak(zzak3);
                zzak3.zzw();
                for (final Long l : list) {
                    try {
                        zzak = zze;
                        zzkr.zzak(zzak);
                        final long longValue = l.longValue();
                        zzak.zzg();
                        zzak.zzW();
                        if (1 != zzak.zzh().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)})) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                        }
                    } catch (final SQLiteException e2) {
                        zzak.zzs.zzay().zzd().zzb("Failed to delete a bundle in a queue table", e2);
                        throw e2;
                    } catch (final SQLiteException e3) {
                        final List list2 = zzz;
                        if (null == list2 || !list2.contains(l)) {
                            throw e3;
                        }
                    }
                }
                final zzak zzak4 = zze;
                zzkr.zzak(zzak4);
                zzak4.zzC();
                final zzak zzak5 = zze;
                zzkr.zzak(zzak5);
                zzak5.zzx();
                zzz = null;
                final zzep zzep = zzd;
                zzkr.zzak(zzep);
                if (!zzep.zza() || !this.zzah()) {
                    zzA = -1;
                    this.zzaf();
                } else {
                    this.zzW();
                }
                zza = 0;
            } catch (final SQLiteException e4) {
                this.zzay().zzd().zzb("Database error while trying to delete uploaded bundles", e4);
                zza = this.zzav().elapsedRealtime();
                this.zzay().zzj().zzb("Disable upload, time", Long.valueOf(zza));
            } catch (final Throwable th3) {
                final zzak zzak6 = zze;
                zzkr.zzak(zzak6);
                zzak6.zzx();
                throw th3;
            }
            zzu = false;
            this.zzad();
        }
        this.zzay().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i2), th);
        zzk.zzd.zzb(this.zzav().currentTimeMillis());
        zzk.zzb.zzb(this.zzav().currentTimeMillis());
        final zzak zzak22 = zze;
        zzkr.zzak(zzak22);
        zzak22.zzy(list);
        this.zzaf();
        zzu = false;
        this.zzad();
    }

    public void zzK(final com.google.android.gms.measurement.internal.zzp r24) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzK(com.google.android.gms.measurement.internal.zzp):void");
    }
    public void zzL() {
        zzr++;
    }
    public void zzM(final zzab zzab) {
        final zzp zzab2 = this.zzab(Preconditions.checkNotNull(zzab.zza));
        if (null != zzab2) {
            this.zzN(zzab, zzab2);
        }
    }
    public void zzN(final zzab zzab, final zzp zzp2) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotEmpty(zzab.zza);
        Preconditions.checkNotNull(zzab.zzc);
        Preconditions.checkNotEmpty(zzab.zzc.zzb);
        this.zzaz().zzg();
        this.zzB();
        if (zzkr.zzaj(zzp2)) {
            if (zzp2.zzh) {
                final zzak zzak = zze;
                zzkr.zzak(zzak);
                zzak.zzw();
                try {
                    this.zzd(zzp2);
                    final String str = Preconditions.checkNotNull(zzab.zza);
                    final zzak zzak2 = zze;
                    zzkr.zzak(zzak2);
                    final zzab zzk2 = zzak2.zzk(str, zzab.zzc.zzb);
                    if (null != zzk2) {
                        this.zzay().zzc().zzc("Removing conditional user property", zzab.zza, zzn.zzj().zzf(zzab.zzc.zzb));
                        final zzak zzak3 = zze;
                        zzkr.zzak(zzak3);
                        zzak3.zza(str, zzab.zzc.zzb);
                        if (zzk2.zze) {
                            final zzak zzak4 = zze;
                            zzkr.zzak(zzak4);
                            zzak4.zzA(str, zzab.zzc.zzb);
                        }
                        final zzau zzau = zzab.zzk;
                        if (null != zzau) {
                            final zzas zzas = zzau.zzb;
                            this.zzX(Preconditions.checkNotNull(this.zzv().zzz(str, Preconditions.checkNotNull(zzab.zzk).zza, null != zzas ? zzas.zzc() : null, zzk2.zzb, zzab.zzk.zzd, true, true)), zzp2);
                        }
                    } else {
                        this.zzay().zzk().zzc("Conditional user property doesn't exist", zzej.zzn(zzab.zza), zzn.zzj().zzf(zzab.zzc.zzb));
                    }
                    final zzak zzak5 = zze;
                    zzkr.zzak(zzak5);
                    zzak5.zzC();
                    final zzak zzak6 = zze;
                    zzkr.zzak(zzak6);
                    zzak6.zzx();
                } catch (final Throwable th) {
                    final zzak zzak7 = zze;
                    zzkr.zzak(zzak7);
                    zzak7.zzx();
                    throw th;
                }
            } else {
                this.zzd(zzp2);
            }
        }
    }
    public void zzO(final zzku zzku, final zzp zzp2) {
        this.zzaz().zzg();
        this.zzB();
        if (zzkr.zzaj(zzp2)) {
            if (!zzp2.zzh) {
                this.zzd(zzp2);
            } else if (!"_npa".equals(zzku.zzb) || null == zzp2.zzr) {
                this.zzay().zzc().zzb("Removing user property", zzn.zzj().zzf(zzku.zzb));
                final zzak zzak = zze;
                zzkr.zzak(zzak);
                zzak.zzw();
                try {
                    this.zzd(zzp2);
                    zzna.zzc();
                    if (zzn.zzf().zzs(null, zzdw.zzan) && zzn.zzf().zzs(null, zzdw.zzap) && "_id".equals(zzku.zzb)) {
                        final zzak zzak2 = zze;
                        zzkr.zzak(zzak2);
                        zzak2.zzA(Preconditions.checkNotNull(zzp2.zza), "_lair");
                    }
                    final zzak zzak3 = zze;
                    zzkr.zzak(zzak3);
                    zzak3.zzA(Preconditions.checkNotNull(zzp2.zza), zzku.zzb);
                    final zzak zzak4 = zze;
                    zzkr.zzak(zzak4);
                    zzak4.zzC();
                    this.zzay().zzc().zzb("User property removed", zzn.zzj().zzf(zzku.zzb));
                    final zzak zzak5 = zze;
                    zzkr.zzak(zzak5);
                    zzak5.zzx();
                } catch (final Throwable th) {
                    final zzak zzak6 = zze;
                    zzkr.zzak(zzak6);
                    zzak6.zzx();
                    throw th;
                }
            } else {
                this.zzay().zzc().zza("Falling back to manifest metadata value for ad personalization");
                this.zzV(new zzku("_npa", this.zzav().currentTimeMillis(), Long.valueOf(!zzp2.zzr.booleanValue() ? 0 : 1), "auto"), zzp2);
            }
        }
    }

    public void zzP(final zzp zzp2) {
        if (null != this.zzy) {
            final ArrayList arrayList = new ArrayList();
            zzz = arrayList;
            arrayList.addAll(zzy);
        }
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        final String str = Preconditions.checkNotNull(zzp2.zza);
        Preconditions.checkNotEmpty(str);
        zzak.zzg();
        zzak.zzW();
        try {
            final SQLiteDatabase zzh2 = zzak.zzh();
            final String[] strArr = {str};
            final int delete = zzh2.delete("apps", "app_id=?", strArr) + zzh2.delete("events", "app_id=?", strArr) + zzh2.delete("user_attributes", "app_id=?", strArr) + zzh2.delete("conditional_properties", "app_id=?", strArr) + zzh2.delete("raw_events", "app_id=?", strArr) + zzh2.delete("raw_events_metadata", "app_id=?", strArr) + zzh2.delete("queue", "app_id=?", strArr) + zzh2.delete("audience_filter_values", "app_id=?", strArr) + zzh2.delete("main_event_params", "app_id=?", strArr) + zzh2.delete("default_event_params", "app_id=?", strArr);
            if (0 < delete) {
                zzak.zzs.zzay().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (final SQLiteException e2) {
            zzak.zzs.zzay().zzd().zzc("Error resetting analytics data. appId, error", zzej.zzn(str), e2);
        }
        if (zzp2.zzh) {
            this.zzK(zzp2);
        }
    }
    public void zzQ(final String str, final zzif zzif) {
        this.zzaz().zzg();
        final String str2 = zzD;
        if (null == str2 || str2.equals(str) || null != zzif) {
            zzD = str;
            zzC = zzif;
        }
    }
    public void zzR() {
        this.zzaz().zzg();
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        zzak.zzz();
        if (0 == this.zzk.zzc.zza()) {
            zzk.zzc.zzb(this.zzav().currentTimeMillis());
        }
        this.zzaf();
    }
    public void zzS(final zzab zzab) {
        final zzp zzab2 = this.zzab(Preconditions.checkNotNull(zzab.zza));
        if (null != zzab2) {
            this.zzT(zzab, zzab2);
        }
    }
    public void zzT(final zzab zzab, final zzp zzp2) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotEmpty(zzab.zza);
        Preconditions.checkNotNull(zzab.zzb);
        Preconditions.checkNotNull(zzab.zzc);
        Preconditions.checkNotEmpty(zzab.zzc.zzb);
        this.zzaz().zzg();
        this.zzB();
        if (zzkr.zzaj(zzp2)) {
            if (!zzp2.zzh) {
                this.zzd(zzp2);
                return;
            }
            final zzab zzab2 = new zzab(zzab);
            boolean z = false;
            zzab2.zze = false;
            final zzak zzak = zze;
            zzkr.zzak(zzak);
            zzak.zzw();
            try {
                final zzak zzak2 = zze;
                zzkr.zzak(zzak2);
                final zzab zzk2 = zzak2.zzk(Preconditions.checkNotNull(zzab2.zza), zzab2.zzc.zzb);
                if (null != zzk2 && !zzk2.zzb.equals(zzab2.zzb)) {
                    this.zzay().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzn.zzj().zzf(zzab2.zzc.zzb), zzab2.zzb, zzk2.zzb);
                }
                if (null != zzk2 && zzk2.zze) {
                    zzab2.zzb = zzk2.zzb;
                    zzab2.zzd = zzk2.zzd;
                    zzab2.zzh = zzk2.zzh;
                    zzab2.zzf = zzk2.zzf;
                    zzab2.zzi = zzk2.zzi;
                    zzab2.zze = true;
                    final zzku zzku = zzab2.zzc;
                    zzab2.zzc = new zzku(zzku.zzb, zzk2.zzc.zzc, zzku.zza(), zzk2.zzc.zzf);
                } else if (TextUtils.isEmpty(zzab2.zzf)) {
                    final zzku zzku2 = zzab2.zzc;
                    zzab2.zzc = new zzku(zzku2.zzb, zzab2.zzd, zzku2.zza(), zzab2.zzc.zzf);
                    zzab2.zze = true;
                    z = true;
                }
                if (zzab2.zze) {
                    final zzku zzku3 = zzab2.zzc;
                    final zzkw zzkw = new zzkw(Preconditions.checkNotNull(zzab2.zza), zzab2.zzb, zzku3.zzb, zzku3.zzc, Preconditions.checkNotNull(zzku3.zza()));
                    final zzak zzak3 = zze;
                    zzkr.zzak(zzak3);
                    if (zzak3.zzL(zzkw)) {
                        this.zzay().zzc().zzd("User property updated immediately", zzab2.zza, zzn.zzj().zzf(zzkw.zzc()), zzkw.zze());
                    } else {
                        this.zzay().zzd().zzd("(2)Too many active user properties, ignoring", zzej.zzn(zzab2.zza), zzn.zzj().zzf(zzkw.zzc()), zzkw.zze());
                    }
                    if (z && null != zzab2.zzi) {
                        this.zzX(new zzau(zzab2.zzi, zzab2.zzd), zzp2);
                    }
                }
                final zzak zzak4 = zze;
                zzkr.zzak(zzak4);
                if (zzak4.zzK(zzab2)) {
                    this.zzay().zzc().zzd("Conditional property added", zzab2.zza, zzn.zzj().zzf(zzab2.zzc.zzb), zzab2.zzc.zza());
                } else {
                    this.zzay().zzd().zzd("Too many conditional properties, ignoring", zzej.zzn(zzab2.zza), zzn.zzj().zzf(zzab2.zzc.zzb), zzab2.zzc.zza());
                }
                final zzak zzak5 = zze;
                zzkr.zzak(zzak5);
                zzak5.zzC();
                final zzak zzak6 = zze;
                zzkr.zzak(zzak6);
                zzak6.zzx();
            } catch (final Throwable th) {
                final zzak zzak7 = zze;
                zzkr.zzak(zzak7);
                zzak7.zzx();
                throw th;
            }
        }
    }
    public void zzU(final String str, final zzah zzah) {
        this.zzaz().zzg();
        this.zzB();
        zzB.put(str, zzah);
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzah);
        zzak.zzg();
        zzak.zzW();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzah.zzh());
        try {
            if (-1 == zzak.zzh().insertWithOnConflict("consent_settings", null, contentValues, 5)) {
                zzak.zzs.zzay().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzej.zzn(str));
            }
        } catch (final SQLiteException e2) {
            zzak.zzs.zzay().zzd().zzc("Error storing consent setting. appId, error", zzej.zzn(str), e2);
        }
    }

    public void zzV(final zzku zzku, final zzp zzp2) {
        long j2;
        final zzku zzku2 = zzku;
        final zzp zzp3 = zzp2;
        this.zzaz().zzg();
        this.zzB();
        if (zzkr.zzaj(zzp2)) {
            if (!zzp3.zzh) {
                this.zzd(zzp3);
                return;
            }
            final int zzl2 = this.zzv().zzl(zzku2.zzb);
            int i2 = 0;
            if (0 != zzl2) {
                final zzky zzv2 = this.zzv();
                final String str = zzku2.zzb;
                this.zzg();
                final String zzC2 = zzv2.zzC(str, 24, true);
                final String str2 = zzku2.zzb;
                this.zzv().zzM(zzE, zzp3.zza, zzl2, "_ev", zzC2, null != str2 ? str2.length() : 0);
                return;
            }
            final int zzd2 = this.zzv().zzd(zzku2.zzb, zzku.zza());
            if (0 != zzd2) {
                final zzky zzv3 = this.zzv();
                final String str3 = zzku2.zzb;
                this.zzg();
                final String zzC3 = zzv3.zzC(str3, 24, true);
                final Object zza2 = zzku.zza();
                if (null != zza2 && ((zza2 instanceof String) || (zza2 instanceof CharSequence))) {
                    i2 = zza2.toString().length();
                }
                this.zzv().zzM(zzE, zzp3.zza, zzd2, "_ev", zzC3, i2);
                return;
            }
            final Object zzB2 = this.zzv().zzB(zzku2.zzb, zzku.zza());
            if (null != zzB2) {
                if ("_sid".equals(zzku2.zzb)) {
                    final long j3 = zzku2.zzc;
                    final String str4 = zzku2.zzf;
                    final String str5 = Preconditions.checkNotNull(zzp3.zza);
                    final zzak zzak = zze;
                    zzkr.zzak(zzak);
                    final zzkw zzp4 = zzak.zzp(str5, "_sno");
                    if (null != zzp4) {
                        final Object obj = zzp4.zze();
                        if (obj instanceof Long) {
                            j2 = ((Long) obj).longValue();
                            this.zzV(new zzku("_sno", j3, Long.valueOf(j2 + 1), str4), zzp3);
                        }
                    }
                    if (null != zzp4) {
                        this.zzay().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzp4.zze());
                    }
                    final zzak zzak2 = zze;
                    zzkr.zzak(zzak2);
                    final zzaq zzn2 = zzak2.zzn(str5, "_s");
                    if (null != zzn2) {
                        j2 = zzn2.zzc;
                        this.zzay().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(j2));
                    } else {
                        j2 = 0;
                    }
                    this.zzV(new zzku("_sno", j3, Long.valueOf(j2 + 1), str4), zzp3);
                }
                final zzkw zzkw = new zzkw(Preconditions.checkNotNull(zzp3.zza), Preconditions.checkNotNull(zzku2.zzf), zzku2.zzb, zzku2.zzc, zzB2);
                this.zzay().zzj().zzc("Setting user property", zzn.zzj().zzf(zzkw.zzc()), zzB2);
                final zzak zzak3 = zze;
                zzkr.zzak(zzak3);
                zzak3.zzw();
                try {
                    zzna.zzc();
                    if (zzn.zzf().zzs(null, zzdw.zzan) && "_id".equals(zzkw.zzc())) {
                        if (zzn.zzf().zzs(null, zzdw.zzaq)) {
                            final zzak zzak4 = zze;
                            zzkr.zzak(zzak4);
                            final zzkw zzp5 = zzak4.zzp(zzp3.zza, "_id");
                            if (null != zzp5 && !zzkw.zze().equals(zzp5.zze())) {
                                final zzak zzak5 = zze;
                                zzkr.zzak(zzak5);
                                zzak5.zzA(zzp3.zza, "_lair");
                            }
                        } else {
                            final zzak zzak6 = zze;
                            zzkr.zzak(zzak6);
                            zzak6.zzA(zzp3.zza, "_lair");
                        }
                    }
                    this.zzd(zzp3);
                    final zzak zzak7 = zze;
                    zzkr.zzak(zzak7);
                    final boolean zzL = zzak7.zzL(zzkw);
                    final zzak zzak8 = zze;
                    zzkr.zzak(zzak8);
                    zzak8.zzC();
                    if (!zzL) {
                        this.zzay().zzd().zzc("Too many unique user properties are set. Ignoring user property", zzn.zzj().zzf(zzkw.zzc()), zzkw.zze());
                        this.zzv().zzM(zzE, zzp3.zza, 9, null, null, 0);
                    }
                    final zzak zzak9 = zze;
                    zzkr.zzak(zzak9);
                    zzak9.zzx();
                } catch (final Throwable th) {
                    final zzak zzak10 = zze;
                    zzkr.zzak(zzak10);
                    zzak10.zzx();
                    throw th;
                }
            }
        }
    }

    public void zzW() {
        /*
            r22 = this;
            r1 = r22
            com.google.android.gms.measurement.internal.zzfq r0 = r22.zzaz()
            r0.zzg()
            r22.zzB()
            r2 = 1
            r1.zzv = r2
            r3 = 0
            com.google.android.gms.measurement.internal.zzft r0 = r1.zzn     // Catch:{ all -> 0x027e }
            r0.zzaw()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzft r0 = r1.zzn     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzjm r0 = r0.zzt()     // Catch:{ all -> 0x027e }
            java.lang.Boolean r0 = r0.zzj()     // Catch:{ all -> 0x027e }
            if (r0 != 0) goto L_0x0038
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzk()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "Upload data called on the client side before use of service was decided"
            r0.zza(r2)     // Catch:{ all -> 0x0034 }
            r1.zzv = r3
        L_0x0030:
            r22.zzad()
            return
        L_0x0034:
            r0 = move-exception
            r2 = r3
            goto L_0x051f
        L_0x0038:
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x027e }
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzd()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "Upload called in the client side when service should be used"
            r0.zza(r2)     // Catch:{ all -> 0x0034 }
            r1.zzv = r3
            goto L_0x0030
        L_0x004e:
            long r4 = r1.zza     // Catch:{ all -> 0x027e }
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x005c
            r22.zzaf()     // Catch:{ all -> 0x0034 }
            r1.zzv = r3
            goto L_0x0030
        L_0x005c:
            com.google.android.gms.measurement.internal.zzfq r0 = r22.zzaz()     // Catch:{ all -> 0x027e }
            r0.zzg()     // Catch:{ all -> 0x027e }
            java.util.List r0 = r1.zzy     // Catch:{ all -> 0x027e }
            if (r0 == 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "Uploading requested multiple times"
            r0.zza(r2)     // Catch:{ all -> 0x0034 }
            r1.zzv = r3
            goto L_0x0030
        L_0x0077:
            com.google.android.gms.measurement.internal.zzep r0 = r1.zzd     // Catch:{ all -> 0x027e }
            zzak(r0)     // Catch:{ all -> 0x027e }
            boolean r0 = r0.zza()     // Catch:{ all -> 0x027e }
            if (r0 != 0) goto L_0x0095
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "Network not connected, ignoring upload request"
            r0.zza(r2)     // Catch:{ all -> 0x0034 }
            r22.zzaf()     // Catch:{ all -> 0x0034 }
            r1.zzv = r3
            goto L_0x0030
        L_0x0095:
            com.google.android.gms.common.util.Clock r0 = r22.zzav()     // Catch:{ all -> 0x027e }
            long r4 = r0.currentTimeMillis()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzaf r0 = r22.zzg()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzdv r8 = com.google.android.gms.measurement.internal.zzdw.zzP     // Catch:{ all -> 0x027e }
            r9 = 0
            int r0 = r0.zze(r9, r8)     // Catch:{ all -> 0x027e }
            r22.zzg()     // Catch:{ all -> 0x027e }
            long r10 = com.google.android.gms.measurement.internal.zzaf.zzz()     // Catch:{ all -> 0x027e }
            long r10 = r4 - r10
            r8 = r3
        L_0x00b2:
            if (r8 >= r0) goto L_0x00bd
            boolean r12 = r1.zzag(r9, r10)     // Catch:{ all -> 0x0034 }
            if (r12 == 0) goto L_0x00bd
            int r8 = r8 + 1
            goto L_0x00b2
        L_0x00bd:
            com.google.android.gms.measurement.internal.zzjo r0 = r1.zzk     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzc     // Catch:{ all -> 0x027e }
            long r10 = r0.zza()     // Catch:{ all -> 0x027e }
            int r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x00e0
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()     // Catch:{ all -> 0x0034 }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r7 = r4 - r10
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x0034 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0034 }
            r0.zzb(r6, r7)     // Catch:{ all -> 0x0034 }
        L_0x00e0:
            com.google.android.gms.measurement.internal.zzak r0 = r1.zze     // Catch:{ all -> 0x027e }
            zzak(r0)     // Catch:{ all -> 0x027e }
            java.lang.String r6 = r0.zzr()     // Catch:{ all -> 0x027e }
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x027e }
            r7 = -1
            if (r0 != 0) goto L_0x049b
            long r10 = r1.zzA     // Catch:{ all -> 0x027e }
            int r0 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x013a
            com.google.android.gms.measurement.internal.zzak r10 = r1.zze     // Catch:{ all -> 0x0034 }
            zzak(r10)     // Catch:{ all -> 0x0034 }
            android.database.sqlite.SQLiteDatabase r0 = r10.zzh()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011b }
            java.lang.String r11 = "select rowid from raw_events order by rowid desc limit 1;"
            android.database.Cursor r11 = r0.rawQuery(r11, r9)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011b }
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x0117 }
            if (r0 != 0) goto L_0x0110
        L_0x010c:
            r11.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0131
        L_0x0110:
            long r7 = r11.getLong(r3)     // Catch:{ SQLiteException -> 0x0117 }
            goto L_0x010c
        L_0x0115:
            r0 = move-exception
            goto L_0x0119
        L_0x0117:
            r0 = move-exception
            goto L_0x011f
        L_0x0119:
            r9 = r11
            goto L_0x0134
        L_0x011b:
            r0 = move-exception
            goto L_0x0134
        L_0x011d:
            r0 = move-exception
            r11 = r9
        L_0x011f:
            com.google.android.gms.measurement.internal.zzft r10 = r10.zzs     // Catch:{ all -> 0x0115 }
            com.google.android.gms.measurement.internal.zzej r10 = r10.zzay()     // Catch:{ all -> 0x0115 }
            com.google.android.gms.measurement.internal.zzeh r10 = r10.zzd()     // Catch:{ all -> 0x0115 }
            java.lang.String r12 = "Error querying raw events"
            r10.zzb(r12, r0)     // Catch:{ all -> 0x0115 }
            if (r11 == 0) goto L_0x0131
            goto L_0x010c
        L_0x0131:
            r1.zzA = r7     // Catch:{ all -> 0x0034 }
            goto L_0x013a
        L_0x0134:
            if (r9 == 0) goto L_0x0139
            r9.close()     // Catch:{ all -> 0x0034 }
        L_0x0139:
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x013a:
            com.google.android.gms.measurement.internal.zzaf r0 = r22.zzg()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzdv r7 = com.google.android.gms.measurement.internal.zzdw.zzf     // Catch:{ all -> 0x027e }
            int r0 = r0.zze(r6, r7)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzaf r7 = r22.zzg()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzdv r8 = com.google.android.gms.measurement.internal.zzdw.zzg     // Catch:{ all -> 0x027e }
            int r7 = r7.zze(r6, r8)     // Catch:{ all -> 0x027e }
            int r7 = java.lang.Math.max(r3, r7)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzak r8 = r1.zze     // Catch:{ all -> 0x027e }
            zzak(r8)     // Catch:{ all -> 0x027e }
            r8.zzg()     // Catch:{ all -> 0x027e }
            r8.zzW()     // Catch:{ all -> 0x027e }
            if (r0 <= 0) goto L_0x0161
            r10 = r2
            goto L_0x0162
        L_0x0161:
            r10 = r3
        L_0x0162:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r10)     // Catch:{ all -> 0x027e }
            if (r7 <= 0) goto L_0x0169
            r10 = r2
            goto L_0x016a
        L_0x0169:
            r10 = r3
        L_0x016a:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r10)     // Catch:{ all -> 0x027e }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x027e }
            android.database.sqlite.SQLiteDatabase r11 = r8.zzh()     // Catch:{ SQLiteException -> 0x0287, all -> 0x0285 }
            java.lang.String r12 = "rowid"
            java.lang.String r13 = "data"
            java.lang.String r14 = "retry_count"
            java.lang.String[] r13 = new java.lang.String[]{r12, r13, r14}     // Catch:{ SQLiteException -> 0x0287, all -> 0x0285 }
            java.lang.String[] r15 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x0287, all -> 0x0285 }
            java.lang.String r12 = "queue"
            java.lang.String r14 = "app_id=?"
            java.lang.String r18 = "rowid"
            java.lang.String r19 = java.lang.String.valueOf(r0)     // Catch:{ SQLiteException -> 0x0287, all -> 0x0285 }
            r16 = 0
            r17 = 0
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SQLiteException -> 0x0287, all -> 0x0285 }
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x01a8 }
            if (r0 != 0) goto L_0x01ad
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x01a8 }
            r11.close()     // Catch:{ all -> 0x0034 }
            r20 = r4
            goto L_0x02ab
        L_0x01a5:
            r0 = move-exception
            goto L_0x0282
        L_0x01a8:
            r0 = move-exception
            r20 = r4
            goto L_0x028f
        L_0x01ad:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x01a8 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x01a8 }
            r13 = r3
        L_0x01b3:
            long r14 = r11.getLong(r3)     // Catch:{ SQLiteException -> 0x01a8 }
            byte[] r0 = r11.getBlob(r2)     // Catch:{ IOException -> 0x0253 }
            com.google.android.gms.measurement.internal.zzkr r2 = r8.zzf     // Catch:{ IOException -> 0x0253 }
            com.google.android.gms.measurement.internal.zzkt r2 = r2.zzi     // Catch:{ IOException -> 0x0253 }
            zzak(r2)     // Catch:{ IOException -> 0x0253 }
            java.io.ByteArrayInputStream r9 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x023e }
            r9.<init>(r0)     // Catch:{ IOException -> 0x023e }
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x023e }
            r0.<init>(r9)     // Catch:{ IOException -> 0x023e }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x023e }
            r3.<init>()     // Catch:{ IOException -> 0x023e }
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch:{ IOException -> 0x023e }
            r20 = r4
        L_0x01d7:
            int r4 = r0.read(r10)     // Catch:{ IOException -> 0x0237 }
            if (r4 > 0) goto L_0x0239
            r0.close()     // Catch:{ IOException -> 0x0237 }
            r9.close()     // Catch:{ IOException -> 0x0237 }
            byte[] r0 = r3.toByteArray()     // Catch:{ IOException -> 0x0237 }
            boolean r2 = r12.isEmpty()     // Catch:{ SQLiteException -> 0x01f3 }
            if (r2 != 0) goto L_0x01f6
            int r2 = r0.length     // Catch:{ SQLiteException -> 0x01f3 }
            int r2 = r2 + r13
            if (r2 <= r7) goto L_0x01f6
            goto L_0x0279
        L_0x01f3:
            r0 = move-exception
            goto L_0x028f
        L_0x01f6:
            com.google.android.gms.internal.measurement.zzfx r2 = com.google.android.gms.internal.measurement.zzfy.zzu()     // Catch:{ IOException -> 0x0222 }
            com.google.android.gms.internal.measurement.zzlf r2 = com.google.android.gms.measurement.internal.zzkt.zzl(r2, r0)     // Catch:{ IOException -> 0x0222 }
            com.google.android.gms.internal.measurement.zzfx r2 = (com.google.android.gms.internal.measurement.zzfx) r2     // Catch:{ IOException -> 0x0222 }
            r3 = 2
            boolean r4 = r11.isNull(r3)     // Catch:{ SQLiteException -> 0x01f3 }
            if (r4 != 0) goto L_0x020e
            int r4 = r11.getInt(r3)     // Catch:{ SQLiteException -> 0x01f3 }
            r2.zzab(r4)     // Catch:{ SQLiteException -> 0x01f3 }
        L_0x020e:
            int r0 = r0.length     // Catch:{ SQLiteException -> 0x01f3 }
            int r13 = r13 + r0
            com.google.android.gms.internal.measurement.zzjz r0 = r2.zzay()     // Catch:{ SQLiteException -> 0x01f3 }
            com.google.android.gms.internal.measurement.zzfy r0 = (com.google.android.gms.internal.measurement.zzfy) r0     // Catch:{ SQLiteException -> 0x01f3 }
            java.lang.Long r2 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteException -> 0x01f3 }
            android.util.Pair r0 = android.util.Pair.create(r0, r2)     // Catch:{ SQLiteException -> 0x01f3 }
            r12.add(r0)     // Catch:{ SQLiteException -> 0x01f3 }
            goto L_0x0269
        L_0x0222:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzft r2 = r8.zzs     // Catch:{ SQLiteException -> 0x01f3 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x01f3 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x01f3 }
            java.lang.String r3 = "Failed to merge queued bundle. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r6)     // Catch:{ SQLiteException -> 0x01f3 }
            r2.zzc(r3, r4, r0)     // Catch:{ SQLiteException -> 0x01f3 }
            goto L_0x0269
        L_0x0237:
            r0 = move-exception
            goto L_0x0241
        L_0x0239:
            r5 = 0
            r3.write(r10, r5, r4)     // Catch:{ IOException -> 0x0237 }
            goto L_0x01d7
        L_0x023e:
            r0 = move-exception
            r20 = r4
        L_0x0241:
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs     // Catch:{ IOException -> 0x0251 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ IOException -> 0x0251 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ IOException -> 0x0251 }
            java.lang.String r3 = "Failed to ungzip content"
            r2.zzb(r3, r0)     // Catch:{ IOException -> 0x0251 }
            throw r0     // Catch:{ IOException -> 0x0251 }
        L_0x0251:
            r0 = move-exception
            goto L_0x0256
        L_0x0253:
            r0 = move-exception
            r20 = r4
        L_0x0256:
            com.google.android.gms.measurement.internal.zzft r2 = r8.zzs     // Catch:{ SQLiteException -> 0x01f3 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x01f3 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x01f3 }
            java.lang.String r3 = "Failed to unzip queued bundle. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r6)     // Catch:{ SQLiteException -> 0x01f3 }
            r2.zzc(r3, r4, r0)     // Catch:{ SQLiteException -> 0x01f3 }
        L_0x0269:
            boolean r0 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x01f3 }
            if (r0 == 0) goto L_0x0279
            if (r13 <= r7) goto L_0x0272
            goto L_0x0279
        L_0x0272:
            r4 = r20
            r2 = 1
            r3 = 0
            r9 = 0
            goto L_0x01b3
        L_0x0279:
            r11.close()     // Catch:{ all -> 0x027e }
            r0 = r12
            goto L_0x02ab
        L_0x027e:
            r0 = move-exception
            r2 = 0
            goto L_0x051f
        L_0x0282:
            r9 = r11
            goto L_0x0495
        L_0x0285:
            r0 = move-exception
            goto L_0x028b
        L_0x0287:
            r0 = move-exception
            r20 = r4
            goto L_0x028e
        L_0x028b:
            r9 = 0
            goto L_0x0495
        L_0x028e:
            r11 = 0
        L_0x028f:
            com.google.android.gms.measurement.internal.zzft r2 = r8.zzs     // Catch:{ all -> 0x01a5 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ all -> 0x01a5 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x01a5 }
            java.lang.String r3 = "Error querying bundles. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r6)     // Catch:{ all -> 0x01a5 }
            r2.zzc(r3, r4, r0)     // Catch:{ all -> 0x01a5 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x01a5 }
            if (r11 == 0) goto L_0x02ab
            r11.close()     // Catch:{ all -> 0x027e }
        L_0x02ab:
            boolean r2 = r0.isEmpty()     // Catch:{ all -> 0x027e }
            if (r2 != 0) goto L_0x0480
            com.google.android.gms.measurement.internal.zzah r2 = r1.zzh(r6)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzag r3 = com.google.android.gms.measurement.internal.zzag.AD_STORAGE     // Catch:{ all -> 0x027e }
            boolean r2 = r2.zzi(r3)     // Catch:{ all -> 0x027e }
            if (r2 == 0) goto L_0x0312
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x027e }
        L_0x02c1:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x027e }
            if (r3 == 0) goto L_0x02e0
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x027e }
            android.util.Pair r3 = (android.util.Pair) r3     // Catch:{ all -> 0x027e }
            java.lang.Object r3 = r3.first     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfy r3 = (com.google.android.gms.internal.measurement.zzfy) r3     // Catch:{ all -> 0x027e }
            java.lang.String r4 = r3.zzK()     // Catch:{ all -> 0x027e }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x027e }
            if (r4 != 0) goto L_0x02c1
            java.lang.String r2 = r3.zzK()     // Catch:{ all -> 0x027e }
            goto L_0x02e1
        L_0x02e0:
            r2 = 0
        L_0x02e1:
            if (r2 == 0) goto L_0x0312
            r3 = 0
        L_0x02e4:
            int r4 = r0.size()     // Catch:{ all -> 0x027e }
            if (r3 >= r4) goto L_0x0312
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x027e }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ all -> 0x027e }
            java.lang.Object r4 = r4.first     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfy r4 = (com.google.android.gms.internal.measurement.zzfy) r4     // Catch:{ all -> 0x027e }
            java.lang.String r5 = r4.zzK()     // Catch:{ all -> 0x027e }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x027e }
            if (r5 == 0) goto L_0x02ff
            goto L_0x030f
        L_0x02ff:
            java.lang.String r4 = r4.zzK()     // Catch:{ all -> 0x027e }
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x027e }
            if (r4 != 0) goto L_0x030f
            r4 = 0
            java.util.List r0 = r0.subList(r4, r3)     // Catch:{ all -> 0x027e }
            goto L_0x0312
        L_0x030f:
            int r3 = r3 + 1
            goto L_0x02e4
        L_0x0312:
            com.google.android.gms.internal.measurement.zzfv r2 = com.google.android.gms.internal.measurement.zzfw.zza()     // Catch:{ all -> 0x027e }
            int r3 = r0.size()     // Catch:{ all -> 0x027e }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x027e }
            int r5 = r0.size()     // Catch:{ all -> 0x027e }
            r4.<init>(r5)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzaf r5 = r22.zzg()     // Catch:{ all -> 0x027e }
            boolean r5 = r5.zzt(r6)     // Catch:{ all -> 0x027e }
            if (r5 == 0) goto L_0x033b
            com.google.android.gms.measurement.internal.zzah r5 = r1.zzh(r6)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzag r7 = com.google.android.gms.measurement.internal.zzag.AD_STORAGE     // Catch:{ all -> 0x027e }
            boolean r5 = r5.zzi(r7)     // Catch:{ all -> 0x027e }
            if (r5 == 0) goto L_0x033b
            r5 = 1
            goto L_0x033c
        L_0x033b:
            r5 = 0
        L_0x033c:
            com.google.android.gms.measurement.internal.zzah r7 = r1.zzh(r6)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzag r8 = com.google.android.gms.measurement.internal.zzag.AD_STORAGE     // Catch:{ all -> 0x027e }
            boolean r7 = r7.zzi(r8)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzah r8 = r1.zzh(r6)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzag r9 = com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE     // Catch:{ all -> 0x027e }
            boolean r8 = r8.zzi(r9)     // Catch:{ all -> 0x027e }
            r9 = 0
        L_0x0351:
            if (r9 >= r3) goto L_0x03c7
            java.lang.Object r10 = r0.get(r9)     // Catch:{ all -> 0x027e }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x027e }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfy r10 = (com.google.android.gms.internal.measurement.zzfy) r10     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzjv r10 = r10.zzbt()     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfx r10 = (com.google.android.gms.internal.measurement.zzfx) r10     // Catch:{ all -> 0x027e }
            java.lang.Object r11 = r0.get(r9)     // Catch:{ all -> 0x027e }
            android.util.Pair r11 = (android.util.Pair) r11     // Catch:{ all -> 0x027e }
            java.lang.Object r11 = r11.second     // Catch:{ all -> 0x027e }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x027e }
            r4.add(r11)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzaf r11 = r22.zzg()     // Catch:{ all -> 0x027e }
            r11.zzh()     // Catch:{ all -> 0x027e }
            r11 = 60000(0xea60, double:2.9644E-319)
            r10.zzag(r11)     // Catch:{ all -> 0x027e }
            r11 = r20
            r10.zzaf(r11)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzft r13 = r1.zzn     // Catch:{ all -> 0x027e }
            r13.zzaw()     // Catch:{ all -> 0x027e }
            r13 = 0
            r10.zzac(r13)     // Catch:{ all -> 0x027e }
            if (r5 != 0) goto L_0x0390
            r10.zzo()     // Catch:{ all -> 0x027e }
        L_0x0390:
            if (r7 != 0) goto L_0x0398
            r10.zzu()     // Catch:{ all -> 0x027e }
            r10.zzr()     // Catch:{ all -> 0x027e }
        L_0x0398:
            if (r8 != 0) goto L_0x039d
            r10.zzm()     // Catch:{ all -> 0x027e }
        L_0x039d:
            com.google.android.gms.measurement.internal.zzaf r13 = r22.zzg()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzdv r14 = com.google.android.gms.measurement.internal.zzdw.zzT     // Catch:{ all -> 0x027e }
            boolean r13 = r13.zzs(r6, r14)     // Catch:{ all -> 0x027e }
            if (r13 == 0) goto L_0x03bf
            com.google.android.gms.internal.measurement.zzjz r13 = r10.zzay()     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfy r13 = (com.google.android.gms.internal.measurement.zzfy) r13     // Catch:{ all -> 0x027e }
            byte[] r13 = r13.zzbq()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzkt r14 = r1.zzi     // Catch:{ all -> 0x027e }
            zzak(r14)     // Catch:{ all -> 0x027e }
            long r13 = r14.zzd(r13)     // Catch:{ all -> 0x027e }
            r10.zzG(r13)     // Catch:{ all -> 0x027e }
        L_0x03bf:
            r2.zza(r10)     // Catch:{ all -> 0x027e }
            int r9 = r9 + 1
            r20 = r11
            goto L_0x0351
        L_0x03c7:
            r11 = r20
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x027e }
            java.lang.String r0 = r0.zzq()     // Catch:{ all -> 0x027e }
            r5 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x027e }
            if (r0 == 0) goto L_0x03e8
            com.google.android.gms.measurement.internal.zzkt r0 = r1.zzi     // Catch:{ all -> 0x027e }
            zzak(r0)     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzjz r5 = r2.zzay()     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfw r5 = (com.google.android.gms.internal.measurement.zzfw) r5     // Catch:{ all -> 0x027e }
            java.lang.String r0 = r0.zzm(r5)     // Catch:{ all -> 0x027e }
            goto L_0x03e9
        L_0x03e8:
            r0 = 0
        L_0x03e9:
            com.google.android.gms.measurement.internal.zzkt r5 = r1.zzi     // Catch:{ all -> 0x027e }
            zzak(r5)     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzjz r5 = r2.zzay()     // Catch:{ all -> 0x027e }
            com.google.android.gms.internal.measurement.zzfw r5 = (com.google.android.gms.internal.measurement.zzfw) r5     // Catch:{ all -> 0x027e }
            byte[] r14 = r5.zzbq()     // Catch:{ all -> 0x027e }
            r22.zzg()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzdv r5 = com.google.android.gms.measurement.internal.zzdw.zzp     // Catch:{ all -> 0x027e }
            r9 = 0
            java.lang.Object r5 = r5.zza(r9)     // Catch:{ all -> 0x027e }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x027e }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0483 }
            r13.<init>(r5)     // Catch:{ MalformedURLException -> 0x0483 }
            boolean r7 = r4.isEmpty()     // Catch:{ MalformedURLException -> 0x0483 }
            r8 = 1
            r7 = r7 ^ r8
            com.google.android.gms.common.internal.Preconditions.checkArgument(r7)     // Catch:{ MalformedURLException -> 0x0483 }
            java.util.List r7 = r1.zzy     // Catch:{ MalformedURLException -> 0x0483 }
            if (r7 == 0) goto L_0x0424
            com.google.android.gms.measurement.internal.zzej r4 = r22.zzay()     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzd()     // Catch:{ MalformedURLException -> 0x0483 }
            java.lang.String r7 = "Set uploading progress before finishing the previous upload"
            r4.zza(r7)     // Catch:{ MalformedURLException -> 0x0483 }
            goto L_0x042b
        L_0x0424:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x0483 }
            r7.<init>(r4)     // Catch:{ MalformedURLException -> 0x0483 }
            r1.zzy = r7     // Catch:{ MalformedURLException -> 0x0483 }
        L_0x042b:
            com.google.android.gms.measurement.internal.zzjo r4 = r1.zzk     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzd     // Catch:{ MalformedURLException -> 0x0483 }
            r4.zzb(r11)     // Catch:{ MalformedURLException -> 0x0483 }
            java.lang.String r4 = "?"
            if (r3 <= 0) goto L_0x043f
            r3 = 0
            com.google.android.gms.internal.measurement.zzfy r2 = r2.zzb(r3)     // Catch:{ MalformedURLException -> 0x0483 }
            java.lang.String r4 = r2.zzy()     // Catch:{ MalformedURLException -> 0x0483 }
        L_0x043f:
            com.google.android.gms.measurement.internal.zzej r2 = r22.zzay()     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzj()     // Catch:{ MalformedURLException -> 0x0483 }
            java.lang.String r3 = "Uploading data. app, uncompressed size, data"
            int r7 = r14.length     // Catch:{ MalformedURLException -> 0x0483 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ MalformedURLException -> 0x0483 }
            r2.zzd(r3, r4, r7, r0)     // Catch:{ MalformedURLException -> 0x0483 }
            r2 = 1
            r1.zzu = r2     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzep r11 = r1.zzd     // Catch:{ MalformedURLException -> 0x0483 }
            zzak(r11)     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzkk r0 = new com.google.android.gms.measurement.internal.zzkk     // Catch:{ MalformedURLException -> 0x0483 }
            r0.<init>(r1, r6)     // Catch:{ MalformedURLException -> 0x0483 }
            r11.zzg()     // Catch:{ MalformedURLException -> 0x0483 }
            r11.zzW()     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzft r2 = r11.zzs     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzfq r2 = r2.zzaz()     // Catch:{ MalformedURLException -> 0x0483 }
            com.google.android.gms.measurement.internal.zzeo r3 = new com.google.android.gms.measurement.internal.zzeo     // Catch:{ MalformedURLException -> 0x0483 }
            r15 = 0
            r10 = r3
            r12 = r6
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x0483 }
            r2.zzo(r3)     // Catch:{ MalformedURLException -> 0x0483 }
        L_0x0480:
            r2 = 0
            goto L_0x0515
        L_0x0483:
            com.google.android.gms.measurement.internal.zzej r0 = r22.zzay()     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzd()     // Catch:{ all -> 0x027e }
            java.lang.String r2 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzej.zzn(r6)     // Catch:{ all -> 0x027e }
            r0.zzc(r2, r3, r5)     // Catch:{ all -> 0x027e }
            goto L_0x0480
        L_0x0495:
            if (r9 == 0) goto L_0x049a
            r9.close()     // Catch:{ all -> 0x027e }
        L_0x049a:
            throw r0     // Catch:{ all -> 0x027e }
        L_0x049b:
            r11 = r4
            r1.zzA = r7     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x027e }
            zzak(r2)     // Catch:{ all -> 0x027e }
            r22.zzg()     // Catch:{ all -> 0x027e }
            long r3 = com.google.android.gms.measurement.internal.zzaf.zzz()     // Catch:{ all -> 0x027e }
            long r4 = r11 - r3
            r2.zzg()     // Catch:{ all -> 0x027e }
            r2.zzW()     // Catch:{ all -> 0x027e }
            android.database.sqlite.SQLiteDatabase r0 = r2.zzh()     // Catch:{ SQLiteException -> 0x04eb, all -> 0x04e9 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x04eb, all -> 0x04e9 }
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x04eb, all -> 0x04e9 }
            java.lang.String r4 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            android.database.Cursor r3 = r0.rawQuery(r4, r3)     // Catch:{ SQLiteException -> 0x04eb, all -> 0x04e9 }
            boolean r0 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x04df }
            if (r0 != 0) goto L_0x04e1
            com.google.android.gms.measurement.internal.zzft r0 = r2.zzs     // Catch:{ SQLiteException -> 0x04df }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x04df }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()     // Catch:{ SQLiteException -> 0x04df }
            java.lang.String r4 = "No expired configs for apps with pending events"
            r0.zza(r4)     // Catch:{ SQLiteException -> 0x04df }
        L_0x04d9:
            r3.close()     // Catch:{ all -> 0x027e }
            goto L_0x04ff
        L_0x04dd:
            r0 = move-exception
            goto L_0x04e7
        L_0x04df:
            r0 = move-exception
            goto L_0x04ed
        L_0x04e1:
            r4 = 0
            java.lang.String r9 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x04df }
            goto L_0x04d9
        L_0x04e7:
            r9 = r3
            goto L_0x0519
        L_0x04e9:
            r0 = move-exception
            goto L_0x0519
        L_0x04eb:
            r0 = move-exception
            r3 = r9
        L_0x04ed:
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs     // Catch:{ all -> 0x04dd }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ all -> 0x04dd }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x04dd }
            java.lang.String r4 = "Error selecting expired configs"
            r2.zzb(r4, r0)     // Catch:{ all -> 0x04dd }
            if (r3 == 0) goto L_0x04ff
            goto L_0x04d9
        L_0x04ff:
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x027e }
            if (r0 != 0) goto L_0x0480
            com.google.android.gms.measurement.internal.zzak r0 = r1.zze     // Catch:{ all -> 0x027e }
            zzak(r0)     // Catch:{ all -> 0x027e }
            com.google.android.gms.measurement.internal.zzg r0 = r0.zzj(r9)     // Catch:{ all -> 0x027e }
            if (r0 == 0) goto L_0x0480
            r1.zzC(r0)     // Catch:{ all -> 0x027e }
            goto L_0x0480
        L_0x0515:
            r1.zzv = r2
            goto L_0x0030
        L_0x0519:
            if (r9 == 0) goto L_0x051e
            r9.close()     // Catch:{ all -> 0x027e }
        L_0x051e:
            throw r0     // Catch:{ all -> 0x027e }
        L_0x051f:
            r1.zzv = r2
            r22.zzad()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzW():void");
    }

    public void zzX(final com.google.android.gms.measurement.internal.zzau r34, final com.google.android.gms.measurement.internal.zzp r35) {
        /*
            r33 = this;
            r1 = r33
            r2 = r34
            r3 = r35
            java.lang.String r4 = "metadata_fingerprint"
            java.lang.String r5 = "app_id"
            java.lang.String r6 = "raw_events"
            java.lang.String r7 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r35)
            java.lang.String r8 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            long r8 = java.lang.System.nanoTime()
            com.google.android.gms.measurement.internal.zzfq r10 = r33.zzaz()
            r10.zzg()
            r33.zzB()
            java.lang.String r10 = r3.zza
            com.google.android.gms.measurement.internal.zzkt r11 = r1.zzi
            zzak(r11)
            boolean r11 = com.google.android.gms.measurement.internal.zzkt.zzA(r34, r35)
            if (r11 != 0) goto L_0x0032
            return
        L_0x0032:
            boolean r11 = r3.zzh
            if (r11 == 0) goto L_0x0a89
            com.google.android.gms.measurement.internal.zzfk r11 = r1.zzc
            zzak(r11)
            java.lang.String r12 = r2.zza
            boolean r11 = r11.zzo(r10, r12)
            java.lang.String r15 = "_err"
            r14 = 0
            if (r11 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzej r3 = r33.zzay()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzk()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r10)
            com.google.android.gms.measurement.internal.zzft r5 = r1.zzn
            com.google.android.gms.measurement.internal.zzee r5 = r5.zzj()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zzd(r6)
            java.lang.String r6 = "Dropping blocked event. appId"
            r3.zzc(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzfk r3 = r1.zzc
            zzak(r3)
            boolean r3 = r3.zzm(r10)
            if (r3 != 0) goto L_0x0097
            com.google.android.gms.measurement.internal.zzfk r3 = r1.zzc
            zzak(r3)
            boolean r3 = r3.zzp(r10)
            if (r3 == 0) goto L_0x007a
            goto L_0x0097
        L_0x007a:
            java.lang.String r3 = r2.zza
            boolean r3 = r15.equals(r3)
            if (r3 != 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()
            com.google.android.gms.measurement.internal.zzkx r12 = r1.zzE
            java.lang.String r2 = r2.zza
            r17 = 0
            r14 = 11
            java.lang.String r15 = "_ev"
            r13 = r10
            r16 = r2
            r11.zzM(r12, r13, r14, r15, r16, r17)
            return
        L_0x0097:
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            com.google.android.gms.measurement.internal.zzg r2 = r2.zzj(r10)
            if (r2 == 0) goto L_0x00de
            long r3 = r2.zzl()
            long r5 = r2.zzc()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.common.util.Clock r5 = r33.zzav()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            long r3 = java.lang.Math.abs(r5)
            r33.zzg()
            com.google.android.gms.measurement.internal.zzdv r5 = com.google.android.gms.measurement.internal.zzdw.zzy
            java.lang.Object r5 = r5.zza(r14)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzej r3 = r33.zzay()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzc()
            java.lang.String r4 = "Fetching config for blocked app"
            r3.zza(r4)
            r1.zzC(r2)
        L_0x00de:
            return
        L_0x00df:
            com.google.android.gms.measurement.internal.zzek r2 = com.google.android.gms.measurement.internal.zzek.zzb(r34)
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()
            com.google.android.gms.measurement.internal.zzaf r12 = r33.zzg()
            int r12 = r12.zzd(r10)
            r11.zzL(r2, r12)
            com.google.android.gms.measurement.internal.zzau r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzej r11 = r33.zzay()
            java.lang.String r11 = r11.zzq()
            r12 = 2
            boolean r11 = android.util.Log.isLoggable(r11, r12)
            if (r11 == 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzej r11 = r33.zzay()
            com.google.android.gms.measurement.internal.zzeh r11 = r11.zzj()
            com.google.android.gms.measurement.internal.zzft r12 = r1.zzn
            com.google.android.gms.measurement.internal.zzee r12 = r12.zzj()
            java.lang.String r12 = r12.zzc(r2)
            java.lang.String r13 = "Logging event"
            r11.zzb(r13, r12)
        L_0x011c:
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze
            zzak(r11)
            r11.zzw()
            r1.zzd(r3)     // Catch:{ all -> 0x014f }
            com.google.android.gms.internal.measurement.zzna.zzc()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r11 = r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r12 = com.google.android.gms.measurement.internal.zzdw.zzan     // Catch:{ all -> 0x014f }
            boolean r11 = r11.zzs(r14, r12)     // Catch:{ all -> 0x014f }
            if (r11 != 0) goto L_0x0153
            com.google.android.gms.measurement.internal.zzaf r11 = r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r12 = com.google.android.gms.measurement.internal.zzdw.zzao     // Catch:{ all -> 0x014f }
            boolean r11 = r11.zzs(r14, r12)     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x0153
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r3.zza     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "_lair"
            r11.zzA(r12, r13)     // Catch:{ all -> 0x014f }
            goto L_0x0153
        L_0x014f:
            r0 = move-exception
            r2 = r0
            goto L_0x0a80
        L_0x0153:
            java.lang.String r11 = "ecommerce_purchase"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = "refund"
            r28 = r8
            if (r11 != 0) goto L_0x0173
            java.lang.String r9 = "purchase"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x014f }
            if (r9 != 0) goto L_0x0173
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r9 = r12.equals(r9)     // Catch:{ all -> 0x014f }
            if (r9 == 0) goto L_0x0175
        L_0x0173:
            r9 = 1
            goto L_0x0176
        L_0x0175:
            r9 = 0
        L_0x0176:
            java.lang.String r11 = "_iap"
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r11 = r11.equals(r13)     // Catch:{ all -> 0x014f }
            if (r11 != 0) goto L_0x0187
            if (r9 == 0) goto L_0x0184
            r9 = 1
            goto L_0x0187
        L_0x0184:
            r8 = r15
            goto L_0x0311
        L_0x0187:
            com.google.android.gms.measurement.internal.zzas r11 = r2.zzb     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "currency"
            java.lang.String r11 = r11.zzg(r13)     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "value"
            if (r9 == 0) goto L_0x01fc
            com.google.android.gms.measurement.internal.zzas r9 = r2.zzb     // Catch:{ all -> 0x014f }
            java.lang.Double r9 = r9.zzd(r13)     // Catch:{ all -> 0x014f }
            double r16 = r9.doubleValue()     // Catch:{ all -> 0x014f }
            r18 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r16 = r16 * r18
            r20 = 0
            int r9 = (r16 > r20 ? 1 : (r16 == r20 ? 0 : -1))
            if (r9 != 0) goto L_0x01ba
            com.google.android.gms.measurement.internal.zzas r9 = r2.zzb     // Catch:{ all -> 0x014f }
            java.lang.Long r9 = r9.zze(r13)     // Catch:{ all -> 0x014f }
            r20 = r15
            long r14 = r9.longValue()     // Catch:{ all -> 0x014f }
            double r13 = (double) r14     // Catch:{ all -> 0x014f }
            double r16 = r13 * r18
            goto L_0x01bc
        L_0x01ba:
            r20 = r15
        L_0x01bc:
            r13 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r9 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r9 > 0) goto L_0x01d6
            r13 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r9 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r9 < 0) goto L_0x01d6
            long r13 = java.lang.Math.round(r16)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r9 = r12.equals(r9)     // Catch:{ all -> 0x014f }
            if (r9 == 0) goto L_0x0208
            long r13 = -r13
            goto L_0x0208
        L_0x01d6:
            com.google.android.gms.measurement.internal.zzej r2 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzk()     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "Data lost. Currency value is too big. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            java.lang.Double r5 = java.lang.Double.valueOf(r16)     // Catch:{ all -> 0x014f }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            r2.zzC()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            return
        L_0x01fc:
            r20 = r15
            com.google.android.gms.measurement.internal.zzas r9 = r2.zzb     // Catch:{ all -> 0x014f }
            java.lang.Long r9 = r9.zze(r13)     // Catch:{ all -> 0x014f }
            long r13 = r9.longValue()     // Catch:{ all -> 0x014f }
        L_0x0208:
            boolean r9 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x014f }
            if (r9 != 0) goto L_0x030f
            java.util.Locale r9 = java.util.Locale.US     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r11.toUpperCase(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = "[A-Z]{3}"
            boolean r11 = r9.matches(r11)     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x030f
            java.lang.String r11 = "_ltv_"
            int r12 = r9.length()     // Catch:{ all -> 0x014f }
            if (r12 == 0) goto L_0x0229
            java.lang.String r9 = r11.concat(r9)     // Catch:{ all -> 0x014f }
            goto L_0x022e
        L_0x0229:
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x014f }
            r9.<init>(r11)     // Catch:{ all -> 0x014f }
        L_0x022e:
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r11)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r11 = r11.zzp(r10, r9)     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x023f
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x014f }
            boolean r12 = r11 instanceof java.lang.Long     // Catch:{ all -> 0x014f }
            if (r12 != 0) goto L_0x0243
        L_0x023f:
            r15 = r20
            r8 = 0
            goto L_0x0270
        L_0x0243:
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x014f }
            long r11 = r11.longValue()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r18 = new com.google.android.gms.measurement.internal.zzkw     // Catch:{ all -> 0x014f }
            java.lang.String r15 = r2.zzc     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.util.Clock r16 = r33.zzav()     // Catch:{ all -> 0x014f }
            long r16 = r16.currentTimeMillis()     // Catch:{ all -> 0x014f }
            long r11 = r11 + r13
            java.lang.Long r19 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x014f }
            r11 = r18
            r12 = r10
            r14 = 0
            r13 = r15
            r8 = r14
            r15 = 0
            r14 = r9
            r8 = r15
            r9 = r20
            r15 = r16
            r17 = r19
            r11.<init>(r12, r13, r14, r15, r17)     // Catch:{ all -> 0x014f }
            r8 = r9
        L_0x026d:
            r9 = r18
            goto L_0x02d3
        L_0x0270:
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r11)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r12 = r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r8 = com.google.android.gms.measurement.internal.zzdw.zzD     // Catch:{ all -> 0x014f }
            int r8 = r12.zze(r10, r8)     // Catch:{ all -> 0x014f }
            int r8 = r8 + -1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)     // Catch:{ all -> 0x014f }
            r11.zzg()     // Catch:{ all -> 0x014f }
            r11.zzW()     // Catch:{ all -> 0x014f }
            android.database.sqlite.SQLiteDatabase r12 = r11.zzh()     // Catch:{ SQLiteException -> 0x02a1 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x02a1 }
            java.lang.String[] r8 = new java.lang.String[]{r10, r10, r8}     // Catch:{ SQLiteException -> 0x02a1 }
            r20 = r15
            java.lang.String r15 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r12.execSQL(r15, r8)     // Catch:{ SQLiteException -> 0x029e }
            goto L_0x02b8
        L_0x029e:
            r0 = move-exception
        L_0x029f:
            r8 = r0
            goto L_0x02a5
        L_0x02a1:
            r0 = move-exception
            r20 = r15
            goto L_0x029f
        L_0x02a5:
            com.google.android.gms.measurement.internal.zzft r11 = r11.zzs     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzej r11 = r11.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r11 = r11.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r12 = "Error pruning currencies. appId"
            java.lang.Object r15 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            r11.zzc(r12, r15, r8)     // Catch:{ all -> 0x014f }
        L_0x02b8:
            com.google.android.gms.measurement.internal.zzkw r18 = new com.google.android.gms.measurement.internal.zzkw     // Catch:{ all -> 0x014f }
            java.lang.String r8 = r2.zzc     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.util.Clock r11 = r33.zzav()     // Catch:{ all -> 0x014f }
            long r15 = r11.currentTimeMillis()     // Catch:{ all -> 0x014f }
            java.lang.Long r17 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x014f }
            r11 = r18
            r12 = r10
            r13 = r8
            r14 = r9
            r8 = r20
            r11.<init>(r12, r13, r14, r15, r17)     // Catch:{ all -> 0x014f }
            goto L_0x026d
        L_0x02d3:
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r11)     // Catch:{ all -> 0x014f }
            boolean r11 = r11.zzL(r9)     // Catch:{ all -> 0x014f }
            if (r11 != 0) goto L_0x0311
            com.google.android.gms.measurement.internal.zzej r11 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r11 = r11.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r12 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r14 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzee r14 = r14.zzj()     // Catch:{ all -> 0x014f }
            java.lang.String r15 = r9.zzc     // Catch:{ all -> 0x014f }
            java.lang.String r14 = r14.zzf(r15)     // Catch:{ all -> 0x014f }
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x014f }
            r11.zzd(r12, r13, r14, r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkx r12 = r1.zzE     // Catch:{ all -> 0x014f }
            r16 = 0
            r17 = 0
            r14 = 9
            r15 = 0
            r13 = r10
            r11.zzM(r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x014f }
            goto L_0x0311
        L_0x030f:
            r8 = r20
        L_0x0311:
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r9 = com.google.android.gms.measurement.internal.zzky.zzah(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r8 = r8.equals(r11)     // Catch:{ all -> 0x014f }
            r33.zzv()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzas r11 = r2.zzb     // Catch:{ all -> 0x014f }
            if (r11 != 0) goto L_0x0327
            r16 = 0
            goto L_0x0347
        L_0x0327:
            com.google.android.gms.measurement.internal.zzar r12 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x014f }
            r12.<init>(r11)     // Catch:{ all -> 0x014f }
            r16 = 0
        L_0x032e:
            boolean r13 = r12.hasNext()     // Catch:{ all -> 0x014f }
            if (r13 == 0) goto L_0x0347
            java.lang.String r13 = r12.next()     // Catch:{ all -> 0x014f }
            java.lang.Object r13 = r11.zzf(r13)     // Catch:{ all -> 0x014f }
            boolean r14 = r13 instanceof android.os.Parcelable[]     // Catch:{ all -> 0x014f }
            if (r14 == 0) goto L_0x032e
            android.os.Parcelable[] r13 = (android.os.Parcelable[]) r13     // Catch:{ all -> 0x014f }
            int r13 = r13.length     // Catch:{ all -> 0x014f }
            long r13 = (long) r13     // Catch:{ all -> 0x014f }
            long r16 = r16 + r13
            goto L_0x032e
        L_0x0347:
            r22 = 1
            long r15 = r16 + r22
            com.google.android.gms.measurement.internal.zzak r11 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r11)     // Catch:{ all -> 0x014f }
            long r12 = r33.zza()     // Catch:{ all -> 0x014f }
            r20 = 0
            r21 = 0
            r17 = 1
            r31 = r4
            r32 = r5
            r4 = 0
            r14 = r10
            r18 = r9
            r19 = r20
            r20 = r8
            com.google.android.gms.measurement.internal.zzai r11 = r11.zzm(r12, r14, r15, r17, r18, r19, r20, r21)     // Catch:{ all -> 0x014f }
            long r12 = r11.zzb     // Catch:{ all -> 0x014f }
            r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r14 = com.google.android.gms.measurement.internal.zzdw.zzj     // Catch:{ all -> 0x014f }
            r15 = 0
            java.lang.Object r14 = r14.zza(r15)     // Catch:{ all -> 0x014f }
            java.lang.Integer r14 = (java.lang.Integer) r14     // Catch:{ all -> 0x014f }
            int r14 = r14.intValue()     // Catch:{ all -> 0x014f }
            long r14 = (long) r14     // Catch:{ all -> 0x014f }
            long r12 = r12 - r14
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            r15 = 1000(0x3e8, double:4.94E-321)
            if (r14 <= 0) goto L_0x03b2
            long r12 = r12 % r15
            int r2 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r2 != 0) goto L_0x03a1
            com.google.android.gms.measurement.internal.zzej r2 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            long r5 = r11.zzb     // Catch:{ all -> 0x014f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x014f }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x014f }
        L_0x03a1:
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            r2.zzC()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            return
        L_0x03b2:
            if (r9 == 0) goto L_0x040f
            long r12 = r11.zza     // Catch:{ all -> 0x014f }
            r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r14 = com.google.android.gms.measurement.internal.zzdw.zzl     // Catch:{ all -> 0x014f }
            r15 = 0
            java.lang.Object r14 = r14.zza(r15)     // Catch:{ all -> 0x014f }
            java.lang.Integer r14 = (java.lang.Integer) r14     // Catch:{ all -> 0x014f }
            int r14 = r14.intValue()     // Catch:{ all -> 0x014f }
            long r14 = (long) r14     // Catch:{ all -> 0x014f }
            long r12 = r12 - r14
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 <= 0) goto L_0x040f
            r14 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 % r14
            int r3 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r3 != 0) goto L_0x03ea
            com.google.android.gms.measurement.internal.zzej r3 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            long r6 = r11.zza     // Catch:{ all -> 0x014f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x014f }
            r3.zzc(r4, r5, r6)     // Catch:{ all -> 0x014f }
        L_0x03ea:
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkx r12 = r1.zzE     // Catch:{ all -> 0x014f }
            java.lang.String r15 = "_ev"
            java.lang.String r2 = r2.zza     // Catch:{ all -> 0x014f }
            r17 = 0
            r14 = 16
            r13 = r10
            r16 = r2
            r11.zzM(r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            r2.zzC()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            return
        L_0x040f:
            r12 = 1000000(0xf4240, float:1.401298E-39)
            if (r8 == 0) goto L_0x045f
            long r13 = r11.zzd     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r8 = r33.zzg()     // Catch:{ all -> 0x014f }
            java.lang.String r15 = r3.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r4 = com.google.android.gms.measurement.internal.zzdw.zzk     // Catch:{ all -> 0x014f }
            int r4 = r8.zze(r15, r4)     // Catch:{ all -> 0x014f }
            int r4 = java.lang.Math.min(r12, r4)     // Catch:{ all -> 0x014f }
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)     // Catch:{ all -> 0x014f }
            long r4 = (long) r4     // Catch:{ all -> 0x014f }
            long r13 = r13 - r4
            r4 = 0
            int r8 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x045f
            int r2 = (r13 > r22 ? 1 : (r13 == r22 ? 0 : -1))
            if (r2 != 0) goto L_0x044e
            com.google.android.gms.measurement.internal.zzej r2 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            long r5 = r11.zzd     // Catch:{ all -> 0x014f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x014f }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x014f }
        L_0x044e:
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            r2.zzC()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            return
        L_0x045f:
            com.google.android.gms.measurement.internal.zzas r4 = r2.zzb     // Catch:{ all -> 0x014f }
            android.os.Bundle r4 = r4.zzc()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzky r5 = r33.zzv()     // Catch:{ all -> 0x014f }
            java.lang.String r8 = "_o"
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x014f }
            r5.zzN(r4, r8, r11)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzky r5 = r33.zzv()     // Catch:{ all -> 0x014f }
            boolean r5 = r5.zzad(r10)     // Catch:{ all -> 0x014f }
            java.lang.String r8 = "_r"
            if (r5 == 0) goto L_0x0490
            com.google.android.gms.measurement.internal.zzky r5 = r33.zzv()     // Catch:{ all -> 0x014f }
            java.lang.Long r11 = java.lang.Long.valueOf(r22)     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "_dbg"
            r5.zzN(r4, r13, r11)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzky r5 = r33.zzv()     // Catch:{ all -> 0x014f }
            r5.zzN(r4, r8, r11)     // Catch:{ all -> 0x014f }
        L_0x0490:
            java.lang.String r5 = "_s"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x014f }
            boolean r5 = r5.equals(r11)     // Catch:{ all -> 0x014f }
            if (r5 == 0) goto L_0x04b6
            com.google.android.gms.measurement.internal.zzak r5 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r5)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r5 = r5.zzp(r11, r7)     // Catch:{ all -> 0x014f }
            if (r5 == 0) goto L_0x04b6
            java.lang.Object r11 = r5.zze     // Catch:{ all -> 0x014f }
            boolean r11 = r11 instanceof java.lang.Long     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x04b6
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()     // Catch:{ all -> 0x014f }
            java.lang.Object r5 = r5.zze     // Catch:{ all -> 0x014f }
            r11.zzN(r4, r7, r5)     // Catch:{ all -> 0x014f }
        L_0x04b6:
            com.google.android.gms.measurement.internal.zzak r5 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r5)     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)     // Catch:{ all -> 0x014f }
            r5.zzg()     // Catch:{ all -> 0x014f }
            r5.zzW()     // Catch:{ all -> 0x014f }
            android.database.sqlite.SQLiteDatabase r7 = r5.zzh()     // Catch:{ SQLiteException -> 0x04f2 }
            com.google.android.gms.measurement.internal.zzft r11 = r5.zzs     // Catch:{ SQLiteException -> 0x04f2 }
            com.google.android.gms.measurement.internal.zzaf r11 = r11.zzf()     // Catch:{ SQLiteException -> 0x04f2 }
            com.google.android.gms.measurement.internal.zzdv r13 = com.google.android.gms.measurement.internal.zzdw.zzo     // Catch:{ SQLiteException -> 0x04f2 }
            int r11 = r11.zze(r10, r13)     // Catch:{ SQLiteException -> 0x04f2 }
            int r11 = java.lang.Math.min(r12, r11)     // Catch:{ SQLiteException -> 0x04f2 }
            r15 = 0
            int r11 = java.lang.Math.max(r15, r11)     // Catch:{ SQLiteException -> 0x04ef }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ SQLiteException -> 0x04ef }
            java.lang.String[] r11 = new java.lang.String[]{r10, r11}     // Catch:{ SQLiteException -> 0x04ef }
            java.lang.String r12 = "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)"
            int r5 = r7.delete(r6, r12, r11)     // Catch:{ SQLiteException -> 0x04ef }
            long r11 = (long) r5
        L_0x04ec:
            r13 = 0
            goto L_0x050b
        L_0x04ef:
            r0 = move-exception
        L_0x04f0:
            r7 = r0
            goto L_0x04f5
        L_0x04f2:
            r0 = move-exception
            r15 = 0
            goto L_0x04f0
        L_0x04f5:
            com.google.android.gms.measurement.internal.zzft r5 = r5.zzs     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r11 = "Error deleting over the limit events. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            r5.zzc(r11, r12, r7)     // Catch:{ all -> 0x014f }
            r11 = 0
            goto L_0x04ec
        L_0x050b:
            int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r5 <= 0) goto L_0x0524
            com.google.android.gms.measurement.internal.zzej r5 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzk()     // Catch:{ all -> 0x014f }
            java.lang.String r7 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x014f }
            r5.zzc(r7, r13, r11)     // Catch:{ all -> 0x014f }
        L_0x0524:
            com.google.android.gms.measurement.internal.zzap r5 = new com.google.android.gms.measurement.internal.zzap     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r12 = r1.zzn     // Catch:{ all -> 0x014f }
            java.lang.String r13 = r2.zzc     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x014f }
            long r2 = r2.zzd     // Catch:{ all -> 0x014f }
            r18 = 0
            r11 = r5
            r14 = r10
            r30 = r15
            r15 = r7
            r16 = r2
            r20 = r4
            r11.<init>((com.google.android.gms.measurement.internal.zzft) r12, (java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15, (long) r16, (long) r18, (android.os.Bundle) r20)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            java.lang.String r3 = r5.zzb     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaq r2 = r2.zzn(r10, r3)     // Catch:{ all -> 0x014f }
            if (r2 != 0) goto L_0x05c2
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            long r2 = r2.zzf(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r4 = r33.zzg()     // Catch:{ all -> 0x014f }
            int r4 = r4.zzb(r10)     // Catch:{ all -> 0x014f }
            long r11 = (long) r4     // Catch:{ all -> 0x014f }
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 < 0) goto L_0x05a4
            if (r9 == 0) goto L_0x05a4
            com.google.android.gms.measurement.internal.zzej r2 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzej.zzn(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r6 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzee r6 = r6.zzj()     // Catch:{ all -> 0x014f }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x014f }
            java.lang.String r5 = r6.zzd(r5)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r6 = r33.zzg()     // Catch:{ all -> 0x014f }
            int r6 = r6.zzb(r10)     // Catch:{ all -> 0x014f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x014f }
            r2.zzd(r3, r4, r5, r6)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzky r11 = r33.zzv()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkx r12 = r1.zzE     // Catch:{ all -> 0x014f }
            r16 = 0
            r17 = 0
            r14 = 8
            r15 = 0
            r13 = r10
            r11.zzM(r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            return
        L_0x05a4:
            com.google.android.gms.measurement.internal.zzaq r2 = new com.google.android.gms.measurement.internal.zzaq     // Catch:{ all -> 0x014f }
            java.lang.String r13 = r5.zzb     // Catch:{ all -> 0x014f }
            long r3 = r5.zzd     // Catch:{ all -> 0x014f }
            r26 = 0
            r27 = 0
            r14 = 0
            r16 = 0
            r18 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            r11 = r2
            r12 = r10
            r20 = r3
            r11.<init>(r12, r13, r14, r16, r18, r20, r22, r24, r25, r26, r27)     // Catch:{ all -> 0x014f }
            goto L_0x05d0
        L_0x05c2:
            com.google.android.gms.measurement.internal.zzft r3 = r1.zzn     // Catch:{ all -> 0x014f }
            long r9 = r2.zzf     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzap r5 = r5.zza(r3, r9)     // Catch:{ all -> 0x014f }
            long r3 = r5.zzd     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaq r2 = r2.zzc(r3)     // Catch:{ all -> 0x014f }
        L_0x05d0:
            com.google.android.gms.measurement.internal.zzak r3 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r3)     // Catch:{ all -> 0x014f }
            r3.zzE(r2)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzfq r2 = r33.zzaz()     // Catch:{ all -> 0x014f }
            r2.zzg()     // Catch:{ all -> 0x014f }
            r33.zzB()     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r35)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r5.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r5.zza     // Catch:{ all -> 0x014f }
            r3 = r35
            java.lang.String r4 = r3.zza     // Catch:{ all -> 0x014f }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x014f }
            com.google.android.gms.internal.measurement.zzfx r2 = com.google.android.gms.internal.measurement.zzfy.zzu()     // Catch:{ all -> 0x014f }
            r4 = 1
            r2.zzZ(r4)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = "android"
            r2.zzV(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x0614
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x014f }
            r2.zzA(r7)     // Catch:{ all -> 0x014f }
        L_0x0614:
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x0621
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x014f }
            r2.zzC(r7)     // Catch:{ all -> 0x014f }
        L_0x0621:
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x062e
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x014f }
            r2.zzD(r7)     // Catch:{ all -> 0x014f }
        L_0x062e:
            long r9 = r3.zzj     // Catch:{ all -> 0x014f }
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r7 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x063b
            int r7 = (int) r9     // Catch:{ all -> 0x014f }
            r2.zzE(r7)     // Catch:{ all -> 0x014f }
        L_0x063b:
            long r9 = r3.zze     // Catch:{ all -> 0x014f }
            r2.zzR(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x064d
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x014f }
            r2.zzQ(r7)     // Catch:{ all -> 0x014f }
        L_0x064d:
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x014f }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r7 = r1.zzh(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r3.zzv     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r9 = com.google.android.gms.measurement.internal.zzah.zzb(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r7 = r7.zzc(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r7.zzh()     // Catch:{ all -> 0x014f }
            r2.zzI(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r2.zzal()     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 == 0) goto L_0x0681
            java.lang.String r7 = r3.zzq     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x0681
            java.lang.String r7 = r3.zzq     // Catch:{ all -> 0x014f }
            r2.zzy(r7)     // Catch:{ all -> 0x014f }
        L_0x0681:
            long r9 = r3.zzf     // Catch:{ all -> 0x014f }
            r11 = 0
            int r7 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x068c
            r2.zzJ(r9)     // Catch:{ all -> 0x014f }
        L_0x068c:
            long r9 = r3.zzs     // Catch:{ all -> 0x014f }
            r2.zzM(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkt r7 = r1.zzi     // Catch:{ all -> 0x014f }
            zzak(r7)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkr r9 = r7.zzf     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r9 = r9.zzn     // Catch:{ all -> 0x014f }
            android.content.Context r9 = r9.zzau()     // Catch:{ all -> 0x014f }
            java.util.Map r9 = com.google.android.gms.measurement.internal.zzdw.zzc(r9)     // Catch:{ all -> 0x014f }
            if (r9 == 0) goto L_0x06aa
            int r10 = r9.size()     // Catch:{ all -> 0x014f }
            if (r10 != 0) goto L_0x06ad
        L_0x06aa:
            r14 = 0
            goto L_0x072c
        L_0x06ad:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x014f }
            r14.<init>()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r10 = com.google.android.gms.measurement.internal.zzdw.zzO     // Catch:{ all -> 0x014f }
            r11 = 0
            java.lang.Object r10 = r10.zza(r11)     // Catch:{ all -> 0x014f }
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch:{ all -> 0x014f }
            int r10 = r10.intValue()     // Catch:{ all -> 0x014f }
            java.util.Set r9 = r9.entrySet()     // Catch:{ all -> 0x014f }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x014f }
        L_0x06c7:
            boolean r11 = r9.hasNext()     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x0724
            java.lang.Object r11 = r9.next()     // Catch:{ all -> 0x014f }
            java.util.MapEntry r11 = (java.util.Map.Entry) r11     // Catch:{ all -> 0x014f }
            java.lang.Object r12 = r11.getKey()     // Catch:{ all -> 0x014f }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "measurement.id."
            boolean r12 = r12.startsWith(r13)     // Catch:{ all -> 0x014f }
            if (r12 == 0) goto L_0x06c7
            java.lang.Object r11 = r11.getValue()     // Catch:{ NumberFormatException -> 0x0712 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ NumberFormatException -> 0x0712 }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x0712 }
            if (r11 == 0) goto L_0x06c7
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ NumberFormatException -> 0x0712 }
            r14.add(r11)     // Catch:{ NumberFormatException -> 0x0712 }
            int r11 = r14.size()     // Catch:{ NumberFormatException -> 0x0712 }
            if (r11 < r10) goto L_0x06c7
            com.google.android.gms.measurement.internal.zzft r11 = r7.zzs     // Catch:{ NumberFormatException -> 0x0712 }
            com.google.android.gms.measurement.internal.zzej r11 = r11.zzay()     // Catch:{ NumberFormatException -> 0x0712 }
            com.google.android.gms.measurement.internal.zzeh r11 = r11.zzk()     // Catch:{ NumberFormatException -> 0x0712 }
            java.lang.String r12 = "Too many experiment IDs. Number of IDs"
            int r13 = r14.size()     // Catch:{ NumberFormatException -> 0x0712 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ NumberFormatException -> 0x0712 }
            r11.zzb(r12, r13)     // Catch:{ NumberFormatException -> 0x0712 }
            goto L_0x0724
        L_0x0712:
            r0 = move-exception
            r11 = r0
            com.google.android.gms.measurement.internal.zzft r12 = r7.zzs     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzej r12 = r12.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r12 = r12.zzk()     // Catch:{ all -> 0x014f }
            java.lang.String r13 = "Experiment ID NumberFormatException"
            r12.zzb(r13, r11)     // Catch:{ all -> 0x014f }
            goto L_0x06c7
        L_0x0724:
            int r7 = r14.size()     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x072c
            goto L_0x06aa
        L_0x072c:
            if (r14 == 0) goto L_0x0731
            r2.zzh(r14)     // Catch:{ all -> 0x014f }
        L_0x0731:
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x014f }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r7 = r1.zzh(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r3.zzv     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r9 = com.google.android.gms.measurement.internal.zzah.zzb(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzah r7 = r7.zzc(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzag r9 = com.google.android.gms.measurement.internal.zzag.AD_STORAGE     // Catch:{ all -> 0x014f }
            boolean r10 = r7.zzi(r9)     // Catch:{ all -> 0x014f }
            if (r10 == 0) goto L_0x0779
            com.google.android.gms.measurement.internal.zzjo r10 = r1.zzk     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x014f }
            android.util.Pair r10 = r10.zzd(r11, r7)     // Catch:{ all -> 0x014f }
            java.lang.Object r11 = r10.first     // Catch:{ all -> 0x014f }
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ all -> 0x014f }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x014f }
            if (r11 != 0) goto L_0x0779
            boolean r11 = r3.zzo     // Catch:{ all -> 0x014f }
            if (r11 == 0) goto L_0x0779
            java.lang.Object r11 = r10.first     // Catch:{ all -> 0x014f }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x014f }
            r2.zzaa(r11)     // Catch:{ all -> 0x014f }
            java.lang.Object r10 = r10.second     // Catch:{ all -> 0x014f }
            if (r10 == 0) goto L_0x0779
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x014f }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x014f }
            r2.zzT(r10)     // Catch:{ all -> 0x014f }
        L_0x0779:
            com.google.android.gms.measurement.internal.zzft r10 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzao r10 = r10.zzg()     // Catch:{ all -> 0x014f }
            r10.zzu()     // Catch:{ all -> 0x014f }
            java.lang.String r10 = android.os.Build.MODEL     // Catch:{ all -> 0x014f }
            r2.zzK(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r10 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzao r10 = r10.zzg()     // Catch:{ all -> 0x014f }
            r10.zzu()     // Catch:{ all -> 0x014f }
            java.lang.String r10 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x014f }
            r2.zzU(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r10 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzao r10 = r10.zzg()     // Catch:{ all -> 0x014f }
            long r10 = r10.zzb()     // Catch:{ all -> 0x014f }
            int r10 = (int) r10     // Catch:{ all -> 0x014f }
            r2.zzae(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r10 = r1.zzn     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzao r10 = r10.zzg()     // Catch:{ all -> 0x014f }
            java.lang.String r10 = r10.zzc()     // Catch:{ all -> 0x014f }
            r2.zzai(r10)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r10 = r33.zzg()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r11 = com.google.android.gms.measurement.internal.zzdw.zzah     // Catch:{ all -> 0x014f }
            r12 = 0
            boolean r10 = r10.zzs(r12, r11)     // Catch:{ all -> 0x014f }
            if (r10 != 0) goto L_0x07c2
            long r12 = r3.zzl     // Catch:{ all -> 0x014f }
            r2.zzz(r12)     // Catch:{ all -> 0x014f }
        L_0x07c2:
            com.google.android.gms.measurement.internal.zzft r10 = r1.zzn     // Catch:{ all -> 0x014f }
            boolean r10 = r10.zzJ()     // Catch:{ all -> 0x014f }
            if (r10 == 0) goto L_0x07d7
            r2.zzak()     // Catch:{ all -> 0x014f }
            r10 = 0
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x014f }
            if (r12 != 0) goto L_0x07d7
            r2.zzL(r10)     // Catch:{ all -> 0x014f }
        L_0x07d7:
            com.google.android.gms.measurement.internal.zzak r10 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r10)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r3.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzg r10 = r10.zzj(r12)     // Catch:{ all -> 0x014f }
            if (r10 != 0) goto L_0x0855
            com.google.android.gms.measurement.internal.zzg r10 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzft r12 = r1.zzn     // Catch:{ all -> 0x014f }
            java.lang.String r13 = r3.zza     // Catch:{ all -> 0x014f }
            r10.<init>(r12, r13)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r1.zzw(r7)     // Catch:{ all -> 0x014f }
            r10.zzH(r12)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r3.zzk     // Catch:{ all -> 0x014f }
            r10.zzV(r12)     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r3.zzb     // Catch:{ all -> 0x014f }
            r10.zzW(r12)     // Catch:{ all -> 0x014f }
            boolean r9 = r7.zzi(r9)     // Catch:{ all -> 0x014f }
            if (r9 == 0) goto L_0x080f
            com.google.android.gms.measurement.internal.zzjo r9 = r1.zzk     // Catch:{ all -> 0x014f }
            java.lang.String r12 = r3.zza     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r9.zzf(r12)     // Catch:{ all -> 0x014f }
            r10.zzae(r9)     // Catch:{ all -> 0x014f }
        L_0x080f:
            r12 = 0
            r10.zzaa(r12)     // Catch:{ all -> 0x014f }
            r10.zzab(r12)     // Catch:{ all -> 0x014f }
            r10.zzZ(r12)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r3.zzc     // Catch:{ all -> 0x014f }
            r10.zzJ(r9)     // Catch:{ all -> 0x014f }
            long r12 = r3.zzj     // Catch:{ all -> 0x014f }
            r10.zzK(r12)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r3.zzd     // Catch:{ all -> 0x014f }
            r10.zzI(r9)     // Catch:{ all -> 0x014f }
            long r12 = r3.zze     // Catch:{ all -> 0x014f }
            r10.zzX(r12)     // Catch:{ all -> 0x014f }
            long r12 = r3.zzf     // Catch:{ all -> 0x014f }
            r10.zzS(r12)     // Catch:{ all -> 0x014f }
            boolean r9 = r3.zzh     // Catch:{ all -> 0x014f }
            r10.zzac(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r9 = r33.zzg()     // Catch:{ all -> 0x014f }
            r12 = 0
            boolean r9 = r9.zzs(r12, r11)     // Catch:{ all -> 0x014f }
            if (r9 != 0) goto L_0x0848
            long r11 = r3.zzl     // Catch:{ all -> 0x014f }
            r10.zzG(r11)     // Catch:{ all -> 0x014f }
        L_0x0848:
            long r11 = r3.zzs     // Catch:{ all -> 0x014f }
            r10.zzT(r11)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r9 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r9)     // Catch:{ all -> 0x014f }
            r9.zzD(r10)     // Catch:{ all -> 0x014f }
        L_0x0855:
            com.google.android.gms.measurement.internal.zzag r9 = com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE     // Catch:{ all -> 0x014f }
            boolean r7 = r7.zzi(r9)     // Catch:{ all -> 0x014f }
            if (r7 == 0) goto L_0x0874
            java.lang.String r7 = r10.zzu()     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x0874
            java.lang.String r7 = r10.zzu()     // Catch:{ all -> 0x014f }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x014f }
            r2.zzB(r7)     // Catch:{ all -> 0x014f }
        L_0x0874:
            java.lang.String r7 = r10.zzx()     // Catch:{ all -> 0x014f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x014f }
            if (r7 != 0) goto L_0x088b
            java.lang.String r7 = r10.zzx()     // Catch:{ all -> 0x014f }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x014f }
            r2.zzP(r7)     // Catch:{ all -> 0x014f }
        L_0x088b:
            com.google.android.gms.measurement.internal.zzak r7 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r3 = r3.zza     // Catch:{ all -> 0x014f }
            java.util.List r3 = r7.zzu(r3)     // Catch:{ all -> 0x014f }
            r13 = r30
        L_0x0898:
            int r7 = r3.size()     // Catch:{ all -> 0x014f }
            if (r13 >= r7) goto L_0x08ce
            com.google.android.gms.internal.measurement.zzgg r7 = com.google.android.gms.internal.measurement.zzgh.zzd()     // Catch:{ all -> 0x014f }
            java.lang.Object r9 = r3.get(r13)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r9 = (com.google.android.gms.measurement.internal.zzkw) r9     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r9.zzc     // Catch:{ all -> 0x014f }
            r7.zzf(r9)     // Catch:{ all -> 0x014f }
            java.lang.Object r9 = r3.get(r13)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r9 = (com.google.android.gms.measurement.internal.zzkw) r9     // Catch:{ all -> 0x014f }
            long r9 = r9.zzd     // Catch:{ all -> 0x014f }
            r7.zzg(r9)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkt r9 = r1.zzi     // Catch:{ all -> 0x014f }
            zzak(r9)     // Catch:{ all -> 0x014f }
            java.lang.Object r10 = r3.get(r13)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkw r10 = (com.google.android.gms.measurement.internal.zzkw) r10     // Catch:{ all -> 0x014f }
            java.lang.Object r10 = r10.zze     // Catch:{ all -> 0x014f }
            r9.zzu(r7, r10)     // Catch:{ all -> 0x014f }
            r2.zzk(r7)     // Catch:{ all -> 0x014f }
            int r13 = r13 + 1
            goto L_0x0898
        L_0x08ce:
            com.google.android.gms.measurement.internal.zzak r3 = r1.zze     // Catch:{ IOException -> 0x0a18 }
            zzak(r3)     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.internal.measurement.zzjz r7 = r2.zzay()     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.internal.measurement.zzfy r7 = (com.google.android.gms.internal.measurement.zzfy) r7     // Catch:{ IOException -> 0x0a18 }
            r3.zzg()     // Catch:{ IOException -> 0x0a18 }
            r3.zzW()     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ IOException -> 0x0a18 }
            java.lang.String r9 = r7.zzy()     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)     // Catch:{ IOException -> 0x0a18 }
            byte[] r9 = r7.zzbq()     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.measurement.internal.zzkr r10 = r3.zzf     // Catch:{ IOException -> 0x0a18 }
            com.google.android.gms.measurement.internal.zzkt r10 = r10.zzi     // Catch:{ IOException -> 0x0a18 }
            zzak(r10)     // Catch:{ IOException -> 0x0a18 }
            long r10 = r10.zzd(r9)     // Catch:{ IOException -> 0x0a18 }
            android.content.ContentValues r12 = new android.content.ContentValues     // Catch:{ IOException -> 0x0a18 }
            r12.<init>()     // Catch:{ IOException -> 0x0a18 }
            java.lang.String r13 = r7.zzy()     // Catch:{ IOException -> 0x0a18 }
            r14 = r32
            r12.put(r14, r13)     // Catch:{ IOException -> 0x0a18 }
            java.lang.Long r13 = java.lang.Long.valueOf(r10)     // Catch:{ IOException -> 0x0a18 }
            r15 = r31
            r12.put(r15, r13)     // Catch:{ IOException -> 0x0a18 }
            java.lang.String r13 = "metadata"
            r12.put(r13, r9)     // Catch:{ IOException -> 0x0a18 }
            android.database.sqlite.SQLiteDatabase r9 = r3.zzh()     // Catch:{ SQLiteException -> 0x0a1c }
            java.lang.String r13 = "raw_events_metadata"
            r4 = 4
            r16 = r2
            r2 = 0
            r9.insertWithOnConflict(r13, r2, r12, r4)     // Catch:{ SQLiteException -> 0x0a15 }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzas r3 = r5.zzf     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzar r4 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x014f }
            r4.<init>(r3)     // Catch:{ all -> 0x014f }
        L_0x092d:
            boolean r3 = r4.hasNext()     // Catch:{ all -> 0x014f }
            if (r3 == 0) goto L_0x093f
            java.lang.String r3 = r4.next()     // Catch:{ all -> 0x014f }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x014f }
            if (r3 == 0) goto L_0x092d
        L_0x093d:
            r13 = 1
            goto L_0x0981
        L_0x093f:
            com.google.android.gms.measurement.internal.zzfk r3 = r1.zzc     // Catch:{ all -> 0x014f }
            zzak(r3)     // Catch:{ all -> 0x014f }
            java.lang.String r4 = r5.zza     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r5.zzb     // Catch:{ all -> 0x014f }
            boolean r3 = r3.zzn(r4, r7)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r4 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r4)     // Catch:{ all -> 0x014f }
            long r17 = r33.zza()     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x014f }
            r23 = 0
            r24 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r16 = r4
            r19 = r7
            com.google.android.gms.measurement.internal.zzai r4 = r16.zzl(r17, r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x014f }
            if (r3 == 0) goto L_0x097f
            long r3 = r4.zze     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzaf r7 = r33.zzg()     // Catch:{ all -> 0x014f }
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzdv r9 = com.google.android.gms.measurement.internal.zzdw.zzn     // Catch:{ all -> 0x014f }
            int r7 = r7.zze(r8, r9)     // Catch:{ all -> 0x014f }
            long r7 = (long) r7     // Catch:{ all -> 0x014f }
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x097f
            goto L_0x093d
        L_0x097f:
            r13 = r30
        L_0x0981:
            r2.zzg()     // Catch:{ all -> 0x014f }
            r2.zzW()     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x014f }
            java.lang.String r3 = r5.zza     // Catch:{ all -> 0x014f }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkr r3 = r2.zzf     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzkt r3 = r3.zzi     // Catch:{ all -> 0x014f }
            zzak(r3)     // Catch:{ all -> 0x014f }
            com.google.android.gms.internal.measurement.zzfo r3 = r3.zzj(r5)     // Catch:{ all -> 0x014f }
            byte[] r3 = r3.zzbq()     // Catch:{ all -> 0x014f }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ all -> 0x014f }
            r4.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x014f }
            r4.put(r14, r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = "name"
            java.lang.String r8 = r5.zzb     // Catch:{ all -> 0x014f }
            r4.put(r7, r8)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = "timestamp"
            long r8 = r5.zzd     // Catch:{ all -> 0x014f }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x014f }
            r4.put(r7, r8)     // Catch:{ all -> 0x014f }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x014f }
            r4.put(r15, r7)     // Catch:{ all -> 0x014f }
            java.lang.String r7 = "data"
            r4.put(r7, r3)     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "realtime"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x014f }
            r4.put(r3, r7)     // Catch:{ all -> 0x014f }
            android.database.sqlite.SQLiteDatabase r3 = r2.zzh()     // Catch:{ SQLiteException -> 0x09f4 }
            r7 = 0
            long r3 = r3.insert(r6, r7, r4)     // Catch:{ SQLiteException -> 0x09f4 }
            r6 = -1
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x09f7
            com.google.android.gms.measurement.internal.zzft r3 = r2.zzs     // Catch:{ SQLiteException -> 0x09f4 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzay()     // Catch:{ SQLiteException -> 0x09f4 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzd()     // Catch:{ SQLiteException -> 0x09f4 }
            java.lang.String r4 = "Failed to insert raw event (got -1). appId"
            java.lang.String r6 = r5.zza     // Catch:{ SQLiteException -> 0x09f4 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzej.zzn(r6)     // Catch:{ SQLiteException -> 0x09f4 }
            r3.zzb(r4, r6)     // Catch:{ SQLiteException -> 0x09f4 }
            goto L_0x0a4d
        L_0x09f4:
            r0 = move-exception
            r3 = r0
            goto L_0x09fc
        L_0x09f7:
            r3 = 0
            r1.zza = r3     // Catch:{ all -> 0x014f }
            goto L_0x0a4d
        L_0x09fc:
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "Error storing raw event. appId"
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x014f }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzej.zzn(r5)     // Catch:{ all -> 0x014f }
            r2.zzc(r4, r5, r3)     // Catch:{ all -> 0x014f }
            goto L_0x0a4d
        L_0x0a12:
            r0 = move-exception
        L_0x0a13:
            r2 = r0
            goto L_0x0a38
        L_0x0a15:
            r0 = move-exception
        L_0x0a16:
            r2 = r0
            goto L_0x0a20
        L_0x0a18:
            r0 = move-exception
            r16 = r2
            goto L_0x0a13
        L_0x0a1c:
            r0 = move-exception
            r16 = r2
            goto L_0x0a16
        L_0x0a20:
            com.google.android.gms.measurement.internal.zzft r3 = r3.zzs     // Catch:{ IOException -> 0x0a12 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzay()     // Catch:{ IOException -> 0x0a12 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzd()     // Catch:{ IOException -> 0x0a12 }
            java.lang.String r4 = "Error storing raw event metadata. appId"
            java.lang.String r5 = r7.zzy()     // Catch:{ IOException -> 0x0a12 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzej.zzn(r5)     // Catch:{ IOException -> 0x0a12 }
            r3.zzc(r4, r5, r2)     // Catch:{ IOException -> 0x0a12 }
            throw r2     // Catch:{ IOException -> 0x0a12 }
        L_0x0a38:
            com.google.android.gms.measurement.internal.zzej r3 = r33.zzay()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzd()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r5 = r16.zzak()     // Catch:{ all -> 0x014f }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzej.zzn(r5)     // Catch:{ all -> 0x014f }
            r3.zzc(r4, r5, r2)     // Catch:{ all -> 0x014f }
        L_0x0a4d:
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze     // Catch:{ all -> 0x014f }
            zzak(r2)     // Catch:{ all -> 0x014f }
            r2.zzC()     // Catch:{ all -> 0x014f }
            com.google.android.gms.measurement.internal.zzak r2 = r1.zze
            zzak(r2)
            r2.zzx()
            r33.zzaf()
            com.google.android.gms.measurement.internal.zzej r2 = r33.zzay()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzj()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r28
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zzb(r4, r3)
            return
        L_0x0a80:
            com.google.android.gms.measurement.internal.zzak r3 = r1.zze
            zzak(r3)
            r3.zzx()
            throw r2
        L_0x0a89:
            r1.zzd(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkr.zzX(com.google.android.gms.measurement.internal.zzau, com.google.android.gms.measurement.internal.zzp):void");
    }

    public boolean zzY() {
        this.zzaz().zzg();
        final FileLock fileLock = zzw;
        if (null == fileLock || !fileLock.isValid()) {
            zze.zzs.zzf();
            try {
                final FileChannel channel = new RandomAccessFile(new File(zzn.zzau().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                zzx = channel;
                final FileLock tryLock = channel.tryLock();
                zzw = tryLock;
                if (null != tryLock) {
                    this.zzay().zzj().zza("Storage concurrent access okay");
                    return true;
                }
                this.zzay().zzd().zza("Storage concurrent data access panic");
                return false;
            } catch (final FileNotFoundException e2) {
                this.zzay().zzd().zzb("Failed to acquire storage lock", e2);
                return false;
            } catch (final IOException e3) {
                this.zzay().zzd().zzb("Failed to access storage lock file", e3);
                return false;
            } catch (final OverlappingFileLockException e4) {
                this.zzay().zzk().zzb("Storage lock already acquired", e4);
                return false;
            }
        } else {
            this.zzay().zzj().zza("Storage concurrent access okay");
            return true;
        }
    }

    
    public long zza() {
        final long currentTimeMillis = this.zzav().currentTimeMillis();
        final zzjo zzjo = zzk;
        zzjo.zzW();
        zzjo.zzg();
        long zza2 = zzjo.zze.zza();
        if (0 == zza2) {
            zza2 = ((long) zzjo.zzs.zzv().zzF().nextInt(86400000)) + 1;
            zzjo.zze.zzb(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    public Context zzau() {
        return zzn.zzau();
    }

    public Clock zzav() {
        return Preconditions.checkNotNull(zzn).zzav();
    }

    public zzaa zzaw() {
        throw null;
    }

    public zzej zzay() {
        return Preconditions.checkNotNull(zzn).zzay();
    }

    public zzfq zzaz() {
        return Preconditions.checkNotNull(zzn).zzaz();
    }
    public zzg zzd(final zzp zzp2) {
        final String str;
        this.zzaz().zzg();
        this.zzB();
        Preconditions.checkNotNull(zzp2);
        Preconditions.checkNotEmpty(zzp2.zza);
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        zzg zzj2 = zzak.zzj(zzp2.zza);
        final zzah zzc2 = this.zzh(zzp2.zza).zzc(zzah.zzb(zzp2.zzv));
        final zzag zzag = com.google.android.gms.measurement.internal.zzag.AD_STORAGE;
        if (zzc2.zzi(zzag)) {
            str = zzk.zzf(zzp2.zza);
        } else {
            str = "";
        }
        if (null == zzj2) {
            zzj2 = new zzg(zzn, zzp2.zza);
            if (zzc2.zzi(com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE)) {
                zzj2.zzH(this.zzw(zzc2));
            }
            if (zzc2.zzi(zzag)) {
                zzj2.zzae(str);
            }
        } else if (zzc2.zzi(zzag) && null != str && !str.equals(zzj2.zzA())) {
            zzj2.zzae(str);
            zzna.zzc();
            final zzaf zzg2 = this.zzg();
            final zzdv zzdv = zzdw.zzan;
            if (!zzg2.zzs(null, zzdv) || !this.zzg().zzs(null, zzdw.zzas)) {
                zzj2.zzH(this.zzw(zzc2));
            } else if (!"00000000-0000-0000-0000-000000000000".equals(zzk.zzd(zzp2.zza, zzc2).first)) {
                zzj2.zzH(this.zzw(zzc2));
            }
            zzna.zzc();
            if (this.zzg().zzs(null, zzdv) && !"00000000-0000-0000-0000-000000000000".equals(zzk.zzd(zzp2.zza, zzc2).first)) {
                final zzak zzak2 = zze;
                zzkr.zzak(zzak2);
                if (null != zzak2.zzp(zzp2.zza, "_id")) {
                    final zzak zzak3 = zze;
                    zzkr.zzak(zzak3);
                    if (null == zzak3.zzp(zzp2.zza, "_lair")) {
                        final zzkw zzkw = new zzkw(zzp2.zza, "auto", "_lair", this.zzav().currentTimeMillis(), 1L);
                        final zzak zzak4 = zze;
                        zzkr.zzak(zzak4);
                        zzak4.zzL(zzkw);
                    }
                }
            }
        } else if (TextUtils.isEmpty(zzj2.zzu()) && zzc2.zzi(com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE)) {
            zzj2.zzH(this.zzw(zzc2));
        }
        zzj2.zzW(zzp2.zzb);
        zzj2.zzE(zzp2.zzq);
        if (!TextUtils.isEmpty(zzp2.zzk)) {
            zzj2.zzV(zzp2.zzk);
        }
        final long j2 = zzp2.zze;
        if (0 != j2) {
            zzj2.zzX(j2);
        }
        if (!TextUtils.isEmpty(zzp2.zzc)) {
            zzj2.zzJ(zzp2.zzc);
        }
        zzj2.zzK(zzp2.zzj);
        final String str2 = zzp2.zzd;
        if (null != str2) {
            zzj2.zzI(str2);
        }
        zzj2.zzS(zzp2.zzf);
        zzj2.zzac(zzp2.zzh);
        if (!TextUtils.isEmpty(zzp2.zzg)) {
            zzj2.zzY(zzp2.zzg);
        }
        if (!this.zzg().zzs(null, zzdw.zzah)) {
            zzj2.zzG(zzp2.zzl);
        }
        zzj2.zzF(zzp2.zzo);
        zzj2.zzad(zzp2.zzr);
        zzj2.zzT(zzp2.zzs);
        zznv.zzc();
        if (this.zzg().zzs(null, zzdw.zzaz)) {
            zzj2.zzaf(zzp2.zzt);
        } else {
            zznv.zzc();
            if (this.zzg().zzs(null, zzdw.zzay)) {
                zzj2.zzaf(null);
            }
        }
        if (zzj2.zzai()) {
            final zzak zzak5 = zze;
            zzkr.zzak(zzak5);
            zzak5.zzD(zzj2);
        }
        return zzj2;
    }

    public zzz zzf() {
        final zzz zzz2 = zzh;
        zzkr.zzak(zzz2);
        return zzz2;
    }

    public zzaf zzg() {
        return Preconditions.checkNotNull(zzn).zzf();
    }
    public zzah zzh(final String str) {
        final String str2;
        zzah zzah = com.google.android.gms.measurement.internal.zzah.zza;
        this.zzaz().zzg();
        this.zzB();
        final zzah zzah2 = (zzah) zzB.get(str);
        if (null != zzah2) {
            return zzah2;
        }
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        Preconditions.checkNotNull(str);
        zzak.zzg();
        zzak.zzW();
        final Cursor cursor = null;
        try {
            final Cursor rawQuery = zzak.zzh().rawQuery("select consent_state from consent_settings where app_id=? limit 1;", new String[]{str});
            if (rawQuery.moveToFirst()) {
                str2 = rawQuery.getString(0);
                rawQuery.close();
            } else {
                rawQuery.close();
                str2 = "G1";
            }
            final zzah zzb2 = com.google.android.gms.measurement.internal.zzah.zzb(str2);
            this.zzU(str, zzb2);
            return zzb2;
        } catch (final SQLiteException e2) {
            zzak.zzs.zzay().zzd().zzc("Database error", "select consent_state from consent_settings where app_id=? limit 1;", e2);
            throw e2;
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }

    public zzak zzi() {
        final zzak zzak = zze;
        zzkr.zzak(zzak);
        return zzak;
    }

    public zzee zzj() {
        return zzn.zzj();
    }

    public zzep zzl() {
        final zzep zzep = zzd;
        zzkr.zzak(zzep);
        return zzep;
    }

    public zzer zzm() {
        final zzer zzer = zzf;
        if (null != zzer) {
            return zzer;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public zzfk zzo() {
        final zzfk zzfk = zzc;
        zzkr.zzak(zzfk);
        return zzfk;
    }

    
    public zzft zzq() {
        return zzn;
    }

    public zzid zzr() {
        final zzid zzid = zzj;
        zzkr.zzak(zzid);
        return zzid;
    }

    public zzjo zzs() {
        return zzk;
    }

    public zzkt zzu() {
        final zzkt zzkt = zzi;
        zzkr.zzak(zzkt);
        return zzkt;
    }

    public zzky zzv() {
        return Preconditions.checkNotNull(zzn).zzv();
    }
    public String zzw(final zzah zzah) {
        if (!zzah.zzi(zzag.ANALYTICS_STORAGE)) {
            return null;
        }
        final byte[] bArr = new byte[16];
        this.zzv().zzF().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    
    public String zzx(final zzp zzp2) {
        try {
            return (String) this.zzaz().zzh(new zzkm(this, zzp2)).get(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, TimeUnit.MILLISECONDS);
        } catch (final InterruptedException | ExecutionException | TimeoutException e2) {
            this.zzay().zzd().zzc("Failed to get app instance id. appId", zzej.zzn(zzp2.zza), e2);
            return null;
        }
    }
    public void zzz(final Runnable runnable) {
        this.zzaz().zzg();
        if (null == this.zzq) {
            zzq = new ArrayList();
        }
        zzq.add(runnable);
    }
}
