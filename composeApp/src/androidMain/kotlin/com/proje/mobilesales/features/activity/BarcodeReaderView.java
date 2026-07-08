package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import java.util.List;
 
public class BarcodeReaderView extends BaseInjectableActivity implements DecoratedBarcodeView.TorchListener {
    private DecoratedBarcodeView barcodeScannerView;
    private AppCompatButton btnCancel;
    private AppCompatImageView imgFlash;
    private LinearLayout ln_barcode;
    private Drawable mCloseFlashDrawable;
    private Drawable mOpenFlashDrawable;
    boolean mFlashOpen;
    private final BarcodeCallback singleCallBack = new BarcodeCallback() {
        public void possibleResultPoints(final List<ResultPoint> list) {
        } 
        public void barcodeResult(final BarcodeResult barcodeResult) {
            barcodeScannerView.pause();
            final Intent intent = new Intent();
            intent.putExtra("SCAN_RESULT", barcodeResult.getText());
            setResult(-1, intent);
            finish();
        }
    }; 
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.simple_barcode);
        this.init();
    }
    public void init() {
        ln_barcode = this.findViewById(R.id.ln_barcode);
        barcodeScannerView = this.findViewById(R.id.zxing_barcode_scanner);
        imgFlash = this.findViewById(R.id.img_switch_flashlight);
        mOpenFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_white_48dp);
        mCloseFlashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_flashlight_off_white_48dp);
        barcodeScannerView.setTorchListener(this);
        barcodeScannerView.decodeContinuous(singleCallBack);
        final AppCompatButton appCompatButton = this.findViewById(R.id.btnCancel);
        btnCancel = appCompatButton;
        appCompatButton.setOnClickListener(new View.OnClickListener() { 
            public void onClick(final View view) {
                lambdainit0(view);
            }
        });
        this.initFlash();
    } 
    public  void lambdainit0(final View view) {
        this.setResult(0, new Intent());
        this.finish();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        barcodeScannerView.resume();
    }
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean onKeyDown(final int i2, final KeyEvent keyEvent) {
        return barcodeScannerView.onKeyDown(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }
    private boolean hasFlash() {
        return this.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }
    public void switchFlashlight() {
        if (mFlashOpen) {
            barcodeScannerView.setTorchOff();
            mFlashOpen = false;
        } else {
            barcodeScannerView.setTorchOn();
            mFlashOpen = true;
        }
    }
    private void initFlash() {
        if (!this.hasFlash()) {
            imgFlash.setVisibility(View.GONE);
        } else {
            imgFlash.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    BarcodeReaderView.this.lambdainitFlash1(view);
                }
            });
        }
    }
    public void lambdainitFlash1(final View view) {
        this.switchFlashlight();
    } 
    public void onTorchOn() {
        imgFlash.setImageDrawable(mCloseFlashDrawable);
    }
    public void onTorchOff() {
        imgFlash.setImageDrawable(mOpenFlashDrawable);
    }
}
