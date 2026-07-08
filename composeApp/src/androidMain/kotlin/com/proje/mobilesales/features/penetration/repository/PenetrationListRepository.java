package com.proje.mobilesales.features.penetration.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import kotlin.jvm.internal.Intrinsics;
public final class PenetrationListRepository extends BasePenetrationRepository {

    public   ISqlHelper<Penetration> getISqlHelperPenetration() {
        ISqlHelper<Penetration> logoSqlHelper = (ISqlHelper<Penetration>) getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.penetration.model.database.Penetration>");
        return logoSqlHelper;
    }
}
