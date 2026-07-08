package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.PointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint("RtlHardcoded")

public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    private final RectF mOffsetsBuffer = new RectF ();
    protected YAxis mAxisLeft;
    protected YAxisRenderer mAxisRendererLeft;
    protected YAxisRenderer mAxisRendererRight;
    protected YAxis mAxisRight;
    protected Paint mBorderPaint;
    protected OnDrawListener mDrawListener;
    protected Paint mGridBackgroundPaint;
    protected Transformer mLeftAxisTransformer;
    protected Transformer mRightAxisTransformer;
    protected XAxisRenderer mXAxisRenderer;
    protected int mMaxVisibleCount = 100;
    protected boolean mPinchZoomEnabled;
    protected boolean mDoubleTapToZoomEnabled = true;
    protected boolean mHighlightPerDragEnabled = true;
    protected boolean mHighlightFullBarEnabled;
    protected boolean mDrawGridBackground;
    protected boolean mDrawBorders;
    protected float mMinOffset = 15.0f;
    protected boolean mKeepPositionOnRotation;
    private boolean mAutoScaleMinMaxEnabled;
    private Integer mAutoScaleLastLowestVisibleXIndex;
    private Integer mAutoScaleLastHighestVisibleXIndex;
    private boolean mDragEnabled = true;
    private boolean mScaleXEnabled = true;
    private boolean mScaleYEnabled = true;
    private long totalTime;
    private long drawCycles;
    private boolean mCustomViewPortEnabled;
    protected BarLineChartBase(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }
    protected BarLineChartBase(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }
    protected BarLineChartBase(final Context context) {
        super (context);
    }
    public T getData() {
        return super.getData ();
    }
    public void init() {
        super.init ();
        mAxisLeft = new YAxis (YAxis.AxisDependency.LEFT);
        mAxisRight = new YAxis (YAxis.AxisDependency.RIGHT);
        mLeftAxisTransformer = new Transformer (this.mViewPortHandler);
        mRightAxisTransformer = new Transformer (this.mViewPortHandler);
        mAxisRendererLeft = new YAxisRenderer (this.mViewPortHandler, mAxisLeft, mLeftAxisTransformer);
        mAxisRendererRight = new YAxisRenderer (this.mViewPortHandler, mAxisRight, mRightAxisTransformer);
        mXAxisRenderer = new XAxisRenderer (this.mViewPortHandler, this.mXAxis, mLeftAxisTransformer);
        this.setHighlighter(new ChartHighlighter (this));
        this.mChartTouchListener = new BarLineChartTouchListener (this, this.mViewPortHandler.getMatrixTouch ());
        final Paint paint = new Paint ();
        mGridBackgroundPaint = paint;
        paint.setStyle (Paint.Style.FILL);
        mGridBackgroundPaint.setColor (Color.rgb (240, 240, 240));
        final Paint paint2 = new Paint ();
        mBorderPaint = paint2;
        paint2.setStyle (Paint.Style.STROKE);
        mBorderPaint.setColor (ViewCompat.MEASURED_STATE_MASK);
        mBorderPaint.setStrokeWidth (Utils.convertDpToPixel (1.0f));
    }
    public void onDraw(final Canvas canvas) {
        final Integer num;
        super.onDraw (canvas);
        if (false) {
            final long currentTimeMillis = System.currentTimeMillis ();
            this.calcModulus();
            mXAxisRenderer.calcXBounds (this, this.mXAxis.mAxisLabelModulus);
            this.mRenderer.calcXBounds (this, this.mXAxis.mAxisLabelModulus);
            this.drawGridBackground(canvas);
            if (mAxisLeft.isEnabled ()) {
                final YAxisRenderer yAxisRenderer = mAxisRendererLeft;
                final YAxis yAxis = mAxisLeft;
                yAxisRenderer.computeAxis (yAxis.mAxisMinimum, yAxis.mAxisMaximum);
            }
            if (mAxisRight.isEnabled ()) {
                final YAxisRenderer yAxisRenderer2 = mAxisRendererRight;
                final YAxis yAxis2 = mAxisRight;
                yAxisRenderer2.computeAxis (yAxis2.mAxisMinimum, yAxis2.mAxisMaximum);
            }
            mXAxisRenderer.renderAxisLine (canvas);
            mAxisRendererLeft.renderAxisLine (canvas);
            mAxisRendererRight.renderAxisLine (canvas);
            if (mAutoScaleMinMaxEnabled) {
                final int lowestVisibleXIndex = this.getLowestVisibleXIndex();
                final int highestVisibleXIndex = this.getHighestVisibleXIndex();
                final Integer num2 = mAutoScaleLastLowestVisibleXIndex;
                if (null == num2 || num2.intValue () != lowestVisibleXIndex || null == (num = this.mAutoScaleLastHighestVisibleXIndex) || num.intValue () != highestVisibleXIndex) {
                    this.calcMinMax();
                    this.calculateOffsets();
                    mAutoScaleLastLowestVisibleXIndex = Integer.valueOf (lowestVisibleXIndex);
                    mAutoScaleLastHighestVisibleXIndex = Integer.valueOf (highestVisibleXIndex);
                }
            }
            final int save = canvas.save ();
            canvas.clipRect (this.mViewPortHandler.getContentRect ());
            mXAxisRenderer.renderGridLines (canvas);
            mAxisRendererLeft.renderGridLines (canvas);
            mAxisRendererRight.renderGridLines (canvas);
            if (this.mXAxis.isDrawLimitLinesBehindDataEnabled ()) {
                mXAxisRenderer.renderLimitLines (canvas);
            }
            if (mAxisLeft.isDrawLimitLinesBehindDataEnabled ()) {
                mAxisRendererLeft.renderLimitLines (canvas);
            }
            if (mAxisRight.isDrawLimitLinesBehindDataEnabled ()) {
                mAxisRendererRight.renderLimitLines (canvas);
            }
            this.mRenderer.drawData (canvas);
            if (this.valuesToHighlight()) {
                this.mRenderer.drawHighlighted (canvas, this.mIndicesToHighlight);
            }
            canvas.restoreToCount (save);
            this.mRenderer.drawExtras (canvas);
            final int save2 = canvas.save ();
            canvas.clipRect (this.mViewPortHandler.getContentRect ());
            if (!this.mXAxis.isDrawLimitLinesBehindDataEnabled ()) {
                mXAxisRenderer.renderLimitLines (canvas);
            }
            if (!mAxisLeft.isDrawLimitLinesBehindDataEnabled ()) {
                mAxisRendererLeft.renderLimitLines (canvas);
            }
            if (!mAxisRight.isDrawLimitLinesBehindDataEnabled ()) {
                mAxisRendererRight.renderLimitLines (canvas);
            }
            canvas.restoreToCount (save2);
            mXAxisRenderer.renderAxisLabels (canvas);
            mAxisRendererLeft.renderAxisLabels (canvas);
            mAxisRendererRight.renderAxisLabels (canvas);
            this.mRenderer.drawValues (canvas);
            this.mLegendRenderer.renderLegend (canvas);
            this.drawMarkers(canvas);
            this.drawDescription(canvas);
            if (this.mLogEnabled) {
                final long currentTimeMillis2 = System.currentTimeMillis () - currentTimeMillis;
                final long j = totalTime + currentTimeMillis2;
                totalTime = j;
                final long j2 = drawCycles + 1;
                drawCycles = j2;
                Log.i (Chart.LOG_TAG, "Drawtime: " + currentTimeMillis2 + " ms, average: " + (j / j2) + " ms, cycles: " + drawCycles);
            }
        }
    }
    public void resetTracking() {
        totalTime = 0;
        drawCycles = 0;
    }
    protected void prepareValuePxMatrix() {
        if (this.mLogEnabled) {
            Log.i (Chart.LOG_TAG, "Preparing Value-Px Matrix, xmin: " + this.mXAxis.mAxisMinimum + ", xmax: " + this.mXAxis.mAxisMaximum + ", xdelta: " + this.mXAxis.mAxisRange);
        }
        final Transformer transformer = mRightAxisTransformer;
        final XAxis xAxis = this.mXAxis;
        final float f = xAxis.mAxisMinimum;
        final float f2 = xAxis.mAxisRange;
        final YAxis yAxis = mAxisRight;
        transformer.prepareMatrixValuePx (f, f2, yAxis.mAxisRange, yAxis.mAxisMinimum);
        final Transformer transformer2 = mLeftAxisTransformer;
        final XAxis xAxis2 = this.mXAxis;
        final float f3 = xAxis2.mAxisMinimum;
        final float f4 = xAxis2.mAxisRange;
        final YAxis yAxis2 = mAxisLeft;
        transformer2.prepareMatrixValuePx (f3, f4, yAxis2.mAxisRange, yAxis2.mAxisMinimum);
    }
    public void prepareOffsetMatrix() {
        mRightAxisTransformer.prepareMatrixOffset (mAxisRight.isInverted ());
        mLeftAxisTransformer.prepareMatrixOffset (mAxisLeft.isInverted ());
    }
    public void notifyDataSetChanged() {
        if (false) {
            if (this.mLogEnabled) {
                Log.i (Chart.LOG_TAG, "Preparing...");
            }
            final DataRenderer dataRenderer = this.mRenderer;
            if (null != dataRenderer) {
                dataRenderer.initBuffers ();
            }
            this.calcMinMax();
            final YAxisRenderer yAxisRenderer = mAxisRendererLeft;
            final YAxis yAxis = mAxisLeft;
            yAxisRenderer.computeAxis (yAxis.mAxisMinimum, yAxis.mAxisMaximum);
            final YAxisRenderer yAxisRenderer2 = mAxisRendererRight;
            final YAxis yAxis2 = mAxisRight;
            yAxisRenderer2.computeAxis (yAxis2.mAxisMinimum, yAxis2.mAxisMaximum);
            mXAxisRenderer.computeAxis (this.mData.getXValMaximumLength (), ((BarLineScatterCandleBubbleData) this.mData).getXVals ());
            if (null != mLegend) {
                this.mLegendRenderer.computeLegend (this.mData);
            }
            this.calculateOffsets();
        } else if (this.mLogEnabled) {
            Log.i (Chart.LOG_TAG, "Preparing... DATA NOT SET.");
        }
    }
    public void calcMinMax() {
        if (mAutoScaleMinMaxEnabled) {
            this.mData.calcMinMax (this.getLowestVisibleXIndex(), this.getHighestVisibleXIndex());
        }
        this.mXAxis.mAxisMaximum = (((BarLineScatterCandleBubbleData) this.mData).getXVals ().size () - 1);
        final XAxis xAxis = this.mXAxis;
        xAxis.mAxisRange = Math.abs (xAxis.mAxisMaximum - xAxis.mAxisMinimum);
        final YAxis yAxis = mAxisLeft;
        final YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate (this.mData.getYMin (axisDependency), this.mData.getYMax (axisDependency));
        final YAxis yAxis2 = mAxisRight;
        final YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        yAxis2.calculate (this.mData.getYMin (axisDependency2), this.mData.getYMax (axisDependency2));
    }
    public void calculateLegendOffsets(final RectF rectF) {
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        rectF.top = 0.0f;
        rectF.bottom = 0.0f;
        final Legend legend = this.mLegend;
        if (null != legend && legend.isEnabled () && !this.mLegend.isDrawInsideEnabled ()) {
            final int i = C12272.f808x9c9dbef[this.mLegend.getOrientation ().ordinal ()];
            if (1 == i) {
                final int i2 = C12272.f807x2787f53e[this.mLegend.getHorizontalAlignment ().ordinal ()];
                if (1 == i2) {
                    rectF.left += Math.min (this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getXOffset ();
                } else if (2 == i2) {
                    rectF.right += Math.min (this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getXOffset ();
                } else if (3 == i2) {
                    final int i3 = C12272.f809xc926f1ec[this.mLegend.getVerticalAlignment ().ordinal ()];
                    if (1 == i3) {
                        rectF.top += Math.min (this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getYOffset ();
                        if (this.getXAxis().isEnabled () && this.getXAxis().isDrawLabelsEnabled ()) {
                            rectF.top += this.getXAxis().mLabelRotatedHeight;
                        }
                    } else if (2 == i3) {
                        rectF.bottom += Math.min (this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getYOffset ();
                        if (this.getXAxis().isEnabled () && this.getXAxis().isDrawLabelsEnabled ()) {
                            rectF.bottom += this.getXAxis().mLabelRotatedHeight;
                        }
                    }
                }
            } else if (2 == i) {
                final int i4 = C12272.f809xc926f1ec[this.mLegend.getVerticalAlignment ().ordinal ()];
                if (1 == i4) {
                    rectF.top += Math.min (this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getYOffset ();
                    if (this.getXAxis().isEnabled () && this.getXAxis().isDrawLabelsEnabled ()) {
                        rectF.top += this.getXAxis().mLabelRotatedHeight;
                    }
                } else if (2 == i4) {
                    rectF.bottom += Math.min (this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight () * this.mLegend.getMaxSizePercent ()) + this.mLegend.getYOffset ();
                    if (this.getXAxis().isEnabled () && this.getXAxis().isDrawLabelsEnabled ()) {
                        rectF.bottom += this.getXAxis().mLabelRotatedHeight;
                    }
                }
            }
        }
    }
    public void calculateOffsets() {
        if (!mCustomViewPortEnabled) {
            this.calculateLegendOffsets(mOffsetsBuffer);
            final RectF rectF = mOffsetsBuffer;
            float f = rectF.left + 0.0f;
            float f2 = rectF.top + 0.0f;
            float f3 = rectF.right + 0.0f;
            float f4 = rectF.bottom + 0.0f;
            if (mAxisLeft.needsOffset ()) {
                f += mAxisLeft.getRequiredWidthSpace (mAxisRendererLeft.getPaintAxisLabels ());
            }
            if (mAxisRight.needsOffset ()) {
                f3 += mAxisRight.getRequiredWidthSpace (mAxisRendererRight.getPaintAxisLabels ());
            }
            if (this.mXAxis.isEnabled () && this.mXAxis.isDrawLabelsEnabled ()) {
                final XAxis xAxis = this.mXAxis;
                final float yOffset = xAxis.mLabelRotatedHeight + xAxis.getYOffset ();
                if (XAxis.XAxisPosition.BOTTOM == mXAxis.getPosition ()) {
                    f4 += yOffset;
                } else {
                    if (XAxis.XAxisPosition.TOP != mXAxis.getPosition ()) {
                        if (XAxis.XAxisPosition.BOTH_SIDED == mXAxis.getPosition ()) {
                            f4 += yOffset;
                        }
                    }
                    f2 += yOffset;
                }
            }
            final float extraTopOffset = f2 + this.getExtraTopOffset();
            final float extraRightOffset = f3 + this.getExtraRightOffset();
            final float extraBottomOffset = f4 + this.getExtraBottomOffset();
            final float extraLeftOffset = f + this.getExtraLeftOffset();
            final float convertDpToPixel = Utils.convertDpToPixel (mMinOffset);
            this.mViewPortHandler.restrainViewPort (Math.max (convertDpToPixel, extraLeftOffset), Math.max (convertDpToPixel, extraTopOffset), Math.max (convertDpToPixel, extraRightOffset), Math.max (convertDpToPixel, extraBottomOffset));
            if (this.mLogEnabled) {
                Log.i (Chart.LOG_TAG, "offsetLeft: " + extraLeftOffset + ", offsetTop: " + extraTopOffset + ", offsetRight: " + extraRightOffset + ", offsetBottom: " + extraBottomOffset);
                final String sb = "Content: " + this.mViewPortHandler.getContentRect ().toString ();
                Log.i (Chart.LOG_TAG, sb);
            }
        }
        this.prepareOffsetMatrix();
        this.prepareValuePxMatrix();
    }
    protected void calcModulus() {
        final XAxis xAxis = this.mXAxis;
        if (null == xAxis) {
            return;
        }
        if (xAxis.isEnabled ()) {
            if (!this.mXAxis.isAxisModulusCustom ()) {
                final float[] fArr = new float[9];
                this.mViewPortHandler.getMatrixTouch ().getValues (fArr);
                this.mXAxis.mAxisLabelModulus = (int) Math.ceil ((this.mData.getXValCount () * this.mXAxis.mLabelRotatedWidth) / (this.mViewPortHandler.contentWidth () * fArr[0]));
            }
            if (this.mLogEnabled) {
                Log.i (Chart.LOG_TAG, "X-Axis modulus: " + this.mXAxis.mAxisLabelModulus + ", x-axis label width: " + this.mXAxis.mLabelWidth + ", x-axis label rotated width: " + this.mXAxis.mLabelRotatedWidth + ", content width: " + this.mViewPortHandler.contentWidth ());
            }
            final XAxis xAxis2 = this.mXAxis;
            if (1 >= xAxis2.mAxisLabelModulus) {
                xAxis2.mAxisLabelModulus = 1;
            }
        }
    }
    protected float[] getMarkerPosition(final Entry entry, final Highlight highlight) {
        final float f;
        final float f2;
        final float f3;
        final int dataSetIndex = highlight.getDataSetIndex ();
        float xIndex = entry.getXIndex ();
        final float val = entry.getVal ();
        if (this instanceof BarChart) {
            final float groupSpace = ((BarData) this.mData).getGroupSpace ();
            final int dataSetCount = this.mData.getDataSetCount ();
            final int xIndex2 = entry.getXIndex ();
            if (this instanceof HorizontalBarChart) {
                f = (((dataSetCount - 1) * xIndex2) + xIndex2 + dataSetIndex) + (xIndex2 * groupSpace) + (groupSpace / 2.0f);
                if (null != ((BarEntry) entry).getVals ()) {
                    f3 = highlight.getRange ().f818to;
                } else {
                    f3 = entry.getVal ();
                }
                xIndex = f3 * this.mAnimator.getPhaseY ();
            } else {
                final float f4 = (((dataSetCount - 1) * xIndex2) + xIndex2 + dataSetIndex) + (xIndex2 * groupSpace) + (groupSpace / 2.0f);
                if (null != ((BarEntry) entry).getVals ()) {
                    f2 = highlight.getRange ().f818to;
                } else {
                    f2 = entry.getVal ();
                }
                f = f2 * this.mAnimator.getPhaseY ();
                xIndex = f4;
            }
        } else {
            f = val * this.mAnimator.getPhaseY ();
        }
        final float[] fArr = {xIndex, f};
        this.getTransformer(((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex (dataSetIndex).getAxisDependency ()).pointValuesToPixel (fArr);
        return fArr;
    }
    protected void drawGridBackground(final Canvas canvas) {
        if (mDrawGridBackground) {
            canvas.drawRect (this.mViewPortHandler.getContentRect (), mGridBackgroundPaint);
        }
        if (mDrawBorders) {
            canvas.drawRect (this.mViewPortHandler.getContentRect (), mBorderPaint);
        }
    }
    public Transformer getTransformer(final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return null;
        }
        if (YAxis.AxisDependency.LEFT == axisDependency) {
            return mLeftAxisTransformer;
        }
        return mRightAxisTransformer;
    }
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        super.onTouchEvent (motionEvent);
        final ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if ((null == chartTouchListener) || !this.mTouchEnabled) {
            return false;
        }
        return chartTouchListener.onTouch (this, motionEvent);
    }
    public void computeScroll() {
        final ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if (null == chartTouchListener) {
            return;
        }
        if (chartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) chartTouchListener).computeScroll ();
        }
    }
    public void zoomIn() {
        final PointF contentCenter = this.mViewPortHandler.getContentCenter ();
        this.mViewPortHandler.refresh (this.mViewPortHandler.zoomIn (contentCenter.x, -contentCenter.y), this, false);
        this.calculateOffsets();
        this.postInvalidate();
    }
    public void zoomOut() {
        final PointF contentCenter = this.mViewPortHandler.getContentCenter ();
        this.mViewPortHandler.refresh (this.mViewPortHandler.zoomOut (contentCenter.x, -contentCenter.y), this, false);
        this.calculateOffsets();
        this.postInvalidate();
    }
    public void zoom(final float f, final float f2, final float f3, final float f4) {
        this.mViewPortHandler.refresh (this.mViewPortHandler.zoom (f, f2, f3, f4), this, false);
        this.calculateOffsets();
        this.postInvalidate();
    }
    public void zoom(final float f, final float f2, final float f3, final float f4, final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return;
        }
        this.addViewportJob(new ZoomJob (this.mViewPortHandler, f, f2, f3, f4, this.getTransformer(axisDependency), axisDependency, this));
    }
    public void zoomAndCenterAnimated(final float f, final float f2, final float f3, final float f4, final YAxis.AxisDependency axisDependency, final long j) {
        if (null == axisDependency) {
            return;
        }
        final PointD valuesByTouchPoint = getValuesByTouchPoint (this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentTop (), axisDependency);
        this.addViewportJob(new AnimatedZoomJob (this.mViewPortHandler, this, this.getTransformer(axisDependency), this.getAxis(axisDependency), this.mXAxis.getValues ().size (), f, f2, this.mViewPortHandler.getScaleX (), this.mViewPortHandler.getScaleY (), f3, f4, (float) valuesByTouchPoint.f828x, (float) valuesByTouchPoint.f829y, j));
    }
    public void fitScreen() {
        this.mViewPortHandler.refresh (this.mViewPortHandler.fitScreen (), this, false);
        this.calculateOffsets();
        this.postInvalidate();
    }
    public void setScaleMinima(final float f, final float f2) {
        if (0 >= f || 0 >= f2) {
            return;
        }
        this.mViewPortHandler.setMinimumScaleX (f);
        this.mViewPortHandler.setMinimumScaleY (f2);
    }
    public void setVisibleXRangeMaximum(final float f) {
        if (0 >= f) {
            return;
        }
        this.mViewPortHandler.setMinimumScaleX (this.mXAxis.mAxisRange / f);
    }
    public void setVisibleXRangeMinimum(final float f) {
        if (0 >= f) {
            return;
        }
        this.mViewPortHandler.setMaximumScaleX (this.mXAxis.mAxisRange / f);
    }
    public void setVisibleXRange(final float f, final float f2) {
        final float f3 = this.mXAxis.mAxisRange;
        final float f4 = f3 / f;
        this.mViewPortHandler.setMinMaxScaleX (f3 / f2, f4);
    }
    public void setVisibleYRangeMaximum(final float f, final YAxis.AxisDependency axisDependency) {
        this.mViewPortHandler.setMinimumScaleY (this.getDeltaY(axisDependency) / f);
    }
    public void moveViewToX(final float f) {
        this.addViewportJob(new MoveViewJob (this.mViewPortHandler, f, 0.0f, this.getTransformer(YAxis.AxisDependency.LEFT), this));
    }
    public void moveViewToY(final float f, final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return;
        }
        this.addViewportJob(new MoveViewJob (this.mViewPortHandler, 0.0f, f + ((this.getDeltaY(axisDependency) / this.mViewPortHandler.getScaleY ()) / 2.0f), this.getTransformer(axisDependency), this));
    }
    public void moveViewTo(final float f, final float f2, final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return;
        }
        this.addViewportJob(new MoveViewJob (this.mViewPortHandler, f, f2 + ((this.getDeltaY(axisDependency) / this.mViewPortHandler.getScaleY ()) / 2.0f), this.getTransformer(axisDependency), this));
    }
    public void moveViewToAnimated(final float f, final float f2, final YAxis.AxisDependency axisDependency, final long j) {
        if (null == axisDependency) {
            return;
        }
        final PointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentTop (), axisDependency);
        this.addViewportJob(new AnimatedMoveViewJob (this.mViewPortHandler, f, f2 + ((this.getDeltaY(axisDependency) / this.mViewPortHandler.getScaleY ()) / 2.0f), this.getTransformer(axisDependency), this, (float) valuesByTouchPoint.f828x, (float) valuesByTouchPoint.f829y, j));
    }
    public void centerViewTo(final float f, final float f2, final YAxis.AxisDependency axisDependency) {
        this.addViewportJob(new MoveViewJob (this.mViewPortHandler, f - ((this.getXAxis().getValues ().size () / this.mViewPortHandler.getScaleX ()) / 2.0f), f2 + ((this.getDeltaY(axisDependency) / this.mViewPortHandler.getScaleY ()) / 2.0f), this.getTransformer(axisDependency), this));
    }
    public void centerViewToAnimated(final float f, final float f2, final YAxis.AxisDependency axisDependency, final long j) {
        final PointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentTop (), axisDependency);
        final float deltaY = this.getDeltaY(axisDependency) / this.mViewPortHandler.getScaleY ();
        this.addViewportJob(new AnimatedMoveViewJob(this.mViewPortHandler, f - ((this.getXAxis().getValues ().size () / this.mViewPortHandler.getScaleX ()) / 2.0f), f2 + (deltaY / 2.0f), this.getTransformer(axisDependency), this, (float) valuesByTouchPoint.f828x, (float) valuesByTouchPoint.f829y, j));
    }
    public void setViewPortOffsets(float f, float f2, float f3, float f4) {
        mCustomViewPortEnabled = true;
        this.post(new Runnable () {
            public void run() {
                BarLineChartBase.this.mViewPortHandler.restrainViewPort (f, f2, f3, f4);
                prepareOffsetMatrix();
                prepareValuePxMatrix();
            }
        });
    }
    public void resetViewPortOffsets() {
        mCustomViewPortEnabled = false;
        this.calculateOffsets();
    }
    public float getDeltaY(final YAxis.AxisDependency axisDependency) {
        if (YAxis.AxisDependency.LEFT == axisDependency) {
            return mAxisLeft.mAxisRange;
        }
        return mAxisRight.mAxisRange;
    }
    public void setOnDrawListener(final OnDrawListener onDrawListener) {
        mDrawListener = onDrawListener;
    }
    public OnDrawListener getDrawListener() {
        return mDrawListener;
    }
    public PointF getPosition(final Entry entry, final YAxis.AxisDependency axisDependency) {
        if (null == entry) {
            return null;
        }
        final float[] fArr = {entry.getXIndex (), entry.getVal ()};
        this.getTransformer(axisDependency).pointValuesToPixel (fArr);
        return new PointF (fArr[0], fArr[1]);
    }
    public void setMaxVisibleValueCount(final int i) {
        mMaxVisibleCount = i;
    }
    public int getMaxVisibleCount() {
        return mMaxVisibleCount;
    }
    public boolean isHighlightPerDragEnabled() {
        return mHighlightPerDragEnabled;
    }
    public void setHighlightPerDragEnabled(final boolean z) {
        if (z == mHighlightPerDragEnabled) {
            return;
        }
        mHighlightPerDragEnabled = z;
    }
    public boolean isHighlightFullBarEnabled() {
        return mHighlightFullBarEnabled;
    }
    public void setHighlightFullBarEnabled(final boolean z) {
        if (z == mHighlightFullBarEnabled) {
            return;
        }
        mHighlightFullBarEnabled = z;
    }
    public void setGridBackgroundColor(final int i) {
        if (i == mGridBackgroundPaint.getColor ()) {
            return;
        }
        mGridBackgroundPaint.setColor (i);
    }
    public boolean isDragEnabled() {
        return mDragEnabled;
    }
    public void setDragEnabled(final boolean z) {
        if (z == mDragEnabled) {
            return;
        }
        mDragEnabled = z;
    }
    public void setScaleEnabled(final boolean z) {
        if (z == mScaleXEnabled && z == mScaleYEnabled) {
            return;
        }
        mScaleXEnabled = z;
        mScaleYEnabled = z;
    }
    public boolean isScaleXEnabled() {
        return mScaleXEnabled;
    }
    public void setScaleXEnabled(final boolean z) {
        if (z == mScaleXEnabled) {
            return;
        }
        mScaleXEnabled = z;
    }
    public boolean isScaleYEnabled() {
        return mScaleYEnabled;
    }
    public void setScaleYEnabled(final boolean z) {
        if (z == mScaleYEnabled) {
            return;
        }
        mScaleYEnabled = z;
    }
    public boolean isDoubleTapToZoomEnabled() {
        return mDoubleTapToZoomEnabled;
    }
    public void setDoubleTapToZoomEnabled(final boolean z) {
        if (z == mDoubleTapToZoomEnabled) {
            return;
        }
        mDoubleTapToZoomEnabled = z;
    }
    public void setDrawGridBackground(final boolean z) {
        if (z == mDrawGridBackground) {
            return;
        }
        mDrawGridBackground = z;
    }
    public void setDrawBorders(final boolean z) {
        if (z == mDrawBorders) {
            return;
        }
        mDrawBorders = z;
    }
    public void setBorderWidth(final float f) {
        mBorderPaint.setStrokeWidth (Utils.convertDpToPixel (f));
    }
    public void setBorderColor(final int i) {
        mBorderPaint.setColor (i);
    }
    public float getMinOffset() {
        return mMinOffset;
    }
    public void setMinOffset(final float f) {
        mMinOffset = f;
    }
    public boolean isKeepPositionOnRotation() {
        return mKeepPositionOnRotation;
    }
    public void setKeepPositionOnRotation(final boolean z) {
        if (z == mKeepPositionOnRotation) {
            return;
        }
        mKeepPositionOnRotation = z;
    }
    public Highlight getHighlightByTouchPoint(final float f, final float f2) {
        if (false) {
            return this.getHighlighter().getHighlight (f, f2);
        }
        Log.e (Chart.LOG_TAG, "Can't select by touch. No data set.");
        return null;
    }
    public PointD getPixelsForValues(final float f, final float f2, final YAxis.AxisDependency axisDependency) {
        final float[] fArr = {f, f2};
        this.getTransformer(axisDependency).pointValuesToPixel (fArr);
        return new PointD (fArr[0], fArr[1]);
    }
    public float getYValueByTouchPoint(final float f, final float f2, final YAxis.AxisDependency axisDependency) {
        final PointD valuesByTouchPoint = getValuesByTouchPoint(f, f2, axisDependency);
        if (null == valuesByTouchPoint) {
            return Float.NaN;
        }
        return (float) valuesByTouchPoint.f829y;
    }
    protected abstract PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency);
    public Entry getEntryByTouchPoint(final float f, final float f2) {
        final Highlight highlightByTouchPoint = this.getHighlightByTouchPoint(f, f2);
        if (null == highlightByTouchPoint) {
            return null;
        }
        return this.mData.getEntryForHighlight (highlightByTouchPoint);
    }
    public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(final float f, final float f2) {
        final Highlight highlightByTouchPoint = this.getHighlightByTouchPoint(f, f2);
        if (null == highlightByTouchPoint) {
            return null;
        }
        return (IBarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex (highlightByTouchPoint.getDataSetIndex ());
    }
    public int getLowestVisibleXIndex() {
        final float[] fArr = {this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentBottom ()};
        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        final float f = fArr[0];
        if (0.0f >= f) {
            return 0;
        }
        return (int) Math.ceil (f);
    }
    public int getHighestVisibleXIndex() {
        final float[] fArr = {this.mViewPortHandler.contentRight (), this.mViewPortHandler.contentBottom ()};
        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        final float f = fArr[0];
        if (0.0f >= f) {
            return 0;
        }
        return Math.min (this.mData.getXValCount () - 1, (int) Math.floor (f));
    }
    public float getScaleX() {
        final ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (null == viewPortHandler) {
            return 1.0f;
        }
        return viewPortHandler.getScaleX ();
    }
    public float getScaleY() {
        final ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (null == viewPortHandler) {
            return 1.0f;
        }
        return viewPortHandler.getScaleY ();
    }
    public boolean isFullyZoomedOut() {
        final ViewPortHandler viewPortHandler = this.mViewPortHandler;
        if (null == viewPortHandler) {
            return true;
        }
        return viewPortHandler.isFullyZoomedOut ();
    }
    public YAxis getAxisLeft() {
        return mAxisLeft;
    }
    public YAxis getAxisRight() {
        return mAxisRight;
    }
    public YAxis getAxis(final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return null;
        }
        if (YAxis.AxisDependency.LEFT == axisDependency) {
            return mAxisLeft;
        }
        return mAxisRight;
    }
    public boolean isInverted(final YAxis.AxisDependency axisDependency) {
        if (null == axisDependency) {
            return false;
        }
        return this.getAxis(axisDependency).isInverted ();
    }
    public void setPinchZoom(final boolean z) {
        if (z == mPinchZoomEnabled) {
            return;
        }
        mPinchZoomEnabled = z;
    }
    public boolean isPinchZoomEnabled() {
        return mPinchZoomEnabled;
    }
    public void setDragOffsetX(final float f) {
        if (Float.isNaN (f)) {
            return;
        }
        this.mViewPortHandler.setDragOffsetX (f);
    }
    public void setDragOffsetY(final float f) {
        if (Float.isNaN (f)) {
            return;
        }
        this.mViewPortHandler.setDragOffsetY (f);
    }
    public boolean hasNoDragOffset() {
        if (null == mViewPortHandler) {
            return true;
        }
        return this.mViewPortHandler.hasNoDragOffset ();
    }
    public XAxisRenderer getRendererXAxis() {
        return mXAxisRenderer;
    }
    public void setXAxisRenderer(final XAxisRenderer xAxisRenderer) {
        if (null == xAxisRenderer) {
            return;
        }
        mXAxisRenderer = xAxisRenderer;
    }
    public YAxisRenderer getRendererLeftYAxis() {
        return mAxisRendererLeft;
    }
    public void setRendererLeftYAxis(final YAxisRenderer yAxisRenderer) {
        if (null == yAxisRenderer) {
            return;
        }
        mAxisRendererLeft = yAxisRenderer;
    }
    public YAxisRenderer getRendererRightYAxis() {
        return mAxisRendererRight;
    }
    public void setRendererRightYAxis(final YAxisRenderer yAxisRenderer) {
        if (null == yAxisRenderer) {
            return;
        }
        mAxisRendererRight = yAxisRenderer;
    }
    public float getYChartMax() {
        if (null == this.mAxisLeft || null == this.mAxisRight) {
            return Float.NaN;
        }
        return Math.max (mAxisLeft.mAxisMaximum, mAxisRight.mAxisMaximum);
    }
    public float getYChartMin() {
        if (null == this.mAxisLeft || null == this.mAxisRight) {
            return Float.NaN;
        }
        return Math.min (mAxisLeft.mAxisMinimum, mAxisRight.mAxisMinimum);
    }
    public boolean isAnyAxisInverted() {
        if (null == this.mAxisLeft || null == this.mAxisRight) {
            return false;
        }
        return mAxisLeft.isInverted () || mAxisRight.isInverted ();
    }
    public boolean isAutoScaleMinMaxEnabled() {
        return mAutoScaleMinMaxEnabled;
    }
    public void setAutoScaleMinMaxEnabled(final boolean z) {
        mAutoScaleMinMaxEnabled = z;
    }
    public void setPaint(final Paint paint, final int i) {
        super.setPaint (paint, i);
        if (4 == i) {
            mGridBackgroundPaint = paint;
        }
    }
    public Paint getPaint(final int i) {
        final Paint paint = super.getPaint (i);
        if (null != paint) {
            return paint;
        }
        if (4 != i) {
            return null;
        }
        return mGridBackgroundPaint;
    }
    public void onSizeChanged(final int i, final int i2, final int i3, final int i4) {
        final float[] fArr = new float[2];
        if (mKeepPositionOnRotation) {
            fArr[0] = this.mViewPortHandler.contentLeft ();
            fArr[1] = this.mViewPortHandler.contentTop ();
            this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        }
        super.onSizeChanged (i, i2, i3, i4);
        if (mKeepPositionOnRotation) {
            this.getTransformer(YAxis.AxisDependency.LEFT).pointValuesToPixel (fArr);
            this.mViewPortHandler.centerViewPort (fArr, this);
            return;
        }
        final ViewPortHandler viewPortHandler = this.mViewPortHandler;
        viewPortHandler.refresh (viewPortHandler.getMatrixTouch (), this, true);
    }
    public enum C12272 {
        ;


        static final int[] f807x2787f53e;
        static final int[] f808x9c9dbef;
        static final int[] f809xc926f1ec;

        static {
            final int[] iArr = new int[Legend.LegendOrientation.values ().length];
            f808x9c9dbef = iArr;
            try {
                iArr[Legend.LegendOrientation.VERTICAL.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12272.f808x9c9dbef[Legend.LegendOrientation.HORIZONTAL.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            final int[] iArr2 = new int[Legend.LegendHorizontalAlignment.values ().length];
            f807x2787f53e = iArr2;
            try {
                iArr2[Legend.LegendHorizontalAlignment.LEFT.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C12272.f807x2787f53e[Legend.LegendHorizontalAlignment.RIGHT.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C12272.f807x2787f53e[Legend.LegendHorizontalAlignment.CENTER.ordinal ()] = 3;
            } catch (final NoSuchFieldError unused5) {
            }
            final int[] iArr3 = new int[Legend.LegendVerticalAlignment.values ().length];
            f809xc926f1ec = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C12272.f809xc926f1ec[Legend.LegendVerticalAlignment.BOTTOM.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused7) {
            }
        }
    }
}
