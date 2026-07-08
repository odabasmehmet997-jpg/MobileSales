package com.proje.mobilesales.features.gpsinfo.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class GpsInfoRepository extends BaseRepository implements IGpsInfoRepository {
    public static final Object baseRepositorybaseErp2 = null;
    public GpsInfoRepository() {
        super(baseRepositorybaseErp2);
    }
    public void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(custGpsInfo, "custGpsInfo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().saveCustomerLocation(custGpsInfo, responseListener);
    }
    public boolean getCustomerGpsEditMode() {
        return getBaseErp().getCustomerGpsEditMode();
    }
    public ArrayList<CustGpsInfo> getCustomerLocations() {
        ArrayList<CustGpsInfo> customerLocations = getLogoSqlHelper().getCustomerLocations();
        Intrinsics.checkNotNullExpressionValue(customerLocations, "getCustomerLocations(...)");
        return customerLocations;
    }
    public void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(custGpsInfo, "custGpsInfo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().updateCustomerLocation(custGpsInfo, responseListener);
    }
    public boolean getVATDefaultValue() {
        return false;
    }
    public boolean setDeliveryDateAsToday() {
        return false;
    }
}
