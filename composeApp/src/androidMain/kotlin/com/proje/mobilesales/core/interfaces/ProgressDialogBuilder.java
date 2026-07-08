package com.proje.mobilesales.core.interfaces;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.View;
import androidx.annotation.NonNull;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import java.lang.ref.WeakReference;
 
public interface ProgressDialogBuilder<T extends ProgressDialog> {
    T cancel();
    T dismiss();
    boolean isShowing();
    ProgressDialogBuilder setButtonNegative(@NonNull String str, @NonNull DialogInterface.OnClickListener onClickListener);
    ProgressDialogBuilder setCancelable(@NonNull boolean z);
    ProgressDialogBuilder setCancelableMessage(Message message);
    ProgressDialogBuilder setMessage(@NonNull String str);
    ProgressDialogBuilder setOnCancelClickListener(DialogInterface.OnCancelListener onCancelListener);
    ProgressDialogBuilder setView(View view);
    T show();
    ProgressDialogBuilder<ProgressDialog> setOnCancelClickListener(Object o);
    ProgressDialogBuilder<ProgressDialog> setOnCancelClickListener();
    class Impl implements ProgressDialogBuilder<ProgressDialog> {
        private final WeakReference<BaseInjectableActivity> mActivityWeakReference;
        private final ProgressDialog mProgressDialog;

        public Impl(Context context, BaseInjectableActivity baseInjectableActivity) {
            this.mProgressDialog = new ProgressDialog(context);
            this.mActivityWeakReference = new WeakReference<>(baseInjectableActivity);
        } 
        public ProgressDialogBuilder setMessage(@NonNull String str) {
            this.mProgressDialog.setMessage(str);
            return this;
        }
        public ProgressDialogBuilder setOnCancelClickListener() {
            return null;
        }
        public ProgressDialogBuilder setCancelableMessage(Message message) {
            this.mProgressDialog.setCancelMessage(message);
            return this;
        } 
        public ProgressDialogBuilder setCancelable(@NonNull boolean z) {
            this.mProgressDialog.setCancelable(z);
            return this;
        }
        public ProgressDialogBuilder setOnCancelClickListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mProgressDialog.setOnCancelListener(onCancelListener);
            return this;
        }

        
        public ProgressDialogBuilder setButtonNegative(@NonNull String str, @NonNull DialogInterface.OnClickListener onClickListener) {
            this.mProgressDialog.setButton(-2, str, onClickListener);
            return this;
        }

        
        public ProgressDialogBuilder setView(View view) {
            this.mProgressDialog.setView(view);
            return this;
        }

        
        public ProgressDialog show() {
            if (this.mActivityWeakReference.get() != null && !this.mActivityWeakReference.get().isActivityDestroyed()) {
                this.mProgressDialog.show();
            }
            return this.mProgressDialog;
        }

        @Override
        public ProgressDialogBuilder<ProgressDialog> setOnCancelClickListener(Object o) {
            return null;
        }


        public ProgressDialog dismiss() {
            ProgressDialog progressDialog;
            if (this.mActivityWeakReference.get() != null && !this.mActivityWeakReference.get().isActivityDestroyed() && (progressDialog = this.mProgressDialog) != null && progressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
            return this.mProgressDialog;
        }

        
        public ProgressDialog cancel() {
            this.mProgressDialog.cancel();
            return this.mProgressDialog;
        }

        
        public boolean isShowing() {
            return this.mProgressDialog.isShowing();
        }
    }
}
