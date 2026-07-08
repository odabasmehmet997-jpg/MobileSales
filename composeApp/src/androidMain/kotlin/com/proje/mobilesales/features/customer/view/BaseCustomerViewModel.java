package com.proje.mobilesales.features.customer.view;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.IBaseCustomerRepository;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt ;
import java.util.Collections;
import java.util.List;


public class BaseCustomerViewModel extends BaseViewModel {
    final String TAG = "BaseCustomerViewModel";
    final IBaseCustomerRepository repository;
    public BaseCustomerViewModel(IBaseCustomerRepository iBaseCustomerRepository) {
        super (iBaseCustomerRepository);
        Intrinsics.checkNotNullParameter (iBaseCustomerRepository, "bRepository");
        this.repository = iBaseCustomerRepository;
    }
    public final void getCheckRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter (workTimeControlProcessType, "workTimeControlProcessType");
        Intrinsics.checkNotNullParameter (responseListener, "checkWorkResponseListener");
        this.repository.checkRemoteWorkTimeControl (workTimeControlProcessType, responseListener);
        Log.i (this.TAG, "GetCheckRemoteWorkTimeControl");
    }
    public final <T> Cursor getRowQueryInReadableDatabase(String str, String[] strArr) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef ();
        Object unused = BuildersKt.runBlocking(null, new BaseCustomerViewModelgetRowQueryInReadableDatabase1(this, refObjectRef, str, strArr, null), 1, null);
        T t = (T) refObjectRef.element;
        checkNotNull (t);
        return (Cursor) t;
    }
    public final Object getUpdateCustomer(int r6, Continuation<? super Unit> r7) {
        throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.BaseCustomerViewModel.getUpdateCustomer(int, kotlin.coroutines.Continuation):java.lang.Object");
    }
    public final Object updateShipAddress(int r6, Continuation<? super Unit> r7) {
        throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.BaseCustomerViewModel.updateShipAddress(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void getCustomerCampaignPoint(int i) {
        Object unused = BuildersKt.runBlocking(null, new BaseCustomerViewModelgetCustomerCampaignPoint1(this, i, null), 1, null);
    }
    public final Object getDBVal(Cursor cursor, String str, ColType colType) {
        Intrinsics.checkNotNullParameter (cursor, "c");
        Intrinsics.checkNotNullParameter (str, "fieldName");
        Intrinsics.checkNotNullParameter (colType, "cType");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef ();
        Object unused = BuildersKt.runBlocking(null, new BaseCustomerViewModelgetDBVal1(this, refObjectRef, cursor, str, colType, null), 1, null);
        Object t = refObjectRef.element;
        checkNotNull (t);
        return t;
    }
    public final List<ClCard> getTableForCustomers(String str, String[] strArr) {
        Exception e;
        List<ClCard> table = Collections.emptyList();
        Intrinsics.checkNotNullParameter (str, "selection");
        Intrinsics.checkNotNullParameter (strArr, "selectionArgs");
        List<ClCard> emptyList = CollectionsKt.emptyList ();
        try {
            table = this.repository.getISqlHelperCustomers ().getTable (ClCard.class, str, strArr);
            checkNotNull (table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.ClCard>");
        } catch (Exception e2) {
            e = e2;
        }
        try {
            Log.i (this.TAG, "getTableForCustomers");
            return table;
        } catch (Exception e3) {
            e = e3;
            emptyList = table;
            Log.e (this.TAG, "getTableForCustomers", e);
            return emptyList;
        }
    }
    public final Object getOneSqlManager(Context r5, String r6, ResponseListener<CustomerDetail> r7, Continuation<? super Unit> r8) {

        throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.BaseCustomerViewModel.getOneSqlManager(android.content.Context, java.lang.String, com.proje.mobilesales.core.interfaces.ResponseListener, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
