package com.proje.mobilesales.features.sales.view.order;

import android.util.Log;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.sales.repository.SalesOrderRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt; 
 
public final class SalesOrderViewModel extends BaseSalesViewModel {
    private final SalesOrderRepository repository;
 
    public SalesOrderViewModel(SalesOrderRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public SalesOrderRepository getRepository() {
        return this.repository;
    }
  
    public String getOrderShipmentType(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking (null, new SalesOrderViewModelgetOrderShipmentType1(this, refObjectRef, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }

    public void getOnlineSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList<Integer> arrayList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlineSalesOrderList(salesType, i2, z, arrayList, responseListener);
        Log.i(getTAG(), "getOnlineSalesOrderList");
    }
}
