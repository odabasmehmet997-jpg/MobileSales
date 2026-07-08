package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
 
public class DecoderResultPointCallback implements ResultPointCallback {
    private Decoder decoder;
    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }
    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.foundPossibleResultPoint(resultPoint);
        }
    }
}
