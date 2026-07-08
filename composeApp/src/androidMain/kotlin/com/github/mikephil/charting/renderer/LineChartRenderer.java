package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;

public class LineChartRenderer extends LineRadarRenderer {
    private final float[] mLineBuffer = new float[4];
    private final Path mCirclePathBuffer = new Path ();
    protected Canvas mBitmapCanvas;
    protected LineDataProvider mChart;
    protected Paint mCirclePaintInner;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
    protected Path cubicPath = new Path ();
    protected Path cubicFillPath = new Path ();

    public LineChartRenderer(LineDataProvider lineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = lineDataProvider;
        Paint paint = new Paint (1);
        this.mCirclePaintInner = paint;
        paint.setStyle (Paint.Style.FILL);
        this.mCirclePaintInner.setColor (-1);
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) mViewPortHandler.getChartWidth ();
        int chartHeight = (int) mViewPortHandler.getChartHeight ();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (!(null != weakReference && weakReference.get ().getWidth () == chartWidth && this.mDrawBitmap.get ().getHeight () == chartHeight)) {
            if (0 < chartWidth && 0 < chartHeight) {
                this.mDrawBitmap = new WeakReference<> (Bitmap.createBitmap (chartWidth, chartHeight, this.mBitmapConfig));
                this.mBitmapCanvas = new Canvas (this.mDrawBitmap.get ());
            } else {
                return;
            }
        }
        this.mDrawBitmap.get ().eraseColor (0);
        for (ILineDataSet dataSet : this.mChart.getLineData ().getDataSets ()) {
            if (dataSet.isVisible () && 0 < dataSet.getEntryCount ()) {
                drawDataSet(canvas, dataSet);
            }
        }
        canvas.drawBitmap (this.mDrawBitmap.get (), 0.0f, 0.0f, mRenderPaint);
    }

    protected void drawDataSet(Canvas canvas, ILineDataSet iLineDataSet) {
        if (1 <= iLineDataSet.getEntryCount ()) {
            mRenderPaint.setStrokeWidth (iLineDataSet.getLineWidth ());
            mRenderPaint.setPathEffect (iLineDataSet.getDashPathEffect ());
            int i = C12371.SwitchMapcomgithubmikephilchartingdataLineDataSetMode[iLineDataSet.getMode ().ordinal ()];
            if (3 == i) {
                drawCubicBezier(canvas, iLineDataSet);
            } else if (4 != i) {
                drawLinear(canvas, iLineDataSet);
            } else {
                drawHorizontalBezier(canvas, iLineDataSet);
            }
            mRenderPaint.setPathEffect (null);
        }
    }

    protected void drawHorizontalBezier(Canvas r20, ILineDataSet r21) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawHorizontalBezier(android.graphics.Canvas, com.github.mikephil.charting.interfaces.datasets.ILineDataSet):void");
    }

    protected void drawCubicBezier(Canvas r26, ILineDataSet r27) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawCubicBezier(android.graphics.Canvas, com.github.mikephil.charting.interfaces.datasets.ILineDataSet):void");
    }

    protected void drawCubicFill(Canvas r4, ILineDataSet r5, Path r6, Transformer r7, int r8, int r9) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawCubicFill(android.graphics.Canvas, com.github.mikephil.charting.interfaces.datasets.ILineDataSet, android.graphics.Path, com.github.mikephil.charting.utils.Transformer, int, int):void");
    }

    protected void drawLinear(Canvas r23, ILineDataSet r24) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawLinear(android.graphics.Canvas, com.github.mikephil.charting.interfaces.datasets.ILineDataSet):void");
    }

    protected void drawLinearFill(Canvas canvas, ILineDataSet iLineDataSet, int i, int i2, Transformer transformer) {
        Path generateFilledPath = generateFilledPath(iLineDataSet, i, i2);
        transformer.pathValueToPixel (generateFilledPath);
        Drawable fillDrawable = iLineDataSet.getFillDrawable ();
        if (null != fillDrawable) {
            drawFilledPath(canvas, generateFilledPath, fillDrawable);
        } else {
            drawFilledPath(canvas, generateFilledPath, iLineDataSet.getFillColor (), iLineDataSet.getFillAlpha ());
        }
    }

    private Path generateFilledPath(ILineDataSet r10, int r11, int r12) {
        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.generateFilledPath(com.github.mikephil.charting.interfaces.datasets.ILineDataSet, int, int):android.graphics.Path");
    }

    public void drawValues(Canvas r20) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public void drawExtras(Canvas canvas) {
        drawCircles(canvas);
    }

    protected void drawCircles(Canvas r22) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.LineChartRenderer.drawCircles(android.graphics.Canvas):void");
    }

    public void drawHighlighted(Canvas canvas, Highlight [] highlightArr) {
        int i;
        int i2;
        LineData lineData = this.mChart.getLineData ();
        for (Highlight highlight : highlightArr) {
            if (-1 == highlight.getDataSetIndex ()) {
                i = 0;
            } else {
                i = highlight.getDataSetIndex ();
            }
            if (-1 == highlight.getDataSetIndex ()) {
                i2 = lineData.getDataSetCount ();
            } else {
                i2 = highlight.getDataSetIndex () + 1;
            }
            if (1 <= i2 - i) {
                while (i < i2) {
                    ILineDataSet iLineDataSet = lineData.getDataSetByIndex (i);
                    if (null != iLineDataSet && iLineDataSet.isHighlightEnabled ()) {
                        int xIndex = highlight.getXIndex ();
                        float f = xIndex;
                        if (f <= this.mChart.getXChartMax () * mAnimator.getPhaseX ()) {
                            float yValForXIndex = iLineDataSet.getYValForXIndex (xIndex);
                            if (!Float.isNaN (yValForXIndex)) {
                                float[] fArr = {f, yValForXIndex * mAnimator.getPhaseY ()};
                                this.mChart.getTransformer (iLineDataSet.getAxisDependency ()).pointValuesToPixel (fArr);
                                drawHighlightLines(canvas, fArr, iLineDataSet);
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public void setBitmapConfig(Bitmap.Config config) {
        this.mBitmapConfig = config;
        releaseBitmap();
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (null != canvas) {
            canvas.setBitmap (null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (null != weakReference) {
            weakReference.get ().recycle ();
            this.mDrawBitmap.clear ();
            this.mDrawBitmap = null;
        }
    }

    public enum C12371 {
        ;
        static final int [] SwitchMapcomgithubmikephilchartingdataLineDataSetMode;

        static {
            int[] iArr = new int[LineDataSet.Mode.values ().length];
            SwitchMapcomgithubmikephilchartingdataLineDataSetMode = iArr;
            try {
                iArr[LineDataSet.Mode.LINEAR.ordinal ()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomgithubmikephilchartingdataLineDataSetMode[LineDataSet.Mode.STEPPED.ordinal ()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomgithubmikephilchartingdataLineDataSetMode[LineDataSet.Mode.CUBIC_BEZIER.ordinal ()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomgithubmikephilchartingdataLineDataSetMode[LineDataSet.Mode.HORIZONTAL_BEZIER.ordinal ()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
