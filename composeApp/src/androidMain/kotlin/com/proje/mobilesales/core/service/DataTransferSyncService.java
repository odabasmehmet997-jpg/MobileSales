package com.proje.mobilesales.core.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.model.GroupItem;
import org.jetbrains.annotations.UnknownNullability;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class DataTransferSyncService extends Service {
    private static final String TAG = "DataTransferSyncService";
    private final BaseErp baseErp;
    private ArrayList<GroupItem> mGroupItems;
    ScheduledExecutorService scheduledExecutorService;
    private long sendToDataPeriod = 0;
    boolean runFlag = true;
    final Runnable doDataSync = new Runnable() { // from class: com.proje.mobilesales.core.service.DataTransferSyncService.1
        @Override // java.lang.Runnable
        public void run() {
            if (Connectivity.isConnected(DataTransferSyncService.this.getApplicationContext())) {
                Log.v(DataTransferSyncService.TAG, "scheduleRun : " + Calendar.getInstance().getTime());
                DataTransferSyncService dataTransferSyncService = DataTransferSyncService.this;
                dataTransferSyncService.runFlag = true;
                dataTransferSyncService.startTransfer();
            }
        }
    };

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public BaseErp getBaseErp() {
        BaseErp baseErp = this.baseErp;
        return baseErp == null ? ErpCreator.getInstance().getmBaseErp() : baseErp;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            if (ContextUtils.getmContext() == null || ContextUtils.getmContext().getPackageName() == null || MobileSales.getInstance().getmContext() == null || getBaseErp() == null) {
                stopSelf();
                return;
            }
            long millis = TimeUnit.MINUTES.toMillis(getBaseErp().getDataExportImportPeriods());
            this.sendToDataPeriod = millis;
            if (millis > 0) {
                try {
                    Log.v(TAG, "scheduleStart : " + Calendar.getInstance().getTime());
                    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
                    this.scheduledExecutorService = newScheduledThreadPool;
                    Runnable runnable = this.doDataSync;
                    long j2 = this.sendToDataPeriod;
                    newScheduledThreadPool.scheduleAtFixedRate(runnable, j2, j2, TimeUnit.MILLISECONDS);
                } catch (Exception e2) {
                    Log.e(MobileSales.TAG, "onCreate: ", e2);
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
                        return;
                    }
                    this.scheduledExecutorService.shutdownNow();
                }
            }
        } catch (Exception e3) {
            Log.e(TAG, "onCreateStopSelf: ", e3);
            stopSelf();
        }
    }

    public void startTransfer() {
        String checkWorkTimeControl = getBaseErp().checkWorkTimeControl(WorkTimeControlProcessType.TransferSend);
        if (checkWorkTimeControl.isEmpty()) {
            sendAllData();
            return;
        }
        Log.e(TAG, "onErrorMsg :" + checkWorkTimeControl);
        getAllData();
    }

    private void sendAllData() {
        if (getBaseErp().isOfflineCustomersExist()) {
            Log.d(TAG, getString(R.string.str_customer_transferring));
            getBaseErp().addOfflineCustomer(new ICustomerSendCompleted() { // from class: com.proje.mobilesales.core.service.DataTransferSyncService.2
                @Override // com.proje.mobilesales.core.interfaces.ICustomerSendCompleted
                public void onCustomerSendCompleted(ResponseEvent responseEvent) {
                    DataTransferSyncService.this.getBaseErp().getSendCreator().getAllSend(new TransferResponseListener(DataTransferSyncService.this));
                }
            });
        } else {
            getBaseErp().getSendCreator().getAllSend(new TransferResponseListener(this));
        }
    }

    
    public void getAllData() {
        boolean z;
        ArrayList<GroupItem> arrayList = this.mGroupItems;
        if (arrayList == null) {
            z = true;
        } else {
            Iterator<GroupItem> it = arrayList.iterator();
            z = false;
            while (it.hasNext() && (z = it.next().isComplete())) {
            }
        }
        if (z) {
            String checkWorkTimeControl = getBaseErp().checkWorkTimeControl(WorkTimeControlProcessType.TransferGet);
            if (checkWorkTimeControl.isEmpty()) {
                if (this.runFlag) {
                    this.runFlag = false;
                    getBaseErp().getAllDataLogo(Preferences.isDemo(this), true);
                    return;
                } else {
                    Log.v(TAG, "runFlag : false");
                    return;
                }
            }
            Log.e(TAG, "onErrorMsg :" + checkWorkTimeControl);
        }
    }

    private record TransferResponseListener(
            WeakReference<DataTransferSyncService> mDataTransferSyncService) implements ResponseListener<Boolean> {
            private TransferResponseListener(DataTransferSyncService mDataTransferSyncService) {
                this.mDataTransferSyncService = new WeakReference<>(mDataTransferSyncService);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel bool) {
                if (this.mDataTransferSyncService.get() != null) {
                    this.mDataTransferSyncService.get().onCreateTransferDataResult(bool, "");
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                Log.e(DataTransferSyncService.TAG, "onError: " + str);
                if (this.mDataTransferSyncService.get() != null) {
                    this.mDataTransferSyncService.get().onCreateTransferDataResult(Boolean.FALSE, str);
                }
            }
        }

    
    public void onCreateTransferDataResult(Boolean bool, String str) {
        if (bool.booleanValue()) {
            ArrayList<GroupItem> groupItems = getBaseErp().getSendCreator().getGroupItems();
            this.mGroupItems = groupItems;
            if (groupItems != null && groupItems.size() > 0) {
                getBaseErp().sendFiche(this.mGroupItems, new SendListener(this));
                return;
            } else {
                getAllData();
                return;
            }
        }
        Log.e(TAG, "onError: " + str);
        getAllData();
    }

    private record SendListener(
            WeakReference<DataTransferSyncService> mDataTransferSyncService) implements ResponseListener<GroupItem> {
            private SendListener(DataTransferSyncService mDataTransferSyncService) {
                this.mDataTransferSyncService = new WeakReference<>(mDataTransferSyncService);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel groupItem) {
                if (this.mDataTransferSyncService.get() != null) {
                    this.mDataTransferSyncService.get().getAllData();
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                Log.e(DataTransferSyncService.TAG, "onError: " + str);
                if (this.mDataTransferSyncService.get() != null) {
                    this.mDataTransferSyncService.get().getAllData();
                }
            }
        }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.d(MobileSales.TAG, "onStartCommand:  startSticky");
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "Stopping DataTransferSyncService");
        ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        super.onDestroy();
    }
}
