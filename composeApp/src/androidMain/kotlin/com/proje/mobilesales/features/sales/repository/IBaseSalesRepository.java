package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Firm;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.model.RequiredFields;
import com.proje.mobilesales.features.product.model.database.Item;
import io.reactivex.disposables.Disposable;

public interface IBaseSalesRepository extends IBaseRepository {
    void checkRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> responseListener);
    Disposable getEDocumentContent(String str, ResponseListener<?> responseListener, SalesType salesType, Boolean bool);
    ISqlHelper<EmailTemplate> getISqlHelperEmailTemplate();
    ISqlHelper<Firm> getISqlHelperFirm();
    ISqlHelper<Item> getISqlHelperItem();
    ISqlHelper<ShipAddress> getISqlHelperShipAddress();
    RequiredFields getRequiredFields(SalesType salesType);
}
