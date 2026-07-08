package com.google.zxing.oned;

import com.google.zxing.Writer;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();
}
