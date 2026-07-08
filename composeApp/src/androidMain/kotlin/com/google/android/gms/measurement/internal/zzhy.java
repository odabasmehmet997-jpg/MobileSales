package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzoe;
import com.google.android.gms.internal.measurement.zzok;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzhy extends zzf {
    @VisibleForTesting
    zzhx zza;
    final zzr zzb;
    @VisibleForTesting
    private boolean zzc = true;
    private zzgt zzd;
    private final Set zze = new CopyOnWriteArraySet();
    private boolean zzf;
    private final AtomicReference zzg = new AtomicReference();
    private final Object zzh = new Object();
    @GuardedBy("consentLock")
    private zzah zzi = new zzah(null, null);
    @GuardedBy("consentLock")
    private int zzj = 100;
    private final AtomicLong zzk = new AtomicLong(0);
    private long zzl = -1;
    private int zzm = 100;
    private final zzkx zzn = new zzhm(this);

    zzhy(final zzft zzft) {
        super(zzft);
        zzb = new zzr(zzft);
    }


    @WorkerThread
    /* renamed from: zzab */
    public void zzB(final Bundle bundle, final long j2) {
        if (TextUtils.isEmpty(zzs.zzh().zzm())) {
            this.zzR(bundle, 0, j2);
        } else {
            zzs.zzay().zzl().zza("Using developer consent only; google app id found");
        }
    }


    @WorkerThread
    public void zzac(final Boolean bool, final boolean z) {
        this.zzg();
        this.zza();
        zzs.zzay().zzc().zzb("Setting app measurement enabled (FE)", bool);
        zzs.zzm().zzh(bool);
        if (z) {
            final zzey zzm2 = zzs.zzm();
            final zzft zzft = zzm2.zzs;
            zzm2.zzg();
            final SharedPreferences.Editor edit = zzm2.zza().edit();
            if (null != bool) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (zzs.zzK() || (null != bool && !bool.booleanValue())) {
            this.zzad();
        }
    }


    @WorkerThread
    public void zzad() {
        this.zzg();
        final String zza2 = zzs.zzm().zzh.zza();
        if (null != zza2) {
            if ("unset".equals(zza2)) {
                this.zzZ("app", "_npa", null, zzs.zzav().currentTimeMillis());
            } else {
                this.zzZ("app", "_npa", Long.valueOf(!"true".equals(zza2) ? 0 : 1), zzs.zzav().currentTimeMillis());
            }
        }
        if (!zzs.zzJ() || !zzc) {
            zzs.zzay().zzc().zza("Updating Scion state (FE)");
            zzs.zzt().zzI();
            return;
        }
        zzs.zzay().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        this.zzy();
        zzok.zzc();
        if (zzs.zzf().zzs(null, zzdw.zzad)) {
            zzs.zzu().zza.zza();
        }
        zzs.zzaz().zzp(new zzhb(this));
    }

    static void zzv(final zzhy zzhy, final zzah zzah, final int i2, final long j2, final boolean z, final boolean z2) {
        zzhy.zzg();
        zzhy.zza();
        if (j2 > zzhy.zzl || !com.google.android.gms.measurement.internal.zzah.zzj(zzhy.zzm, i2)) {
            final zzey zzm2 = zzhy.zzs.zzm();
            final zzft zzft = zzm2.zzs;
            zzm2.zzg();
            if (zzm2.zzl(i2)) {
                final SharedPreferences.Editor edit = zzm2.zza().edit();
                edit.putString("consent_settings", zzah.zzh());
                edit.putInt("consent_source", i2);
                edit.apply();
                zzhy.zzl = j2;
                zzhy.zzm = i2;
                zzhy.zzs.zzt().zzF(z);
                if (z2) {
                    zzhy.zzs.zzt().zzu(new AtomicReference());
                    return;
                }
                return;
            }
            zzhy.zzs.zzay().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(i2));
            return;
        }
        zzhy.zzs.zzay().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzah);
    }

    public void zzA() {
        if ((zzs.zzau().getApplicationContext() instanceof Application) && null != this.zza) {
            ((Application) zzs.zzau().getApplicationContext()).unregisterActivityLifecycleCallbacks(zza);
        }
    }


    public void zzC(final Bundle bundle) {
        if (null == bundle) {
            zzs.zzm().zzr.zzb(new Bundle());
            return;
        }
        final Bundle zza2 = zzs.zzm().zzr.zza();
        for (final String next : bundle.keySet()) {
            final Object obj = bundle.get(next);
            if (null != obj && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                if (zzs.zzv().zzae(obj)) {
                    zzs.zzv().zzM(zzn, null, 27, null, null, 0);
                }
                zzs.zzay().zzl().zzc("Invalid default event parameter type. Name, value", next, obj);
            } else if (zzky.zzag(next)) {
                zzs.zzay().zzl().zzb("Invalid default event parameter name. Name", next);
            } else if (null == obj) {
                zza2.remove(next);
            } else {
                final zzky zzv = zzs.zzv();
                zzs.zzf();
                if (zzv.zzZ("param", next, 100, obj)) {
                    zzs.zzv().zzN(zza2, next, obj);
                }
            }
        }
        zzs.zzv();
        final int zzc2 = zzs.zzf().zzc();
        if (zza2.size() > zzc2) {
            int i2 = 0;
            for (final String str : new TreeSet(zza2.keySet())) {
                i2++;
                if (i2 > zzc2) {
                    zza2.remove(str);
                }
            }
            zzs.zzv().zzM(zzn, null, 26, null, null, 0);
            zzs.zzay().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        zzs.zzm().zzr.zzb(zza2);
        zzs.zzt().zzH(zza2);
    }

    public void zzD(final String str, final String str2, final Bundle bundle) {
        this.zzE(str, str2, bundle, true, true, zzs.zzav().currentTimeMillis());
    }

    public void zzE(final String str, final String str2, final Bundle bundle, final boolean z, final boolean z2, final long j2) {
        final String str3 = null == str ? "app" : str;
        final Bundle bundle2 = null == bundle ? new Bundle() : bundle;
        final String str4 = str2;
        if (zzky.zzak(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            zzs.zzs().zzx(bundle2, j2);
            return;
        }
        final long j3 = j2;
        boolean z3 = !z2 || null == this.zzd || zzky.zzag(str2);
        this.zzM(str3, str2, j2, bundle2, z2, z3, z, null);
    }

    public void zzF(final String str, final String str2, final Bundle bundle, final String str3) {
        zzft.zzO();
        this.zzM("auto", str2, zzs.zzav().currentTimeMillis(), bundle, false, true, true, str3);
    }


    @WorkerThread
    public void zzG(final String str, final String str2, final Bundle bundle) {
        this.zzg();
        this.zzH(str, str2, zzs.zzav().currentTimeMillis(), bundle);
    }


    @WorkerThread
    public void zzH(final String str, final String str2, final long j2, final Bundle bundle) {
        this.zzg();
        this.zzI(str, str2, j2, bundle, true, null == this.zzd || zzky.zzag(str2), true, null);
    }

    /*  WARNING: type inference failed for: r5v10, types: [java.lang.Object[]] */
    /*  WARNING: type inference failed for: r5v12, types: [java.lang.Object[]] */

    /*  WARNING: Multi-variable type inference failed */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzI(final java.lang.String r21, final java.lang.String r22, final long r23, final android.os.Bundle r25, final boolean r26, final boolean r27, final boolean r28, final java.lang.String r29) {
        /*
            r20 = this;
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r12 = r25
            r13 = 1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            r20.zzg()
            r20.zza()
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            boolean r0 = r0.zzJ()
            if (r0 == 0) goto L_0x0517
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzea r0 = r0.zzh()
            java.util.List r0 = r0.zzn()
            if (r0 == 0) goto L_0x0042
            boolean r0 = r0.contains(r9)
            if (r0 == 0) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()
            java.lang.String r1 = "Dropping non-safelisted event. event name, origin"
            r0.zzc(r1, r9, r8)
            return
        L_0x0042:
            boolean r0 = r7.zzf
            r14 = 0
            if (r0 != 0) goto L_0x00a0
            r7.zzf = r13
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0091 }
            boolean r0 = r0.zzN()     // Catch:{ ClassNotFoundException -> 0x0091 }
            java.lang.String r1 = "com.google.android.gms.tagmanager.TagManagerService"
            if (r0 != 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0091 }
            android.content.Context r0 = r0.zzau()     // Catch:{ ClassNotFoundException -> 0x0091 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0091 }
            java.lang.Class r0 = java.lang.Class.forName(r1, r13, r0)     // Catch:{ ClassNotFoundException -> 0x0091 }
            goto L_0x0066
        L_0x0062:
            java.lang.Class r0 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0091 }
        L_0x0066:
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            java.lang.Class[] r1 = new java.lang.Class[]{r1}     // Catch:{ Exception -> 0x0080 }
            java.lang.String r2 = "initialize"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r1)     // Catch:{ Exception -> 0x0080 }
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs     // Catch:{ Exception -> 0x0080 }
            android.content.Context r1 = r1.zzau()     // Catch:{ Exception -> 0x0080 }
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch:{ Exception -> 0x0080 }
            r0.invoke(r14, r1)     // Catch:{ Exception -> 0x0080 }
            goto L_0x00a0
        L_0x0080:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0091 }
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()     // Catch:{ ClassNotFoundException -> 0x0091 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzk()     // Catch:{ ClassNotFoundException -> 0x0091 }
            java.lang.String r2 = "Failed to invoke Tag Manager's initialize() method"
            r1.zzb(r2, r0)     // Catch:{ ClassNotFoundException -> 0x0091 }
            goto L_0x00a0
        L_0x0091:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzi()
            java.lang.String r1 = "Tag Manager is not found and thus will not be used"
            r0.zza(r1)
        L_0x00a0:
            java.lang.String r0 = "_cmp"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x00cc
            java.lang.String r0 = "gclid"
            boolean r1 = r12.containsKey(r0)
            if (r1 == 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzaw()
            java.lang.String r4 = r12.getString(r0)
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.common.util.Clock r0 = r0.zzav()
            long r5 = r0.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_lgclid"
            r1 = r20
            r1.zzZ(r2, r3, r4, r5)
        L_0x00cc:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            r0.zzaw()
            if (r26 == 0) goto L_0x00ee
            boolean r0 = com.google.android.gms.measurement.internal.zzky.zzal(r22)
            if (r0 == 0) goto L_0x00ee
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r0 = r0.zzv()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzr
            android.os.Bundle r1 = r1.zza()
            r0.zzK(r12, r1)
        L_0x00ee:
            r0 = 40
            r15 = 0
            if (r28 != 0) goto L_0x0177
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzaw()
            java.lang.String r1 = "_iap"
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x0177
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            java.lang.String r2 = "event"
            boolean r3 = r1.zzab(r2, r9)
            r4 = 2
            if (r3 != 0) goto L_0x0110
            goto L_0x012a
        L_0x0110:
            java.lang.String[] r3 = com.google.android.gms.measurement.internal.zzgq.zza
            java.lang.String[] r5 = com.google.android.gms.measurement.internal.zzgq.zzb
            boolean r3 = r1.zzY(r2, r3, r5, r9)
            if (r3 != 0) goto L_0x011d
            r4 = 13
            goto L_0x012a
        L_0x011d:
            com.google.android.gms.measurement.internal.zzft r3 = r1.zzs
            r3.zzf()
            boolean r1 = r1.zzX(r2, r0, r9)
            if (r1 != 0) goto L_0x0129
            goto L_0x012a
        L_0x0129:
            r4 = r15
        L_0x012a:
            if (r4 == 0) goto L_0x0177
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zze()
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzee r2 = r2.zzj()
            java.lang.String r2 = r2.zzd(r9)
            java.lang.String r3 = "Invalid public event name. Event will not be logged (FE)"
            r1.zzb(r3, r2)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            r2.zzf()
            java.lang.String r0 = r1.zzC(r9, r0, r13)
            if (r9 == 0) goto L_0x015a
            int r15 = r22.length()
        L_0x015a:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzkx r2 = r7.zzn
            r3 = 0
            java.lang.String r5 = "_ev"
            r21 = r1
            r22 = r2
            r23 = r3
            r24 = r4
            r25 = r5
            r26 = r0
            r27 = r15
            r21.zzM(r22, r23, r24, r25, r26, r27)
            return
        L_0x0177:
            com.google.android.gms.internal.measurement.zzpi.zzc()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            com.google.android.gms.measurement.internal.zzdv r2 = com.google.android.gms.measurement.internal.zzdw.zzat
            boolean r1 = r1.zzs(r14, r2)
            java.lang.String r2 = "_sc"
            if (r1 == 0) goto L_0x01ae
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzim r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzj(r15)
            if (r1 == 0) goto L_0x01a3
            boolean r3 = r12.containsKey(r2)
            if (r3 != 0) goto L_0x01a3
            r1.zzd = r13
        L_0x01a3:
            if (r26 == 0) goto L_0x01a9
            if (r28 != 0) goto L_0x01a9
            r3 = r13
            goto L_0x01aa
        L_0x01a9:
            r3 = r15
        L_0x01aa:
            com.google.android.gms.measurement.internal.zzky.zzJ(r1, r12, r3)
            goto L_0x01d1
        L_0x01ae:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzim r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzj(r15)
            if (r1 == 0) goto L_0x01c7
            boolean r3 = r12.containsKey(r2)
            if (r3 != 0) goto L_0x01c7
            r1.zzd = r13
        L_0x01c7:
            if (r26 == 0) goto L_0x01cd
            if (r28 != 0) goto L_0x01cd
            r3 = r13
            goto L_0x01ce
        L_0x01cd:
            r3 = r15
        L_0x01ce:
            com.google.android.gms.measurement.internal.zzky.zzJ(r1, r12, r3)
        L_0x01d1:
            java.lang.String r1 = "am"
            boolean r1 = r1.equals(r8)
            boolean r3 = com.google.android.gms.measurement.internal.zzky.zzag(r22)
            if (r26 == 0) goto L_0x021e
            com.google.android.gms.measurement.internal.zzgt r4 = r7.zzd
            if (r4 == 0) goto L_0x021e
            if (r3 != 0) goto L_0x021e
            if (r1 == 0) goto L_0x01e8
            r16 = r13
            goto L_0x0220
        L_0x01e8:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzee r1 = r1.zzj()
            java.lang.String r1 = r1.zzd(r9)
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzee r2 = r2.zzj()
            java.lang.String r2 = r2.zzb(r12)
            java.lang.String r3 = "Passing event to registered event handler (FE)"
            r0.zzc(r3, r1, r2)
            com.google.android.gms.measurement.internal.zzgt r0 = r7.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            com.google.android.gms.measurement.internal.zzgt r1 = r7.zzd
            r2 = r21
            r3 = r22
            r4 = r25
            r5 = r23
            r1.interceptEvent(r2, r3, r4, r5)
            return
        L_0x021e:
            r16 = r1
        L_0x0220:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            boolean r1 = r1.zzM()
            if (r1 == 0) goto L_0x0516
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            int r1 = r1.zzh(r9)
            if (r1 == 0) goto L_0x027e
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zze()
            com.google.android.gms.measurement.internal.zzft r3 = r7.zzs
            com.google.android.gms.measurement.internal.zzee r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r9)
            java.lang.String r4 = "Invalid event name. Event will not be logged (FE)"
            r2.zzb(r4, r3)
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r2 = r2.zzv()
            com.google.android.gms.measurement.internal.zzft r3 = r7.zzs
            r3.zzf()
            java.lang.String r0 = r2.zzC(r9, r0, r13)
            if (r9 == 0) goto L_0x0262
            int r15 = r22.length()
        L_0x0262:
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r2 = r2.zzv()
            com.google.android.gms.measurement.internal.zzkx r3 = r7.zzn
            java.lang.String r4 = "_ev"
            r21 = r2
            r22 = r3
            r23 = r29
            r24 = r1
            r25 = r4
            r26 = r0
            r27 = r15
            r21.zzM(r22, r23, r24, r25, r26, r27)
            return
        L_0x027e:
            java.lang.String r0 = "_sn"
            java.lang.String r1 = "_si"
            java.lang.String r6 = "_o"
            java.lang.String[] r0 = new java.lang.String[]{r6, r0, r2, r1}
            java.util.List r5 = com.google.android.gms.common.util.CollectionUtils.listOf((T[]) r0)
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r0.zzv()
            r2 = r29
            r3 = r22
            r4 = r25
            r0 = r6
            r6 = r28
            android.os.Bundle r12 = r1.zzy(r2, r3, r4, r5, r6)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzim r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzj(r15)
            r5 = 0
            java.lang.String r4 = "_ae"
            if (r1 == 0) goto L_0x02e4
            boolean r1 = r4.equals(r9)
            if (r1 == 0) goto L_0x02e4
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkc r1 = r1.zzu()
            com.google.android.gms.measurement.internal.zzka r1 = r1.zzb
            com.google.android.gms.measurement.internal.zzkc r2 = r1.zzc
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs
            com.google.android.gms.common.util.Clock r2 = r2.zzav()
            long r2 = r2.elapsedRealtime()
            long r13 = r1.zzb
            long r13 = r2 - r13
            r1.zzb = r2
            int r1 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x02e4
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            r1.zzH(r12, r13)
        L_0x02e4:
            com.google.android.gms.internal.measurement.zzob.zzc()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            com.google.android.gms.measurement.internal.zzdv r2 = com.google.android.gms.measurement.internal.zzdw.zzac
            r13 = 0
            boolean r1 = r1.zzs(r13, r2)
            if (r1 == 0) goto L_0x036f
            java.lang.String r1 = "auto"
            boolean r1 = r1.equals(r8)
            java.lang.String r2 = "_ffr"
            if (r1 != 0) goto L_0x034e
            java.lang.String r1 = "_ssr"
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x034e
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            java.lang.String r2 = r12.getString(r2)
            boolean r3 = com.google.android.gms.common.util.Strings.isEmptyOrWhitespace(r2)
            if (r3 == 0) goto L_0x031a
            r2 = r13
            goto L_0x0320
        L_0x031a:
            if (r2 == 0) goto L_0x0320
            java.lang.String r2 = r2.trim()
        L_0x0320:
            com.google.android.gms.measurement.internal.zzft r3 = r1.zzs
            com.google.android.gms.measurement.internal.zzey r3 = r3.zzm()
            com.google.android.gms.measurement.internal.zzex r3 = r3.zzo
            java.lang.String r3 = r3.zza()
            boolean r3 = com.google.android.gms.measurement.internal.zzky.zzak(r2, r3)
            if (r3 != 0) goto L_0x033e
            com.google.android.gms.measurement.internal.zzft r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzex r1 = r1.zzo
            r1.zzb(r2)
            goto L_0x036f
        L_0x033e:
            com.google.android.gms.measurement.internal.zzft r0 = r1.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()
            java.lang.String r1 = "Not logging duplicate session_start_with_rollout event"
            r0.zza(r1)
            return
        L_0x034e:
            boolean r1 = r4.equals(r9)
            if (r1 == 0) goto L_0x036f
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzft r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzex r1 = r1.zzo
            java.lang.String r1 = r1.zza()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x036f
            r12.putString(r2, r1)
        L_0x036f:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r14.add(r12)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzj
            long r1 = r1.zza()
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x03f3
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            boolean r1 = r1.zzk(r10)
            if (r1 == 0) goto L_0x03f3
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzl
            boolean r1 = r1.zzb()
            if (r1 == 0) goto L_0x03f3
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzj()
            java.lang.String r2 = "Current session is expired, remove the session number, ID, and engagement time"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r18 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_sid"
            r17 = 0
            r1 = r20
            r13 = r4
            r4 = r17
            r8 = r5
            r5 = r18
            r1.zzZ(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r5 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_sno"
            r4 = 0
            r1 = r20
            r1.zzZ(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r5 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_se"
            r1 = r20
            r1.zzZ(r2, r3, r4, r5)
            goto L_0x03f5
        L_0x03f3:
            r13 = r4
            r8 = r5
        L_0x03f5:
            java.lang.String r1 = "extend_session"
            long r1 = r12.getLong(r1, r8)
            r3 = 1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x041c
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzj()
            java.lang.String r2 = "EXTEND_SESSION param attached: initiate a new session or extend the current active session"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkc r1 = r1.zzu()
            com.google.android.gms.measurement.internal.zzkb r1 = r1.zza
            r2 = 1
            r1.zzb(r10, r2)
        L_0x041c:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Set r2 = r12.keySet()
            r1.<init>(r2)
            java.util.Collections.sort(r1)
            int r2 = r1.size()
            r3 = r15
        L_0x042d:
            if (r3 >= r2) goto L_0x047a
            java.lang.Object r4 = r1.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0477
            com.google.android.gms.measurement.internal.zzft r5 = r7.zzs
            r5.zzv()
            java.lang.Object r5 = r12.get(r4)
            boolean r6 = r5 instanceof android.os.Bundle
            if (r6 == 0) goto L_0x044c
            r6 = 1
            android.os.Bundle[] r8 = new android.os.Bundle[r6]
            android.os.Bundle r5 = (android.os.Bundle) r5
            r8[r15] = r5
            goto L_0x0472
        L_0x044c:
            boolean r6 = r5 instanceof android.os.Parcelable[]
            if (r6 == 0) goto L_0x045d
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r6 = r5.length
            java.lang.Class<android.os.Bundle[]> r8 = android.os.Bundle[].class
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r6, r8)
            r8 = r5
            android.os.Bundle[] r8 = (android.os.Bundle[]) r8
            goto L_0x0472
        L_0x045d:
            boolean r6 = r5 instanceof java.util.ArrayList
            if (r6 == 0) goto L_0x0471
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r6 = r5.size()
            android.os.Bundle[] r6 = new android.os.Bundle[r6]
            java.lang.Object[] r5 = r5.toArray(r6)
            r8 = r5
            android.os.Bundle[] r8 = (android.os.Bundle[]) r8
            goto L_0x0472
        L_0x0471:
            r8 = 0
        L_0x0472:
            if (r8 == 0) goto L_0x0477
            r12.putParcelableArray(r4, r8)
        L_0x0477:
            r4 = 1
            int r3 = r3 + r4
            goto L_0x042d
        L_0x047a:
            r8 = r15
        L_0x047b:
            int r1 = r14.size()
            if (r8 >= r1) goto L_0x04e6
            java.lang.Object r1 = r14.get(r8)
            android.os.Bundle r1 = (android.os.Bundle) r1
            if (r8 == 0) goto L_0x048e
            java.lang.String r2 = "_ep"
            r9 = r21
            goto L_0x0492
        L_0x048e:
            r9 = r21
            r2 = r22
        L_0x0492:
            r1.putString(r0, r9)
            if (r27 == 0) goto L_0x04a1
            com.google.android.gms.measurement.internal.zzft r3 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r3 = r3.zzv()
            android.os.Bundle r1 = r3.zzt(r1)
        L_0x04a1:
            r12 = r1
            com.google.android.gms.measurement.internal.zzau r5 = new com.google.android.gms.measurement.internal.zzau
            com.google.android.gms.measurement.internal.zzas r3 = new com.google.android.gms.measurement.internal.zzas
            r3.<init>(r12)
            r1 = r5
            r4 = r21
            r15 = r5
            r5 = r23
            r1.<init>(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzjm r1 = r1.zzt()
            r5 = r29
            r1.zzA(r15, r5)
            if (r16 != 0) goto L_0x04e2
            java.util.Set r1 = r7.zze
            java.util.Iterator r15 = r1.iterator()
        L_0x04c5:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L_0x04e2
            java.lang.Object r1 = r15.next()
            com.google.android.gms.measurement.internal.zzgu r1 = (com.google.android.gms.measurement.internal.zzgu) r1
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>(r12)
            r2 = r21
            r3 = r22
            r5 = r23
            r1.onEvent(r2, r3, r4, r5)
            r5 = r29
            goto L_0x04c5
        L_0x04e2:
            r1 = 1
            int r8 = r8 + r1
            r15 = 0
            goto L_0x047b
        L_0x04e6:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            r0.zzaw()
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzim r0 = r0.zzs()
            r1 = 0
            com.google.android.gms.measurement.internal.zzif r0 = r0.zzj(r1)
            if (r0 == 0) goto L_0x0516
            r1 = r22
            boolean r0 = r13.equals(r1)
            if (r0 == 0) goto L_0x0516
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzkc r0 = r0.zzu()
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r1 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzka r0 = r0.zzb
            r3 = 1
            r0.zzd(r3, r3, r1)
        L_0x0516:
            return
        L_0x0517:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()
            java.lang.String r1 = "Event not sent since app measurement is disabled"
            r0.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zzI(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    public void zzJ(final zzgu zzgu) {
        this.zza();
        Preconditions.checkNotNull(zzgu);
        if (!zze.add(zzgu)) {
            zzs.zzay().zzk().zza("OnEventListener already registered");
        }
    }

    public void zzK(final long j2) {
        zzg.set(null);
        zzs.zzaz().zzp(new zzhg(this, j2));
    }


    public void zzL(final long j2, final boolean z) {
        this.zzg();
        this.zza();
        zzs.zzay().zzc().zza("Resetting analytics data (FE)");
        final zzkc zzu = zzs.zzu();
        zzu.zzg();
        zzu.zzb.zza();
        final boolean zzJ = zzs.zzJ();
        final zzey zzm2 = zzs.zzm();
        zzm2.zzc.zzb(j2);
        if (!TextUtils.isEmpty(zzm2.zzs.zzm().zzo.zza())) {
            zzm2.zzo.zzb(null);
        }
        zzok.zzc();
        final zzaf zzf2 = zzm2.zzs.zzf();
        final zzdv zzdv = zzdw.zzad;
        if (zzf2.zzs(null, zzdv)) {
            zzm2.zzj.zzb(0);
        }
        if (!zzm2.zzs.zzf().zzv()) {
            zzm2.zzi(!zzJ);
        }
        zzm2.zzp.zzb(null);
        zzm2.zzq.zzb(0);
        zzm2.zzr.zzb(null);
        if (z) {
            zzs.zzt().zzC();
        }
        zzok.zzc();
        if (zzs.zzf().zzs(null, zzdv)) {
            zzs.zzu().zza.zza();
        }
        zzc = !zzJ;
    }


    public void zzM(final String str, final String str2, final long j2, final Bundle bundle, final boolean z, final boolean z2, final boolean z3, final String str3) {
        final Bundle bundle2 = new Bundle(bundle);
        for (final String next : bundle2.keySet()) {
            final Object obj = bundle2.get(next);
            if (obj instanceof Bundle) {
                bundle2.putBundle(next, new Bundle((Bundle) obj));
            } else {
                int i2 = 0;
                if (obj instanceof Parcelable[] parcelableArr) {
                    while (i2 < parcelableArr.length) {
                        final Parcelable parcelable = parcelableArr[i2];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelable);
                        }
                        i2++;
                    }
                } else if (obj instanceof List list) {
                    while (i2 < list.size()) {
                        final Object obj2 = list.get(i2);
                        if (obj2 instanceof Bundle) {
                            list.set(i2, new Bundle((Bundle) obj2));
                        }
                        i2++;
                    }
                }
            }
        }
        zzs.zzaz().zzp(new zzhd(this, str, str2, j2, bundle2, z, z2, z3, str3));
    }


    public void zzN(final String str, final String str2, final long j2, final Object obj) {
        zzs.zzaz().zzp(new zzhe(this, str, str2, obj, j2));
    }


    public void zzO(final String str) {
        zzg.set(str);
    }

    public void zzP(final Bundle bundle) {
        this.zzQ(bundle, zzs.zzav().currentTimeMillis());
    }

    public void zzQ(final Bundle bundle, final long j2) {
        Preconditions.checkNotNull(bundle);
        final Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzs.zzay().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        final Class<String> cls = String.class;
        zzgp.zza(bundle2, "app_id", cls, null);
        zzgp.zza(bundle2, FirebaseAnalytics.Param.ORIGIN, cls, null);
        zzgp.zza(bundle2, "name", cls, null);
        zzgp.zza(bundle2, "value", Object.class, null);
        zzgp.zza(bundle2, "trigger_event_name", cls, null);
        final Class<Long> cls2 = Long.class;
        zzgp.zza(bundle2, "trigger_timeout", cls2, 0L);
        zzgp.zza(bundle2, "timed_out_event_name", cls, null);
        final Class<Bundle> cls3 = Bundle.class;
        zzgp.zza(bundle2, "timed_out_event_params", cls3, null);
        zzgp.zza(bundle2, "triggered_event_name", cls, null);
        zzgp.zza(bundle2, "triggered_event_params", cls3, null);
        zzgp.zza(bundle2, "time_to_live", cls2, 0L);
        zzgp.zza(bundle2, "expired_event_name", cls, null);
        zzgp.zza(bundle2, "expired_event_params", cls3, null);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString(FirebaseAnalytics.Param.ORIGIN));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong("creation_timestamp", j2);
        final String string = bundle2.getString("name");
        final Object obj = bundle2.get("value");
        if (0 != this.zzs.zzv().zzl(string)) {
            zzs.zzay().zzd().zzb("Invalid conditional user property name", zzs.zzj().zzf(string));
        } else if (0 == this.zzs.zzv().zzd(string, obj)) {
            final Object zzB = zzs.zzv().zzB(string, obj);
            if (null == zzB) {
                zzs.zzay().zzd().zzc("Unable to normalize conditional user property value", zzs.zzj().zzf(string), obj);
                return;
            }
            zzgp.zzb(bundle2, zzB);
            final long j3 = bundle2.getLong("trigger_timeout");
            if (!TextUtils.isEmpty(bundle2.getString("trigger_event_name"))) {
                zzs.zzf();
                if (15552000000L < j3 || 1 > j3) {
                    zzs.zzay().zzd().zzc("Invalid conditional user property timeout", zzs.zzj().zzf(string), Long.valueOf(j3));
                    return;
                }
            }
            final long j4 = bundle2.getLong("time_to_live");
            zzs.zzf();
            if (15552000000L < j4 || 1 > j4) {
                zzs.zzay().zzd().zzc("Invalid conditional user property time to live", zzs.zzj().zzf(string), Long.valueOf(j4));
            } else {
                zzs.zzaz().zzp(new zzhh(this, bundle2));
            }
        } else {
            zzs.zzay().zzd().zzc("Invalid conditional user property value", zzs.zzj().zzf(string), obj);
        }
    }

    public void zzR(final Bundle bundle, final int i2, final long j2) {
        this.zza();
        final String zzg2 = zzah.zzg(bundle);
        if (null != zzg2) {
            zzs.zzay().zzl().zzb("Ignoring invalid consent setting", zzg2);
            zzs.zzay().zzl().zza("Valid consent values are 'granted', 'denied'");
        }
        this.zzS(zzah.zza(bundle), i2, j2);
    }

    public void zzS(final zzah zzah, final int i2, final long j2) {
        boolean z;
        final boolean z2;
        final zzah zzah2;
        final boolean z3;
        this.zza();
        if (-10 != i2 && null == zzah.zze() && null == zzah.zzf()) {
            zzs.zzay().zzl().zza("Discarding empty consent settings");
            return;
        }
        synchronized (zzh) {
            try {
                z = false;
                if (com.google.android.gms.measurement.internal.zzah.zzj(i2, zzj)) {
                    z3 = zzah.zzk(zzi);
                    final zzag zzag = com.google.android.gms.measurement.internal.zzag.ANALYTICS_STORAGE;
                    if (zzah.zzi(zzag) && !zzi.zzi(zzag)) {
                        z = true;
                    }
                    final zzah zzd2 = zzah.zzd(zzi);
                    zzi = zzd2;
                    zzj = i2;
                    zzah2 = zzd2;
                    z2 = z;
                    z = true;
                } else {
                    zzah2 = zzah;
                    z2 = false;
                    z3 = false;
                }
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!z) {
            zzs.zzay().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzah2);
            return;
        }
        final long andIncrement = zzk.getAndIncrement();
        if (z3) {
            zzg.set(null);
            zzs.zzaz().zzq(new zzhs(this, zzah2, j2, i2, andIncrement, z2));
        } else if (30 == i2 || -10 == i2) {
            zzs.zzaz().zzq(new zzht(this, zzah2, i2, andIncrement, z2));
        } else {
            zzs.zzaz().zzp(new zzhu(this, zzah2, i2, andIncrement, z2));
        }
    }

    public void zzT(final Bundle bundle, final long j2) {
        zzoe.zzc();
        if (zzs.zzf().zzs(null, zzdw.zzal)) {
            zzs.zzaz().zzq(new zzgz(this, bundle, j2));
        } else {
            this.zzB(bundle, j2);
        }
    }

    @WorkerThread
    public void zzU(final zzgt zzgt) {
        final zzgt zzgt2;
        this.zzg();
        this.zza();
        if (!(null == zzgt || zzgt == (zzgt2 = zzd))) {
            Preconditions.checkState(null == zzgt2, "EventInterceptor already set.");
        }
        zzd = zzgt;
    }

    public void zzV(final Boolean bool) {
        this.zza();
        zzs.zzaz().zzp(new zzhr(this, bool));
    }


    @WorkerThread
    public void zzW(final zzah zzah) {
        final boolean z;
        this.zzg();
        z = (zzah.zzi(zzag.ANALYTICS_STORAGE) && zzah.zzi(zzag.AD_STORAGE)) || zzs.zzt().zzM();
        if (z != zzs.zzK()) {
            zzs.zzG(z);
            final zzey zzm2 = zzs.zzm();
            final zzft zzft = zzm2.zzs;
            zzm2.zzg();
            final Boolean valueOf = zzm2.zza().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzm2.zza().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || null == valueOf || valueOf.booleanValue()) {
                this.zzac(Boolean.valueOf(z), false);
            }
        }
    }

    public void zzX(final String str, final String str2, final Object obj, final boolean z) {
        this.zzY("auto", "_ldl", obj, true, zzs.zzav().currentTimeMillis());
    }


    /*  WARNING: Removed duplicated region for block: B:20:0x006b  */
    /*  WARNING: Removed duplicated region for block: B:22:0x007b  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzZ(final java.lang.String r9, final java.lang.String r10, final java.lang.Object r11, final long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzg()
            r8.zza()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x0061
            boolean r0 = r11 instanceof java.lang.String
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L_0x0050
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0050
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r10 = r0.toLowerCase(r10)
            java.lang.String r11 = "false"
            boolean r10 = r11.equals(r10)
            r2 = 1
            r0 = 1
            if (r0 == r10) goto L_0x0037
            r4 = 0
            goto L_0x0038
        L_0x0037:
            r4 = r2
        L_0x0038:
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            com.google.android.gms.measurement.internal.zzft r0 = r8.zzs
            com.google.android.gms.measurement.internal.zzey r0 = r0.zzm()
            com.google.android.gms.measurement.internal.zzex r0 = r0.zzh
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x004a
            java.lang.String r11 = "true"
        L_0x004a:
            r0.zzb(r11)
            r6 = r10
        L_0x004e:
            r3 = r1
            goto L_0x0063
        L_0x0050:
            if (r11 != 0) goto L_0x0061
            com.google.android.gms.measurement.internal.zzft r10 = r8.zzs
            com.google.android.gms.measurement.internal.zzey r10 = r10.zzm()
            com.google.android.gms.measurement.internal.zzex r10 = r10.zzh
            java.lang.String r0 = "unset"
            r10.zzb(r0)
            r6 = r11
            goto L_0x004e
        L_0x0061:
            r3 = r10
            r6 = r11
        L_0x0063:
            com.google.android.gms.measurement.internal.zzft r10 = r8.zzs
            boolean r10 = r10.zzJ()
            if (r10 != 0) goto L_0x007b
            com.google.android.gms.measurement.internal.zzft r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzej r9 = r9.zzay()
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzj()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L_0x007b:
            com.google.android.gms.measurement.internal.zzft r10 = r8.zzs
            boolean r10 = r10.zzM()
            if (r10 != 0) goto L_0x0084
            return
        L_0x0084:
            com.google.android.gms.measurement.internal.zzku r10 = new com.google.android.gms.measurement.internal.zzku
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zzft r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzjm r9 = r9.zzt()
            r9.zzK(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zzZ(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public void zzaa(final zzgu zzgu) {
        this.zza();
        Preconditions.checkNotNull(zzgu);
        if (!zze.remove(zzgu)) {
            zzs.zzay().zzk().zza("OnEventListener had not been registered");
        }
    }


    public boolean zzf() {
        return false;
    }

    public int zzh(final String str) {
        Preconditions.checkNotEmpty(str);
        zzs.zzf();
        return 25;
    }

    public Boolean zzi() {
        final AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzs.zzaz().zzd(atomicReference, 15000, "boolean test flag value", new zzhj(this, atomicReference));
    }

    public Double zzj() {
        final AtomicReference atomicReference = new AtomicReference();
        return (Double) zzs.zzaz().zzd(atomicReference, 15000, "double test flag value", new zzhq(this, atomicReference));
    }

    public Integer zzl() {
        final AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzs.zzaz().zzd(atomicReference, 15000, "int test flag value", new zzhp(this, atomicReference));
    }

    public Long zzm() {
        final AtomicReference atomicReference = new AtomicReference();
        return (Long) zzs.zzaz().zzd(atomicReference, 15000, "long test flag value", new zzho(this, atomicReference));
    }

    public String zzo() {
        return (String) zzg.get();
    }

    public String zzp() {
        final zzif zzi2 = zzs.zzs().zzi();
        if (null != zzi2) {
            return zzi2.zzb;
        }
        return null;
    }

    public String zzq() {
        final zzif zzi2 = zzs.zzs().zzi();
        if (null != zzi2) {
            return zzi2.zza;
        }
        return null;
    }

    public String zzr() {
        final AtomicReference atomicReference = new AtomicReference();
        return (String) zzs.zzaz().zzd(atomicReference, 15000, "String test flag value", new zzhn(this, atomicReference));
    }

    public ArrayList zzs(final String str, final String str2) {
        if (zzs.zzaz().zzs()) {
            zzs.zzay().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        zzs.zzaw();
        if (zzaa.zza()) {
            zzs.zzay().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        final AtomicReference atomicReference = new AtomicReference();
        zzs.zzaz().zzd(atomicReference, CoroutineLiveDataKt.DEFAULT_TIMEOUT, "get conditional user properties", new zzhk(this, atomicReference, null, str, str2));
        final List list = (List) atomicReference.get();
        if (null != list) {
            return zzky.zzG(list);
        }
        zzs.zzay().zzd().zzb("Timed out waiting for get conditional user properties", null);
        return new ArrayList();
    }

    public Map zzu(final String str, final String str2, final boolean z) {
        if (zzs.zzaz().zzs()) {
            zzs.zzay().zzd().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzs.zzaw();
        if (zzaa.zza()) {
            zzs.zzay().zzd().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        final AtomicReference atomicReference = new AtomicReference();
        zzs.zzaz().zzd(atomicReference, CoroutineLiveDataKt.DEFAULT_TIMEOUT, "get user properties", new zzhl(this, atomicReference, null, str, str2, z));
        final List<zzku> list = (List) atomicReference.get();
        if (null == list) {
            zzs.zzay().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        final ArrayMap arrayMap = new ArrayMap(list.size());
        for (final zzku zzku : list) {
            final Object zza2 = zzku.zza();
            if (null != zza2) {
                arrayMap.put(zzku.zzb, zza2);
            }
        }
        return arrayMap;
    }

    @WorkerThread
    public void zzy() {
        this.zzg();
        this.zza();
        if (zzs.zzM()) {
            if (zzs.zzf().zzs(null, zzdw.zzX)) {
                final zzaf zzf2 = zzs.zzf();
                zzf2.zzs.zzaw();
                final Boolean zzk2 = zzf2.zzk("google_analytics_deferred_deep_link_enabled");
                if (null != zzk2 && zzk2.booleanValue()) {
                    zzs.zzay().zzc().zza("Deferred Deep Link feature enabled.");
                    zzs.zzaz().zzp(new zzgx(this));
                }
            }
            zzs.zzt().zzq();
            zzc = false;
            final zzey zzm2 = zzs.zzm();
            zzm2.zzg();
            final String string = zzm2.zza().getString("previous_os_version", null);
            zzm2.zzs.zzg().zzu();
            final String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                final SharedPreferences.Editor edit = zzm2.zza().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (!TextUtils.isEmpty(string)) {
                zzs.zzg().zzu();
                if (!string.equals(str)) {
                    final Bundle bundle = new Bundle();
                    bundle.putString("_po", string);
                    this.zzG("auto", "_ou", bundle);
                }
            }
        }
    }

    public void zzz(final String str, final String str2, final Bundle bundle) {
        final long currentTimeMillis = zzs.zzav().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        final Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong("creation_timestamp", currentTimeMillis);
        if (null != str2) {
            bundle2.putString("expired_event_name", str2);
            bundle2.putBundle("expired_event_params", bundle);
        }
        zzs.zzaz().zzp(new zzhi(this, bundle2));
    }

    /*  WARNING: Removed duplicated region for block: B:19:0x004b  */
    /*  WARNING: Removed duplicated region for block: B:24:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzY(final java.lang.String r18, final java.lang.String r19, final java.lang.Object r20, final boolean r21, final long r22) {
        /*
            r17 = this;
            r6 = r17
            r2 = r19
            r0 = r20
            if (r18 != 0) goto L_0x000b
            java.lang.String r1 = "app"
            goto L_0x000d
        L_0x000b:
            r1 = r18
        L_0x000d:
            r3 = 0
            r4 = 24
            if (r21 == 0) goto L_0x001e
            com.google.android.gms.measurement.internal.zzft r5 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r5 = r5.zzv()
            int r5 = r5.zzl(r2)
        L_0x001c:
            r13 = r5
            goto L_0x0048
        L_0x001e:
            com.google.android.gms.measurement.internal.zzft r5 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r5 = r5.zzv()
            java.lang.String r7 = "user property"
            boolean r8 = r5.zzab(r7, r2)
            r9 = 6
            if (r8 != 0) goto L_0x002f
        L_0x002d:
            r13 = r9
            goto L_0x0048
        L_0x002f:
            java.lang.String[] r8 = com.google.android.gms.measurement.internal.zzgs.zza
            r10 = 0
            boolean r8 = r5.zzY(r7, r8, r10, r2)
            if (r8 != 0) goto L_0x003b
            r5 = 15
            goto L_0x001c
        L_0x003b:
            com.google.android.gms.measurement.internal.zzft r8 = r5.zzs
            r8.zzf()
            boolean r5 = r5.zzX(r7, r4, r2)
            if (r5 != 0) goto L_0x0047
            goto L_0x002d
        L_0x0047:
            r13 = r3
        L_0x0048:
            r5 = 1
            if (r13 == 0) goto L_0x0071
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r0 = r0.zzv()
            com.google.android.gms.measurement.internal.zzft r1 = r6.zzs
            r1.zzf()
            java.lang.String r15 = r0.zzC(r2, r4, r5)
            if (r2 == 0) goto L_0x0060
            int r3 = r19.length()
        L_0x0060:
            r16 = r3
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r10 = r0.zzv()
            com.google.android.gms.measurement.internal.zzkx r11 = r6.zzn
            r12 = 0
            java.lang.String r14 = "_ev"
            r10.zzM(r11, r12, r13, r14, r15, r16)
            return
        L_0x0071:
            if (r0 == 0) goto L_0x00c7
            com.google.android.gms.measurement.internal.zzft r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r7 = r7.zzv()
            int r11 = r7.zzd(r2, r0)
            if (r11 == 0) goto L_0x00b1
            com.google.android.gms.measurement.internal.zzft r1 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzft r7 = r6.zzs
            r7.zzf()
            java.lang.String r13 = r1.zzC(r2, r4, r5)
            boolean r1 = r0 instanceof java.lang.String
            if (r1 != 0) goto L_0x0099
            boolean r1 = r0 instanceof java.lang.CharSequence
            if (r1 == 0) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            r14 = r3
            goto L_0x00a2
        L_0x0099:
            java.lang.String r0 = r20.toString()
            int r3 = r0.length()
            goto L_0x0097
        L_0x00a2:
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r8 = r0.zzv()
            com.google.android.gms.measurement.internal.zzkx r9 = r6.zzn
            r10 = 0
            java.lang.String r12 = "_ev"
            r8.zzM(r9, r10, r11, r12, r13, r14)
            return
        L_0x00b1:
            com.google.android.gms.measurement.internal.zzft r3 = r6.zzs
            com.google.android.gms.measurement.internal.zzky r3 = r3.zzv()
            java.lang.Object r5 = r3.zzB(r2, r0)
            if (r5 == 0) goto L_0x00c6
            r0 = r17
            r2 = r19
            r3 = r22
            r0.zzN(r1, r2, r3, r5)
        L_0x00c6:
            return
        L_0x00c7:
            r5 = 0
            r0 = r17
            r2 = r19
            r3 = r22
            r0.zzN(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zzY(java.lang.String, java.lang.String, java.lang.Object, boolean, long):void");
    }
}
