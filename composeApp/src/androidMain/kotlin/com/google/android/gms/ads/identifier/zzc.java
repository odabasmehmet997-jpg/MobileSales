package com.google.android.gms.ads.identifier;

import android.util.Log;
import com.google.android.gms.internal.ads_identifier.zzi;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zzc {
    public static void zza(String str) {
        try {
            zzi.zzb(263);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    String sb = "Received non-success response code " +
                            responseCode +
                            " from pinging URL: " +
                            str;
                    Log.w("HttpUrlPinger", sb);
                }
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException | RuntimeException e2) {
            String message = e2.getMessage();
            String sb2 = "Error while pinging URL: " +
                    str +
                    ". " +
                    message;
            Log.w("HttpUrlPinger", sb2, e2);
        } finally {
            zzi.zza();
        }
    }
}
