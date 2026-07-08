package com.proje.mobilesales.features.tools.repository;


import com.proje.mobilesales.core.base.BaseRepository;

public final class AverageCalcRepository extends BaseRepository implements IAverageCalcRepository {
    @Override
    public boolean getVATDefaultValue() {
        return false;
    }

    @Override
    public boolean setDeliveryDateAsToday() {
        return false;
    }
}

