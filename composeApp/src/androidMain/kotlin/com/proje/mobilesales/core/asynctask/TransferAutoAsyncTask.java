package com.proje.mobilesales.core.asynctask; 

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.data.SendCreatorImpl;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.CustomToast;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.model.GroupItem;
import com.proje.mobilesales.features.sales.model.Sales;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
public class TransferAutoAsyncTask {
    static int sLocalFicheRef;
    static ArrayList<TransferData> transferDataList = new ArrayList<>();
    public static void autoFicheTransfer(int i2) {
        int convertStringToInt;
        if (i2 > 0) {
            sLocalFicheRef = i2;
            if ((convertStringToInt = StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().upVal("245"))) == 0 || !ContextUtils.checkInternetConnection()) {
                return;
            }
            if (convertStringToInt == 1) {
                new Handler().postDelayed(new Runnable() { 

                    public void run() {
                        TransferAutoAsyncTask.lambdaautoFicheTransfer2();
                    }
                }, 1000);
            } else {
                new CheckCustomerBeforeAutoTransferFiche(null).execute();
            }
        }
    }
    public static  void lambdaautoFicheTransfer2() {
        try {
            new AlertDialog.Builder(ContextUtils.getmContext()).setMessage(ContextUtils.getmContext().getString(R.string.str_question_want_transfer)).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    TransferAutoAsyncTask.lambdaautoFicheTransfer0(dialogInterface, i2);
                }
            }).setNegativeButton(ContextUtils.getmContext().getString(R.string.str_no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    TransferAutoAsyncTask.lambdaautoFicheTransfer0(dialogInterface, i2);
                }
            }).show();
        } catch (Exception unused) {
            Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
        }
    }
    public static  void lambdaautoFicheTransfer0(DialogInterface dialogInterface, int i2) {
        new CheckCustomerBeforeAutoTransferFiche(null).execute();
        dialogInterface.dismiss();
    }
    public static void autoFicheTransferNoParam(int i2) {
        sLocalFicheRef = i2;
        if (i2 > 0) {
            new CheckCustomerBeforeAutoTransferFiche(null).execute();
        }
    }
    public static void publishFicheSendNotification(int i2, boolean z, boolean z2) {
        LocalBroadcastManager.getInstance(ContextUtils.getmContext()).sendBroadcast(new Intent(IntentExtraName.ACTION_TRANSFER_FICHE).putExtra(IntentExtraName.EXTRA_TRANSFER_ID, i2).putExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, z).putExtra(IntentExtraName.EXTRA_TRANSFER_CLREF, z2));
    }
    public static class RiskCalculateAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private final int mClRef;
        ProgressDialog progressDialog = new ProgressDialog(ContextUtils.getmContext());
        public RiskCalculateAsyncTask(int i2) {
            this.mClRef = i2;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage(ContextUtils.getmContext().getString(R.string.str_please_wait));
            this.progressDialog.show();
        }

        public Boolean doInBackground(Void... voidArr) {
            BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            ClRisk customerRiskTotals = baseErp.getCustomerRiskTotals(this.mClRef);
            if (customerRiskTotals == null) {
                return Boolean.FALSE;
            }
            baseErp.updateCustomerRiskTotals(customerRiskTotals, "", this.mClRef);
            baseErp.getLogoSqlHelper().updateCustomerRiskTotals(customerRiskTotals);
            return Boolean.TRUE;
        }
        @Override
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            this.progressDialog.dismiss();
        }
    }
    public static class AutoTransferFicheSendWcf extends AsyncTask<Void, Void, Boolean> {
        private final boolean isClRefUpdate;
        private boolean transferContinue;
        private String error = "";
        private boolean success = false;
        private String transferError = "";
        private String netError = "";
        public AutoTransferFicheSendWcf(boolean z) {
            this.isClRefUpdate = z;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            this.transferContinue = true;
            this.netError = "";
            Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_please_wait), Toast.LENGTH_LONG).show();
            String typeMessage = TransferAutoAsyncTask.getTypeMessage();
            Context context = ContextUtils.getmContext();
            Toast.makeText(context, typeMessage + " " + ContextUtils.getStringResource(R.string.str_data_transferring), Toast.LENGTH_LONG).show();
        }
        public Boolean doInBackground(Void... voidArr) {
            if (!ContextUtils.checkConnectionWithoutMessage()) {
                this.error = ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found);
                return Boolean.FALSE;
            }
            String checkWorkTimeControl = ErpCreator.getInstance().getmBaseErp().checkWorkTimeControl(WorkTimeControlProcessType.TransferSend);
            if (!checkWorkTimeControl.equals("")) {
                this.error = checkWorkTimeControl;
                return Boolean.FALSE;
            }
            GroupItem groupItem = getGroupItem();
            if (groupItem == null) {
                return Boolean.FALSE;
            }
            if (!TransferAutoAsyncTask.isDataTransferring(String.valueOf(TransferAutoAsyncTask.sLocalFicheRef), FTypeControlUtils.getMainFType()).isEmpty()) {
                return Boolean.FALSE;
            }
            TransferAutoAsyncTask.transferDataList.add(new TransferData(String.valueOf(TransferAutoAsyncTask.sLocalFicheRef), FTypeControlUtils.getMainFType()));
            ErpCreator.getInstance().getmBaseErp().sendFiche(groupItem, new ResponseListener<GroupItem>() {
                public void onResponse( GroupItem groupItem2) {
                    Log.d(MobileSales.TAG, "onResponse: test gönderim transfer fiche " + groupItem2.isComplete() + groupItem2.isError() + ((BaseResult) groupItem2.getItemList().get(0)).isSuccess());
                    AutoTransferFicheSendWcf.this.transferError = groupItem2.getErrorMessage();
                    AutoTransferFicheSendWcf.this.success = !groupItem2.isError();
                    AutoTransferFicheSendWcf.this.success = ((BaseResult) groupItem2.getItemList().get(0)).isSuccess();
                    AutoTransferFicheSendWcf.this.transferError = ((BaseResult) groupItem2.getItemList().get(0)).getErrorString();
                    AutoTransferFicheSendWcf.this.endTask();
                    try {
                        new RiskCalculateAsyncTask(((BaseResult) groupItem2.getItemList().get(0)).getClRef()).execute();
                    } catch (Exception unused) {
                        Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                    }
                }

                @Override
                public void onResponse(ArrayList<GroupItem> obj) {

                }

                @Override
                public void onResponse() {

                }

                public void onError(String str) {
                    Log.d(MobileSales.TAG, "onResponse: test gönderim " + str);
                    AutoTransferFicheSendWcf.this.transferError = str;
                    AutoTransferFicheSendWcf.this.success = false;
                    AutoTransferFicheSendWcf.this.endTask();
                }
                public void onResponse(@Nullable @UnknownNullability PrintSlipModel t) {

                }

                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onResponse(Boolean aBoolean) {

                }

                @Override
                public void onResponse(Sales sales) {

                }

                @Override
                public void onResponse(TigerServiceResult tigerServiceResult) {

                }
            });
            return Boolean.TRUE;
        }
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (!bool.booleanValue()) {
                endTask();
            }
        }
        private GroupItem getGroupItem() {
            SendCreatorImpl sendCreator = ErpCreator.getInstance().getmBaseErp().getSendCreator();
            if (FTypeControlUtils.isMainFTypeOrder()) {
                return sendCreator.getOrder(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeDemand()) {
                return sendCreator.getDemand(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeInvoice()) {
                return ((SendCreatorImpl<?, ?>) sendCreator).getInvoice(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeDispatch()) {
                return sendCreator.getDispatch(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeOneToOne()) {
                return sendCreator.getOneToOne(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeCashReceipt()) {
                return sendCreator.getCash(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
                return sendCreator.getCaseCash(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
                return sendCreator.getCreditCard(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
                return sendCreator.getCheque(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
                return sendCreator.getDeed(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypePenetration()) {
                return sendCreator.getPenetration(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeVisit()) {
                return sendCreator.getVisit(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeToDo()) {
                return sendCreator.getTodoWorProc(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeRetailInvoice()) {
                return sendCreator.getRetailInvoice(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeCabin()) {
                return sendCreator.getCabinTransAndCabin(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            if (FTypeControlUtils.isMainFTypeWhTransfer()) {
                return sendCreator.getWhTransfer(TransferAutoAsyncTask.sLocalFicheRef).getGroupItem();
            }
            return null;
        }
        public void endTask() {
            this.transferContinue = false;
            if (!TextUtils.isEmpty(this.error)) {
                Toast.makeText(ContextUtils.getmContext(), this.error, Toast.LENGTH_LONG).show();
            } else if (!ContextUtils.checkConnectionWithoutMessage()) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_required_internet_connection) + SqlLiteVariable._NEW_LINE + this.netError, Toast.LENGTH_LONG).show();
            } else if (this.success) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_data_transfer_completed), Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.isEmpty(this.transferError)) {
                if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                    CustomToast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.error_general_message_for_object_service), 1, 5).show();
                } else {
                    CustomToast.makeText(ContextUtils.getmContext(), this.transferError, 1, 5).show();
                }
            }
            TransferAutoAsyncTask.publishFicheSendNotification(TransferAutoAsyncTask.sLocalFicheRef, this.success, this.isClRefUpdate);
            TransferAutoAsyncTask.deleteTransferingData(String.valueOf(TransferAutoAsyncTask.sLocalFicheRef), FTypeControlUtils.getMainFType());
            TransferAutoAsyncTask.sLocalFicheRef = -1;
        }
    }
    public static  class AnonymousClass1 {
        static final  int[] SwitchMapcomlogomobilesalescoreenumsFType;

        static {
            int[] iArr = new int[FType.values().length];
            SwitchMapcomlogomobilesalescoreenumsFType = iArr;
            try {
                iArr[FType.siparis.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.talep.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.fatura.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.irsaliye.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.nakit.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.kredikarti.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.nakitkasa.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.cek.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.senet.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.birebir.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.ziyaret.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.YeniMusteri.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.anket.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.penetrasyon.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsFType[FType.perakendefatura.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }
    public static String getTypeMessage() {
        switch (AnonymousClass1.SwitchMapcomlogomobilesalescoreenumsFType[FTypeControlUtils.getMainFType().ordinal()]) {
            case 1:
                return ContextUtils.getStringResource(R.string.str_order);
            case 2:
                return ContextUtils.getStringResource(R.string.str_demand);
            case 3:
                if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                    return ContextUtils.getStringResource(R.string.str_invoice);
                }
                return ContextUtils.getStringResource(R.string.str_return_invoice);
            case 4:
                if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                    return ContextUtils.getStringResource(R.string.str_dispatch);
                }
                return ContextUtils.getStringResource(R.string.str_return_dispatch);
            case 5:
                return ContextUtils.getStringResource(R.string.str_cash_collection);
            case 6:
                return ContextUtils.getStringResource(R.string.str_credit_card_slip);
            case 7:
                return ContextUtils.getStringResource(R.string.str_safe_deposit);
            case 8:
                return ContextUtils.getStringResource(R.string.str_check_collection);
            case 9:
                return ContextUtils.getStringResource(R.string.str_payroll_note_collection);
            case 10:
                return ContextUtils.getStringResource(R.string.str_exchange);
            case 11:
                return ContextUtils.getStringResource(R.string.str_visit);
            case 12:
                return ContextUtils.getStringResource(R.string.str_new_customer);
            case 13:
                return ContextUtils.getStringResource(R.string.str_survey);
            case 14:
                return ContextUtils.getStringResource(R.string.str_penetration);
            case 15:
                if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                    return ContextUtils.getStringResource(R.string.str_sales_retail_invoice);
                }
                return ContextUtils.getStringResource(R.string.str_sales_retail_return_invoice);
            default:
                return "";
        }
    }
    public static class CheckCustomerBeforeAutoTransferFiche extends AsyncTask<Void, Void, Void> {
        private String clCode;
        private int clRef;
        private String className;
        private String error;
        private boolean isClRefUpdate;
        private boolean isCustomerSend;
        private boolean success;
        private final String transferError;

        private CheckCustomerBeforeAutoTransferFiche() {
            this.error = "";
            this.className = "";
            this.transferError = "";
            this.clCode = "";
            this.clRef = 0;
        }
        CheckCustomerBeforeAutoTransferFiche(AnonymousClass1 r1) {
            this();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            int i2 = AnonymousClass1.SwitchMapcomlogomobilesalescoreenumsFType[FTypeControlUtils.getMainFType().ordinal()];
            if (i2 != 1) {
                if (i2 != 15) {
                    switch (i2) {
                        case 5:
                        case 6:
                            this.className = CashCredit.class.getSimpleName();
                            break;
                        case 7:
                            this.className = CaseCash.class.getSimpleName();
                            break;
                        case 8:
                        case 9:
                            this.className = Chequedeed.class.getSimpleName();
                            break;
                    }
                }
                this.className = Invoice.class.getSimpleName();
            } else {
                this.className = "Orders";
            }
            if (!this.className.isEmpty() && !isCustomerTransfered(this.className)) {
                this.clRef = getFicheCustomerRef(this.className);
                String customerClCode = ErpCreator.getInstance().getmBaseErp().getCustomerClCode(this.clRef);
                this.clCode = customerClCode;
                FType fType = FType.YeniMusteri;
                String isDataTransfering = TransferAutoAsyncTask.isDataTransferring(customerClCode, fType);
                if (!isDataTransfering.equals("")) {
                    Toast.makeText(ContextUtils.getmContext(), isDataTransfering, Toast.LENGTH_LONG).show();
                    return;
                }
                TransferAutoAsyncTask.transferDataList.add(new TransferData(this.clCode, fType));
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_customer_transferring), Toast.LENGTH_LONG).show();
                this.isCustomerSend = true;
            }
        }
        public Void doInBackground(Void... voidArr) {
            if (!ContextUtils.checkConnectionWithoutMessage()) {
                this.error = ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found);
                return null;
            }
            String checkWorkTimeControl = ErpCreator.getInstance().getmBaseErp().checkWorkTimeControl(WorkTimeControlProcessType.TransferSend);
            if (!checkWorkTimeControl.equals("")) {
                this.error = checkWorkTimeControl;
            } else if (this.isCustomerSend) {
                sendCustomer(this.clRef);
            } else {
                this.success = true;
            }
            return null;
        }
        private void sendCustomer(int i2) {
            ErpCreator.getInstance().getmBaseErp().addOfflineCustomer(i2, new ICustomerSendCompleted() {
                public void onCustomerSendCompleted(ResponseEvent responseEvent) {
                    if (responseEvent.isSuccess()) {
                        CheckCustomerBeforeAutoTransferFiche.this.success = true;
                        CheckCustomerBeforeAutoTransferFiche.this.isClRefUpdate = true;
                        CheckCustomerBeforeAutoTransferFiche.this.endCustomerSendTask();
                    } else if (responseEvent.getErrorMessage() != null && !responseEvent.getErrorMessage().isEmpty()) {
                        if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                            Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.error_general_message_for_object_service), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ContextUtils.getmContext(), responseEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }

        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            if (!this.isCustomerSend) {
                endCustomerSendTask();
            }
        }

        private int getFicheCustomerRef(String str) {
            return ErpCreator.getInstance().getmBaseErp().getFicheCustomerRef(str, TransferAutoAsyncTask.sLocalFicheRef);
        }

        private boolean isCustomerTransfered(String str) {
            return ErpCreator.getInstance().getmBaseErp().isFicheCustomerTransfered(str, TransferAutoAsyncTask.sLocalFicheRef);
        }

        public void endCustomerSendTask() {
            TransferAutoAsyncTask.deleteTransferingData(this.clCode, FType.YeniMusteri);
            if (!TextUtils.isEmpty(this.error)) {
                Toast.makeText(ContextUtils.getmContext(), this.error, Toast.LENGTH_LONG).show();
            } else if (this.success) {
                String isDataTransfering = TransferAutoAsyncTask.isDataTransferring(String.valueOf(TransferAutoAsyncTask.sLocalFicheRef), FTypeControlUtils.getMainFType());
                if (!isDataTransfering.equals("")) {
                    Toast.makeText(ContextUtils.getmContext(), isDataTransfering, Toast.LENGTH_LONG).show();
                } else {
                    new AutoTransferFicheSendWcf(this.isClRefUpdate).execute();
                }
            } else if (!TextUtils.isEmpty(this.transferError)) {
                Toast.makeText(ContextUtils.getmContext(), this.transferError, Toast.LENGTH_LONG).show();
            }
        }
    }
    public static String isDataTransferring(String str, FType fType) {
        for (int i2 = 0; i2 < transferDataList.size(); i2++) {
            if (transferDataList.get(i2).fType == fType && transferDataList.get(i2).logicalRef.equals(str)) {
                return getTypeMessage() + " " + ContextUtils.getStringResource(R.string.str_data_transferring);
            }
        }
        return "";
    }
    public static void deleteTransferingData(String str, FType fType) {
        for (int i2 = 0; i2 < transferDataList.size(); i2++) {
            if (transferDataList.get(i2).fType == fType && transferDataList.get(i2).logicalRef.equals(str)) {
                transferDataList.remove(i2);
                return;
            }
        }
    }
    public static class TransferData {
        public FType fType;
        public String logicalRef;

        TransferData(String str, FType fType) {
            this.logicalRef = str;
            this.fType = fType;
        }
    }
    public static class AnonymousClass2 {
        public Object invoke(Object p0) {
            return p0;
        }
    }
}
