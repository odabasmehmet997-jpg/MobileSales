package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class MarkerView extends RelativeLayout {
    protected MarkerView(final Context context, final int i) {
        super (context);
        this.setupLayoutResource(i);
    }

    public abstract int getXOffset(float f);

    public abstract int getYOffset(float f);

    public abstract void refreshContent(Entry entry, Highlight highlight);

    private void setupLayoutResource(final int i) {
        final View inflate = LayoutInflater.from (this.getContext()).inflate (i, this);
        inflate.setLayoutParams (new LayoutParams (-2, -2));
        inflate.measure (MeasureSpec.makeMeasureSpec (0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec (0, MeasureSpec.UNSPECIFIED));
        inflate.layout (0, 0, inflate.getMeasuredHeight (), inflate.getMeasuredWidth ());
    }

    public void draw(final Canvas canvas, final float f, final float f2) {
        final float xOffset = f + this.getXOffset(f);
        final float yOffset = f2 + this.getYOffset(f2);
        canvas.translate (xOffset, yOffset);
        this.draw(canvas);
        canvas.translate (-xOffset, -yOffset);
    }
}
