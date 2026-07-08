package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.client.android.DecodeHintManager;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.google.zxing.client.android.R;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class DecoratedBarcodeView extends FrameLayout {
    private BarcodeView barcodeView;
    private TextView statusView;
    private TorchListener torchListener;
    private ViewfinderView viewFinder;
    public interface TorchListener {
        void onTorchOff();

        void onTorchOn();
    }
    private class WrappedCallback implements BarcodeCallback {
        private final BarcodeCallback delegate;

        public WrappedCallback(BarcodeCallback barcodeCallback) {
            this.delegate = barcodeCallback;
        }

        public void barcodeResult(BarcodeResult barcodeResult) {
            this.delegate.barcodeResult(barcodeResult);
        }

        public void possibleResultPoints(List<ResultPoint> list) {
            Iterator<ResultPoint> it = list.iterator();
            while (it.hasNext()) {
                DecoratedBarcodeView.this.viewFinder.addPossibleResultPoint(it.next());
            }
            this.delegate.possibleResultPoints(list);
        }
    }
    public DecoratedBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet);
    }
    private void initialize(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.zxing_view);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.zxing_view_zxing_scanner_layout, R.layout.zxing_barcode_scanner);
        typedArrayObtainStyledAttributes.recycle();
        View.inflate(getContext(), resourceId, this);
        BarcodeView barcodeView = findViewById(R.id.zxing_barcode_surface);
        this.barcodeView = barcodeView;
        if (barcodeView == null) {
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.BarcodeView on provided layout with the id \"zxing_barcode_surface\".");
        }
        barcodeView.initializeAttributes(attributeSet);
        ViewfinderView viewfinderView = findViewById(R.id.zxing_viewfinder_view);
        this.viewFinder = viewfinderView;
        if (viewfinderView == null) {
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.ViewfinderView on provided layout with the id \"zxing_viewfinder_view\".");
        }
        viewfinderView.setCameraPreview(this.barcodeView);
        this.statusView = findViewById(R.id.zxing_status_view);
    }
    public void initializeFromIntent(Intent intent) {
        int intExtra;
        Set<BarcodeFormat> decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
        Map<DecodeHintType, ?> decodeHints = DecodeHintManager.parseDecodeHints(intent);
        CameraSettings cameraSettings = new CameraSettings();
        if (intent.hasExtra("SCAN_CAMERA_ID") && (intExtra = intent.getIntExtra("SCAN_CAMERA_ID", -1)) >= 0) {
            cameraSettings.setRequestedCameraId(intExtra);
        }
        if (intent.hasExtra("TORCH_ENABLED") && intent.getBooleanExtra("TORCH_ENABLED", false)) {
            setTorchOn();
        }
        String stringExtra = intent.getStringExtra("PROMPT_MESSAGE");
        if (stringExtra != null) {
            setStatusText(stringExtra);
        }
        int intExtra2 = intent.getIntExtra("SCAN_TYPE", 0);
        String stringExtra2 = intent.getStringExtra("CHARACTER_SET");
        new MultiFormatReader().setHints(decodeHints);
        this.barcodeView.setCameraSettings(cameraSettings);
        this.barcodeView.setDecoderFactory(new DefaultDecoderFactory(decodeFormats, decodeHints, stringExtra2, intExtra2));
    }
    public void setCameraSettings(CameraSettings cameraSettings) {
        this.barcodeView.setCameraSettings(cameraSettings);
    }
    public void setDecoderFactory(DecoderFactory decoderFactory) {
        this.barcodeView.setDecoderFactory(decoderFactory);
    }
    public DecoderFactory getDecoderFactory() {
        return this.barcodeView.getDecoderFactory();
    }
    public CameraSettings getCameraSettings() {
        return this.barcodeView.getCameraSettings();
    }
    public void setStatusText(String str) {
        TextView textView = this.statusView;
        if (textView != null) {
            textView.setText(str);
        }
    }
    public void pause() {
        this.barcodeView.pause();
    }
    public void pauseAndWait() throws InterruptedException {
        this.barcodeView.pauseAndWait();
    }
    public void resume() {
        this.barcodeView.resume();
    }
    public BarcodeView getBarcodeView() {
        return findViewById(R.id.zxing_barcode_surface);
    }
    public ViewfinderView getViewFinder() {
        return this.viewFinder;
    }
    public TextView getStatusView() {
        return this.statusView;
    }
    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeSingle(new WrappedCallback(barcodeCallback));
    }
    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeContinuous(new WrappedCallback(barcodeCallback));
    }
    public void setTorchOn() {
        this.barcodeView.setTorch(true);
        TorchListener torchListener = this.torchListener;
        if (torchListener != null) {
            torchListener.onTorchOn();
        }
    }
    public void setTorchOff() {
        this.barcodeView.setTorch(false);
        TorchListener torchListener = this.torchListener;
        if (torchListener != null) {
            torchListener.onTorchOff();
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == 24) {
            setTorchOn();
            return true;
        }
        if (keyCode == 25) {
            setTorchOff();
            return true;
        }
        if (keyCode == 27 || keyCode == 80) {
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
    public void setTorchListener(TorchListener torchListener) {
        this.torchListener = torchListener;
    }
}
