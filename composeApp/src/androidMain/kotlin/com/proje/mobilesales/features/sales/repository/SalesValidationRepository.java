package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClCard;

public final class SalesValidationRepository extends BaseSalesRepository {
    public ISqlHelper<ClCard> getISqlHelperClCard() {
        return (ISqlHelper<ClCard>) this.getBaseRepository().getLogoSqlHelper();
    }
}
