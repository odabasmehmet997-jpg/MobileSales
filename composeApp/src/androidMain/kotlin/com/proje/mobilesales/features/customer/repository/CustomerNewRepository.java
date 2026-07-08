package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerNewRepository extends BaseCustomerRepository {
    public ISqlHelper<ClCard> getISqlHelperClCard() {
        ISqlHelper logoSqlHelper = getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.customer.model.database.ClCard>");
        return logoSqlHelper;
    }

    public void saveNewCustomerFromSqlManager(CustomerNew customerNew, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseRepository().getSqlManager().saveNewCustomer(customerNew, responseListener);
    }

    public void updateNewCustomerFromSqlManager(CustomerNew customerNew, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseRepository().getSqlManager().updateNewCustomer(customerNew, responseListener);
    }
}
