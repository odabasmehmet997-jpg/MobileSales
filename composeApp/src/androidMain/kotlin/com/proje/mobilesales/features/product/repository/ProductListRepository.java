package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.FormGroupModel;
import com.proje.mobilesales.features.model.Resource;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import io.reactivex.Observable;

public abstract   class ProductListRepository extends BaseProductRepository implements IProductListRepository {
    public ISqlHelper<Service> getISqlHelperService() {
        ISqlHelper<Service> logoSqlHelper = getBaseErp().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.Service>");
        return logoSqlHelper;
    }
    public ISqlHelper<ItemPrice> getISqlHelperItemPrice() {
        ISqlHelper<ItemPrice> logoSqlHelper = getBaseErp().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.product.model.database.ItemPrice>");
        return logoSqlHelper;
    }
    public Observable<Resource<List<FormGroupModel>>> getFormGroups(int i) {
        Observable<Resource<List<FormGroupModel>>> formGroups = getBaseErp().getFormGroups(i);
        Intrinsics.checkNotNull(formGroups, "null cannot be cast to non-null type io.reactivex.Observable<com.proje.mobilesales.features.model.Resource<kotlin.collections.List<com.proje.mobilesales.features.model.FormGroupModel>>>");
        return formGroups;
    }
    public void getRecommendedProducts(int i, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getRecommendedProducts(i, responseListener);
    }
}
