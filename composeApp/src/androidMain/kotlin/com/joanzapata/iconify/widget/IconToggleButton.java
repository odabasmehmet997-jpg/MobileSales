package com.joanzapata.iconify.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.internal.HasOnViewAttachListener;


public class IconToggleButton extends androidx.appcompat.widget.AppCompatToggleButton implements HasOnViewAttachListener {
    private HasOnViewAttachListenerDelegate delegate;
    public IconToggleButton(Context context, AttributeSet attributeSet, int i2) {
        super (context, attributeSet, i2);
        init();
    }
    public IconToggleButton(Context context, AttributeSet attributeSet) {
        super (context, attributeSet);
        init();
    }
    public IconToggleButton(Context context) {
        super (context);
        init();
    }
    private void init() {
        setTransformationMethod(null);
    }
    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText (Iconify.compute (getContext(), charSequence, this), BufferType.NORMAL);
    }
    public void setOnViewAttachListener(OnViewAttachListener onViewAttachListener) {
        if (null == delegate) {
          this.delegate = new HasOnViewAttachListenerDelegate (this);
        }
      this.delegate.setOnViewAttachListener (onViewAttachListener);
    }
    protected void onAttachedToWindow() {
        super.onAttachedToWindow ();
      this.delegate.onAttachedToWindow ();
    }
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow ();
      this.delegate.onDetachedFromWindow ();
    }
}
