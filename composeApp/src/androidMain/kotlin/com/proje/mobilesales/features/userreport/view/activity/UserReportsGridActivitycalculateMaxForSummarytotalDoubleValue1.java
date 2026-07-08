package com.proje.mobilesales.features.userreport.view.activity;

import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

public final class UserReportsGridActivitycalculateMaxForSummarytotalDoubleValue1 extends Lambda implements Function1<GenericDataPrimitive, Double> {
    public static final UserReportsGridActivitycalculateMaxForSummarytotalDoubleValue1 INSTANCE = new UserReportsGridActivitycalculateMaxForSummarytotalDoubleValue1();
    UserReportsGridActivitycalculateMaxForSummarytotalDoubleValue1() {
        super(1);
    }
    public Double invoke(GenericDataPrimitive genericDataPrimitive) {
        Intrinsics.checkNotNullParameter(genericDataPrimitive, "b");
        return Double.valueOf(genericDataPrimitive.getValue() == null ? 0.0d : StringUtils.convertStringToDouble(genericDataPrimitive.getValue().toString()));
    }

    public Double invoke(CoroutineScope p1, Continuation<? super Result<Double>> p2) {
        return 0.0d;
    }
    @Override
    public Double invoke(Object p1) {
        return 0.0;
    }
}
