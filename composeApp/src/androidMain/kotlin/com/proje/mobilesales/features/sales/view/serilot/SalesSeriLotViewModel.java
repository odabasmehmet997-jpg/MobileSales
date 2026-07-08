package com.proje.mobilesales.features.sales.view.serilot;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.sales.repository.SalesSeriLotRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;

public final class SalesSeriLotViewModel extends BaseSalesViewModel {
    private final SalesSeriLotRepository repository;
    public SalesSeriLotViewModel(SalesSeriLotRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public SalesSeriLotRepository getRepository() {
        return this.repository;
    }
    public boolean getSurplusAmountEnabled() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new SalesSeriLotViewModelgetSurplusAmountEnabled1(refBooleanRef, this, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public void getSerialLotList(SerialLotListQueryParam queryParam, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(queryParam, "queryParam");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getSerialLotList(queryParam, responseListener);

        } catch (Exception e) {
            responseListener.onFailure(e);
        }
        Log.i(getTAG(), "getSerialLotList");
    }

    public  String getMainUnitCode(String itemCode) {
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new SalesSeriLotViewModelgetMainUnitCode1(refObjectRef, this, itemCode, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
}
