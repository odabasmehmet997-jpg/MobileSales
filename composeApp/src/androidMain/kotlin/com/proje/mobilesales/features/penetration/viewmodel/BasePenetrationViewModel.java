package com.proje.mobilesales.features.penetration.viewmodel;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.penetration.repository.BasePenetrationRepository;
import kotlin.jvm.internal.Intrinsics;

public class BasePenetrationViewModel extends BaseViewModel {
    private final BasePenetrationRepository repository;
    private final String tag = "BasePenetrationViewModel";
    public BasePenetrationViewModel(BasePenetrationRepository basePenetrationRepository) {
        super(basePenetrationRepository);
        Intrinsics.checkNotNullParameter(basePenetrationRepository, "bRepository");
        this.repository = basePenetrationRepository;
    }
    public final String getTag() {
        return this.tag;
    }
    public final void deletePenetrationFicheLocal(int i, int i2, ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.deletePenetrationFicheLocalFromSqlManager(i, i2, responseListener);
        Log.i("ContentValues", "deletePenetrationFicheLocal");
    }
    public final void getCheckRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(workTimeControlProcessType, "workTimeControlProcessType");
        Intrinsics.checkNotNullParameter(responseListener, "checkWorkResponseListener");
        this.repository.checkRemoteWorkTimeControl(workTimeControlProcessType, responseListener);
        Log.i("ContentValues", "GetCheckRemoteWorkTimeControl");
    }
}
