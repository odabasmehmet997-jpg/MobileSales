package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.utils.Utils;

public abstract class ComponentBase {
    protected boolean mEnabled = true;
    protected float mXOffset = 5.0f;
    protected float mYOffset = 5.0f;
    protected Typeface mTypeface;
    protected float mTextSize = 10.0f;
    protected int mTextColor = ViewCompat.MEASURED_STATE_MASK;

    public float getXOffset() {
        return mXOffset;
    }

    public void setXOffset(final float f) {
        mXOffset = Utils.convertDpToPixel (f);
    }

    public float getYOffset() {
        return mYOffset;
    }

    public void setYOffset(final float f) {
        mYOffset = Utils.convertDpToPixel (f);
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    public void setTypeface(final Typeface typeface) {
        mTypeface = typeface;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float f) {
        if (24.0f < f) {
            f = 24.0f;
        }
        if (6.0f > f) {
            f = 6.0f;
        }
        mTextSize = Utils.convertDpToPixel (f);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(final int i) {
        mTextColor = i;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(final boolean z) {
        mEnabled = z;
    }
}
