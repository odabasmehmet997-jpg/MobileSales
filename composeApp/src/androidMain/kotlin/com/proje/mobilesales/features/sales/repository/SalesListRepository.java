package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class SalesListRepository extends BaseSalesRepository implements ISalesListRepository {
    public void getCustomerRiskLimit(final int i2, final ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getBaseErp().getRiskLimit(i2, responseListener);
    }
    public void getOrderFicheStatus(final ArrayList<String> ficheRefList, final ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheRefList, "ficheRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getBaseErp().getOrderFicheStatus(ficheRefList, responseListener);
    }
    public Disposable getEDocumentContentFromLocal(final int i2, final ResponseListener<?> responseListener, final SalesType salesType) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        return this.getBaseRepository().getSqlManager().getEDocumentContent(i2, responseListener, salesType);
    }
}
