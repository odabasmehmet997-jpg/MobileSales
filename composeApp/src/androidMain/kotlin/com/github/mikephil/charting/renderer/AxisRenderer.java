package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AxisRenderer extends Renderer {
    protected Paint mAxisLabelPaint = new Paint (1);
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    protected AxisRenderer(ViewPortHandler viewPortHandler, Transformer transformer) {
        super (viewPortHandler);
        this.mTrans = transformer;
        Paint paint = new Paint ();
        this.mGridPaint = paint;
        paint.setColor (-7829368);
        this.mGridPaint.setStrokeWidth (1.0f);
        Paint paint2 = this.mGridPaint;
        final Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle (style);
        this.mGridPaint.setAlpha (90);
        Paint paint3 = new Paint ();
        this.mAxisLinePaint = paint3;
        paint3.setColor (ViewCompat.MEASURED_STATE_MASK);
        this.mAxisLinePaint.setStrokeWidth (1.0f);
        this.mAxisLinePaint.setStyle (style);
        Paint paint4 = new Paint (1);
        this.mLimitLinePaint = paint4;
        paint4.setStyle (style);
    }

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }
}
