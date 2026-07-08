package com.joanzapata.iconify.internal;

import android.widget.TextView;
import androidx.core.view.ViewCompat;


public interface HasOnViewAttachListener {
    void setOnViewAttachListener(OnViewAttachListener onViewAttachListener);
    interface OnViewAttachListener {
        void onAttach();

        void onDetach();
    }
    class HasOnViewAttachListenerDelegate {
        private final TextView view;
        private OnViewAttachListener listener;

        public HasOnViewAttachListenerDelegate(TextView textView) {
          this.view = textView;
        }

        public void setOnViewAttachListener(OnViewAttachListener onViewAttachListener) {
            OnViewAttachListener onViewAttachListener2 = this.listener;
            if (null != onViewAttachListener2) {
                onViewAttachListener2.onDetach ();
            }
          this.listener = onViewAttachListener;
            if (!ViewCompat.isAttachedToWindow (this.view) || null == onViewAttachListener) {
                return;
            }
            onViewAttachListener.onAttach ();
        }

        public void onAttachedToWindow() {
            OnViewAttachListener onViewAttachListener = this.listener;
            if (null != onViewAttachListener) {
                onViewAttachListener.onAttach ();
            }
        }

        public void onDetachedFromWindow() {
            OnViewAttachListener onViewAttachListener = this.listener;
            if (null != onViewAttachListener) {
                onViewAttachListener.onDetach ();
            }
        }
    }
}
