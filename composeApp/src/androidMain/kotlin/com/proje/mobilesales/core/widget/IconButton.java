package com.proje.mobilesales.core.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.proje.mobilesales.R;

import static com.proje.mobilesales.core.utils.AppUtils.getThemedResId;

public class IconButton extends androidx.appcompat.widget.AppCompatImageButton {
    private static final int[][] STATES = {new int[]{R.attr.state_enabled}, new int[]{-16842910}};
    private final ColorStateList mColorStateList;
    private final boolean mTinted;
    public IconButton(Context context) {
        this(context, null);
    }
    public IconButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public IconButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setBackgroundResource(getThemedResId(context, R.attr.selectableItemBackgroundBorderless));
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MyIconButton, 0, 0);
        int color = ContextCompat.getColor(context, getThemedResId(context, R.attr.textColorSecondary));
        this.mColorStateList = new ColorStateList(STATES, new int[]{obtainStyledAttributes.getColor(0, ContextCompat.getColor(context, getThemedResId(context, R.attr.textColorPrimary))), color});
        this.mTinted = obtainStyledAttributes.hasValue(0);
        if (getSuggestedMinimumWidth() == 0) {
            setMinimumWidth(context.getResources().getDimensionPixelSize(R.dimen.icon_button_width));
        }
        setScaleType(ImageView.ScaleType.CENTER);
        setImageDrawable(tint(getDrawable()));
        obtainStyledAttributes.recycle();
    }
    public void setImageResource(int i2) {
        setImageDrawable(ContextCompat.getDrawable(getContext(), i2));
    }
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(tint(drawable));
    }
    private Drawable tint(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (this.mTinted) {
            drawable = drawable.mutate();
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrap, this.mColorStateList);
        return wrap;
    }
}
