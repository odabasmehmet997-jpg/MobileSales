package com.google.android.gms.internal.gtm;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzer extends zzbr {
    
    public static final byte[] zza = SqlLiteVariable._NEW_LINE.getBytes(StandardCharsets.UTF_8);
    private final String zzb = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleAnalytics", zzbs.zza, Build.VERSION.RELEASE, zzff.zzd(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zzfb zzc;

    zzer(zzbu zzbu) {
        super(zzbu);
        this.zzc = new zzfb(zzbu.zzr());
    }

    private int zzg(java.net.URL r5, byte[] r6, int r7) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzer.zzg(java.net.URL, byte[], int):int");
    }

    private URL zzh() {
        zzw();
        String zzi = zzcs.zzi();
        zzw();
        try {
            return new URL(zzi + zzeh.zzn.zzb());
        } catch (MalformedURLException e2) {
            zzJ("Error trying to parse the hardcoded host url", e2);
            return null;
        }
    }

    private URL zzi(zzek zzek) {
        String str;
        if (zzek.zzh()) {
            zzw();
            String zzi = zzcs.zzi();
            zzw();
            str = zzi + zzcs.zzj();
        } else {
            zzw();
            String zzk = zzcs.zzk();
            zzw();
            str = zzk + zzcs.zzj();
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e2) {
            zzJ("Error trying to parse the hardcoded host url", e2);
            return null;
        }
    }

    private URL zzj(zzek zzek, String str) {
        String str2;
        if (zzek.zzh()) {
            zzw();
            String zzi = zzcs.zzi();
            zzw();
            str2 = zzi + zzcs.zzj() + "?" + str;
        } else {
            zzw();
            String zzk = zzcs.zzk();
            zzw();
            str2 = zzk + zzcs.zzj() + "?" + str;
        }
        try {
            return new URL(str2);
        } catch (MalformedURLException e2) {
            zzJ("Error trying to parse the hardcoded host url", e2);
            return null;
        }
    }

    private void zzk(java.net.HttpURLConnection r4) throws java.io.IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzer.zzk(java.net.HttpURLConnection):void");
    }

    private static void zzl(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (0 != sb.length()) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, StandardCharsets.UTF_8));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, StandardCharsets.UTF_8));
    }

    
    @VisibleForTesting
    public String zza(zzek zzek, boolean z) {
        String str;
        Preconditions.checkNotNull(zzek);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry entry : zzek.zzg().entrySet()) {
                String str2 = (String) entry.getKey();
                if (!"ht".equals(str2) && !"qt".equals(str2) && !"AppUID".equals(str2) && !"z".equals(str2) && !"_gmsv".equals(str2)) {
                    zzl(sb, str2, (String) entry.getValue());
                }
            }
            zzl(sb, "ht", String.valueOf(zzek.zzd()));
            zzl(sb, "qt", String.valueOf(zzC().currentTimeMillis() - zzek.zzd()));
            zzw();
            if (z) {
                long zzc2 = zzek.zzc();
                if (0 != zzc2) {
                    str = String.valueOf(zzc2);
                } else {
                    str = String.valueOf(zzek.zzb());
                }
                zzl(sb, "z", str);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e2) {
            zzJ("Failed to encode name or value", e2);
            return null;
        }
    }

    
    @VisibleForTesting
    public HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof final HttpURLConnection httpURLConnection) {
            httpURLConnection.setDefaultUseCaches(false);
            zzw();
            httpURLConnection.setConnectTimeout(((Integer) zzeh.zzw.zzb()).intValue());
            zzw();
            httpURLConnection.setReadTimeout(((Integer) zzeh.zzx.zzb()).intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, this.zzb);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    public java.util.List zzc(java.util.List r20) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzer.zzc(java.util.List):java.util.List");
    }

    
    public void zzd() {
        zzO("Network initialized. User agent", this.zzb);
    }

    public boolean zze() {
        NetworkInfo networkInfo;
        zzr.zzh();
        zzV();
        try {
            networkInfo = ((ConnectivityManager) zzo().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (null != networkInfo && networkInfo.isConnected()) {
            return true;
        }
        zzN("No network connectivity");
        return false;
    }
}
