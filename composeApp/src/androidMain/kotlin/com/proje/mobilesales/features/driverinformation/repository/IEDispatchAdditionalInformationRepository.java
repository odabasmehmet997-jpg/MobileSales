package com.proje.mobilesales.features.driverinformation.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;

public interface IEDispatchAdditionalInformationRepository extends IBaseRepository {
    void getEDispatchAdditionalInfo(Customer customer, ResponseListener<EDispatchAdditionalInfo> responseListener);
}
