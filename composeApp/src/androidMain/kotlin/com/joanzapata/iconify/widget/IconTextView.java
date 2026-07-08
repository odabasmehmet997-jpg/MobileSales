package com.joanzapata.iconify.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.internal.HasOnViewAttachListener;

public class IconTextView extends androidx.appcompat.widget.AppCompatTextView implements HasOnViewAttachListener {
    private HasOnViewAttachListenerDelegate delegate;
    public IconTextView(final Context context) {
        super (context);
      this.init();
    }
    public IconTextView(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
      this.init();
    }
    public IconTextView(final Context context, final AttributeSet attributeSet, final int i2) {
        super (context, attributeSet, i2);
      this.init();
    }
    private void init() {
      this.setTransformationMethod(null);
    }
    public void setText(final CharSequence charSequence, final BufferType bufferType) {
        super.setText (Iconify.compute (this.getContext(), charSequence, this), bufferType);
    }
    public void setOnViewAttachListener(final OnViewAttachListener onViewAttachListener) {
        if (null == delegate) {
            delegate = new HasOnViewAttachListenerDelegate (this);
        }
        delegate.setOnViewAttachListener (onViewAttachListener);
    }
    protected void onAttachedToWindow() {
        super.onAttachedToWindow ();
        delegate.onAttachedToWindow ();
    }
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow ();
        delegate.onDetachedFromWindow ();
    }
}
