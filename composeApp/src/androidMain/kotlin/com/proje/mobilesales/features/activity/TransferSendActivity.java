package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.IEditSendFicheListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.AutoVisitUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.view.AnimatedExpandableListView;
import com.proje.mobilesales.features.adapter.ExpandableAdapterCopy;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.model.GroupItem;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import javax.inject.Inject;
 
public class TransferSendActivity extends BaseErpActivity implements IEditSendFicheListener {
    private static final String STATE_COMPLETE = "state:complete";
    private static final String STATE_COUNTER = "state:counter";
    private static final String STATE_GROUP_ITEMS = "state:groupItems";
    public ExpandableAdapterCopy adapter;
    private boolean complete;
    private AnimatedExpandableListView listView;
    AlertDialogBuilder mAlertDialogBuilder;

    @Inject
    BaseErp mBaseErp;
    @Inject
    ISqlManager mSqlManager;
    private int mCounter;
    private ArrayList<GroupItem> mGroupItems;
    private FicheMode mLastFicheMode;
    ProgressDialogBuilder mProgressDialogBuilder;
    public void editSelectedFiche(final BaseResult baseResult, final GroupItem groupItem) {
        this.getFicheEdit(baseResult, groupItem);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.all_send_logo);
        this.getActivityComponent().inject(this);
        this.setToolbar();
        if (null != bundle) {
            mGroupItems = bundle.getParcelableArrayList(TransferSendActivity.STATE_GROUP_ITEMS);
            mCounter = bundle.getInt(TransferSendActivity.STATE_COUNTER);
            complete = bundle.getBoolean(TransferSendActivity.STATE_COMPLETE);
        } else {
            mGroupItems = mBaseErp.getSendCreator().getGroupItems();
            mCounter = 0;
            complete = false;
        }
        if (baseErp.isActiveAutoVisit()) {
            mProgressDialogBuilder.setMessage(this.getString(R.string.type_send_auto_visits)).show();
            AutoVisitUtils.sendAutoVisitsToWorProcess();
            mProgressDialogBuilder.dismiss();
        }
        this.sendAllToLogo();
    }
    private void sendAllToLogo() {
        final ArrayList<GroupItem> arrayList = mGroupItems;
        if (null == arrayList || 0 >= arrayList.size()) {
            return;
        }
        this.adapter = new ExpandableAdapterCopy(this, mGroupItems);
        final AnimatedExpandableListView animatedExpandableListView = this.findViewById(R.id.listView);
        listView = animatedExpandableListView;
        animatedExpandableListView.setAdapter(this.adapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(final ExpandableListView expandableListView, final View view, final int i2, final long j2) {
                final boolean lambdasendAllToLogo0;
                lambdasendAllToLogo0 = lambdasendAllToLogo0(expandableListView, view, i2, j2);
                return lambdasendAllToLogo0;
            }
        });
        this.sendFiche();
    }
    public boolean lambdasendAllToLogo0(final ExpandableListView expandableListView, final View view, final int i2, final long j2) {
        if (listView.isGroupExpanded(i2)) {
            listView.collapseGroupWithAnimation(i2);
            return true;
        }
        listView.expandGroupWithAnimation(i2);
        return true;
    }
    private void initAlertDialog() {
        mAlertDialogBuilder.setTitle(R.string.str_fiche_send_complete).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdainitAlertDialog1(dialogInterface, i2);
            }
        }).create().show();
    }
    public void lambdainitAlertDialog1(final DialogInterface dialogInterface, final int i2) {
        this.adapter.notifyDataSetChanged();
        dialogInterface.dismiss();
    }
    private void sendFiche() {
        baseErp.sendFiche(mGroupItems, new SendListener(this));
    }
    private boolean checkComplete() {
        final Iterator<GroupItem> it = mGroupItems.iterator();
        while (it.hasNext()) {
            if (!it.next().isComplete()) {
                return false;
            }
        }
        return true;
    }
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putSerializable(TransferSendActivity.STATE_GROUP_ITEMS, mGroupItems);
        bundle.putInt(TransferSendActivity.STATE_COUNTER, mCounter);
        bundle.putBoolean(TransferSendActivity.STATE_COMPLETE, complete);
        super.onSaveInstanceState(bundle);
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (16908332 == menuItem.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public void notifyAdapter(final GroupItem groupItem) {
        this.adapter.notifyDataSetChanged();
        if (this.checkComplete()) {
            complete = true;
            final AlertDialogBuilder alertDialogBuilder = mAlertDialogBuilder;
            if (null == alertDialogBuilder || alertDialogBuilder.isShowing()) {
                return;
            }
            this.initAlertDialog();
        }
    }
    public void notifyAdapter(final String str) {
        this.adapter.notifyDataSetChanged();
    }
    private static class RiskCalculateAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private final int mClRef;
        ProgressDialog progressDialog = new ProgressDialog(ContextUtils.getmContext());

        public RiskCalculateAsyncTask(final int i2) {
            mClRef = i2;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(ContextUtils.getmContext().getString(R.string.str_please_wait));
            progressDialog.show();
        }
        protected Boolean doInBackground(final Void... voidArr) {
            final BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            final ClRisk customerRiskTotals = baseErp.getCustomerRiskTotals(mClRef);
            if (null != customerRiskTotals) {
                baseErp.updateCustomerRiskTotals(customerRiskTotals, "", mClRef);
                baseErp.getLogoSqlHelper().updateCustomerRiskTotals(customerRiskTotals);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        protected void onPostExecute(final Boolean bool) {
           // super.onPostExecute((RiskCalculateAsyncTask) bool);
            super.onPostExecute( bool);
            progressDialog.dismiss();
        }
    }
    private record SendListener(WeakReference<TransferSendActivity> mTransferSendActivity) implements ResponseListener<GroupItem> {
            private SendListener(final TransferSendActivity mTransferSendActivity) {
                this(new WeakReference<>(mTransferSendActivity));
            } 
            public void onResponse( final GroupItem groupItem) {
                if (null == this.mTransferSendActivity.get() || mTransferSendActivity.get().isActivityDestroyed()) {
                    return;
                }
                mTransferSendActivity.get().notifyAdapter(groupItem);
                try {
                    new RiskCalculateAsyncTask(((BaseResult) groupItem.getItemList().get(0)).getClRef()).execute();
                } catch (final Exception e2) {
                    e2.printStackTrace();
                }
            }

        @Override
        public void onResponse(ArrayList<GroupItem> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

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

        public void onError(final String str) {
                Log.d(MobileSales.TAG, "onError: " + str);
                if (null == this.mTransferSendActivity.get() || mTransferSendActivity.get().isActivityDestroyed()) {
                    return;
                }
                mTransferSendActivity.get().notifyAdapter(str);
            }
        }
    private void getFicheEdit(final BaseResult baseResult, final GroupItem groupItem) {
        this.setFicheRefType(groupItem, baseResult);
        final CustomerOperation customerOperation = new CustomerOperation();
        final FicheMode ficheMode = FicheMode.EDIT;
        customerOperation.setFicheMode(ficheMode);
        if (FTypeControlUtils.isMainFTypeSalesFiche()) {
            mLastFicheMode = ficheMode;
            mProgressDialogBuilder.setMessage(this.getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
            if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
                mSqlManager.getSalesOrderOne(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                return;
            } else if (FTypeControlUtils.isMainFTypeWhTransfer()) {
                mSqlManager.getSalesWhTransfer(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                return;
            } else {
                mSqlManager.getSalesInvoiceExam(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                return;
            }
        }
        if (FTypeControlUtils.isMainFTypeCashReceipt()) {
            this.openReceiptFiche(MainActivity.sFicheRef, ReceiptType.CASH);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
            this.openReceiptFiche(MainActivity.sFicheRef, ReceiptType.CREDIT);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            this.openReceiptFiche(MainActivity.sFicheRef, ReceiptType.CASE);
        } else if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
            this.openReceiptFiche(MainActivity.sFicheRef, ReceiptType.CHEQUE);
        } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
            this.openReceiptFiche(MainActivity.sFicheRef, ReceiptType.DEED);
        }
    }
    public   void lambdagetFicheEdit2(final DialogInterface dialogInterface) {
        mLastFicheMode = null;
        dialogInterface.dismiss();
    }
    private void openReceiptFiche(final int i2, final ReceiptType receiptType) {
        final String simpleName;
        final CustomerOperation customerOperation = new CustomerOperation();
        customerOperation.setFicheMode(FicheMode.EDIT);
        customerOperation.setReceiptType(receiptType);
        Intent intent = new Intent();
        if (receiptType == ReceiptType.CASH || receiptType == ReceiptType.CREDIT) {
            intent = new Intent(this, CashAndCreditCardFicheActivity.class);
            simpleName = CashCredit.class.getSimpleName();
        } else if (receiptType == ReceiptType.CASE) {
            intent = new Intent(this, CaseFicheActivity.class);
            simpleName = CaseCash.class.getSimpleName();
        } else if (receiptType == ReceiptType.CHEQUE || receiptType == ReceiptType.DEED) {
            intent = new Intent(this, ChequeAndDeedFicheActivity.class);
            simpleName = Chequedeed.class.getSimpleName();
        } else {
            simpleName = "";
        }
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, baseErp.getFicheCustomerRef(simpleName, i2));
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, i2);
        this.editSelectedFiche(intent);
    }
    enum C26201 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferOperationName;

        static {
            final int[] iArr = new int[TransferOperationName.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferOperationName = iArr;
            try {
                iArr[TransferOperationName.INVOICE.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_INVOICE.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_INVOICE.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_RETURN_INVOICE.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DISPATCH.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_DISPATCH.ordinal()] = 6;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ORDER.ordinal()] = 7;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ONE_TO_ONE_CHANGE.ordinal()] = 8;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASH.ordinal()] = 9;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CREDIT_CARD.ordinal()] = 10;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CHEQUE.ordinal()] = 11;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEED.ordinal()] = 12;
            } catch (final NoSuchFieldError unused12) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASE_CASH.ordinal()] = 13;
            } catch (final NoSuchFieldError unused13) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEMAND.ordinal()] = 14;
            } catch (final NoSuchFieldError unused14) {
            }
            try {
                C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.WHTRANSFER.ordinal()] = 15;
            } catch (final NoSuchFieldError unused15) {
            }
        }
    }
    private void setFicheRefType(final GroupItem groupItem, final BaseResult baseResult) {
        MainActivity.sFicheRef = baseResult.getLogicalRef();
        switch (C26201.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[groupItem.getTransferOperationName().ordinal()]) {
            case 1:
            case 2:
                FTypeControlUtils.setMainFType(FType.fatura);
                break;
            case 3:
            case 4:
                FTypeControlUtils.setMainFType(FType.perakendefatura);
                break;
            case 5:
            case 6:
                FTypeControlUtils.setMainFType(FType.irsaliye);
                break;
            case 7:
                FTypeControlUtils.setMainFType(FType.siparis);
                break;
            case 8:
                FTypeControlUtils.setMainFType(FType.birebir);
                break;
            case 9:
                FTypeControlUtils.setMainFType(FType.nakit);
                break;
            case 10:
                FTypeControlUtils.setMainFType(FType.kredikarti);
                break;
            case 11:
                FTypeControlUtils.setMainFType(FType.cek);
                break;
            case 12:
                FTypeControlUtils.setMainFType(FType.senet);
                break;
            case 13:
                FTypeControlUtils.setMainFType(FType.nakitkasa);
                break;
            case 14:
                FTypeControlUtils.setMainFType(FType.talep);
                break;
            case 15:
                FTypeControlUtils.setMainFType(FType.whtransfer);
                break;
            default:
                FTypeControlUtils.setMainFType(FType.all);
                break;
        }
    }
    private record SalesRecyclerSalesFicheGet( WeakReference<TransferSendActivity> mAdapter) implements ResponseListener<Sales> {
            private SalesRecyclerSalesFicheGet(final TransferSendActivity mAdapter) {
                this(new WeakReference<>(mAdapter));
            }
            public void onResponse(  final Sales sales) {
                if (null != this.mAdapter.get()) {
                    mAdapter.get().onSalesGet(sales, "");
                }
            }

        @Override
        public void onResponse(ArrayList<Sales> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override
        public void onResponse(Boolean aBoolean) {

        }

        public void onError(final String str) {
                if (null != this.mAdapter.get()) {
                    mAdapter.get().onSalesGet(null, str);
                }
            }
        }
    public void onSalesGet(final Sales sales, final String str) {
        mProgressDialogBuilder.dismiss();
        if (null != sales) {
            final FicheMode ficheMode = mLastFicheMode;
            if (null != ficheMode) {
                this.openSalesFiche(sales, ficheMode);
                return;
            } else {
                Toast.makeText(this, this.getString(R.string.str_cancel_process), Toast.LENGTH_LONG).show();
                return;
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private void openSalesFiche(final Sales sales, final FicheMode ficheMode) {
        final CustomerOperation customerOperation = new CustomerOperation(sales.getmSalesType(), sales.getFicheType(), ficheMode);
        final Intent intent = new Intent(this, SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, sales.getClRef());
        intent.putExtra("bigdata:synccode", mBaseErp.saveObject(sales));
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
        this.editSelectedFiche(intent);
    }
    private void editSelectedFiche(final Intent intent) {
        this.startActivity(intent);
        this.finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.sFicheRef = -1;
    }
}
