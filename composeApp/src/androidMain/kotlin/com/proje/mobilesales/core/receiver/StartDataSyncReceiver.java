package com.proje.mobilesales.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.proje.mobilesales.core.service.DataSyncService;



public class StartDataSyncReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, DataSyncService.class));
    }
}
