package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.sales.model.Sales;
import kotlin.jvm.internal.Intrinsics;
 
public final class SalesNewRepository extends BaseSalesRepository implements ISalesNewRepository {
    
    public Sales getSalesObject(final int i2, final boolean z) {
        final Object object = this.getBaseRepository().getObject(i2, z);
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
        return (Sales) object;
    }
    public ISqlHelper<EDispatchAdditionalInfo> getISqlHelperEDespatchAdditionalInfo() {
        final ISqlHelper<EDispatchAdditionalInfo> logoSqlHelper = this.getBaseErp().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo>");
        return logoSqlHelper;
    }
    public ISqlHelper<Specodes> getISqlHelperSpecodes() {
        return this.getBaseErp().getLogoSqlHelper();
    }
    public ISqlHelper<Branch> getISqlHelperBranch() {
        return this.getBaseErp().getLogoSqlHelper();
    }
    public boolean isSalesPersonWarrantyCodePriceFirst() {
        return this.getBaseErp().getSalesPersonWarrantyCodePriceFirst();
    }
}
