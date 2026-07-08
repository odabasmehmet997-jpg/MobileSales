package com.proje.mobilesales.core.asynctask;

import android.Manifest;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.core.exception.NoConnectivityException;
import com.proje.mobilesales.core.utils.ContextUtils;
import okhttp3.Interceptor;
import okhttp3.Response;
import java.io.IOException;
import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public class ConnectivityInterceptors implements Interceptor {
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public Response intercept(Chain chain) throws IOException {
        if (isConnected(ContextUtils.getmContext())) {
            return chain.proceed(chain.request().newBuilder().build());
        }
        throw new NoConnectivityException();
    }
}