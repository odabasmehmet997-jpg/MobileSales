package com.proje.mobilesales.features.penetration.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public class BasePenetrationRepository extends BaseRepository {
    public BasePenetrationRepository() {
        super(baseRepositorybaseErp2);
    }

    public final void checkRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(workTimeControlProcessType, "type");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().checkRemoteWorkTimeControl(workTimeControlProcessType, responseListener);
    }
}
