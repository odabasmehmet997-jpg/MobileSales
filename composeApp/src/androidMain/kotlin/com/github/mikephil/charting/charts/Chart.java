package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.animation.EasingFunction;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ChartAnimator mAnimator;
    protected ChartTouchListener mChartTouchListener;
    protected ValueFormatter mDefaultFormatter;
    protected Paint mDescPaint;
    protected Paint mDrawPaint;
    protected ChartHighlighter mHighlighter;
    protected Highlight[] mIndicesToHighlight;
    protected Paint mInfoPaint;
    protected Legend mLegend;
    protected LegendRenderer mLegendRenderer;
    protected MarkerView mMarkerView;
    protected DataRenderer mRenderer;
    protected OnChartValueSelectedListener mSelectionListener;
    protected ViewPortHandler mViewPortHandler;
    protected XAxis mXAxis;
    protected boolean mLogEnabled;
    protected T mData;
    protected boolean mHighLightPerTapEnabled = true;
    protected String mDescription = "Description";
    protected boolean mTouchEnabled = true;
    protected boolean mDrawMarkerViews = true;
    protected ArrayList<Runnable> mJobs = new ArrayList<> ();
    private PointF mDescriptionPosition;
    private OnChartGestureListener mGestureListener;
    private String mNoDataTextDescription;
    private boolean mDragDecelerationEnabled = true;
    private float mDragDecelerationFrictionCoef = 0.9f;
    private String mNoDataText = "No chart data available.";
    private float mExtraTopOffset;
    private float mExtraRightOffset;
    private float mExtraBottomOffset;
    private float mExtraLeftOffset;
    private boolean mOffsetsCalculated;
    private boolean mUnbind;
    protected Chart(final Context context) {
        super (context);
        this.init();
    }
    protected Chart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
        this.init();
    }
    protected Chart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
        this.init();
    }
    protected abstract void calcMinMax();
    protected abstract void calculateOffsets();
    protected abstract float[] getMarkerPosition(Entry entry, Highlight highlight);
    public abstract void notifyDataSetChanged();
    public void init() {
        this.setWillNotDraw(false);
        mAnimator = new ChartAnimator (new ValueAnimator.AnimatorUpdateListener () {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                Chart.this.postInvalidate();
            }
        });
        Utils.init (this.getContext());
        mDefaultFormatter = new DefaultValueFormatter (1);
        mViewPortHandler = new ViewPortHandler ();
        final Legend legend = new Legend ();
        mLegend = legend;
        mLegendRenderer = new LegendRenderer (mViewPortHandler, legend);
        mXAxis = new XAxis ();
        final Paint paint = new Paint (1);
        mDescPaint = paint;
        paint.setColor (ViewCompat.MEASURED_STATE_MASK);
        mDescPaint.setTextAlign (Paint.Align.RIGHT);
        mDescPaint.setTextSize (Utils.convertDpToPixel (9.0f));
        final Paint paint2 = new Paint (1);
        mInfoPaint = paint2;
        paint2.setColor (Color.rgb (247, 189, 51));
        mInfoPaint.setTextAlign (Paint.Align.CENTER);
        mInfoPaint.setTextSize (Utils.convertDpToPixel (12.0f));
        mDrawPaint = new Paint (4);
        if (mLogEnabled) {
            Log.i ("", "Chart.init()");
        }
    }
    public void clear() {
        mData = null;
        mIndicesToHighlight = null;
        this.invalidate();
    }
    public void clearValues() {
        mData.clearValues ();
        this.invalidate();
    }
    public boolean isEmpty() {
        final T t = mData;
        return null == t || 0 >= t.getYValCount ();
    }
    protected void calculateFormatter(final float f, final float f2) {
        final float f3;
        final T t = mData;
        if (null == t || 2 > t.getXValCount ()) {
            f3 = Math.max (Math.abs (f), Math.abs (f2));
        } else {
            f3 = Math.abs (f2 - f);
        }
        mDefaultFormatter = new DefaultValueFormatter (Utils.getDecimals (f3));
    }
    public void onDraw(final Canvas canvas) {
        if (null == this.mData) {
            final boolean isEmpty = TextUtils.isEmpty (mNoDataText);
            final boolean isEmpty2 = TextUtils.isEmpty (mNoDataTextDescription);
            float f = 0.0f;
            final float calcTextHeight = !isEmpty ? Utils.calcTextHeight (mInfoPaint, mNoDataText) : 0.0f;
            final float calcTextHeight2 = !isEmpty2 ? Utils.calcTextHeight (mInfoPaint, mNoDataTextDescription) : 0.0f;
            if (!isEmpty && !isEmpty2) {
                f = mInfoPaint.getFontSpacing () - calcTextHeight;
            }
            float height = ((this.getHeight() - ((calcTextHeight + f) + calcTextHeight2)) / 2.0f) + calcTextHeight;
            if (!isEmpty) {
                canvas.drawText (mNoDataText, (this.getWidth() / 2), height, mInfoPaint);
                if (!isEmpty2) {
                    height = height + calcTextHeight + f;
                }
            }
            if (!isEmpty2) {
                canvas.drawText (mNoDataTextDescription, (this.getWidth() / 2), height, mInfoPaint);
            }
        } else if (!mOffsetsCalculated) {
            this.calculateOffsets();
            mOffsetsCalculated = true;
        }
    }
    public void drawDescription(final Canvas canvas) {
        if (!"".equals (this.mDescription)) {
            final PointF pointF = mDescriptionPosition;
            if (null == pointF) {
                canvas.drawText (mDescription, (this.getWidth() - mViewPortHandler.offsetRight ()) - 10.0f, (this.getHeight() - mViewPortHandler.offsetBottom ()) - 10.0f, mDescPaint);
            } else {
                canvas.drawText (mDescription, pointF.x, pointF.y, mDescPaint);
            }
        }
    }
    public Highlight[] getHighlighted() {
        return mIndicesToHighlight;
    }
    public boolean isHighlightPerTapEnabled() {
        return mHighLightPerTapEnabled;
    }
    public void setHighlightPerTapEnabled(final boolean z) {
        mHighLightPerTapEnabled = z;
    }
    public boolean valuesToHighlight() {
        final Highlight[] highlightArr = mIndicesToHighlight;
        return null != highlightArr && 0 < highlightArr.length && null != highlightArr[0];
    }
    public void highlightValues(final Highlight[] highlightArr) {
        final Highlight highlight;
        mIndicesToHighlight = highlightArr;
        if (null == highlightArr || 0 >= highlightArr.length || null == (highlight = highlightArr[0])) {
            mChartTouchListener.setLastHighlighted (null);
        } else {
            mChartTouchListener.setLastHighlighted (highlight);
        }
        this.invalidate();
    }
    public void highlightValue(final int i, final int i2) {
        this.highlightValue(i, i2, true);
    }
    public void highlightValue(final int i, final int i2, final boolean z) {
        if (0 > i || 0 > i2 || i >= mData.getXValCount () || i2 >= mData.getDataSetCount ()) {
            this.highlightValue(null, z);
        } else {
            this.highlightValue(new Highlight (i, i2), z);
        }
    }
    public void highlightValue(final Highlight highlight) {
        this.highlightValue(highlight, false);
    }
    public void highlightValue(Highlight highlight, final boolean z) {
        Entry entry = null;
        if (null == highlight) {
            mIndicesToHighlight = null;
        } else {
            if (mLogEnabled) {
                Log.i (Chart.LOG_TAG, "Highlighted: " + highlight);
            }
            final Entry entryForHighlight = mData.getEntryForHighlight (highlight);
            if (null == entryForHighlight) {
                mIndicesToHighlight = null;
                highlight = null;
            } else {
                if ((this instanceof BarLineChartBase) && ((BarLineChartBase) this).isHighlightFullBarEnabled ()) {
                    highlight = new Highlight (highlight.getXIndex (), Float.NaN, -1, -1, -1);
                }
                mIndicesToHighlight = new Highlight[]{highlight};
            }
            entry = entryForHighlight;
        }
        if (z && null != this.mSelectionListener) {
            if (!this.valuesToHighlight()) {
                mSelectionListener.onNothingSelected ();
            } else {
                mSelectionListener.onValueSelected (entry, highlight.getDataSetIndex (), highlight);
            }
        }
        this.invalidate();
    }
    public void highlightTouch(final Highlight highlight) {
        this.highlightValue(highlight, true);
    }
    public void setOnTouchListener(final ChartTouchListener chartTouchListener) {
        mChartTouchListener = chartTouchListener;
    }
    public void drawMarkers(final Canvas canvas) {
        float f;
        Entry entryForHighlight;
        float f2;
        if (null != this.mMarkerView && mDrawMarkerViews && this.valuesToHighlight()) {
            int i = 0;
            while (true) {
                final Highlight[] highlightArr = mIndicesToHighlight;
                if (i < highlightArr.length) {
                    final Highlight highlight = highlightArr[i];
                    final int xIndex = highlight.getXIndex ();
                    highlight.getDataSetIndex ();
                    final XAxis xAxis = mXAxis;
                    if (null != xAxis) {
                        f = xAxis.mAxisRange;
                    } else {
                        final T t = mData;
                        if (null == t) {
                            f2 = 0.0f;
                        } else {
                            f2 = t.getXValCount ();
                        }
                        f = f2 - 1.0f;
                    }
                    final float f3 = xIndex;
                    if (f3 <= f && f3 <= f * mAnimator.getPhaseX () && null != (entryForHighlight = this.mData.getEntryForHighlight (this.mIndicesToHighlight[i])) && entryForHighlight.getXIndex () == mIndicesToHighlight[i].getXIndex ()) {
                        final float[] markerPosition = this.getMarkerPosition(entryForHighlight, highlight);
                        if (mViewPortHandler.isInBounds (markerPosition[0], markerPosition[1])) {
                            mMarkerView.refreshContent (entryForHighlight, highlight);
                            mMarkerView.measure (MeasureSpec.makeMeasureSpec (0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec (0, MeasureSpec.UNSPECIFIED));
                            final MarkerView markerView = mMarkerView;
                            markerView.layout (0, 0, markerView.getMeasuredWidth (), mMarkerView.getMeasuredHeight ());
                            if (0.0f >= markerPosition[1] - this.mMarkerView.getHeight ()) {
                                final float f4 = markerPosition[1];
                                mMarkerView.draw (canvas, markerPosition[0], f4 + (mMarkerView.getHeight () - f4));
                            } else {
                                mMarkerView.draw (canvas, markerPosition[0], markerPosition[1]);
                            }
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
    public ChartAnimator getAnimator() {
        return mAnimator;
    }
    public boolean isDragDecelerationEnabled() {
        return mDragDecelerationEnabled;
    }
    public void setDragDecelerationEnabled(final boolean z) {
        mDragDecelerationEnabled = z;
    }
    public float getDragDecelerationFrictionCoef() {
        return mDragDecelerationFrictionCoef;
    }
    public void setDragDecelerationFrictionCoef(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f >= 1.0f) {
            f = 0.999f;
        }
        mDragDecelerationFrictionCoef = f;
    }
    public void animateY(final int i, final EasingFunction easingFunction) {
        mAnimator.animateY (i, easingFunction);
    }
    public void animateXY(final int i, final int i2, final Easing.EasingOption easingOption, final Easing.EasingOption easingOption2) {
        mAnimator.animateXY (i, i2, easingOption, easingOption2);
    }
    public void animateX(final int i, final Easing.EasingOption easingOption) {
        mAnimator.animateX (i, easingOption);
    }
    public void animateY(final int i, final Easing.EasingOption easingOption) {
        mAnimator.animateY (i, easingOption);
    }
    public void animateX(final int i) {
        mAnimator.animateX (i);
    }
    public void animateY(final int i) {
        mAnimator.animateY (i);
    }
    public void animateXY(final int i, final int i2) {
        mAnimator.animateXY (i, i2);
    }
    public XAxis getXAxis() {
        return mXAxis;
    }
    public ValueFormatter getDefaultValueFormatter() {
        return mDefaultFormatter;
    }
    public void setOnChartValueSelectedListener(final OnChartValueSelectedListener onChartValueSelectedListener) {
        mSelectionListener = onChartValueSelectedListener;
    }
    public OnChartGestureListener getOnChartGestureListener() {
        return mGestureListener;
    }
    public void setOnChartGestureListener(final OnChartGestureListener onChartGestureListener) {
        mGestureListener = onChartGestureListener;
    }
    public float getYMax() {
        return mData.getYMax ();
    }
    public float getYMin() {
        return mData.getYMin ();
    }
    public float getXChartMax() {
        return mXAxis.mAxisMaximum;
    }
    public float getXChartMin() {
        return mXAxis.mAxisMinimum;
    }
    public int getXValCount() {
        return mData.getXValCount ();
    }
    public int getValueCount() {
        return mData.getYValCount ();
    }
    public PointF getCenter() {
        return new PointF (this.getWidth() / 2.0f, this.getHeight() / 2.0f);
    }
    public PointF getCenterOffsets() {
        return mViewPortHandler.getContentCenter ();
    }
    public void setDescription(String str) {
        if (null == str) {
            str = "";
        }
        mDescription = str;
    }
    public void setDescriptionPosition(final float f, final float f2) {
        mDescriptionPosition = new PointF (f, f2);
    }
    public void setDescriptionTypeface(final Typeface typeface) {
        mDescPaint.setTypeface (typeface);
    }
    public void setDescriptionTextSize(float textSize) {
        if (16.0f < textSize) {
            textSize = 16.0f;
        }
        if (6.0f > textSize) {
            textSize = 6.0f;
        }
        mDescPaint.setTextSize (Utils.convertDpToPixel (textSize));
    }
    public void setDescriptionColor(final int color) {
        mDescPaint.setColor (color);
    }
    public void setExtraOffsets(final float left, final float top, final float right, final float bottom) {
        this.setExtraLeftOffset(left);
        this.setExtraTopOffset(top);
        this.setExtraRightOffset(right);
        this.setExtraBottomOffset(bottom);
    }
    public float getExtraTopOffset() {
        return mExtraTopOffset;
    }
    public void setExtraTopOffset(final float topOffset) {
        mExtraTopOffset = Utils.convertDpToPixel (topOffset);
    }
    public float getExtraRightOffset() {
        return mExtraRightOffset;
    }
    public void setExtraRightOffset(final float rightOffset) {
        mExtraRightOffset = Utils.convertDpToPixel (rightOffset);
    }
    public float getExtraBottomOffset() {
        return mExtraBottomOffset;
    }
    public void setExtraBottomOffset(final float bottomOffset) {
        mExtraBottomOffset = Utils.convertDpToPixel (bottomOffset);
    }
    public float getExtraLeftOffset() {
        return mExtraLeftOffset;
    }
    public void setExtraLeftOffset(final float leftOffset) {
        mExtraLeftOffset = Utils.convertDpToPixel (leftOffset);
    }
    public boolean isLogEnabled() {
        return mLogEnabled;
    }
    public void setLogEnabled(final boolean logEnabled) {
        mLogEnabled = logEnabled;
    }
    public void setNoDataText(final String noDataText) {
        mNoDataText = noDataText;
    }
    public void setNoDataTextDescription(final String noDataTextDescription) {
        mNoDataTextDescription = noDataTextDescription;
    }
    public void setTouchEnabled(final boolean touchEnabled) {
        mTouchEnabled = touchEnabled;
    }
    public MarkerView getMarkerView() {
        return mMarkerView;
    }
    public void setMarkerView(final MarkerView markerView) {
        mMarkerView = markerView;
    }
    public Legend getLegend() {
        return mLegend;
    }
    public LegendRenderer getLegendRenderer() {
        return mLegendRenderer;
    }
    public RectF getContentRect() {
        return mViewPortHandler.getContentRect ();
    }
    public void disableScroll() {
        final ViewParent parent = this.getParent();
        if (null != parent) {
            parent.requestDisallowInterceptTouchEvent (true);
        }
    }
    public void enableScroll() {
        final ViewParent parent = this.getParent();
        if (null != parent) {
            parent.requestDisallowInterceptTouchEvent (false);
        }
    }
    public void setPaint(final Paint paint, final int i) {
        if (7 == i) {
            mInfoPaint = paint;
        } else if (11 == i) {
            mDescPaint = paint;
        }
    }
    public Paint getPaint(final int i) {
        if (7 == i) {
            return mInfoPaint;
        }
        if (11 != i) {
            return null;
        }
        return mDescPaint;
    }
    public boolean isDrawMarkerViewEnabled() {
        return mDrawMarkerViews;
    }
    public void setDrawMarkerViews(final boolean z) {
        mDrawMarkerViews = z;
    }
    public String getXValue(final int i) {
        final T t = mData;
        if (null == t || t.getXValCount () <= i) {
            return null;
        }
        return mData.getXVals ().get (i);
    }
    public List<Entry> getEntriesAtIndex(final int i) {
        final ArrayList arrayList = new ArrayList ();
        for (int i2 = 0; i2 < mData.getDataSetCount (); i2++) {
            final Entry entryForXIndex = mData.getDataSetByIndex (i2).getEntryForXIndex (i);
            if (null != entryForXIndex) {
                arrayList.add (entryForXIndex);
            }
        }
        return arrayList;
    }
    public T getData() {
        return mData;
    }
    public void setData(final T t) {
        if (null == t) {
            Log.e (Chart.LOG_TAG, "Cannot set data for chart. Provided data object is null.");
            return;
        }
        mOffsetsCalculated = false;
        mData = t;
        this.calculateFormatter(t.getYMin (), t.getYMax ());
        for (final IDataSet iDataSet : mData.getDataSets ()) {
            if (Utils.needsDefaultFormatter (iDataSet.getValueFormatter ())) {
                iDataSet.setValueFormatter (mDefaultFormatter);
            }
        }
        this.notifyDataSetChanged();
        if (mLogEnabled) {
            Log.i (Chart.LOG_TAG, "Data is set.");
        }
    }
    public ViewPortHandler getViewPortHandler() {
        return mViewPortHandler;
    }
    public DataRenderer getRenderer() {
        return mRenderer;
    }
    public void setRenderer(final DataRenderer dataRenderer) {
        if (null != dataRenderer) {
            mRenderer = dataRenderer;
        }
    }
    public ChartHighlighter getHighlighter() {
        return mHighlighter;
    }
    public void setHighlighter(final ChartHighlighter chartHighlighter) {
        mHighlighter = chartHighlighter;
    }
    public PointF getCenterOfView() {
        return this.getCenter();
    }
    public Bitmap getChartBitmap() {
        final Bitmap createBitmap = Bitmap.createBitmap (this.getWidth(), this.getHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas (createBitmap);
        final Drawable background = this.getBackground();
        if (null != background) {
            background.draw (canvas);
        } else {
            canvas.drawColor (-1);
        }
        this.draw(canvas);
        return createBitmap;
    }
    public boolean saveToPath(final String str, final String str2) {
        final Bitmap chartBitmap = this.getChartBitmap();
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream (Environment.getExternalStorageDirectory ().getPath () + str2 + "/" + str + ".png");
            chartBitmap.compress (Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close ();
            return true;
        } catch (final Exception e) {
            e.printStackTrace ();
            return false;
        }
    }
    public boolean saveToGallery(String str, final String str2, final String str3, final Bitmap.CompressFormat compressFormat, int i) {
        final String str4;
        if (0 > i || 100 < i) {
            i = 50;
        }
        final long currentTimeMillis = System.currentTimeMillis ();
        final File externalStorageDirectory = Environment.getExternalStorageDirectory ();
        final File file = new File (externalStorageDirectory.getAbsolutePath () + "/DCIM/" + str2);
        if (!file.exists () && !file.mkdirs ()) {
            return false;
        }
        final int i2 = C12292.SwitchMapandroidgraphicsBitmapCompressFormat[compressFormat.ordinal ()];
        if (1 == i2) {
            str4 = "image/png";
            if (!str.endsWith (".png")) {
                str = str + ".png";
            }
        } else if (2 != i2) {
            str4 = "image/jpeg";
            if (!str.endsWith (".jpg") && !str.endsWith (".jpeg")) {
                str = str + ".jpg";
            }
        } else {
            str4 = "image/webp";
            if (!str.endsWith (".webp")) {
                str = str + ".webp";
            }
        }
        final String str5 = file.getAbsolutePath () + "/" + str;
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream (str5);

            this.getChartBitmap().compress (Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush ();
            fileOutputStream.close ();
            final long length = new File (str5).length ();
            final ContentValues contentValues = new ContentValues (8);
            contentValues.put ("title", str);
            contentValues.put ("_display_name", str);
            contentValues.put ("date_added", Long.valueOf (currentTimeMillis));
            contentValues.put ("mime_type", str4);
            contentValues.put ("description", str3);
            contentValues.put ("orientation", 0);
            contentValues.put ("_data", str5);
            contentValues.put ("_size", Long.valueOf (length));
            return null != this.getContext().getContentResolver ().insert (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (final IOException e) {
            e.printStackTrace ();
            return false;
        }
    }
    public boolean saveToGallery(final String str, final int i) {
        return this.saveToGallery(str, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.JPEG, i);
    }
    public void removeViewportJob(final Runnable runnable) {
        mJobs.remove (runnable);
    }
    public void clearAllViewportJobs() {
        mJobs.clear ();
    }
    public void addViewportJob(final Runnable runnable) {
        if (mViewPortHandler.hasChartDimens ()) {
            this.post(runnable);
        } else {
            mJobs.add (runnable);
        }
    }
    public ArrayList<Runnable> getJobs() {
        return mJobs;
    }
    protected void onLayout(final boolean z, final int i, final int i2, final int i3, final int i4) {
        for (int i5 = 0; i5 < this.getChildCount(); i5++) {
            this.getChildAt(i5).layout (i, i2, i3, i4);
        }
    }
    protected void onMeasure(final int i, final int i2) {
        super.onMeasure (i, i2);
        final int convertDpToPixel = (int) Utils.convertDpToPixel (50.0f);
        this.setMeasuredDimension(Math.max (this.getSuggestedMinimumWidth(), View.resolveSize(convertDpToPixel, i)), Math.max (this.getSuggestedMinimumHeight(), View.resolveSize(convertDpToPixel, i2)));
    }
    public void onSizeChanged(final int i, final int i2, final int i3, final int i4) {
        if (mLogEnabled) {
            Log.i (Chart.LOG_TAG, "OnSizeChanged()");
        }
        if (0 < i && 0 < i2 && 10000 > i && 10000 > i2) {
            mViewPortHandler.setChartDimens (i, i2);
            if (mLogEnabled) {
                Log.i (Chart.LOG_TAG, "Setting chart dimens, width: " + i + ", height: " + i2);
            }
            final Iterator<Runnable> it = mJobs.iterator ();
            while (it.hasNext ()) {
                this.post(it.next ());
            }
            mJobs.clear ();
        }
        this.notifyDataSetChanged();
        super.onSizeChanged (i, i2, i3, i4);
    }
    public void setHardwareAccelerationEnabled(final boolean z) {
        if (z) {
            this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow ();
        if (mUnbind) {
            this.unbindDrawables(this);
        }
    }
    private void unbindDrawables(final View view) {
        if (null != view.getBackground ()) {
            view.getBackground ().setCallback (null);
        }
        if (view instanceof final ViewGroup viewGroup) {
            int i = 0;
            while (true) {
                if (i < viewGroup.getChildCount ()) {
                    this.unbindDrawables(viewGroup.getChildAt (i));
                    i++;
                } else {
                    viewGroup.removeAllViews ();
                    return;
                }
            }
        }
    }
    public void setUnbindEnabled(final boolean z) {
        mUnbind = z;
    }
    public enum C12292 {
        ;
        static final int[] SwitchMapandroidgraphicsBitmapCompressFormat;

        static {
            final int[] iArr = new int[Bitmap.CompressFormat.values ().length];
            SwitchMapandroidgraphicsBitmapCompressFormat = iArr;
            try {
                iArr[Bitmap.CompressFormat.PNG.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12292.SwitchMapandroidgraphicsBitmapCompressFormat[Bitmap.CompressFormat.WEBP.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C12292.SwitchMapandroidgraphicsBitmapCompressFormat[Bitmap.CompressFormat.JPEG.ordinal ()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }
}
