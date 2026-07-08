package com.proje.mobilesales.features.tools.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.enums.UserMenu;
import com.proje.mobilesales.features.model.UserMenuRights;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class OtherMenuRepository extends BaseRepository implements IOtherMenuRepository {
    public OtherMenuRepository() {
        super(baseRepositorybaseErp2);
    }

    public boolean isGpsLocationInfo() {
        UserMenuRights userMenuRights = getUserMenuRights();
        UserMenu userMenu = UserMenu.GPS_LOCATION_INFO;
        return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
    }

    public boolean isWorkInfo() {
        UserMenuRights userMenuRights = getUserMenuRights();
        UserMenu userMenu = UserMenu.WORK_INFO;
        return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
    }
    public boolean isInvoiceAvgCalc() {
        UserMenuRights userMenuRights = getUserMenuRights();
        UserMenu userMenu = UserMenu.INVOICE_AVG_CALC;
        return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
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
