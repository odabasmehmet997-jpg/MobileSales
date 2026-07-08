package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.sales.model.Sales;

public interface ISalesNewRepository extends IBaseSalesRepository {
    ISqlHelper<Branch> getISqlHelperBranch();
    ISqlHelper<EDispatchAdditionalInfo> getISqlHelperEDespatchAdditionalInfo();
    ISqlHelper<Specodes> getISqlHelperSpecodes();
    Sales getSalesObject(int i2, boolean z);
    boolean isSalesPersonWarrantyCodePriceFirst();
}
