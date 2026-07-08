package com.github.mikephil.charting.data.realm.base;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class RealmLineRadarDataSet<T extends RealmObject> extends RealmLineScatterCandleRadarDataSet<T, Entry> implements ILineRadarDataSet<Entry> {
    protected Drawable mFillDrawable;
    private int mFillColor = Color.rgb (140, 234, 255);
    private int mFillAlpha = 85;
    private float mLineWidth = 2.5f;
    private boolean mDrawFilled;

    protected RealmLineRadarDataSet(RealmResults<T> realmResults, String str) {
        super (realmResults, str);
    }

    protected RealmLineRadarDataSet(RealmResults<T> realmResults, String str, String str2) {
        super (realmResults, str, str2);
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
