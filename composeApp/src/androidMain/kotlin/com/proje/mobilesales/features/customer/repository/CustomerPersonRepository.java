package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerPersonRepository extends BaseCustomerRepository {
    public ISqlHelper<ClCardIncharge> getISqlHelperClCardIncharge() {
        ISqlHelper logoSqlHelper = getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.customer.model.database.ClCardIncharge>");
        return logoSqlHelper;
    }
}
