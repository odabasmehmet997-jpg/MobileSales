package com.proje.mobilesales.features.userreport.view.activity;

import java.util.Date;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;


public final class UserReportsGridActivitycalculateMaxForSummarymaxDate2 extends Lambda implements Function2<Date, Date, Integer> {
    public static final UserReportsGridActivitycalculateMaxForSummarymaxDate2 INSTANCE = new UserReportsGridActivitycalculateMaxForSummarymaxDate2();
    UserReportsGridActivitycalculateMaxForSummarymaxDate2() {
        super(2);
    }
    public Unit invoke(Date date, Date date2) {
        Intrinsics.checkNotNullParameter(date, "obj");
        return Integer.valueOf(date.compareTo(date2));
    }
    @Override
    public Integer invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return 0;
    }
}
