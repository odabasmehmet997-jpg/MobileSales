package com.github.mikephil.charting.data.realm.implementation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.realm.base.RealmLineRadarDataSet;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import io.realm.RealmObject;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

public class RealmLineDataSet<T extends RealmObject> extends RealmLineRadarDataSet<T> implements ILineDataSet {
    private List<Integer> mCircleColors;
    private LineDataSet.Mode mMode = LineDataSet.Mode.LINEAR;
    private int mCircleColorHole = -1;
    private float mCircleRadius = 8.0f;
    private float mCircleHoleRadius = 4.0f;
    private float mCubicIntensity = 0.2f;
    private DashPathEffect mDashPathEffect;
    private FillFormatter mFillFormatter = new DefaultFillFormatter ();
    private boolean mDrawCircles = true;
    private boolean mDrawCircleHole = true;

    public RealmLineDataSet(RealmResults<T> realmResults, String str) {
        super (realmResults, str);
        this.mCircleColors = null;
        ArrayList arrayList = new ArrayList ();
        this.mCircleColors = arrayList;
        arrayList.add (Integer.valueOf (Color.rgb (140, 234, 255)));
        this.build(this.results);
        this.calcMinMax(0, this.results.size ());
    }

    public RealmLineDataSet(RealmResults<T> realmResults, String str, String str2) {
        super (realmResults, str, str2);
        this.mCircleColors = null;
        ArrayList arrayList = new ArrayList ();
        this.mCircleColors = arrayList;
        arrayList.add (Integer.valueOf (Color.rgb (140, 234, 255)));
        this.build(this.results);
        this.calcMinMax(0, this.results.size ());
    }


    public LineDataSet.Mode getMode() {
        return this.mMode;
    }

    public void setMode(LineDataSet.Mode mode) {
        this.mMode = mode;
    }

    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCubicIntensity(float f) {
        if (1.0f < f) {
            f = 1.0f;
        }
        if (0.05f > f) {
            f = 0.05f;
        }
        this.mCubicIntensity = f;
    }

    public void setCircleSize(float f) {
        this.mCircleRadius = Utils.convertDpToPixel (f);
    }


    public float getCircleRadius() {
        return this.mCircleRadius;
    }

    public float getCircleHoleRadius() {
        return this.mCircleHoleRadius;
    }

    public void setCircleHoleRadius(float f) {
        this.mCircleHoleRadius = Utils.convertDpToPixel (f);
    }

    public void enableDashedLine(float f, float f2, float f3) {
        this.mDashPathEffect = new DashPathEffect (new float[]{f, f2}, f3);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }


    public boolean isDashedLineEnabled() {
        return null != mDashPathEffect;
    }


    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setDrawCircles(boolean z) {
        this.mDrawCircles = z;
    }


    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }


    public void setDrawCubic(boolean z) {
        this.mMode = z ? LineDataSet.Mode.CUBIC_BEZIER : LineDataSet.Mode.LINEAR;
    }


    public boolean isDrawCubicEnabled() {
        return LineDataSet.Mode.CUBIC_BEZIER == mMode;
    }


    public void setDrawStepped(boolean z) {
        this.mMode = z ? LineDataSet.Mode.STEPPED : LineDataSet.Mode.LINEAR;
    }


    public boolean isDrawSteppedEnabled() {
        return LineDataSet.Mode.STEPPED == mMode;
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    public void setCircleColors(List<Integer> list) {
        this.mCircleColors = list;
    }

    public void setCircleColors(int [] iArr) {
        this.mCircleColors = ColorTemplate.createColors (iArr);
    }

    public int getCircleColor(int i) {
        List<Integer> list = this.mCircleColors;
        return list.get (i % list.size ()).intValue ();
    }

    public void setCircleColors(int [] iArr, Context context) {
        ArrayList arrayList = new ArrayList ();
        for (int i : iArr) {
            arrayList.add (Integer.valueOf (context.getResources ().getColor (i)));
        }
        this.mCircleColors = arrayList;
    }

    public void setCircleColor(int i) {
        resetCircleColors();
        this.mCircleColors.add (Integer.valueOf (i));
    }

    public void resetCircleColors() {
        this.mCircleColors = new ArrayList ();
    }

    public void setCircleColorHole(int i) {
        this.mCircleColorHole = i;
    }


    public int getCircleHoleColor() {
        return this.mCircleColorHole;
    }

    public void setDrawCircleHole(boolean z) {
        this.mDrawCircleHole = z;
    }


    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public FillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }

    public void setFillFormatter(FillFormatter fillFormatter) {
        if (null == fillFormatter) {
            this.mFillFormatter = new DefaultFillFormatter ();
        } else {
            this.mFillFormatter = fillFormatter;
        }
    }
}
