package com.proje.mobilesales.features.cabinoperation.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class CabinRepository extends BaseRepository implements ICabinRepository {
    public CabinRepository() {
        super(baseRepositorybaseErp2);
    }

    public Disposable getCabinList(String query, ResponseListener<ArrayList<Cabin>> responseListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable cabinList = getSqlManager().getCabinList(query, responseListener);
        Intrinsics.checkNotNullExpressionValue(cabinList, "getCabinList(...)");
        return cabinList;
    }
    public void deliverCabinToCustomer(Cabin cabin, int i2) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        getBaseErp().deliverCabinToCustomer(cabin, i2);
    }
    public void deliverCabinToStorage(Cabin cabin, int i2) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        getBaseErp().deliverCabinToStorage(cabin, i2);
    }
    public void receiveCabinFromCustomer(Cabin cabin, int i2, int i3) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        getBaseErp().receiveCabinFromCustomer(cabin, i2, i3);
    }
}
