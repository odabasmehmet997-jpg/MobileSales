package com.google.android.material.transition.platform;

class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    static FadeModeResult startOnTop(int i2, int i3) {
        return new FadeModeResult(i2, i3, false);
    }

    static FadeModeResult endOnTop(int i2, int i3) {
        return new FadeModeResult(i2, i3, true);
    }

    private FadeModeResult(int i2, int i3, boolean z) {
        this.startAlpha = i2;
        this.endAlpha = i3;
        this.endOnTop = z;
    }
}
