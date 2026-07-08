package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import com.journeyapps.barcodescanner.Size;

public class FitXYStrategy extends PreviewScalingStrategy {
    private static float absRatio(float f2) {
        return f2 < 1.0f ? 1.0f / f2 : f2;
    }
    protected float getScore(Size size, Size size2) {
        int r3 = size.width();
        if (r3 <= 0 || size.height() <= 0) {
            return 0.0f;
        }
        float fAbsRatio = (1.0f / absRatio((r3 * 1.0f) / size2.width())) / absRatio((size.height() * 1.0f) / size2.height());
        float fAbsRatio2 = absRatio(((size.width() * 1.0f) / size.height()) / ((size2.width() * 1.0f) / size2.height()));
        return fAbsRatio * (((1.0f / fAbsRatio2) / fAbsRatio2) / fAbsRatio2);
    }
    public Rect scalePreview(Size size, Size size2) {
        return new Rect(0, 0, size2.width(), size2.height());
    }
}
