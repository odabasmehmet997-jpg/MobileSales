package com.proje.mobilesales.features.settings.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class DeviceListActivity extends Activity {

    private static final boolean f1278D = true;
    private static final String TAG = "DeviceListActivity";
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    public static final Companion Companion = new Companion(null);
    private static String EXTRA_DEVICE_ADDRESS = "device_address";

    private final AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            DeviceListActivity.mDeviceClickListenerlambda1(DeviceListActivity.this, adapterView, view, i2, j2);
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            ArrayAdapter arrayAdapter;
            ArrayAdapter arrayAdapter2;
            ArrayAdapter arrayAdapter3;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                Intrinsics.checkNotNull(bluetoothDevice);
                if (12 != bluetoothDevice.getBondState()) {
                    arrayAdapter3 = DeviceListActivity.this.mNewDevicesArrayAdapter;
                    Intrinsics.checkNotNull(arrayAdapter3);
                    arrayAdapter3.add(StringsKt.trimIndent("\n    " + bluetoothDevice.getName() + "\n    " + bluetoothDevice.getAddress() + "\n    "));
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                DeviceListActivity.this.setProgressBarIndeterminateVisibility(false);
                arrayAdapter = DeviceListActivity.this.mNewDevicesArrayAdapter;
                Intrinsics.checkNotNull(arrayAdapter);
                if (0 == arrayAdapter.getCount()) {
                    arrayAdapter2 = DeviceListActivity.this.mNewDevicesArrayAdapter;
                    Intrinsics.checkNotNull(arrayAdapter2);
                    arrayAdapter2.add(ContextUtils.getStringResource(R.string.str_device_not_found));
                }
            }
        }
    };

    
    @SuppressLint("MissingPermission")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(5);
        setContentView(R.layout.device_list);
        setResult(0);
        View findViewById = findViewById(R.id.button_scan);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.Button");
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.DeviceListActivityExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceListActivity.onCreatelambda0(DeviceListActivity.this, view);
            }
        });
        this.mPairedDevicesArrayAdapter = new ArrayAdapter<>(this, R.layout.device_name);
        this.mNewDevicesArrayAdapter = new ArrayAdapter<>(this, R.layout.device_name);
        View findViewById2 = findViewById(R.id.paired_devices);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ListView");
        ListView listView = (ListView) findViewById2;
        listView.setAdapter(this.mPairedDevicesArrayAdapter);
        listView.setOnItemClickListener(this.mDeviceClickListener);
        View findViewById3 = findViewById(R.id.new_devices);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ListView");
        ListView listView2 = (ListView) findViewById3;
        listView2.setAdapter(this.mNewDevicesArrayAdapter);
        listView2.setOnItemClickListener(this.mDeviceClickListener);
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter(...)");
        this.mBtAdapter = defaultAdapter;
        if (null == defaultAdapter) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
            defaultAdapter = null;
        }
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        Intrinsics.checkNotNull(bondedDevices);
        if (!bondedDevices.isEmpty()) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                ArrayAdapter<String> arrayAdapter = this.mPairedDevicesArrayAdapter;
                Intrinsics.checkNotNull(arrayAdapter);
                arrayAdapter.add(StringsKt.trimIndent("\n    " + bluetoothDevice.getName() + "\n    " + bluetoothDevice.getAddress() + "\n    "));
            }
            return;
        }
        ArrayAdapter<String> arrayAdapter2 = this.mPairedDevicesArrayAdapter;
        Intrinsics.checkNotNull(arrayAdapter2);
        arrayAdapter2.add(ContextUtils.getStringResource(R.string.str_no_paired_device_found));
    }

    
    public static void onCreatelambda0(DeviceListActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.doDiscovery();
        view.setVisibility(View.GONE);
    }

    
    @SuppressLint("MissingPermission")
    protected void onDestroy() {
        super.onDestroy();
        if (null == mBtAdapter) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
        }
        BluetoothAdapter bluetoothAdapter = this.mBtAdapter;
        if (null == bluetoothAdapter) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
            bluetoothAdapter = null;
        }
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(this.mReceiver);
    }

    @SuppressLint("MissingPermission")
    private void doDiscovery() {
        Log.d(TAG, "doDiscovery()");
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.str_no_paired_device_found);
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
        BluetoothAdapter bluetoothAdapter = this.mBtAdapter;
        BluetoothAdapter bluetoothAdapter2 = null;
        if (null == bluetoothAdapter) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
            bluetoothAdapter = null;
        }
        if (bluetoothAdapter.isDiscovering()) {
            BluetoothAdapter bluetoothAdapter3 = this.mBtAdapter;
            if (null == bluetoothAdapter3) {
                Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
                bluetoothAdapter3 = null;
            }
            bluetoothAdapter3.cancelDiscovery();
        }
        BluetoothAdapter bluetoothAdapter4 = this.mBtAdapter;
        if (null == bluetoothAdapter4) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
        } else {
            bluetoothAdapter2 = bluetoothAdapter4;
        }
        bluetoothAdapter2.startDiscovery();
    }

    
    public static void mDeviceClickListenerlambda1(DeviceListActivity this0, AdapterView adapterView, View view, int i2, long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BluetoothAdapter bluetoothAdapter = this0.mBtAdapter;
        if (null == bluetoothAdapter) {
            Intrinsics.throwUninitializedPropertyAccessException("mBtAdapter");
            bluetoothAdapter = null;
        }
        bluetoothAdapter.cancelDiscovery();
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
        String obj = ((TextView) view).getText().toString();
        if (Intrinsics.areEqual(obj, ContextUtils.getStringResource(R.string.str_no_paired_device_found)) || Intrinsics.areEqual(obj, ContextUtils.getStringResource(R.string.str_device_not_found))) {
            return;
        }
        String substring = obj.substring(obj.length() - 17);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DEVICE_ADDRESS, substring);
        this0.setResult(-1, intent);
        this0.finish();
    }

    /* compiled from: DeviceListActivity.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_DEVICE_ADDRESS() {
            return DeviceListActivity.EXTRA_DEVICE_ADDRESS;
        }

        public void setEXTRA_DEVICE_ADDRESS(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            DeviceListActivity.EXTRA_DEVICE_ADDRESS = str;
        }
    }
}
