package com.proje.mobilesales.features.gpsinfo.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import java.util.ArrayList;

public interface IGpsInfoRepository extends IBaseRepository {
    boolean getCustomerGpsEditMode();
    ArrayList<CustGpsInfo> getCustomerLocations();
    void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener);
    void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener);
}
