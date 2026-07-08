package com.proje.mobilesales.core.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.StringRes;
 
public interface ToastBuilder<T extends Toast> {
    ToastBuilder setLength(int i2);

    ToastBuilder setMessage(@StringRes int i2);

    ToastBuilder setMessage(String str);

    ToastBuilder setView(View view);

    T show();

    class Impl implements ToastBuilder<Toast> {
        private final Toast mToast;

        public Impl(Context context) {
            this.mToast = Toast.makeText(context, null, 1);
        }

        
        public ToastBuilder setMessage(@StringRes int i2) {
            this.mToast.setText(i2);
            return this;
        }

        
        public ToastBuilder setMessage(String str) {
            this.mToast.setText(str);
            return this;
        }

        
        public ToastBuilder setLength(int i2) {
            this.mToast.setDuration(i2);
            return this;
        }

        
        public ToastBuilder setView(View view) {
            this.mToast.setView(view);
            return this;
        }

        
        public Toast show() {
            this.mToast.show();
            return this.mToast;
        }
    }
}
