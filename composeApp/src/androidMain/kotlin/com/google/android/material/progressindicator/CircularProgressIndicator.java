package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*  INFO: loaded from: classes2.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_CircularProgressIndicator;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface IndicatorDirection {
    }

    public CircularProgressIndicator(@NonNull Context context) {
        this(context, null);
    }

    public CircularProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2, DEF_STYLE_RES);
        initializeDrawables();
    }

    /*  INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public CircularProgressIndicatorSpec createSpec(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    private void initializeDrawables() {
        CircularDrawingDelegate circularDrawingDelegate = new CircularDrawingDelegate(this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.createCircularDrawable(getContext(), this.spec, circularDrawingDelegate));
        setProgressDrawable(DeterminateDrawable.createCircularDrawable(getContext(), this.spec, circularDrawingDelegate));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i2) {
        super.setTrackThickness(i2);
        this.spec.validateSpec();
    }

    @Px
    public int getIndicatorInset() {
        return this.spec.indicatorInset;
    }

    public void setIndicatorInset(@Px int i2) {
        S s = this.spec;
        if (((CircularProgressIndicatorSpec) s).indicatorInset != i2) {
            ((CircularProgressIndicatorSpec) s).indicatorInset = i2;
            invalidate();
        }
    }

    @Px
    public int getIndicatorSize() {
        return this.spec.indicatorSize;
    }

    public void setIndicatorSize(@Px int i2) {
        int iMax = Math.max(i2, getTrackThickness() * 2);
        S s = this.spec;
        if (((CircularProgressIndicatorSpec) s).indicatorSize != iMax) {
            ((CircularProgressIndicatorSpec) s).indicatorSize = iMax;
            ((CircularProgressIndicatorSpec) s).validateSpec();
            requestLayout();
            invalidate();
        }
    }

    public int getIndicatorDirection() {
        return this.spec.indicatorDirection;
    }

    public void setIndicatorDirection(int i2) {
        this.spec.indicatorDirection = i2;
        invalidate();
    }
}
