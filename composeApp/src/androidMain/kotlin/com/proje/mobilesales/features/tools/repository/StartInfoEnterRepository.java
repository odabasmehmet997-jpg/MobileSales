package com.proje.mobilesales.features.tools.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class StartInfoEnterRepository extends BaseRepository implements IStartInfoEnterRepository {
    public StartInfoEnterRepository() {
        super(baseRepositorybaseErp2);
    }

    public void sendStartInfoEnter(StartInfo startInfo) {
        Intrinsics.checkNotNullParameter(startInfo, "startInfo");
        getBaseErp().sendStartInfoEnter(startInfo);
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
