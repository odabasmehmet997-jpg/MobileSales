package com.proje.mobilesales.features.licence.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.features.licence.model.database.LicenseInfo;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class LicenseRepository extends BaseRepository {
    public LicenseRepository() {
        super(baseRepositorybaseErp2);
    }

    public long insertLicenseInfo(LicenseInfo licenseInfo) {
        Intrinsics.checkNotNullParameter(licenseInfo, "licenseInfo");
        return getBaseErp().getLogoSqlBriteDatabase().insert(licenseInfo, true);
    }
}
