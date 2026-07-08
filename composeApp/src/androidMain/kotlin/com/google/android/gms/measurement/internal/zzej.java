package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzej extends zzgn {
    
    public char zza;
    
    public long zzb = -1;
    @GuardedBy("this")
    private String zzc;
    private final zzeh zzd = new zzeh(this, 6, false, false);
    private final zzeh zze = new zzeh(this, 6, true, false);
    private final zzeh zzf = new zzeh(this, 6, false, true);
    private final zzeh zzg = new zzeh(this, 5, false, false);
    private final zzeh zzh = new zzeh(this, 5, true, false);
    private final zzeh zzi = new zzeh(this, 5, false, true);
    private final zzeh zzj = new zzeh(this, 4, false, false);
    private final zzeh zzk = new zzeh(this, 3, false, false);
    private final zzeh zzl = new zzeh(this, 2, false, false);

    zzej(zzft zzft) {
        super(zzft);
    }

    static Object zzn(String str) {
        if (null == str) {
            return null;
        }
        return new zzei(str);
    }

    static String zzo(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (null == str) {
            str = str2;
        }
        String zzp = zzp(z, obj);
        String zzp2 = zzp(z, obj2);
        String zzp3 = zzp(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zzp)) {
            sb.append(str2);
            sb.append(zzp);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zzp2)) {
            sb.append(str2);
            sb.append(zzp2);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zzp3)) {
            sb.append(str3);
            sb.append(zzp3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    static String zzp(boolean z, Object obj) {
        String className;
        String str = "";
        if (null == obj) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i2 = 0;
        if (obj instanceof final Long l) {
            if (!z) {
                return obj.toString();
            }
            if (100 > Math.abs(l.longValue())) {
                return obj.toString();
            }
            if ('-' == obj.toString().charAt(0)) {
                str = "-";
            }
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, valueOf.length() - 1));
            long round2 = Math.round(Math.pow(10.0d, valueOf.length()) - 4.0d);
            final String sb = str +
                    round +
                    "..." +
                    str +
                    round2;
            return sb;
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else {
            if (obj instanceof final Throwable th) {
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzy = zzy(zzft.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (!stackTraceElement.isNativeMethod() && null != (className = stackTraceElement.getClassName()) && zzy(className).equals(zzy)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                        break;
                    }
                    i2++;
                }
                return sb2.toString();
            } else if (obj instanceof zzei) {
                return ((zzei) obj).zza();
            } else {
                if (z) {
                    return "-";
                }
                return obj.toString();
            }
        }
    }

    private static String zzy(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (-1 == lastIndexOf) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public zzeh zzc() {
        return this.zzk;
    }

    public zzeh zzd() {
        return this.zzd;
    }

    public zzeh zze() {
        return this.zzf;
    }

    
    public boolean zzf() {
        return false;
    }

    public zzeh zzh() {
        return this.zze;
    }

    public zzeh zzi() {
        return this.zzj;
    }

    public zzeh zzj() {
        return this.zzl;
    }

    public zzeh zzk() {
        return this.zzg;
    }

    public zzeh zzl() {
        return this.zzi;
    }

    public zzeh zzm() {
        return this.zzh;
    }

    
    @VisibleForTesting
    public String zzq() {
        String str;
        synchronized (this) {
            try {
                if (null == zzc) {
                    if (null != zzs.zzy()) {
                        this.zzc = this.zzs.zzy();
                    } else {
                        this.zzc = this.zzs.zzf().zzn();
                    }
                }
                Preconditions.checkNotNull(this.zzc);
                str = this.zzc;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    
    public void zzt(int i2, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && Log.isLoggable(zzq(), i2)) {
            Log.println(i2, zzq(), zzo(false, str, obj, obj2, obj3));
        }
        if (!z2 && 5 <= i2) {
            Preconditions.checkNotNull(str);
            zzfq zzo = this.zzs.zzo();
            if (null == zzo) {
                Log.println(6, zzq(), "Scheduler not set. Not logging error/warn");
            } else if (!zzo.zzx()) {
                Log.println(6, zzq(), "Scheduler not initialized. Not logging error/warn");
            } else {
                if (9 <= i2) {
                    i2 = 8;
                }
                zzo.zzp(new zzeg(this, i2, str, obj, obj2, obj3));
            }
        }
    }
}
