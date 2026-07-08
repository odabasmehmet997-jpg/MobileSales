package com.proje.mobilesales.core.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.proje.mobilesales.R;


import static com.proje.mobilesales.core.utils.AppUtils.getThemedResId;

public class TintableTextView extends androidx.appcompat.widget.AppCompatTextView {
    private final int mTextColor;
    public TintableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public TintableTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTextColor = getTextColor(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TintableTextView, 0, 0);
        setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getDrawable(2), obtainStyledAttributes.getDrawable(3), obtainStyledAttributes.getDrawable(1), obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
    }
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(tint(drawable), tint(drawable2), tint(drawable3), tint(drawable4));
    }
    private int getTextColor(Context context, AttributeSet attributeSet) {
        int i2;
        int color = ContextCompat.getColor(getContext(), getThemedResId(getContext(), R.attr.textColorTertiary));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.textAppearance, R.attr.textColor});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId == 0) {
            i2 = obtainStyledAttributes.getColor(0, color);
        } else {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, new int[]{R.attr.textColor});
            int color2 = obtainStyledAttributes2.getColor(0, color);
            obtainStyledAttributes2.recycle();
            i2 = color2;
        }
        obtainStyledAttributes.recycle();
        return i2;
    }
    public Drawable tint(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, this.mTextColor);
        return wrap;
    }
    public void setLeftDraw(int i2) {
        setCompoundDrawablesWithIntrinsicBounds(tint(ResourcesCompat.getDrawable(getResources(), i2, null)), null, null, null);
    }
    public void setCustomerOperationLeftDraw(int i2) {
        Drawable drawable = i2 == -1 ? null : ResourcesCompat.getDrawable(getResources(), i2, null);
        Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_chevron_right_grey600_24dp, null);
        if (getResources().getConfiguration().getLayoutDirection() == 1) {
            setCompoundDrawablesWithIntrinsicBounds(tint(drawable2), null, tint(drawable), null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(tint(drawable), null, tint(drawable2), null);
        }
    }
}
