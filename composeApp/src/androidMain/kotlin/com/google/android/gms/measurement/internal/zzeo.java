package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzeo implements Runnable {
    final zzep zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzel zzd;
    private final String zze;
    private final Map zzf;

    public zzeo(final zzep zzep, final String str, final URL url, final byte[] bArr, final Map map, final zzel zzel) {
        zza = zzep;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzel);
        zzb = url;
        zzc = bArr;
        zzd = zzel;
        zze = str;
        zzf = map;
    }

    /*  WARNING: Removed duplicated region for block: B:53:0x0104 A[SYNTHETIC, Splitter:B:53:0x0104] */
    /*  WARNING: Removed duplicated region for block: B:73:0x0135 A[SYNTHETIC, Splitter:B:73:0x0135] */
    /*  WARNING: Removed duplicated region for block: B:78:0x0151  */
    /*  WARNING: Removed duplicated region for block: B:83:0x0174 A[SYNTHETIC, Splitter:B:83:0x0174] */
    /*  WARNING: Removed duplicated region for block: B:88:0x0190  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzep r1 = r14.zza
            r1.zzax()
            r1 = 0
            r2 = 0
            com.google.android.gms.measurement.internal.zzep r3 = r14.zza     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            java.net.URL r4 = r14.zzb     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            if (r5 == 0) goto L_0x0128
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r4.setDefaultUseCaches(r1)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzft r5 = r3.zzs     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r5.zzf()     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r5 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r5)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzft r3 = r3.zzs     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r3.zzf()     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r3 = 61000(0xee48, float:8.5479E-41)
            r4.setReadTimeout(r3)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r4.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            r3 = 1
            r4.setDoInput(r3)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            java.util.Map r5 = r14.zzf     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            if (r5 == 0) goto L_0x0065
            java.util.Set r5 = r5.entrySet()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
        L_0x0043:
            boolean r6 = r5.hasNext()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            if (r6 == 0) goto L_0x0065
            java.lang.Object r6 = r5.next()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.util.MapEntry r6 = (java.util.Map.Entry) r6     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.Object r7 = r6.getKey()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.Object r6 = r6.getValue()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r4.addRequestProperty(r7, r6)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            goto L_0x0043
        L_0x005f:
            r3 = move-exception
            goto L_0x011a
        L_0x0062:
            r3 = move-exception
            goto L_0x011e
        L_0x0065:
            byte[] r5 = r14.zzc     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            if (r5 == 0) goto L_0x00b7
            com.google.android.gms.measurement.internal.zzep r5 = r14.zza     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzkr r5 = r5.zzf     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzkt r5 = r5.zzu()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            byte[] r6 = r14.zzc     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            byte[] r5 = r5.zzy(r6)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzep r6 = r14.zza     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzft r6 = r6.zzs     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzay()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzj()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            int r7 = r5.length     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.String r8 = "Uploading data. size"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r6.zzb(r8, r9)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r4.setDoOutput(r3)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.String r3 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r4.addRequestProperty(r3, r6)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r4.setFixedLengthStreamingMode(r7)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r4.connect()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.io.OutputStream r3 = r4.getOutputStream()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r3.write(r5)     // Catch:{ IOException -> 0x00aa, all -> 0x00a8 }
            r3.close()     // Catch:{ IOException -> 0x00aa, all -> 0x00a8 }
            goto L_0x00b7
        L_0x00a8:
            r5 = move-exception
            goto L_0x00ac
        L_0x00aa:
            r5 = move-exception
            goto L_0x00b1
        L_0x00ac:
            r9 = r1
            r12 = r2
            r2 = r3
            goto L_0x0133
        L_0x00b1:
            r9 = r1
            r12 = r2
            r2 = r3
        L_0x00b4:
            r10 = r5
            goto L_0x0172
        L_0x00b7:
            int r8 = r4.getResponseCode()     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.util.Map r11 = r4.getHeaderFields()     // Catch:{ IOException -> 0x0114, all -> 0x0110 }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0100 }
            r3.<init>()     // Catch:{ all -> 0x0100 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ all -> 0x0100 }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x00d6 }
        L_0x00cc:
            int r7 = r5.read(r6)     // Catch:{ all -> 0x00d6 }
            if (r7 <= 0) goto L_0x00d8
            r3.write(r6, r1, r7)     // Catch:{ all -> 0x00d6 }
            goto L_0x00cc
        L_0x00d6:
            r1 = move-exception
            goto L_0x0102
        L_0x00d8:
            byte[] r10 = r3.toByteArray()     // Catch:{ all -> 0x00d6 }
            r5.close()     // Catch:{ IOException -> 0x00fd, all -> 0x00fa }
            r4.disconnect()
            com.google.android.gms.measurement.internal.zzep r0 = r14.zza
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfq r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzen r1 = new com.google.android.gms.measurement.internal.zzen
            java.lang.String r6 = r14.zze
            com.google.android.gms.measurement.internal.zzel r7 = r14.zzd
            r9 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
        L_0x00f6:
            r0.zzp(r1)
            return
        L_0x00fa:
            r1 = move-exception
            r5 = r1
            goto L_0x0108
        L_0x00fd:
            r1 = move-exception
            r5 = r1
            goto L_0x010b
        L_0x0100:
            r1 = move-exception
            r5 = r2
        L_0x0102:
            if (r5 == 0) goto L_0x0107
            r5.close()     // Catch:{ IOException -> 0x00fd, all -> 0x00fa }
        L_0x0107:
            throw r1     // Catch:{ IOException -> 0x00fd, all -> 0x00fa }
        L_0x0108:
            r9 = r8
            r12 = r11
            goto L_0x0133
        L_0x010b:
            r10 = r5
            r9 = r8
            r12 = r11
            goto L_0x0172
        L_0x0110:
            r5 = move-exception
            r12 = r2
            r9 = r8
            goto L_0x0133
        L_0x0114:
            r5 = move-exception
            r12 = r2
            r10 = r5
            r9 = r8
            goto L_0x0172
        L_0x011a:
            r9 = r1
            r12 = r2
            r5 = r3
            goto L_0x0133
        L_0x011e:
            r9 = r1
            r12 = r2
            r10 = r3
            goto L_0x0172
        L_0x0122:
            r3 = move-exception
            r5 = r3
            goto L_0x0130
        L_0x0125:
            r3 = move-exception
            r5 = r3
            goto L_0x016d
        L_0x0128:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            java.lang.String r4 = "Failed to obtain HTTP connection"
            r3.<init>(r4)     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
            throw r3     // Catch:{ IOException -> 0x0125, all -> 0x0122 }
        L_0x0130:
            r9 = r1
            r4 = r2
            r12 = r4
        L_0x0133:
            if (r2 == 0) goto L_0x014f
            r2.close()     // Catch:{ IOException -> 0x0139 }
            goto L_0x014f
        L_0x0139:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzep r2 = r14.zza
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()
            java.lang.String r3 = r14.zze
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzej.zzn(r3)
            r2.zzc(r0, r3, r1)
        L_0x014f:
            if (r4 == 0) goto L_0x0154
            r4.disconnect()
        L_0x0154:
            com.google.android.gms.measurement.internal.zzep r0 = r14.zza
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfq r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzen r1 = new com.google.android.gms.measurement.internal.zzen
            java.lang.String r7 = r14.zze
            com.google.android.gms.measurement.internal.zzel r8 = r14.zzd
            r11 = 0
            r13 = 0
            r10 = 0
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            r0.zzp(r1)
            throw r5
        L_0x016d:
            r9 = r1
            r4 = r2
            r12 = r4
            goto L_0x00b4
        L_0x0172:
            if (r2 == 0) goto L_0x018e
            r2.close()     // Catch:{ IOException -> 0x0178 }
            goto L_0x018e
        L_0x0178:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzep r2 = r14.zza
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzd()
            java.lang.String r3 = r14.zze
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzej.zzn(r3)
            r2.zzc(r0, r3, r1)
        L_0x018e:
            if (r4 == 0) goto L_0x0193
            r4.disconnect()
        L_0x0193:
            com.google.android.gms.measurement.internal.zzep r0 = r14.zza
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfq r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzen r1 = new com.google.android.gms.measurement.internal.zzen
            java.lang.String r7 = r14.zze
            com.google.android.gms.measurement.internal.zzel r8 = r14.zzd
            r11 = 0
            r13 = 0
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            goto L_0x00f6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeo.run():void");
    }
}
