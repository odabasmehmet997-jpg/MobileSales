package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import java.util.List;

public interface ISalesDetailRepository extends IBaseSalesRepository {
    ISqlHelper<ItemPrice> getISqlHelperItemPrice();
    ISqlHelper<MuhRefCodes> getISqlHelperMuhRefCodes();
    ISqlHelper<Units> getISqlHelperUnits();
    ISqlHelper<WareHouse> getISqlHelperWareHouse();
    List<ItemUnits> getItemUnits(int i2);
}
