package com.proje.mobilesales.core.utils;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.service.BluetoothService;

public class BluetoothUtil {
    public static final String DEVICE_NAME = "device_name";
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_WRITE = 3;
    public static final int REQUEST_CONNECT_DEVICE_1 = 1;
    public static final int REQUEST_CONNECT_DEVICE_2 = 2;
    public static final int REQUEST_ENABLE_BT = 2;
    private static final String TAG = "BluetoothUtil";
    public static final String TOAST = "toast";
    public static BluetoothAdapter mBluetoothAdapter1 = null;
    public static BluetoothAdapter mBluetoothAdapter2 = null;
    public static BluetoothStateChange mBluetoothStateChange = null;
    public static BluetoothService mChatService1 = null;
    public static BluetoothService mChatService2 = null;
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 == 4) {
                    BluetoothUtil.printerName1 = message.getData().getString(BluetoothUtil.DEVICE_NAME);
                    return;
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    Toast.makeText(ContextUtils.getmContext(), message.getData().getString(BluetoothUtil.TOAST), Toast.LENGTH_LONG).show();
                    return;
                }
            }
            int i3 = message.arg1;
            if (i3 == 0) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(0);
                return;
            }
            if (i3 == 1) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(1);
                return;
            }
            if (i3 == 2) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_connecting_device), Toast.LENGTH_SHORT).show();
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(2);
            } else if (i3 == 3) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(3);
            } else {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(-1);
            }
        }
    };
    public static Handler mHandler2 = new Handler() {
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 == 4) {
                    BluetoothUtil.printerName2 = message.getData().getString(BluetoothUtil.DEVICE_NAME);
                    return;
                }
                if (i2 != 5) {
                    return;
                }
                Toast.makeText(ContextUtils.getmContext(), message.getData().getString(BluetoothUtil.TOAST), Toast.LENGTH_SHORT).show();
                return;
            }
            int i3 = message.arg1;
            if (i3 == 0) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(0);
                return;
            }
            if (i3 == 1) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(1);
                return;
            }
            if (i3 == 2) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_connecting_device), Toast.LENGTH_SHORT).show();
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(2);
            } else if (i3 == 3) {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(3);
            } else {
                BluetoothUtil.mBluetoothStateChange.onBluetoothStateChange(-1);
            }
        }
    };
    public static String printerAddress1 = "";
    public static String printerAddress2 = "";
    public static String printerName1 = "";
    public static String printerName2 = "";
    public interface BluetoothStateChange {
        void onBluetoothStateChange(int i2);
    }
    public static int getPrinterTypeInt(FType fType) {
        return 1;
    }
    public static void setPrinterType(FType fType, boolean z) {
    }
    public static void setmBluetoothStateChange(BluetoothStateChange bluetoothStateChange) {
        mBluetoothStateChange = bluetoothStateChange;
    }
    public static void createBluetoothInstance(boolean z) {
        if (z) {
            if (mChatService1 != null) {
                return;
            }
            mChatService1 = new BluetoothService(ContextUtils.getmContext(), mHandler);
        } else {
            if (mChatService2 != null) {
                return;
            }
            mChatService2 = new BluetoothService(ContextUtils.getmContext(), mHandler2);
        }
    }
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public static boolean openBluetooth(boolean z) {
        try {
            setBluetoothAdapter(z, BluetoothAdapter.getDefaultAdapter());
            if (getBluetoothAdapter(z).isEnabled()) {
                return false;
            }
            getBluetoothAdapter(z).enable();
            return getBluetoothAdapter(z).isEnabled();
        } catch (Exception e2) {
            Log.e(TAG, "openBluetooth: ", e2);
            return false;
        }
    }
    public static void reOpenBluetooth(boolean z) {
        try {
            if (getBluetoothService(z) == null || getBluetoothService(z).getState() != 0) {
                return;
            }
            getBluetoothService(z).start();
        } catch (Exception e2) {
            Log.e(TAG, "closeBluetooth: ", e2);
        }
    }
    public static void closeBluetooth(boolean z) {
        try {
            if (getBluetoothAdapter(z) != null) {
                getBluetoothAdapter(z).isEnabled();
            }
            if (getBluetoothService(z) != null) {
                getBluetoothService(z).stop();
            }
            setBluetoothAdapter(z, null);
            setBlueToothService(z, null);
        } catch (Exception e2) {
            Log.e(TAG, "onActivityResult: ", e2);
        }
    }
    public static void connectPrinter(boolean z, boolean z2) {
        createBluetoothInstance(z2);
        if (z2) {
            if (mChatService1.getState() == 3) {
                mBluetoothStateChange.onBluetoothStateChange(3);
                if (z) {
                    Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_device_connected), Toast.LENGTH_SHORT).show();
                    return;
                }
                return;
            }
            if (!TextUtils.isEmpty(printerAddress1)) {
                try {
                    mChatService1.connect(mBluetoothAdapter1.getRemoteDevice(printerAddress1));
                    return;
                } catch (Exception unused) {
                    Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_20_bluetooth_connection_error), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_21_bluetooth_name_null_error), Toast.LENGTH_SHORT).show();
            return;
        }
        if (mChatService2.getState() == 3) {
            mBluetoothStateChange.onBluetoothStateChange(3);
            if (z) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_device_connected), Toast.LENGTH_SHORT).show();
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(printerAddress2)) {
            try {
                mChatService2.connect(mBluetoothAdapter1.getRemoteDevice(printerAddress2));
                return;
            } catch (Exception unused2) {
                Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_20_bluetooth_connection_error), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_21_bluetooth_name_null_error), Toast.LENGTH_SHORT).show();
    }
    private void setBluetoothState(int i2) {
        mBluetoothStateChange.onBluetoothStateChange(i2);
    }
    public static BluetoothService getBluetoothService(boolean z) {
        if (z) {
            return mChatService1;
        }
        return mChatService2;
    }
    public static boolean hasDeviceBluetooth() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            return true;
        }
        Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_39_bluetooth_not_found), Toast.LENGTH_LONG).show();
        return false;
    }
    public static BluetoothAdapter getBluetoothAdapter(boolean z) {
        if (z) {
            return mBluetoothAdapter1;
        }
        return mBluetoothAdapter2;
    }
    public static void setBluetoothAdapter(boolean z, BluetoothAdapter bluetoothAdapter) {
        if (z) {
            mBluetoothAdapter1 = bluetoothAdapter;
        } else {
            mBluetoothAdapter2 = bluetoothAdapter;
        }
    }
    public static void setBlueToothService(boolean z, BluetoothService bluetoothService) {
        if (z) {
            mChatService1 = bluetoothService;
        } else {
            mChatService2 = bluetoothService;
        }
    }
    public static String getPrinterAddress(boolean z) {
        if (z) {
            return printerAddress1;
        }
        return printerAddress2;
    }
    public static String getPrinterName(boolean z) {
        if (z) {
            return printerName1;
        }
        return printerName2;
    }
    public static void setPrinterAddress(boolean z, String str) {
        if (z) {
            printerAddress1 = str;
        } else {
            printerAddress2 = str;
        }
    }
    public static void setPrinterName(boolean z, String str) {
        if (z) {
            printerAddress1 = str;
        } else {
            printerAddress2 = str;
        }
    }
}
