package com.google.android.material.transition.platform;

import android.graphics.RectF;
import androidx.annotation.RequiresApi;

class FitModeEvaluators {
    private static final FitModeEvaluator WIDTH = new FitModeEvaluator() {
        public FitModeResult evaluate(float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            float fLerp = TransitionUtils.lerp(f5, f7, f3, f4, f2, true);
            float f9 = fLerp / f5;
            float f10 = fLerp / f7;
            return new FitModeResult(f9, f10, fLerp, f6 * f9, fLerp, f8 * f10);
        }
        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartHeight > fitModeResult.currentEndHeight;
        }
        public void applyMask(RectF rectF, float f2, FitModeResult fitModeResult) {
            rectF.bottom -= Math.abs(fitModeResult.currentEndHeight - fitModeResult.currentStartHeight) * f2;
        }
    };
    private static final FitModeEvaluator HEIGHT = new FitModeEvaluator() {
        public FitModeResult evaluate(float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            float fLerp = TransitionUtils.lerp(f6, f8, f3, f4, f2, true);
            float f9 = fLerp / f6;
            float f10 = fLerp / f8;
            return new FitModeResult(f9, f10, f5 * f9, fLerp, f7 * f10, fLerp);
        }

        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartWidth > fitModeResult.currentEndWidth;
        }
        public void applyMask(RectF rectF, float f2, FitModeResult fitModeResult) {
            float fAbs = (Math.abs(fitModeResult.currentEndWidth - fitModeResult.currentStartWidth) / 2.0f) * f2;
            rectF.left += fAbs;
            rectF.right -= fAbs;
        }
    };

    static FitModeEvaluator get(int i2, boolean z, RectF rectF, RectF rectF2) {
        if (i2 == 0) {
            return shouldAutoFitToWidth(z, rectF, rectF2) ? WIDTH : HEIGHT;
        }
        if (i2 == 1) {
            return WIDTH;
        }
        if (i2 == 2) {
            return HEIGHT;
        }
        throw new IllegalArgumentException("Invalid fit mode: " + i2);
    }

    private static boolean shouldAutoFitToWidth(boolean z, RectF rectF, RectF rectF2) {
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        float fWidth2 = rectF2.width();
        float fHeight2 = rectF2.height();
        float f2 = (fHeight2 * fWidth) / fWidth2;
        float f3 = (fWidth2 * fHeight) / fWidth;
        if (z) {
            return !(f2 < fHeight);
        } else return !(f3 < fHeight2);
    }

    private FitModeEvaluators() {
    }
}
