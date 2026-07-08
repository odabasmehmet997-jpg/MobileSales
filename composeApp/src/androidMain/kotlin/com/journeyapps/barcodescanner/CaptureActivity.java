package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import com.proje.mobilesales.C2442R;

public class CaptureActivity extends Activity {
    private DecoratedBarcodeView barcodeScannerView;
    private CaptureManager capture;
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.barcodeScannerView = initializeContent();
        CaptureManager captureManager = new CaptureManager(this, this.barcodeScannerView);
        this.capture = captureManager;
        captureManager.initializeFromIntent(getIntent(), bundle);
        this.capture.decode();
    }
    protected DecoratedBarcodeView initializeContent() {
        setContentView(C2442R.layout.zxing_capture);
        return findViewById(C2442R.id.zxing_barcode_scanner);
    }
    protected void onResume() {
        super.onResume();
        this.capture.onResume();
    }
    protected void onPause() {
        super.onPause();
        try {
            this.capture.onPause();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.capture.onSaveInstanceState(bundle);
    }
    public void onRequestPermissionsResult(int r1, @NonNull String[] strArr, @NonNull int[] r3) {
        this.capture.onRequestPermissionsResult(r1, strArr, r3);
    }
    public boolean onKeyDown(int r2, KeyEvent keyEvent) {
        return this.barcodeScannerView.onKeyDown(r2, keyEvent) || super.onKeyDown(r2, keyEvent);
    }
}
