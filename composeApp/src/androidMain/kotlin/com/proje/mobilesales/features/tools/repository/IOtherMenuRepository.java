package com.proje.mobilesales.features.tools.repository;

import com.proje.mobilesales.core.base.IBaseRepository;

public interface IOtherMenuRepository extends IBaseRepository {
    boolean isGpsLocationInfo();
    boolean isInvoiceAvgCalc();
    boolean isWorkInfo();
}
