package com.proje.mobilesales.features.visit.viewmodel;
 
import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import com.proje.mobilesales.features.visit.repository.IVisitRepository;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class VisitViewModel extends BaseViewModel {
    private final IVisitRepository repository;
    private final String tag;
    public VisitViewModel(IVisitRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.tag = "VisitViewModel";
    }
    public  void saveVisitFiche(Visit visit, ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(visit, "visit");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository.saveVisitFiche(visit, listener);
        Log.i(this.tag, "SaveVisitFiche");
    }
    public  void updateVisitFiche(Visit visit, ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(visit, "visit");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository.updateVisitFiche(visit, listener);
        Log.i(this.tag, "UpdateVisitFiche");
    }
    public  void getVisitList(String query, ResponseListener<ArrayList<VisitInfoShort>> listener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository.getVisitList(query, listener);
        Log.i(this.tag, "GetVisitList");
    }
    public  void deleteVisitFicheLocal(int i2, int i3, ResponseListener<Integer> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository.deleteVisitFicheLocal(i2, i3, listener);
        Log.i(this.tag, "DeleteVisitFicheLocal");
    }
    public  void getVisitExam(int i2, ResponseListener<Visit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.repository.getVisitExam(i2, listener);
        Log.i(this.tag, "GetVisitExam");
    }
}
