package com.proje.mobilesales.features.licence.viewmodel;

import com.proje.mobilesales.features.licence.model.database.LicenseInfo;
import com.proje.mobilesales.features.licence.repository.LicenseRepository;
import kotlin.jvm.internal.Intrinsics;

public final class LicenseViewModel {
    private final LicenseRepository repository;

    public LicenseViewModel(LicenseRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public void insertLicenseInfo(LicenseInfo licenseInfo) {
        Intrinsics.checkNotNullParameter(licenseInfo, "licenseInfo");
        this.repository.insertLicenseInfo(licenseInfo);
    }
}
