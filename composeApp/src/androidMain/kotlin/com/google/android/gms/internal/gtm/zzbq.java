package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzbq {
    private final zzbu zza;

    protected zzbq(final zzbu zzbu) {
        Preconditions.checkNotNull(zzbu);
        zza = zzbu;
    }

    protected static String zzD(final String str, final Object obj, final Object obj2, final Object obj3) {
        String str2;
        final String zza2 = zzbq.zza(obj);
        final String zza3 = zzbq.zza(obj2);
        final String zza4 = zzbq.zza(obj3);
        final StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        } else {
            str2 = "";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zza4)) {
            sb.append(str3);
            sb.append(zza4);
        }
        return sb.toString();
    }

    public static final boolean zzU() {
        return Log.isLoggable((String) zzeh.zzb.zzb(), 2);
    }

    private static String zza(final Object obj) {
        if (null == obj) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Boolean) {
            return Boolean.toString(obj == Boolean.TRUE);
        }
        if (obj instanceof Throwable) {
            return obj.toString();
        }
        return obj.toString();
    }

    private final void zzb(final int i2, final String str, final Object obj, final Object obj2, final Object obj3) {
        final zzbu zzbu = zza;
        final zzeo zzn = null != zzbu ? zzbu.zzn() : null;
        if (null != zzn) {
            final String str2 = (String) zzeh.zzb.zzb();
            if (Log.isLoggable(str2, i2)) {
                Log.println(i2, str2, zzbq.zzD(str, obj, obj2, obj3));
            }
            if (5 <= i2) {
                zzn.zze(i2, str, obj, obj2, obj3);
                return;
            }
            return;
        }
        final String str3 = (String) zzeh.zzb.zzb();
        if (Log.isLoggable(str3, i2)) {
            Log.println(i2, str3, zzbq.zzD(str, obj, obj2, obj3));
        }
    }

    
    public final zzeu zzA() {
        return zza.zzo();
    }

    
    public final zzfg zzB() {
        return zza.zzq();
    }

    
    public final Clock zzC() {
        return zza.zzr();
    }

    public final void zzE(final String str) {
        this.zzb(3, str, null, null, null);
    }

    public final void zzF(final String str, @Nullable final Object obj) {
        this.zzb(3, str, obj, null, null);
    }

    public final void zzG(final String str, final Object obj, final Object obj2) {
        this.zzb(3, str, obj, obj2, null);
    }

    public final void zzH(final String str, final Object obj, final Object obj2, final Object obj3) {
        this.zzb(3, "POST compressed size, ratio %, url", obj, obj2, obj3);
    }

    public final void zzI(final String str) {
        this.zzb(6, str, null, null, null);
    }

    public final void zzJ(final String str, @Nullable final Object obj) {
        this.zzb(6, str, obj, null, null);
    }

    public final void zzK(final String str, @Nullable final Object obj, @Nullable final Object obj2) {
        this.zzb(6, str, obj, obj2, null);
    }

    public final void zzL(final String str) {
        this.zzb(4, str, null, null, null);
    }

    public final void zzM(final String str, @Nullable final Object obj) {
        this.zzb(4, str, obj, null, null);
    }

    public final void zzN(final String str) {
        this.zzb(2, str, null, null, null);
    }

    public final void zzO(final String str, @Nullable final Object obj) {
        this.zzb(2, str, obj, null, null);
    }

    public final void zzP(final String str, @Nullable final Object obj, @Nullable final Object obj2) {
        this.zzb(2, str, obj, obj2, null);
    }

    public final void zzQ(final String str) {
        this.zzb(5, str, null, null, null);
    }

    public final void zzR(final String str, @Nullable final Object obj) {
        this.zzb(5, str, obj, null, null);
    }

    public final void zzS(final String str, @Nullable final Object obj, @Nullable final Object obj2) {
        this.zzb(5, str, obj, obj2, null);
    }

    public final void zzT(final String str, @Nullable final Object obj, @Nullable final Object obj2, @Nullable final Object obj3) {
        this.zzb(5, "Deleted fewer hits then expected", obj, obj2, obj3);
    }

    
    public final Context zzo() {
        return zza.zza();
    }

    public final GoogleAnalytics zzp() {
        return zza.zzc();
    }

    
    public final zzr zzq() {
        return zza.zzd();
    }

    
    public final zzbh zzr() {
        return zza.zze();
    }

    
    public final zzbp zzs() {
        return zza.zzf();
    }

    public final zzbu zzt() {
        return zza;
    }

    
    public final zzce zzu() {
        return zza.zzh();
    }

    
    public final zzcm zzv() {
        return zza.zzi();
    }

    
    public final zzcs zzw() {
        return zza.zzj();
    }

    
    public final zzcw zzx() {
        return zza.zzk();
    }

    
    public final zzcx zzy() {
        return zza.zzl();
    }

    
    public final zzeo zzz() {
        return zza.zzm();
    }
}
