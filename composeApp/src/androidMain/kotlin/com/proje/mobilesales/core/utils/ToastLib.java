package com.proje.mobilesales.core.utils;

import android.R;
import android.annotation.SuppressLint;
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
import com.droidbyme.toastlib.ToastEnum;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.widget.IconTextView;
import com.proje.mobilesales.R;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"RtlHardcoded", "InflateParams"})
public final class ToastLib {
    private final Toast toast;
    public ToastLib(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Iconify.with(new FontAwesomeModule());
        float f2 = builder.getContext().getResources().getDisplayMetrics().density;
        View inflate = LayoutInflater.from(builder.getContext()).inflate(R.layout.layout_toast, null);
        TextView textView = inflate.findViewById(R.id.textView);
        ImageView imageView = inflate.findViewById(R.id.imageView);
        IconTextView iconTextView = inflate.findViewById(R.id.faTextView);
        textView.setText(builder.getMsg());
        if (builder.getTextColor() != 0) {
            textView.setTextColor(builder.getTextColor());
            iconTextView.setTextColor(builder.getTextColor());
        }
        if (builder.getBackgroundColor() == 0) {
            builder.setBackgroundColor(ContextCompat.getColor(builder.getContext(), R.color.background_light));
        }
        if (builder.getIcon() == 0) {
            imageView.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(builder.getFaString()) && builder.getFaString().length() > 0) {
                iconTextView.setVisibility(View.VISIBLE);
                iconTextView.setText(builder.getFaString());
            } else {
                iconTextView.setVisibility(View.GONE);
            }
        } else {
            imageView.setImageResource(builder.getIcon());
            iconTextView.setVisibility(View.GONE);
        }
        if (builder.getIcon() != 0 && builder.getIconColor() != 0) {
            imageView.setColorFilter(builder.getIconColor(), PorterDuff.Mode.SRC_ATOP);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(builder.getRadius());
        gradientDrawable.setStroke(builder.getStrokeWidth(), builder.getStrokeColor());
        gradientDrawable.setColor(builder.getBackgroundColor());
        inflate.setBackground(gradientDrawable);
        Typeface typeface = builder.getTypeface();
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
        int i2 = 1;
        if (builder.isBold()) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }
        textView.setTextSize(2, builder.getSize());
        imageView.setLayoutParams(new LinearLayout.LayoutParams((int) ((builder.getWidth() * f2) + 0.5f), (int) ((builder.getHeight() * f2) + 0.5f)));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
        layoutParams.setMargins((int) (f2 * builder.getSpacing()), 0, 0, 0);
        textView.setLayoutParams(layoutParams);
        inflate.setPadding(builder.getPadding(), builder.getPadding(), builder.getPadding(), builder.getPadding());
        Toast toast = new Toast(builder.getContext());
        this.toast = toast;
        toast.setView(inflate);
        if (builder.getMyEnum() != null) {
            ToastEnum myEnum = builder.getMyEnum();
            Intrinsics.checkNotNull(myEnum);
        }
        i2 = 0;
        toast.setDuration(Toast.LENGTH_SHORT);
        if (builder.getGravity() != 48 && builder.getGravity() != 80) {
            if (builder.getGravity() != 8388611 && builder.getGravity() != 8388613 && builder.getGravity() != 3 && builder.getGravity() != 5) {
                toast.setGravity(builder.getGravity(), 0, 0);
            } else {
                toast.setGravity(builder.getGravity(), builder.getMargin(), 0);
            }
        } else {
            toast.setGravity(builder.getGravity(), 0, builder.getMargin());
        }
        toast.show();
    }
    public Toast getToast() {
        return this.toast;
    }
    public static final class Builder {
        private int backgroundColor;
        private final Context context;
        private String faString;
        private int gravity;
        private int height;
        private int icon;
        private int iconColor;
        private boolean isBold;
        private int margin;
        private final String msg;
        private ToastEnum myEnum;
        private int padding;
        private int radius;
        private int size;
        private int spacing;
        private int strokeColor;
        private int strokeWidth;
        private int textColor;
        private Typeface typeface;
        private int width;

        public Builder(Context context, String msg) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(msg, "msg");
            this.context = context;
            this.msg = msg;
            this.faString = "";
            this.backgroundColor = ContextCompat.getColor(context, R.color.background_light);
            this.textColor = ContextCompat.getColor(context, R.color.primary_text_light);
            this.radius = 4;
            this.gravity = 80;
            this.margin = 36;
            this.size = 20;
            this.width = 24;
            this.height = 24;
            this.spacing = 8;
            this.padding = 4;
        }

        public Context getContext() {
            return this.context;
        }

        public String getMsg() {
            return this.msg;
        }

        public ToastEnum getMyEnum() {
            return this.myEnum;
        }

        public void setMyEnum(ToastEnum toastEnum) {
            this.myEnum = toastEnum;
        }

        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public void setBackgroundColor(int i2) {
            this.backgroundColor = i2;
        }

        public int getTextColor() {
            return this.textColor;
        }

        public void setTextColor(int i2) {
            this.textColor = i2;
        }

        public int getIcon() {
            return this.icon;
        }

        public void setIcon(int i2) {
            this.icon = i2;
        }

        public int getIconColor() {
            return this.iconColor;
        }

        public void setIconColor(int i2) {
            this.iconColor = i2;
        }

        public int getRadius() {
            return this.radius;
        }

        public void setRadius(int i2) {
            this.radius = i2;
        }

        public Typeface getTypeface() {
            return this.typeface;
        }

        public void setTypeface(Typeface typeface) {
            this.typeface = typeface;
        }

        public boolean isBold() {
            return this.isBold;
        }

        public void setBold(boolean z) {
            this.isBold = z;
        }

        public int getGravity() {
            return this.gravity;
        }

        public void setGravity(int i2) {
            this.gravity = i2;
        }

        public int getMargin() {
            return this.margin;
        }

        public void setMargin(int i2) {
            this.margin = i2;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int i2) {
            this.size = i2;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i2) {
            this.width = i2;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i2) {
            this.height = i2;
        }

        public int getSpacing() {
            return this.spacing;
        }

        public void setSpacing(int i2) {
            this.spacing = i2;
        }

        public int getStrokeWidth() {
            return this.strokeWidth;
        }

        public void setStrokeWidth(int i2) {
            this.strokeWidth = i2;
        }

        public int getStrokeColor() {
            return this.strokeColor;
        }

        public void setStrokeColor(int i2) {
            this.strokeColor = i2;
        }

        public int getPadding() {
            return this.padding;
        }

        public void setPadding(int i2) {
            this.padding = i2;
        }

        public String getFaString() {
            return this.faString;
        }

        public void setFaString(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.faString = str;
        }

        public Builder duration(ToastEnum myEnum) {
            Intrinsics.checkNotNullParameter(myEnum, "myEnum");
            this.myEnum = myEnum;
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

        public Builder icon(int i2) {
            this.icon = i2;
            return this;
        }

        public Builder icon(String faString) {
            Intrinsics.checkNotNullParameter(faString, "faString");
            this.faString = faString;
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

        public Builder typeface(Typeface typeface) {
            Intrinsics.checkNotNullParameter(typeface, "typeface");
            this.typeface = typeface;
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
            this.strokeWidth = i2;
            this.strokeColor = i3;
            return this;
        }
    }
}
