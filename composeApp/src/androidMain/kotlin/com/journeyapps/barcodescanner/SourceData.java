package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ResultPoint;
import java.io.ByteArrayOutputStream;

public class SourceData {
    private Rect cropRect;
    private final RawImageData data;
    private final int imageFormat;
    private boolean previewMirrored;
    private final int rotation;
    private final int scalingFactor = 1;
    public SourceData(byte[] bArr, int r3, int r4, int r5, int r6) {
        this.data = new RawImageData(bArr, r3, r4);
        this.rotation = r6;
        this.imageFormat = r5;
        if (r3 * r4 <= bArr.length) {
            return;
        }
        throw new IllegalArgumentException("Image data does not match the resolution. " + r3 + "x" + r4 + " > " + bArr.length);
    }
    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }
    public void setPreviewMirrored(boolean z) {
        this.previewMirrored = z;
    }
    public ResultPoint translateResultPoint(ResultPoint resultPoint) {
        float x = (resultPoint.getX() * this.scalingFactor) + this.cropRect.left;
        float y = (resultPoint.getY() * this.scalingFactor) + this.cropRect.top;
        if (this.previewMirrored) {
            x = this.data.width() - x;
        }
        return new ResultPoint(x, y);
    }
    public boolean isRotated() {
        return this.rotation % 180 != 0;
    }
    public PlanarYUVLuminanceSource createSource() {
        RawImageData rawImageDataCropAndScale = this.data.rotateCameraPreview(this.rotation).cropAndScale(this.cropRect, this.scalingFactor);
        return new PlanarYUVLuminanceSource(rawImageDataCropAndScale.data(), rawImageDataCropAndScale.width(), rawImageDataCropAndScale.height(), 0, 0, rawImageDataCropAndScale.width(), rawImageDataCropAndScale.height(), false);
    }
    public Bitmap getBitmap(Rect rect, int r11) {
        if (rect == null) {
            rect = new Rect(0, 0, this.data.width(), this.data.height());
        } else if (isRotated()) {
            rect = new Rect(rect.top, rect.left, rect.bottom, rect.right);
        }
        YuvImage yuvImage = new YuvImage(this.data.data(), this.imageFormat, this.data.width(), this.data.height(), null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = r11;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        if (this.rotation == 0) {
            return bitmapDecodeByteArray;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(this.rotation);
        return Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, false);
    }
}
