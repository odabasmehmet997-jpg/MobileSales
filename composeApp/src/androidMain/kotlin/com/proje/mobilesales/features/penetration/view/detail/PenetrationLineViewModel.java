package com.proje.mobilesales.features.penetration.view.detail;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.repository.PenetrationLineRepository;
import com.proje.mobilesales.features.penetration.viewmodel.BasePenetrationViewModel;
import kotlin.jvm.internal.Intrinsics;

public final class PenetrationLineViewModel extends BasePenetrationViewModel {
    private final PenetrationLineRepository repository;
    public PenetrationLineViewModel(PenetrationLineRepository penetrationLineRepository) {
        super(penetrationLineRepository);
        Intrinsics.checkNotNullParameter(penetrationLineRepository, "repository");
        this.repository = penetrationLineRepository;
    }

    public PenetrationLineRepository getRepository() {
        return this.repository;
    }

    public void getPenetrationExam(int i, ResponseListener<Penetration> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getPenetrationExam(i, responseListener);
        Log.i("ContentValues", "getPenetrationExam");
    }

    public void savePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(penetration, "penetration");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.savePenetrationFicheFromSqlManager(penetration, responseListener);
        Log.i("ContentValues", "savePenetrationFiche");
    }

    public void updatePenetrationFiche(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(penetration, "penetration");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.updatePenetrationFicheFromSqlManager(penetration, responseListener);
        Log.i("ContentValues", "updatePenetrationFiche");
    }

    public void getTableList(Class<?> cls, String str, String[] strArr, String str2, String str3, String str4, PenetrationActivity.PenetrationLineResponseListener responseListener) {
        Intrinsics.checkNotNullParameter(cls, "table");
        Intrinsics.checkNotNullParameter(str, "selection");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSqlManager().getTableList(cls, str, strArr, str2, str3, str4, responseListener);
        Log.i(getTag(), "getTableList");
    }
}
