package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import kotlin.jvm.internal.Intrinsics;

public final class SalesCampaignRepository extends BaseSalesRepository {
    public ISqlHelper<ItemUnits> getISqlHelperItemUnits() {
        final ISqlHelper<ItemUnits> logoSqlHelper = (ISqlHelper<ItemUnits>) this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.product.model.database.ItemUnits>");
        return logoSqlHelper;
    }
    public ItemSlip getItemSlipObject(final int i2, final boolean z) {
        final Object object = this.getBaseRepository().getObject(i2, z);
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip");
        return (ItemSlip) object;
    }
}
