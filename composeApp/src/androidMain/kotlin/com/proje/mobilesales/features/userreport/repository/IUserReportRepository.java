package com.proje.mobilesales.features.userreport.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;

public interface IUserReportRepository extends IBaseRepository {
    void runUserDefinedSQL(UserReportsActivity.UserDefinedResponseListener responseListener, String str, String str2);
}
