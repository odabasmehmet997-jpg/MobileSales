package com.droidbyme.toastlib;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.widget.IconTextView;
public class ToastLib {
    public ToastLib(Builder builder) {
        Iconify.with(new FontAwesomeModule());
        float f2 = builder.context.getResources().getDisplayMetrics().density;
        View viewInflate = LayoutInflater.from(builder.context).inflate(R_layout.layout_toast, (ViewGroup) null);
        TextView textView = viewInflate.findViewById(R_id.textView);
        ImageView imageView = viewInflate.findViewById(R_id.imageView);
        IconTextView iconTextView = viewInflate.findViewById(R_id.faTextView);
        textView.setText(builder.msg);
        if (builder.textColor != 0) {
            textView.setTextColor(builder.textColor);
            iconTextView.setTextColor(builder.textColor);
        }
        if (builder.backgroundColor == 0) {
            builder.backgroundColor = ContextCompat.getColor(builder.context, android.R.color.transparent);
        }
        imageView.setVisibility(View.GONE);
        if (TextUtils.isEmpty(builder.faString) || builder.faString.length() == 0) {
            iconTextView.setVisibility(View.GONE);
        } else {
            iconTextView.setVisibility(View.VISIBLE);
            iconTextView.setText(builder.faString);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(builder.radius);
        gradientDrawable.setStroke(builder.strokewidth, builder.strokeColor);
        gradientDrawable.setColor(builder.backgroundColor);
        viewInflate.setBackground(gradientDrawable);
        if (builder.typeface != null) {
            textView.setTypeface(builder.typeface);
        }
        if (builder.isBold) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }
        textView.setTextSize(2, builder.size);
        imageView.setLayoutParams(new LinearLayout.LayoutParams((int) ((builder.width * f2) + 0.5f), (int) ((builder.height * f2) + 0.5f)));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
        layoutParams.setMargins((int) (f2 * builder.spacing), 0, 0, 0);
        textView.setLayoutParams(layoutParams);
        viewInflate.setPadding(builder.padding, builder.padding, builder.padding, builder.padding);
        Toast toast = new Toast(builder.context);
        toast.setView(viewInflate);
        if (builder.myEnum != null && builder.myEnum.getId() == ToastEnum.LONG.getId()) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        if (builder.gravity == 48 || builder.gravity == 80) {
            toast.setGravity(builder.gravity, 0, builder.margin);
        } else if (builder.gravity == 8388611 || builder.gravity == 8388613 || builder.gravity == 3 || builder.gravity == 5) {
            toast.setGravity(builder.gravity, builder.margin, 0);
        } else {
            toast.setGravity(builder.gravity, 0, 0);
        }
        toast.show();
    }
    public static class Builder {
        private int backgroundColor;
        private final Context context;
        private int gravity;
        private int height;
        private boolean isBold;
        private int margin;
        private final String msg;
        private ToastEnum myEnum;
        private int padding;
        private int radius;
        private int size;
        private int spacing;
        private int textColor;
        private Typeface typeface;
        private int width;
        private final int icon = 0;
        private int iconColor = 0;
        private int strokewidth = 0;
        private int strokeColor = 0;
        private String faString = "";

        public Builder(Context context, String str) {
            this.backgroundColor = android.R.color.transparent;
            this.textColor = android.R.color.black;
            this.radius = 4;
            this.gravity = 80;
            this.margin = 36;
            this.size = 20;
            this.width = 24;
            this.height = 24;
            this.spacing = 8;
            this.padding = 4;
            this.context = context;
            this.msg = str;
            this.backgroundColor = ContextCompat.getColor(context, android.R.color.transparent);
            this.textColor = ContextCompat.getColor(context, android.R.color.black);
        }

        public Builder duration(ToastEnum toastEnum) {
            this.myEnum = toastEnum;
            return this;
        }

        public ToastLib show() {
            return new ToastLib(this);
        }

        public Builder backgroundColor(int i2) {
            this.backgroundColor = i2;
            return this;
        }

        public Builder textColor(int i2) {
            this.textColor = i2;
            return this;
        }

        public Builder icon(String str) {
            this.faString = str;
            return this;
        }

        public Builder iconColor(int i2) {
            this.iconColor = i2;
            return this;
        }

        public Builder corner(int i2) {
            this.radius = i2;
            return this;
        }

        public Builder isBold(boolean z) {
            this.isBold = z;
            return this;
        }

        public Builder gravity(int i2) {
            this.gravity = i2;
            return this;
        }

        public Builder margin(int i2) {
            this.margin = i2;
            return this;
        }

        public Builder padding(int i2) {
            this.padding = i2;
            return this;
        }

        public Builder textSize(int i2) {
            this.size = i2;
            return this;
        }

        public Builder iconSize(int i2, int i3) {
            this.width = i2;
            this.height = i3;
            return this;
        }

        public Builder spacing(int i2) {
            this.spacing = i2;
            return this;
        }

        public Builder stroke(int i2, int i3) {
            this.strokewidth = i2;
            this.strokeColor = i3;
            return this;
        }
    }
}
