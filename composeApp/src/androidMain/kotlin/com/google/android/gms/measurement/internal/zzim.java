package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzim extends zzf {
    @VisibleForTesting
    zzif zza;
    private volatile zzif zzb;
    private volatile zzif zzc;
    private final Map zzd = new ConcurrentHashMap();
    @GuardedBy("activityLock")
    private Activity zze;
    @GuardedBy("activityLock")
    private volatile boolean zzf;
    private volatile zzif zzg;

    public zzif zzh;
    @GuardedBy("activityLock")
    private boolean zzi;
    private final Object zzj = new Object();
    @GuardedBy("this")
    private zzif zzk;
    @GuardedBy("this")
    private String zzl;

    public zzim(zzft zzft) {
        super(zzft);
    }

    @MainThread
    private void zzA(Activity activity, zzif zzif, boolean z) {
        zzif zzif2;
        zzif zzif3;
        zzif zzif4 = zzif;
        if (null == zzb) {
            zzif2 = this.zzc;
        } else {
            zzif2 = this.zzb;
        }
        zzif zzif5 = zzif2;
        if (null == zzif4.zzb) {
            zzif3 = new zzif(zzif4.zza, null != activity ? zzl(activity.getClass(), "Activity") : null, zzif4.zzc, zzif4.zze, zzif4.zzf);
        } else {
            zzif3 = zzif4;
        }
        this.zzc = this.zzb;
        this.zzb = zzif3;
        this.zzs.zzaz().zzp(new zzih(this, zzif3, zzif5, this.zzs.zzav().elapsedRealtime(), z));
    }


    @WorkerThread
    public void zzB(zzif zzif, zzif zzif2, long j2, boolean z, Bundle bundle) {
        String str;
        long j3;
        long j4;
        zzif zzif3 = zzif;
        zzif zzif4 = zzif2;
        long j5 = j2;
        Bundle bundle2 = bundle;
        zzg();
        boolean z2 = false;
        boolean z3 = null == zzif4 || zzif4.zzc != zzif3.zzc || !zzky.zzak(zzif4.zzb, zzif3.zzb) || !zzky.zzak(zzif4.zza, zzif3.zza);
        if (z && null != zza) {
            z2 = true;
        }
        if (z3) {
            Bundle bundle3 = null != bundle2 ? new Bundle(bundle2) : new Bundle();
            zzky.zzJ(zzif3, bundle3, true);
            if (null != zzif4) {
                String str2 = zzif4.zza;
                if (null != str2) {
                    bundle3.putString("_pn", str2);
                }
                String str3 = zzif4.zzb;
                if (null != str3) {
                    bundle3.putString("_pc", str3);
                }
                bundle3.putLong("_pi", zzif4.zzc);
            }
            if (z2) {
                zzka zzka = this.zzs.zzu().zzb;
                long j6 = j5 - zzka.zzb;
                zzka.zzb = j5;
                if (0 < j6) {
                    this.zzs.zzv().zzH(bundle3, j6);
                }
            }
            if (!this.zzs.zzf().zzu()) {
                bundle3.putLong("_mst", 1);
            }
            if (!zzif3.zze) {
                str = "auto";
            } else {
                str = "app";
            }
            String str4 = str;
            long currentTimeMillis = this.zzs.zzav().currentTimeMillis();
            if (zzif3.zze) {
                j4 = currentTimeMillis;
                long j7 = zzif3.zzf;
                if (0 != j7) {
                    j3 = j7;
                    this.zzs.zzq().zzH(str4, "_vs", j3, bundle3);
                }
            } else {
                j4 = currentTimeMillis;
            }
            j3 = j4;
            this.zzs.zzq().zzH(str4, "_vs", j3, bundle3);
        }
        if (z2) {
            zzC(this.zza, true, j5);
        }
        this.zza = zzif3;
        if (zzif3.zze) {
            this.zzh = zzif3;
        }
        this.zzs.zzt().zzG(zzif3);
    }


    @WorkerThread
    public void zzC(zzif zzif, boolean z, long j2) {
        this.zzs.zzd().zzf(this.zzs.zzav().elapsedRealtime());
        if (this.zzs.zzu().zzb.zzd(null != zzif && zzif.zzd, z, j2) && null != zzif) {
            zzif.zzd = false;
        }
    }

    static void zzp(zzim zzim, Bundle bundle, zzif zzif, zzif zzif2, long j2) {
        bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
        bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        zzim.zzB(zzif, zzif2, j2, true, zzim.zzs.zzv().zzy(null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, null, false));
    }

    @MainThread
    private zzif zzz(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzif zzif = (zzif) this.zzd.get(activity);
        if (null == zzif) {
            zzif zzif2 = new zzif(null, zzl(activity.getClass(), "Activity"), this.zzs.zzv().zzq());
            this.zzd.put(activity, zzif2);
            zzif = zzif2;
        }
        return null != zzg ? this.zzg : zzif;
    }


    public boolean zzf() {
        return false;
    }

    public zzif zzi() {
        return this.zzb;
    }

    @WorkerThread
    public zzif zzj(boolean z) {
        zza();
        zzg();
        if (!z) {
            return this.zza;
        }
        zzif zzif = this.zza;
        return null != zzif ? zzif : this.zzh;
    }


    @VisibleForTesting
    public String zzl(Class cls, String str) {
        String str2;
        String canonicalName = cls.getCanonicalName();
        if (null == canonicalName) {
            return "Activity";
        }
        String[] split = canonicalName.split("\\.");
        int length = split.length;
        if (0 < length) {
            str2 = split[length - 1];
        } else {
            str2 = "";
        }
        int length2 = str2.length();
        this.zzs.zzf();
        if (100 >= length2) {
            return str2;
        }
        this.zzs.zzf();
        return str2.substring(0, 100);
    }

    @MainThread
    public void zzr(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (this.zzs.zzf().zzu() && null != bundle && null != (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service"))) {
            this.zzd.put(activity, new zzif(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong(Name.MARK)));
        }
    }

    @MainThread
    public void zzs(Activity activity) {
        synchronized (this.zzj) {
            try {
                if (activity == this.zze) {
                    this.zze = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (this.zzs.zzf().zzu()) {
            this.zzd.remove(activity);
        }
    }

    @MainThread
    public void zzt(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        if (!this.zzs.zzf().zzu()) {
            this.zzb = null;
            this.zzs.zzaz().zzp(new zzij(this, elapsedRealtime));
            return;
        }
        zzif zzz = zzz(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        this.zzs.zzaz().zzp(new zzik(this, zzz, elapsedRealtime));
    }

    @MainThread
    public void zzu(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (this.zzs.zzf().zzu()) {
                    this.zzg = null;
                    this.zzs.zzaz().zzp(new zzil(this));
                }
            }
        }
        if (!this.zzs.zzf().zzu()) {
            this.zzb = this.zzg;
            this.zzs.zzaz().zzp(new zzii(this));
            return;
        }
        zzA(activity, zzz(activity), false);
        zzd zzd2 = this.zzs.zzd();
        zzd2.zzs.zzaz().zzp(new zzc(zzd2, zzd2.zzs.zzav().elapsedRealtime()));
    }

    @MainThread
    public void zzv(Activity activity, Bundle bundle) {
        zzif zzif;
        if (this.zzs.zzf().zzu() && null != bundle && null != (zzif = (zzif) zzd.get(activity))) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong(Name.MARK, zzif.zzc);
            bundle2.putString("name", zzif.zza);
            bundle2.putString("referrer_name", zzif.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    /*  WARNING: Code restructure failed: missing block: B:24:0x0088, code lost:
        if (r5.length() <= 100) goto L_0x00a3;
     */
    /*  WARNING: Code restructure failed: missing block: B:31:0x00b4, code lost:
        if (r6.length() <= 100) goto L_0x00cf;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzw(@androidx.annotation.NonNull android.app.Activity r4, @androidx.annotation.Size(max = 36, min = 1) java.lang.String r5, @androidx.annotation.Size(max = 36, min = 1) java.lang.String r6) {
        /*
            r3 = this;
            com.google.android.gms.measurement.internal.zzft r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            boolean r0 = r0.zzu()
            if (r0 != 0) goto L_0x001c
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called while screen reporting is disabled."
            r4.zza(r5)
            return
        L_0x001c:
            com.google.android.gms.measurement.internal.zzif r0 = r3.zzb
            if (r0 != 0) goto L_0x0030
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called while no activity active"
            r4.zza(r5)
            return
        L_0x0030:
            java.util.Map r1 = r3.zzd
            java.lang.Object r1 = r1.get(r4)
            if (r1 != 0) goto L_0x0048
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen must be called with an activity in the activity lifecycle"
            r4.zza(r5)
            return
        L_0x0048:
            if (r6 != 0) goto L_0x0054
            java.lang.Class r6 = r4.getClass()
            java.lang.String r1 = "Activity"
            java.lang.String r6 = r3.zzl(r6, r1)
        L_0x0054:
            java.lang.String r1 = r0.zzb
            boolean r1 = com.google.android.gms.measurement.internal.zzky.zzak(r1, r6)
            java.lang.String r0 = r0.zza
            boolean r0 = com.google.android.gms.measurement.internal.zzky.zzak(r0, r5)
            if (r1 == 0) goto L_0x0075
            if (r0 != 0) goto L_0x0065
            goto L_0x0075
        L_0x0065:
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called with the same class and name"
            r4.zza(r5)
            return
        L_0x0075:
            r0 = 100
            if (r5 == 0) goto L_0x00a3
            int r1 = r5.length()
            if (r1 <= 0) goto L_0x008b
            com.google.android.gms.measurement.internal.zzft r1 = r3.zzs
            r1.zzf()
            int r1 = r5.length()
            if (r1 > r0) goto L_0x008b
            goto L_0x00a3
        L_0x008b:
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            int r5 = r5.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "Invalid screen name length in setCurrentScreen. Length"
            r4.zzb(r6, r5)
            return
        L_0x00a3:
            if (r6 == 0) goto L_0x00cf
            int r1 = r6.length()
            if (r1 <= 0) goto L_0x00b7
            com.google.android.gms.measurement.internal.zzft r1 = r3.zzs
            r1.zzf()
            int r1 = r6.length()
            if (r1 > r0) goto L_0x00b7
            goto L_0x00cf
        L_0x00b7:
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzl()
            int r5 = r6.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "Invalid class name length in setCurrentScreen. Length"
            r4.zzb(r6, r5)
            return
        L_0x00cf:
            com.google.android.gms.measurement.internal.zzft r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()
            if (r5 != 0) goto L_0x00de
            java.lang.String r1 = "null"
            goto L_0x00df
        L_0x00de:
            r1 = r5
        L_0x00df:
            java.lang.String r2 = "Setting current screen to name, class"
            r0.zzc(r2, r1, r6)
            com.google.android.gms.measurement.internal.zzif r0 = new com.google.android.gms.measurement.internal.zzif
            com.google.android.gms.measurement.internal.zzft r1 = r3.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            long r1 = r1.zzq()
            r0.<init>(r5, r6, r1)
            java.util.Map r5 = r3.zzd
            r5.put(r4, r0)
            r5 = 1
            r3.zzA(r4, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzim.zzw(android.app.Activity, java.lang.String, java.lang.String):void");
    }

    /*  WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r2 > 100) goto L_0x0036;
     */
    /*  WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        if (r4 > 100) goto L_0x0068;
     */
    /*  WARNING: Code restructure failed: missing block: B:45:0x00c4, code lost:
        r0 = r12.zzs.zzay().zzj();
     */
    /*  WARNING: Code restructure failed: missing block: B:46:0x00ce, code lost:
        if (r3 != null) goto L_0x00d3;
     */
    /*  WARNING: Code restructure failed: missing block: B:47:0x00d0, code lost:
        r1 = "null";
     */
    /*  WARNING: Code restructure failed: missing block: B:48:0x00d3, code lost:
        r1 = r3;
     */
    /*  WARNING: Code restructure failed: missing block: B:49:0x00d4, code lost:
        if (r4 != null) goto L_0x00d9;
     */
    /*  WARNING: Code restructure failed: missing block: B:50:0x00d6, code lost:
        r2 = "null";
     */
    /*  WARNING: Code restructure failed: missing block: B:51:0x00d9, code lost:
        r2 = r4;
     */
    /*  WARNING: Code restructure failed: missing block: B:52:0x00da, code lost:
        r0.zzc("Logging screen view with name, class", r1, r2);
     */
    /*  WARNING: Code restructure failed: missing block: B:53:0x00e1, code lost:
        if (r12.zzb != null) goto L_0x00e6;
     */
    /*  WARNING: Code restructure failed: missing block: B:54:0x00e3, code lost:
        r0 = r12.zzc;
     */
    /*  WARNING: Code restructure failed: missing block: B:55:0x00e6, code lost:
        r0 = r12.zzb;
     */
    /*  WARNING: Code restructure failed: missing block: B:56:0x00e8, code lost:
        r2 = new com.google.android.gms.measurement.internal.zzif(r3, r4, r12.zzs.zzv().zzq(), true, r14);
        r12.zzb = r2;
        r12.zzc = r0;
        r12.zzg = r2;
        r12.zzs.zzaz().zzp(new com.google.android.gms.measurement.internal.zzig(r12, r13, r2, r0, r12.zzs.zzav().elapsedRealtime()));
     */
    /*  WARNING: Code restructure failed: missing block: B:57:0x011d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzx(android.os.Bundle r13, long r14) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.zzj
            monitor-enter(r0)
            boolean r1 = r12.zzi     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x001b
            com.google.android.gms.measurement.internal.zzft r13 = r12.zzs     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzej r13 = r13.zzay()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzeh r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Cannot log screen view event when the app is in the background."
            r13.zza(r14)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x0018:
            r13 = move-exception
            goto L_0x011e
        L_0x001b:
            java.lang.String r1 = "screen_name"
            java.lang.String r3 = r13.getString(r1)     // Catch:{ all -> 0x0018 }
            r1 = 100
            if (r3 == 0) goto L_0x004f
            int r2 = r3.length()     // Catch:{ all -> 0x0018 }
            if (r2 <= 0) goto L_0x0036
            int r2 = r3.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzft r4 = r12.zzs     // Catch:{ all -> 0x0018 }
            r4.zzf()     // Catch:{ all -> 0x0018 }
            if (r2 <= r1) goto L_0x004f
        L_0x0036:
            com.google.android.gms.measurement.internal.zzft r13 = r12.zzs     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzej r13 = r13.zzay()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzeh r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Invalid screen name length for screen view. Length"
            int r15 = r3.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x0018 }
            r13.zzb(r14, r15)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x004f:
            java.lang.String r2 = "screen_class"
            java.lang.String r2 = r13.getString(r2)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0081
            int r4 = r2.length()     // Catch:{ all -> 0x0018 }
            if (r4 <= 0) goto L_0x0068
            int r4 = r2.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzft r5 = r12.zzs     // Catch:{ all -> 0x0018 }
            r5.zzf()     // Catch:{ all -> 0x0018 }
            if (r4 <= r1) goto L_0x0081
        L_0x0068:
            com.google.android.gms.measurement.internal.zzft r13 = r12.zzs     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzej r13 = r13.zzay()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzeh r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Invalid screen class length for screen view. Length"
            int r15 = r2.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x0018 }
            r13.zzb(r14, r15)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x0081:
            if (r2 != 0) goto L_0x0096
            android.app.Activity r1 = r12.zze     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0093
            java.lang.Class r1 = r1.getClass()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Activity"
            java.lang.String r1 = r12.zzl(r1, r2)     // Catch:{ all -> 0x0018 }
        L_0x0091:
            r4 = r1
            goto L_0x0097
        L_0x0093:
            java.lang.String r1 = "Activity"
            goto L_0x0091
        L_0x0096:
            r4 = r2
        L_0x0097:
            com.google.android.gms.measurement.internal.zzif r1 = r12.zzb     // Catch:{ all -> 0x0018 }
            boolean r2 = r12.zzf     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x00c3
            if (r1 == 0) goto L_0x00c3
            r2 = 0
            r12.zzf = r2     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = r1.zzb     // Catch:{ all -> 0x0018 }
            boolean r2 = com.google.android.gms.measurement.internal.zzky.zzak(r2, r4)     // Catch:{ all -> 0x0018 }
            java.lang.String r1 = r1.zza     // Catch:{ all -> 0x0018 }
            boolean r1 = com.google.android.gms.measurement.internal.zzky.zzak(r1, r3)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x00c3
            if (r1 == 0) goto L_0x00c3
            com.google.android.gms.measurement.internal.zzft r13 = r12.zzs     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzej r13 = r13.zzay()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzeh r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Ignoring call to log screen view event with duplicate parameters."
            r13.zza(r14)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x00c3:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzft r0 = r12.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()
            if (r3 != 0) goto L_0x00d3
            java.lang.String r1 = "null"
            goto L_0x00d4
        L_0x00d3:
            r1 = r3
        L_0x00d4:
            if (r4 != 0) goto L_0x00d9
            java.lang.String r2 = "null"
            goto L_0x00da
        L_0x00d9:
            r2 = r4
        L_0x00da:
            java.lang.String r5 = "Logging screen view with name, class"
            r0.zzc(r5, r1, r2)
            com.google.android.gms.measurement.internal.zzif r0 = r12.zzb
            if (r0 != 0) goto L_0x00e6
            com.google.android.gms.measurement.internal.zzif r0 = r12.zzc
            goto L_0x00e8
        L_0x00e6:
            com.google.android.gms.measurement.internal.zzif r0 = r12.zzb
        L_0x00e8:
            com.google.android.gms.measurement.internal.zzif r1 = new com.google.android.gms.measurement.internal.zzif
            com.google.android.gms.measurement.internal.zzft r2 = r12.zzs
            com.google.android.gms.measurement.internal.zzky r2 = r2.zzv()
            long r5 = r2.zzq()
            r7 = 1
            r2 = r1
            r8 = r14
            r2.<init>(r3, r4, r5, r7, r8)
            r12.zzb = r1
            r12.zzc = r0
            r12.zzg = r1
            com.google.android.gms.measurement.internal.zzft r14 = r12.zzs
            com.google.android.gms.common.util.Clock r14 = r14.zzav()
            long r10 = r14.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzft r14 = r12.zzs
            com.google.android.gms.measurement.internal.zzfq r14 = r14.zzaz()
            com.google.android.gms.measurement.internal.zzig r15 = new com.google.android.gms.measurement.internal.zzig
            r5 = r15
            r6 = r12
            r7 = r13
            r8 = r1
            r9 = r0
            r5.<init>(r6, r7, r8, r9, r10)
            r14.zzp(r15)
            return
        L_0x011e:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzim.zzx(android.os.Bundle, long):void");
    }

    @WorkerThread
    public void zzy(String str, zzif zzif) {
        zzg();
        synchronized (this) {
            try {
                String str2 = this.zzl;
                if (null != str2 && !str2.equals(str)) {
                    if (null != zzif) {
                    }
                }
                this.zzl = str;
                this.zzk = zzif;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
