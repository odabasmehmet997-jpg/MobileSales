package com.proje.mobilesales.core.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.BluetoothUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
 
public class BluetoothService {

    /* renamed from: D */
    private static final boolean f1199D = true;
    private static final String NAME = "MobileSales";
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    private static final String TAG = "MobileSales";
    private static final UUID _UUID = UUID.randomUUID();
    private AcceptThread mAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private final Handler mHandler;
    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    private int mState = 0;

    public BluetoothService(Context context, Handler handler) {
        this.mHandler = handler;
    }

    private synchronized void setState(int i2) {
        Log.d(MobileSales.TAG, "setState() " + this.mState + " -> " + i2);
        this.mState = i2;
        this.mHandler.obtainMessage(1, i2, -1).sendToTarget();
    }

    public synchronized int getState() {
        return this.mState;
    }

    public synchronized void start() {
        try {
            Log.d(MobileSales.TAG, "start");
            ConnectThread connectThread = this.mConnectThread;
            if (connectThread != null) {
                connectThread.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread = this.mConnectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
                this.mConnectedThread = null;
            }
            if (this.mAcceptThread == null) {
                AcceptThread acceptThread = new AcceptThread();
                this.mAcceptThread = acceptThread;
                acceptThread.start();
            }
            setState(1);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void connect(BluetoothDevice bluetoothDevice) {
        ConnectThread connectThread;
        try {
            Log.d(MobileSales.TAG, "connect to: " + bluetoothDevice);
            if (this.mState == 2 && (connectThread = this.mConnectThread) != null) {
                connectThread.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread = this.mConnectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
                this.mConnectedThread = null;
            }
            ConnectThread connectThread2 = new ConnectThread(bluetoothDevice);
            this.mConnectThread = connectThread2;
            connectThread2.start();
            setState(2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        try {
            Log.d(MobileSales.TAG, "connected");
            ConnectThread connectThread = this.mConnectThread;
            if (connectThread != null) {
                connectThread.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread = this.mConnectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
                this.mConnectedThread = null;
            }
            AcceptThread acceptThread = this.mAcceptThread;
            if (acceptThread != null) {
                acceptThread.cancel();
                this.mAcceptThread = null;
            }
            ConnectedThread connectedThread2 = new ConnectedThread(bluetoothSocket);
            this.mConnectedThread = connectedThread2;
            connectedThread2.start();
            Message obtainMessage = this.mHandler.obtainMessage(4);
            Bundle bundle = new Bundle();
            bundle.putString(BluetoothUtil.DEVICE_NAME, bluetoothDevice.getName());
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
            setState(3);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void stop() {
        try {
            Log.d(MobileSales.TAG, "stop");
            ConnectThread connectThread = this.mConnectThread;
            if (connectThread != null) {
                connectThread.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread = this.mConnectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
                this.mConnectedThread = null;
            }
            AcceptThread acceptThread = this.mAcceptThread;
            if (acceptThread != null) {
                acceptThread.cancel();
                this.mAcceptThread = null;
            }
            setState(0);
        } catch (Throwable th) {
            throw th;
        }
    }

    public void write(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.mState != 3) {
                    return;
                }
                this.mConnectedThread.write(bArr);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String writeAndRead(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.mState != 3) {
                    return "";
                }
                return this.mConnectedThread.writeAndRead(bArr);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public void connectionFailed() {
        setState(1);
        Message obtainMessage = this.mHandler.obtainMessage(5);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothUtil.TOAST, ContextUtils.getStringResource(R.string.str_cannot_connect_device));
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }
    public void connectionLost() {
        setState(1);
        Message obtainMessage = this.mHandler.obtainMessage(5);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothUtil.TOAST, ContextUtils.getStringResource(R.string.str_device_disconnected));
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            BluetoothServerSocket bluetoothServerSocket;
            try {
                bluetoothServerSocket = BluetoothService.this.mAdapter.listenUsingRfcommWithServiceRecord(MobileSales.TAG, BluetoothService._UUID);
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "listen() failed", e2);
                bluetoothServerSocket = null;
            }
            this.mmServerSocket = bluetoothServerSocket;
        }

        public void run() {
            Log.d(MobileSales.TAG, "BEGIN mAcceptThread" + this);
            setName("AcceptThread");
            BluetoothSocket bluetoothSocket = null;
            while (BluetoothService.this.mState != 3) {
                try {
                    BluetoothServerSocket bluetoothServerSocket = this.mmServerSocket;
                    if (bluetoothServerSocket != null) {
                        bluetoothSocket = bluetoothServerSocket.accept();
                    }
                    if (bluetoothSocket != null) {
                        synchronized (BluetoothService.this) {
                            int i2 = BluetoothService.this.mState;
                            if (i2 != 0) {
                                if (i2 == 1 || i2 == 2) {
                                    BluetoothService.this.connected(bluetoothSocket, bluetoothSocket.getRemoteDevice());
                                } else if (i2 != 3) {
                                }
                            }
                            bluetoothSocket.close();
                        }
                    }
                } catch (IOException e2) {
                    Log.e(MobileSales.TAG, "accept() failed", e2);
                }
            }
            Log.i(MobileSales.TAG, "END mAcceptThread");
        }

        public void cancel() {
            Log.d(MobileSales.TAG, "cancel " + this);
            try {
                BluetoothServerSocket bluetoothServerSocket = this.mmServerSocket;
                if (bluetoothServerSocket != null) {
                    bluetoothServerSocket.close();
                }
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "close() of server failed", e2);
            }
        }
    }

    private class ConnectThread extends Thread {
        private final BluetoothDevice mmDevice;
        private BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice bluetoothDevice) {
            BluetoothSocket bluetoothSocket;
            this.mmDevice = bluetoothDevice;
            try {
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(bluetoothDevice.getUuids()[0].getUuid());
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "createRfcommSocketToServiceRecord", e2);
                bluetoothSocket = null;
            }
            this.mmSocket = bluetoothSocket;
        }
        public void run() {
            Log.i(MobileSales.TAG, "BEGIN mConnectThread");
            setName("ConnectThread");
            BluetoothService.this.mAdapter.cancelDiscovery();
            try {
                this.mmSocket.connect();
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "bluetooth connect", e2);
                try {
                    BluetoothSocket bluetoothSocket = (BluetoothSocket) this.mmDevice.getClass().getMethod("createRfcommSocket", Integer.TYPE).invoke(this.mmDevice, 1);
                    this.mmSocket = bluetoothSocket;
                    bluetoothSocket.connect();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    BluetoothService.this.connectionFailed();
                    try {
                        this.mmSocket.close();
                    } catch (IOException e4) {
                        Log.e(MobileSales.TAG, "unable to close() socket during connection failure", e4);
                    }
                    BluetoothService.this.start();
                    return;
                } catch (IllegalAccessException e5) {
                    e5.printStackTrace();
                    BluetoothService.this.connectionFailed();
                    try {
                        this.mmSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    BluetoothService.this.start();
                    return;
                } catch (NoSuchMethodException e6) {
                    e6.printStackTrace();
                    BluetoothService.this.connectionFailed();
                    try {
                        this.mmSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    BluetoothService.this.start();
                    return;
                } catch (InvocationTargetException e7) {
                    e7.printStackTrace();
                    BluetoothService.this.connectionFailed();
                    try {
                        this.mmSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    BluetoothService.this.start();
                    return;
                }
            }
            synchronized (BluetoothService.this) {
                BluetoothService.this.mConnectThread = null;
            }
            BluetoothService.this.connected(this.mmSocket, this.mmDevice);
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "close() of connect socket failed", e2);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket bluetoothSocket) {
            OutputStream mmOutStream1;
            InputStream mmInStream1;
            InputStream inputStream;
            Log.d(MobileSales.TAG, "create ConnectedThread");
            this.mmSocket = bluetoothSocket;
            OutputStream outputStream = null;
            IOException e;
            try {
                inputStream = bluetoothSocket.getInputStream();
                try {
                    outputStream = bluetoothSocket.getOutputStream();
                } catch (IOException e2) {
                    e = e2;
                    Log.e(MobileSales.TAG, "temp sockets not created", e);
                    mmInStream1 = inputStream;
                    mmOutStream1 = outputStream;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
            }
            mmInStream1 = inputStream;
            this.mmInStream = mmInStream1;
            mmOutStream1 = outputStream;
            this.mmOutStream = mmOutStream1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.i(MobileSales.TAG, "BEGIN mConnectedThread");
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    BluetoothService.this.mHandler.obtainMessage(2, this.mmInStream.read(bArr), -1, bArr).sendToTarget();
                } catch (IOException e2) {
                    Log.e(MobileSales.TAG, "disconnected", e2);
                    BluetoothService.this.connectionLost();
                    return;
                }
            }
        }

        public void write(byte[] bArr) {
            try {
                this.mmOutStream.write(bArr);
                this.mmOutStream.flush();
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "Exception during write", e2);
            }
        }

        public String writeAndRead(byte[] bArr) {
            try {
                this.mmOutStream.write(bArr);
                this.mmOutStream.flush();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                byte[] bArr2 = new byte[1024];
                byte[] bArr3 = new byte[1024];
                int i2 = 0;
                while (this.mmInStream.available() > 0) {
                    try {
                        int read = this.mmInStream.read(bArr2);
                        if (read >= 0) {
                            System.arraycopy(bArr2, 0, bArr3, i2, read);
                        }
                        bArr2 = bArr3;
                        i2 = read;
                    } catch (IOException unused) {
                        return "-1";
                    }
                }
                return new String(bArr2);
            } catch (IOException e3) {
                Log.e(MobileSales.TAG, "Exception during write", e3);
                return "-1";
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e2) {
                Log.e(MobileSales.TAG, "close() of connect socket failed", e2);
            }
        }
    }
}
