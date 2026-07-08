package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
 
public final class AutoFocusManager {
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = "AutoFocusManager";
    private final int MESSAGE_FOCUS = 1;
    private final Camera.AutoFocusCallback autoFocusCallback;
    private final Camera camera;
    private final Handler.Callback focusHandlerCallback;
    private boolean focusing;
    private final Handler handler;
    private boolean stopped;
    private final boolean useAutoFocus;
    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }
    class C25702 implements Camera.AutoFocusCallback {
        C25702() {
        }
 
        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusManager.this.handler.post(new Runnable() {
                private C25702 f0;

                public void run() {
                    this.f0.lambdaonAutoFocus0();
                }
            });
        }
 
        public  void lambdaonAutoFocus0() {
            AutoFocusManager.this.focusing = false;
            AutoFocusManager.this.autoFocusAgainLater();
        }
    }
    public AutoFocusManager(Camera camera, CameraSettings cameraSettings) {
        Handler.Callback callback = new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (message.what != AutoFocusManager.this.MESSAGE_FOCUS) {
                    return false;
                }
                AutoFocusManager.this.focus();
                return true;
            }
        };
        this.focusHandlerCallback = callback;
        this.autoFocusCallback = new C25702();
        this.handler = new Handler(callback);
        this.camera = camera;
        String focusMode = camera.getParameters().getFocusMode();
        boolean z = cameraSettings.isAutoFocusEnabled() && FOCUS_MODES_CALLING_AF.contains(focusMode);
        this.useAutoFocus = z;
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + z);
        start();
    }
    public synchronized void autoFocusAgainLater() {
        if (!this.stopped && !this.handler.hasMessages(this.MESSAGE_FOCUS)) {
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(this.MESSAGE_FOCUS), 2000L);
        }
    }
    public void start() {
        this.stopped = false;
        focus();
    }
    public void focus() {
        if (!this.useAutoFocus || this.stopped || this.focusing) {
            return;
        }
        try {
            this.camera.autoFocus(this.autoFocusCallback);
            this.focusing = true;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected exception while focusing", e2);
            autoFocusAgainLater();
        }
    }
    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }
    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e2) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e2);
            }
        }
    }
}
