package com.google.android.gms.internal.gtm;

import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzrn implements zzro {
    private HttpURLConnection zza;
    private InputStream zzb;

    zzrn() {
    }

    public InputStream zza(final String str) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        zza = httpURLConnection;
        final int responseCode = httpURLConnection.getResponseCode();
        if (200 == responseCode) {
            final InputStream inputStream = httpURLConnection.getInputStream();
            zzb = inputStream;
            return inputStream;
        }
        final String str2 = "Bad response: " + responseCode;
        if (404 == responseCode) {
            throw new FileNotFoundException(str2);
        } else if (503 == responseCode) {
            throw new zzrq(str2);
        } else {
            throw new IOException(str2);
        }
    }

    public void zzb() {
        final HttpURLConnection httpURLConnection = zza;
        try {
            final InputStream inputStream = zzb;
            if (null != inputStream) {
                inputStream.close();
            }
        } catch (final IOException e2) {
            Log.e("GoogleTagManager", "HttpUrlConnectionNetworkClient: Error when closing http input stream: " + e2.getMessage(), e2);
        }
        if (null != httpURLConnection) {
            httpURLConnection.disconnect();
        }
    }
}
