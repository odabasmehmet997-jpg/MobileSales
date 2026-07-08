package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class Legend extends ComponentBase {
    public float mNeededHeight;
    public float mNeededWidth;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private Boolean[] mCalculatedLabelBreakPoints;
    private FSize[] mCalculatedLabelSizes;
    private FSize[] mCalculatedLineSizes;
    private int[] mColors;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private int[] mExtraColors;
    private String[] mExtraLabels;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private String[] mLabels;
    private float mMaxSizePercent;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public Legend() {
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new FSize[0];
        this.mCalculatedLabelBreakPoints = new Boolean[0];
        this.mCalculatedLineSizes = new FSize[0];
        this.mFormSize = Utils.convertDpToPixel (8.0f);
        this.mXEntrySpace = Utils.convertDpToPixel (6.0f);
        this.mYEntrySpace = Utils.convertDpToPixel (0.0f);
        this.mFormToTextSpace = Utils.convertDpToPixel (5.0f);
        mTextSize = Utils.convertDpToPixel (10.0f);
        this.mStackSpace = Utils.convertDpToPixel (3.0f);
        mXOffset = Utils.convertDpToPixel (5.0f);
        mYOffset = Utils.convertDpToPixel (3.0f);
    }

    public Legend(int[] iArr, String[] strArr) {
        this ();
        if (null == iArr || null == strArr) {
            throw new IllegalArgumentException ("colors array or labels array is NULL");
        } else if (iArr.length == strArr.length) {
            this.mColors = iArr;
            this.mLabels = strArr;
        } else {
            throw new IllegalArgumentException ("colors array and labels array need to be of same size");
        }
    }

    public Legend(List<Integer> list, List<String> list2) {
        this ();
        if (null == list || null == list2) {
            throw new IllegalArgumentException ("colors array or labels array is NULL");
        } else if (list.size () == list2.size ()) {
            this.mColors = Utils.convertIntegers (list);
            this.mLabels = Utils.convertStrings (list2);
        } else {
            throw new IllegalArgumentException ("colors array and labels array need to be of same size");
        }
    }

    public void setComputedColors(List<Integer> list) {
        this.mColors = Utils.convertIntegers (list);
    }

    public void setComputedLabels(List<String> list) {
        this.mLabels = Utils.convertStrings (list);
    }

    public float getMaximumEntryWidth(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f + this.mFormSize + this.mFormToTextSpace;
            }
            String str = strArr[i];
            if (null != str) {
                float calcTextWidth = Utils.calcTextWidth (paint, str);
                if (calcTextWidth > f) {
                    f = calcTextWidth;
                }
            }
            i++;
        }
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f;
            }
            String str = strArr[i];
            if (null != str) {
                float calcTextHeight = Utils.calcTextHeight (paint, str);
                if (calcTextHeight > f) {
                    f = calcTextHeight;
                }
            }
            i++;
        }
    }

    public int[] getColors() {
        return this.mColors;
    }

    public String[] getLabels() {
        return this.mLabels;
    }

    public String getLabel(int i) {
        return this.mLabels[i];
    }

    public int[] getExtraColors() {
        return this.mExtraColors;
    }

    public String[] getExtraLabels() {
        return this.mExtraLabels;
    }

    public void setExtra(List<Integer> list, List<String> list2) {
        this.mExtraColors = Utils.convertIntegers (list);
        this.mExtraLabels = Utils.convertStrings (list2);
    }

    public void setExtra(int[] iArr, String[] strArr) {
        this.mExtraColors = iArr;
        this.mExtraLabels = strArr;
    }

    public void setCustom(int[] iArr, String[] strArr) {
        if (iArr.length == strArr.length) {
            this.mLabels = strArr;
            this.mColors = iArr;
            this.mIsLegendCustom = true;
            return;
        }
        throw new IllegalArgumentException ("colors array and labels array need to be of same size");
    }

    public void setCustom(List<Integer> list, List<String> list2) {
        if (list.size () == list2.size ()) {
            this.mColors = Utils.convertIntegers (list);
            this.mLabels = Utils.convertStrings (list2);
            this.mIsLegendCustom = true;
            return;
        }
        throw new IllegalArgumentException ("colors array and labels array need to be of same size");
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendPosition getPosition() {
        LegendOrientation legendOrientation = this.mOrientation;
        if (LegendOrientation.VERTICAL == legendOrientation && LegendHorizontalAlignment.CENTER == mHorizontalAlignment && LegendVerticalAlignment.CENTER == mVerticalAlignment) {
            return LegendPosition.PIECHART_CENTER;
        }
        if (LegendOrientation.HORIZONTAL == legendOrientation) {
            if (LegendVerticalAlignment.TOP == mVerticalAlignment) {
                LegendHorizontalAlignment legendHorizontalAlignment = this.mHorizontalAlignment;
                if (LegendHorizontalAlignment.LEFT == legendHorizontalAlignment) {
                    return LegendPosition.ABOVE_CHART_LEFT;
                }
                return LegendHorizontalAlignment.RIGHT == legendHorizontalAlignment ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER;
            }
            LegendHorizontalAlignment legendHorizontalAlignment2 = this.mHorizontalAlignment;
            if (LegendHorizontalAlignment.LEFT == legendHorizontalAlignment2) {
                return LegendPosition.BELOW_CHART_LEFT;
            }
            return LegendHorizontalAlignment.RIGHT == legendHorizontalAlignment2 ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER;
        } else if (LegendHorizontalAlignment.LEFT == mHorizontalAlignment) {
            LegendVerticalAlignment legendVerticalAlignment = this.mVerticalAlignment;
            if (LegendVerticalAlignment.TOP != legendVerticalAlignment || !this.mDrawInside) {
                return LegendVerticalAlignment.CENTER == legendVerticalAlignment ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART;
            }
            return LegendPosition.LEFT_OF_CHART_INSIDE;
        } else {
            LegendVerticalAlignment legendVerticalAlignment2 = this.mVerticalAlignment;
            if (LegendVerticalAlignment.TOP != legendVerticalAlignment2 || !this.mDrawInside) {
                return LegendVerticalAlignment.CENTER == legendVerticalAlignment2 ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART;
            }
            return LegendPosition.RIGHT_OF_CHART_INSIDE;
        }
    }

    public void setPosition(LegendPosition legendPosition) {
        switch (C12321.f815x7d277f6a[legendPosition.ordinal ()]) {
            case 1:
            case 2:
            case 3:
                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
                this.mVerticalAlignment = LegendPosition.LEFT_OF_CHART_CENTER == legendPosition ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case 4:
            case 5:
            case 6:
                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
                this.mVerticalAlignment = LegendPosition.RIGHT_OF_CHART_CENTER == legendPosition ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case 7:
            case 8:
            case 9:
                this.mHorizontalAlignment = LegendPosition.ABOVE_CHART_LEFT == legendPosition ? LegendHorizontalAlignment.LEFT : LegendPosition.ABOVE_CHART_RIGHT == legendPosition ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case 10:
            case 11:
            case 12:
                this.mHorizontalAlignment = LegendPosition.BELOW_CHART_LEFT == legendPosition ? LegendHorizontalAlignment.LEFT : LegendPosition.BELOW_CHART_RIGHT == legendPosition ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case 13:
                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.CENTER;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
        }
        this.mDrawInside = LegendPosition.LEFT_OF_CHART_INSIDE == legendPosition || LegendPosition.RIGHT_OF_CHART_INSIDE == legendPosition;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormSize(float f) {
        this.mFormSize = Utils.convertDpToPixel (f);
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float f) {
        this.mXEntrySpace = Utils.convertDpToPixel (f);
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float f) {
        this.mYEntrySpace = Utils.convertDpToPixel (f);
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float f) {
        this.mFormToTextSpace = Utils.convertDpToPixel (f);
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float f) {
        this.mStackSpace = f;
    }

    public float getFullWidth(Paint paint) {
        float f;
        float f2 = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f2;
            }
            String str = strArr[i];
            if (null != str) {
                if (1122868 != mColors[i]) {
                    f2 += this.mFormSize + this.mFormToTextSpace;
                }
                f2 += Utils.calcTextWidth (paint, str);
                if (i < this.mLabels.length - 1) {
                    f = this.mXEntrySpace;
                    f2 += f;
                    i++;
                } else {
                    i++;
                }
            } else {
                f2 += this.mFormSize;
                if (i < strArr.length - 1) {
                    f = this.mStackSpace;
                    f2 += f;
                    i++;
                } else {
                    i++;
                }
            }
        }
    }

    public float getFullHeight(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f;
            }
            String str = strArr[i];
            if (null != str) {
                f += Utils.calcTextHeight (paint, str);
                if (i < this.mLabels.length - 1) {
                    f += this.mYEntrySpace;
                }
            }
            i++;
        }
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float f) {
        this.mMaxSizePercent = f;
    }

    public FSize[] getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public Boolean[] getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public FSize[] getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint paint, ViewPortHandler viewPortHandler) {
        float f;
        int i;
        float f2;
        Paint paint2 = paint;
        this.mTextWidthMax = getMaximumEntryWidth(paint);
        this.mTextHeightMax = getMaximumEntryHeight(paint);
        int i2 = C12321.f814x9c9dbef[this.mOrientation.ordinal ()];
        int i3 = ColorTemplate.COLOR_SKIP;
        if (1 == i2) {
            float lineHeight = Utils.getLineHeight (paint);
            int length = this.mLabels.length;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 0.0f;
            boolean z = false;
            for (int i4 = 0; i4 < length; i4++) {
                boolean z2 = 1122868 != mColors[i4];
                if (!z) {
                    f5 = 0.0f;
                }
                if (z2) {
                    if (z) {
                        f5 += this.mStackSpace;
                    }
                    f5 += this.mFormSize;
                }
                if (null != mLabels[i4]) {
                    if (z2 && !z) {
                        f5 += this.mFormToTextSpace;
                    } else if (z) {
                        f3 = Math.max (f3, f5);
                        f4 += this.mYEntrySpace + lineHeight;
                        f5 = 0.0f;
                        z = false;
                    }
                    f5 += Utils.calcTextWidth (paint, this.mLabels[i4]);
                    if (i4 < length - 1) {
                        f4 += this.mYEntrySpace + lineHeight;
                    }
                } else {
                    f5 += this.mFormSize;
                    if (i4 < length - 1) {
                        f5 += this.mStackSpace;
                    }
                    z = true;
                }
                f3 = Math.max (f3, f5);
            }
            this.mNeededWidth = f3;
            this.mNeededHeight = f4;
        } else if (2 == i2) {
            int length2 = this.mLabels.length;
            float lineHeight2 = Utils.getLineHeight (paint);
            float lineSpacing = Utils.getLineSpacing (paint) + this.mYEntrySpace;
            float contentWidth = viewPortHandler.contentWidth () * this.mMaxSizePercent;
            ArrayList arrayList = new ArrayList (length2);
            ArrayList arrayList2 = new ArrayList (length2);
            ArrayList arrayList3 = new ArrayList ();
            int i5 = -1;
            int i6 = -1;
            float f6 = 0.0f;
            int i7 = 0;
            float f7 = 0.0f;
            float f8 = 0.0f;
            while (i7 < length2) {
                boolean z3 = this.mColors[i7] != i3;
                arrayList2.add (Boolean.FALSE);
                if (i6 == i5) {
                    f = 0.0f;
                } else {
                    f = f8 + this.mStackSpace;
                }
                String str = this.mLabels[i7];
                if (null != str) {
                    arrayList.add (Utils.calcTextSize (paint2, str));
                    f8 = f + (z3 ? this.mFormToTextSpace + this.mFormSize : 0.0f) + ((FSize) arrayList.get (i7)).width ();
                } else {
                    arrayList.add (new FSize (0.0f, 0.0f));
                    f8 = f + (z3 ? this.mFormSize : 0.0f);
                    if (-1 == i6) {
                        i6 = i7;
                    }
                }
                if (null != mLabels[i7] || i7 == length2 - 1) {
                    int i8 = (0.0f < f6 ? 1 : (0.0f == f6 ? 0 : -1));
                    if (0 == i8) {
                        f2 = 0.0f;
                    } else {
                        f2 = this.mXEntrySpace;
                    }
                    if (!this.mWordWrapEnabled || 0 == i8 || contentWidth - f6 >= f2 + f8) {
                        i = -1;
                        f6 += f2 + f8;
                    } else {
                        arrayList3.add (new FSize (f6, lineHeight2));
                        f7 = Math.max (f7, f6);
                        i = -1;
                        arrayList2.set (-1 < i6 ? i6 : i7, Boolean.TRUE);
                        f6 = f8;
                    }
                    if (i7 == length2 - 1) {
                        arrayList3.add (new FSize (f6, lineHeight2));
                        f7 = Math.max (f7, f6);
                    }
                } else {
                    i = -1;
                }
                if (null != mLabels[i7]) {
                    i6 = i;
                }
                i7++;
                paint2 = paint;
                i5 = i;
                i3 = ColorTemplate.COLOR_SKIP;
            }
            this.mCalculatedLabelSizes = (FSize[]) arrayList.toArray (new FSize[arrayList.size ()]);
            this.mCalculatedLabelBreakPoints = (Boolean[]) arrayList2.toArray (new Boolean[arrayList2.size ()]);
            FSize[] fSizeArr = (FSize[]) arrayList3.toArray (new FSize[arrayList3.size ()]);
            this.mCalculatedLineSizes = fSizeArr;
            this.mNeededWidth = f7;
            this.mNeededHeight = (lineHeight2 * fSizeArr.length) + (lineSpacing * (0 == fSizeArr.length ? 0 : fSizeArr.length - 1));
        }
    }

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    enum C12321 {
        ;

        /* renamed from: SwitchMapcomgithubmikephilchartingcomponentsLegendLegendOrientation */
        static final int[] f814x9c9dbef;

        /* renamed from: SwitchMapcomgithubmikephilchartingcomponentsLegendLegendPosition */
        static final int[] f815x7d277f6a;

        static {
            int[] iArr = new int[LegendOrientation.values ().length];
            f814x9c9dbef = iArr;
            try {
                iArr[LegendOrientation.VERTICAL.ordinal ()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f814x9c9dbef[LegendOrientation.HORIZONTAL.ordinal ()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[LegendPosition.values ().length];
            f815x7d277f6a = iArr2;
            try {
                iArr2[LegendPosition.LEFT_OF_CHART.ordinal ()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f815x7d277f6a[LegendPosition.LEFT_OF_CHART_INSIDE.ordinal ()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f815x7d277f6a[LegendPosition.LEFT_OF_CHART_CENTER.ordinal ()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f815x7d277f6a[LegendPosition.RIGHT_OF_CHART.ordinal ()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f815x7d277f6a[LegendPosition.RIGHT_OF_CHART_INSIDE.ordinal ()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f815x7d277f6a[LegendPosition.RIGHT_OF_CHART_CENTER.ordinal ()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f815x7d277f6a[LegendPosition.ABOVE_CHART_LEFT.ordinal ()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f815x7d277f6a[LegendPosition.ABOVE_CHART_CENTER.ordinal ()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f815x7d277f6a[LegendPosition.ABOVE_CHART_RIGHT.ordinal ()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f815x7d277f6a[LegendPosition.BELOW_CHART_LEFT.ordinal ()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f815x7d277f6a[LegendPosition.BELOW_CHART_CENTER.ordinal ()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f815x7d277f6a[LegendPosition.BELOW_CHART_RIGHT.ordinal ()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f815x7d277f6a[LegendPosition.PIECHART_CENTER.ordinal ()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }
}
