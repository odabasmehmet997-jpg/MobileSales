package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class SalesDetailRepository extends BaseSalesRepository implements ISalesDetailRepository {
    public ISqlHelper<WareHouse> getISqlHelperWareHouse() {
        final ISqlHelper logoSqlHelper = this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.WareHouse>");
        return logoSqlHelper;
    }
    public ISqlHelper<MuhRefCodes> getISqlHelperMuhRefCodes() {
        final ISqlHelper logoSqlHelper = this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.MuhRefCodes>");
        return logoSqlHelper;
    }
    public ISqlHelper<Units> getISqlHelperUnits() {
        final ISqlHelper logoSqlHelper = this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.Units>");
        return logoSqlHelper;
    }
    public ISqlHelper<ItemPrice> getISqlHelperItemPrice() {
        return (ISqlHelper<ItemPrice>) this.getBaseRepository().getLogoSqlHelper();
    }
    public List<ItemUnits> getItemUnits(final int i2) {
        final List<ItemUnits> itemUnits = this.getBaseRepository().getLogoSqlHelper().getItemUnits(i2);
        Intrinsics.checkNotNullExpressionValue(itemUnits, "getItemUnits(...)");
        return itemUnits;
    }
}
