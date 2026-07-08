package com.proje.mobilesales.features.customer.view.operation;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import com.proje.mobilesales.features.model.CustomerReports;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.*;
import kotlinx.coroutines.BuildersKt;

public final class CustomerOperationViewModel extends BaseCustomerViewModel {
    final String TAG;
    final ICustomerOperationRepository repository;
    public CustomerOperationViewModel(ICustomerOperationRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.TAG = "CustomerOperationViewModel";
    } 
    public int getClEInvoiceUser(int i2) {
        Ref.IntRef refIntRef = new Ref.IntRef();
        try {
            BuildersKt.runBlocking(null, new CustomerOperationViewModelgetClEInvoiceUser1(this, refIntRef, i2, null), 1, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refIntRef.element;
    }
 
    public boolean getCanCreateInvoiceForEInvoiceCustomer() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new C2672x824a8aee(this, refBooleanRef, null), 1, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }  
    public String getReportXML(int i2) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new CustomerOperationViewModelgetReportXML1(this, refObjectRef, i2, null), 1, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    } 
    public int getSaveObject(Report report) {
        Intrinsics.checkNotNullParameter(report, "report");
        Ref.IntRef refIntRef = new Ref.IntRef();
        try {
            BuildersKt.runBlocking(null, new CustomerOperationViewModelgetSaveObject1(this, refIntRef, report, null), 1, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refIntRef.element;
    }  
    public List<UserReports> getTableForUserReports(Class<UserReports> tableClass, String selection, String[] selectionArgs, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = CollectionsKt.emptyList();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetTableForUserReports1(this, refObjectRef, tableClass, selection, selectionArgs, str, str2, str3, null), 1, null);
        return (List) refObjectRef.element;
    } 
    public boolean getIsVisit() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsVisit1(this, refBooleanRef, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    } 
    public boolean getIsPenetration() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsPenetration1(this, refBooleanRef, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    } 
    public   boolean getIsCustomerReport() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            BuildersKt.runBlocking (null, new CustomerOperationViewModelgetIsCustomerReport1(this, refBooleanRef, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    } 
    public   boolean getIsReceipt() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking (null, new CustomerOperationViewModelgetIsReceipt1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public   boolean getIsSales() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsSales1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsOrder() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsOrder1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsCash() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsCash1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsCase() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsCase1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsCreditCard() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsCreditCard1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsCheque() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsCheque1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsDeed() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsDeed1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsInvoice() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsInvoice1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsReturnInvoice() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsReturnInvoice1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsRetailInvoice() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsRetailInvoice1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsDispatch() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsDispatch1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsReturnDispatch() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsReturnDispatch1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }
    public boolean getIsOneToOneChange() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new CustomerOperationViewModelgetIsOneToOneChange1(this, refBooleanRef, null), 1, null);
        return refBooleanRef.element;
    }

    public CustomerReports getCustomerReports() {
        Log.e(this.TAG, "getCustomerReports");
        return this.repository.getCustomerReports();
    }
}
