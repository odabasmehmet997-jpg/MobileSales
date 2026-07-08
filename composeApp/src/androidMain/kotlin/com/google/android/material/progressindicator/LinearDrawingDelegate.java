package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.collection.ScatterMapKt;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.color.utilities.Contrast;
import com.google.android.material.progressindicator.DrawingDelegate;
import com.google.android.material.transformation.FabTransformationScrimBehavior;

/*  INFO: loaded from: classes2.dex */
final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    private float displayedCornerRadius;
    private float displayedTrackThickness;

    @FloatRange(from = 0.0d, to = Contrast.RATIO_MIN)
    private float totalTrackLengthFraction;
    private float trackLength;
    private boolean useStrokeCap;

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredWidth() {
        return -1;
    }

    LinearDrawingDelegate(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.trackLength = 300.0f;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredHeight() {
        return this.spec.trackThickness;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void adjustCanvas(@NonNull Canvas canvas, @NonNull Rect rect, @FloatRange(from = 0.0d, to = Contrast.RATIO_MIN) float f2, boolean z, boolean z2) {
        this.trackLength = rect.width();
        float f3 = this.spec.trackThickness;
        canvas.translate(rect.left + (rect.width() / 2.0f), rect.top + (rect.height() / 2.0f) + Math.max(0.0f, (rect.height() - f3) / 2.0f));
        if (this.spec.drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        float f4 = this.trackLength / 2.0f;
        float f5 = f3 / 2.0f;
        canvas.clipRect(-f4, -f5, f4, f5);
        S s = this.spec;
        this.useStrokeCap = ((LinearProgressIndicatorSpec) s).trackThickness / 2 == ((LinearProgressIndicatorSpec) s).trackCornerRadius;
        this.displayedTrackThickness = ((LinearProgressIndicatorSpec) s).trackThickness * f2;
        this.displayedCornerRadius = Math.min(((LinearProgressIndicatorSpec) s).trackThickness / 2, ((LinearProgressIndicatorSpec) s).trackCornerRadius) * f2;
        if (z || z2) {
            if ((z && this.spec.showAnimationBehavior == 2) || (z2 && this.spec.hideAnimationBehavior == 1)) {
                canvas.scale(1.0f, -1.0f);
            }
            if (z || (z2 && this.spec.hideAnimationBehavior != 3)) {
                canvas.translate(0.0f, (this.spec.trackThickness * (1.0f - f2)) / 2.0f);
            }
        }
        if (z2 && this.spec.hideAnimationBehavior == 3) {
            this.totalTrackLengthFraction = f2;
        } else {
            this.totalTrackLengthFraction = 1.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull DrawingDelegate.ActiveIndicator activeIndicator, int i2) {
        int iCompositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(activeIndicator.color, i2);
        float f2 = activeIndicator.startFraction;
        float f3 = activeIndicator.endFraction;
        int i3 = activeIndicator.gapSize;
        drawLine(canvas, paint, f2, f3, iCompositeARGBWithAlpha, i3, i3);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void fillTrack(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, int i2, int i3, @Px int i4) {
        drawLine(canvas, paint, f2, f3, MaterialColors.compositeARGBWithAlpha(i2, i3), i4, i4);
    }

    private void drawLine(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, @ColorInt int i2, @Px int i3, @Px int i4) {
        float fClamp = MathUtils.clamp(f2, 0.0f, 1.0f);
        float fClamp2 = MathUtils.clamp(f3, 0.0f, 1.0f);
        float fLerp = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, fClamp);
        float fLerp2 = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, fClamp2);
        int iClamp = (int) ((i3 * MathUtils.clamp(fLerp, 0.0f, 0.01f)) / 0.01f);
        int iClamp2 = (int) ((i4 * (1.0f - MathUtils.clamp(fLerp2, 0.99f, 1.0f))) / 0.01f);
        float f4 = this.trackLength;
        int i5 = (int) ((fLerp * f4) + iClamp);
        int i6 = (int) ((fLerp2 * f4) - iClamp2);
        float f5 = (-f4) / 2.0f;
        if (i5 <= i6) {
            float f6 = this.displayedCornerRadius;
            float f7 = i5 + f6;
            float f8 = i6 - f6;
            float f9 = f6 * 2.0f;
            paint.setColor(i2);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(this.displayedTrackThickness);
            if (f7 >= f8) {
                drawRoundedBlock(canvas, paint, new PointF(f7 + f5, 0.0f), new PointF(f8 + f5, 0.0f), f9, this.displayedTrackThickness);
                return;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(this.useStrokeCap ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            float f10 = f7 + f5;
            float f11 = f8 + f5;
            canvas.drawLine(f10, 0.0f, f11, 0.0f, paint);
            if (this.useStrokeCap || this.displayedCornerRadius <= 0.0f) {
                return;
            }
            paint.setStyle(Paint.Style.FILL);
            if (f7 > 0.0f) {
                drawRoundedBlock(canvas, paint, new PointF(f10, 0.0f), f9, this.displayedTrackThickness);
            }
            if (f8 < this.trackLength) {
                drawRoundedBlock(canvas, paint, new PointF(f11, 0.0f), f9, this.displayedTrackThickness);
            }
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void drawStopIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @ColorInt int i2, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = ScatterMapKt.Sentinel) int i3) {
        int iCompositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(i2, i3);
        if (this.spec.trackStopIndicatorSize <= 0 || iCompositeARGBWithAlpha == 0) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(iCompositeARGBWithAlpha);
        PointF pointF = new PointF((this.trackLength / 2.0f) - (this.displayedTrackThickness / 2.0f), 0.0f);
        S s = this.spec;
        drawRoundedBlock(canvas, paint, pointF, ((LinearProgressIndicatorSpec) s).trackStopIndicatorSize, ((LinearProgressIndicatorSpec) s).trackStopIndicatorSize);
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull PointF pointF, float f2, float f3) {
        drawRoundedBlock(canvas, paint, pointF, null, f2, f3);
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull PointF pointF, @Nullable PointF pointF2, float f2, float f3) {
        float fMin = Math.min(f3, this.displayedTrackThickness);
        float f4 = f2 / 2.0f;
        float fMin2 = Math.min(f4, (this.displayedCornerRadius * fMin) / this.displayedTrackThickness);
        RectF rectF = new RectF((-f2) / 2.0f, (-fMin) / 2.0f, f4, fMin / 2.0f);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        if (pointF2 != null) {
            canvas.translate(pointF2.x, pointF2.y);
            Path path = new Path();
            path.addRoundRect(rectF, fMin2, fMin2, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.translate(-pointF2.x, -pointF2.y);
        }
        canvas.translate(pointF.x, pointF.y);
        canvas.drawRoundRect(rectF, fMin2, fMin2, paint);
        canvas.restore();
    }
}
