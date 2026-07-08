package com.proje.mobilesales.features.userreport.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;
public abstract  class UserReportRepository extends BaseRepository implements IUserReportRepository {
    public UserReportRepository() {
        super(baseRepositorybaseErp2);
    }
    public void runUserDefinedSQL(final UserReportsActivity.UserDefinedResponseListener responseListener, final String sql, final String param) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(sql, "sql");
        Intrinsics.checkNotNullParameter(param, "param");
        this.getBaseErp().runUserDefinedSQL(responseListener, sql, param);
    }
}