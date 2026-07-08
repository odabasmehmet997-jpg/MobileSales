package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import org.kxml2.wap.Wbxml;
import java.util.ArrayList;
import java.util.List;

public enum ColorTemplate {
    ;
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb (207, 248, 246), Color.rgb (148, 212, 212), Color.rgb (136, 180, 187), Color.rgb (118, 174, 175), Color.rgb (42, 109, Wbxml.EXT_T_2)};
    public static final int[] JOYFUL_COLORS = {Color.rgb (217, 80, 138), Color.rgb (254, 149, 7), Color.rgb (254, 247, 120), Color.rgb (106, 167, 134), Color.rgb (53, Wbxml.EXT_2, 209)};
    public static final int[] PASTEL_COLORS = {Color.rgb (64, 89, 128), Color.rgb (149, 165, 124), Color.rgb (217, 184, 162), Color.rgb (191, 134, 134), Color.rgb (179, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb (Wbxml.EXT_1, 37, 82), Color.rgb (255, 102, 0), Color.rgb (245, 199, 0), Color.rgb (106, 150, 31), Color.rgb (179, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb (Wbxml.EXT_0, 255, 140), Color.rgb (255, 247, 140), Color.rgb (255, 208, 140), Color.rgb (140, 234, 255), Color.rgb (255, 140, 157)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};

    public static int getColorWithAlphaComponent(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((i2 & 255) << 24);
    }

    public static int rgb(String str) {
        int parseLong = (int) Long.parseLong (str.replace ("#", ""), 16);
        return Color.rgb ((parseLong >> 16) & 255, (parseLong >> 8) & 255, parseLong & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb (51, 181, 229);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        ArrayList arrayList = new ArrayList ();
        for (int i : iArr) {
            arrayList.add (Integer.valueOf (resources.getColor (i)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        ArrayList arrayList = new ArrayList ();
        for (int i : iArr) {
            arrayList.add (Integer.valueOf (i));
        }
        return arrayList;
    }
}
