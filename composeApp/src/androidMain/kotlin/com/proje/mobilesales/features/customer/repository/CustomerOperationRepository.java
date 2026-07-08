package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.model.CustomerReports;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerOperationRepository extends BaseCustomerRepository implements ICustomerOperationRepository {
    public ISqlHelper<UserReports> getISqlHelperUserReports() {
        ISqlHelper logoSqlHelper = getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.userreport.model.database.UserReports>");
        return logoSqlHelper;
    }
    public CustomerReports getCustomerReports() {
        CustomerReports customerReports = getBaseErp().customerReports();
        Intrinsics.checkNotNullExpressionValue(customerReports, "customerReports(...)");
        return customerReports;
    }
}
