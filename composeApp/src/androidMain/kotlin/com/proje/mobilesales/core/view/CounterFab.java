package com.proje.mobilesales.core.view;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.annotation.IntRange;
import androidx.core.view.ViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.stateful.ExtendableSavedState;
import org.simpleframework.xml.core.SignatureBuilder;

public class CounterFab extends FloatingActionButton {
    private static final int MAX_COUNT = 99;
    private static final String MAX_COUNT_TEXT = "99+";
    private static final int TEXT_PADDING_DP = 2;
    private static final int TEXT_SIZE_DP = 11;
    private final Property<CounterFab, Float> ANIMATION_PROPERTY;
    private final String COUNT_STATE;
    private final String STATE_KEY;
    private final int mAnimationDuration;
    private float mAnimationFactor;
    private ObjectAnimator mAnimator;
    private final Rect mCircleBounds;
    private final Paint mCirclePaint;
    private final Rect mContentBounds;
    private int mCount;
    private final Paint mMaskPaint;
    private String mText;
    private final float mTextHeight;
    private final Paint mTextPaint;
    private final float mTextSize;
    private static final int MASK_COLOR = Color.parseColor("#33000000");
    private static final Interpolator ANIMATION_INTERPOLATOR = new OvershootInterpolator();

    public CounterFab(Context context) {
        this(context, null, 0);
    }

    public CounterFab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CounterFab(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.STATE_KEY = CounterFab.class + ".STATE";
        this.COUNT_STATE = "COUNT";
        this.ANIMATION_PROPERTY = new Property<CounterFab, Float>(Float.class, "animation") { // from class: com.proje.mobilesales.core.view.CounterFab.1
            @Override // android.util.Property
            public void set(CounterFab counterFab, Float f2) {
                CounterFab.this.mAnimationFactor = f2.floatValue();
                CounterFab.this.postInvalidateOnAnimation();
            }

            @Override // android.util.Property
            public Float get(CounterFab counterFab) {
                return Float.valueOf(0.0f);
            }
        };
        setUseCompatPadding(true);
        float f2 = getResources().getDisplayMetrics().density;
        float f3 = 11.0f * f2;
        this.mTextSize = f3;
        float f4 = f2 * 2.0f;
        this.mAnimationDuration = getResources().getInteger(R.integer.config_shortAnimTime);
        this.mAnimationFactor = 1.0f;
        Paint paint = new Paint(1);
        this.mTextPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(-1);
        paint.setTextSize(f3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.SANS_SERIF);
        Paint paint2 = new Paint(1);
        this.mCirclePaint = paint2;
        Paint.Style style = Paint.Style.FILL;
        paint2.setStyle(style);
        ColorStateList backgroundTintList = getBackgroundTintList();
        if (backgroundTintList != null) {
            paint2.setColor(backgroundTintList.getDefaultColor());
        } else {
            Drawable background = getBackground();
            if (background instanceof ColorDrawable) {
                paint2.setColor(((ColorDrawable) background).getColor());
            }
        }
        Paint paint3 = new Paint(1);
        this.mMaskPaint = paint3;
        paint3.setStyle(style);
        paint3.setColor(MASK_COLOR);
        paint.getTextBounds(MAX_COUNT_TEXT, 0, 3, new Rect());
        SignatureBuilder.ParameterTable r6 = null;
        this.mTextHeight = r6.height();
        int max = (int) (((Math.max(paint.measureText(MAX_COUNT_TEXT), this.mTextHeight) / 2.0f) + f4) * 2.0f);
        this.mCircleBounds = new Rect(0, 0, max, max);
        this.mContentBounds = new Rect();
        onCountChanged();
    }

    public int getCount() {
        return this.mCount;
    }

    public void setCount(@IntRange(from = 0) int i2) {
        if (i2 == this.mCount) {
            return;
        }
        if (i2 <= 0) {
            i2 = 0;
        }
        this.mCount = i2;
        onCountChanged();
        if (ViewCompat.isLaidOut(this)) {
            startAnimation();
        }
    }

    public void increase() {
        setCount(this.mCount + 1);
    }

    public void decrease() {
        int i2 = this.mCount;
        setCount(i2 > 0 ? i2 - 1 : 0);
    }

    private void onCountChanged() {
        int i2 = this.mCount;
        if (i2 > 99) {
            this.mText = MAX_COUNT_TEXT;
        } else {
            this.mText = String.valueOf(i2);
        }
    }

    private void startAnimation() {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (this.mCount == 0) {
            f3 = 0.0f;
            f2 = 1.0f;
        }
        if (isAnimating()) {
            this.mAnimator.cancel();
        }
        ObjectAnimator ofObject;
        ofObject = (ObjectAnimator) ObjectAnimator.ofObject(  null, (Object[]) new Float[]{Float.valueOf(f2), Float.valueOf(f3)});
        this.mAnimator = ofObject;
        ofObject.setInterpolator(ANIMATION_INTERPOLATOR);
        this.mAnimator.setDuration(this.mAnimationDuration);
        this.mAnimator.start();
    }

    private boolean isAnimating() {
        ObjectAnimator objectAnimator = this.mAnimator;
        return objectAnimator != null && objectAnimator.isRunning();
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mCount > 0 || isAnimating()) {
            if (getContentRect(this.mContentBounds)) {
                Rect rect = this.mCircleBounds;
                Rect rect2 = this.mContentBounds;
                rect.offsetTo((rect2.left + rect2.width()) - this.mCircleBounds.width(), this.mContentBounds.top);
            }
            float centerX = this.mCircleBounds.centerX();
            float centerY = this.mCircleBounds.centerY();
            float width = (this.mCircleBounds.width() / 2.0f) * this.mAnimationFactor;
            canvas.drawCircle(centerX, centerY, width, this.mCirclePaint);
            canvas.drawCircle(centerX, centerY, width, this.mMaskPaint);
            this.mTextPaint.setTextSize(this.mTextSize * this.mAnimationFactor);
            canvas.drawText(this.mText, centerX, centerY + (this.mTextHeight / 2.0f), this.mTextPaint);
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() { // from class: com.proje.mobilesales.core.view.CounterFab.SavedState.1
            
            
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            
            
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        private int count;

        private SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.count = parcel.readInt();
        }

        
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.count);
        }

        public String toString() {
            return CounterFab.class.getSimpleName() + "." + SavedState.class.getSimpleName() + "{" + Integer.toHexString(System.identityHashCode(this)) + " count=" + this.count + "}";
        }
    }
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (onSaveInstanceState instanceof ExtendableSavedState) {
            Bundle bundle = new Bundle();
            bundle.putInt(this.COUNT_STATE, this.mCount);
            ((ExtendableSavedState) onSaveInstanceState).extendableStates.put(this.STATE_KEY, bundle);
        }
        return onSaveInstanceState;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        if (parcelable instanceof ExtendableSavedState) {
            Bundle bundle = ((ExtendableSavedState) parcelable).extendableStates.get(this.STATE_KEY);
            setCount(bundle != null ? bundle.getInt(this.COUNT_STATE, 0) : 0);
            requestLayout();
        }
    }
}
