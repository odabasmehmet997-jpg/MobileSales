package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.base.BaseRepository;
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
import kotlin.jvm.internal.Intrinsics;
import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public class BaseSalesRepository extends BaseRepository implements IBaseSalesRepository {
    public BaseSalesRepository() {
        super(baseRepositorybaseErp2);
    }
    public void checkRemoteWorkTimeControl(final WorkTimeControlProcessType type, final ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getBaseErp().checkRemoteWorkTimeControl(type, responseListener);
    }
    public ISqlHelper<Item> getISqlHelperItem() {
        return (ISqlHelper<Item>) this.getBaseRepository().getLogoSqlHelper();
    }
    public ISqlHelper<EmailTemplate> getISqlHelperEmailTemplate() {
        final ISqlHelper logoSqlHelper = this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.EmailTemplate>");
        return logoSqlHelper;
    }
    public Disposable getEDocumentContent(final String ficheId, final ResponseListener<?> responseListener, final SalesType salesType, final Boolean bool) {
        Intrinsics.checkNotNullParameter(ficheId, "ficheId");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        return this.getBaseErp().getEDocumentContent(ficheId, responseListener, salesType, bool);
    }
    public ISqlHelper<Firm> getISqlHelperFirm() {
        return (ISqlHelper<Firm>) this.getBaseRepository().getLogoSqlHelper();
    }
    public RequiredFields getRequiredFields(final SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        final RequiredFields requiredFields = this.getBaseErp().getRequiredFields(salesType);
        Intrinsics.checkNotNullExpressionValue(requiredFields, "getRequiredFields(...)");
        return requiredFields;
    }
    public ISqlHelper<ShipAddress> getISqlHelperShipAddress() {
        final ISqlHelper logoSqlHelper = this.getBaseRepository().getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.dbmodel.ShipAddress>");
        return logoSqlHelper;
    }
    @Override
    public boolean getVATDefaultValue() {
        return false;
    }
    @Override
    public boolean setDeliveryDateAsToday() {
        return false;
    }
}
