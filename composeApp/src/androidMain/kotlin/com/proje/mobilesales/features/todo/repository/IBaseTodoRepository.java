package com.proje.mobilesales.features.todo.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.enums.MobileSalesUpdateType;
public interface IBaseTodoRepository extends IBaseRepository {
    void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int i2);
}
