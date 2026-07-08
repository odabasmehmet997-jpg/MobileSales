package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfd implements zzbf {
    private final String zza;
    private final Context zzb;
    private final zzfc zzc;
    private Throwable th;
    zzfd(Context context, zzfc zzfc) {
        String str;
        this.zzb = context.getApplicationContext();
        this.zzc = zzfc;
        String str2 = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            str = null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str = sb.toString();
        }
        this.zza = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleTagManager", "4.00", str2, str, Build.MODEL, Build.ID);
    }
    static URL zzc(zzbv zzbv) {
        try {
            return new URL(zzbv.zzc());
        } catch (MalformedURLException unused) {
            Log.e("GoogleTagManager", "Error trying to parse the GTM url.");
            return null;
        }
    }
    public void zza(List list) {
        boolean z;
        IOException e2;
        InputStream inputStream;
        int min = Math.min(list.size(), 40);
        boolean z2 = true;
        for (int i2 = 0; i2 < min; i2++) {
            zzbv zzbv = (zzbv) list.get(i2);
            URL zzc2 = zzc(zzbv);
            if (zzc2 == null) {
                Log.w("GoogleTagManager", "No destination: discarding hit.");
                ((zzdp) this.zzc).zza().zzl(zzbv.zzb());
                zzdc.zzb.zzd("Permanent failure dispatching hitId: " + zzbv.zzb());
            } else {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) zzc2.openConnection();
                    if (z2) {
                        try {
                            Context context = this.zzb;
                            Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
                            intent.addCategory(context.getPackageName());
                            intent.putExtra(zzdf.zza, true);
                            context.sendBroadcast(intent);
                        } catch (Throwable th) {
                            th = th;
                            inputStream = null;
                            z = true;
                        }
                    }
                    try {
                        httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, this.zza);
                        int responseCode = httpURLConnection.getResponseCode();
                        InputStream inputStream2 = httpURLConnection.getInputStream();
                        if (responseCode != 200) {
                            Log.w("GoogleTagManager", "Bad response: " + responseCode);
                            this.zzc.zza(zzbv);
                        } else {
                            ((zzdp) this.zzc).zza().zzl(zzbv.zzb());
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e3) {
                                e2 = e3;
                                z = false;
                                Log.w("GoogleTagManager", "Exception sending hit: ".concat(e2.getClass().getSimpleName()));
                                Log.w("GoogleTagManager", e2.getMessage());
                                this.zzc.zza(zzbv);
                                z2 = z;
                            }
                        }
                        httpURLConnection.disconnect();
                        z2 = false;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = null;
                        z = false;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                e2 = e4;
                                Log.w("GoogleTagManager", "Exception sending hit: ".concat(e2.getClass().getSimpleName()));
                                Log.w("GoogleTagManager", e2.getMessage());
                                this.zzc.zza(zzbv);
                                z2 = z;
                            }
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                } catch (Throwable e5) {
                    z = z2;
                    e2 = (IOException) e5;
                    Log.w("GoogleTagManager", "Exception sending hit: ".concat(e2.getClass().getSimpleName()));
                    Log.w("GoogleTagManager", e2.getMessage());
                    this.zzc.zza(zzbv);
                    z2 = z;
                }
            }
        }
    }
    public boolean zzb() {
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzb.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdc.zzb.zzd("...no network connectivity");
        return false;
    }
}
