package com.proje.mobilesales.features.visit.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class VisitRepository extends BaseRepository implements IVisitRepository {
    public VisitRepository() {
        super(baseRepositorybaseErp2);
    }

    public void saveVisitFiche(Visit visit, ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(visit, "visit");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getSqlManager().saveVisitFiche(visit, listener);
    }
    public void updateVisitFiche(Visit visit, ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(visit, "visit");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getSqlManager().updateVisitFiche(visit, listener);
    }
    public void getVisitList(String query, ResponseListener<ArrayList<VisitInfoShort>> responseListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getVisitList(query, responseListener);
    }
    public void deleteVisitFicheLocal(int i2, int i3, ResponseListener<Integer> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getSqlManager().deleteVisitFicheLocal(i2, i3, listener);
    }
    public void getVisitExam(int i2, ResponseListener<Visit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getSqlManager().getVisitExam(i2, listener);
    }

    @Override
    public boolean getVATDefaultValue() {
        return false;
    }

    @Override
    public boolean setDeliveryDateAsToday() {
        return false;
    }
}
