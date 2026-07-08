package com.proje.mobilesales.features.userreport.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.userreport.repository.IUserReportRepository;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import kotlin.jvm.internal.Intrinsics;

public final class UserReportViewModel extends BaseViewModel {
    private final IUserReportRepository repository;
    private final String tag;
    public UserReportViewModel(final IUserReportRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "UserReportViewModel";
    }
    public void runUserDefinedSQL(final UserReportsActivity.UserDefinedResponseListener responseListener, final String sql, final String str) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(sql, "sql");
        repository.runUserDefinedSQL(responseListener, sql, str);
    }
}
