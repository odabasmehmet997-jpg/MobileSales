package com.proje.mobilesales.features.settings.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.features.settings.adapter.BluetoothDeviceListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class BluetoothDeviceListActivity extends Activity {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_DEVICE_ADDRESS = BluetoothDeviceListActivity.class.getName() + ".EXTRA_DEVICE_ADDRESS";
    private static final String EXTRA_DEVICE_NAME = BluetoothDeviceListActivity.class.getName() + ".EXTRA_DEVICE_NAME";
    public static final int REQUEST_OPEN_BLUETOOTH = 999;
    private TextView devices;
    private ListView devicesListView;
    private BluetoothDeviceListAdapter mAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private ProgressDialog mProgressDialog;
    private Button scanButton;
    private final ArrayList<BluetoothDevice> mDeviceList = new ArrayList<>();
    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            BluetoothDeviceListActivity.onItemClickListenerlambda4(this.f0, adapterView, view, i2, j2);
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.STATE_CHANGED", action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 10) {
                    BluetoothDeviceListActivity bluetoothDeviceListActivity = this.this0;
                    String string = bluetoothDeviceListActivity.getString(R.string.str_bluetooth_close);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    bluetoothDeviceListActivity.showToast(string);
                    this.this0.mDeviceList = new ArrayList();
                    BluetoothDeviceListAdapter bluetoothDeviceListAdapter = this.this0.mAdapter;
                    Intrinsics.checkNotNull(bluetoothDeviceListAdapter);
                    bluetoothDeviceListAdapter.setData(this.this0.mDeviceList);
                } else if (intExtra == 12) {
                    BluetoothDeviceListActivity bluetoothDeviceListActivity2 = this.this0;
                    String string2 = bluetoothDeviceListActivity2.getString(R.string.str_bluetooth_open);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    bluetoothDeviceListActivity2.showToast(string2);
                }
                ProgressDialog progressDialog = this.this0.mProgressDialog;
                Intrinsics.checkNotNull(progressDialog);
                if (progressDialog.isShowing()) {
                    ProgressDialog progressDialog2 = this.this0.mProgressDialog;
                    Intrinsics.checkNotNull(progressDialog2);
                    progressDialog2.dismiss();
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_STARTED", action)) {
                this.this0.mDeviceList = new ArrayList();
                ProgressDialog progressDialog3 = this.this0.mProgressDialog;
                Intrinsics.checkNotNull(progressDialog3);
                progressDialog3.show();
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                ProgressDialog progressDialog4 = this.this0.mProgressDialog;
                Intrinsics.checkNotNull(progressDialog4);
                if (progressDialog4.isShowing()) {
                    ProgressDialog progressDialog5 = this.this0.mProgressDialog;
                    Intrinsics.checkNotNull(progressDialog5);
                    progressDialog5.dismiss();
                }
                BluetoothDeviceListAdapter bluetoothDeviceListAdapter2 = this.this0.mAdapter;
                Intrinsics.checkNotNull(bluetoothDeviceListAdapter2);
                bluetoothDeviceListAdapter2.setData(this.this0.mDeviceList);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                ProgressDialog progressDialog6 = this.this0.mProgressDialog;
                Intrinsics.checkNotNull(progressDialog6);
                if (!progressDialog6.isShowing()) {
                    ProgressDialog progressDialog7 = this.this0.mProgressDialog;
                    Intrinsics.checkNotNull(progressDialog7);
                    progressDialog7.show();
                }
                BluetoothDevice bluetoothDevice = R intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice != null) {
                    this.this0.mDeviceList.add(bluetoothDevice);
                }
                Intrinsics.checkNotNull(bluetoothDevice);
                if (bluetoothDevice.getName() != null) {
                    BluetoothDeviceListActivity bluetoothDeviceListActivity3 = this.this0;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string3 = bluetoothDeviceListActivity3.getString(R.string.str_bluetooth_device_found);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String str = String.format(string3, Arrays.copyOf(new Object[]{bluetoothDevice.getName()}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    bluetoothDeviceListActivity3.showToast(str);
                }
            }
        }
    };
    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (Intrinsics.areEqual("android.bluetooth.device.action.BOND_STATE_CHANGED", intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
                if (intExtra == 12 && intExtra2 == 11) {
                    BluetoothDeviceListActivity bluetoothDeviceListActivity = this.this0;
                    String string = bluetoothDeviceListActivity.getString(R.string.str_bluetooth_device_paired);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    bluetoothDeviceListActivity.showToast(string);
                } else if (intExtra == 10 && intExtra2 == 12) {
                    BluetoothDeviceListActivity bluetoothDeviceListActivity2 = this.this0;
                    String string2 = bluetoothDeviceListActivity2.getString(R.string.str_bluetooth_device_unpaired);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    bluetoothDeviceListActivity2.showToast(string2);
                }
                BluetoothDeviceListAdapter bluetoothDeviceListAdapter = this.this0.mAdapter;
                Intrinsics.checkNotNull(bluetoothDeviceListAdapter);
                bluetoothDeviceListAdapter.notifyDataSetChanged();
            }
        }
    };
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(5);
        setContentView(R.layout.activity_bluetooth_device_list);
        setResult(0);
        openBluetooth();
        View viewFindViewById = findViewById(R.id.btn_scan);
        Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type android.widget.Button");
        this.scanButton = (Button) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.txt_title_device);
        Intrinsics.checkNotNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.devices = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.lst_device);
        Intrinsics.checkNotNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.ListView");
        this.devicesListView = (ListView) viewFindViewById3;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Toast.makeText(this, getString(R.string.exp_39_bluetooth_not_found), Toast.LENGTH_LONG).show();
            finish();
        }
        BluetoothDeviceListAdapter bluetoothDeviceListAdapter = new BluetoothDeviceListAdapter(this);
        this.mAdapter = bluetoothDeviceListAdapter;
        Intrinsics.checkNotNull(bluetoothDeviceListAdapter);
        bluetoothDeviceListAdapter.setListener(new BluetoothDeviceListAdapter.OnButtonClickListener() {
            public void onButtonClick(int i2) {
                Object obj = BluetoothDeviceListActivity.this.mDeviceList.get(i2);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                BluetoothDevice bluetoothDevice = (BluetoothDevice) obj;
                if (bluetoothDevice.getBondState() == 12) {
                    BluetoothDeviceListActivity.this.unPairDevice(bluetoothDevice);
                    return;
                }
                BluetoothDeviceListActivity bluetoothDeviceListActivity = BluetoothDeviceListActivity.this;
                String string = bluetoothDeviceListActivity.getString(R.string.str_please_wait_pairing);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                bluetoothDeviceListActivity.showToast(string);
                BluetoothDeviceListActivity.this.pairDevice(bluetoothDevice);
            }
        });
        ListView listView = this.devicesListView;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(this.mAdapter);
        ListView listView2 = this.devicesListView;
        Intrinsics.checkNotNull(listView2);
        listView2.setOnItemClickListener(this.onItemClickListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        registerReceiver(this.mReceiver, intentFilter);
        registerReceiver(this.mPairReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        initProgressDialog();
        clickScanButton();
    }
    public   void openBluetooth() {
        if (Connectivity.isBluetoothEnable()) {
            return;
        }
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1000);
    }

    public  void closeBluetooth() {
        if (Connectivity.isBluetoothEnable()) {
            Connectivity.closeBluetooth();
        }
    }
    public   void onItemClickList(int i2) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        Intrinsics.checkNotNull(bluetoothAdapter);
        if (bluetoothAdapter.isDiscovering()) {
            BluetoothAdapter bluetoothAdapter2 = this.mBluetoothAdapter;
            Intrinsics.checkNotNull(bluetoothAdapter2);
            bluetoothAdapter2.cancelDiscovery();
        }
        Log.d(MobileSales.TAG, "onItemClickList: " + i2);
        BluetoothDeviceListAdapter bluetoothDeviceListAdapter = this.mAdapter;
        Intrinsics.checkNotNull(bluetoothDeviceListAdapter);
        BluetoothDevice item = bluetoothDeviceListAdapter.getItem(i2);
        if (item != null) {
            if (item.getBondState() != 12) {
                String string = getString(R.string.str_please_select_device_pair);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                showToast(string);
            } else {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DEVICE_ADDRESS, item.getAddress());
                intent.putExtra(EXTRA_DEVICE_NAME, item.getName());
                setResult(-1, intent);
                finish();
            }
        }
    }

    private void initProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mProgressDialog = progressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait_search_device));
        ProgressDialog progressDialog2 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setCancelable(true);
        ProgressDialog progressDialog3 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog3);
        progressDialog3.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                BluetoothDeviceListActivity.initProgressDialoglambda0(this.f0, dialogInterface, i2);
            }
        });
    }
    public static void initProgressDialoglambda0(BluetoothDeviceListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
        this0.stopBluetoothAdapter();
    }
    private void clickScanButton() {
        if (PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", getString(R.string.str_location_permission_for_nearby_devices)) || PermissionUtils.checkPermission(this, "android.permission.ACCESS_COARSE_LOCATION", getString(R.string.str_location_permission_for_nearby_devices))) {
            Button button = this.scanButton;
            Intrinsics.checkNotNull(button);
            button.setOnClickListener(new View.OnClickListener() {
                public  void onClick(View view) {
                    BluetoothDeviceListActivity.clickScanButtonlambda1(this.f0, view);
                }
            });
        }
    }
    public static  void clickScanButtonlambda1(BluetoothDeviceListActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.doDiscovery();
    }
    public void doDiscovery() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        Intrinsics.checkNotNull(bluetoothAdapter);
        if (bluetoothAdapter.isEnabled()) {
            stopBluetoothAdapter();
            BluetoothAdapter bluetoothAdapter2 = this.mBluetoothAdapter;
            Intrinsics.checkNotNull(bluetoothAdapter2);
            if (bluetoothAdapter2.startDiscovery()) {
                Log.d(MobileSales.TAG, "doDiscovery: started");
                return;
            } else {
                Log.d(MobileSales.TAG, "doDiscovery: start failed");
                return;
            }
        }
        openBluetooth();
    }
    protected void onActivityResult(int i2, int i3, Intent intent) {
        Unit unit;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 999 && i3 == -1) {
            if (intent != null) {
                try {
                    String string = getString(R.string.str_bluetooth_open);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    showToast(string);
                    unit = Unit.INSTANCE;
                } catch (Exception e2) {
                    showToast("Error: " + e2.getMessage());
                    return;
                }
            } else {
                unit = null;
            }
            if (unit != null) {
            } else {
                throw new NullPointerException("Data is null");
            }
        }
    }
    protected void onDestroy() {
        stopBluetoothAdapter();
        unregisterReceiver(this.mReceiver);
        unregisterReceiver(this.mPairReceiver);
        super.onDestroy();
    }
    private void stopBluetoothAdapter() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            Intrinsics.checkNotNull(bluetoothAdapter);
            if (bluetoothAdapter.isDiscovering()) {
                BluetoothAdapter bluetoothAdapter2 = this.mBluetoothAdapter;
                Intrinsics.checkNotNull(bluetoothAdapter2);
                bluetoothAdapter2.cancelDiscovery();
            }
        }
    }
    public void pairDevice(BluetoothDevice bluetoothDevice) {
        try {
            bluetoothDevice.getClass().getMethod("createBond", null).invoke(bluetoothDevice, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void unPairDevice(BluetoothDevice bluetoothDevice) {
        try {
            bluetoothDevice.getClass().getMethod("removeBond", null).invoke(bluetoothDevice, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }
    public static void onItemClickListenerlambda4(BluetoothDeviceListActivity this0, AdapterView adapterView, View view, int i2, long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.onItemClickList(i2);
    }
    public void onRequestPermissionsResult(int i2, String[] permissions2, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i2 == 1072) {
            if (!(grantResults.length == 0) && grantResults[0] == 0) {
                doDiscovery();
            } else {
                Toast.makeText(this, getString(R.string.str_permissions_denied), 1).show();
            }
        }
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public  String getEXTRA_DEVICE_ADDRESS() {
            return BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS;
        }

        public  String getEXTRA_DEVICE_NAME() {
            return BluetoothDeviceListActivity.EXTRA_DEVICE_NAME;
        }
    }
}
