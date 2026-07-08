package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.firebase.messaging.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgl extends zzdy {
    
    public final zzkr zza;
    private Boolean zzb;
    private String zzc;

    public zzgl(final zzkr zzkr, final String str) {
        Preconditions.checkNotNull(zzkr);
        zza = zzkr;
    }

    private void zzA(final zzau zzau, final zzp zzp) {
        zza.zzA();
        zza.zzD(zzau, zzp);
    }

    @BinderThread
    private void zzy(final zzp zzp, final boolean z) {
        Preconditions.checkNotNull(zzp);
        Preconditions.checkNotEmpty(zzp.zza);
        this.zzz(zzp.zza, false);
        zza.zzv().zzW(zzp.zzb, zzp.zzq);
    }

    @BinderThread
    private void zzz(final String str, final boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (null == this.zzb) {
                        boolean z2 = true;
                        if (!"com.google.android.gms".equals(zzc) && !UidVerifier.isGooglePlayServicesUid(zza.zzau(), getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(zza.zzau()).isUidGoogleSigned(getCallingUid())) {
                                z2 = false;
                            }
                        }
                        zzb = Boolean.valueOf(z2);
                    }
                    if (zzb.booleanValue()) {
                        return;
                    }
                } catch (final SecurityException e2) {
                    zza.zzay().zzd().zzb("Measurement Service called with invalid calling package. appId", zzej.zzn(str));
                    throw e2;
                }
            }
            if (null == this.zzc && GooglePlayServicesUtilLight.uidHasPackageName(zza.zzau(), getCallingUid(), str)) {
                zzc = str;
            }
            if (!str.equals(zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
            }
            return;
        }
        zza.zzay().zzd().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    
    @VisibleForTesting
    public zzau zzb(final zzau zzau, final zzp zzp) {
        final zzas zzas;
        if (!(!Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzau.zza) || null == (zzas = zzau.zzb) || 0 == zzas.zza())) {
            final String zzg = zzau.zzb.zzg("_cis");
            if ("referrer broadcast".equals(zzg) || "referrer API".equals(zzg)) {
                zza.zzay().zzi().zzb("Event has been filtered ", zzau.toString());
                return new zzau("_cmpx", zzau.zzb, zzau.zzc, zzau.zzd);
            }
        }
        return zzau;
    }

    @BinderThread
    public String zzd(final zzp zzp) {
        this.zzy(zzp, false);
        return zza.zzx(zzp);
    }

    @BinderThread
    public List zze(final zzp zzp, final boolean z) {
        this.zzy(zzp, false);
        final String str = zzp.zza;
        Preconditions.checkNotNull(str);
        try {
            final List<zzkw> list = (List) zza.zzaz().zzh(new zzgi(this, str)).get();
            final ArrayList arrayList = new ArrayList(list.size());
            for (final zzkw zzkw : list) {
                if (!z) {
                    if (!zzky.zzag(zzkw.zzc())) {
                    }
                }
                arrayList.add(new zzku(zzkw));
            }
            return arrayList;
        } catch (final InterruptedException e2) {
            e = e2;
            zza.zzay().zzd().zzc("Failed to get user properties. appId", zzej.zzn(zzp.zza), e);
            return null;
        } catch (final ExecutionException e3) {
            e = e3;
            zza.zzay().zzd().zzc("Failed to get user properties. appId", zzej.zzn(zzp.zza), e);
            return null;
        }
    }

    @BinderThread
    public List zzf(final String str, final String str2, final zzp zzp) {
        this.zzy(zzp, false);
        final String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) zza.zzaz().zzh(new zzfz(this, str3, str, str2)).get();
        } catch (final InterruptedException | ExecutionException e2) {
            zza.zzay().zzd().zzb("Failed to get conditional user properties", e2);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public List zzg(final String str, final String str2, final String str3) {
        this.zzz(str, true);
        try {
            return (List) zza.zzaz().zzh(new zzga(this, str, str2, str3)).get();
        } catch (final InterruptedException | ExecutionException e2) {
            zza.zzay().zzd().zzb("Failed to get conditional user properties as", e2);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public List zzh(final String str, final String str2, final boolean z, final zzp zzp) {
        this.zzy(zzp, false);
        final String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            final List<zzkw> list = (List) zza.zzaz().zzh(new zzfx(this, str3, str, str2)).get();
            final ArrayList arrayList = new ArrayList(list.size());
            for (final zzkw zzkw : list) {
                if (!z) {
                    if (!zzky.zzag(zzkw.zzc())) {
                    }
                }
                arrayList.add(new zzku(zzkw));
            }
            return arrayList;
        } catch (final InterruptedException e2) {
            e = e2;
            zza.zzay().zzd().zzc("Failed to query user properties. appId", zzej.zzn(zzp.zza), e);
            return Collections.emptyList();
        } catch (final ExecutionException e3) {
            e = e3;
            zza.zzay().zzd().zzc("Failed to query user properties. appId", zzej.zzn(zzp.zza), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public List zzi(final String str, final String str2, final String str3, final boolean z) {
        this.zzz(str, true);
        try {
            final List<zzkw> list = (List) zza.zzaz().zzh(new zzfy(this, str, str2, str3)).get();
            final ArrayList arrayList = new ArrayList(list.size());
            for (final zzkw zzkw : list) {
                if (!z) {
                    if (!zzky.zzag(zzkw.zzc())) {
                    }
                }
                arrayList.add(new zzku(zzkw));
            }
            return arrayList;
        } catch (final InterruptedException e2) {
            e = e2;
            zza.zzay().zzd().zzc("Failed to get user properties as. appId", zzej.zzn(str), e);
            return Collections.emptyList();
        } catch (final ExecutionException e3) {
            e = e3;
            zza.zzay().zzd().zzc("Failed to get user properties as. appId", zzej.zzn(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public void zzj(final zzp zzp) {
        this.zzy(zzp, false);
        this.zzx(new zzgj(this, zzp));
    }

    @BinderThread
    public void zzk(final zzau zzau, final zzp zzp) {
        Preconditions.checkNotNull(zzau);
        this.zzy(zzp, false);
        this.zzx(new zzge(this, zzau, zzp));
    }

    @BinderThread
    public void zzl(final zzau zzau, final String str, final String str2) {
        Preconditions.checkNotNull(zzau);
        Preconditions.checkNotEmpty(str);
        this.zzz(str, true);
        this.zzx(new zzgf(this, zzau, str));
    }

    @BinderThread
    public void zzm(final zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        this.zzz(zzp.zza, false);
        this.zzx(new zzgb(this, zzp));
    }

    @BinderThread
    public void zzn(final zzab zzab, final zzp zzp) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotNull(zzab.zzc);
        this.zzy(zzp, false);
        final zzab zzab2 = new zzab(zzab);
        zzab2.zza = zzp.zza;
        this.zzx(new zzfv(this, zzab2, zzp));
    }

    @BinderThread
    public void zzo(final zzab zzab) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotNull(zzab.zzc);
        Preconditions.checkNotEmpty(zzab.zza);
        this.zzz(zzab.zza, true);
        this.zzx(new zzfw(this, new zzab(zzab)));
    }

    @BinderThread
    public void zzp(final zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        Preconditions.checkNotNull(zzp.zzv);
        final zzgd zzgd = new zzgd(this, zzp);
        Preconditions.checkNotNull(zzgd);
        if (zza.zzaz().zzs()) {
            zzgd.run();
        } else {
            zza.zzaz().zzq(zzgd);
        }
    }

    @BinderThread
    public void zzq(final long j2, final String str, final String str2, final String str3) {
        this.zzx(new zzgk(this, str2, str3, str, j2));
    }

    @BinderThread
    public void zzr(final Bundle bundle, final zzp zzp) {
        this.zzy(zzp, false);
        final String str = zzp.zza;
        Preconditions.checkNotNull(str);
        this.zzx(new zzfu(this, str, bundle));
    }

    @BinderThread
    public void zzs(final zzp zzp) {
        this.zzy(zzp, false);
        this.zzx(new zzgc(this, zzp));
    }

    @BinderThread
    public void zzt(final zzku zzku, final zzp zzp) {
        Preconditions.checkNotNull(zzku);
        this.zzy(zzp, false);
        this.zzx(new zzgh(this, zzku, zzp));
    }

    @BinderThread
    public byte[] zzu(final zzau zzau, final String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzau);
        this.zzz(str, true);
        zza.zzay().zzc().zzb("Log and bundle. event", zza.zzj().zzd(zzau.zza));
        final long nanoTime = zza.zzav().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) zza.zzaz().zzi(new zzgg(this, zzau, str)).get();
            if (null == bArr) {
                zza.zzay().zzd().zzb("Log and bundle returned null. appId", zzej.zzn(str));
                bArr = new byte[0];
            }
            zza.zzay().zzc().zzd("Log and bundle processed. event, size, time_ms", zza.zzj().zzd(zzau.zza), Integer.valueOf(bArr.length), Long.valueOf((zza.zzav().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (final InterruptedException e2) {
            e = e2;
            zza.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzej.zzn(str), zza.zzj().zzd(zzau.zza), e);
            return null;
        } catch (final ExecutionException e3) {
            e = e3;
            zza.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzej.zzn(str), zza.zzj().zzd(zzau.zza), e);
            return null;
        }
    }

    
    public void zzv(final zzau zzau, final zzp zzp) {
        final zzc zzc2;
        if (!zza.zzo().zzl(zzp.zza)) {
            this.zzA(zzau, zzp);
            return;
        }
        zza.zzay().zzj().zzb("EES config found for", zzp.zza);
        final zzfk zzo = zza.zzo();
        final String str = zzp.zza;
        if (TextUtils.isEmpty(str)) {
            zzc2 = null;
        } else {
            zzc2 = (zzc) zzo.zzc.get(str);
        }
        if (null != zzc2) {
            try {
                final Map zzs = zza.zzu().zzs(zzau.zzb.zzc(), true);
                String zza2 = zzgq.zza(zzau.zza);
                if (null == zza2) {
                    zza2 = zzau.zza;
                }
                if (zzc2.zze(new zzaa(zza2, zzau.zzd, zzs))) {
                    if (zzc2.zzg()) {
                        zza.zzay().zzj().zzb("EES edited event", zzau.zza);
                        this.zzA(zza.zzu().zzi(zzc2.zza().zzb()), zzp);
                    } else {
                        this.zzA(zzau, zzp);
                    }
                    if (zzc2.zzf()) {
                        for (final zzaa zzaa : zzc2.zza().zzc()) {
                            zza.zzay().zzj().zzb("EES logging created event", zzaa.zzd());
                            this.zzA(zza.zzu().zzi(zzaa), zzp);
                        }
                        return;
                    }
                    return;
                }
            } catch (final zzd unused) {
                zza.zzay().zzd().zzc("EES error. appId, eventName", zzp.zzb, zzau.zza);
            }
            zza.zzay().zzj().zzb("EES was not applied to event", zzau.zza);
            this.zzA(zzau, zzp);
            return;
        }
        zza.zzay().zzj().zzb("EES not loaded for", zzp.zza);
        this.zzA(zzau, zzp);
    }

    
    public void zzw(final String str, final Bundle bundle) {
        final zzak zzi = zza.zzi();
        zzi.zzg();
        zzi.zzW();
        final byte[] zzbq = zzi.zzf.zzu().zzj(new zzap(zzi.zzs, "", str, "dep", 0, 0, bundle)).zzbq();
        zzi.zzs.zzay().zzj().zzc("Saving default event parameters, appId, data size", zzi.zzs.zzj().zzd(str), Integer.valueOf(zzbq.length));
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzbq);
        try {
            if (-1 == zzi.zzh().insertWithOnConflict("default_event_params", null, contentValues, 5)) {
                zzi.zzs.zzay().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzej.zzn(str));
            }
        } catch (final SQLiteException e2) {
            zzi.zzs.zzay().zzd().zzc("Error storing default event parameters. appId", zzej.zzn(str), e2);
        }
    }

    
    @VisibleForTesting
    public void zzx(final Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (zza.zzaz().zzs()) {
            runnable.run();
        } else {
            zza.zzaz().zzp(runnable);
        }
    }
}
