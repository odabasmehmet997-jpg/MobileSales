package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.enums.DeleteDataParamEnum;
import com.proje.mobilesales.core.enums.TransferActivityMenu;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.event.TransferEvent;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.RecyclerViewClickListener;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.view.RecyclerViewTouchListener;
import com.proje.mobilesales.features.adapter.ListMenuAdapter;
import com.proje.mobilesales.features.model.Response;
import com.proje.mobilesales.features.model.TransferGetItem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.proje.mobilesales.features.sales.model.Sales;
import org.greenrobot.eventbus.EventBus;

public class TransferActivity extends BaseErpActivity {
    public static ArrayList<String> arrayList;
    private String deleteDocTypesParamValue;
    AlertDialogBuilder mAlertDialogBuilder;
    AlertDialogBuilder mDeleteDataAlertDialogBuilder;

    AlertDialogBuilder mDeleteDataConfirmDialogBuilder;
    private RecyclerView mRecyclerView;

    SharedPreferencesHelper mSharedPreferencesHelper;

    AlertDialogBuilder mUnsentCabinAlertDialogBuilder;
    private List<TransferActivityMenu> transferActivityMenuList;
    boolean firstUse;
    private boolean isCustomerSend;
    private String selectedDeleteDocsParamValue = "";
    public static void lambdatransferEvent0(final DialogInterface dialogInterface, final int i2) {
    }
    public static void lambdatransferEvent1(final DialogInterface dialogInterface) {
    }
    public static void lambdatransferEvent2(final DialogInterface dialogInterface, final int i2) {
    }     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.DATA_TRANSFER;
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_menu_list);
        this.getActivityComponent().inject(this);
        this.setToolbar();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initRecycleView();
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_transfer_get, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initRecycleView() {
        mRecyclerView = this.findViewById(R.id.rcwMenuList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        final ArrayList arrayList2 = new ArrayList();
        transferActivityMenuList = new ArrayList();
        final TransferActivityMenu transferActivityMenu = TransferActivityMenu.TRANSFER_SEND;
        arrayList2.add(this.getString(transferActivityMenu.stringMenuId));
        transferActivityMenuList.add(transferActivityMenu);
        final TransferActivityMenu transferActivityMenu2 = TransferActivityMenu.TRANSFER_GET;
        arrayList2.add(this.getString(transferActivityMenu2.stringMenuId));
        transferActivityMenuList.add(transferActivityMenu2);
        final String transferDataDelete = baseErp.getTransferDataDelete();
        deleteDocTypesParamValue = transferDataDelete;
        if (null != transferDataDelete && !transferDataDelete.isEmpty()) {
            final TransferActivityMenu transferActivityMenu3 = TransferActivityMenu.TRANSFER_DELETE;
            arrayList2.add(this.getString(transferActivityMenu3.stringMenuId));
            transferActivityMenuList.add(transferActivityMenu3);
        }
        mRecyclerView.setAdapter(new ListMenuAdapter(arrayList2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final RecyclerView recyclerView = mRecyclerView;
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, new C26171(), 4000L));
    }
    class C26171 implements RecyclerViewClickListener {
        public void onClickLong(final View view, final int i2) {
        }

        C26171() {
        }

        @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET, Manifest.permission.WAKE_LOCK})
        public void onClick(final View view, final int i2) {
            final TransferActivityMenu transferActivityMenu = transferActivityMenuList.get(i2);
            if (!ContextUtils.checkConnectionWithoutMessage()) {
                mAlertDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.activity.TransferActivity1ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialogInterface, final int i3) {
                        dialogInterface.dismiss();
                    }
                }).show();
                return;
            }
            if (transferActivityMenu == TransferActivityMenu.TRANSFER_SEND) {
                TransferActivity.this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                TransferActivity.this.baseErp.checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(TransferActivity.this, transferActivityMenu));
            } else if (transferActivityMenu == TransferActivityMenu.TRANSFER_GET) {
                baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.TRANSFER_GET_DATA);
                TransferActivity.this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                TransferActivity.this.baseErp.checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferGet, new CheckWorkTimeListener(TransferActivity.this, transferActivityMenu));
            } else if (transferActivityMenu == TransferActivityMenu.TRANSFER_DELETE) {
                startTransferDelete();
            }
        }
    }

    private record CheckWorkTimeListener(WeakReference<TransferActivity> mActivity,
                                         TransferActivityMenu mTransferActivityMenu) implements ResponseListener<String> {
            private CheckWorkTimeListener(final TransferActivity mActivity, final TransferActivityMenu mTransferActivityMenu) {
                this(new WeakReference<>(mActivity), mTransferActivityMenu);
            }

        @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
        public void onResponse(final PrintSlipModel str) {
                if (null == this.mActivity.get() || mActivity.get().isActivityDestroyed()) {
                    return;
                }
                mActivity.get().mProgressDialogBuilder.dismiss();
                if (!TextUtils.isEmpty((CharSequence) str)) {
                    Toast.makeText(mActivity.get(), str.size(), Toast.LENGTH_SHORT).show();
                    return;
                }
                final int i2 = C26193.SwitchMapcomprojemobilesalescoreenumsTransferActivityMenu[mTransferActivityMenu.ordinal()];
                if (1 == i2) {
                    mActivity.get().isCustomerSend = false;
                    if (mActivity.get().baseErp.isOfflineCustomersExist()) {
                        mActivity.get().sendCustomers();
                        return;
                    } else {
                        mActivity.get().sendAllData();
                        return;
                    }
                }
                if (2 != i2) {
                    return;
                }
                if (Preferences.getTransferGetOptionsType(ContextUtils.getmContext()) && mActivity.get().baseErp.isUnsentCabinExists()) {
                    mActivity.get().mUnsentCabinAlertDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.exp_56_unsent_cabin_operation_before_get_data)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int i3) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                } else {
                    mActivity.get().startTransfer();
                }
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

        @Override
        public void onResponse(String obj) {

        }

        @Override
        public void onResponse(ArrayList<String> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        public void onError(final String str) {
                if (null == this.mActivity.get() || mActivity.get().isActivityDestroyed()) {
                    return;
                }
                mActivity.get().mProgressDialogBuilder.dismiss();
            }
        }
    enum C26193 {
        ;
        static final   int[] SwitchMapcomprojemobilesalescoreenumsTransferActivityMenu;

        static {
            final int[] iArr = new int[TransferActivityMenu.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferActivityMenu = iArr;
            try {
                iArr[TransferActivityMenu.TRANSFER_SEND.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C26193.SwitchMapcomprojemobilesalescoreenumsTransferActivityMenu[TransferActivityMenu.TRANSFER_GET.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
        }
    }
    public void sendAllData() {
        baseErp.getSendCreator().getAllSend(new TransferResponseListener(this));
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    void startTransfer() {
        if (ContextUtils.checkInternetConnection()) {
            firstUse = true;
            if (MainActivity.getDataFlag) {
                baseErp.getAllDataLogo(Preferences.isDemo(this), false);
            } else {
                Toast.makeText(this, this.getString(R.string.str_receiving_is_running_on_service), Toast.LENGTH_LONG).show();
            }
        }
    }
    public void transferEvent(final TransferEvent transferEvent) {
        if (transferEvent.isSuccess()) {
            mAlertDialogBuilder.setMessage(this.getString(R.string.str_transfer_get_end)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdatransferEvent0(dialogInterface, i2);
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(final DialogInterface dialogInterface) {
                    lambdatransferEvent1(dialogInterface);
                }
            }).setCancelable(false).create();
            mAlertDialogBuilder.setCancelOnTouchOutside(false).show();
            return;
        }
        String str = transferEvent.getErrorMessage();
        if (null != transferEvent.getTransferGet()) {
            for (final TransferGetItem transferGetItem : transferEvent.getTransferGet().getTransferGetItems()) {
                if (!transferGetItem.getTransferError().isEmpty()) {
                    str = str + String.format("\n%s : %s \n", this.getString(transferGetItem.getTransferGetType().resId), transferGetItem.getTransferError());
                }
            }
        }
        mAlertDialogBuilder.setMessage(str).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdatransferEvent2(dialogInterface, i2);
            }
        }).create().show();
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (16908332 == itemId) {
            this.finish();
            return true;
        }
        if (R.id.menu_settings == itemId) {
            this.startActivity(new Intent(this, TransferGetSettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
     public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
     public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    public void baseResultEvent(final BaseResult baseResult) {
        mProgressDialogBuilder.dismiss();
        if (baseResult.isSuccess()) {
            this.startActivity(transferActivityMenuList.get(0).destActivity);
        } else {
            Toast.makeText(this, baseResult.getErrorString(), Toast.LENGTH_LONG).show();
        }
    }
    public void responseEvent(final Response response) {
        mProgressDialogBuilder.dismiss();
        if (response.isSuccess()) {
            this.startActivity(transferActivityMenuList.get(0).destActivity);
        } else {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private record TransferResponseListener(
            WeakReference<TransferActivity> mTransferActivity) implements ResponseListener<Boolean> {
            private TransferResponseListener(final TransferActivity mTransferActivity) {
                this(new WeakReference<>(mTransferActivity));
            }
            public void onResponse(  final Boolean bool) {
                if (null == this.mTransferActivity.get() || mTransferActivity.get().isActivityDestroyed()) {
                    return;
                }
                mTransferActivity.get().onCreateTransferDataResult(bool, "");
            }

        @Override
        public void onResponse(ArrayList<Boolean> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        @Override
        public void onResponse(Sales sales) {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(final String str) {
                if (null == this.mTransferActivity.get() || mTransferActivity.get().isActivityDestroyed()) {
                    return;
                }
                Log.d(MobileSales.TAG, "onError: " + str);
                mTransferActivity.get().onCreateTransferDataResult(Boolean.FALSE, str);
            }
        }
    public void onCreateTransferDataResult(final Boolean bool, final String str) {
        mProgressDialogBuilder.dismiss();
        if (bool.booleanValue()) {
            this.startActivity(transferActivityMenuList.get(0).destActivity);
        } else if (!isCustomerSend) {
            Toast.makeText(this, R.string.str_no_information_transferred, Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    public void sendCustomers() {
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_customer_transferring)).setCancelable(false).show();
        isCustomerSend = true;
        baseErp.addOfflineCustomer(new ICustomerSendCompleted() {
            public void onCustomerSendCompleted(final ResponseEvent responseEvent) {
                if (null != responseEvent.getErrorMessage() && !responseEvent.getErrorMessage().isEmpty()) {
                    Toast.makeText(TransferActivity.this, responseEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
                if (responseEvent.isSuccess()) {
                    final TransferActivity transferActivity = TransferActivity.this;
                    Toast.makeText(transferActivity, transferActivity.getString(R.string.str_customers_transferred), Toast.LENGTH_LONG).show();
                }
                TransferActivity.this.mProgressDialogBuilder.dismiss();
                sendAllData();
            }
        });
    }

    public void startTransferDelete() {
        String[] split = baseErp.getTransferDataDelete().split(",");
        final String[] deleteDocumentTypes = DeleteDataParamEnum.getDeleteDocumentTypes(split);
        boolean[] zArr = new boolean[split.length];
        mDeleteDataAlertDialogBuilder.setTitle(R.string.str_select_document_type).setMultiChoiceItems(deleteDocumentTypes, zArr, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2, final boolean z) {
                lambdastartTransferDelete3(zArr, dialogInterface, i2, z);
            }
        }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdastartTransferDelete6(split, zArr, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }
    public static void lambdastartTransferDelete3(final boolean[] zArr, final DialogInterface dialogInterface, final int i2, final boolean z) {
        zArr[i2] = z;
    }
    public void lambdastartTransferDelete6(final String[] strArr, final boolean[] zArr, final DialogInterface dialogInterface, final int i2) {
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (zArr[i3]) {
                selectedDeleteDocsParamValue += strArr[i3];
                selectedDeleteDocsParamValue += ",";
            }
        }
        if (selectedDeleteDocsParamValue.isEmpty()) {
            return;
        }
        mDeleteDataConfirmDialogBuilder.setMessage(this.getString(R.string.str_delete_data_confirm)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface2, final int i4) {
                lambdastartTransferDelete4(dialogInterface2, i4);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface2, final int i4) {
                lambdastartTransferDelete5(dialogInterface2, i4);
            }
        }).show();
    }
    public void lambdastartTransferDelete4(final DialogInterface dialogInterface, final int i2) {
        baseErp.getLogoSqlBriteDatabase().executeMultipleStatements(DeleteDataParamEnum.getDeleteQueries(selectedDeleteDocsParamValue.split(",")));
        Toast.makeText(this, this.getString(R.string.str_delete_data_end), Toast.LENGTH_LONG).show();
        selectedDeleteDocsParamValue = "";
    }
    public void lambdastartTransferDelete5(final DialogInterface dialogInterface, final int i2) {
        selectedDeleteDocsParamValue = "";
    }
}
