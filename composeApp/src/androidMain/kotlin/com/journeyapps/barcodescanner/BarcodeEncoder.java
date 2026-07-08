package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.google.zxing.common.BitMatrix;
 
public class BarcodeEncoder {
    public Bitmap createBitmap(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] r1 = new int[width * height];
        for (int r0 = 0; r0 < height; r0++) {
            int r2 = r0 * width;
            for (int r3 = 0; r3 < width; r3++) {
                r1[r2 + r3] = bitMatrix.get(r3, r0) ? ViewCompat.MEASURED_STATE_MASK : -1;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(r1, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
