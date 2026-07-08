package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.base.BaseRepository;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public abstract class BaseProductRepository extends BaseRepository implements IBaseProductRepository {
    public BaseProductRepository() {
        super(baseRepositorybaseErp2);
    }
}
