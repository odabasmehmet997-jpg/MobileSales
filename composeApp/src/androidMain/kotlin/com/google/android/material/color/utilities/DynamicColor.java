package com.google.android.material.color.utilities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache;
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    /*  INFO: Access modifiers changed from: private */
    public static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    public DynamicColor(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z, @Nullable Function<DynamicScheme, DynamicColor> function3, @Nullable Function<DynamicScheme, DynamicColor> function4, @Nullable ContrastCurve contrastCurve, @Nullable Function<DynamicScheme, ToneDeltaPair> function5) {
        this.hctCache = new HashMap<>();
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public DynamicColor(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z, @Nullable Function<DynamicScheme, DynamicColor> function3, @Nullable Function<DynamicScheme, DynamicColor> function4, @Nullable ContrastCurve contrastCurve, @Nullable Function<DynamicScheme, ToneDeltaPair> function5, @Nullable Function<DynamicScheme, Double> function6) {
        this.hctCache = new HashMap<>();
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve;
        this.toneDeltaPair = function5;
        this.opacity = function6;
    }

    @NonNull
    public static DynamicColor fromPalette(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2) {
        return new DynamicColor(str, function, function2, false, null, null, null, null);
    }

    @NonNull
    public static DynamicColor fromPalette(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z) {
        return new DynamicColor(str, function, function2, z, null, null, null, null);
    }

    @NonNull
    public static DynamicColor fromArgb(@NonNull String str, int i2) {
        final Hct hctFromInt = Hct.fromInt(i2);
        final TonalPalette tonalPaletteFromInt = TonalPalette.fromInt(i2);
        return fromPalette(str, new Function() { // from class: com.google.android.material.color.utilities.DynamicColor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public Object apply(Object obj) {
                return DynamicColor.lambda$fromArgb$0(tonalPaletteFromInt, (DynamicScheme) obj);
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.DynamicColor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public Object apply(Object obj) {
                return DynamicColor.lambda$fromArgb$1(hctFromInt, (DynamicScheme) obj);
            }
        });
    }

    /*  INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double lambda$fromArgb$1(Hct hct, DynamicScheme dynamicScheme) {
        return Double.valueOf(hct.getTone());
    }

    public int getArgb(@NonNull DynamicScheme dynamicScheme) {
        int i2 = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i2;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(function.apply(dynamicScheme).doubleValue() * 255.0d)) << 24) | (16777215 & i2);
    }

    @NonNull
    public Hct getHct(@NonNull DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct hct2 = this.palette.apply(dynamicScheme).getHct(getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, hct2);
        return hct2;
    }

    public double getTone(@NonNull DynamicScheme dynamicScheme) {
        double dMax;
        double dMin;
        boolean z = dynamicScheme.contrastLevel < 0.0d;
        Function<DynamicScheme, ToneDeltaPair> function = this.toneDeltaPair;
        if (function != null) {
            ToneDeltaPair toneDeltaPairApply = function.apply(dynamicScheme);
            DynamicColor roleA = toneDeltaPairApply.getRoleA();
            DynamicColor roleB = toneDeltaPairApply.getRoleB();
            double delta = toneDeltaPairApply.getDelta();
            TonePolarity polarity = toneDeltaPairApply.getPolarity();
            boolean stayTogether = toneDeltaPairApply.getStayTogether();
            double tone = this.background.apply(dynamicScheme).getTone(dynamicScheme);
            boolean z2 = polarity == TonePolarity.NEARER || (polarity == TonePolarity.LIGHTER && !dynamicScheme.isDark) || (polarity == TonePolarity.DARKER && dynamicScheme.isDark);
            DynamicColor dynamicColor = z2 ? roleA : roleB;
            DynamicColor dynamicColor2 = z2 ? roleB : roleA;
            boolean zEquals = this.name.equals(dynamicColor.name);
            double d2 = dynamicScheme.isDark ? 1.0d : -1.0d;
            double contrast = dynamicColor.contrastCurve.getContrast(dynamicScheme.contrastLevel);
            double contrast2 = dynamicColor2.contrastCurve.getContrast(dynamicScheme.contrastLevel);
            double dDoubleValue = dynamicColor.tone.apply(dynamicScheme).doubleValue();
            if (Contrast.ratioOfTones(tone, dDoubleValue) < contrast) {
                dDoubleValue = foregroundTone(tone, contrast);
            }
            double dDoubleValue2 = dynamicColor2.tone.apply(dynamicScheme).doubleValue();
            if (Contrast.ratioOfTones(tone, dDoubleValue2) < contrast2) {
                dDoubleValue2 = foregroundTone(tone, contrast2);
            }
            if (z) {
                dDoubleValue = foregroundTone(tone, contrast);
                dDoubleValue2 = foregroundTone(tone, contrast2);
            }
            if ((dDoubleValue2 - dDoubleValue) * d2 < delta) {
                double d3 = delta * d2;
                dDoubleValue2 = MathUtils.clampDouble(0.0d, 100.0d, dDoubleValue + d3);
                if ((dDoubleValue2 - dDoubleValue) * d2 < delta) {
                    dDoubleValue = MathUtils.clampDouble(0.0d, 100.0d, dDoubleValue2 - d3);
                }
            }
            if (50.0d > dDoubleValue || dDoubleValue >= 60.0d) {
                if (50.0d > dDoubleValue2 || dDoubleValue2 >= 60.0d) {
                    dMax = dDoubleValue2;
                } else if (!stayTogether) {
                    dMax = d2 > 0.0d ? 60.0d : 49.0d;
                } else if (d2 > 0.0d) {
                    dMax = Math.max(dDoubleValue2, (delta * d2) + 60.0d);
                    dDoubleValue = 60.0d;
                } else {
                    dMin = Math.min(dDoubleValue2, (delta * d2) + 49.0d);
                    dMax = dMin;
                    dDoubleValue = 49.0d;
                }
            } else if (d2 > 0.0d) {
                dMax = Math.max(dDoubleValue2, (delta * d2) + 60.0d);
                dDoubleValue = 60.0d;
            } else {
                dMin = Math.min(dDoubleValue2, (delta * d2) + 49.0d);
                dMax = dMin;
                dDoubleValue = 49.0d;
            }
            return zEquals ? dDoubleValue : dMax;
        }
        double dDoubleValue3 = this.tone.apply(dynamicScheme).doubleValue();
        Function<DynamicScheme, DynamicColor> function2 = this.background;
        if (function2 == null) {
            return dDoubleValue3;
        }
        double tone2 = function2.apply(dynamicScheme).getTone(dynamicScheme);
        double contrast3 = this.contrastCurve.getContrast(dynamicScheme.contrastLevel);
        if (Contrast.ratioOfTones(tone2, dDoubleValue3) < contrast3) {
            dDoubleValue3 = foregroundTone(tone2, contrast3);
        }
        if (z) {
            dDoubleValue3 = foregroundTone(tone2, contrast3);
        }
        double d4 = (!this.isBackground || 50.0d > dDoubleValue3 || dDoubleValue3 >= 60.0d) ? dDoubleValue3 : Contrast.ratioOfTones(49.0d, tone2) >= contrast3 ? 49.0d : 60.0d;
        if (this.secondBackground == null) {
            return d4;
        }
        double tone3 = this.background.apply(dynamicScheme).getTone(dynamicScheme);
        double tone4 = this.secondBackground.apply(dynamicScheme).getTone(dynamicScheme);
        double dMax2 = Math.max(tone3, tone4);
        double dMin2 = Math.min(tone3, tone4);
        if (Contrast.ratioOfTones(dMax2, d4) >= contrast3 && Contrast.ratioOfTones(dMin2, d4) >= contrast3) {
            return d4;
        }
        double dLighter = Contrast.lighter(dMax2, contrast3);
        double dDarker = Contrast.darker(dMin2, contrast3);
        ArrayList arrayList = new ArrayList();
        if (dLighter != -1.0d) {
            arrayList.add(Double.valueOf(dLighter));
        }
        if (dDarker != -1.0d) {
            arrayList.add(Double.valueOf(dDarker));
        }
        if (tonePrefersLightForeground(tone3) || tonePrefersLightForeground(tone4)) {
            if (dLighter == -1.0d) {
                return 100.0d;
            }
            return dLighter;
        }
        if (arrayList.size() == 1) {
            return ((Double) arrayList.get(0)).doubleValue();
        }
        if (dDarker == -1.0d) {
            return 0.0d;
        }
        return dDarker;
    }

    public static double foregroundTone(double d2, double d3) {
        double dLighterUnsafe = Contrast.lighterUnsafe(d2, d3);
        double dDarkerUnsafe = Contrast.darkerUnsafe(d2, d3);
        double dRatioOfTones = Contrast.ratioOfTones(dLighterUnsafe, d2);
        double dRatioOfTones2 = Contrast.ratioOfTones(dDarkerUnsafe, d2);
        if (tonePrefersLightForeground(d2)) {
            return (dRatioOfTones >= d3 || dRatioOfTones >= dRatioOfTones2 || ((Math.abs(dRatioOfTones - dRatioOfTones2) > 0.1d ? 1 : (Math.abs(dRatioOfTones - dRatioOfTones2) == 0.1d ? 0 : -1)) < 0 && (dRatioOfTones > d3 ? 1 : (dRatioOfTones == d3 ? 0 : -1)) < 0 && (dRatioOfTones2 > d3 ? 1 : (dRatioOfTones2 == d3 ? 0 : -1)) < 0)) ? dLighterUnsafe : dDarkerUnsafe;
        }
        return (dRatioOfTones2 >= d3 || dRatioOfTones2 >= dRatioOfTones) ? dDarkerUnsafe : dLighterUnsafe;
    }

    public static double enableLightForeground(double d2) {
        if (!tonePrefersLightForeground(d2) || toneAllowsLightForeground(d2)) {
            return d2;
        }
        return 49.0d;
    }

    public static boolean tonePrefersLightForeground(double d2) {
        return Math.round(d2) < 60;
    }

    public static boolean toneAllowsLightForeground(double d2) {
        return Math.round(d2) <= 49;
    }
}
