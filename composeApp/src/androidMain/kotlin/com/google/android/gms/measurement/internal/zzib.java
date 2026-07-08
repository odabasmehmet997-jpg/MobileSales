package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzib implements Runnable {
    final zzic zza;
    private final URL zzb;
    private final String zzc;
    private final zzfr zzd;

    public zzib(final zzic zzic, final String str, final URL url, final byte[] bArr, final Map map, final zzfr zzfr, final byte[] bArr2) {
        zza = zzic;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfr);
        zzb = url;
        zzd = zzfr;
        zzc = str;
    }

    private void zzb(final int i2, final Exception exc, final byte[] bArr, final Map map) {
        zza.zzs.zzaz().zzp(new zzia(this, i2, exc, bArr, map));
    }

    /*  WARNING: Removed duplicated region for block: B:29:0x006c A[SYNTHETIC, Splitter:B:29:0x006c] */
    /*  WARNING: Removed duplicated region for block: B:49:0x0093  */
    /*  WARNING: Removed duplicated region for block: B:54:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r10 = this;
            com.google.android.gms.measurement.internal.zzic r0 = r10.zza
            r0.zzax()
            r0 = 0
            r1 = 0
            com.google.android.gms.measurement.internal.zzic r2 = r10.zza     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            java.net.URL r3 = r10.zzb     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            boolean r4 = r3 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            if (r4 == 0) goto L_0x0086
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r3.setDefaultUseCaches(r0)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            com.google.android.gms.measurement.internal.zzft r4 = r2.zzs     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r4.zzf()     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r4 = 60000(0xea60, float:8.4078E-41)
            r3.setConnectTimeout(r4)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r2.zzf()     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r2 = 61000(0xee48, float:8.5479E-41)
            r3.setReadTimeout(r2)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r3.setInstanceFollowRedirects(r0)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            r2 = 1
            r3.setDoInput(r2)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            int r2 = r3.getResponseCode()     // Catch:{ IOException -> 0x007c, all -> 0x0076 }
            java.util.Map r4 = r3.getHeaderFields()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0068 }
            r5.<init>()     // Catch:{ all -> 0x0068 }
            java.io.InputStream r6 = r3.getInputStream()     // Catch:{ all -> 0x0068 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0054 }
        L_0x004a:
            int r8 = r6.read(r7)     // Catch:{ all -> 0x0054 }
            if (r8 <= 0) goto L_0x0056
            r5.write(r7, r0, r8)     // Catch:{ all -> 0x0054 }
            goto L_0x004a
        L_0x0054:
            r0 = move-exception
            goto L_0x006a
        L_0x0056:
            byte[] r0 = r5.toByteArray()     // Catch:{ all -> 0x0054 }
            r6.close()     // Catch:{ IOException -> 0x0066, all -> 0x0064 }
            r3.disconnect()
            r10.zzb(r2, r1, r0, r4)
            return
        L_0x0064:
            r0 = move-exception
            goto L_0x0091
        L_0x0066:
            r0 = move-exception
            goto L_0x009d
        L_0x0068:
            r0 = move-exception
            r6 = r1
        L_0x006a:
            if (r6 == 0) goto L_0x006f
            r6.close()     // Catch:{ IOException -> 0x0066, all -> 0x0064 }
        L_0x006f:
            throw r0     // Catch:{ IOException -> 0x0066, all -> 0x0064 }
        L_0x0070:
            r0 = move-exception
            r4 = r1
            goto L_0x0091
        L_0x0073:
            r0 = move-exception
            r4 = r1
            goto L_0x009d
        L_0x0076:
            r2 = move-exception
            r4 = r1
        L_0x0078:
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0091
        L_0x007c:
            r2 = move-exception
            r4 = r1
        L_0x007e:
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x009d
        L_0x0082:
            r2 = move-exception
            goto L_0x008e
        L_0x0084:
            r2 = move-exception
            goto L_0x009a
        L_0x0086:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            java.lang.String r3 = "Failed to obtain HTTP connection"
            r2.<init>(r3)     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
            throw r2     // Catch:{ IOException -> 0x0084, all -> 0x0082 }
        L_0x008e:
            r3 = r1
            r4 = r3
            goto L_0x0078
        L_0x0091:
            if (r3 == 0) goto L_0x0096
            r3.disconnect()
        L_0x0096:
            r10.zzb(r2, r1, r1, r4)
            throw r0
        L_0x009a:
            r3 = r1
            r4 = r3
            goto L_0x007e
        L_0x009d:
            if (r3 == 0) goto L_0x00a2
            r3.disconnect()
        L_0x00a2:
            r10.zzb(r2, r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzib.run():void");
    }


    public void zza(final int i2, final Exception exc, final byte[] bArr, final Map map) {
        final zzfr zzfr = zzd;
        zzfr.zza().zzC(zzc, i2, exc, bArr, map);
    }
}
