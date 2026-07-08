package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.model.CustomerReports;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
public interface ICustomerOperationRepository extends IBaseCustomerRepository {
    CustomerReports getCustomerReports();

    ISqlHelper<UserReports> getISqlHelperUserReports();
}
