package com.proje.mobilesales.features.penetration.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.penetration.model.Penetration;
import kotlin.jvm.internal.Intrinsics;

public final class PenetrationLineRepository extends BasePenetrationRepository {
    public   void getPenetrationExam(int i, ResponseListener<Penetration> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getPenetrationExam(i, responseListener);
    }
}
