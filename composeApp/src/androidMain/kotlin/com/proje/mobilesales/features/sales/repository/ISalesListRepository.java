package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;

public interface ISalesListRepository extends IBaseSalesRepository {
    void getCustomerRiskLimit(int i2, ResponseListener<?> responseListener);
    Disposable getEDocumentContentFromLocal(int i2, ResponseListener<?> responseListener, SalesType salesType);
    void getOrderFicheStatus(ArrayList<String> arrayList, ResponseListener<?> responseListener);
}
