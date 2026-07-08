package com.proje.mobilesales.core.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.asynctask.TransferAutoAsyncTask;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.activity.MainActivity;

public class DataSyncService extends Service {
    private int ficheId;
    private int ficheType;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.core.service.DataSyncService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(MobileSales.TAG, "onReceive: receive");
            DataSyncService.this.ficheId = intent.getIntExtra(IntentExtraName.EXTRA_FICHE_ID, -1);
            DataSyncService.this.ficheType = intent.getIntExtra(IntentExtraName.EXTRA_FICHE_TYPE, -1);
            DataSyncService.this.sendFiche();
        }
    };
    private final BroadcastReceiver mNoParamReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.core.service.DataSyncService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(MobileSales.TAG, "onReceive: receive");
            DataSyncService.this.ficheId = intent.getIntExtra(IntentExtraName.EXTRA_FICHE_ID, -1);
            DataSyncService.this.ficheType = intent.getIntExtra(IntentExtraName.EXTRA_FICHE_TYPE, -1);
            DataSyncService.this.sendNoParamFiche();
        }
    };

    private void ensureServiceStaysRunning(Intent intent) {
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mReceiver, new IntentFilter(IntentExtraName.ACTION_FICHE_INSERT));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mNoParamReceiver, new IntentFilter(IntentExtraName.ACTION_FICHE_NO_PARAM_INSERT));
    }
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.d(MobileSales.TAG, "onStartCommand:  startSticky");
        return 1;
    }

    public void sendFiche() {
        Log.d(MobileSales.TAG, "sendFiche: ficheId=" + this.ficheId + " ficheType=" + this.ficheType);
        FTypeControlUtils.setMainFType(FType.values()[this.ficheType]);
        int i2 = this.ficheId;
        MainActivity.sFicheRef = i2;
        TransferAutoAsyncTask.autoFicheTransfer(i2);
    }
    public void sendNoParamFiche() {
        Log.d(MobileSales.TAG, "sendFiche: ficheId=" + this.ficheId + " ficheType=" + this.ficheType);
        FTypeControlUtils.setMainFType(FType.values()[this.ficheType]);
        int i2 = this.ficheId;
        MainActivity.sFicheRef = i2;
        TransferAutoAsyncTask.autoFicheTransferNoParam(i2);
    }
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReceiver);
        } catch (Exception ignored) {
        }
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mNoParamReceiver);
        } catch (Exception ignored) {
        }
        Log.d(MobileSales.TAG, "onDestroy: DataSyncService Destroy but recreate :) YouWillNeverKillMe");
    }
}
