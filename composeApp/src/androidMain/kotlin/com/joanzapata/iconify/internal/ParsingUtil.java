package com.joanzapata.iconify.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.joanzapata.iconify.Icon;
import java.util.List;


public class ParsingUtil {
  private static final String ANDROID_PACKAGE_NAME = "android";
    public static CharSequence parse(final Context context, final List<IconFontDescriptorWrapper> list, final CharSequence charSequence, final TextView textView) {
        final Context applicationContext = context.getApplicationContext ();
        if (null == charSequence) {
            return charSequence;
        }
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder (charSequence);
        recursivePrepareSpannableIndexes(applicationContext, charSequence.toString (), spannableStringBuilder, list, 0);
        if (hasAnimatedSpans(spannableStringBuilder)) {
            if (false) {
                throw new IllegalArgumentException ("You can't use \"spin\" without providing the target TextView.");
            }
            if (!(textView instanceof HasOnViewAttachListener)) {
                throw new IllegalArgumentException (textView.getClass ().getSimpleName () + " does not implement HasOnViewAttachListener. Please use IconTextView, IconButton or IconToggleButton.");
            }
            ((HasOnViewAttachListener) textView).setOnViewAttachListener (new AnonymousClass1 (textView));
        } else if (textView instanceof HasOnViewAttachListener) {
            ((HasOnViewAttachListener) textView).setOnViewAttachListener (null);
        }
        return spannableStringBuilder;
    }
    private static boolean hasAnimatedSpans(SpannableStringBuilder spannableStringBuilder) {
        for (CustomTypefaceSpan customTypefaceSpan : spannableStringBuilder.getSpans (0, spannableStringBuilder.length (), CustomTypefaceSpan.class)) {
            if (customTypefaceSpan.isAnimated ()) {
                return true;
            }
        }
        return false;
    }
    private static void recursivePrepareSpannableIndexes(Context context, String str, SpannableStringBuilder spannableStringBuilder, List<IconFontDescriptorWrapper> list, int i2) {
        int indexOf;
        int indexOf2;
        String[] strArr;
        int i3;
        int colorFromResource;
        String spannableStringBuilder2 = spannableStringBuilder.toString ();
        int indexOf3 = spannableStringBuilder2.indexOf ('{', i2);
        if (-1 == indexOf3 || -1 == (indexOf2 = (indexOf = spannableStringBuilder2.indexOf ('}', indexOf3)) + 1)) {
            return;
        }
        int i4 = indexOf3 + 1;
        String[] split = spannableStringBuilder2.substring (i4, indexOf).split (" ");
        int i5 = 0;
        String str2 = split[0];
        Icon icon = null;
        IconFontDescriptorWrapper iconFontDescriptorWrapper = null;
        for (int i6 = 0; i6 < list.size () && null == (icon = (iconFontDescriptorWrapper = list.get (i6)).getIcon (str2)); i6++) {
        }
        Icon icon2 = icon;
        if (null == icon2) {
            recursivePrepareSpannableIndexes(context, str, spannableStringBuilder, list, indexOf2);
            return;
        }
        boolean z = false;
        boolean z2 = false;
        float f2 = -1.0f;
        float f3 = -1.0f;
        int i7 = 1;
        int i8 = Integer.MAX_VALUE;
        while (i7 < split.length) {
            String str3 = split[i7];
            if ("spin".equalsIgnoreCase (str3)) {
                strArr = split;
                colorFromResource = i8;
                z = true;
            } else if ("baseline".equalsIgnoreCase (str3)) {
                strArr = split;
                colorFromResource = i8;
                z2 = true;
            } else {
                if (str3.matches ("([0-9]*(\\.[0-9]*)?)dp")) {
                    f2 = dpToPx(context, Float.valueOf (str3.substring (i5, str3.length () - 2)).floatValue ());
                } else if (str3.matches ("([0-9]*(\\.[0-9]*)?)sp")) {
                    f2 = spToPx(context, Float.valueOf (str3.substring (i5, str3.length () - 2)).floatValue ());
                } else {
                    if (str3.matches ("([0-9]*)px")) {
                        strArr = split;
                        f2 = Integer.valueOf (str3.substring (i5, str3.length () - 2)).intValue ();
                    } else {
                        if (str3.matches ("@dimen/(.*)")) {
                            strArr = split;
                            f2 = getPxFromDimen(context, context.getPackageName (), str3.substring (7));
                            if (0.0f > f2) {
                                throw new IllegalArgumentException ("Unknown resource " + str3 + " in \"" + str + "\"");
                            }
                        } else {
                            strArr = split;
                            if (str3.matches ("@android:dimen/(.*)")) {
                                f2 = getPxFromDimen(context, ANDROID_PACKAGE_NAME, str3.substring (15));
                                if (0.0f > f2) {
                                    throw new IllegalArgumentException ("Unknown resource " + str3 + " in \"" + str + "\"");
                                }
                            } else if (str3.matches ("([0-9]*(\\.[0-9]*)?)%")) {
                                i5 = 0;
                                f3 = Float.valueOf (str3.substring (0, str3.length () - 1)).floatValue () / 100.0f;
                            } else {
                                i3 = 0;
                                if (str3.matches ("#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})")) {
                                    colorFromResource = Color.parseColor (str3);
                                } else if (str3.matches ("@color/(.*)")) {
                                    colorFromResource = getColorFromResource(context, context.getPackageName (), str3.substring (7));
                                    if (Integer.MAX_VALUE == colorFromResource) {
                                        throw new IllegalArgumentException ("Unknown resource " + str3 + " in \"" + str + "\"");
                                    }
                                } else if (str3.matches ("@android:color/(.*)")) {
                                    colorFromResource = getColorFromResource(context, ANDROID_PACKAGE_NAME, str3.substring (15));
                                    if (Integer.MAX_VALUE == colorFromResource) {
                                        throw new IllegalArgumentException ("Unknown resource " + str3 + " in \"" + str + "\"");
                                    }
                                } else {
                                    throw new IllegalArgumentException ("Unknown expression " + str3 + " in \"" + str + "\"");
                                }
                                i7++;
                                i5 = i3;
                                split = strArr;
                                i8 = colorFromResource;
                            }
                        }
                        colorFromResource = i8;
                        i3 = 0;
                        i7++;
                        i5 = i3;
                        split = strArr;
                        i8 = colorFromResource;
                    }
                    colorFromResource = i8;
                }
                strArr = split;
                colorFromResource = i8;
            }
            i3 = i5;
            i7++;
            i5 = i3;
            split = strArr;
            i8 = colorFromResource;
        }
        SpannableStringBuilder replace = spannableStringBuilder.replace (indexOf3, indexOf2, "" + icon2.character ());
        replace.setSpan (new CustomTypefaceSpan (icon2, iconFontDescriptorWrapper.getTypeface (context), f2, f3, i8, z, z2), indexOf3, i4, 17);
        recursivePrepareSpannableIndexes(context, str, replace, list, indexOf3);
    }
    public static float getPxFromDimen(Context context, String str, String str2) {
        Resources resources = context.getResources ();
        int identifier = resources.getIdentifier (str2, "dimen", str);
        if (0 >= identifier) {
            return -1.0f;
        }
        return resources.getDimension (identifier);
    }
    public static int getColorFromResource(Context context, String str, String str2) {
        Resources resources = context.getResources ();
        int identifier = resources.getIdentifier (str2, TypedValues.Custom.S_COLOR, str);
        if (0 >= identifier) {
            return Integer.MAX_VALUE;
        }
        return resources.getColor (identifier);
    }
    public static float dpToPx(Context context, float f2) {
        return TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, f2, context.getResources ().getDisplayMetrics ());
    }
    public static float spToPx(Context context, float f2) {
        return TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_SP, f2, context.getResources ().getDisplayMetrics ());
    }
    static class AnonymousClass1 implements HasOnViewAttachListener.OnViewAttachListener {
        final TextView valtarget;
        boolean isAttached;

        AnonymousClass1(TextView textView) {
          this.valtarget = textView;
        }


        public void onAttach() {
          this.isAttached = true;
            ViewCompat.postOnAnimation (this.valtarget, new Runnable () { // from class: com.joanzapata.iconify.internal.ParsingUtil.1.1
                // java.lang.Runnable
                public void run() {
                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                    if (anonymousClass1.isAttached) {
                        anonymousClass1.valtarget.invalidate ();
                        ViewCompat.postOnAnimation (AnonymousClass1.this.valtarget, this);
                    }
                }
            });
        }


        public void onDetach() {
          this.isAttached = false;
        }
    }
}
