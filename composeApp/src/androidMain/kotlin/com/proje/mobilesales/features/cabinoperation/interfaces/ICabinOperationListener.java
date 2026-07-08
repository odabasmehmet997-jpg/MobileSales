package com.proje.mobilesales.features.cabinoperation.interfaces;

import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;

public interface ICabinOperationListener {
    void getCabinDeliverToCustomer(Cabin cabin);

    void getCabinDeliverToStorage(Cabin cabin);

    void getCabinReceivedFromCustomer(Cabin cabin, int i2);
}
