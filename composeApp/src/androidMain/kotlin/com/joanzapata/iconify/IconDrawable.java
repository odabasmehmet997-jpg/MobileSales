package com.joanzapata.iconify;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.TypedValue;
import androidx.core.view.ViewCompat;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;

public class IconDrawable extends Drawable {
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    private Context context;
    private com.joanzapata.iconify.Icon icon;
    private TextPaint paint;
    private int size = -1;
    private int alpha = 255;
    public IconDrawable(final Context context, final String str) {
        if (null == context) {
            throw new IllegalArgumentException ("Context cannot be null");
        }
        if (null == str) {
            throw new IllegalArgumentException ("Icon key cannot be null");
        }
        final com.joanzapata.iconify.Icon findIconForKey = com.joanzapata.iconify.Iconify.findIconForKey (str);
        if (null == findIconForKey) {
            throw new IllegalArgumentException ("No icon with that key \"" + str + "\".");
        }
      this.init(context, findIconForKey);
    }
    public IconDrawable(final Context context, final com.joanzapata.iconify.Icon icon) {
        if (null == context) {
            throw new IllegalArgumentException ("Context cannot be null");
        }
        if (null == icon) {
            throw new IllegalArgumentException ("Icon cannot be null");
        }
      this.init(context, icon);
    }
    public boolean isStateful() {
        return true;
    }
    private void init(final Context context, final Icon icon) {
        if (null == context) {
            throw new IllegalArgumentException ("Context cannot be null");
        }
        if (null == icon) {
            throw new IllegalArgumentException ("Icon cannot be null");
        }
        this.context = context;
        this.icon = icon;
        paint = new TextPaint ();
        final IconFontDescriptorWrapper findTypefaceOf = Iconify.findTypefaceOf (icon);
        if (null == findTypefaceOf) {
            throw new IllegalStateException ("Unable to find the module associated with icon " + icon.key () + ", have you registered the module you are trying to use with Iconify.with(...) in your Application?");
        }
        paint.setTypeface (findTypefaceOf.getTypeface (context));
        paint.setStyle (Paint.Style.FILL);
        paint.setTextAlign (Paint.Align.CENTER);
        paint.setUnderlineText (false);
        paint.setColor (ViewCompat.MEASURED_STATE_MASK);
        paint.setAntiAlias (true);
    }
    public IconDrawable actionBarSize() {
        return this.sizeDp(24);
    }
    public IconDrawable sizeDp(final int i2) {
        return this.sizePx(this.convertDpToPx(context, i2));
    }
    public IconDrawable sizePx(final int i2) {
        size = i2;
      this.setBounds(0, 0, i2, i2);
      this.invalidateSelf();
        return this;
    }
    public IconDrawable color(final int i2) {
        paint.setColor (i2);
      this.invalidateSelf();
        return this;
    }
    public IconDrawable colorRes(final int i2) {
        paint.setColor (context.getResources ().getColor (i2));
      this.invalidateSelf();
        return this;
    }
    public IconDrawable alpha(final int i2) {
      this.setAlpha(i2);
      this.invalidateSelf();
        return this;
    }
    public int getIntrinsicHeight() {
        return size;
    }
    public int getIntrinsicWidth() {
        return size;
    }
    public void draw(final Canvas canvas) {
        final Rect bounds = this.getBounds();
        paint.setTextSize (bounds.height ());
        final Rect rect = new Rect ();
        final String valueOf = String.valueOf (icon.character ());
        paint.getTextBounds (valueOf, 0, 1, rect);
        canvas.drawText (valueOf, bounds.exactCenterX (), ((bounds.top + (this.size / 2.0f)) + rect.height ()) - rect.bottom, paint);
    }
    public boolean setState(final int[] iArr) {
        final int alpha = paint.getAlpha ();
        final int i2 = this.isEnabled(iArr) ? this.alpha : this.alpha / 2;
        paint.setAlpha (i2);
        return alpha != i2;
    }
    public void setAlpha(final int i2) {
        alpha = i2;
        paint.setAlpha (i2);
    }
    public void setColorFilter(final ColorFilter colorFilter) {
        paint.setColorFilter (colorFilter);
    }
    public void clearColorFilter() {
        paint.setColorFilter (null);
    }
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
    public void setStyle(final Paint.Style style) {
        paint.setStyle (style);
    }
    private boolean isEnabled(final int[] iArr) {
        for (final int i2 : iArr) {
            if (16842910 == i2) {
                return true;
            }
        }
        return false;
    }
    private int convertDpToPx(final Context context, final float f2) {
        return (int) TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, f2, context.getResources ().getDisplayMetrics ());
    }
}
