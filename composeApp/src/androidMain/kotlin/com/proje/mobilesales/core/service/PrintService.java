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
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.printutil.PrinterUtility;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
public class PrintService extends Service {
    private static final String TAG = "PrintService";
    private PrinterUtility mPrinterUtility;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.core.service.PrintService.1
        void C25621() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(MobileSales.TAG, "onReceive: receive");
            String action = intent.getAction();
            if (action.equals(IntentExtraName.ACTION_FICHE_PRINT)) {
                PrintService.this.printFiche(intent.getIntExtra(IntentExtraName.EXTRA_FICHE_ID, -1), intent.getIntExtra(IntentExtraName.EXTRA_LOCAL_FICHE_ID, -1), (FicheType) intent.getSerializableExtra(IntentExtraName.EXTRA_FICHE_TYPE), intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false), intent.getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1));
            } else if (action.equals(IntentExtraName.ACTION_TRANSPORT_FICHE_PRINT)) {
                PrintService.this.printTransportFiche(intent.getIntExtra(IntentExtraName.EXTRA_ITEM_ID, -1), (FicheType) intent.getSerializableExtra(IntentExtraName.EXTRA_FICHE_TYPE));
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        try {
            if (ContextUtils.getmContext() == null || ContextUtils.getmContext().getPackageName() == null) {
                stopSelf();
                return;
            }
            IntentFilter intentFilter = new IntentFilter(IntentExtraName.ACTION_FICHE_PRINT);
            intentFilter.addAction(IntentExtraName.ACTION_TRANSPORT_FICHE_PRINT);
            LocalBroadcastManager.getInstance(this).registerReceiver(this.mReceiver, intentFilter);
            this.mPrinterUtility = new PrinterUtility(ErpCreator.getInstance().getmBaseErp());
        } catch (Exception e2) {
            Log.e(TAG, "onCreateStopSelf: ", e2);
            stopSelf();
        }
    }
    public int onStartCommand(Intent intent, int i2, int i3) {
        return super.onStartCommand(intent, i2, i3);
    }

    public void printFiche(int i2, int i3, FicheType ficheType, boolean z, int i4) {
        Log.d(TAG, "printFiche() called with: ficheId = [" + i2 + "], ficheType = [" + ficheType + "]");
        this.mPrinterUtility.print(i2, i3, ficheType, z, i4);
    }

    public void printTransportFiche(int i2, FicheType ficheType) {
        Log.d(TAG, "printTransportFiche() called with: ficheId = [" + i2 + "], ficheType = [" + ficheType + "]");
        this.mPrinterUtility.printTransport(i2, ficheType);
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReceiver);
    }

    class C25621 extends BroadcastReceiver {
        C25621() {
        }
        public void onReceive(Context context, Intent intent) {
            Log.d(MobileSales.TAG, "onReceive: receive");
            String action = intent.getAction();
            if (action.equals(IntentExtraName.ACTION_FICHE_PRINT)) {
                PrintService.this.printFiche(intent.getIntExtra(IntentExtraName.EXTRA_FICHE_ID, -1), intent.getIntExtra(IntentExtraName.EXTRA_LOCAL_FICHE_ID, -1), (FicheType) intent.getSerializableExtra(IntentExtraName.EXTRA_FICHE_TYPE), intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false), intent.getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1));
            } else if (action.equals(IntentExtraName.ACTION_TRANSPORT_FICHE_PRINT)) {
                PrintService.this.printTransportFiche(intent.getIntExtra(IntentExtraName.EXTRA_ITEM_ID, -1), (FicheType) intent.getSerializableExtra(IntentExtraName.EXTRA_FICHE_TYPE));
            }
        }
    }
}
