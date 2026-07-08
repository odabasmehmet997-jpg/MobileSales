package com.proje.mobilesales.features.cabinoperation.interfaces;

import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;

public interface INewCabinOperationListener {
    void addAvailableCabinToCustomer(Cabin cabin);
}
