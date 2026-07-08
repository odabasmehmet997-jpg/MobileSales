package com.proje.mobilesales.core.sql;

import com.proje.mobilesales.core.enums.ErpType;
import io.reactivex.Scheduler;

public abstract class BaseSqlManagerFactory {
    public abstract SqlManager<Object> getSql(ErpType erpType, Scheduler scheduler);
}
