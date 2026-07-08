package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;
import com.proje.mobilesales.R;

public class CameraInstance {
    private static final String TAG = "CameraInstance";
    private final CameraManager cameraManager;
    private final CameraThread cameraThread;
    private DisplayConfiguration displayConfiguration;
    private final Handler mainHandler;
    private Handler readyHandler;
    private CameraSurface surface;
    private boolean open = false;
    private boolean cameraClosed = true;
    private CameraSettings cameraSettings = new CameraSettings();
    private final Runnable opener = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Opening camera");
                CameraInstance.this.cameraManager.open();
            } catch (Exception e2) {
                CameraInstance.this.notifyError(e2);
                Log.e(CameraInstance.TAG, "Failed to open camera", e2);
            }
        }
    };
    private final Runnable configure = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Configuring camera");
                CameraInstance.this.cameraManager.configure();
                if (CameraInstance.this.readyHandler != null) {
                    CameraInstance.this.readyHandler.obtainMessage(R.id.zxing_prewiew_size_ready, CameraInstance.this.getPreviewSize()).sendToTarget();
                }
            } catch (Exception e2) {
                CameraInstance.this.notifyError(e2);
                Log.e(CameraInstance.TAG, "Failed to configure camera", e2);
            }
        }
    };
    private final Runnable previewStarter = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Starting preview");
                CameraInstance.this.cameraManager.setPreviewDisplay(CameraInstance.this.surface);
                CameraInstance.this.cameraManager.startPreview();
            } catch (Exception e2) {
                CameraInstance.this.notifyError(e2);
                Log.e(CameraInstance.TAG, "Failed to start preview", e2);
            }
        }
    };
    private final Runnable closer = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Closing camera");
                CameraInstance.this.cameraManager.stopPreview();
                CameraInstance.this.cameraManager.close();
            } catch (Exception e2) {
                Log.e(CameraInstance.TAG, "Failed to close camera", e2);
            }
            CameraInstance.this.cameraClosed = true;
            CameraInstance.this.readyHandler.sendEmptyMessage(R.id.zxing_camera_closed);
            CameraInstance.this.cameraThread.decrementInstances();
        }
    };
    public CameraInstance(Context context) {
        Util.validateMainThread();
        this.cameraThread = CameraThread.getInstance();
        CameraManager cameraManager = new CameraManager(context);
        this.cameraManager = cameraManager;
        cameraManager.setCameraSettings(this.cameraSettings);
        this.mainHandler = new Handler();
    }
    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
        this.cameraManager.setDisplayConfiguration(displayConfiguration);
    }
    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }
    public void setReadyHandler(Handler handler) {
        this.readyHandler = handler;
    }
    public void setSurface(CameraSurface cameraSurface) {
        this.surface = cameraSurface;
    }
    public void setCameraSettings(CameraSettings cameraSettings) {
        if (this.open) {
            return;
        }
        this.cameraSettings = cameraSettings;
        this.cameraManager.setCameraSettings(cameraSettings);
    }
    public Size getPreviewSize() {
        return this.cameraManager.getPreviewSize();
    }
    public void open() {
        Util.validateMainThread();
        this.open = true;
        this.cameraClosed = false;
        this.cameraThread.incrementAndEnqueue(this.opener);
    }
    public void configureCamera() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.configure);
    }
    public void startPreview() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.previewStarter);
    }
    public void setTorch(final boolean z) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new Runnable() {
                private CameraInstance f0;

                public void run() {
                    this.f0.lambdasetTorch0(z);
                }
            });
        }
    }
    public  void lambdasetTorch0(boolean z) {
        this.cameraManager.setTorch(z);
    }
    public void close() {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(this.closer);
        } else {
            this.cameraClosed = true;
        }
        this.open = false;
    }
    public boolean isCameraClosed() {
        return this.cameraClosed;
    }
    public void requestPreview(final PreviewCallback previewCallback) {
        this.mainHandler.post(new Runnable() {
            private CameraInstance f0;

            public void run() {
                this.f0.lambdarequestPreview3(previewCallback);
            }
        });
    }
    public  void lambdarequestPreview3(final PreviewCallback previewCallback) {
        if (!this.open) {
            Log.d(TAG, "Camera is closed, not requesting preview");
        } else {
            this.cameraThread.enqueue(new Runnable() {
                private CameraInstance f0;

                public void run() {
                    this.f0.lambdanull2(previewCallback);
                }
            });
        }
    }
    public  void lambdanull2(PreviewCallback previewCallback) {
        this.cameraManager.requestPreviewFrame(previewCallback);
    }
    private void validateOpen() {
        if (!this.open) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }
    private void notifyError(Exception exc) {
        Handler handler = this.readyHandler;
        if (handler != null) {
            handler.obtainMessage(R.id.zxing_camera_error, exc).sendToTarget();
        }
    }
}
