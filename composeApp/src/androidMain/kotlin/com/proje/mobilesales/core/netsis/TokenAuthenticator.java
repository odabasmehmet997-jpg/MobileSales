package com.proje.mobilesales.core.netsis;

import android.text.TextUtils;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Route;
import org.springframework.http.HttpHeaders;

public class TokenAuthenticator extends NetsisToken implements Authenticator {
    public TokenAuthenticator(NetsisRestTokenApi netsisRestTokenApi) {
        super(netsisRestTokenApi);
    }

    public Request authenticate(Route route, okhttp3.Response response) throws IOException {
        if (response.request().header(HttpHeaders.AUTHORIZATION) == null && responseCount(response) <= 2) {
            if (!refreshToken() && !getNewToken()) {
                logout();
            }
            if (!TextUtils.isEmpty(getTokenManager().getAccessToken())) {
                return response.request();
            }
        }
        return null;
    }
}
