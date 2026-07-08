package com.proje.mobilesales.features.sales.view.validation;

import android.util.Log;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.repository.SalesValidationRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;


public final class SalesValidationViewModel extends BaseSalesViewModel {
    private final SalesValidationRepository repository;
 
    public SalesValidationViewModel(SalesValidationRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public SalesValidationRepository getRepository() {
        return this.repository;
    }

    public void getOrderValidationList(int i2, int i3, String filter, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderValidationList(i2, i3, filter, responseListener);
        Log.i(getTAG(), "getOrderValidationList");
    }

    public void readOrderFiche(ArrayList<?> ficheRef, DataObjectType dataObjectType, Sales sales, List<?> list, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheRef, "ficheRef");
        Intrinsics.checkNotNullParameter(dataObjectType, "dataObjectType");
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.readOrderFiche(ficheRef, dataObjectType, sales, list, responseListener);
        Log.i(getTAG(), "readOrderFiche");
    }

    public List<ClCard> getTableForClCard(Class<ClCard> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            BuildersKt.runBlocking(null, new SalesValidationViewModelgetTableForClCard1(this, refObjectRef, tableClass, selection, selectionArgs, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }

    public boolean getVATDefaultValue() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new SalesValidationViewModelgetVATDefaultValue1(refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }

    public void updateOrderDispatchable(List<Integer> orderLogicalRefList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(orderLogicalRefList, "orderLogicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.updateOrderDispatchable(orderLogicalRefList, responseListener);
        Log.i(getTAG(), "updateOrderDispatchable");
    }

    public void updateOrderUnDispatchable(List<Integer> orderLogicalRefList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(orderLogicalRefList, "orderLogicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.updateOrderUnDispatchable(orderLogicalRefList, responseListener);
        Log.i(getTAG(), "updateOrderUnDispatchable");
    }

    public void rejectOrder(String orderNumber, String customerCode, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        Intrinsics.checkNotNullParameter(customerCode, "customerCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.rejectOrder(orderNumber, customerCode, responseListener);
        Log.i(getTAG(), "rejectOrder");
    }
}
