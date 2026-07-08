package com.proje.mobilesales.features.settings.repository;

import com.proje.mobilesales.core.base.BaseRepository;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class PrintPreferencesRepository extends BaseRepository implements IPrintPreferencesRepository {
    public PrintPreferencesRepository() {
        super(baseRepositorybaseErp2);
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
