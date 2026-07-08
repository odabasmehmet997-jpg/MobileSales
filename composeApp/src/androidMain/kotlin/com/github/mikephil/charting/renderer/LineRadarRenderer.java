package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class LineRadarRenderer extends LineScatterCandleRadarRenderer {
    protected LineRadarRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
    }

    public void drawFilledPath(Canvas canvas, Path path, Drawable drawable) {
        if (clipPathSupported()) {
            canvas.save ();
            canvas.clipPath (path);
            drawable.setBounds ((int) mViewPortHandler.contentLeft (), (int) mViewPortHandler.contentTop (), (int) mViewPortHandler.contentRight (), (int) mViewPortHandler.contentBottom ());
            drawable.draw (canvas);
            canvas.restore ();
            return;
        }
        throw new RuntimeException ("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + Utils.getSDKInt () + ".");
    }

    public void drawFilledPath(Canvas canvas, Path path, int i, int i2) {
        int i3 = (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
        if (clipPathSupported()) {
            canvas.save ();
            canvas.clipPath (path);
            canvas.drawColor (i3);
            canvas.restore ();
            return;
        }
        Paint.Style style = mRenderPaint.getStyle ();
        int color = mRenderPaint.getColor ();
        mRenderPaint.setStyle (Paint.Style.FILL);
        mRenderPaint.setColor (i3);
        canvas.drawPath (path, mRenderPaint);
        mRenderPaint.setColor (color);
        mRenderPaint.setStyle (style);
    }

    private boolean clipPathSupported() {
        return 18 <= Utils.getSDKInt ();
    }
}
