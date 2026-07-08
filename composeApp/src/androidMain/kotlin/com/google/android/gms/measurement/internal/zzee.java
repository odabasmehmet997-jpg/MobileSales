package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzee {
    private static final AtomicReference zza = new AtomicReference();
    private static final AtomicReference zzb = new AtomicReference();
    private static final AtomicReference zzc = new AtomicReference();
    private final zzed zzd;

    public zzee(final zzed zzed) {
        zzd = zzed;
    }

    private static String zzg(final String str, final String[] strArr, final String[] strArr2, final AtomicReference atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (zzky.zzak(str, strArr[i2])) {
                synchronized (atomicReference) {
                    try {
                        String[] strArr3 = (String[]) atomicReference.get();
                        if (null == strArr3) {
                            strArr3 = new String[strArr2.length];
                            atomicReference.set(strArr3);
                        }
                        str2 = strArr3[i2];
                        if (null == str2) {
                            str2 = strArr2[i2] + "(" + strArr[i2] + ")";
                            strArr3[i2] = str2;
                        }
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    
    public String zza(final Object[] objArr) {
        String str;
        if (null == objArr) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (final Bundle bundle : objArr) {
            if (bundle instanceof Bundle) {
                str = this.zzb(bundle);
            } else {
                str = String.valueOf(bundle);
            }
            if (null != str) {
                if (1 != sb.length()) {
                    sb.append(", ");
                }
                sb.append(str);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    
    public String zzb(final Bundle bundle) {
        String str;
        if (null == bundle) {
            return null;
        }
        if (!zzd.zza()) {
            return bundle.toString();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (final String next : bundle.keySet()) {
            if (8 != sb.length()) {
                sb.append(", ");
            }
            sb.append(this.zze(next));
            sb.append("=");
            final Object obj = bundle.get(next);
            if (obj instanceof Bundle) {
                str = this.zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                str = this.zza((Object[]) obj);
            } else if (obj instanceof ArrayList) {
                str = this.zza(((ArrayList) obj).toArray());
            } else {
                str = String.valueOf(obj);
            }
            sb.append(str);
        }
        sb.append("}]");
        return sb.toString();
    }

    
    public String zzc(final zzau zzau) {
        final String str;
        if (!zzd.zza()) {
            return zzau.toString();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzau.zzc);
        sb.append(",name=");
        sb.append(this.zzd(zzau.zza));
        sb.append(",params=");
        final zzas zzas = zzau.zzb;
        if (null == zzas) {
            str = null;
        } else if (!zzd.zza()) {
            str = zzas.toString();
        } else {
            str = this.zzb(zzas.zzc());
        }
        sb.append(str);
        return sb.toString();
    }

    
    public String zzd(final String str) {
        if (null == str) {
            return null;
        }
        if (!zzd.zza()) {
            return str;
        }
        return zzee.zzg(str, zzgq.zzc, zzgq.zza, zzee.zza);
    }

    
    public String zze(final String str) {
        if (null == str) {
            return null;
        }
        if (!zzd.zza()) {
            return str;
        }
        return zzee.zzg(str, zzgr.zzb, zzgr.zza, zzee.zzb);
    }

    
    public String zzf(final String str) {
        if (null == str) {
            return null;
        }
        if (!zzd.zza()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zzee.zzg(str, zzgs.zzb, zzgs.zza, zzee.zzc);
        }
        return "experiment_id(" + str + ")";
    }
}
