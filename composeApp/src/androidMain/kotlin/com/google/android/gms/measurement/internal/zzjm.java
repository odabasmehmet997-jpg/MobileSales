package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzjm extends zzf {

    public final zzjl zza;

    public zzdz zzb;
    private volatile Boolean zzc;
    private final zzan zzd;
    private final zzkd zze;
    private final List zzf = new ArrayList();
    private final zzan zzg;

    zzjm(zzft zzft) {
        super(zzft);
        this.zze = new zzkd(zzft.zzav());
        this.zza = new zzjl(this);
        this.zzd = new zziw(this, zzft);
        this.zzg = new zziy(this, zzft);
    }

    @WorkerThread
    private zzp zzO(boolean z) {
        Pair zza2;
        this.zzs.zzaw();
        zzea zzh = this.zzs.zzh();
        String str = null;
        if (z) {
            zzej zzay = this.zzs.zzay();
            if (!(null == zzay.zzs.zzm().zzb || null == (zza2 = zzay.zzs.zzm().zzb.zza()) || zza2 == zzey.zza)) {
                String valueOf = String.valueOf(zza2.second);
                String str2 = (String) zza2.first;
                final String sb = valueOf +
                        ":" +
                        str2;
                str = sb;
            }
        }
        return zzh.zzj(str);
    }


    @WorkerThread
    public void zzP() {
        zzg();
        this.zzs.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (RuntimeException e2) {
                this.zzs.zzay().zzd().zzb("Task exception while flushing queue", e2);
            }
        }
        this.zzf.clear();
        this.zzg.zzb();
    }


    @WorkerThread
    public void zzQ() {
        zzg();
        this.zze.zzb();
        zzan zzan = this.zzd;
        this.zzs.zzf();
        zzan.zzd(((Long) zzdw.zzI.zza(null)).longValue());
    }

    @WorkerThread
    private void zzR(Runnable runnable) throws IllegalStateException {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.zzf.size();
        this.zzs.zzf();
        if (1000 <= ((long) size)) {
            this.zzs.zzay().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable);
        this.zzg.zzd(60000);
        zzr();
    }

    private boolean zzS() {
        this.zzs.zzaw();
        return true;
    }

    static void zzo(zzjm zzjm, ComponentName componentName) {
        zzjm.zzg();
        if (null != zzjm.zzb) {
            zzjm.zzb = null;
            zzjm.zzs.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzjm.zzg();
            zzjm.zzr();
        }
    }


    @WorkerThread
    public void zzA(zzau zzau, String str) {
        Preconditions.checkNotNull(zzau);
        zzg();
        zza();
        zzS();
        zzR(new zzjb(this, true, zzO(true), this.zzs.zzi().zzo(zzau), zzau, str));
    }

    @WorkerThread
    public void zzB(zzcf zzcf, zzau zzau, String str) {
        zzg();
        zza();
        if (0 != zzs.zzv().zzo(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
            this.zzs.zzay().zzk().zza("Not bundling data. Service unavailable or out of date");
            this.zzs.zzv().zzR(zzcf, new byte[0]);
            return;
        }
        zzR(new zzix(this, zzau, str, zzcf));
    }


    @WorkerThread
    public void zzC() {
        zzg();
        zza();
        zzp zzO = zzO(false);
        zzS();
        this.zzs.zzi().zzj();
        zzR(new zziq(this, zzO));
    }


    @WorkerThread
    @VisibleForTesting
    public void zzD(zzdz zzdz, AbstractSafeParcelable abstractSafeParcelable, zzp zzp) {
        int i2;
        zzg();
        zza();
        zzS();
        this.zzs.zzf();
        int i3 = 100;
        int i4 = 0;
        while (1001 > i4 && 100 == i3) {
            ArrayList arrayList = new ArrayList();
            List zzi = this.zzs.zzi().zzi(100);
            if (null != zzi) {
                arrayList.addAll(zzi);
                i2 = zzi.size();
            } else {
                i2 = 0;
            }
            if (null != abstractSafeParcelable && 100 > i2) {
                arrayList.add(abstractSafeParcelable);
            }
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i5);
                if (abstractSafeParcelable2 instanceof zzau) {
                    try {
                        zzdz.zzk((zzau) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e2) {
                        this.zzs.zzay().zzd().zzb("Failed to send event to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzku) {
                    try {
                        zzdz.zzt((zzku) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e3) {
                        this.zzs.zzay().zzd().zzb("Failed to send user property to the service", e3);
                    }
                } else if (abstractSafeParcelable2 instanceof zzab) {
                    try {
                        zzdz.zzn((zzab) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e4) {
                        this.zzs.zzay().zzd().zzb("Failed to send conditional user property to the service", e4);
                    }
                } else {
                    this.zzs.zzay().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i4++;
            i3 = i2;
        }
    }


    @WorkerThread
    public void zzE(zzab zzab) {
        Preconditions.checkNotNull(zzab);
        zzg();
        zza();
        this.zzs.zzaw();
        zzR(new zzjc(this, true, zzO(true), this.zzs.zzi().zzn(zzab), new zzab(zzab), zzab));
    }


    @WorkerThread
    public void zzF(boolean z) {
        zzg();
        zza();
        if (z) {
            zzS();
            this.zzs.zzi().zzj();
        }
        if (zzM()) {
            zzR(new zzja(this, zzO(false)));
        }
    }


    @WorkerThread
    public void zzG(zzif zzif) {
        zzg();
        zza();
        zzR(new zziu(this, zzif));
    }

    @WorkerThread
    public void zzH(Bundle bundle) {
        zzg();
        zza();
        zzR(new zziv(this, zzO(false), bundle));
    }


    @WorkerThread
    public void zzI() {
        zzg();
        zza();
        zzR(new zziz(this, zzO(true)));
    }


    @WorkerThread
    @VisibleForTesting
    public void zzJ(zzdz zzdz) {
        zzg();
        Preconditions.checkNotNull(zzdz);
        this.zzb = zzdz;
        zzQ();
        zzP();
    }


    @WorkerThread
    public void zzK(zzku zzku) {
        zzg();
        zza();
        zzS();
        zzR(new zzio(this, zzO(true), this.zzs.zzi().zzp(zzku), zzku));
    }

    @WorkerThread
    public boolean zzL() {
        zzg();
        zza();
        return null != zzb;
    }


    @WorkerThread
    public boolean zzM() {
        zzg();
        zza();
        return !zzN() || this.zzs.zzv().zzm() >= ((Integer) zzdw.zzai.zza(null)).intValue();
    }


    /*  WARNING: Removed duplicated region for block: B:39:0x011c  */
    /*  WARNING: Removed duplicated region for block: B:40:0x012c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzN() {
        /*
            r7 = this;
            r7.zzg()
            r7.zza()
            java.lang.Boolean r0 = r7.zzc
            if (r0 != 0) goto L_0x014c
            r7.zzg()
            r7.zza()
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r0 = r0.zzm()
            r0.zzg()
            android.content.SharedPreferences r1 = r0.zza()
            java.lang.String r2 = "use_service"
            boolean r1 = r1.contains(r2)
            r3 = 0
            if (r1 != 0) goto L_0x0028
            r0 = 0
            goto L_0x0034
        L_0x0028:
            android.content.SharedPreferences r0 = r0.zza()
            boolean r0 = r0.getBoolean(r2, r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L_0x0034:
            r1 = 1
            if (r0 == 0) goto L_0x003f
            boolean r4 = r0.booleanValue()
            if (r4 == 0) goto L_0x003f
            goto L_0x0146
        L_0x003f:
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            r4.zzaw()
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            com.google.android.gms.measurement.internal.zzea r4 = r4.zzh()
            int r4 = r4.zzh()
            if (r4 != r1) goto L_0x0053
        L_0x0050:
            r3 = r1
            goto L_0x010e
        L_0x0053:
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzj()
            java.lang.String r5 = "Checking service availability"
            r4.zza(r5)
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r4 = r4.zzv()
            r5 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r4 = r4.zzo(r5)
            if (r4 == 0) goto L_0x00fd
            if (r4 == r1) goto L_0x00ed
            r5 = 2
            if (r4 == r5) goto L_0x00c7
            r0 = 3
            if (r4 == r0) goto L_0x00b7
            r0 = 9
            if (r4 == r0) goto L_0x00a7
            r0 = 18
            if (r4 == r0) goto L_0x0097
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzk()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = "Unexpected service status"
            r0.zzb(r4, r1)
        L_0x0094:
            r1 = r3
            goto L_0x010e
        L_0x0097:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzk()
            java.lang.String r3 = "Service updating"
            r0.zza(r3)
            goto L_0x0050
        L_0x00a7:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzk()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L_0x0094
        L_0x00b7:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzk()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
            goto L_0x0094
        L_0x00c7:
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzc()
            java.lang.String r5 = "Service container out of date"
            r4.zza(r5)
            com.google.android.gms.measurement.internal.zzft r4 = r7.zzs
            com.google.android.gms.measurement.internal.zzky r4 = r4.zzv()
            int r4 = r4.zzm()
            r5 = 17443(0x4423, float:2.4443E-41)
            if (r4 >= r5) goto L_0x00e5
            goto L_0x010e
        L_0x00e5:
            if (r0 != 0) goto L_0x00e8
            goto L_0x00e9
        L_0x00e8:
            r1 = r3
        L_0x00e9:
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x010e
        L_0x00ed:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()
            java.lang.String r4 = "Service missing"
            r0.zza(r4)
            goto L_0x010e
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzj()
            java.lang.String r3 = "Service available"
            r0.zza(r3)
            goto L_0x0050
        L_0x010e:
            if (r3 != 0) goto L_0x012c
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            boolean r0 = r0.zzx()
            if (r0 == 0) goto L_0x012c
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzd()
            java.lang.String r1 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r1)
            goto L_0x0145
        L_0x012c:
            if (r1 == 0) goto L_0x0145
            com.google.android.gms.measurement.internal.zzft r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzey r0 = r0.zzm()
            r0.zzg()
            android.content.SharedPreferences r0 = r0.zza()
            android.content.SharedPreferencesEditor r0 = r0.edit()
            r0.putBoolean(r2, r3)
            r0.apply()
        L_0x0145:
            r1 = r3
        L_0x0146:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r7.zzc = r0
        L_0x014c:
            java.lang.Boolean r0 = r7.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjm.zzN():boolean");
    }


    public boolean zzf() {
        return false;
    }


    public Boolean zzj() {
        return this.zzc;
    }


    @WorkerThread
    public void zzq() {
        zzg();
        zza();
        zzp zzO = zzO(true);
        this.zzs.zzi().zzk();
        zzR(new zzit(this, zzO));
    }


    @WorkerThread
    public void zzr() {
        zzg();
        zza();
        if (!zzL()) {
            if (zzN()) {
                this.zza.zzc();
            } else if (!this.zzs.zzf().zzx()) {
                this.zzs.zzaw();
                List<ResolveInfo> queryIntentServices = this.zzs.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzs.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (null == queryIntentServices || 0 >= queryIntentServices.size()) {
                    this.zzs.zzay().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                    return;
                }
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context zzau = this.zzs.zzau();
                this.zzs.zzaw();
                intent.setComponent(new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zza.zzb(intent);
            }
        }
    }

    @WorkerThread
    public void zzs() {
        zzg();
        zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzs.zzau(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    @WorkerThread
    public void zzt(zzcf zzcf) {
        zzg();
        zza();
        zzR(new zzis(this, zzO(false), zzcf));
    }

    @WorkerThread
    public void zzu(AtomicReference atomicReference) {
        zzg();
        zza();
        zzR(new zzir(this, atomicReference, zzO(false)));
    }


    @WorkerThread
    public void zzv(zzcf zzcf, String str, String str2) {
        zzg();
        zza();
        zzR(new zzje(this, str, str2, zzO(false), zzcf));
    }


    @WorkerThread
    public void zzw(AtomicReference atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        zzR(new zzjd(this, atomicReference, null, str2, str3, zzO(false)));
    }


    @WorkerThread
    public void zzx(AtomicReference atomicReference, boolean z) {
        zzg();
        zza();
        zzR(new zzip(this, atomicReference, zzO(false), z));
    }


    @WorkerThread
    public void zzy(zzcf zzcf, String str, String str2, boolean z) {
        zzg();
        zza();
        zzR(new zzin(this, str, str2, zzO(false), z, zzcf));
    }


    @WorkerThread
    public void zzz(AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        zzR(new zzjf(this, atomicReference, null, str2, str3, zzO(false), z));
    }
}
