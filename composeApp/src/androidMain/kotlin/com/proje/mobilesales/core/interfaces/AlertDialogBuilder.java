package com.proje.mobilesales.core.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;
import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import java.lang.ref.WeakReference;
import java.util.List;
 
public interface AlertDialogBuilder<T extends Dialog> {
    T create();

    void dismiss();

    AlertDialog getAlertDialog();

    boolean isShowing();

    AlertDialog setCancelOnTouchOutside(boolean z);

    AlertDialogBuilder setCancelable(boolean z);

    AlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str);

    AlertDialogBuilder setItems(@ArrayRes int i2, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setMessage(@StringRes int i2);

    AlertDialogBuilder setMessage(String str);

    AlertDialogBuilder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener);

    AlertDialogBuilder setNegativeButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setNeutralButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    AlertDialogBuilder setPositiveButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setSingleChoice(@ArrayRes int i2, int i3, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setSingleChoice(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setSingleChoiceItems(Cursor cursor, int i2, String str, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setSingleChoiceItems(List<CharSequenceGet> list, int i2, String str, DialogInterface.OnClickListener onClickListener);

    AlertDialogBuilder setTitle(@StringRes int i2);

    AlertDialogBuilder setTitle(String str);

    AlertDialogBuilder setView(View view);

    T show();

    boolean isActivityDestroyed();

    void onSaveResult(boolean b, String s);

    class Impl implements AlertDialogBuilder<AlertDialog> {
        private AlertDialog mAlertDialog;
        private final AlertDialog.Builder mBuilder;
        private final WeakReference<BaseInjectableActivity> mInjectableActivityWeakReference;

        public Impl(Context context, BaseInjectableActivity baseInjectableActivity) {
            this.mBuilder = new AlertDialog.Builder(context);
            this.mInjectableActivityWeakReference = new WeakReference<>(baseInjectableActivity);
        }

        
        public AlertDialogBuilder setMessage(@StringRes int i2) {
            this.mBuilder.setMessage(i2);
            return this;
        }

        
        public AlertDialogBuilder setMessage(String str) {
            this.mBuilder.setMessage(str);
            return this;
        }

        
        public AlertDialogBuilder setTitle(@StringRes int i2) {
            this.mBuilder.setTitle(i2);
            return this;
        }

        
        public AlertDialogBuilder setTitle(String str) {
            this.mBuilder.setTitle(str);
            return this;
        }

        
        public AlertDialogBuilder setView(View view) {
            this.mBuilder.setView(view);
            return this;
        }

        
        public AlertDialogBuilder setNegativeButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setNegativeButton(i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setPositiveButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setPositiveButton(i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setNeutralButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setNeutralButton(i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mBuilder.setOnCancelListener(onCancelListener);
            return this;
        }

        
        public AlertDialogBuilder setSingleChoiceItems(Cursor cursor, int i2, String str, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setSingleChoiceItems(cursor, i2, str, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setSingleChoiceItems(List<CharSequenceGet> list, int i2, String str, DialogInterface.OnClickListener onClickListener) {
            CharSequence[] charSequenceArr = new CharSequence[list.size()];
            for (int i3 = 0; i3 < list.size(); i3++) {
                charSequenceArr[i3] = list.get(i3).getCharSequence();
            }
            this.mBuilder.setSingleChoiceItems(charSequenceArr, i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.mBuilder.setMultiChoiceItems(charSequenceArr, zArr, onMultiChoiceClickListener);
            return this;
        }

        
        public AlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.mBuilder.setCursor(cursor, onClickListener, str);
            return this;
        }

        
        public AlertDialogBuilder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setItems(charSequenceArr, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setItems(@ArrayRes int i2, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setItems(i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setSingleChoice(@ArrayRes int i2, int i3, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setSingleChoiceItems(i2, i3, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setSingleChoice(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            this.mBuilder.setSingleChoiceItems(charSequenceArr, i2, onClickListener);
            return this;
        }

        
        public AlertDialogBuilder setCancelable(boolean z) {
            this.mBuilder.setCancelable(z);
            return this;
        }

        
        public AlertDialog getAlertDialog() {
            return this.mAlertDialog;
        }

        
        public void dismiss() {
            AlertDialog alertDialog;
            if (this.mInjectableActivityWeakReference.get() == null || this.mInjectableActivityWeakReference.get().isActivityDestroyed() || (alertDialog = this.mAlertDialog) == null || !alertDialog.isShowing()) {
                return;
            }
            this.mAlertDialog.dismiss();
        }

        
        public AlertDialog setCancelOnTouchOutside(boolean z) {
            AlertDialog alertDialog = this.mAlertDialog;
            if (alertDialog != null) {
                alertDialog.setCanceledOnTouchOutside(z);
            }
            return this.mAlertDialog;
        }

        
        public boolean isShowing() {
            AlertDialog alertDialog = this.mAlertDialog;
            if (alertDialog == null) {
                return false;
            }
            return alertDialog.isShowing();
        }

        
        public AlertDialog create() {
            if (!this.mInjectableActivityWeakReference.get().isActivityDestroyed()) {
                this.mAlertDialog = this.mBuilder.create();
            }
            return this.mAlertDialog;
        }

        
        public AlertDialog show() {
            if (!this.mInjectableActivityWeakReference.get().isActivityDestroyed()) {
                this.mAlertDialog = this.mBuilder.show();
            }
            return this.mAlertDialog;
        }

        @Override
        public boolean isActivityDestroyed() {
            return false;
        }

        @Override
        public void onSaveResult(boolean b, String s) {

        }
    }
}
