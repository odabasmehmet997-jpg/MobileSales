package com.proje.mobilesales.core.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.AppUtils;
import com.proje.mobilesales.core.utils.ContextUtils;

public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectivityReceiverListener connectivityReceiverListener;
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean z);
    }
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public void onReceive(Context context, Intent intent) {
        ConnectivityReceiverListener connectivityReceiverListener2 = connectivityReceiverListener;
        if (connectivityReceiverListener2 != null) {
            connectivityReceiverListener2.onNetworkConnectionChanged(AppUtils.isConnected(ContextUtils.getmContext()));
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) MobileSales.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
