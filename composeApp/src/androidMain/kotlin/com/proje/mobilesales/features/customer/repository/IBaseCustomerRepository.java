package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClCard;
public interface IBaseCustomerRepository extends IBaseRepository {
    void checkRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> responseListener);

    ISqlHelper<ClCard> getISqlHelperCustomers();
}
