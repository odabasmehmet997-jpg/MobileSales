package com.proje.mobilesales.features.sales.view.list;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.model.FicheQueryParams;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import com.proje.mobilesales.features.sales.repository.ISalesListRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;


public final class SalesListViewModel extends BaseSalesViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SalesListViewModel";
    private final ISalesListRepository repository; 
    public SalesListViewModel(ISalesListRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public ISalesListRepository getRepository() {
        return this.repository;
    }
    public SalesFicheMenuRights getSalesFicheMenuRights() {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = new SalesFicheMenuRights(null, null, null, null, null, false, false, 127, null);
        try {
            BuildersKt.runBlocking (null, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (SalesFicheMenuRights) refObjectRef.element;
    }
    public boolean getSalesShowDetail(FicheType ficheType) {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new SalesListViewModelgetSalesShowDetail1(this, refBooleanRef, ficheType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public Disposable getEDocumentContentFromLocal(int i2, ResponseListener<?> responseListener, SalesType salesType) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable eDocumentContentFromLocal = this.repository.getEDocumentContentFromLocal(i2, responseListener, salesType);
        Log.i(getTAG(), "getEDocumentContentFromLocal");
        return eDocumentContentFromLocal;
    }
    public Disposable getSalesCursorToList(Cursor cursor, SalesListFragment.SalesListResponseListener responseListener) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable salesCursorToListFromSqlManager = this.repository.getSalesCursorToListFromSqlManager(cursor, responseListener);
        Log.i(getTAG(), "getSalesCursorToList");
        return salesCursorToListFromSqlManager;
    }
    public void getSalesOrderOne(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesOrderOneFromSqlManager(i2, responseListener);
        Log.i(getTAG(), "getSalesOrderOne");
    }
    public void deleteSalesFicheLocal(int i2, SalesType salesType, int i3, ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.deleteSalesFicheLocalFromSqlManager(i2, salesType, i3, responseListener);
        Log.i(getTAG(), "deleteSalesFicheLocal");
    }
    public void deleteSalesFicheListLocal(List<Integer> logicalRefList, SalesType salesType, ResponseListener<List<Integer>> responseListener) {
        Intrinsics.checkNotNullParameter(logicalRefList, "logicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.deleteSalesListFicheLocalFromSqlManager(logicalRefList, salesType, responseListener);
        Log.i(getTAG(), "deleteSalesFicheListLocal");
    }
    public void getSalesWhTransfer(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesWhTransferFromSqlManager(i2, responseListener);
        Log.i(getTAG(), "getSalesWhTransfer");
    }
    public void getSalesInvoiceExam(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesInvoiceExamFromSqlManager(i2, responseListener);
        Log.i(getTAG(), "getSalesInvoiceExam");
    }

    public boolean canSendEDocuments() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }

    public boolean canUseEDocumentOperations() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }

    public void printFiche(Context context, FicheType ficheType, int i2, int i3, boolean z, int i4) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            BuildersKt.runBlocking(null, new SalesListViewModelprintFiche1(this, context, ficheType, i2, i3, z, i4, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getIsOrderStatusStillEditable(FicheQueryParams ficheQueryParams, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(ficheQueryParams, "ficheQueryParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.isOrderStatusStillEditable(ficheQueryParams, responseListener);
        Log.i(getTAG(), "getIsOrderStatusStillEditable");
    }

    public void insertFicheNoParamBroadcastMessage(int i2, int i3) {
        try {
            BuildersKt.runBlocking(null, new SalesListViewModelinsertFicheNoParamBroadcastMessage1(this, i2, i3, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelInvoiceFiche(int i2, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.cancelInvoiceFiche(i2, responseListener);
        Log.i(getTAG(), "cancelInvoiceFiche");
    }

    public void createEDocumentDraft(int i2, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.createEDocumentDraft(i2, ficheType, responseListener);
        Log.i(getTAG(), "createEDocumentDraft");
    }

    public void createEDocumentDraftAndSend(int i2, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.createEDocumentDraftAndSend(i2, ficheType, responseListener);
        Log.i(getTAG(), "createEDocumentDraftAndSend");
    }

    public void sendRecvEDispatchDocuments(int i2, int i3, ResponseListener<EDocumentResponse> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.sendRecvEDispatchDocuments(i2, i3, responseListener);
        Log.i(getTAG(), "sendRecvEDispatchDocuments");
    }

    public void sendRecvEInvoiceDocuments(int i2, int i3, ResponseListener<EDocumentResponse> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.sendRecvEInvoiceDocuments(i2, i3, responseListener);
        Log.i(getTAG(), "sendRecvEInvoiceDocuments");
    }

    public void sendEArchiveDocuments(int i2, int i3, ResponseListener<EDocumentResponse> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.sendEArchiveDocuments(i2, i3, responseListener);
        Log.i(getTAG(), "sendEArchiveDocuments");
    }

    public void getCustomerRiskLimit(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerRiskLimit(i2, responseListener);
        Log.i(getTAG(), "getCustomerRiskLimit");
    }

    public void getOrderFicheStatus(ArrayList<String> ficheRefList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheRefList, "ficheRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderFicheStatus(ficheRefList, responseListener);
        Log.i(getTAG(), "getOrderFicheStatus");
    }

    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getTAG() {
            return SalesListViewModel.TAG;
        }
    }
}
