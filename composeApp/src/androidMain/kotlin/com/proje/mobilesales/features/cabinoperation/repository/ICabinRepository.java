package com.proje.mobilesales.features.cabinoperation.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;

public interface ICabinRepository extends IBaseRepository {
    void deliverCabinToCustomer(Cabin cabin, int i2);
    void deliverCabinToStorage(Cabin cabin, int i2);
    Disposable getCabinList(String str, ResponseListener<ArrayList<Cabin>> responseListener);
    void receiveCabinFromCustomer(Cabin cabin, int i2, int i3);
}
