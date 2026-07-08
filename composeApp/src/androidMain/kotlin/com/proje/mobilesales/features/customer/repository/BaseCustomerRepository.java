package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public class BaseCustomerRepository extends BaseRepository implements IBaseCustomerRepository {
    public BaseCustomerRepository() {
        super(baseRepositorybaseErp2);
    }

    public void checkRemoteWorkTimeControl(WorkTimeControlProcessType type, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().checkRemoteWorkTimeControl(type, responseListener);
    }
    public ISqlHelper<ClCard> getISqlHelperCustomers() {
        ISqlHelper logoSqlHelper = getLogoSqlHelper();
        Intrinsics.checkNotNull(logoSqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.ISqlHelper<com.proje.mobilesales.features.customer.model.database.ClCard>");
        return logoSqlHelper;
    }
}
