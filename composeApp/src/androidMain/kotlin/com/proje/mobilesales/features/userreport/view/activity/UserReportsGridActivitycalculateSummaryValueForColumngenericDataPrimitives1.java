package com.proje.mobilesales.features.userreport.view.activity;

import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import java.util.stream.Stream;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
 

public final class UserReportsGridActivitycalculateSummaryValueForColumngenericDataPrimitives1 extends Lambda implements Function1<GenericData, Stream<? extends GenericDataPrimitive>> {
    public static final UserReportsGridActivitycalculateSummaryValueForColumngenericDataPrimitives1 INSTANCE = new UserReportsGridActivitycalculateSummaryValueForColumngenericDataPrimitives1();
    UserReportsGridActivitycalculateSummaryValueForColumngenericDataPrimitives1() {
        super(1);
    }

    public Stream<? extends GenericDataPrimitive> invoke(GenericData genericData) {
        Intrinsics.checkNotNullParameter(genericData, "b");
        return genericData.getGenericDataPrimitives().stream();
    }

    @Override
    public Stream<? extends GenericDataPrimitive> invoke(Object p1) {
        return Stream.empty();
    }
}
