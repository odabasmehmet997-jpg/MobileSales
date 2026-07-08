package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzeo extends zzbr {
    private static zzeo zza;

    public zzeo(zzbu zzbu) {
        super(zzbu);
    }

    public static zzeo zza() {
        return zza;
    }

    @VisibleForTesting
    private static String zzf(Object obj) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        String str = "-";
        if (obj instanceof final Long l) {
            if (100 > Math.abs(l.longValue())) {
                return obj.toString();
            }
            char charAt = obj.toString().charAt(0);
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            StringBuilder sb = new StringBuilder();
            if ('-' != charAt) {
                str = "";
            }
            sb.append(str);
            sb.append(Math.round(Math.pow(10.0d, valueOf.length() - 1)));
            sb.append("...");
            sb.append(str);
            sb.append(Math.round(Math.pow(10.0d, valueOf.length()) - 4.0d));
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else {
            return obj instanceof Throwable ? obj.getClass().getCanonicalName() : str;
        }
    }

    public void zzb(zzek zzek, String str) {
        String str2;
        if (null != zzek) {
            str2 = zzek.toString();
        } else {
            str2 = "no hit data";
        }
        zzR("Discarding hit. " + str, str2);
    }

    public void zzc(Map map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (0 < sb.length()) {
                sb.append(',');
            }
            sb.append((String) entry.getKey());
            sb.append('=');
            sb.append((String) entry.getValue());
        }
        zzR("Discarding hit. " + str, sb.toString());
    }

    
    public void zzd() {
        synchronized (zzeo.class) {
            zza = this;
        }
    }

    public synchronized void zze(int i2, String str, Object obj, Object obj2, Object obj3) {
        char c2;
        try {
            Preconditions.checkNotNull(str);
            if (zzw().zzb()) {
                zzw();
                c2 = 'C';
            } else {
                zzw();
                c2 = 'c';
            }
            String str2 = ExifInterface.GPS_MEASUREMENT_3D + "01VDIWEA?".charAt(i2) + c2 + zzbs.zza + ":" + zzbq.zzD(str, zzf(obj), zzf(obj2), zzf(obj3));
            if (1024 < str2.length()) {
                str2 = str2.substring(0, 1024);
            }
            zzeu zzp = zzt().zzp();
            if (null != zzp) {
                zzp.zze().zzc(str2);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
