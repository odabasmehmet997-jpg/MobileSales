package com.journeyapps.barcodescanner;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result; 
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.PreviewCallback;
import com.google.zxing.client.android.R;

public class DecoderThread {
    private static final String TAG = "DecoderThread";
    private final CameraInstance cameraInstance;
    private Rect cropRect;
    private Decoder decoder;
    private Handler handler;
    private final Handler resultHandler;
    private HandlerThread thread;
    private boolean running = false;
    private final Object LOCK = new Object();
    private final Handler.Callback callback = new Handler.Callback() {  
        public boolean handleMessage(Message message) {
            int r0 = message.what;
            if (r0 == R.id.zxing_decode) {
                DecoderThread.this.decode((SourceData) message.obj);
                return true;
            }
            if (r0 != R.id.zxing_preview_failed) {
                return true;
            }
            DecoderThread.this.requestNextPreview();
            return true;
        }
    };
    private final PreviewCallback previewCallback = new PreviewCallback() {
        public void onPreview(SourceData sourceData) {
            synchronized (DecoderThread.this.LOCK) {
                try {
                    if (DecoderThread.this.running) {
                        DecoderThread.this.handler.obtainMessage(R.id.zxing_decode, sourceData).sendToTarget();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        public void onPreviewError(Exception exc) {
            synchronized (DecoderThread.this.LOCK) {
                try {
                    if (DecoderThread.this.running) {
                        DecoderThread.this.handler.obtainMessage(R.id.zxing_preview_failed).sendToTarget();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };
    public DecoderThread(CameraInstance cameraInstance, Decoder decoder, Handler handler) {
        Util.validateMainThread();
        this.cameraInstance = cameraInstance;
        this.decoder = decoder;
        this.resultHandler = handler;
    }
    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }
    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }
    public void start() {
        Util.validateMainThread();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.thread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.thread.getLooper(), this.callback);
        this.running = true;
        requestNextPreview();
    }
    public void stop() {
        Util.validateMainThread();
        synchronized (this.LOCK) {
            this.running = false;
            this.handler.removeCallbacksAndMessages(null);
            this.thread.quit();
        }
    }
    public void requestNextPreview() {
        this.cameraInstance.requestPreview(this.previewCallback);
    }
    protected LuminanceSource createSource(SourceData sourceData) {
        if (this.cropRect == null) {
            return null;
        }
        return sourceData.createSource();
    }
    public void decode(SourceData sourceData) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        sourceData.setCropRect(this.cropRect);
        LuminanceSource luminanceSourceCreateSource = createSource(sourceData);
        Result resultDecode = luminanceSourceCreateSource != null ? this.decoder.decode(luminanceSourceCreateSource) : null;
        if (resultDecode != null) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Log.d(TAG, "Found barcode in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms");
            if (this.resultHandler != null) {
                Message messageObtain = Message.obtain(this.resultHandler, R.id.zxing_decode_succeeded, new BarcodeResult(resultDecode, sourceData));
                messageObtain.setData(new Bundle());
                messageObtain.sendToTarget();
            }
        } else {
            Handler handler = this.resultHandler;
            if (handler != null) {
                Message.obtain(handler, R.id.zxing_decode_failed).sendToTarget();
            }
        }
        if (this.resultHandler != null) {
            Message.obtain(this.resultHandler, R.id.zxing_possible_result_points, BarcodeResult.transformResultPoints(this.decoder.getPossibleResultPoints(), sourceData)).sendToTarget();
        }
        requestNextPreview();
    }
}
