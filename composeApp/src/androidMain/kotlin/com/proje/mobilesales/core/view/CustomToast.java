package com.proje.mobilesales.core.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import com.proje.mobilesales.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CustomToast.kt */

public final class CustomToast extends Toast {
    public static final int CONFUSING = 6;
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT = 5;
    public static final int ERROR = 3;
    public static final int INFO = 4;
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    public static final int SUCCESS = 1;
    public static final int WARNING = 2;

    /* compiled from: CustomToast.kt */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    /* compiled from: CustomToast.kt */
    @Retention(RetentionPolicy.SOURCE)
    public @interface LayoutType {
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i2, int i3) {
        return Companion.makeText(context, charSequence, i2, i3);
    }

    
    public CustomToast(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* compiled from: CustomToast.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Toast makeText(Context context, CharSequence charSequence, int i2, int i3) {
            Intrinsics.checkNotNullParameter(context, "context");
            Toast toast = new Toast(context);
            toast.setDuration(i2);
            View inflate = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false);
            TextView textView = inflate.findViewById(R.id.toast_text);
            LinearLayout linearLayout = inflate.findViewById(R.id.toast_type);
            ImageView imageView = inflate.findViewById(R.id.toast_icon);
            textView.setText(charSequence);
            switch (i3) {
                case 1:
                    linearLayout.setBackgroundResource(R.drawable.success_shape);
                    imageView.setImageResource(R.drawable.ic_check_black_24dp);
                    break;
                case 2:
                    linearLayout.setBackgroundResource(R.drawable.warning_shape);
                    imageView.setImageResource(R.drawable.ic_pan_tool_black_24dp);
                    break;
                case 3:
                    linearLayout.setBackgroundResource(R.drawable.error_shape);
                    imageView.setImageResource(R.drawable.ic_clear_black_24dp);
                    break;
                case 4:
                    linearLayout.setBackgroundResource(R.drawable.info_shape);
                    imageView.setImageResource(R.drawable.ic_info_outline_black_24dp);
                    break;
                case 5:
                    linearLayout.setBackgroundResource(R.drawable.default_shape);
                    imageView.setVisibility(8);
                    break;
                case 6:
                    linearLayout.setBackgroundResource(R.drawable.confusing_shape);
                    imageView.setImageResource(R.drawable.ic_refresh_black_24dp);
                    break;
            }
            toast.setView(inflate);
            return toast;
        }

        public Toast makeText(Context context, CharSequence charSequence, int i2, int i3, @DrawableRes int i4) {
            Intrinsics.checkNotNullParameter(context, "context");
            Toast toast = new Toast(context);
            toast.setDuration(i2);
            View inflate = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false);
            TextView textView = inflate.findViewById(R.id.toast_text);
            LinearLayout linearLayout = inflate.findViewById(R.id.toast_type);
            ImageView imageView = inflate.findViewById(R.id.toast_icon);
            textView.setText(charSequence);
            imageView.setImageResource(i4);
            switch (i3) {
                case 1:
                    linearLayout.setBackgroundResource(R.drawable.success_shape);
                    break;
                case 2:
                    linearLayout.setBackgroundResource(R.drawable.warning_shape);
                    break;
                case 3:
                    linearLayout.setBackgroundResource(R.drawable.error_shape);
                    break;
                case 4:
                    linearLayout.setBackgroundResource(R.drawable.info_shape);
                    break;
                case 5:
                    linearLayout.setBackgroundResource(R.drawable.default_shape);
                    imageView.setVisibility(8);
                    break;
                case 6:
                    linearLayout.setBackgroundResource(R.drawable.confusing_shape);
                    break;
                default:
                    linearLayout.setBackgroundResource(R.drawable.default_shape);
                    imageView.setVisibility(8);
                    break;
            }
            toast.setView(inflate);
            return toast;
        }
    }
}
