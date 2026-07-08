package com.proje.mobilesales.features.userreport.view.activity;

import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
 
public final class UserReportsGridActivitycalculateSumForSummarytotalDoubleValue1 extends Lambda implements Function1<GenericDataPrimitive, Double> {
    public static final UserReportsGridActivitycalculateSumForSummarytotalDoubleValue1 INSTANCE = new UserReportsGridActivitycalculateSumForSummarytotalDoubleValue1();
    UserReportsGridActivitycalculateSumForSummarytotalDoubleValue1() {
        super(1);
    }
    public Double invoke(GenericDataPrimitive genericDataPrimitive) {
        Intrinsics.checkNotNullParameter(genericDataPrimitive, "b");
        return Double.valueOf(genericDataPrimitive.getValue() == null ? 0.0d : StringUtils.convertStringToDouble(genericDataPrimitive.getValue().toString()));
    }
    public Double invoke(Object p1) {
        return 0.0;
    }
}
