package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.NotifyType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.receiver.ConnectivityReceiver;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.adapter.TransferGetAdapter;
import com.proje.mobilesales.features.model.TransferGet;
import com.proje.mobilesales.features.model.TransferGetItem;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;

public class TransferGetActivity extends BaseErpActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    AlertDialogBuilder mAlertDialogBuilder;
    SharedPreferencesHelper mSharedPreferencesHelper;
    RecyclerView rcwGet;
    TransferGet transferGet;
    TransferGetAdapter transferGetAdapter;
    boolean selectAll = true;
    boolean firstUse;
    public static  void lambdaresponseEvent1(final DialogInterface dialogInterface, final int i2) {
    }
    public static  void lambdaresponseEvent2(final DialogInterface dialogInterface) {
    }
    public static  void lambdaresponseEvent3(final DialogInterface dialogInterface, final int i2) {
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_transfer_get);
        this.setToolbar();
        transferGet = mSharedPreferencesHelper.getTransferGet();
        this.initTransferSelect();
        this.initRecyclerView();
        this.findViewById(R.id.fabStartTransfer).setOnClickListener(view -> lambdaonCreate0(view));
    }

    
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public  void lambdaonCreate0(final View view) {
        this.fabStartTransfer();
    }
    public void update(final NotifyType notifyType) {
        super.update(notifyType);
        if (notifyType == NotifyType._UPDATE_ADAPTER) {
            transferGetAdapter.notifyDataSetChanged();
        }
    }

    void initTransferSelect() {
        for (TransferGetItem transferGetItem : transferGet.getTransferGetItems()) {
            if (!transferGetItem.isSelect()) {
                selectAll = false;
                return;
            }
        }
    }

    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (16908332 == itemId) {
            this.finish();
            return true;
        }
        if (R.id.menu_select_all != itemId) {
            if (R.id.menu_settings == itemId) {
                this.startActivity(new Intent(this, TransferGetSettingActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(menuItem);
        }
        final boolean z = !selectAll;
        selectAll = z;
        this.selectAllTransferItem(z);
        transferGetAdapter.notifyDataSetChanged();
        return true;
    }

    void initRecyclerView() {
        rcwGet = this.findViewById(R.id.rcwTransferGet);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rcwGet.setLayoutManager(linearLayoutManager);
        rcwGet.setHasFixedSize(true);
        if (this.transferGet.getTransferGetItems().isEmpty()) {
            this.initGetList();
        }
        final TransferGetAdapter transferGetAdapter = new TransferGetAdapter(transferGet.getTransferGetItems());
        this.transferGetAdapter = transferGetAdapter;
        rcwGet.setAdapter(transferGetAdapter);
        rcwGet.setItemAnimator(new DefaultItemAnimator());
    }

    private void initGetList() {
        firstUse = true;
        transferGet.getTransferGetItems().addAll(baseErp.getTransferList());
        this.selectAllTransferItem(true);
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    void fabStartTransfer() {
        final TransferGet transferGet = new TransferGet();
        if (null != this.transferGet.getTransferGetItems()) {
            for (final TransferGetItem transferGetItem : this.transferGet.getTransferGetItems()) {
                transferGetItem.setTransferError("");
                if (transferGetItem.isSelect()) {
                    transferGet.getTransferGetItems().add(transferGetItem);
                }
            }
            if (!transferGet.getTransferGetItems().isEmpty()) {
                if (ContextUtils.checkInternetConnection()) {
                    this.startTransfer(transferGet);
                    return;
                }
                return;
            }
            Toast.makeText(this, this.getString(R.string.exp_8_transfer_select_size), Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_transfer_get, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void startTransfer(final TransferGet transferGet) {
        baseErp.getAllDataLogo(false, false);
    }

    void selectAllTransferItem(final boolean z) {
        for (TransferGetItem transferGetItem : transferGet.getTransferGetItems()) {
            transferGetItem.setSelect(z);
        }
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

    public void responseEvent(final ResponseEvent responseEvent) {
        if (responseEvent.isSuccess()) {
            mAlertDialogBuilder.setMessage(this.getString(R.string.str_transfer_get_end)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.activity.TransferGetActivityExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    lambdaresponseEvent1(dialogInterface, i2);
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(final DialogInterface dialogInterface) {
                    lambdaresponseEvent2(dialogInterface);
                }
            }).setCancelable(false).create();
            mAlertDialogBuilder.setCancelOnTouchOutside(false).show();
            return;
        }
        StringBuilder str = new StringBuilder(responseEvent.getErrorMessage());
        for (final TransferGetItem transferGetItem : transferGet.getTransferGetItems()) {
            if (!transferGetItem.getTransferError().isEmpty()) {
                str.append(String.format("\n%s : %s \n", this.getString(transferGetItem.getTransferGetType().resId), transferGetItem.getTransferError()));
            }
        }
        mAlertDialogBuilder.setMessage(str.toString()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdaresponseEvent3(dialogInterface, i2);
            }
        }).create().show();
    }
    public void onBackPressed() {
        this.saveTransferOptions();
        super.onBackPressed();
    }
     public void onDestroy() {
        this.saveTransferOptions();
        super.onDestroy();
    }

    void saveTransferOptions() {
        mSharedPreferencesHelper.saveTransferGet(transferGet);
    }
    public void onNetworkConnectionChanged(final boolean z) {
        mAlertDialogBuilder.setTitle(R.string.exp_23_internet_connection_not_found).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.activity.TransferGetActivityExternalSyntheticLambda4

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdaonNetworkConnectionChanged4(dialogInterface, i2);
            }
        }).show();
    }

    
    public static  void lambdaonNetworkConnectionChanged4(final DialogInterface dialogInterface, final int i2) {
        if (-1 == i2) {
            dialogInterface.dismiss();
        }
        dialogInterface.dismiss();
    }
}
