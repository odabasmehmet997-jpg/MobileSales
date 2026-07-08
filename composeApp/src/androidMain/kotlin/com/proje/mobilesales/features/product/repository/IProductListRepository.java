package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.FormGroupModel;
import com.proje.mobilesales.features.model.Resource;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import java.util.List;
import io.reactivex.Observable;

public interface IProductListRepository extends IBaseProductRepository {
    Observable<Resource<List<FormGroupModel>>> getFormGroups(int i);
    ISqlHelper<ItemPrice> getISqlHelperItemPrice();
    ISqlHelper<Service> getISqlHelperService();
    void getRecommendedProducts(int i, ResponseListener<?> responseListener);
}
