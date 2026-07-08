package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import java.util.List;

public interface BarcodeCallback {
    void barcodeResult(BarcodeResult barcodeResult);
    default void possibleResultPoints(List<ResultPoint> list) {
    }
}
