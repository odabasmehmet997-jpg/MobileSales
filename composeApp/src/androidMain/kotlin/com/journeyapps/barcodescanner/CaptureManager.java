package com.journeyapps.barcodescanner;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager; 
import com.google.zxing.client.android.InactivityTimer;
import com.proje.mobilesales.C2442R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
public class CaptureManager {
    private static final String TAG = "CaptureManager";
    private static final int cameraPermissionReqCode = 250;
    private final Activity activity;
    private boolean askedPermission;
    private final DecoratedBarcodeView barcodeView;
    private final BeepManager beepManager;
    private final Handler handler;
    private final InactivityTimer inactivityTimer;
    private final CameraPreview.StateListener stateListener;
    private int orientationLock = -1;
    private boolean returnBarcodeImagePath = false;
    private boolean showDialogIfMissingCameraPermission = true;
    private String missingCameraPermissionDialogMessage = "";
    private boolean destroyed = false;
    private boolean finishWhenClosed = false;
    private final BarcodeCallback callback = new C25631();
    class C25631 implements BarcodeCallback {
        
        public void possibleResultPoints(List<ResultPoint> list) {
        }
        C25631() {
        } 
        public void barcodeResult(final BarcodeResult barcodeResult) {
            CaptureManager.this.barcodeView.pause();
            CaptureManager.this.beepManager.playBeepSoundAndVibrate();
            CaptureManager.this.handler.post(new Runnable() {
                private C25631 f0;

                public void run() {
                    this.f0.lambdabarcodeResult0(barcodeResult);
                }
            });
        } 
        public void lambdabarcodeResult0(BarcodeResult barcodeResult) {
            CaptureManager.this.returnResult(barcodeResult);
        }
    }
    public CaptureManager(Activity activity, DecoratedBarcodeView decoratedBarcodeView) {
        CameraPreview.StateListener stateListener = new CameraPreview.StateListener() {  
            public void previewSized() {
            }

            public void previewStarted() {
            }

            public void previewStopped() {
            } 
            public void cameraError(Exception exc) {
                CaptureManager captureManager = CaptureManager.this;
                captureManager.displayFrameworkBugMessageAndExit(captureManager.activity.getString(C2442R.string.zxing_msg_camera_framework_bug));
            } 
            public void cameraClosed() {
                if (CaptureManager.this.finishWhenClosed) {
                    Log.d(CaptureManager.TAG, "Camera closed; finishing activity");
                    CaptureManager.this.finish();
                }
            }
        };
        this.stateListener = stateListener;
        this.askedPermission = false;
        this.activity = activity;
        this.barcodeView = decoratedBarcodeView;
        decoratedBarcodeView.getBarcodeView().addStateListener(stateListener);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity, new Runnable() {
            private CaptureManager f0;

            public void run() {
                this.f0.lambdanew0();
            }
        });
        this.beepManager = new BeepManager(activity);
    } 
    public   void lambdanew0() {
        Log.d(TAG, "Finishing due to inactivity");
        finish();
    }
    public void initializeFromIntent(Intent intent, Bundle bundle) {
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt("SAVED_ORIENTATION_LOCK", -1);
        }
        if (intent != null) {
            if (intent.getBooleanExtra("SCAN_ORIENTATION_LOCKED", true)) {
                lockOrientation();
            }
            if ("com.google.zxing.client.android.SCAN".equals(intent.getAction())) {
                this.barcodeView.initializeFromIntent(intent);
            }
            if (!intent.getBooleanExtra("BEEP_ENABLED", true)) {
                this.beepManager.setBeepEnabled(false);
            }
            if (intent.hasExtra("SHOW_MISSING_CAMERA_PERMISSION_DIALOG")) {
                setShowMissingCameraPermissionDialog(intent.getBooleanExtra("SHOW_MISSING_CAMERA_PERMISSION_DIALOG", true), intent.getStringExtra("MISSING_CAMERA_PERMISSION_DIALOG_MESSAGE"));
            }
            if (intent.hasExtra("TIMEOUT")) {
                this.handler.postDelayed(new Runnable() {
                    private CaptureManager f0;

                    public void run() {
                        this.f0.returnResultTimeout();
                    }
                }, intent.getLongExtra("TIMEOUT", 0L));
            }
            if (intent.getBooleanExtra("BARCODE_IMAGE_ENABLED", false)) {
                this.returnBarcodeImagePath = true;
            }
        }
    }
    @SuppressLint("WrongConstant")
    protected void lockOrientation() {
        if (this.orientationLock == -1) {
            int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
            int r1 = this.activity.getResources().getConfiguration().orientation;
            int r3 = 0;
            if (r1 == 2) {
                if (rotation != 0 && rotation != 1) {
                    r3 = 8;
                }
            } else if (r1 == 1) {
                r3 = (rotation == 0 || rotation == 3) ? 1 : 9;
            }
            this.orientationLock = r3;
        }
        this.activity.setRequestedOrientation(this.orientationLock);
    }
    public void decode() {
        this.barcodeView.decodeSingle(this.callback);
    }
    public void onResume() {
        openCameraWithPermission();
        this.inactivityTimer.start();
    } 
    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0) {
            this.barcodeView.resume();
        } else {
            if (this.askedPermission) {
                return;
            }
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, cameraPermissionReqCode);
            this.askedPermission = true;
        }
    }
    public void onRequestPermissionsResult(int r1, String[] strArr, int[] r3) {
        if (r1 == cameraPermissionReqCode) {
            if (r3.length > 0 && r3[0] == 0) {
                this.barcodeView.resume();
                return;
            }
            setMissingCameraPermissionResult();
            if (this.showDialogIfMissingCameraPermission) {
                displayFrameworkBugMessageAndExit(this.missingCameraPermissionDialogMessage);
            } else {
                closeAndFinish();
            }
        }
    }
    public void onPause() throws InterruptedException {
        this.inactivityTimer.cancel();
        this.barcodeView.pauseAndWait();
    }
    public void onDestroy() {
        this.destroyed = true;
        this.inactivityTimer.cancel();
        this.handler.removeCallbacksAndMessages(null);
    }
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("SAVED_ORIENTATION_LOCK", this.orientationLock);
    }
    public static Intent resultIntent(BarcodeResult barcodeResult, String str) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra("SCAN_RESULT", barcodeResult.toString());
        intent.putExtra("SCAN_RESULT_FORMAT", barcodeResult.getBarcodeFormat().toString());
        byte[] rawBytes = barcodeResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra("SCAN_RESULT_BYTES", rawBytes);
        }
        Map<ResultMetadataType, Object> resultMetadata = barcodeResult.getResultMetadata();
        if (resultMetadata != null) {
            ResultMetadataType resultMetadataType = ResultMetadataType.UPC_EAN_EXTENSION;
            if (resultMetadata.containsKey(resultMetadataType)) {
                intent.putExtra("SCAN_RESULT_UPC_EAN_EXTENSION", resultMetadata.get(resultMetadataType).toString());
            }
            Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra("SCAN_RESULT_ORIENTATION", number.intValue());
            }
            String str2 = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", str2);
            }
            Iterable iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                Iterator it = iterable.iterator();
                int r1 = 0;
                while (it.hasNext()) {
                    intent.putExtra("SCAN_RESULT_BYTE_SEGMENTS_" + r1, (byte[]) it.next());
                    r1++;
                }
            }
        }
        if (str != null) {
            intent.putExtra("SCAN_RESULT_IMAGE_PATH", str);
        }
        return intent;
    }
    private String getBarcodeImagePath(BarcodeResult barcodeResult) throws IOException {
        if (this.returnBarcodeImagePath) {
            Bitmap bitmap = barcodeResult.getBitmap();
            try {
                File fileCreateTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return fileCreateTempFile.getAbsolutePath();
            } catch (IOException e2) {
                Log.w(TAG, "Unable to create temporary file and store bitmap! " + e2);
            }
        }
        return null;
    }
    public void finish() {
        this.activity.finish();
    }
    protected void closeAndFinish() {
        if (this.barcodeView.getBarcodeView().isCameraClosed()) {
            finish();
        } else {
            this.finishWhenClosed = true;
        }
        this.barcodeView.pause();
        this.inactivityTimer.cancel();
    }
    private void setMissingCameraPermissionResult() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("MISSING_CAMERA_PERMISSION", true);
        this.activity.setResult(0, intent);
    }
    protected void returnResultTimeout() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("TIMEOUT", true);
        this.activity.setResult(0, intent);
        closeAndFinish();
    }
    protected void returnResult(BarcodeResult barcodeResult) {
        try {
            this.activity.setResult(-1, resultIntent(barcodeResult, getBarcodeImagePath(barcodeResult)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeAndFinish();
    }
    protected void displayFrameworkBugMessageAndExit(String str) {
        if (this.activity.isFinishing() || this.destroyed || this.finishWhenClosed) {
            return;
        }
        if (str.isEmpty()) {
            str = this.activity.getString(C2442R.string.zxing_msg_camera_framework_bug);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle(this.activity.getString(C2442R.string.zxing_app_name));
        builder.setMessage(str);
        builder.setPositiveButton(C2442R.string.zxing_button_ok, new DialogInterface.OnClickListener() {
            private CaptureManager f0;

            public void onClick(DialogInterface dialogInterface, int r2) {
                this.f0.lambdadisplayFrameworkBugMessageAndExit1(dialogInterface, r2);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            private CaptureManager f0;

            public void onCancel(DialogInterface dialogInterface) {
                this.f0.lambdadisplayFrameworkBugMessageAndExit2(dialogInterface);
            }
        });
        builder.show();
    }
    public  void lambdadisplayFrameworkBugMessageAndExit1(DialogInterface dialogInterface, int r2) {
        finish();
    }
    public  void lambdadisplayFrameworkBugMessageAndExit2(DialogInterface dialogInterface) {
        finish();
    }
    public void setShowMissingCameraPermissionDialog(boolean z, String str) {
        this.showDialogIfMissingCameraPermission = z;
        if (str == null) {
            str = "";
        }
        this.missingCameraPermissionDialogMessage = str;
    }
}
