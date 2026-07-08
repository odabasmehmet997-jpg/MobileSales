package com.joanzapata.iconify.internal;

import android.content.Context;
import android.graphics.Typeface;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;
import java.util.HashMap;
import java.util.Map;

public class IconFontDescriptorWrapper {
    private final IconFontDescriptor iconFontDescriptor;
    private final Map<String, Icon> iconsByKey = new HashMap ();
    private Typeface cachedTypeface;
    public IconFontDescriptorWrapper(IconFontDescriptor iconFontDescriptor) {
        this.iconFontDescriptor = iconFontDescriptor;
        for (Icon icon : iconFontDescriptor.characters ()) {
          this.iconsByKey.put (icon.key (), icon);
        }
    }
    public Icon getIcon(String str) {
        return this.iconsByKey.get (str);
    }
    public IconFontDescriptor getIconFontDescriptor() {
        return this.iconFontDescriptor;
    }
    public Typeface getTypeface(Context context) {
        Typeface typeface = this.cachedTypeface;
        if (null != typeface) {
            return typeface;
        }
        synchronized (this) {
            try {
                Typeface typeface2 = this.cachedTypeface;
                if (null != typeface2) {
                    return typeface2;
                }
                Typeface createFromAsset = Typeface.createFromAsset (context.getAssets (), this.iconFontDescriptor.ttfFileName ());
              this.cachedTypeface = createFromAsset;
                return createFromAsset;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public boolean hasIcon(Icon icon) {
        return this.iconsByKey.containsValue (icon);
    }
}
