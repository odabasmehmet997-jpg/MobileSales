package com.proje.mobilesales.features.reports.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;

public interface IItemReportsRepository extends IBaseRepository {
    void getReportOnline(ResponseListener<?> responseListener);
}
