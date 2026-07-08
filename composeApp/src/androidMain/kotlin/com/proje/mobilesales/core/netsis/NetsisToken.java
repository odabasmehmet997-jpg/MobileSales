package com.proje.mobilesales.core.netsis;

import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.http.HttpHeaders;

public abstract class NetsisToken {
    private static final String _BRANCH_CODE = "branchcode";
    private static final String _DB_NAME = "dbname";
    private static final String _DB_PASSWORD = "dbpassword";
    private static final String _DB_PASSWORD_NES = "sad";
    private static final String _DB_TYPE = "dbtype";
    private static final String _DB_USER = "dbuser";
    private static final String _GRANT_TYPE = "grant_type";
    private static final String _PASSWORD = "password";
    private static final String _REFRESH_TOKEN = "refresh_token";
    private static final String _USERNAME = "username";
    protected final NetsisRestTokenApi mRestTokenApi;
    SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper();
    Preferences.NetsisUserSettings mUserSettings = new Preferences.NetsisUserSettings(MobileSales.getInstance().getmContext());

    public NetsisToken(NetsisRestTokenApi netsisRestTokenApi) {
        this.mRestTokenApi = netsisRestTokenApi;
    }

    private final Map<String, String> getTokenFieldMap(NetsisTokenManager netsisTokenManager, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(_GRANT_TYPE, z ? _REFRESH_TOKEN : _PASSWORD);
        if (!z) {
            hashMap.put(_BRANCH_CODE, String.valueOf(this.mUserSettings.getBranchCode()));
            hashMap.put(_PASSWORD, this.mUserSettings.getPassword());
            hashMap.put(_USERNAME, this.mUserSettings.getUsername());
            hashMap.put(_DB_NAME, this.mUserSettings.getDbName());
            hashMap.put(_DB_USER, this.mUserSettings.getDbUsername());
            hashMap.put(_DB_PASSWORD, this.mUserSettings.getDbPassword());
            hashMap.put(_DB_TYPE, String.valueOf(this.mUserSettings.getDbType()));
        } else {
            hashMap.put(_REFRESH_TOKEN, TextUtils.isEmpty(netsisTokenManager.getRefreshToken()) ? "" : netsisTokenManager.getRefreshToken());
        }
        return hashMap;
    }

    protected final void setAuthHeader(Request.Builder builder, String str) {
        if (str != null) {
            builder.header(HttpHeaders.AUTHORIZATION, getBearer());
        }
    }

    protected final String getBearer() {
        return String.format("Bearer %s", new SharedPreferencesHelper().getNetsisTokenManager().getAccessToken());
    }

    protected final void saveTokenManager(NetsisTokenManager netsisTokenManager) {
        this.mSharedPreferencesHelper.saveNetsisTokenManager(netsisTokenManager);
    }

    protected final int responseCount(okhttp3.Response response) {
        int i2 = 1;
        while (true) {
            response = response.priorResponse();
            if (response == null) {
                return i2;
            }
            i2++;
        }
    }

    protected final NetsisTokenManager getTokenManager() {
        return this.mSharedPreferencesHelper.getNetsisTokenManager();
    }

    protected final boolean refreshToken() throws IOException {
        return getToken(true);
    }

    protected final boolean getNewToken() throws IOException {
        return getToken(false);
    }

    protected final boolean getToken(boolean z) throws IOException {
        try {
            if (!HttpUrl.parse(this.mUserSettings.getServerAddress()).host().equals(this.mRestTokenApi.refreshToken(getTokenFieldMap(getTokenManager(), z), NetsisCrypt.generateTrustedKey("B2B"), UUID.randomUUID().toString()).request().url().host())) {
                NetsisTokenService.changeInstance(this.mUserSettings.getServerAddress());
            }
        } catch (Exception e2) {
            Log.e("getTokenRefresh", "error on get refresh token", e2);
        }
        retrofit2.Response<NetsisTokenManager> execute = NetsisTokenService.getInstance(this.mUserSettings.getServerAddress()).getRestTokenApi().refreshToken(getTokenFieldMap(getTokenManager(), z), NetsisCrypt.generateTrustedKey("B2B"), UUID.randomUUID().toString()).execute();
        if (execute.code() != 200) {
            return false;
        }
        setNewToken(execute.body());
        return true;
    }

    protected final boolean logout() throws IOException {
        if (this.mRestTokenApi.logout(getBearer()).execute().code() != 200) {
            return false;
        }
        clearToken();
        return true;
    }

    protected final void setNewToken(NetsisTokenManager netsisTokenManager) {
        saveTokenManager(netsisTokenManager);
    }

    protected final void clearToken() {
        NetsisTokenManager tokenManager = getTokenManager();
        tokenManager.setRefreshToken("");
        tokenManager.setAccessToken("");
        saveTokenManager(tokenManager);
    }
}
