package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class LineScatterCandleRadarRenderer extends DataRenderer {
    private final Path mHighlightLinePath = new Path ();

    protected LineScatterCandleRadarRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
    }

    public void drawHighlightLines(Canvas canvas, float[] fArr, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet) {
        mHighlightPaint.setColor (iLineScatterCandleRadarDataSet.getHighLightColor ());
        mHighlightPaint.setStrokeWidth (iLineScatterCandleRadarDataSet.getHighlightLineWidth ());
        mHighlightPaint.setPathEffect (iLineScatterCandleRadarDataSet.getDashPathEffectHighlight ());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled ()) {
            this.mHighlightLinePath.reset ();
            this.mHighlightLinePath.moveTo (fArr[0], mViewPortHandler.contentTop ());
            this.mHighlightLinePath.lineTo (fArr[0], mViewPortHandler.contentBottom ());
            canvas.drawPath (this.mHighlightLinePath, mHighlightPaint);
        }
        if (iLineScatterCandleRadarDataSet.isHorizontalHighlightIndicatorEnabled ()) {
            this.mHighlightLinePath.reset ();
            this.mHighlightLinePath.moveTo (mViewPortHandler.contentLeft (), fArr[1]);
            this.mHighlightLinePath.lineTo (mViewPortHandler.contentRight (), fArr[1]);
            canvas.drawPath (this.mHighlightLinePath, mHighlightPaint);
        }
    }
}
