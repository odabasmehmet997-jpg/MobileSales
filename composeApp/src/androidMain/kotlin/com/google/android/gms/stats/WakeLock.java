package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzb;
import com.google.android.gms.internal.stats.zzc;
import com.google.android.gms.internal.stats.zzi;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ShowFirstParty
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-stats@@17.0.1 */
public class WakeLock {
    private static final long zzb = TimeUnit.DAYS.toMillis(366);
    private static volatile ScheduledExecutorService zzc;
    private static final Object zzd = new Object();
    private static final zzd zze = new zzb();
    @GuardedBy("acquireReleaseLock")
    zzb zza;
    private final Object zzf = new Object();
    @GuardedBy("acquireReleaseLock")
    private final PowerManager.WakeLock zzg;
    @GuardedBy("acquireReleaseLock")
    private int zzh;
    @GuardedBy("acquireReleaseLock")
    private Future<?> zzi;
    @GuardedBy("acquireReleaseLock")
    private long zzj;
    @GuardedBy("acquireReleaseLock")
    private final Set<zze> zzk = new HashSet();
    @GuardedBy("acquireReleaseLock")
    private boolean zzl = true;
    @GuardedBy("acquireReleaseLock")
    private int zzm;
    private final Clock zzn = DefaultClock.getInstance();
    private WorkSource zzo;
    private final String zzp;
    private final String zzq;
    private final Context zzr;
    @GuardedBy("acquireReleaseLock")
    private final Map<String, zzc> zzs = new HashMap();
    private final AtomicInteger zzt = new AtomicInteger(0);
    private final ScheduledExecutorService zzu;

    @KeepForSdk
    public WakeLock(@NonNull Context context, int i2, @NonNull String str) {
        String str2;
        String packageName = context.getPackageName();
        Preconditions.checkNotNull(context, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(str, "WakeLock: wakeLockName must not be empty");
        this.zzr = context.getApplicationContext();
        this.zzq = str;
        this.zza = null;
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = str;
            if (0 != valueOf.length()) {
                str2 = "*gcore*:" + valueOf;
            } else {
                str2 = "*gcore*:";
            }
            this.zzp = str2;
        } else {
            this.zzp = str;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (null != powerManager) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(i2, str);
            this.zzg = newWakeLock;
            if (WorkSourceUtil.hasWorkSourcePermission(context)) {
                WorkSource fromPackage = WorkSourceUtil.fromPackage(context, Strings.isEmptyOrWhitespace(packageName) ? context.getPackageName() : packageName);
                this.zzo = fromPackage;
                if (null != fromPackage) {
                    zze(newWakeLock, fromPackage);
                }
            }
            ScheduledExecutorService scheduledExecutorService = zzc;
            if (null == scheduledExecutorService) {
                synchronized (zzd) {
                    try {
                        scheduledExecutorService = zzc;
                        if (null == scheduledExecutorService) {
                            zzh.zza();
                            scheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
                            zzc = scheduledExecutorService;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            this.zzu = scheduledExecutorService;
            return;
        }
        throw new zzi("expected a non-null reference".substring(0, 29));
    }

    public static void zza(@NonNull WakeLock wakeLock) {
        synchronized (wakeLock.zzf) {
            try {
                if (wakeLock.isHeld()) {
                    Log.e("WakeLock", wakeLock.zzp + " ** IS FORCE-RELEASED ON TIMEOUT **");
                    wakeLock.zzc();
                    if (wakeLock.isHeld()) {
                        wakeLock.zzh = 1;
                        wakeLock.zzd(0);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @GuardedBy("acquireReleaseLock")
    private final String zzb(String str) {
        if (this.zzl) {
            TextUtils.isEmpty(null);
        }
        return null;
    }

    @GuardedBy("acquireReleaseLock")
    private final void zzc() {
        if (!this.zzk.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.zzk);
            this.zzk.clear();
            if (0 < arrayList.size()) {
                zze zze2 = (zze) arrayList.get(0);
                throw null;
            }
        }
    }

    private final void zzd(int r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zzd(int):void");
    }

    private static void zze(PowerManager.WakeLock wakeLock, WorkSource workSource) {
        try {
            wakeLock.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e2) {
            Log.wtf("WakeLock", e2.toString());
        }
    }

    @KeepForSdk
    public void acquire(long j2) {
        this.zzt.incrementAndGet();
        long j3 = zzb;
        long j4 = LocationRequestCompat.PASSIVE_INTERVAL;
        long max = Math.max(Math.min(LocationRequestCompat.PASSIVE_INTERVAL, j3), 1);
        if (0 < j2) {
            max = Math.min(j2, max);
        }
        synchronized (this.zzf) {
            try {
                if (!isHeld()) {
                    this.zza = zzb.zza(false, (zzc) null);
                    this.zzg.acquire();
                    this.zzn.elapsedRealtime();
                }
                this.zzh++;
                this.zzm++;
                zzb(null);
                zzc zzc2 = this.zzs.get(null);
                if (null == zzc2) {
                    zzc2 = new zzc((zzb) null);
                    this.zzs.put((Object) null, zzc2);
                }
                zzc2.zza++;
                long elapsedRealtime = this.zzn.elapsedRealtime();
                if (LocationRequestCompat.PASSIVE_INTERVAL - elapsedRealtime > max) {
                    j4 = elapsedRealtime + max;
                }
                if (j4 > this.zzj) {
                    this.zzj = j4;
                    Future<?> future = this.zzi;
                    if (null != future) {
                        future.cancel(false);
                    }
                    this.zzi = this.zzu.schedule(new zza(this), max, TimeUnit.MILLISECONDS);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public boolean isHeld() {
        boolean z;
        synchronized (this.zzf) {
            z = 0 < zzh;
        }
        return z;
    }

    @KeepForSdk
    public void release() {
        if (0 > zzt.decrementAndGet()) {
            Log.e("WakeLock", this.zzp + " release without a matched acquire!");
        }
        synchronized (this.zzf) {
            try {
                zzb(null);
                if (this.zzs.containsKey(null)) {
                    zzc zzc2 = this.zzs.get(null);
                    if (null != zzc2) {
                        int i2 = zzc2.zza - 1;
                        zzc2.zza = i2;
                        if (0 == i2) {
                            this.zzs.remove(null);
                        }
                    }
                } else {
                    Log.w("WakeLock", this.zzp + " counter does not exist");
                }
                zzd(0);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public void setReferenceCounted(boolean z) {
        synchronized (this.zzf) {
            this.zzl = z;
        }
    }
}
