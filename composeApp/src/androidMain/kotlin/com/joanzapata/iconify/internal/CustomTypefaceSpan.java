package com.joanzapata.iconify.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import com.joanzapata.iconify.Icon;

public class CustomTypefaceSpan extends ReplacementSpan {
    private static final float BASELINE_RATIO = 0.14285715f;
    private static final int ROTATION_DURATION = 2000;
    private static final Rect TEXT_BOUNDS = new Rect ();
    private static final Paint LOCAL_PAINT = new Paint ();
    private final boolean baselineAligned;
    private final String icon;
    private final int iconColor;
    private final float iconSizePx;
    private final float iconSizeRatio;
    private final boolean rotate;
    private final long rotationStartTime = System.currentTimeMillis ();
    private final Typeface type;
    private androidx.constraintlayout.core.motion.utils.Rect r7;
    public CustomTypefaceSpan(Icon icon, Typeface typeface, float f2, float f3, int i2, boolean z, boolean z2) {
      this.rotate = z;
      this.baselineAligned = z2;
        this.icon = String.valueOf (icon.character ());
      this.type = typeface;
      this.iconSizePx = f2;
      this.iconSizeRatio = f3;
      this.iconColor = i2;
    }
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        Paint paint2 = LOCAL_PAINT;
        paint2.set (paint);
        applyCustomTypeFace(paint2, this.type);
        String str = this.icon;
        Rect rect = TEXT_BOUNDS;
        paint2.getTextBounds (str, 0, 1, rect);
        if (null != fontMetricsInt) {
            fontMetricsInt.descent = (int) (rect.height () * (this.baselineAligned ? 0.0f : BASELINE_RATIO));
            int height = rect.height ();
            int i4 = fontMetricsInt.descent;
            int i5 = -(height - i4);
            fontMetricsInt.ascent = i5;
            fontMetricsInt.top = i5;
            fontMetricsInt.bottom = i4;
        }
        return rect.width ();
    }
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        applyCustomTypeFace(paint, this.type);
        paint.getTextBounds (this.icon, 0, 1, TEXT_BOUNDS);
        canvas.save ();
        float f3 = this.baselineAligned ? 0.0f : BASELINE_RATIO;
        if (this.rotate) {
            canvas.rotate (((System.currentTimeMillis () - this.rotationStartTime) / 2000.0f) * 360.0f,
                        (this.r7.width () / 2.0f) + f2, (i5 - (this.r7.height () / 2.0f)) + (this.r7.height () * f3));
        }
        canvas.drawText (this.icon, f2 - this.r7.left, (i5 - this.r7.bottom) + (this.r7.height () * f3), paint);
        canvas.restore ();
    }
    public boolean isAnimated() {
        return this.rotate;
    }
    private void applyCustomTypeFace(Paint paint, Typeface typeface) {
        paint.setFakeBoldText (false);
        paint.setTextSkewX (0.0f);
        paint.setTypeface (typeface);
        if (this.rotate) {
            paint.clearShadowLayer ();
        }
        if (0.0f < iconSizeRatio) {
            paint.setTextSize (paint.getTextSize () * this.iconSizeRatio);
        } else {
            float f2 = this.iconSizePx;
            if (0.0f < f2) {
                paint.setTextSize (f2);
            }
        }
        int i2 = this.iconColor;
        if (Integer.MAX_VALUE > i2) {
            paint.setColor (i2);
        }
    }
}
