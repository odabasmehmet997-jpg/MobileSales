package com.proje.mobilesales.features.visit.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import java.util.ArrayList;

public interface IVisitRepository extends IBaseRepository {
    void deleteVisitFicheLocal(int i2, int i3, ResponseListener<Integer> responseListener);
    void getVisitExam(int i2, ResponseListener<Visit> responseListener);
    void getVisitList(String str, ResponseListener<ArrayList<VisitInfoShort>> responseListener);
    void saveVisitFiche(Visit visit, ResponseListener<Boolean> responseListener);
    void updateVisitFiche(Visit visit, ResponseListener<Boolean> responseListener);
}
