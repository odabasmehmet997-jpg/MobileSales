package com.proje.mobilesales.features.userreport.view.activity;

import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
 
public final class UserReportsGridActivitycalculateMaxForSummarytotalValue1 extends Lambda implements Function1<GenericDataPrimitive, Long> {
    public static final UserReportsGridActivitycalculateMaxForSummarytotalValue1 INSTANCE = new UserReportsGridActivitycalculateMaxForSummarytotalValue1();
    UserReportsGridActivitycalculateMaxForSummarytotalValue1() {
        super(1);
    }
    public Long invoke(GenericDataPrimitive genericDataPrimitive) {
        Intrinsics.checkNotNullParameter(genericDataPrimitive, "b");
        return Long.valueOf(genericDataPrimitive.getValue() == null ? 0 : StringUtils.convertStringToLong(genericDataPrimitive.getValue().toString()));
    }
    public Long invoke(Object p1) {
        return 0L;
    }
}
