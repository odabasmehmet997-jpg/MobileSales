package com.github.mikephil.charting.components;

import com.github.mikephil.charting.formatter.DefaultXAxisValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class XAxis extends AxisBase {
    public int mLabelWidth = 1;
    public int mLabelHeight = 1;
    public int mLabelRotatedWidth = 1;
    public int mLabelRotatedHeight = 1;
    public int mAxisLabelModulus = 1;
    protected List<String> mValues = new ArrayList ();
    protected float mLabelRotationAngle;
    protected XAxisValueFormatter mXAxisValueFormatter = new DefaultXAxisValueFormatter ();
    private int mSpaceBetweenLabels = 4;
    private boolean mIsAxisModulusCustom;
    private boolean mAvoidFirstLastClipping;
    private XAxisPosition mPosition = XAxisPosition.TOP;

    public XAxis() {
        mYOffset = Utils.convertDpToPixel (4.0f);
    }

    public XAxisPosition getPosition() {
        return this.mPosition;
    }

    public void setPosition(XAxisPosition xAxisPosition) {
        this.mPosition = xAxisPosition;
    }

    public float getLabelRotationAngle() {
        return this.mLabelRotationAngle;
    }

    public void setLabelRotationAngle(float f) {
        this.mLabelRotationAngle = f;
    }

    public void setLabelsToSkip(int i) {
        if (0 > i) {
            i = 0;
        }
        this.mIsAxisModulusCustom = true;
        this.mAxisLabelModulus = i + 1;
    }

    public void resetLabelsToSkip() {
        this.mIsAxisModulusCustom = false;
    }

    public boolean isAxisModulusCustom() {
        return this.mIsAxisModulusCustom;
    }

    public int getSpaceBetweenLabels() {
        return this.mSpaceBetweenLabels;
    }

    public void setSpaceBetweenLabels(int i) {
        this.mSpaceBetweenLabels = i;
    }

    public void setAvoidFirstLastClipping(boolean z) {
        this.mAvoidFirstLastClipping = z;
    }

    public boolean isAvoidFirstLastClippingEnabled() {
        return this.mAvoidFirstLastClipping;
    }

    public List<String> getValues() {
        return this.mValues;
    }

    public void setValues(List<String> list) {
        this.mValues = list;
    }

    public XAxisValueFormatter getValueFormatter() {
        return this.mXAxisValueFormatter;
    }

    public void setValueFormatter(XAxisValueFormatter xAxisValueFormatter) {
        if (null == xAxisValueFormatter) {
            this.mXAxisValueFormatter = new DefaultXAxisValueFormatter ();
        } else {
            this.mXAxisValueFormatter = xAxisValueFormatter;
        }
    }

    public String getLongestLabel() {
        String str = "";
        for (int i = 0; i < this.mValues.size (); i++) {
            String str2 = this.mValues.get (i);
            if (str.length () < str2.length ()) {
                str = str2;
            }
        }
        return str;
    }

    public enum XAxisPosition {
        TOP,
        BOTTOM,
        BOTH_SIDED,
        TOP_INSIDE,
        BOTTOM_INSIDE
    }
}
