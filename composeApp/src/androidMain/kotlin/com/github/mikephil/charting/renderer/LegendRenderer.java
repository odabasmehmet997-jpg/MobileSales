package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super (viewPortHandler);
        this.mLegend = legend;
        Paint paint = new Paint (1);
        this.mLegendLabelPaint = paint;
        paint.setTextSize (Utils.convertDpToPixel (9.0f));
        this.mLegendLabelPaint.setTextAlign (Paint.Align.LEFT);
        Paint paint2 = new Paint (1);
        this.mLegendFormPaint = paint2;
        paint2.setStyle (Paint.Style.FILL);
        this.mLegendFormPaint.setStrokeWidth (3.0f);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    public void computeLegend(ChartData<?> chartData) {
        if (!this.mLegend.isLegendCustom ()) {
            ArrayList arrayList = new ArrayList ();
            ArrayList arrayList2 = new ArrayList ();
            for (int i = 0; i < chartData.getDataSetCount (); i++) {
                IDataSet dataSetByIndex = chartData.getDataSetByIndex (i);
                List<Integer> colors = dataSetByIndex.getColors ();
                int entryCount = dataSetByIndex.getEntryCount ();
                if (dataSetByIndex instanceof IBarDataSet iBarDataSet) {
                    if (iBarDataSet.isStacked ()) {
                        String[] stackLabels = iBarDataSet.getStackLabels ();
                        int i2 = 0;
                        while (i2 < colors.size () && i2 < iBarDataSet.getStackSize ()) {
                            arrayList.add (stackLabels[i2 % stackLabels.length]);
                            arrayList2.add (colors.get (i2));
                            i2++;
                        }
                        if (null != iBarDataSet.getLabel ()) {
                            arrayList2.add (Integer.valueOf (ColorTemplate.COLOR_SKIP));
                            arrayList.add (iBarDataSet.getLabel ());
                        }
                    }
                }
                if (dataSetByIndex instanceof IPieDataSet iPieDataSet) {
                    List<String> xVals = chartData.getXVals ();
                    int i3 = 0;
                    while (i3 < colors.size () && i3 < entryCount && i3 < xVals.size ()) {
                        arrayList.add (xVals.get (i3));
                        arrayList2.add (colors.get (i3));
                        i3++;
                    }
                    if (null != iPieDataSet.getLabel ()) {
                        arrayList2.add (Integer.valueOf (ColorTemplate.COLOR_SKIP));
                        arrayList.add (iPieDataSet.getLabel ());
                    }
                } else {
                    if (dataSetByIndex instanceof ICandleDataSet iCandleDataSet) {
                        if (1122867 != iCandleDataSet.getDecreasingColor ()) {
                            arrayList2.add (Integer.valueOf (iCandleDataSet.getDecreasingColor ()));
                            arrayList2.add (Integer.valueOf (iCandleDataSet.getIncreasingColor ()));
                            arrayList.add (null);
                            arrayList.add (dataSetByIndex.getLabel ());
                        }
                    }
                    int i4 = 0;
                    while (i4 < colors.size () && i4 < entryCount) {
                        if (i4 >= colors.size () - 1 || i4 >= entryCount - 1) {
                            arrayList.add (chartData.getDataSetByIndex (i).getLabel ());
                        } else {
                            arrayList.add (null);
                        }
                        arrayList2.add (colors.get (i4));
                        i4++;
                    }
                }
            }
            if (!(null == mLegend.getExtraColors () || null == mLegend.getExtraLabels ())) {
                for (int i5 : this.mLegend.getExtraColors ()) {
                    arrayList2.add (Integer.valueOf (i5));
                }
                Collections.addAll (arrayList, this.mLegend.getExtraLabels ());
            }
            this.mLegend.setComputedColors (arrayList2);
            this.mLegend.setComputedLabels (arrayList);
        }
        Typeface typeface = this.mLegend.getTypeface ();
        if (null != typeface) {
            this.mLegendLabelPaint.setTypeface (typeface);
        }
        this.mLegendLabelPaint.setTextSize (this.mLegend.getTextSize ());
        this.mLegendLabelPaint.setColor (this.mLegend.getTextColor ());
        this.mLegend.calculateDimensions (this.mLegendLabelPaint, mViewPortHandler);
    }

    public void renderLegend(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        Legend.LegendHorizontalAlignment legendHorizontalAlignment;
        int i;
        FSize[] fSizeArr;
        Canvas canvas2;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        Legend.LegendDirection legendDirection;
        float f13;
        float f14;
        float f15;
        float f16;
        float contentRight;
        float f17;
        double d;
        if (this.mLegend.isEnabled ()) {
            Typeface typeface = this.mLegend.getTypeface ();
            if (null != typeface) {
                this.mLegendLabelPaint.setTypeface (typeface);
            }
            this.mLegendLabelPaint.setTextSize (this.mLegend.getTextSize ());
            this.mLegendLabelPaint.setColor (this.mLegend.getTextColor ());
            float lineHeight = Utils.getLineHeight (this.mLegendLabelPaint);
            float lineSpacing = Utils.getLineSpacing (this.mLegendLabelPaint) + this.mLegend.getYEntrySpace ();
            float calcTextHeight = lineHeight - (Utils.calcTextHeight (this.mLegendLabelPaint, "ABC") / 2.0f);
            String[] labels = this.mLegend.getLabels ();
            int[] colors = this.mLegend.getColors ();
            float formToTextSpace = this.mLegend.getFormToTextSpace ();
            float xEntrySpace = this.mLegend.getXEntrySpace ();
            Legend.LegendOrientation orientation = this.mLegend.getOrientation ();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment ();
            Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment ();
            Legend.LegendDirection direction = this.mLegend.getDirection ();
            float formSize = this.mLegend.getFormSize ();
            float stackSpace = this.mLegend.getStackSpace ();
            float yOffset = this.mLegend.getYOffset ();
            float xOffset = this.mLegend.getXOffset ();
            float f18 = stackSpace;
            int i2 = C12361.f824x2787f53e[horizontalAlignment.ordinal ()];
            float f19 = xEntrySpace;
            if (1 == i2) {
                f2 = lineHeight;
                f = formToTextSpace;
                if (Legend.LegendOrientation.VERTICAL != orientation) {
                    xOffset += mViewPortHandler.contentLeft ();
                }
                f3 = Legend.LegendDirection.RIGHT_TO_LEFT == direction ? xOffset + this.mLegend.mNeededWidth : xOffset;
            } else if (2 == i2) {
                f2 = lineHeight;
                f = formToTextSpace;
                if (Legend.LegendOrientation.VERTICAL == orientation) {
                    contentRight = mViewPortHandler.getChartWidth ();
                } else {
                    contentRight = mViewPortHandler.contentRight ();
                }
                f3 = contentRight - xOffset;
                if (Legend.LegendDirection.LEFT_TO_RIGHT == direction) {
                    f3 -= this.mLegend.mNeededWidth;
                }
            } else if (3 != i2) {
                f2 = lineHeight;
                f = formToTextSpace;
                f3 = 0.0f;
            } else {
                final Legend.LegendOrientation legendOrientation = Legend.LegendOrientation.VERTICAL;
                if (legendOrientation == orientation) {
                    f17 = mViewPortHandler.getChartWidth () / 2.0f;
                } else {
                    f17 = mViewPortHandler.contentLeft () + (mViewPortHandler.contentWidth () / 2.0f);
                }
                final Legend.LegendDirection legendDirection2 = Legend.LegendDirection.LEFT_TO_RIGHT;
                f3 = f17 + (legendDirection2 == direction ? xOffset : -xOffset);
                f2 = lineHeight;
                if (legendOrientation == orientation) {
                    double d2 = f3;
                    if (legendDirection2 == direction) {
                        f = formToTextSpace;
                        d = ((-this.mLegend.mNeededWidth) / 2.0d) + xOffset;
                    } else {
                        f = formToTextSpace;
                        d = (this.mLegend.mNeededWidth / 2.0d) - xOffset;
                    }
                    f3 = (float) (d2 + d);
                } else {
                    f = formToTextSpace;
                }
            }
            int i3 = C12361.f825x9c9dbef[orientation.ordinal ()];
            int i4 = ColorTemplate.COLOR_SKIP;
            if (1 == i3) {
                Canvas canvas3 = canvas;
                FSize[] calculatedLineSizes = this.mLegend.getCalculatedLineSizes ();
                FSize[] calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes ();
                Boolean[] calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints ();
                int i5 = C12361.f826xc926f1ec[verticalAlignment.ordinal ()];
                if (1 != i5) {
                    if (2 == i5) {
                        yOffset = (mViewPortHandler.getChartHeight () - yOffset) - this.mLegend.mNeededHeight;
                    } else if (3 != i5) {
                        yOffset = 0.0f;
                    } else {
                        yOffset += (mViewPortHandler.getChartHeight () - this.mLegend.mNeededHeight) / 2.0f;
                    }
                }
                float f20 = f3;
                int i6 = 0;
                int i7 = 0;
                for (int length = labels.length; i6 < length; length = length) {
                    if (i6 >= calculatedLabelBreakPoints.length || !calculatedLabelBreakPoints[i6].booleanValue ()) {
                        f5 = f20;
                        f4 = yOffset;
                    } else {
                        f4 = yOffset + f2 + lineSpacing;
                        f5 = f3;
                    }
                    if (f5 == f3 && Legend.LegendHorizontalAlignment.CENTER == horizontalAlignment && i7 < calculatedLineSizes.length) {
                        f5 += (Legend.LegendDirection.RIGHT_TO_LEFT == direction ? calculatedLineSizes[i7].width () : -calculatedLineSizes[i7].width ()) / 2.0f;
                        i7++;
                    }
                    boolean z = 1122868 != colors[i6];
                    boolean z2 = null == labels[i6];
                    if (z) {
                        if (Legend.LegendDirection.RIGHT_TO_LEFT == direction) {
                            f5 -= formSize;
                        }
                        i = i6;
                        fSizeArr = calculatedLineSizes;
                        canvas2 = canvas3;
                        legendHorizontalAlignment = horizontalAlignment;
                        drawForm(canvas, f5, f4 + calcTextHeight, i, this.mLegend);
                        f5 = Legend.LegendDirection.LEFT_TO_RIGHT == direction ? f5 + formSize : f5;
                    } else {
                        i = i6;
                        legendHorizontalAlignment = horizontalAlignment;
                        fSizeArr = calculatedLineSizes;
                        canvas2 = canvas3;
                    }
                    if (!z2) {
                        if (z) {
                            f5 += Legend.LegendDirection.RIGHT_TO_LEFT == direction ? -f : f;
                        }
                        final Legend.LegendDirection legendDirection3 = Legend.LegendDirection.RIGHT_TO_LEFT;
                        if (legendDirection3 == direction) {
                            f5 -= calculatedLabelSizes[i].width ();
                        }
                        drawLabel(canvas2, f5, f4 + f2, labels[i]);
                        if (Legend.LegendDirection.LEFT_TO_RIGHT == direction) {
                            f5 += calculatedLabelSizes[i].width ();
                        }
                        if (legendDirection3 == direction) {
                            f7 = f19;
                            f8 = -f7;
                        } else {
                            f7 = f19;
                            f8 = f7;
                        }
                        f6 = f5 + f8;
                    } else {
                        f7 = f19;
                        f6 = f5 + (Legend.LegendDirection.RIGHT_TO_LEFT == direction ? -f18 : f18);
                    }
                    i6 = i + 1;
                    f19 = f7;
                    canvas3 = canvas2;
                    yOffset = f4;
                    i7 = i7;
                    calculatedLineSizes = fSizeArr;
                    horizontalAlignment = legendHorizontalAlignment;
                    f20 = f6;
                }
            } else if (2 == i3) {
                int i8 = C12361.f826xc926f1ec[verticalAlignment.ordinal ()];
                if (1 == i8) {
                    if (Legend.LegendHorizontalAlignment.CENTER == horizontalAlignment) {
                        f15 = 0.0f;
                    } else {
                        f15 = mViewPortHandler.contentTop ();
                    }
                    f9 = f15 + yOffset;
                } else if (2 == i8) {
                    if (Legend.LegendHorizontalAlignment.CENTER == horizontalAlignment) {
                        f16 = mViewPortHandler.getChartHeight ();
                    } else {
                        f16 = mViewPortHandler.contentBottom ();
                    }
                    f9 = f16 - (this.mLegend.mNeededHeight + yOffset);
                } else if (3 != i8) {
                    f9 = 0.0f;
                } else {
                    Legend legend = this.mLegend;
                    f9 = ((mViewPortHandler.getChartHeight () / 2.0f) - (legend.mNeededHeight / 2.0f)) + legend.getYOffset ();
                }
                float f21 = f9;
                float f22 = 0.0f;
                boolean z3 = false;
                int i9 = 0;
                while (i9 < labels.length) {
                    boolean z4 = colors[i9] != i4;
                    if (z4) {
                        final Legend.LegendDirection legendDirection4 = Legend.LegendDirection.LEFT_TO_RIGHT;
                        f11 = legendDirection4 == direction ? f3 + f22 : f3 - (formSize - f22);
                        f12 = f18;
                        legendDirection = direction;
                        f10 = calcTextHeight;
                        drawForm(canvas, f11, f21 + calcTextHeight, i9, this.mLegend);
                        if (legendDirection4 == legendDirection) {
                            f11 += formSize;
                        }
                    } else {
                        legendDirection = direction;
                        f10 = calcTextHeight;
                        f12 = f18;
                        f11 = f3;
                    }
                    String str = labels[i9];
                    if (null != str) {
                        if (!z4 || z3) {
                            f13 = f;
                            if (z3) {
                                f11 = f3;
                            }
                        } else {
                            if (Legend.LegendDirection.LEFT_TO_RIGHT == legendDirection) {
                                f14 = f;
                                f13 = f14;
                            } else {
                                f13 = f;
                                f14 = -f13;
                            }
                            f11 += f14;
                        }
                        if (Legend.LegendDirection.RIGHT_TO_LEFT == legendDirection) {
                            f11 -= Utils.calcTextWidth (this.mLegendLabelPaint, str);
                        }
                        if (!z3) {
                            drawLabel(canvas, f11, f21 + f2, labels[i9]);
                        } else {
                            f21 += f2 + lineSpacing;
                            drawLabel(canvas, f11, f21 + f2, labels[i9]);
                        }
                        f21 += f2 + lineSpacing;
                        f22 = 0.0f;
                    } else {
                        f13 = f;
                        f22 += formSize + f12;
                        z3 = true;
                    }
                    i9++;
                    direction = legendDirection;
                    f18 = f12;
                    f = f13;
                    calcTextHeight = f10;
                    i4 = ColorTemplate.COLOR_SKIP;
                }
            }
        }
    }

    protected void drawForm(Canvas canvas, float f, float f2, int i, Legend legend) {
        if (1122868 != legend.getColors ()[i]) {
            this.mLegendFormPaint.setColor (legend.getColors ()[i]);
            float formSize = legend.getFormSize ();
            float f3 = formSize / 2.0f;
            int i2 = C12361.f823xfbec3b85[legend.getForm ().ordinal ()];
            if (1 == i2) {
                canvas.drawCircle (f + f3, f2, f3, this.mLegendFormPaint);
            } else if (2 == i2) {
                canvas.drawRect (f, f2 - f3, f + formSize, f2 + f3, this.mLegendFormPaint);
            } else if (3 == i2) {
                canvas.drawLine (f, f2, f + formSize, f2, this.mLegendFormPaint);
            }
        }
    }

    protected void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText (str, f, f2, this.mLegendLabelPaint);
    }

    public enum C12361 {
        ;
        static final int [] f823xfbec3b85;
        static final int [] f824x2787f53e;
        static final int [] f825x9c9dbef;
        static final int [] f826xc926f1ec;

        static {
            int[] iArr = new int[Legend.LegendForm.values ().length];
            f823xfbec3b85 = iArr;
            try {
                iArr[Legend.LegendForm.CIRCLE.ordinal ()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f823xfbec3b85[Legend.LegendForm.SQUARE.ordinal ()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f823xfbec3b85[Legend.LegendForm.LINE.ordinal ()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Legend.LegendOrientation.values ().length];
            f825x9c9dbef = iArr2;
            try {
                iArr2[Legend.LegendOrientation.HORIZONTAL.ordinal ()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f825x9c9dbef[Legend.LegendOrientation.VERTICAL.ordinal ()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values ().length];
            f826xc926f1ec = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal ()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f826xc926f1ec[Legend.LegendVerticalAlignment.BOTTOM.ordinal ()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f826xc926f1ec[Legend.LegendVerticalAlignment.CENTER.ordinal ()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr4 = new int[Legend.LegendHorizontalAlignment.values ().length];
            f824x2787f53e = iArr4;
            try {
                iArr4[Legend.LegendHorizontalAlignment.LEFT.ordinal ()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f824x2787f53e[Legend.LegendHorizontalAlignment.RIGHT.ordinal ()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f824x2787f53e[Legend.LegendHorizontalAlignment.CENTER.ordinal ()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
