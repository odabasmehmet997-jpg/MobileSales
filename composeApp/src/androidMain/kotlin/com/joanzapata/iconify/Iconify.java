package com.joanzapata.iconify;

import android.content.Context;
import android.widget.TextView;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;
import com.joanzapata.iconify.internal.ParsingUtil;
import java.util.ArrayList;
import java.util.List;

public enum Iconify {
  ;
  private static final List<IconFontDescriptorWrapper> iconFontDescriptors = new ArrayList<> ();
    public static IconifyInitializer with(IconFontDescriptor iconFontDescriptor) {
        return new IconifyInitializer (iconFontDescriptor);
    }
    public static void addIcons(TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            if (null == textView) {
                continue;
            }
            textView.setText (compute(textView.getContext (), textView.getText (), textView));
        }
    }
    private static void addIconFontDescriptor(IconFontDescriptor iconFontDescriptor) {
        for (IconFontDescriptorWrapper descriptor : iconFontDescriptors) {
            if (descriptor.getIconFontDescriptor ().ttfFileName ().equals (iconFontDescriptor.ttfFileName ())) {
                return;
            }
        }
        iconFontDescriptors.add (new IconFontDescriptorWrapper (iconFontDescriptor));
    }
    public static CharSequence compute(Context context, CharSequence charSequence) {
        return compute(context, charSequence, null);
    }
    public static CharSequence compute(Context context, CharSequence charSequence, TextView textView) {
        return ParsingUtil.parse (context, iconFontDescriptors, charSequence, textView);
    }
    public static IconFontDescriptorWrapper findTypefaceOf(com.joanzapata.iconify.Icon icon) {
        for (IconFontDescriptorWrapper iconFontDescriptorWrapper : iconFontDescriptors) {
            if (iconFontDescriptorWrapper.hasIcon (icon)) {
                return iconFontDescriptorWrapper;
            }
        }
        return null;
    }
    public static com.joanzapata.iconify.Icon findIconForKey(String str) {
        int size = iconFontDescriptors.size ();
        for (int i2 = 0; i2 < size; i2++) {
            Icon icon = iconFontDescriptors.get (i2).getIcon (str);
            if (null != icon) {
                return icon;
            }
        }
        return null;
    }
    public static class IconifyInitializer {
        public IconifyInitializer(IconFontDescriptor iconFontDescriptor) {
          Iconify.addIconFontDescriptor(iconFontDescriptor);
        }

        public IconifyInitializer with(IconFontDescriptor iconFontDescriptor) {
          Iconify.addIconFontDescriptor(iconFontDescriptor);
            return this;
        }
    }
}
