package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzek {
    private final Map zza;
    private final List zzb;
    private final long zzc;
    private final long zzd;
    private final int zze;
    private final boolean zzf;
    private final String zzg;

    public zzek(zzbq zzbq, Map map, long j2, boolean z) {
        this(zzbq, map, j2, z, 0, 0, null);
    }

    public static zzek zze(zzbq zzbq, zzek zzek, Map map) {
        return new zzek(zzbq, map, zzek.zzd, zzek.zzf, zzek.zzc, zzek.zze, zzek.zzb);
    }

    private String zzi(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(!str.startsWith("&"), "Short param name required");
        String str3 = (String) this.zza.get(str);
        return null != str3 ? str3 : str2;
    }

    private static String zzj(zzbq zzbq, Object obj) {
        if (null == obj) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (256 < length) {
            obj2 = obj2.substring(0, 256);
            zzbq.zzS("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static boolean zzl(Object obj) {
        if (null == obj) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ht=");
        sb.append(this.zzd);
        if (0 != zzc) {
            sb.append(", dbId=");
            sb.append(this.zzc);
        }
        if (0 != zze) {
            sb.append(", appUID=");
            sb.append(this.zze);
        }
        ArrayList arrayList = new ArrayList(this.zza.keySet());
        Collections.sort(arrayList);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = (String) arrayList.get(i2);
            sb.append(", ");
            sb.append(str);
            sb.append("=");
            sb.append((String) this.zza.get(str));
        }
        return sb.toString();
    }

    public int zza() {
        return this.zze;
    }

    public long zzb() {
        return this.zzc;
    }

    public long zzc() {
        return zzff.zza(zzi("_s", "0"));
    }

    public long zzd() {
        return this.zzd;
    }

    public String zzf() {
        return zzi("_m", "");
    }

    public Map zzg() {
        return this.zza;
    }

    public boolean zzh() {
        return this.zzf;
    }

    public zzek(com.google.android.gms.internal.gtm.zzbq r1, java.util.Map r2, long r3, boolean r5, long r6, int r8, java.util.List r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzek.<init>(com.google.android.gms.internal.gtm.zzbq, java.util.Map, long, boolean, long, int, java.util.List):void");
    }

    private static String zzk(zzbq zzbq, Object obj) {
        String str;
        if (null == obj) {
            str = "";
        } else {
            str = obj.toString();
        }
        int length = str.length();
        if (8192 >= length) {
            return str;
        }
        String substring = str.substring(0, 8192);
        zzbq.zzS("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }
}
