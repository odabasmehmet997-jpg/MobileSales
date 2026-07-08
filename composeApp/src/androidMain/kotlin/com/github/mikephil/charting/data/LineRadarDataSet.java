package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    protected Drawable mFillDrawable;
    private int mFillColor = Color.rgb (140, 234, 255);
    private int mFillAlpha = 85;
    private float mLineWidth = 2.5f;
    private boolean mDrawFilled;

    protected LineRadarDataSet(List<T> list, String str) {
        super (list, str);
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public void setFillColor(int i) {
        this.mFillColor = i;
        this.mFillDrawable = null;
    }

    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int i) {
        this.mFillAlpha = i;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setLineWidth(float f) {
        if (0.2f > f) {
            f = 0.2f;
        }
        if (10.0f < f) {
            f = 10.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel (f);
    }

    public void setDrawFilled(boolean z) {
        this.mDrawFilled = z;
    }

    public boolean isDrawFilledEnabled() {
        return this.mDrawFilled;
    }
}
