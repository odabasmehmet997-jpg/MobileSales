package com.proje.mobilesales.features.todo.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.enums.MobileSalesUpdateType;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class BaseTodoRepository extends BaseRepository implements IBaseTodoRepository {
    public BaseTodoRepository() {
        super(baseRepositorybaseErp2);
    }
    public void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int i2) {
        getBaseErp().updateDataLogo(mobileSalesUpdateType, i2);
    }
    @Override
    public boolean getVATDefaultValue() {
        return false;
    }
    @Override
    public boolean setDeliveryDateAsToday() {
        return false;
    }
}
